package hanmangul.common.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import hanmangul.common.config.etc.EgovProperties;
import hanmangul.common.egov.EgovFileUploadUtil;
import hanmangul.common.egov.EgovFormBasedFileUtil;
import hanmangul.common.egov.EgovFormBasedFileVo;

/**
 * @FileName : FileUpload.java
 * @Project : gbict
 * @Date : 2016. 6. 29.
 * @작성자 : 전한석
 * @변경이력 :
 * @프로그램 설명 : ckeditor 의 이미지 업로드를 하기 위한 기능구현
 */

@SuppressWarnings("serial")
@Controller
public class FileUploadController implements Serializable {

    /** 첨부파일 위치 지정 => globals.properties */
    private String uploadDir = EgovProperties.getProperty("Globals.fileStorePath");
    /** 허용할 확장자를 .확장자 형태로 연달아 기술한다. ex) .gif.jpg.jpeg.png => globals.properties */
    private String extWhiteList = EgovProperties.getProperty("Globals.fileStorePath");

    /**
     * @Method Name : ckeditorImageUpload
     * @작성자 : 전한석
     * @작성일 : 2016. 6. 29.
     * @변경이력 :
     * @Method 설명 :ckeditor 의 이미지 업로드를 하기 위한 기능구현
     * @param request
     * @param response
     * @param upload
     * @throws Exception
     */

    @RequestMapping(value = "/common/ckeditorImageUpload", method = RequestMethod.POST)
    public void ckeditorImageUpload(MultipartHttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) throws Exception {
        OutputStream out = null;
        PrintWriter printWriter = null;
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String url = null;
        EgovFormBasedFileVo vo = null;
        try {
            // 프로퍼티의 이미지 경로 지정
            final String uploadDir = EgovProperties.getProperty("Globals.fileStorePath");
            // CommonsMultipartResolver 에서 자동으로 잡아줌
            final long maxFileSize = 1024 * 1024 * 600;
            // 전자정부 프레임워크에서 제공되는 메서드에서 maxFileSize 파라메터만 받고, 사용되지는 않음
            List<EgovFormBasedFileVo> list = EgovFileUploadUtil.uploadFiles(request, uploadDir, maxFileSize);

            if (list.size() > 0) {
                vo = list.get(0); // 첫번째 이미지
                System.out.println(">> request.getRequestURI : " + request.getRequestURI());
                url = /*
                       * request.getScheme() + "://" + request.getServerName() + ":" +
                       * request.getServerPort() +
                       */request.getContextPath() + "/common/ckeditorImageSrc?" + "path=" + vo.getServerSubPath() + "&physical=" + vo.getPhysicalName() + "&contentType=" + vo.getContentType();
            }

            // 저장된 이미지는 'CKEditorFuncNum'의 이름으로 콜백된다
            String callback = request.getParameter("CKEditorFuncNum");
            printWriter = response.getWriter();
            String fileUrl = url; // url경로
            printWriter.println("{\"filename\" : \"" + vo.getServerSubPath() + vo.getContentType() + "\", \"uploaded\" : 1, \"url\":\"" + fileUrl + "\"}");
            printWriter.flush();
        } catch (IOException e) {
            // e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                // e.printStackTrace();
            }
        }
        return;
    }

    /**
     * 이미지 view를 제공한다.
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/common/ckeditorImageSrc", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 2017.12.12 - 출력 모듈 경로 변경 취약점 조치
        // KISA 보안약점 조치 (2018-10-29, 윤창원)
        System.out.println(request.getParameter("path"));
        System.out.println(request.getParameter("physical"));
        System.out.println(request.getParameter("contentType"));

        String subPath = isNullToString(request.getParameter("path"));
        String physical = isNullToString(request.getParameter("physical"));
        String mimeType = isNullToString(request.getParameter("contentType"));

        System.out.println(">> subPath : " + subPath);
        System.out.println(">> physical : " + physical);
        System.out.println(">> mimeType : " + mimeType);

        if (subPath.indexOf("..") >= 0)
            throw new Exception("Security Exception - illegal url called.");
        if (physical.indexOf("..") >= 0)
            throw new Exception("Security Exception - illegal url called.");

        String ext = "";
        if (physical.lastIndexOf(".") > 0) {
            ext = physical.substring(physical.lastIndexOf(".") + 1, physical.length()).toLowerCase();
        }
        if (ext == null) {
            System.out.println(">> ext is null");
            throw new FileNotFoundException();
        }

        if (extWhiteList.indexOf(ext) >= 0) {
            EgovFormBasedFileUtil.viewFile(response, uploadDir, subPath, physical, mimeType);
        } else {
            System.out.println(">> extWhiteList error");
            throw new FileNotFoundException();
        }
    }

    /**
     * 객체가 null인지 확인하고 null인 경우 "" 로 바꾸는 메서드
     * 
     * @param object 원본 객체
     * @return resultVal 문자열
     */
    public String isNullToString(Object object) {
        String string = "";

        if (object != null) {
            string = object.toString().trim();
        }

        return string;
    }
}
