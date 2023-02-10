package hanmangul.api.log.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hanmangul.api.common.Response;
import hanmangul.api.common.ResponseHandler;
import hanmangul.api.log.service.ApiLogService;
import hanmangul.api.log.vo.ReqBody;
import lombok.RequiredArgsConstructor;

// 테스트용도
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final ApiLogService apiLogService;

    @GetMapping("/v1/aaa")
    public Response aaa() {
        return ResponseHandler.success();
    }

    @PostMapping(value = "/v1/bbb", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response bbb(@RequestBody ReqBody body) throws Exception {

        System.out.println("body = " + body);

        if (body.getA().equals("error")) {
            throw new SecurityException();
        }

        apiLogService.test(body);

        return ResponseHandler.success();
    }
}
