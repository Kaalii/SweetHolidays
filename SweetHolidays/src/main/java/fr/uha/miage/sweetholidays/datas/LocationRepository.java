package fr.uha.miage.sweetholidays.datas;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
	
	/*Recherche par le nom*/
	
	Location findByName(String name);
	
	/*Gestion de la recherche dans la page d'index*/
	@Query("select l from Location l where l.accomodation_type = :accomodation_type and l.capacity_location = :capacity_location and l.city = :city")
	 List<Location> findByAccomodation_TypeAndCapacity_LocationAndCity(@Param("accomodation_type")String accomodation_type, @Param("capacity_location")int capacity_location, @Param("city")String city);

}
