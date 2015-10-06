package fr.uha.miage.sweetholidays.datas;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
	
/*
 	@Query("select r from Reservation where r.id_client= :id_client and r.id_logement= :id_logement")
	List<Reservation> findById_clientAndId_logement(@Param("id_client") String id_client,@Param("id_logement") String id_logement);
 */
}
