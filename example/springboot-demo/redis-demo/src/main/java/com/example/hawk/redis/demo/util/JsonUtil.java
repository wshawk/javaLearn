package com.example.hawk.redis.demo.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

public class JsonUtil {

    private static final Gson gson = new GsonBuilder().create();

    private JsonUtil() {
    }

    public static <T> T deserialize(String jsonStr, TypeToken<T> typeToken) {
        return gson.fromJson(jsonStr, typeToken.getType());
    }

    public static <T> T deserialize(String jsonStr, Class<T> clazz) {
        return gson.fromJson(jsonStr, clazz);
    }

    public static String serialize(Object o) {
        return gson.toJson(o);
    }

    public static void main(String[] args) {
        Map<String, Map<String, String>> map = new HashMap();
        Map<String, String> subMap = new HashMap();
        map.put("subMap", subMap);
        subMap.put("key", "value");
        String json = serialize(subMap);
        System.out.println(json);
        System.out.println(serialize(deserialize(json, new TypeToken<Map<String, Object>>() {
        })));
    }
}