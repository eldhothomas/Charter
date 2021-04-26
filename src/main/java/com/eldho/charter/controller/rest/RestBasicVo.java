package com.eldho.charter.controller.rest;

public class RestBasicVo {

    private final long id;
    private final String content;

    public RestBasicVo(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
