package hanmangul.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import hanmangul.api.common.ResponseHandler;
import hanmangul.common.utils.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Enumeration;

// TODO: 오류 코드 정의
@ControllerAdvice
@Controller
@Slf4j
public class CommonExceptionHandler {
    private static final String[] AJAX_KEY = { "x-requested-with", "ajax" };
    private static final String[] AJAX_VALUE = { "xmlhttprequest", "true" };

    private final static String RESOLVER_DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseBody
    public Object handleIllegalArgumentException(Exception e, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (isNotAjaxRequest(request)) {
            response.sendRedirect("/err");
            return RESOLVER_DEFAULT_ERROR_VIEW;
        }

        return ResponseHandler.fail(e.getMessage());
    }

    @ExceptionHandler(value = { SecurityException.class })
    @ResponseBody
    public Object handleSecurityException(Exception e, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (isNotAjaxRequest(request)) {
            response.sendRedirect("/err");
            return RESOLVER_DEFAULT_ERROR_VIEW;
        }
        return ResponseHandler.fail("login" + e.getMessage());
    }

    @ExceptionHandler(value = { BindException.class })
    @ResponseBody
    public Object handleBindException(Exception e, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (isNotAjaxRequest(request)) {
            response.sendRedirect("/err");
            return RESOLVER_DEFAULT_ERROR_VIEW;
        }

        return ResponseHandler.fail(Validator.getErrorMsg(bindingResult));
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws Exception {
        e.printStackTrace();
        if (isNotAjaxRequest(request)) {
            response.sendRedirect("/err");
            return RESOLVER_DEFAULT_ERROR_VIEW;
        }

        return ResponseHandler.fail(e.getMessage());
    }

    private boolean isNotAjaxRequest(HttpServletRequest request) {

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            if (Arrays.asList(AJAX_KEY).contains(key)) {
                String value = request.getHeader(key);
                if (Arrays.asList(AJAX_VALUE).contains(value)) {
                    return false;
                }
            }
        }

        String contentType = request.getContentType();
        return !contentType.equals(MediaType.APPLICATION_JSON_VALUE);
    }

    // ================
    // EgovConfigWebDispatcherServlet.configureHandlerExceptionResolvers 설정
    // =============== //

//    @ExceptionHandler(value = {DataAccessException.class})
//    public String handleDataAccessException(Exception e) {
////        return "cmm/error/dataAccessFailure";
//        return RESOLVER_DEFAULT_ERROR_VIEW;
//    }
//
//    @ExceptionHandler(value = {TransactionException.class})
//    public String handleTransactionException(Exception e) {
////        return "cmm/error/transactionFailure";
//        return RESOLVER_DEFAULT_ERROR_VIEW;
//    }
//
//    @ExceptionHandler(value = EgovBizException.class)
//    public String handleEgovBizException(Exception e) {
////        return "cmm/error/egovError";
//        return RESOLVER_DEFAULT_ERROR_VIEW;
//    }
//
//    @ExceptionHandler(value = AccessDeniedException.class)
//    public String handleAccessDeniedException(Exception e) {
////        return "cmm/error/accessDenied";
//        return RESOLVER_DEFAULT_ERROR_VIEW;
//    }
}
