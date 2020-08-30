import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


@Configuration
@ComponentScan("com.zomentum")
public class AppConfig {

	/*
	 * @Bean(name = "weeklyLockerAnalysisService") public
	 * WeeklyLockerAnalysisService getWeeklyLockerAnalysisService() { return new
	 * WeeklyLockerAnalysisServiceImpl(); }
	 * 
	 * 
	 * @Bean(name = "customLockerAnalysisService") public
	 * CustomLockerAnalysisService getCustomLockerAnalysisService() { return new
	 * CustomLockerAnalysisServiceImpl(getWeeklyLockerAnalysisService()); }
	 */
	/*
	 * 
	 * @Bean(name = "customLockerAnalysisController") public
	 * CustomLockerAnalysisController getCustomLockerAnalysisController() { return
	 * new CustomLockerAnalysisController(); }
	 */
	
	@Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        System.out.println("Config is starting.");
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }
	 
}
