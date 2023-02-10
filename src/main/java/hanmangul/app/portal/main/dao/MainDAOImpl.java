package hanmangul.app.portal.main.dao;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import hanmangul.api.common.Response;
import hanmangul.app.portal.main.vo.MainVO;
import hanmangul.common.dao.SqlComAbstractDAO;

@Repository
public class MainDAOImpl extends SqlComAbstractDAO implements MainDAO {

    @Override
    public List<EgovMap> getMenu() {
        return selectList("MainDAO.getMenu");
    }

    @Override
    public List<EgovMap> getBoardList(MainVO mainVo) {
        return selectList("MainDAO.getBoardList", mainVo);
    }

    @Override
    public int getBoardCnt(MainVO mainVo) {
        return selectOne("MainDAO.getBoardCnt", mainVo);
    }

    @Override
    public String getMenuName(MainVO mainVo) {
        return selectOne("MainDAO.getMenuName", mainVo);
    }

    @Override
    public void putMapHome(MainVO mainVo) {
        insert("MainDAO.putMapHome", mainVo);
    }

    @Override
    public List<EgovMap> getMapHome() {
        return selectList("MainDAO.getMapHome");
    }
}
