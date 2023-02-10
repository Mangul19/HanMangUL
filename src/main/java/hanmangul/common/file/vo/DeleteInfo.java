package hanmangul.common.file.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;

import hanmangul.common.file.entity.RfrncTbl;
import lombok.Data;

@Data
public class DeleteInfo {
    List<Long> deleteList;
    @NotBlank
    RfrncTbl rfrncTbl;
    @NotBlank
    Long refSeq;
    Long mbrSeq;

    public boolean isValid() {
        return deleteList.size() != 0 && rfrncTbl != null;
    }
}
