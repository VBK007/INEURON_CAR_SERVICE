package com.ineuron.carservice.response;

import lombok.Getter;

@Getter
public class Success {

    private String message;
    private Object payload;
    private Long count;

    public Success(final String message, final Object payload, final Long count) {
        this.message = message;
        this.payload = payload;
        this.count   = count;
    }

}
