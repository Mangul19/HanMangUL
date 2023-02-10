package hanmangul.api.log.web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hanmangul.api.common.Response;
import hanmangul.api.common.ResponseHandler;
import hanmangul.api.log.vo.ReqBody;

@RestController
public class TestController {
    @RequestMapping("/json")
    public Response jsonApi(HttpServletRequest request, @RequestBody ReqBody reqBody) {
        Map<String, Object> rtn = new HashMap<>();
        rtn.put("method", request.getMethod());
        rtn.put("requestBody", reqBody);
        rtn.put("contentType", request.getContentType());
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            rtn.put("header-" + key, value);
        }

        return ResponseHandler.success(rtn);
    }

    @RequestMapping("/form-url")
    public Response formUrlApi(HttpServletRequest request, @ModelAttribute ReqBody reqBody) {
        Map<String, Object> rtn = new HashMap<>();
        rtn.put("method", request.getMethod());
        rtn.put("requestBody", reqBody);
        rtn.put("contentType", request.getContentType());
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            rtn.put("header-" + key, value);
        }

        return ResponseHandler.success(rtn);
    }
}
