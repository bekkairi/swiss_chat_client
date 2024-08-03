package com.swiss.sharing.client.service.mapper;

import com.swiss.sharing.client.service.dto.AddressDTO;
import com.swiss.sharing.client.service.dto.LocalisationDTO;
import com.swiss.sharing.client.service.dto.UserDTO;
import com.swiss.sharing.client.service.entity.User;
import com.swiss.sharing.client.service.security.SecurityUtil;
import org.mapstruct.*;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {


    @Mapping(target = "password", ignore = true)
    @Mapping(expression = "java(userPublicKey(user))", target = "publicKey")
    UserDTO map(User user);

    default GeoJsonPoint map(LocalisationDTO localisation) {
        if (localisation==null){
            return null;
        }
        return new GeoJsonPoint(localisation.getX(), localisation.getY());
    }

    User map(UserDTO userDTO);

    default UserDTO simpleMap(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .address(AddressDTO.builder().city(user.getAddress().getCity()).build())
                .build();
    }

    @AfterMapping
    default void userCredentials(UserDTO userDTO
            , @MappingTarget User user) {
        user.setPrivateKey(SecurityUtil.saveKeysToBase64());
        user.setPassword(SecurityUtil.hashWith512(userDTO.getPassword()));
    }

    @AfterMapping
    default String userPublicKey(User user) {
        return SecurityUtil.publicKeyToBase64(user.getPrivateKey());

    }

}
