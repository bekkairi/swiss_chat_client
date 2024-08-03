package com.swiss.sharing.client.service.exceptions;

public class UserServiceException extends RuntimeException {
    public UserServiceException(Exception e) {
        super(e);
    }

    public UserServiceException(String errorMsg) {
        super(errorMsg);
    }
}
