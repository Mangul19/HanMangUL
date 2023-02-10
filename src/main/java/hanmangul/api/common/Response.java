package hanmangul.api.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private boolean success;
//    private String code;
    private Object data;
    private String message;
    private int code;
}
