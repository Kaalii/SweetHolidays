package fr.uha.miage.sweetholidays.datas;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

	List<Client> findByName(String name); 
	
	List<Client> findByPrenom(String prenom);
	
	List<Client> findByEmail(String email); 
}
