package fr.uha.miage.sweetholidays;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import fr.uha.miage.sweetholidays.datas.Location;



@SpringBootApplication
public class SweetHolidaysApplication implements CommandLineRunner 
{
	private static final Logger log = LoggerFactory.getLogger(SweetHolidaysApplication.class);


	public static void main(String[] args)
	{
		SpringApplication.run(SweetHolidaysApplication.class, args);
		
	}

	@Override
	public void run(String... arg0) throws Exception {
		//Création des tables 
				log.info("OPERATION SUR LA BASE DE DONNEES");
				System.out.println("-------------------------------------");
				System.out.println("Création de table ");
				jdbcTemplate.execute("DROP TABLE Location IF EXISTS");
				jdbcTemplate.execute("CREATE TABLE Location( id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, nom_location VARCHAR(255), adresse_location VARCHAR(255))");
				
				
				System.out.println("-------------------------------------");
				log.info("TEST d'insertion dans la table");
				jdbcTemplate.batchUpdate("INSERT INTO Location(id,nom_location,adresse_location) VALUES(NULL,'Appartement 1',' 12 rue de la paix')");
				jdbcTemplate.batchUpdate("INSERT INTO Location(id,nom_location,adresse_location) VALUES(NULL,'Appartement 2',' 1 rue de la Libérté')");
				
				log.info("TEST de selection dans la base avec 1 paramètre");
				try {
					System.out.println(jdbcTemplate.queryForList("select id from Location", Integer.class));
					System.out.println(jdbcTemplate.queryForList("select nom_location from Location", String.class));
					System.out.println(jdbcTemplate.queryForList("select adresse_location from Location", String.class));
				} 
				catch (EmptyResultDataAccessException e) {
				   if(log.isDebugEnabled()){
				       System.out.println("Correction - multiples erreurs de selection");
				   }
				   
				}

				log.info("Test de récupération d'un objet complet");
				 Object[] parameters = new Object[] {new Integer(1)};
				    Object o = jdbcTemplate.queryForObject("select nom_location from Location where id = ?",
				        parameters, String.class);
				    System.out.println(o.toString());
				
				
				 log.info("Test de recupération d'objet complet"); 
				 List<Location> locations = this.jdbcTemplate.query(
						 "select * from Location",
						 new RowMapper<Location>() {
						 public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
						Location location = new Location();
						location.setName(rs.getString("nom_location"));
						location.setAddress(rs.getString("adresse_location"));
						return location;
						 }
						 });
				 
				 //Affichage d'une liste
				 for(Iterator iter=locations.iterator(); iter.hasNext(); )
				 {
					Location Appartement = (Location) iter.next();
					 System.out.println(Appartement);
				 }
				 
		
	}
}
