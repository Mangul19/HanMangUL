package hanmangul.app.portal.main.dao;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import hanmangul.api.common.Response;
import hanmangul.app.portal.main.vo.MainVO;

public interface MainDAO {

    List<EgovMap> getMenu();

    List<EgovMap> getBoardList(MainVO mainVo);

    int getBoardCnt(MainVO mainVo);

    String getMenuName(MainVO mainVo);

    void putMapHome(MainVO mainVo);

    List<EgovMap> getMapHome();
}
