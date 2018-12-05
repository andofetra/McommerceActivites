package com.mexpedition.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExpeditionNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -7174131234798146339L;

    public ExpeditionNotFoundException(Integer id) {
        super("Id not found = " + id);
    }
}
