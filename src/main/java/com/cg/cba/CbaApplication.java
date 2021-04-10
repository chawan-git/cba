package com.cg.cba;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class CbaApplication {
	
	private final static Logger log = LogManager.getLogger(CbaApplication.class);
	
	public static void main(String[] args) {
		log.info("Cab Booking Application Started");
		SpringApplication.run(CbaApplication.class, args);
		log.info("Cab Booking Application Stopped");
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowCredentials(true).allowedOrigins("http://localhost:3000","https://cab.rao.life","https://cab-test.rao.life").allowedMethods("*");
            }
        };
    }

}
