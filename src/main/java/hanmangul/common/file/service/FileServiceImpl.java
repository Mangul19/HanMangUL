package hanmangul.common.file.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import hanmangul.app.portal.member.service.MemberService;
import hanmangul.app.portal.member.vo.MemberVO;
import hanmangul.common.file.dto.FileDto;
import hanmangul.common.file.entity.RfrncTbl;
import hanmangul.common.utils.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hanmangul.common.config.etc.EgovWebUtil;
import hanmangul.common.file.dao.FileDAO;
import hanmangul.common.file.vo.DeleteInfo;
import hanmangul.common.file.vo.FileVO;
import hanmangul.common.file.vo.UploadInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service("fileService")
public class FileServiceImpl implements FileService {
    @Resource(name = "fileDAO")
    private FileDAO fileDAO;
    @Resource(name = "memberService")
    private MemberService memberService;

    @Override
    public void uploadFiles(UploadInfo uploadInfo) throws Exception {
        if (!uploadInfo.isValid()) {
            throw new IllegalArgumentException();
        }
        String filePath = uploadInfo.getAbsoluteFilePath();
        File storedPath = new File(EgovWebUtil.filePathBlackList(filePath));
        if (!storedPath.exists() || storedPath.isFile()) {
            if (storedPath.mkdirs()) {
                log.debug("[file.mkdirs] saveFolder : Creation Success ");
            }
        }

        List<FileVO> fileVOList = new ArrayList<>();
        for (MultipartFile file : uploadInfo.getFiles()) {
            if (StringUtils.isEmpty(file.getOriginalFilename())) {
                continue;
            }

            // 확장자 제한
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            if (!uploadInfo.getAllowFileType().isValid(ext)) {
                throw new IllegalArgumentException("파일 확장자 제한");
            }

            String storeFileNm = UUID.randomUUID().toString();

            file.transferTo(new File(EgovWebUtil.filePathBlackList(filePath + File.separator + storeFileNm + '.' + ext)));

            FileVO fileVO = new FileVO();

            fileVO.setRfrncSn(uploadInfo.getRfrncSn());
            fileVO.setRfrncTbl(uploadInfo.getRfrncTbl());
            fileVO.setOrgnlFileNm(new String(file.getOriginalFilename().getBytes(StandardCharsets.UTF_8)));
            fileVO.setSrvrFileNm(storeFileNm);
            fileVO.setFilePath(filePath);
            fileVO.setFileSz(file.getSize());
            fileVO.setFileDwnldAuthrtCd(uploadInfo.getFileDwnldAuthrtCd());
            fileVO.setFileDelAuthrtCd(uploadInfo.getFileDelAuthrtCd());
            fileVO.setCreatedBy(uploadInfo.getCreatedBy());
            fileVO.setCreatedDate(LocalDateTime.now());
            fileVO.setFileExtnNm(ext);
            fileVO.setUseYn("Y");

            fileVOList.add(fileVO);
        }

        fileDAO.insertFileList(fileVOList);
    }

    @Override
    public void deleteFiles(DeleteInfo deleteInfo, HttpServletRequest request) throws Exception {

        if (!deleteInfo.isValid()) {
            throw new IllegalArgumentException();
        }

        List<FileVO> deleteFileList = fileDAO.selectFileListInFileSn(deleteInfo.getDeleteList());
        for (FileVO file : deleteFileList) {

//             삭제 권한 확인
            MemberVO member = memberService.getLoginMember(request.getSession());
            FileUtils.checkDeleteAuth(file, member);

            try {
                Files.deleteIfExists(Paths.get(file.getAbsolutePath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteNoticeFiles(DeleteInfo deleteInfo, HttpServletRequest request) throws Exception {

        deleteInfo.setRfrncTbl(RfrncTbl.NOTICE);

        if (!deleteInfo.isValid()) {
            throw new IllegalArgumentException();
        }

        List<FileVO> deleteFileList = fileDAO.selectFileListInFileSn(deleteInfo.getDeleteList());
        for (FileVO file : deleteFileList) {

//             삭제 권한 확인
//            MemberVO member = memberService.getLoginMember(request.getSession());
//            FileUtils.checkDeleteAuth(file, member);

            try {
                Files.deleteIfExists(Paths.get(file.getAbsolutePath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 파일을 모두 삭제한 후 DB 처리
        fileDAO.deleteAll(deleteInfo.getDeleteList());
    }

    @Override
    public FileVO getFile(Long fileSeq) throws Exception {
        FileVO file = fileDAO.selectFile(fileSeq);
        if (file == null) {
            throw new IllegalArgumentException("File Not Exist");
        }
        return file;
    }

    @Override
    public List<FileDto> getNoticeFilesList(int bbsSn) {

        FileVO fileVO = new FileVO();
        fileVO.setRfrncSn((long) bbsSn);
        fileVO.setRfrncTbl(RfrncTbl.NOTICE);

        try {
            List<FileDto> list = fileDAO.selectFileList(fileVO);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
