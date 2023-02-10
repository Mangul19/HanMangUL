package hanmangul.app.portal.main.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hanmangul.api.common.Response;
import hanmangul.app.portal.main.service.MainService;
import hanmangul.app.portal.main.vo.MainVO;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainApiController {

    private final MainService mainService;

    //공통 코드 호출
    @GetMapping("/getMenu")
    public Response getMenu() {
        return mainService.getMenu();
    }

    //게시글 호출
    @PostMapping("/getBoardList")
    public Response getBoardList(HttpServletRequest request, MainVO mainVo) {
        return mainService.getBoardList(request, mainVo);
    }

    //게시글 작성
    @PostMapping("/putBoardInfo")
    public Response putBoardInfo(HttpServletRequest request, MainVO mainVo) {
        return mainService.putBoardInfo(request, mainVo);
    }

    //맛집 등록
    @PostMapping("/putMapHome")
    public Response putMapHome(HttpServletRequest request, MainVO mainVo) {
        return mainService.putMapHome(request, mainVo);
    }

    //맛집 불러오기
    @GetMapping("/getMapHome")
    public Response getMapHome() {
        return mainService.getMapHome();
    }
}