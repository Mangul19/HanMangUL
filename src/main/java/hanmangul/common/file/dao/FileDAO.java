package hanmangul.common.file.dao;

import java.util.List;

import hanmangul.common.file.dto.FileDto;
import hanmangul.common.file.vo.FileVO;

public interface FileDAO {
    void insertFileList(List<FileVO> fileVOList);

    List<FileDto> selectFileList(FileVO fileVO);

    FileVO selectFile(Long fileSn);

    List<FileVO> selectFileListInFileSn(List<Long> deleteFileSnList);

    void deleteAll(List<Long> deleteFileSnList);
}
