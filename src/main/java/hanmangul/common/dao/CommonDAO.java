package hanmangul.common.dao;

import java.util.List;

import hanmangul.common.vo.CommonCodeVO;

public interface CommonDAO {

    List<CommonCodeVO> selectCommonCodeList(CommonCodeVO codeVO) throws Exception;

    List<CommonCodeVO> selectCommomCodeOne(String totalCd) throws Exception;
}