package hanmangul.common.utils.http;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Getter
@Setter
public class HttpConfig {
    private boolean useCache = false;
    private boolean doInput = true;
    private boolean doOutput = true;
    private final HashMap<String, String> headers = new HashMap<>();
    private int connectTimeOut = 30000;
    private int readTimeOut = 30000;
    private String method = "POST";
    private String contentType = APPLICATION_JSON_VALUE;

    public void useJwtToken(String token) {
        if (StringUtils.isNotEmpty(token)) {
            headers.put("Authorization", "Bearer " + token);
        }
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public void clearHeader() {
        headers.clear();
    }
}
