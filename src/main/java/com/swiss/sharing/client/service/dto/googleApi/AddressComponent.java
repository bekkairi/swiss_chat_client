package com.swiss.sharing.client.service.dto.googleApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressComponent {
    @JsonProperty("long_name")
    private String longName;
    private String shortName;
    private List<String> types;
}
