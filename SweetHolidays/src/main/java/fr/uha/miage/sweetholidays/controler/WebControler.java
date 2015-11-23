package fr.uha.miage.sweetholidays.controler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import fr.uha.miage.sweetholidays.datas.Client;
import fr.uha.miage.sweetholidays.datas.ClientRepository;
import fr.uha.miage.sweetholidays.datas.ClientRepositoryImpl;
import fr.uha.miage.sweetholidays.datas.Location;
import fr.uha.miage.sweetholidays.datas.LocationRepository;
import fr.uha.miage.sweetholidays.datas.Newflat;
import fr.uha.miage.sweetholidays.datas.Recherche;
import fr.uha.miage.sweetholidays.datas.Reservation;
import fr.uha.miage.sweetholidays.datas.ReservationRepository;
import fr.uha.miage.sweetholidays.datas.ReservationRepositoryImpl;
import fr.uha.miage.sweetholidays.datas.LocationRepositoryImpl;

	@Controller
	public class WebControler extends WebMvcConfigurerAdapter {

		@Autowired
		LocationRepositoryImpl locate ;
		
		@Autowired
		ClientRepositoryImpl client ; 
		
		@Autowired
		LocationRepository loc;
		
		@Autowired
		ClientRepository cli ; 
		
		@Autowired
		ReservationRepository res ; 
		
		@Autowired
		ReservationRepositoryImpl reserv ; 
		
		//Variable qui permet de mémoriser les info POST en cas d'inscription
		Recherche rech;
		//Boolean de reservation ok/not
		boolean resa;
		
	    @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	    	registry.addViewController("/SweetSearch").setViewName("resultat");
	    	/****Ajout***/
	    	 registry.addViewController("/SweetAddFlat").setViewName("addApart");
	    }
	    /*Fonction d'encryptage du mot de passe */  
	    public String encrypt(String password){
	        String crypte="";
	        for (int i=0; i<password.length();i++)  {
	            int c=password.charAt(i)^48; 
	            crypte=crypte+(char)c;
	        }
	        return crypte;
	    }
	    
	    @RequestMapping(value="/SweetHolidays", method=RequestMethod.GET)
	    public String showIndexGet(Client client_insc, Recherche rech, Model model) {
	    	
	    	// Effacement des répertoires pour des multiples recherches.
	    	long count_location = locate.countLocations(); 
	    	long count_clients = client.countClients() ; 
	    	
	   /*	if(count_location == 0 && count_clients == 0){
	    		locate.effaceRep();
	    		client.effaceRepClient();
	    	}
	   */

	    	 List<Location> Loc = new ArrayList<Location>();
	    	
	    	//Récupération des résultats
	        Location loc1 =new Location("Sweet", 125.0, 4,"8 StrauStrasse", "F5", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic3.jpg", "Bern");
	        Location loc2 =new Location("SweetHome1", 185.0, 4,"8 StrauStrasse", "F8", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic4.jpg", "Berlin");
	        Location loc3 =new Location("SweetHome2", 225.0, 4,"8 StrauStrasse", "Chambre simple", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic7.jpg", "Hamburg");
	        Location loc4 =new Location("SweetHome3", 125.0, 4,"8 StrauStrasse", "Chambre double", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic3.jpg", "Paris");
	        Location loc5 =new Location("SweetHome4", 185.0, 4,"8 StrauStrasse", "F2", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic4.jpg", "Cologne");
	        Location loc6 =new Location("SweetHome5", 225.0, 4,"50 rue des fleurs", "F5", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic7.jpg", "Bern");
	        Location loc7 =new Location("SweetHome6", 225.0, 4,"Blablabla", "F8", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic4.jpg", "Berlin");
	        
	        if(count_location == 0 ){
	       		Loc.add(loc1);
	       		Loc.add(loc2);
	       		Loc.add(loc3);
	       		Loc.add(loc4);
	       		Loc.add(loc5);
	       		Loc.add(loc6);	  
	       		Loc.add(loc7);
	       		
	       		locate.saveLoc(loc1);
	       		locate.saveLoc(loc2);
	       		locate.saveLoc(loc3);
	       		locate.saveLoc(loc4);
	       		locate.saveLoc(loc5);
	       		locate.saveLoc(loc6);
	       		locate.saveLoc(loc7);
	        }
	    	
	       		
	       		/* partie client 
	       		 * 
	       		 */
	        if(count_clients == 0 ){	
	       		Client c3 = new Client("KABAB", "Fahd", "fahd.kabab@uha.fr","fk") ;
	       		Client c4 = new Client("Grandsire", "Alexandre", "alex.grandsire@gmail.com","DUCD") ;
	       		Client c5 = new Client("AMPS", "Sevan", "sevan.amps@outlook.com","DUCD") ;
	       		
	       		client.saveClient(c3);
	       		client.saveClient(c4);
	       		client.saveClient(c5);
	        }
	       		
	       	/*	System.out.println("c1 : "+c1.toString());
	       		System.out.println("c2 : "+c2.toString()); 
	       		System.out.println("Les clients ont étés sauvées"); */
	       		
	       		client.printRepClient(); 
	       		List<Client> list_cli = new ArrayList();
	       		
	       		list_cli = cli.findByName("AMPS"); 
	       		//System.out.println("Find by name donne : "+list_cli.toString());
	       		
	    	/**Récupération des location dans la BDD pour remplir les listes déroulantes**/
	    	List<Location> list_loc = new ArrayList<Location>();
	    	//Toutes les location dans la BDD
	    	list_loc = locate.printRep();
	    	
	    	//On extrait les city
	    	HashSet<String> citySet = new HashSet<String>();
	    	for (int i = 0; i < list_loc.size(); i++) {
	    		citySet.add(list_loc.get(i).getCity());
	    	}
	    	List<String> City = new ArrayList<String>(citySet);
	    	//On extrait les capacités
	    	HashSet<Integer> CapacitySet = new HashSet<Integer>();
	    	for (int i = 0; i < list_loc.size(); i++) {
	    		CapacitySet.add(list_loc.get(i).getCapacity_location());
	    	}
	    	List<Integer> Loca_number = new ArrayList<Integer>(CapacitySet);
	    	
	    	System.out.println("Affichage de toutes les locations sur la page Index");
	    	for (int i = 0; i < list_loc.size(); i++) {
	    		System.out.println(list_loc.get(i).toString());
	    	}
	    	
	    	model.addAttribute("list_city_load", City);
	    	model.addAttribute("list_capacity_load", Loca_number);
	    	
	        return "index";
	    }
	    @RequestMapping(value="/SweetHolidays", method=RequestMethod.POST)
	    public String showIndexPost(Recherche rech, @Valid Client client_insc, BindingResult bindingResult, HttpServletRequest request, Model model) {
	    	 if (bindingResult.hasErrors()) {
		            return "index";
		        }
	    	 /* Création ou récupération de la session */
	    	 HttpSession session = request.getSession();
	    	 List<Client> liste_resultat = new ArrayList();
	    	 /*Encryptage du mot de passe client */
	    	 if(client_insc.getEmail() != null) { 
		    	 if(client_insc.getEmail().length()>16){
		    		 client_insc.setMdp(encrypt(client_insc.getMdp()));
		    	 }
		    	 else {
		    		 client_insc.setMdp(encrypt(client_insc.getMdp()));
		    	 }
	    	 }
	    	 
	    	 //Si l'utilisateur effectue une connexion au lieu d'une inscription
	    	 if(client_insc.getName() == null) {
	    		 //On récupère les utilisateur ayant ce mail dans la bdd
	    		 liste_resultat = cli.findByEmail(client_insc.getEmail());
	    		 //On regarde si la liste contient qqun et si les mdp correpsondent
	    		 if(liste_resultat.size() > 0 && liste_resultat.get(0).getMdp().equals(client_insc.getMdp())) {
	    			 //Mise en session de l'utilisateur
	    			 session.setAttribute("AUTH", liste_resultat.get(0));
	    		 }
	    	 }
	    	 else {
		    	/*On récupère l'inscription du client sur la page et on l'enregistre dans la base*/
		    	client.saveClient(client_insc);
		    	//On le met en session c'est considéré comme une connexion
		    	session.setAttribute("AUTH", client_insc);
	    	 }
	    	 //Suppression de la session lors de la déco
	    	 if(client_insc.getEmail() == null)
	    	 {
	    		 session.setAttribute("AUTH", null);
	    	 }
	    	 
	    	 
	    	 /**Récupération des location dans la BDD pour remplir les listes déroulantes**/
		    	List<Location> list_loc = new ArrayList<Location>();
		    	//Toutes les location dans la BDD
		    	list_loc = locate.printRep();
		    	
		    	//On extrait les city
		    	HashSet<String> citySet = new HashSet<String>();
		    	for (int i = 0; i < list_loc.size(); i++) {
		    		citySet.add(list_loc.get(i).getCity());
		    	}
		    	List<String> City = new ArrayList<String>(citySet);
		    	//On extrait les capacités
		    	HashSet<Integer> CapacitySet = new HashSet<Integer>();
		    	for (int i = 0; i < list_loc.size(); i++) {
		    		CapacitySet.add(list_loc.get(i).getCapacity_location());
		    	}
		    	List<Integer> Loca_number = new ArrayList<Integer>(CapacitySet);
		    	
		    	
		    	model.addAttribute("list_city_load", City);
		    	model.addAttribute("list_capacity_load", Loca_number);
	    	
	    	
	        return "index";
	    }
	    
	    
	    @RequestMapping(value="/SweetPlace", method=RequestMethod.GET)
	    public String showPlacesGet(Client client_insc) {
	        return "rooms";
	    }
	    @RequestMapping(value="/SweetPlace", method=RequestMethod.POST)
	    public String showPlacesPost(@Valid Client client_insc, BindingResult bindingResult,  HttpServletRequest request) {
	    	 if (bindingResult.hasErrors()) {
		            return "rooms";
		        }
	    	 /* Création ou récupération de la session */
	    	 HttpSession session = request.getSession();
	    	 List<Client> liste_resultat = new ArrayList();
	    	 /*Encryptage du mot de passe client */
	    	 if(client_insc.getEmail() != null) { 
		    	 if(client_insc.getEmail().length()>16){
		    		 client_insc.setMdp(encrypt(client_insc.getMdp()));
		    	 }
		    	 else {
		    		 client_insc.setMdp(encrypt(client_insc.getMdp()));
		    	 }
	    	 }
	    	 
	    	 //Si l'utilisateur effectue une connexion au lieu d'une inscription
	    	 if(client_insc.getName() == null) {
	    		// System.out.println(client_insc);
	    		 //On récupère les utilisateur ayant ce mail dans la bdd
	    		 liste_resultat = cli.findByEmail(client_insc.getEmail());
	    		 //On regarde si la liste contient qqun et si les mdp correpsondent
	    		 if(liste_resultat.size() > 0 && liste_resultat.get(0).getMdp().equals(client_insc.getMdp())) {
	    			 //Mise en session de l'utilisateur
	    			 session.setAttribute("AUTH", liste_resultat.get(0));
	    		 }
	    	 }
	    	 else {
		    	/*On récupère l'inscription du client sur la page et on l'enregistre dans la base*/
		    	client.saveClient(client_insc);
		    	//On le met en session c'est considéré comme une connexion
		    	session.setAttribute("AUTH", client_insc);
	    	 }
	    	 //Suppression de la session lors de la déco
	    	 if(client_insc.getEmail() == null)
	    	 {
	    		 session.setAttribute("AUTH", null);
	    	 }
	    	
	        return "rooms";
	    }
	    
	    
	    @RequestMapping(value="/SweetResa", method=RequestMethod.GET)
	    public String showResaGet(Client client_insc, Recherche rech,BindingResult bindingResult, Model model) {
	    	 if (bindingResult.hasErrors()) {
	    		 System.out.println(bindingResult.toString());
		            return "reservation";
		        }
	    	
	    //	System.out.println("Récupération des informations et chargement par un GET De la page SweetResa");
	    	/**Récupération des location dans la BDD pour remplir les listes déroulantes**/
	    	List<Location> list_loc = new ArrayList<Location>();
	    	//Toutes les location dans la BDD
	    	list_loc = locate.printRep();
	    //	System.out.println("List_loc : "+list_loc.toString());
	    //	System.out.println("locate : "+locate.printRep());
	    	//On extrait les city
	    	HashSet<String> citySet = new HashSet<String>();
	    	for (int i = 0; i < list_loc.size(); i++) {
	    		citySet.add(list_loc.get(i).getCity());
	    	}
	    	List<String> City = new ArrayList<String>(citySet);
	    	//On extrait les capacités
	    	HashSet<Integer> CapacitySet = new HashSet<Integer>();
	    	for (int i = 0; i < list_loc.size(); i++) {
	    		CapacitySet.add(list_loc.get(i).getCapacity_location());
	    	}
	    	List<Integer> Loca_number = new ArrayList<Integer>(CapacitySet);
	    	
	    	
	    	
	    	model.addAttribute("list_city_load", City);
	    	model.addAttribute("list_capacity_load", Loca_number);
	    	model.addAttribute("recherche",rech);
	    	
	    	
	    	
	        return "reservation";
	    }  
	    @RequestMapping(value="/SweetResa", method=RequestMethod.POST)
	    public String showResaPost(Recherche rech, @Valid Client client_insc, BindingResult bindingResult, HttpServletRequest request, Model model) {
	    	 if (bindingResult.hasErrors()) {
	    		 System.out.println(bindingResult.toString());
		            return "reservation";
		        }
	    	 
	    	 /* Création ou récupération de la session */
	    	 HttpSession session = request.getSession();
	    	 List<Client> liste_resultat = new ArrayList();
	    	 /*Encryptage du mot de passe client */
	    	 if(client_insc.getEmail() != null) { 
		    	 if(client_insc.getEmail().length()>16){
		    		 client_insc.setMdp(encrypt(client_insc.getMdp()));
		    	 }
		    	 else {
		    		 client_insc.setMdp(encrypt(client_insc.getMdp()));
		    	 }
	    	 }
	    	 
	    	 //Si l'utilisateur effectue une connexion au lieu d'une inscription
	    	 if(client_insc.getName() == null) {
	    		 //On récupère les utilisateur ayant ce mail dans la bdd
	    		 liste_resultat = cli.findByEmail(client_insc.getEmail());
	    		 //On regarde si la liste contient qqun et si les mdp correpsondent
	    		 if(liste_resultat.size() > 0 && liste_resultat.get(0).getMdp().equals(client_insc.getMdp())) {
	    			 //Mise en session de l'utilisateur
	    			 session.setAttribute("AUTH", liste_resultat.get(0));
	    		 }
	    	 }
	    	 else {
		    	/*On récupère l'inscription du client sur la page et on l'enregistre dans la base*/
		    	client.saveClient(client_insc);
		    	//On le met en session c'est considéré comme une connexion
		    	session.setAttribute("AUTH", client_insc);
	    	 }
	    	 //Suppression de la session lors de la déco
	    	 if(client_insc.getEmail() == null)
	    	 {
	    		 session.setAttribute("AUTH", null);
	    	 }
	    	
	    	// System.out.println("Récupération des informations et chargement par un GET De la page SweetResa");
		    	/**Récupération des location dans la BDD pour remplir les listes déroulantes**/
		    	List<Location> list_loc = new ArrayList<Location>();
		    	//Toutes les location dans la BDD
		    	list_loc = locate.printRep();
		    //	System.out.println("List_loc : "+list_loc.toString());
		    //	System.out.println("locate : "+locate.printRep());
		    	//On extrait les city
		    	HashSet<String> citySet = new HashSet<String>();
		    	for (int i = 0; i < list_loc.size(); i++) {
		    		citySet.add(list_loc.get(i).getCity());
		    	}
		    	List<String> City = new ArrayList<String>(citySet);
		    	//On extrait les capacités
		    	HashSet<Integer> CapacitySet = new HashSet<Integer>();
		    	for (int i = 0; i < list_loc.size(); i++) {
		    		CapacitySet.add(list_loc.get(i).getCapacity_location());
		    	}
		    	List<Integer> Loca_number = new ArrayList<Integer>(CapacitySet);
		    	
		    	
		    	
		    	model.addAttribute("list_city_load", City);
		    	model.addAttribute("list_capacity_load", Loca_number);

	    	 
	        return "reservation";
	    }
	    
	    	    
	    @RequestMapping(value="/SweetActivities", method=RequestMethod.GET)
	    public String showActiGet(Client client_insc) {
	        return "activities";
	    }
	    @RequestMapping(value="/SweetActivities", method=RequestMethod.POST)
	    public String showActiPost(@Valid Client client_insc, BindingResult bindingResult, HttpServletRequest request) {
	    	 if (bindingResult.hasErrors()) {
		            return "activities";
		        }
	    	 /* Création ou récupération de la session */
	    	 HttpSession session = request.getSession();
	    	 List<Client> liste_resultat = new ArrayList();
	    	 /*Encryptage du mot de passe client */
	    	 if(client_insc.getEmail() != null) { 
		    	 if(client_insc.getEmail().length()>16){
		    		 client_insc.setMdp(encrypt(client_insc.getMdp()));
		    	 }
		    	 else {
		    		 client_insc.setMdp(encrypt(client_insc.getMdp()));
		    	 }
	    	 }
	    	 
	    	 //Si l'utilisateur effectue une connexion au lieu d'une inscription
	    	 if(client_insc.getName() == null) {
	    		 //On récupère les utilisateur ayant ce mail dans la bdd
	    		 liste_resultat = cli.findByEmail(client_insc.getEmail());
	    		 //On regarde si la liste contient qqun et si les mdp correpsondent
	    		 if(liste_resultat.size() > 0 && liste_resultat.get(0).getMdp().equals(client_insc.getMdp())) {
	    			 //Mise en session de l'utilisateur
	    			 session.setAttribute("AUTH", liste_resultat.get(0));
	    		 }
	    	 }
	    	 else {
		    	/*On récupère l'inscription du client sur la page et on l'enregistre dans la base*/
		    	client.saveClient(client_insc);
		    	//On le met en session c'est considéré comme une connexion
		    	session.setAttribute("AUTH", client_insc);
	    	 }
	    	 //Suppression de la session lors de la déco
	    	 if(client_insc.getEmail() == null)
	    	 {
	    		 session.setAttribute("AUTH", null);
	    	 }
	    	
	        return "activities";
	    }
	    
	    
	    @RequestMapping(value="/SweetContact", method=RequestMethod.GET)
	    public String showContactGet(@Valid Client client_insc) {
	        return "contact";
	    }
	    @RequestMapping(value="/SweetContact", method=RequestMethod.POST)
	    public String showContactPost(@Valid Client client_insc, BindingResult bindingResult, HttpServletRequest request) {
	    	 if (bindingResult.hasErrors()) {
		            return "contact";
		        }
			/* Création ou récupération de la session */
	    	 HttpSession session = request.getSession();
	    	 List<Client> liste_resultat = new ArrayList();
	    	 /*Encryptage du mot de passe client */
	    	 if(client_insc.getEmail() != null) {
		    	 if(client_insc.getEmail().length()>16){
		    		 client_insc.setMdp(encrypt(client_insc.getMdp()));
		    	 }
		    	 else {
		    		 client_insc.setMdp(encrypt(client_insc.getMdp()));
		    	 }
	    	 }
	    	 
	    	 //Si l'utilisateur effectue une connexion au lieu d'une inscription
	    	 if(client_insc.getName() == null) {
	    		 //On récupère les utilisateur ayant ce mail dans la bdd
	    		 liste_resultat = cli.findByEmail(client_insc.getEmail());
	    		 //On regarde si la liste contient qqun et si les mdp correpsondent
	    		 if(liste_resultat.size() > 0 && liste_resultat.get(0).getMdp().equals(client_insc.getMdp())) {
	    			 //Mise en session de l'utilisateur
	    			 session.setAttribute("AUTH", liste_resultat.get(0));
	    		 }
	    	 }
	    	 else {
		    	/*On récupère l'inscription du client sur la page et on l'enregistre dans la base*/
		    	client.saveClient(client_insc);
		    	//On le met en session c'est considéré comme une connexion
		    	session.setAttribute("AUTH", client_insc);
	    	 }
	    	 //Suppression de la session lors de la déco
	    	 if(client_insc.getEmail() == null)
	    	 {
	    		 session.setAttribute("AUTH", null);
	    	 }
	    	 
	    	 
	    	return "contact";
	    }
	    
	    
	    @RequestMapping(value="/SweetNew", method=RequestMethod.GET)
	    public String showNewGet(Newflat newflat, HttpServletRequest request) {
	    	/* Création ou récupération de la session */
	    	 HttpSession session = request.getSession();
	    	 if(session.getAttribute("AUTH") == null)
			 {
	    		 return "index";
			 }

	        return "new";
	    }
	    /*@RequestMapping(value="/SweetNew", method=RequestMethod.POST)
	    public String showNewPost(BindingResult bindingResult) {
	    	 if (bindingResult.hasErrors()) {
		            return "new";
		        }	    	
	        return "new";
	    }*/
	    
	    
	    @RequestMapping(value="/SweetDetails", method=RequestMethod.GET)
	    public String showDetailGet(@Valid Client client_insc) {
	        return "details";
	    }
	    @RequestMapping(value="/SweetDetails", method=RequestMethod.POST)
	    public String showDetailPost(@Valid Client client_insc, BindingResult bindingResult,  HttpServletRequest request) {
	    	 if (bindingResult.hasErrors()) {
		            return "details";
		        }
	    	 /* Création ou récupération de la session */
	    	 HttpSession session = request.getSession();
	    	 List<Client> liste_resultat = new ArrayList();
	    	 /*Encryptage du mot de passe client */
	    	 if(client_insc.getEmail() != null) {
		    	 if(client_insc.getEmail().length()>16){
		    		 client_insc.setMdp(encrypt(client_insc.getMdp()));
		    	 }
		    	 else {
		    		 client_insc.setMdp(encrypt(client_insc.getMdp()));
		    	 }
	    	 }
	    	 
	    	 //Si l'utilisateur effectue une connexion au lieu d'une inscription
	    	 if(client_insc.getName() == null) {
	    		 //On récupère les utilisateur ayant ce mail dans la bdd
	    		 liste_resultat = cli.findByEmail(client_insc.getEmail());
	    		 //On regarde si la liste contient qqun et si les mdp correpsondent
	    		 if(liste_resultat.size() > 0 && liste_resultat.get(0).getMdp().equals(client_insc.getMdp())) {
	    			 //Mise en session de l'utilisateur
	    			 session.setAttribute("AUTH", liste_resultat.get(0));
	    		 }
	    	 }
	    	 else {
		    	/*On récupère l'inscription du client sur la page et on l'enregistre dans la base*/
		    	client.saveClient(client_insc);
		    	//On le met en session c'est considéré comme une connexion
		    	session.setAttribute("AUTH", client_insc);
	    	 }
	    	 //Suppression de la session lors de la déco
	    	 if(client_insc.getEmail() == null)
	    	 {
	    		 session.setAttribute("AUTH", null);
	    	 }
	    	 
	        return "details";
	    }
	     
	    
	     /*@RequestMapping(value="/SweetSearch", method=RequestMethod.GET)
		    public String showSearch(Client client_insc, Recherche rech) {
		        return "resultat";
		    }*/
	     @RequestMapping(value="/SweetSearch", method=RequestMethod.POST)
	     public String checkSearchInfo(@ModelAttribute Recherche recherche, @Valid Client client_insc, Location loc_result, BindingResult bindingResult, Model model, HttpServletRequest request) {
		        if (bindingResult.hasErrors()) {
		        	System.out.println(bindingResult.toString());
		            return "index";
		        }
		        
		        if(recherche.getArrivalDate() != null){
		        	rech = recherche;
		        }
		        
		        /* Création ou récupération de la session */
		    	 HttpSession session = request.getSession();
		    	 List<Client> liste_resultat1 = new ArrayList();
		    	 /*Encryptage du mot de passe client */    		 
		    		 if(client_insc.getEmail() != null && session.getAttribute("AUTH") == null) {
				    	 if(client_insc.getEmail().length()>16){
				    		 client_insc.setMdp(encrypt(client_insc.getMdp()));
				    	 }
				    	 else {
				    		 client_insc.setMdp(encrypt(client_insc.getMdp()));
				    	 }
		    		 }
		    	 
			    	 //Si l'utilisateur effectue une connexion au lieu d'une inscription
			    	 if(recherche.getArrivalDate() == null && client_insc.getName() == null && session.getAttribute("AUTH") == null && client_insc.getEmail() != null) {
			    		//On réaffiche la recherche préinscription
				        recherche = rech;
			    		 //On récupère les utilisateur ayant ce mail dans la bdd
			    		 liste_resultat1 = cli.findByEmail(client_insc.getEmail());
			    		 //On regarde si la liste contient qqun et si les mdp correpsondent
			    		 if(liste_resultat1.size() > 0 && liste_resultat1.get(0).getMdp().equals(client_insc.getMdp())) {
			    			 //Mise en session de l'utilisateur
			    			 session.setAttribute("AUTH", liste_resultat1.get(0));
			    		 }
			    	 }

			        //Si la recherche est null c'est qu'il y a une inscription
			        if(recherche.getArrivalDate() == null && client_insc.getName() != null && session.getAttribute("AUTH") == null) {
			        //	System.out.println(client_insc);
			        	//On réaffiche la recherche préinscription
			        	recherche = rech;
			        	 /*On récupère l'inscription du client sur la page et on l'enregistre dans la base*/
				    	  client.saveClient(client_insc);
				    	//On le met en session c'est considéré comme une connexion
				    	  session.setAttribute("AUTH", client_insc);
			        }
			        
		    	 //Suppression de la session lors de la déco
		    	 if(client_insc.getEmail() == null && recherche.getArrivalDate() == null)
		    	 {
		    		 session.setAttribute("AUTH", null);
		    	 }
	       		/*
		        * 1.Récupérer les informations de la recherche 
		        * 2.Récupérer une liste de Location disponible selon les différentes paramètres de la recherche
		        * 3.Envoyez la liste résultat vers la page résultat
		        */
		       
		        //Récupération des paramètres de recherche
		        String date_arrivee = recherche.getArrivalDate() ; 
		        String date_debut = recherche.getDepartureDate() ; 
		        //Formater de date
		        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		        //Date voulu par le client
	    		Date date_arr_resa_voulu;
	    		Date date_deb_resa_voulu;
	    		//Liste de toutes les resa
	    		List<Reservation> liste_resa = new ArrayList();
				liste_resa = (List<Reservation>) res.findAll();
				resa = true;
				
				try {
					date_arr_resa_voulu = formatter.parse(date_arrivee);
					date_deb_resa_voulu = formatter.parse(date_debut);
					
					for (int i = 0; i < liste_resa.size(); i++) {
						Date date_deb_resa = formatter.parse(liste_resa.get(i).getArrival_date());
						Date date_arr_resa = formatter.parse(liste_resa.get(i).getDeparture_date());
						
						if(date_arr_resa_voulu.before(date_deb_resa) && date_deb_resa_voulu.before(date_arr_resa)){
							resa = true;
						}
						else if(date_arr_resa_voulu.after(date_deb_resa) && date_deb_resa_voulu.after(date_arr_resa)){
							resa = true;
						}
						else {
							resa = false;
						}
					}
					
		    		//System.out.println(formatter.format(date_arr_resa_voulu));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				

		    	
		        int nombre_de_personnes = recherche.getNumber_of_People() ;
		        //String type_de_chambre = recherche.getType_of_room(); 
		        String city = recherche.getCity() ; 
		        
		        //Récupération de la liste 
		       // List<Location> liste_resultat = loc.findByAccomodation_TypeAndCapacity_LocationAndCity(type_de_chambre, nombre_de_personnes, city);
		        List<Location> liste_resultat = loc.findByCapacity_LocationAndCity(nombre_de_personnes,city);
		      //  System.out.println("La liste résultat est : " + loc.toString());
		        
		        
		        //Envoyez la liste vers la page résultat
		        model.addAttribute("Loc_result", liste_resultat);
		        model.addAttribute("recherche", rech);

		        return "resultat";
		    }
	     
	     /******** Code Fahd ajout appart ******/
	     /*@RequestMapping(value="/SweetAddFlat", method=RequestMethod.GET)
	     public String showForm(Newflat newflat) {
	         return "addApart";
	     }*/
	     
	     @RequestMapping(value="/SweetAddFlat", method=RequestMethod.POST)
	     public String checkFlatInfo(@ModelAttribute @Valid Newflat newflat, BindingResult bindingResult, Model model) {
	         if (bindingResult.hasErrors()) {
	             return "new";
	         }
	         model.addAttribute("newflat", newflat);
	         String name = newflat.getNom_Log();
	         Double price = newflat.getPrix_Log();
	         Integer capacity_location = newflat.getCapa_Log();
	         String address = newflat.getAdresse_Log();
	         String accomodation_type = newflat.getType_Log();
	         String accomadation_Rules = newflat.getRegle_Log();
	         String accomodation_description = newflat.getDesc_Log();
	         String img_location = "";
	         String city = newflat.getVille_Log();
	         
	         Location loc1 = new Location(name,price,capacity_location,address,accomodation_type,accomadation_Rules,accomodation_description,img_location,city);
	    
	       		
	         locate.saveLoc(loc1);

	         System.out.println("------------- La location sauvegardé est : : "+loc1.toString());
	         return "addApart";
	     }

	     /**************************************/
	   
	     
	     /*
	      * Partie validation de la réservation + résumé
	      */
	     @RequestMapping(value="/SweetValidation", method=RequestMethod.GET)
		    public String showResaValidGet(Client client_insc) {
		        return "reservation_validation";
		    }
		    @RequestMapping(value="/SweetValidation", method=RequestMethod.POST)
		    public String showResaValidPost(@Valid Client client_insc, Location loc_result, Model model, BindingResult bindingResult, HttpServletRequest request) {
		    	if (bindingResult.hasErrors()) {
		    		 	System.out.println("Erreur : "+bindingResult.toString());
			            return "reservation_validation";
			        }
		    	 HttpSession session = request.getSession();
		    	 Client lio = (Client) session.getAttribute("AUTH");
		    	 
		//    	 System.out.println(lio.getId());
		    	 //Récupération de l'id de la location
		  //  	 System.out.println(loc_result.getId());
		    	
		    	 //Enregistrement de la réservation
		    	 long id_Loc_Loue = loc_result.getId(); 
		    	 long id_client_reserve = lio.getId() ; 
		    	
		    //	System.out.println("Votre recherche : "+rech.toString());
		    	
		    	 if(resa){
			    		
				    	Reservation reservation_enregistre = new Reservation(id_client_reserve, id_Loc_Loue,rech.getArrivalDate(), rech.getDepartureDate(), rech.getNumber_of_People()); 
				    	
				    	reserv.saveResa(reservation_enregistre);
				    	res.save(reservation_enregistre);
				    	//System.out.println("Liste de réservation : "+reserv.printRep());
	
				    	model.addAttribute("reservation", reservation_enregistre );
			    	}
			    	else{
			    		
			    		Reservation reservation_enregistre = null;
			    		model.addAttribute("reservation", reservation_enregistre );
			    		
			    	}
		    	
		        return "reservation_validation";
		    }
		    
		    @RequestMapping(value="/SweetAdmin", method=RequestMethod.GET)
		    public String showResaAdminGET(Model model) {
	    		//Envoyer la liste de réservations sur la page. La liste => reserv 
	    		model.addAttribute("liste_resa",reserv.printRep());
		        return "reservation_admin";
		    }
		    @RequestMapping(value="/SweetAdmin", method=RequestMethod.POST)
		    public String showResaAdminPOST() {
		        return "reservation_admin";
		    }
		    
		    @RequestMapping(value="/SweetAccount", method=RequestMethod.GET)
		    public String showResaCliGET(Model model, HttpServletRequest request) {
	    		//Envoyer la liste de réservations sur la page. La liste => reserv
		    	
		    	HttpSession session = request.getSession();
		    	Client cli = (Client) session.getAttribute("AUTH");
		    	List<Location> liste_resu = new ArrayList<Location>();
		    	List<Reservation> liste_reserv = res.findById_client(cli.getId());
		    	
		    	for(int i=0; i < liste_reserv.size(); i++){
		    		liste_resu.add(locate.findLocationById(liste_reserv.get(i).getId_logement()));
		    	}

			    model.addAttribute("Loc_result", liste_resu);
	    		model.addAttribute("liste_resa", res.findById_client(cli.getId()));
		        return "SweetAccount";
		    }
		    @RequestMapping(value="/SweetAccount", method=RequestMethod.POST)
		    public String showResaCliPOST() {
		        return "SweetAccount";
		    }

	}
