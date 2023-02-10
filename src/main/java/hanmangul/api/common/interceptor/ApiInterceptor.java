package hanmangul.api.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import hanmangul.api.common.Response;
import hanmangul.api.common.ResponseHandler;
import hanmangul.api.common.filter.ApiFilterResponseWrapper;
import hanmangul.api.log.entity.ApiLog;
import hanmangul.api.log.service.ApiLogService;
import hanmangul.common.utils.CommonFnc;
import hanmangul.common.utils.JsonUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Slf4j
public class ApiInterceptor extends WebContentInterceptor {

    @Resource(name = "apiLogService")
    private ApiLogService apiLogService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        // TODO 권한 / 인증 처리
        if ("인증/권한이 실패했을 때".equals("")) {
            try {
                // 응답 메시지 생성
                Response res = ResponseHandler.fail("인증 실패");
                String resBody = JsonUtils.toJson(res);

                // 로그 기록
                ApiLog apiLog = createApiLog(request, resBody);
                apiLogService.saveLog(apiLog);
                response.getWriter().write(resBody);
                return false;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ApiLog apiLog = createApiLog(request, response);
        apiLogService.saveLog(apiLog);
    }

    private ApiLog createApiLog(HttpServletRequest request, String resBody) {
        String reqBody = CommonFnc.getRequestBody(request);

        return ApiLog.builder().clientIp(CommonFnc.getClientIp(request))
//                .clientNm() TODO : 프로젝트에 맞게 설정
                .reqUri(request.getRequestURI()).method(request.getMethod()).queryString(request.getQueryString()).reqBody(reqBody).createdDate(LocalDateTime.now()).resBody(resBody).build();
    }

    private ApiLog createApiLog(HttpServletRequest request, HttpServletResponse response) {
        String clientIp = CommonFnc.getClientIp(request);
        String requestURI = request.getRequestURI();
        String reqBody = CommonFnc.getRequestBody(request);
        String method = request.getMethod();
        String resBody = "";
        if (response instanceof ApiFilterResponseWrapper) {
            resBody = ((ApiFilterResponseWrapper) response).getDataStreamToString();
        }

        return ApiLog.builder().clientIp(clientIp)
//                .clientNm() TODO : 프로젝트에 맞게 설정
                .reqUri(requestURI).method(method).queryString(request.getQueryString()).reqBody(reqBody).createdDate(LocalDateTime.now()).resBody(resBody).build();
    }
}
