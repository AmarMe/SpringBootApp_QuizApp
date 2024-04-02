package com.amar.quizapp.Model;

public class SubmitResponse {
    private Integer id;
    private String response;

    public SubmitResponse(){}

    public SubmitResponse(Integer id, String response) {
        this.id = id;
        this.response = response;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
