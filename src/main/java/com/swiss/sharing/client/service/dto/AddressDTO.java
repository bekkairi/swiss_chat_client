package com.swiss.sharing.client.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private String buildingNumber;
    private String postalCode;
    private String address;
    private String city;
    private String country;
    private LocalisationDTO localisation;
}
