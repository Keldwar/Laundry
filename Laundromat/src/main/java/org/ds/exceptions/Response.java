package org.ds.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Класс Ответ - хранит сообщение о какой-либо операции
 */
public class Response {
    private String message;

    public Response(@JsonProperty("field_name") String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
