package hanmangul.app.portal.login.vo;

import hanmangul.common.vo.ApiDefaultVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginVO extends ApiDefaultVO {

    private long userSn; // 회원일련번호사용자일련번호

    private String userAuthrtCd;// 회원 권한코드(권한코드 tb_authrt : AUTHRT_CD)

    private String userAuthrtNm;// 회원 권한명(권한코드 tb_authrt : authrt_nm)

    private String userJoinSeCd;// 회원가입구분코드, tb_commoncode : TOTAL_CD(UJC)

    private String userId; // 회원아이디

    private String userPswd; // 회원패스워드

    private String userSsoToken;// 회원SSO토큰

    private String userInstCd; // 회원기관코드, tb_commoncode : TOTAL_CD(INC)

    private String userInstNm; // 회원기관명, tb_commoncode : CD_NM

    private String userDeptNm; // 회원부서명

    private String userNm; // 회원 이름

    private String userEmlAddr; // 회원 이메일

    private String user_tel_no; // 회원 전화번호

    private String aprvYn; // 승인 여부 (Y/N)

    private String useYn; // 사용 여부 (Y/N)

}