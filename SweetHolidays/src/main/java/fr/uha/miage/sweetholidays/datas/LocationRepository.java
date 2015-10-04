package fr.uha.miage.sweetholidays.datas;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends PagingAndSortingRepository<Location, Long> {
	
	@Async
	@Query("SELECT l FROM Location l WHERE l.id=:id")
	public Location findById(Long id);
	
	@Async
	@Query("SELECT l FROM Location l WHERE l.Name=:Name")
	public Location findByName(String Name);
    
	@Async
	@Query("SELECT l FROM Location l WHERE l.Address=:Address")
	public Location findByAddress(String Adress);    
}
