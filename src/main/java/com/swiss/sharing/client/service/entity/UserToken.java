package com.swiss.sharing.client.service.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
public class UserToken {
    @Id
    private String email;
    private String token;
}
