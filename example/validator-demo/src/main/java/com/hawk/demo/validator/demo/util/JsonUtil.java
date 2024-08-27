package com.hawk.demo.validator.demo.util;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonUtil {
    //序列化 Gson的LocalDateTime和String转化
    private static final JsonSerializer<LocalDateTime> jsonSerializerDateTime = (localDateTime, type, jsonSerializationContext)
            -> new JsonPrimitive(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    private static final JsonSerializer<LocalDate> jsonSerializerDate = (localDate, type, jsonSerializationContext)
            -> new JsonPrimitive(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    //反序列化 Gson的LocalDateTime和String转化
    private static final JsonDeserializer<LocalDateTime> jsonDeserializerDateTime = (jsonElement, type, jsonDeserializationContext)
            -> LocalDateTime.parse(jsonElement.getAsJsonPrimitive().getAsString(),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private static final JsonDeserializer<LocalDate> jsonDeserializerDate = (jsonElement, type, jsonDeserializationContext)
            -> LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString(),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            /* 更改先后顺序没有影响 */
            .registerTypeAdapter(LocalDateTime.class, jsonSerializerDateTime)
            .registerTypeAdapter(LocalDate.class, jsonSerializerDate)
            .registerTypeAdapter(LocalDateTime.class, jsonDeserializerDateTime)
            .registerTypeAdapter(LocalDate.class, jsonDeserializerDate)
            .create();

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