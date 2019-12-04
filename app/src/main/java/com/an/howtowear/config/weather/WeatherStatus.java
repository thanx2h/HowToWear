package com.an.howtowear.config.weather;

import com.an.howtowear.support.inter.HTWEnum;

public enum WeatherStatus implements HTWEnum {

    THUNDERSTORM1("200", "11d"), // thunderstorm with light rain
    THUNDERSTORM2("201", "11d"), // thunderstorm with rain
    THUNDERSTORM3("202", "11d"), // thunderstorm with heavy rain
    THUNDERSTORM4("210", "11d"), // light thunderstorm
    THUNDERSTORM5("211", "11d"), // thunderstorm
    THUNDERSTORM6("212", "11d"), // heavy thunderstorm
    THUNDERSTORM7("221", "11d"), // ragged thunderstorm
    THUNDERSTORM8("230", "11d"), // thunderstorm with light drizzle
    THUNDERSTORM9("231", "11d"), // thunderstorm with drizzle
    THUNDERSTORM10("232", "11d"); // thunderstorm with heavy drizzle

    private String name;
    private String value;

    WeatherStatus(String name, String value) {
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
