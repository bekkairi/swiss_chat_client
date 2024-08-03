package com.swiss.sharing.client.service.exceptions;

public class UserExistsException extends UserServiceException {

    public UserExistsException(String errorMsg) {
        super(errorMsg);
    }
}
