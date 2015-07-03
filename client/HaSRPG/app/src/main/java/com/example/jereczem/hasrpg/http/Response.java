package com.example.jereczem.hasrpg.http;

/**
 * Created by jereczem on 03.07.15.
 */
public class Response {
    private String message;
    private Integer code;

    public Response(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
