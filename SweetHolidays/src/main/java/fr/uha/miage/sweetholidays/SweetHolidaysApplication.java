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
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import fr.uha.miage.sweetholidays.datas.Location;



@SpringBootApplication
public class SweetHolidaysApplication implements CommandLineRunner 
{
	private static final Logger log = LoggerFactory.getLogger(SweetHolidaysApplication.class);
	
    @Autowired
    JdbcTemplate jdbcTemplate;

	public static void main(String[] args)
	{
		SpringApplication.run(SweetHolidaysApplication.class, args);
		
	}

	@Override
	public void run(String... arg0) throws Exception {
		//Création des tables 
				log.info("OPERATION SUR LA BASE DE DONNEES");
				System.out.println("-------------------------------------");
/*
 * 				location.setName(rs.getString("nom_location"));
				location.setAddress(rs.getString("adresse_location"));
				location.setPrice(rs.getDouble("prix_location"));
				location.setCapacity_location(rs.getInt("capacity_location"));
				location.setAccomadation_Rules(rs.getString("Accomadation_Rules"));
				location.setAccomodation_type(rs.getInt("Accomodation_type"));		
 */
				System.out.println("Création de table ");
				createTable("CREATE TABLE Location( id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,nom_location VARCHAR(255),prix_location FLOAT(5), adresse_location VARCHAR(255),capacity_location INT(2), type_location VARCHAR(10), description_location VARCHAR(100), rules_location VARCHAR(100))","Location");
				createTable("CREATE TABLE Client(  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, nom_client VARCHAR(25), prenom_client VARCHAR(20), email_client VARCHAR(10), mot_de_passe_client VARCHAR(10))", "Client");
				createTable("CREATE TABLE Equipement(  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,nom_equipement VARCHAR(20))","Equipement") ;
				createTable("CREATE TABLE possede(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, id_equipement INT(2), id_logement INT(2))","possede");
				createTable("CREATE TABLE Reservation(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,id_client	INT(2),	id_logement	INT(2),	date_arrivee	VARCHAR(100),date_depart	VARCHAR(100),nombre_personne INT(2))","Reservation"); 
				createTable("CREATE TABLE CarteBancaire(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,	id_client INT(2),nom_client_cb VARCHAR(100),prenom_client_cb VARCHAR(100),numero_cb INT(2),	date_expiration_cb	VARCHAR(20), crypto_cb	INT(3))","CarteBancaire");
				
				System.out.println("-------------------------------------");
				log.info("TEST d'insertion dans la table");
				
				insertTable("INSERT INTO Location(id,nom_location,adresse_location) VALUES(NULL,'Appartement 1',' 12 rue de la paix')");
				insertTable("INSERT INTO Location(id,nom_location,adresse_location) VALUES(NULL,'Appartement 2',' 1 rue de la Libérté')");
				
				insertTable("INSERT INTO Location(id,nom_location,prix_location,adresse_location,capacity_location,type_location,description_location,rules_location) VALUES(NULL,'Appart Sevan',152010.0,'12 rue de la paix 68128 Village Neuf',4,'F3','Appartrement trop bien','Pas fumer')");
				
				System.out.println("-------------------------------------");
				log.info("Affichage d'une liste selon 1 paramètre");
				
					printTable("id","Location",1);
					printTable("nom_location", "Location" , 4);
					printTable("adresse_location","Location",4);
					
					
				log.info("Liste de toutes les locations disponibles") ;
				listeAllLocation();
		 
		
	}
	

	
	public void createAllTable(){
		/* Table LOCATION */
		jdbcTemplate.execute("DROP TABLE Location IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE Location( id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, nom_location VARCHAR(255), adresse_location VARCHAR(255))");
		
		/* Table Client */
		jdbcTemplate.execute("DROP TABLE Client IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE Client( id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, nom_client VARCHAR(255), prenom_client VARCHAR(255))");
		
	}
	
	public void createTable(String sql,String nameTable){
		jdbcTemplate.execute("DROP TABLE "+nameTable+" IF EXISTS");
		jdbcTemplate.execute(sql);
	}
	
	public void insertTable(String sql)
	{
		jdbcTemplate.batchUpdate(sql);
	}
	
	/*
	 * Affiche la table nameTable selon le ParameterName et selon le TypeOfClass
	 * 1 => Integer
	 * 2 => Double
	 * 3 => long 
	 * 4 => String 
	 * 5 => Boolean
	 */
	public void printTable(String nameParameter, String nameTable,int TypeOfClass )
	{
		try {
			switch (TypeOfClass){
				case 1 :
					System.out.println(jdbcTemplate.queryForList("select "+nameParameter+" from "+nameTable+" ",Integer.class));
					break;
				case 2 :
					System.out.println(jdbcTemplate.queryForList("select "+nameParameter+" from "+nameTable+" ",Double.class));
					break;
				case 3 :
					System.out.println(jdbcTemplate.queryForList("select "+nameParameter+" from "+nameTable+" ",Long.class));
					break;
				case 4 :
					System.out.println(jdbcTemplate.queryForList("select "+nameParameter+" from "+nameTable+" ",String.class));
					break;
				case 5 :
					System.out.println(jdbcTemplate.queryForList("select "+nameParameter+" from "+nameTable+" ",Boolean.class));
					break;
				
			}
		} catch (DataAccessException e) {

			e.printStackTrace();
		}
		
	}
	
	
	public void listeAllLocation()
	{
		 List<Location> locations = this.jdbcTemplate.query(
				 "select * from Location",
				 new RowMapper<Location>() {
				 public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
				Location location = new Location();
				location.setName(rs.getString("nom_location"));
				location.setAddress(rs.getString("adresse_location"));
				location.setPrice(rs.getDouble("prix_location"));
				location.setCapacity_location(rs.getInt("capacity_location"));
				location.setAccomadation_Rules(rs.getString("rules_location"));
				location.setAccomodation_type(rs.getString("type_location"));
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
