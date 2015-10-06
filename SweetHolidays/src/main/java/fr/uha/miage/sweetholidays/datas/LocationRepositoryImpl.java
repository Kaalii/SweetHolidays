package fr.uha.miage.sweetholidays.datas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LocationRepositoryImpl {

	@Autowired
	 public LocationRepository locRep ;
	
	public LocationRepositoryImpl() {
		super();
	}
	
	public void effaceRep() {
		locRep.deleteAll(); 
	}
	

	public void saveLoc(Location l)
	{
		locRep.save(l);
	}
	
	public long countLocations()
	{
		return locRep.count() ;
	}

	public Location findLocationById(long id)
	{
		return locRep.findOne(id);
		
	}
	
	public List<Location> printRep()
	{
		return (List<Location>) locRep.findAll();
	}
	
	
	public String LocationIsExist(long id)
	{
		if(locRep.exists(id)){
			return "La location existe"; 
		}
		else
			return "La location n'existe pas"; 
	}

}

