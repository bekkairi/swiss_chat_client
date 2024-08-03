package com.swiss.sharing.client.service.dto.googleApi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Addresses {
    private List<AddressComponents> results;
}
