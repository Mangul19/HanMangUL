package hanmangul.common.file.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import hanmangul.common.dao.SqlComAbstractDAO;
import hanmangul.common.file.dto.FileDto;
import hanmangul.common.file.vo.FileVO;

@Repository("fileDAO")
public class FileDAOImpl extends SqlComAbstractDAO implements FileDAO {

    @Override
    public void insertFileList(List<FileVO> fileVOList) {
        insert("FileDAO.insertFileList", fileVOList);
    }

    @Override
    public List<FileDto> selectFileList(FileVO fileVO) {
        return selectList("FileDAO.selectFileList", fileVO);
    }

    @Override
    public FileVO selectFile(Long fileSn) {
        return selectOne("FileDAO.selectFile", fileSn);
    }

    @Override
    public List<FileVO> selectFileListInFileSn(List<Long> deleteFileSnList) {
        return selectList("FileDAO.selectFileListInFileSn", deleteFileSnList);
    }

    @Override
    public void deleteAll(List<Long> deleteFileSnList) {
        delete("FileDAO.deleteAll", deleteFileSnList);
    }
}
