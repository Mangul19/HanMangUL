package hanmangul.common.file.vo;

import java.io.File;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import hanmangul.common.file.entity.RfrncTbl;
import hanmangul.common.file.enums.AllowFileType;
import hanmangul.common.file.enums.FileAuth;
import hanmangul.common.utils.PropertiesLoader;
import lombok.Data;

@Data
public class UploadInfo {
    private List<MultipartFile> files;
    @NotBlank
    private RfrncTbl rfrncTbl;
    @NotBlank
    private Long rfrncSn;

//    @Value("${spring.servlet.multipart.location}")
    private String rootPath = PropertiesLoader.getProperties("spring.servlet.multipart.location").toString();

    /**
     * 기본 파일 경로
     */
    // String rootPath = EgovProperties.getProperty("Globals.file.path");
    private FileAuth fileDwnldAuthrtCd = FileAuth.ALL;
    private FileAuth fileDelAuthrtCd = FileAuth.OWNER;

    private AllowFileType allowFileType = AllowFileType.ALL;

    private Long createdBy;

    public boolean isValid() {
        return files.size() != 0 && rfrncTbl != null && rfrncSn != null && rootPath != null && fileDwnldAuthrtCd != null && fileDelAuthrtCd != null;
    }

    public String getAbsoluteFilePath() {
        if (!rootPath.endsWith(File.separator)) {
            rootPath += File.separator;
        }
        return rootPath + rfrncTbl.name() + File.separator + rfrncSn + File.separator;
    }
}
