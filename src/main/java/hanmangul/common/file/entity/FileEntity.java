package hanmangul.common.file.entity;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import hanmangul.common.entity.BaseEntity;
import hanmangul.common.file.enums.FileAuth;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "tb_file")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FileEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "FILE_SN")
    @Comment("파일 시퀀스")
    private Long fileSn;

    @Column
    private Long rfrncSn;

    /**
     * 구분자
     */
    @Enumerated(EnumType.STRING)
    private RfrncTbl rfrncTbl;

    @Column
    private String orgnlFileNm;

    @Column
    private String srvrFileNm;

    @Column
    private String fileExtnNm;

    @Column
    private String filePath;

    @Column
    private Long fileSz;

    /**
     * 다운로드 권한
     */
    @Enumerated(EnumType.STRING)
    private FileAuth fileDwnldAuthrtCd;

    /**
     * 삭제 권한
     */
    @Enumerated(EnumType.STRING)
    private FileAuth fileDelAuthrtCd;

    public String getAbsolutePath() {
        return filePath + File.separator + srvrFileNm;
    }
}
