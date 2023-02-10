package hanmangul.common.file.vo;

import java.io.File;

import hanmangul.common.file.entity.RfrncTbl;
import hanmangul.common.file.enums.FileAuth;
import hanmangul.common.vo.ApiDefaultVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileVO extends ApiDefaultVO {

    // 파일 일련번호
    private Long fileSn;

    // 참조 테이블
    private RfrncTbl rfrncTbl;

    // 참조 테이블 일련번호
    private Long rfrncSn;

    // 파일 원본명
    private String orgnlFileNm;

    // 서버에 저장되는 파일명
    private String srvrFileNm;

    // 파일 확장자명
    private String fileExtnNm;

    // 파일 저장 경로
    private String filePath;

    // 파일크기
    private Long fileSz;

    // 정렬일련번호
    private Long sortSn;

    // 파일 다운로드 권한, tb_commoncode : TOTAL_CD(FAC)
    private FileAuth fileDwnldAuthrtCd;

    // 파일 삭제 권한, tb_commoncode : TOTAL_CD(FAC)
    private FileAuth fileDelAuthrtCd;

    // 사용여부
    private String useYn;

    // 등록자
    private int rgtrSn;

    // 등록일시
    private String regDt;

    // 수정자
    private String mdfrSn;

    // 수정일시
    private String mdfcnDt;

    public String getAbsolutePath() {
        return filePath + File.separator + srvrFileNm;
    }

}