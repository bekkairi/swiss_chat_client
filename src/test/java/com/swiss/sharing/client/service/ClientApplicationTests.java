package com.swiss.sharing.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiss.sharing.client.service.dto.AddressDTO;
import com.swiss.sharing.client.service.dto.LocalisationDTO;
import com.swiss.sharing.client.service.dto.UserDTO;
import com.swiss.sharing.client.service.entity.UserToken;
import com.swiss.sharing.client.service.repositories.UserRepository;
import com.swiss.sharing.client.service.repositories.UserTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ClientApplicationTests {

    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    UserTokenRepository userTokenRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void clean() {
        userTokenRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void createUser() throws Exception {
        this.mockMvc
                .perform(
                        post("/api/v1/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(userForTest())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("cat"))
                .andExpect(jsonPath("$.publicKey").isNotEmpty());
    }

    @Test
    void creatingAnExistingUserShouldFail() throws Exception {
        mockMvc
                .perform(
                        post("/api/v1/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(userForTest())));

        mockMvc
                .perform(
                        post("/api/v1/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(userForTest())))
                .andExpect(status().is(CONFLICT.value()));
    }

    @Test
    void loginUser() throws Exception {
        mockMvc
                .perform(
                        post("/api/v1/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(userForTest())));

        this.mockMvc
                .perform(
                        post("/api/v1/users/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(userForTest())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("cat"))
                .andExpect(jsonPath("$.publicKey").isNotEmpty());

    }

    @Test
    void validateUser() throws Exception {
        var output = this.mockMvc
                .perform(
                        post("/api/v1/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(userForTest())))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();


        var userDto = mapper.readValue(output, UserDTO.class);
        var userToken = UserToken.builder().token(userDto.getPublicKey()).email(userDto.getEmail()).build();

        this.mockMvc
                .perform(
                        post("/api/v1/users/validate")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(userToken)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(userToken.getEmail()))
                .andExpect(jsonPath("$.firstName").value("cat"));

    }

    private UserDTO userForTest() {

        return UserDTO.builder()
                .email("mail@test.com")
                .password("Ilyes!2017")
                .firstName("cat")
                .lastName("bestCat")
                .address(AddressDTO.builder()
                        .buildingNumber("12")
                        .address("any where")
                        .city("no city")
                        .country("no country")
                        .build()
                ).build();
    }

    @Test
    void createUserWithLocalisation() throws Exception {
        var user=userForTest();
        LocalisationDTO localisationDTO= new LocalisationDTO();
        localisationDTO.setX(46.19392344999999F);
        localisationDTO.setY(6.14404957499999F);
        user.getAddress().setLocalisation(localisationDTO);

        this.mockMvc
                .perform(
                        post("/api/v1/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("cat"))
                .andExpect(jsonPath("$.publicKey").isNotEmpty())
                .andExpect(jsonPath("$.address.address").value("Rue Jean-Violette 4, 1205 Genève, Switzerland"));
    }

}
