package hanmangul.common.utils;

import hanmangul.common.config.etc.EgovProperties;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PropertiesConfig {

    // 파일구분자
    final static String FILE_SEPARATOR = System.getProperty("file.separator");

    public static final String RELATIVE_PATH_PREFIX = EgovProperties.class.getResource("") == null ? "" : EgovProperties.class.getResource("").getPath().substring(0, EgovProperties.class.getResource("").getPath().lastIndexOf("com"));

    public static final String GLOBALS_PROPERTIES_FILE = "egovframework" + FILE_SEPARATOR + "egovProps" + FILE_SEPARATOR + "globals.properties";

    @Bean
    public PropertiesFactoryBean globalProperties() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource(GLOBALS_PROPERTIES_FILE));

        return bean;
    }
}
