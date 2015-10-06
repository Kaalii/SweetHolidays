package fr.uha.miage.sweetholidays.datas;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
	
	/*Recherche par le nom*/
	
	Location findByName(String name);

}
