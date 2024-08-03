package com.swiss.sharing.client.service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Valid
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String id;
    @NotBlank(message = "First name should be provided")
    private String firstName;
    @NotBlank(message = "Last name should be provided")
    private String lastName;

    @Pattern(message = "Email should be valid", regexp = "^(.+)@(\\S+)$")
    private String email;
    @NotBlank(message = "Password should not be empty")
    @Pattern(message = "Password should be provided", regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\\s).{8,15}$")
    private String password;
    private String avatar;
    @NotNull
    private AddressDTO address;

    private String publicKey;
}
