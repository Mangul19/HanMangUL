package hanmangul.app.portal.login.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hanmangul.app.portal.login.dao.LoginDAO;
import lombok.RequiredArgsConstructor;

@Service("LoginService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    @Resource(name = "LoginDAO")
    private LoginDAO loginDAO;
}
