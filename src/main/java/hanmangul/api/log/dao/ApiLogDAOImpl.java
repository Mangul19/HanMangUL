package hanmangul.api.log.dao;

import org.springframework.stereotype.Repository;

import hanmangul.api.log.entity.ApiLog;
import hanmangul.common.dao.SqlComAbstractDAO;

@Repository
public class ApiLogDAOImpl extends SqlComAbstractDAO implements ApiLogDAO {
    @Override
    public void insertApiLog(ApiLog apiLog) {
        insert("ApiLogDAO.insertApiLog", apiLog);
    }
}
