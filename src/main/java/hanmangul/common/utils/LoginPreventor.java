package hanmangul.common.utils;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import hanmangul.app.portal.login.vo.LoginVO;

public class LoginPreventor {
    public static ConcurrentHashMap<String, HttpSession> loginUsers = new ConcurrentHashMap<String, HttpSession>();

    public static boolean findByLoginId(String loginId) {
        System.out.println(">> loginUsers.size : " + loginUsers.size());
        return loginUsers.containsKey(loginId);
    }

    // 다중 invalidate
    public static void invalidateByLoginId(String loginId) {
        Enumeration<String> e = loginUsers.keys();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            HttpSession value = loginUsers.get(key);
            try {
                LoginVO vo = (LoginVO) value.getAttribute("loginVO");
                if (vo == null) {
                    loginUsers.remove(key, value);
                } else {
                    if (vo.getUserId().equals(loginId)) {
                        try {
                            loginUsers.remove(key, value);
                            value.invalidate();
                        } catch (Exception e1) {

                        }
                    }
                }
            } catch (IllegalStateException ex) {
                loginUsers.remove(key, value);
            }
            /*
             * LoginVO vo = (LoginVO) value.getAttribute("loginVO"); if (vo == null) {
             * loginUsers.remove(key, value); } else { if (vo.getUserId().equals(loginId)) {
             * try { loginUsers.remove(key, value); value.invalidate(); } catch (Exception
             * e1) {
             * 
             * } } }
             */

        }
        System.out.println(">> loginUsers.size : " + loginUsers.size());
    }

    public static void invalidateBySessionId(String sessionId) {
        Enumeration<String> e = loginUsers.keys();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            if (key.equals(sessionId)) {
                try {
                    loginUsers.get(key).invalidate();
                } catch (Exception e1) {

                }
            }
        }
    }

    public static HttpSession findSessionBySessionId(String sessionId) {
        HttpSession session = null;
        Enumeration<String> e = loginUsers.keys();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            HttpSession loginSession = loginUsers.get(key);
            if (sessionId.equals(loginSession.getId())) {
                session = loginSession;
                break;
            }
        }

        return session;
    }
}
