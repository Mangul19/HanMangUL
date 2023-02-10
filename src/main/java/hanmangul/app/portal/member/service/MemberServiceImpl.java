package hanmangul.app.portal.member.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hanmangul.app.portal.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Service("memberService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    @Transactional
    @Override
    public void join(MemberVO member) {
        // something to do..
    }

    @Override
    @Transactional
    public MemberVO login(String mbrId, String mbrPwd) throws Exception {
        // something to do..

        return null;
    }

    @Override
    public MemberVO getLoginMember(HttpSession session) throws Exception {
        // something to do..

        return null;
    }
}
