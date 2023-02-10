package hanmangul.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    /* 성공 */
    public final static String RESULT_CODE_SUCCESS = "SUCCESS";
    /* 부분 성공 */
    public final static String RESULT_CODE_PARTIAL_SUCCESS = "PARTIAL_SUCCESS";
    /* 결과 없음 */
    public final static String RESULT_CODE_NO_RESULT = "NO_RESULT";
    /* 부적절한 파라미터 */
    public final static String RESULT_CODE_INVALID_PARAMETER = "INVALID_PARAM";
    /* 인증 안됨 */
    public final static String RESULT_CODE_NO_AUTHENTICATION = "NO_AUTHENTICATION";
    /* 권한 없음 */
    public final static String RESULT_CODE_NO_AUTHORIZATION = "NO_AUTHORIZATION";
    /* 토큰 만료 */
    public final static String RESULT_CODE_TOKEN_EXPIRED = "EXPIRED";
    /* 서버 오류 */
    public final static String RESULT_CODE_FAIL = "FAIL";

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj, String code, Integer totalCnt) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);
        map.put("code", code);

        if (totalCnt != 0) {
            map.put("totalCnt", totalCnt);
        }
        return new ResponseEntity<Object>(map, status);
    }

    // 성공
    public static ResponseEntity<Object> responseSuccess(String message) {
        return generateResponse(message, HttpStatus.OK, null, RESULT_CODE_SUCCESS, 0);
    }

    // 성공
    public static ResponseEntity<Object> responseSuccess(String message, Object object) {
        return generateResponse(message, HttpStatus.OK, object, RESULT_CODE_SUCCESS, 0);
    }

    // 성공
    public static ResponseEntity<Object> responseSuccess(String message, Object object, Integer totalCnt) {
        return generateResponse(message, HttpStatus.OK, object, RESULT_CODE_SUCCESS, totalCnt);
    }

    // 부분 성공
    public static ResponseEntity<Object> responsePartialSuccess(String message) {
        return generateResponse(message, HttpStatus.OK, null, RESULT_CODE_PARTIAL_SUCCESS, 0);
    }

    // 부분 성공
    public static ResponseEntity<Object> responsePartialSuccess(String message, Object object) {
        return generateResponse(message, HttpStatus.OK, object, RESULT_CODE_PARTIAL_SUCCESS, 0);
    }

    // 결과 없음
    public static ResponseEntity<Object> responseNoResult(String message) {
        return generateResponse(message, HttpStatus.OK, null, RESULT_CODE_NO_RESULT, 0);
    }

    // 결과 없음
    public static ResponseEntity<Object> responseNoResult(String message, Object object) {
        return generateResponse(message, HttpStatus.OK, object, RESULT_CODE_NO_RESULT, 0);
    }

    // 부적절한 파라미터
    public static ResponseEntity<Object> responseInvalidParameter(String message) {
        return generateResponse(message, HttpStatus.OK, null, RESULT_CODE_INVALID_PARAMETER, 0);
    }

    // 부적절한 파라미터
    public static ResponseEntity<Object> responseInvalidParameter(String message, Object object) {
        return generateResponse(message, HttpStatus.OK, object, RESULT_CODE_INVALID_PARAMETER, 0);
    }

    // 권한 없음
    public static ResponseEntity<Object> responseNoAuthentication(String message) {
        return generateResponse(message, HttpStatus.OK, null, RESULT_CODE_NO_AUTHENTICATION, 0);
    }

    // 권한 없음
    public static ResponseEntity<Object> responseNoAuthentication(String message, Object object) {
        return generateResponse(message, HttpStatus.OK, object, RESULT_CODE_NO_AUTHENTICATION, 0);
    }

    // 권한 없음
    public static ResponseEntity<Object> responseNoAuth(String message) {
        return generateResponse(message, HttpStatus.OK, null, RESULT_CODE_NO_AUTHORIZATION, 0);
    }

    // 권한 없음
    public static ResponseEntity<Object> responseNoAuth(String message, Object object) {
        return generateResponse(message, HttpStatus.OK, object, RESULT_CODE_NO_AUTHORIZATION, 0);
    }

    // 토큰 만료
    public static ResponseEntity<Object> responseTokenExpired(String message) {
        return generateResponse(message, HttpStatus.OK, null, RESULT_CODE_TOKEN_EXPIRED, 0);
    }

    // 토큰 만료
    public static ResponseEntity<Object> responseTokenExpired(String message, Object object) {
        return generateResponse(message, HttpStatus.OK, object, RESULT_CODE_TOKEN_EXPIRED, 0);
    }

    // 서버 오류
    public static ResponseEntity<Object> responseFail(String message) {
        return generateResponse(message, HttpStatus.BAD_REQUEST, null, RESULT_CODE_FAIL, 0);
    }

    // 서버 오류
    public static ResponseEntity<Object> responseFail(String message, Object object) {
        return generateResponse(message, HttpStatus.BAD_REQUEST, object, RESULT_CODE_FAIL, 0);
    }

}
