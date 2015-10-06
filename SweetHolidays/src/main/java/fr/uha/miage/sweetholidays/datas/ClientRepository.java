package fr.uha.miage.sweetholidays.datas;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

	Client findByName(String name); 
	
	Client findByPrenom(String prenom);
	
	Client findByEmail(String email); 
}
