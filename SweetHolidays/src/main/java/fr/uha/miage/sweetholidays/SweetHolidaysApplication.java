package fr.uha.miage.sweetholidays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;



@SpringBootApplication
public class SweetHolidaysApplication  
{
   private static final Logger log = LoggerFactory.getLogger(SweetHolidaysApplication.class);
	
    @Autowired
    JdbcTemplate jdbcTemplate;

	public static void main(String[] args)
	{
		log.info("Lancement de l'application");
		SpringApplication.run(SweetHolidaysApplication.class, args);
		
	}

}
