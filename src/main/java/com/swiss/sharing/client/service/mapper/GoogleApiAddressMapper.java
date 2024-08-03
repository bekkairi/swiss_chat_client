package com.swiss.sharing.client.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiss.sharing.client.service.dto.AddressDTO;
import com.swiss.sharing.client.service.dto.googleApi.AddressComponent;
import com.swiss.sharing.client.service.dto.googleApi.AddressComponents;
import com.swiss.sharing.client.service.dto.googleApi.Addresses;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class GoogleApiAddressMapper {

    private static final List<String> cityType = List.of("administrative_area_level_1", "political");
    private static final List<String> countryType = List.of("country", "political");

    private static final List<String> postalCodeType = List.of("postal_code");

    private static final List<String> buildingNumberCodeType = List.of("street_number");

    ObjectMapper objectMapper = new ObjectMapper();


    public GoogleApiAddressMapper() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public AddressDTO fromGoogleJson(String data, AddressDTO input) {
        try {
            var addresses = objectMapper.readValue(data, Addresses.class);
            return map(addresses, input);
        } catch (JsonProcessingException ex) {
            log.error(ex.getMessage(), ex);
            return input;
        }
    }

    private AddressDTO map(Addresses addresses, AddressDTO input) {
        return addresses
                .getResults()
                .stream()
                .findFirst()
                .map(e -> map(e, input))
                .orElse(input);
    }

    private AddressDTO map(AddressComponents addressComponents, AddressDTO input) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setLocalisation(input.getLocalisation());
        addressDTO.setAddress(addressComponents.getFormattedAddress());

        addressDTO.setBuildingNumber(getLongName(addressComponents, buildingNumberCodeType));
        addressDTO.setCity(getLongName(addressComponents, cityType));
        addressDTO.setCountry(getLongName(addressComponents, countryType));
        addressDTO.setPostalCode(getLongName(addressComponents, postalCodeType));

        return addressDTO;
    }

    private String getLongName(AddressComponents addressComponents, List<String> types) {
        return addressComponents
                .getAddressComponents()
                .stream()
                .filter(e -> types.equals(e.getTypes()))
                .findAny()
                .map(AddressComponent::getLongName)
                .orElse("");
    }

}
