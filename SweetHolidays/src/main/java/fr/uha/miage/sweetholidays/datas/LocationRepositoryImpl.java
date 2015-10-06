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
		System.out.println("Essai de sauvegardé une location");
		locRep.save(l);
	}
	
	
	public void saveLoc(String string, double d, int i, String string2, String string3, String string4, String string5,String string6)
	{
		System.out.println("Essai de sauvegardé une location");
		System.out.println("Affichage des paramètres "+string+" "+d+" "+i+" "+string2+" "+string3+" "+string4+" "+string5+" "+string6);
		Location l = new Location(string,d,i,string2,string3,string4,string5,string6);
		l.toString();
		locRep.save(l);
		System.out.println("La location à été sauvegardé");
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
		System.out.println("Essai de récupération de la liste des locations sauvegardé");
		List<Location> l = (List<Location>) locRep.findAll() ; 
		l.toString();
		return l;
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

