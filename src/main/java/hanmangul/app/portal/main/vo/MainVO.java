package hanmangul.app.portal.main.vo;

import hanmangul.common.vo.SearchVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainVO extends SearchVO {
    private String pageName;
    private String pagePath;

    private String mapLng;
    private String mapLat;
    private String mapName;
}
