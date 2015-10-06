package fr.uha.miage.sweetholidays.datas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
@Transactional
public class ReservationRepositoryImpl {

	@Autowired
	public ReservationRepository resRep ;
	
	public void effaceRep(){
		resRep.deleteAll() ;
	}
	
	public void saveResa(Reservation r){
		resRep.save(r);
	}
	
	public long countReservations(){
		return resRep.count(); 
	}
	
	public Reservation findReservationById(long id){
		return resRep.findOne(id);
	}
	
	public List<Reservation> printRep()
	{
		return (List<Reservation>)resRep.findAll();
	}
}
