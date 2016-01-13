package com.nick.dagger2sample.database.models;

import com.google.gson.annotations.SerializedName;

public class Weather {
    private long id;
    @SerializedName("main")
    private String weatherTitle;
    @SerializedName("description")
    private String weatherDescription;
    @SerializedName("icon")
    private String iconType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWeatherTitle() {
        return weatherTitle;
    }

    public void setWeatherTitle(String weatherTitle) {
        this.weatherTitle = weatherTitle;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }
}
