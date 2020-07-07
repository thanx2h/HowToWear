package com.an.howtowear.config;

import com.an.howtowear.support.inter.HTWEnum;

public enum PreferenceKey implements HTWEnum {

    WEATHER_PUSH("weather_push", "weather_push"), // thunderstorm with light rain
    TIME_SETTING("time_setting", "time_setting"); // thunderstorm with rain

    private String name;
    private String value;

    PreferenceKey(String name, String value) {
        this.name = name;
        this.value = value;
    }


    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
