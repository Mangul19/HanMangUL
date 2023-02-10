package hanmangul.api.log.service;

import hanmangul.api.log.entity.ApiLog;
import hanmangul.api.log.vo.ReqBody;

public interface ApiLogService {
    void saveLog(ApiLog apiLog) throws Exception;

    void test(ReqBody reqBody) throws Exception;
}
