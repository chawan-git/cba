package com.cg.cba;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CbaApplication {
	
	private final static Logger log = LogManager.getLogger(CbaApplication.class);
	
	public static void main(String[] args) {
		log.info("Cab Booking Application Started");
		SpringApplication.run(CbaApplication.class, args);
		log.info("Cab Booking Application Stopped");
	}

}
