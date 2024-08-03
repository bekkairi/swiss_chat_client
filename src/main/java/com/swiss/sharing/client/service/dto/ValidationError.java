package com.swiss.sharing.client.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationError<T> {
    private T object;
    private String field;
    private String error;
}
