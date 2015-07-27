package com.example.jereczem.hasrpg.networking;

/**
 * Created by jereczem on 03.07.15.
 */
public class HttpResponse {
    private String message;
    private Integer code;

    public HttpResponse(Integer code, String message) {
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
