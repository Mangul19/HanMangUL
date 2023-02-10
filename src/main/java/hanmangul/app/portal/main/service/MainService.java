package hanmangul.app.portal.main.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import hanmangul.api.common.Response;
import hanmangul.app.portal.main.vo.MainVO;

public interface MainService {
    String getmain(HttpServletRequest request);

    Response getMenu();

    String getMenuInPath(HttpServletRequest request, ModelMap model, MainVO mainVo);

    Response getBoardList(HttpServletRequest request, MainVO mainVo);

    Response putBoardInfo(HttpServletRequest request, MainVO mainVo);

    Response putMapHome(HttpServletRequest request, MainVO mainVo);

    Response getMapHome();
}
