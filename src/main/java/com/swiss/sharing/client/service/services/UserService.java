package com.swiss.sharing.client.service.services;

import com.swiss.sharing.client.service.dto.UserDTO;
import com.swiss.sharing.client.service.entity.User;
import com.swiss.sharing.client.service.entity.UserToken;
import com.swiss.sharing.client.service.exceptions.UserExistsException;
import com.swiss.sharing.client.service.mapper.GoogleApiAddressMapper;
import com.swiss.sharing.client.service.mapper.UserMapper;
import com.swiss.sharing.client.service.repositories.UserRepository;
import com.swiss.sharing.client.service.repositories.UserTokenRepository;
import com.swiss.sharing.client.service.security.SecurityUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.net.URI;

import static java.lang.String.format;

@Data
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserTokenRepository userTokenRepository;
    private final KafkaProducer kafkaProducer;
    @Value("${swiss.sharing.topic.new_client}")
    @Autowired
    private String newUserTopic;

    private RestTemplate restTemplate = new RestTemplate();
    private GoogleApiAddressMapper googleApiAddressMapper = new GoogleApiAddressMapper();

    @Value("${swiss.sharing.google.api.geoLocalisation.url}")
    private String urlGoogleApi;
    @Value("${swiss.sharing.google.api.geoLocalisation.api_key}")
    private String googleApiKey;

    public UserDTO create(UserDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new UserExistsException(format("User with email %s exists", dto.getEmail()));
        }
        getAddressFromGeoLocalisation(dto);
        var createdUser = createUserToken(userRepository.save(userMapper.map(dto)));
        Flux.just(createdUser).subscribe(t -> kafkaProducer.sendMessage(t, newUserTopic));
        return createdUser;
    }

    public UserDTO getUser(UserDTO dto) {
        return userRepository.findByEmail(dto.getEmail()).filter(
                storedUser -> storedUser.getPassword().equals(SecurityUtil.hashWith512(dto.getPassword()))
        ).map(this::createUserToken).orElseThrow(() -> new BadCredentialsException("User not found or password not match"));

    }

    public UserDTO validateUser(String email, String publicKey) {
        return userTokenRepository.findByEmailAndToken(email, publicKey)
                .map(e -> userRepository.findByEmail(e.getEmail()))
                .orElseThrow(() -> new BadCredentialsException(format("User %s not found or key %s expired", email, publicKey)))
                .map(userMapper::map)
                .orElseThrow(() -> new BadCredentialsException("User not found or key expired"));
    }

    public Page<UserDTO> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::simpleMap);
    }

    private UserDTO createUserToken(User user) {
        var userDTO = userMapper.map(user);
        userTokenRepository.save(UserToken.builder()
                .email(userDTO.getEmail())
                .token(userDTO.getPublicKey())
                .build()
        );

        return userDTO;

    }

    private void getAddressFromGeoLocalisation(UserDTO dto) {
        if (dto.getAddress().getLocalisation()==null ||  dto.getAddress().getLocalisation().getX()==null){
            return;
        }
        Float x = dto.getAddress().getLocalisation().getX();
        Float y = dto.getAddress().getLocalisation().getY();

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(urlGoogleApi)
                // Add query parameter
                .queryParam("key", googleApiKey)
                .queryParam("latlng", format("%f,%f", x, y));

        URI uri = builder.build().toUri();
        ResponseEntity<String> result = restTemplate.postForEntity(uri, null, String.class);
        dto.setAddress(googleApiAddressMapper.fromGoogleJson(result.getBody(), dto.getAddress()));
    }

}
