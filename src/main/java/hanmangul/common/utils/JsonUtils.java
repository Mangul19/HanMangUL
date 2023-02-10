package hanmangul.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.time.LocalDateTime;

@Slf4j
public class JsonUtils {
    public static String toJson(Object o) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter()).create();
        return gson.toJson(o);
    }

    public static <T> T fromJson(String json, Class<T> t) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter()).create();
        return gson.fromJson(json, t);
    }

    public static <T> T fromJson(Object json, Class<T> t) {
        ObjectMapper mapper = new ObjectMapper();
        String str = null;
        try {
            str = mapper.writeValueAsString(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return fromJson(str, t);
    }

    public static <T> T readJsonFile(String path, Class<T> t) {
        try (FileReader fileReader = new FileReader(path)) {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter()).create();
            JsonReader jsonReader = gson.newJsonReader(fileReader);
            return gson.fromJson(jsonReader, t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJsonPretty(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return "json paring error";
        }
    }

    public static String toJsonObjectMapper(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return "json paring error";
        }
    }
}
