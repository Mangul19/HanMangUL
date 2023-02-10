package hanmangul.common.file.dto;

import hanmangul.common.file.entity.FileEntity;
import hanmangul.common.file.entity.RfrncTbl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {
    private Long fileSn;
    private RfrncTbl rfrncTbl;
    private Long rfrncSn;
    private String srvrFileNm;
    private String orgnlFileNm;
    private String fileExtnNm;
    private Long fileSz;

    public static FileDto fromEntity(FileEntity file) {
        return FileDto.builder().fileSn(file.getFileSn()).rfrncTbl(file.getRfrncTbl()).rfrncSn(file.getRfrncSn()).srvrFileNm(file.getSrvrFileNm()).orgnlFileNm(file.getOrgnlFileNm()).fileExtnNm(file.getFileExtnNm()).fileSz(file.getFileSz()).build();
    }
}
