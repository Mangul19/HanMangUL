package hanmangul.app.portal.login.web;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hanmangul.api.common.Response;
import hanmangul.api.common.ResponseHandler;
import hanmangul.app.portal.login.service.LoginService;
import hanmangul.app.portal.login.vo.LoginVO;
import hanmangul.common.utils.LoginPreventor;
import hanmangul.common.utils.LoginUtils;

@Controller
public class LoginApiController {

    @Resource(name = "LoginService")
    private LoginService loginService;

    // 로그인
    @ResponseBody
    @RequestMapping("/api/login/login")
    public Response login(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {

        Map<String, Object> rtn = new HashMap<>();

        LoginVO loginVO = LoginUtils.getAuthenticatedUser();
        rtn.put("userJoinSeCd", loginVO.getUserJoinSeCd());

        if (loginVO != null) {
            LoginPreventor.loginUsers.remove(loginVO.getUserId().toLowerCase(), session);
        }

        session.invalidate();

        return ResponseHandler.success(rtn);
    }

    // 로그 아웃
    @ResponseBody
    @RequestMapping("/api/login/logout")
    public Response logoutAction(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {

        Map<String, Object> rtn = new HashMap<>();

        LoginVO loginVO = LoginUtils.getAuthenticatedUser();
        rtn.put("userJoinSeCd", loginVO.getUserJoinSeCd());

        if (loginVO != null) {
            LoginPreventor.loginUsers.remove(loginVO.getUserId().toLowerCase(), session);
        }

        session.invalidate();

        return ResponseHandler.success(rtn);
    }
}