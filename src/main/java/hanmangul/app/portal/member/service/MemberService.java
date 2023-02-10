package hanmangul.app.portal.member.service;

import javax.servlet.http.HttpSession;

import hanmangul.app.portal.member.vo.MemberVO;

public interface MemberService {
    void join(MemberVO member);

    MemberVO login(String mbrId, String mbrPwd) throws Exception;

    MemberVO getLoginMember(HttpSession session) throws Exception;
}
