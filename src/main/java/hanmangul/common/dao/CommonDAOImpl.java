package hanmangul.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import hanmangul.common.vo.CommonCodeVO;

@Repository("commonDAO")
public class CommonDAOImpl extends SqlComAbstractDAO implements CommonDAO {

    @Override
    public List<CommonCodeVO> selectCommonCodeList(CommonCodeVO codeVO) throws Exception {
        // TODO Auto-generated method stub
        return selectList("CommonDAO.selectCommomCodeList", codeVO);
    }

    @Override
    public List<CommonCodeVO> selectCommomCodeOne(String totalCd) throws Exception {
        return selectList("CommonDAO.selectCommomCodeOne", totalCd);
    }
}