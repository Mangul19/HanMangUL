package hanmangul.api.common;

public class ResponseHandler {
    public static Response success() {
        Response response = new Response();
        response.setSuccess(true);
//        response.setCode(code);
        response.setMessage("success");
        return response;
    }

    public static Response success(Object data) {
        Response response = new Response();
        response.setSuccess(true);
//        response.setCode(code);
        response.setData(data);
        response.setMessage("success");
        return response;
    }

    public static Response success(Object data, String message) {
        Response response = new Response();
        response.setSuccess(true);
//        response.setCode(code);
        response.setData(data);
        response.setMessage(message);
        return response;
    }

    public static Response fail(String message, int code) {
        Response response = new Response();
        response.setSuccess(false);
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static Response fail(String message) {
        Response response = new Response();
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }
}
