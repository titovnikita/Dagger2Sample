package com.nick.dagger2sample.database.models.responses;

import com.google.gson.annotations.SerializedName;
import com.nick.dagger2sample.database.models.Weather;

public class WeatherResponse {
    private long id;
    @SerializedName("dt")
    private long time;
    @SerializedName("cod")
    private int responseCode;
    @SerializedName("name")
    private String city;
    @SerializedName("weather")
    private Weather weatherInfo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Weather getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(Weather weatherInfo) {
        this.weatherInfo = weatherInfo;
    }
}
