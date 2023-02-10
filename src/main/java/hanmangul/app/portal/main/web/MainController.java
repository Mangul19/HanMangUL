package hanmangul.app.portal.main.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hanmangul.app.portal.main.service.MainService;
import hanmangul.app.portal.main.vo.MainVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    /** 메인화면 */
    @RequestMapping(value = "/main")
    public String main(HttpServletRequest request) throws Exception {
        return mainService.getmain(request);
    }

    // ---------- Ajax api ----------

    //화면 호출
    @RequestMapping("/getMenuInPath")
    public String getMenuInPath(HttpServletRequest request, ModelMap model, MainVO mainVo) {
        return mainService.getMenuInPath(request, model, mainVo);
    }
}