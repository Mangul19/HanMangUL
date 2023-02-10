package hanmangul.api.log.service;

import org.springframework.stereotype.Service;

import hanmangul.api.log.dao.ApiLogDAO;
import hanmangul.api.log.entity.ApiLog;
import hanmangul.api.log.vo.ReqBody;
import lombok.RequiredArgsConstructor;

@Service("apiLogService")
@RequiredArgsConstructor
public class ApiLogLogServiceImpl implements ApiLogService {

    private final ApiLogDAO apiLogDAO;

    @Override
    public void saveLog(ApiLog apiLog) {
        apiLogDAO.insertApiLog(apiLog);
    }

    // 테스트용도
    @Override
    public void test(ReqBody reqBody) throws Exception {
        ApiLog apiLog = ApiLog.builder().reqBody("이거는 데이터가 들어가면 오류에요").build();

        apiLogDAO.insertApiLog(apiLog);

        if (reqBody.getA().equals("service")) {
            throw new IllegalArgumentException("service error");
        }

    }
}
