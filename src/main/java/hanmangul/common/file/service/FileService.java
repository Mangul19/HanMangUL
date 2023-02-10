package hanmangul.common.file.service;

import hanmangul.common.file.dto.FileDto;
import hanmangul.common.file.vo.DeleteInfo;
import hanmangul.common.file.vo.FileVO;
import hanmangul.common.file.vo.UploadInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FileService {
    void uploadFiles(UploadInfo uploadInfo) throws Exception;

    void deleteFiles(DeleteInfo deleteInfo, HttpServletRequest request) throws Exception;

    void deleteNoticeFiles(DeleteInfo deleteInfo, HttpServletRequest request) throws Exception;

    FileVO getFile(Long fileSn) throws Exception;

    List<FileDto> getNoticeFilesList(int bbsSn);
}
