package hanmangul.common.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class EgovConfigProperty {
    @Bean
    public PropertiesFactoryBean globalsProperties() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("/egovframework/egovProps/globals.properties"));
        return bean;
    }
}
