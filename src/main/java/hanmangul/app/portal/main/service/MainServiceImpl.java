package hanmangul.app.portal.main.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import hanmangul.api.common.Response;
import hanmangul.api.common.ResponseHandler;
import hanmangul.app.portal.main.dao.MainDAO;
import hanmangul.app.portal.main.vo.MainVO;
import hanmangul.common.paging.PagingHelper;
import lombok.RequiredArgsConstructor;

@Service("mainService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

private final MainDAO mainDAO;

    @Override
    public String getmain(HttpServletRequest request) {
        request.getSession().removeAttribute("writeTrue"); 
        return "app/portal/main/main";
    }

    @Override
    public String getMenuInPath(HttpServletRequest request, ModelMap model, MainVO mainVo) {
        String MenuName = mainDAO.getMenuName(mainVo);
        if (MenuName == null || MenuName.isEmpty()) {
            return "error";
        }

        request.getSession().setAttribute("PageName", mainVo.getPageName());
        request.getSession().setAttribute("PagePath", mainVo.getPagePath());
        model.addAttribute("Path", mainVo.getPageName());

        if (mainVo.getPagePath().equals("write")) {
            request.getSession().setAttribute("writeTrue", "True"); 
        } else {
            request.getSession().removeAttribute("writeTrue"); 
        }

        return "app/portal/board/" + mainVo.getPagePath();
    }

    @Override
    public Response getMenu() {
        List<EgovMap> MenuList = mainDAO.getMenu();

        Map<String, Object> rtn = new HashMap<>();
        rtn.put("MenuList", MenuList);

        return ResponseHandler.success(rtn);
    }

    @Override
    public Response getBoardList(HttpServletRequest request, MainVO mainVo) {
        mainVo.setPagePath(String.valueOf(request.getSession().getAttribute("PageName")));

        PaginationInfo paginationInfo = PagingHelper.getDefaultPaginationInfo(mainVo);
        int BoardCnt = mainDAO.getBoardCnt(mainVo);
        paginationInfo.setTotalRecordCount(BoardCnt);
        mainVo.setFirstIndex(paginationInfo.getFirstRecordIndex());

        List<EgovMap> BoardList = mainDAO.getBoardList(mainVo);

        Map<String, Object> rtn = new HashMap<>();
        rtn.put("boardList", BoardList);
        rtn.put("paginationInfo", paginationInfo);

        return ResponseHandler.success(rtn);
    }

    @Override
    public Response putBoardInfo(HttpServletRequest request, MainVO mainVo) {
        String Write = (String) request.getSession().getAttribute("writeTrue"); 

        if (Write == null || Write.isEmpty()) {
            return ResponseHandler.fail("비정상적인 경로로 접근하였습니다.", 001);
        }

        request.getSession().removeAttribute("writeTrue"); 
        return ResponseHandler.success("suc", "정상적으로 게시글을 등록하였습니다.");
    }

    @Override
    public Response putMapHome(HttpServletRequest request, MainVO mainVo) {
        mainDAO.putMapHome(mainVo);

        return ResponseHandler.success("suc");
    }

    @Override
    public Response getMapHome() {
        List<EgovMap> MapHomeList = mainDAO.getMapHome();

        Map<String, Object> rtn = new HashMap<>();
        rtn.put("MapHomeList", MapHomeList);

        return ResponseHandler.success(rtn);
    }
}
