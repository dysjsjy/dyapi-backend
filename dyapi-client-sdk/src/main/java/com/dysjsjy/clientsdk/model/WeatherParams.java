package com.dysjsjy.clientsdk.model;

import java.io.Serializable;

public class WeatherParams implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String city;

    private String type;

    public WeatherParams() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
