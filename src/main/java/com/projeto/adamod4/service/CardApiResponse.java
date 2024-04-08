package com.projeto.adamod4.service;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CardApiResponse {
    @JsonProperty("data")
    private List<CardData> data;

    public List<CardData> getData() {
        return data;
    }

    public void setData(List<CardData> data){
        this.data = data;
    }
}
