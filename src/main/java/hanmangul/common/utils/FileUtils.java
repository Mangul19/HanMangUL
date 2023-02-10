package hanmangul.common.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import hanmangul.app.portal.member.vo.MemberVO;
import hanmangul.common.file.enums.FileAuth;
import hanmangul.common.file.vo.FileVO;

public class FileUtils {
    /**
     * fileMap에서 데이터가 없는 파일 제외
     *
     * @param files
     * @return
     */
    public static Map<String, MultipartFile> parseFileMap(Map<String, MultipartFile> files) {
        Iterator<Map.Entry<String, MultipartFile>> itr = files.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, MultipartFile> entry = itr.next();
            long size = entry.getValue().getSize();
            if (size == 0L) {
                itr.remove();
            }
        }
        return files;
    }

    public static boolean isEmpty(MultipartFile file) {
        if (file == null) {
            return true;
        }

        return file.isEmpty();
    }

    public static boolean isEmpty(List<MultipartFile> files) {
        boolean result = false;

        if (files == null || files.size() == 0) {
            return true;
        }

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean isEmpty(Object obj) {
        if (obj instanceof MultipartFile) {
            return isEmpty((MultipartFile) obj);
        } else if (obj instanceof List) {
            return isEmpty((List<MultipartFile>) obj);
        } else {
            return true;
        }
    }

    public static List<MultipartFile> getFiles(Object obj) {
        List<MultipartFile> files = new ArrayList<>();
        if (obj instanceof MultipartFile) {
            files.add((MultipartFile) obj);
        } else if (obj instanceof List) {
            files.addAll((List<MultipartFile>) obj);
        }
        return files;
    }

    public static Boolean isMultipartRequest(HttpServletRequest request) {
        return request.getContentType() != null && request.getContentType().toLowerCase().contains("multipart/form-data");
    }

    /**
     * 다운로드 권한
     */
    public static void checkDownloadAuth(FileVO file, MemberVO member) throws Exception {
        if (file.getFileDwnldAuthrtCd().equals(FileAuth.OWNER)) {
            if (!file.getCreatedBy().equals(member.getSeq())) {
                throw new SecurityException("Not Authorized");
            }
        }
    }

    /**
     * 삭제 권한
     */
    public static void checkDeleteAuth(FileVO file, MemberVO member) throws Exception {
        if (file.getFileDelAuthrtCd().equals(FileAuth.OWNER)) {
            if (!file.getCreatedBy().equals(member.getSeq())) {
                throw new SecurityException("Not Authorized");
            }
        } else if (file.getFileDelAuthrtCd().equals(FileAuth.ADMIN)) {
            // TODO : 관리자 권한 설정
        }
    }
}
