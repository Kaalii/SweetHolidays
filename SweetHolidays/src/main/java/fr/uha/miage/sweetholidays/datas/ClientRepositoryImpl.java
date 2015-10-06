package fr.uha.miage.sweetholidays.datas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientRepositoryImpl {
	
	@Autowired
	public ClientRepository clientRep ; 
	

	/**
	 * 
	 */
	public ClientRepositoryImpl() {
		super();
	} 
	
	public void effaceRepClient() {
		clientRep.deleteAll();
	}
	
	public void saveClient(Client c){
		clientRep.save(c);
	}
	
	public long countClients(){
		return clientRep.count(); 
	}
	
	public Client findClientById(Long id) {
		return clientRep.findOne(id); 
	}
	
	public List<Client> printRepClient() 
	{
		return (List<Client>)clientRep.findAll() ; 
	}
}
