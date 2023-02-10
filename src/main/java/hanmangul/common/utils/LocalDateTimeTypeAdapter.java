package hanmangul.common.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Gson 은 JAVA 8 이후에 새로 나온 build-in class 를 지원하지 않는다. 따라서 이 클래스들을 처리해주기 위해
 * TypeAdapter 를 직접 작성하여 Gson 객체를 생성할 때 주입한다. 그냥 사용할 경우 Illgal reflective access
 * 경고가 발생한다.
 */
public class LocalDateTimeTypeAdapter extends TypeAdapter<LocalDateTime> {
    @Override
    public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
        if (localDateTime == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(localDateTime.toString());
        }
    }

    @Override
    public LocalDateTime read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        } else {
            String text = jsonReader.nextString();
            LocalDateTime parsed = null;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            if (isNotParsable(text, formatter)) {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                if (isNotParsable(text, formatter)) {
                    return null;
                }
            }

            parsed = LocalDateTime.parse(text, formatter);

            return parsed;
        }
    }

    private boolean isNotParsable(String text, DateTimeFormatter formatter) {
        try {
            LocalDateTime.parse(text, formatter);
        } catch (Exception e) {
            return true;
        }
        return false;
    }
}
