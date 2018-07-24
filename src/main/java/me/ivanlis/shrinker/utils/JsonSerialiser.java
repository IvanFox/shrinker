package me.ivanlis.shrinker.utils;

import com.google.gson.Gson;
import java.lang.reflect.Type;

public enum JsonSerialiser {
    JSON_SERIALISER;

    private final Gson gson;


    JsonSerialiser() {
        gson = new Gson();
    }

    public String toJson(Object o) {
        return gson.toJson(o);
    }

    public <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }
}
