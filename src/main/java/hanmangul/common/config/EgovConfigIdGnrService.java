package hanmangul.common.config;

import javax.sql.DataSource;

import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl;
import org.egovframe.rte.fdl.idgnr.impl.strategy.EgovIdGnrStrategyImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class EgovConfigIdGnrService {
    private final DataSource dataSource;

    @Bean(destroyMethod = "destroy")
    public EgovIdGnrService egovIdGnrFile() {
        EgovIdGnrStrategyImpl strategy = new EgovIdGnrStrategyImpl();
        strategy.setPrefix("FILE_");
        strategy.setCipers(15);
        strategy.setFillChar('0');

        EgovTableIdGnrServiceImpl idGnrService = new EgovTableIdGnrServiceImpl();
        idGnrService.setDataSource(dataSource);
        idGnrService.setStrategy(strategy);
        idGnrService.setBlockSize(1);
        idGnrService.setTable("cm_idgnr_seq");
        idGnrService.setTableName("FILE_ID");

        return idGnrService;
    }
}
