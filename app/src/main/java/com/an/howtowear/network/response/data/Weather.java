package com.an.howtowear.network.response.data;

import com.google.gson.annotations.SerializedName;

public class Weather{

    @SerializedName("id")
    private String id;     //    list.weather.id Weather condition id

    @SerializedName("main")
    private String main; //    list.weather.main Group of weather parameters (Rain, Snow, Extreme etc.)

    @SerializedName("description")
    private String description; //    list.weather.description Weather condition within the group

    @SerializedName("icon")
    private String icon;//    list.weather.icon Weather icon id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id='" + id + '\'' +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}