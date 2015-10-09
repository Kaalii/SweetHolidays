package fr.uha.miage.sweetholidays.controler;


import java.util.ArrayList;
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
		
		//Variable qui permet de mémoriser les info POST en cas d'inscription
		Recherche rech;
		
	    @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	    	registry.addViewController("/SweetSearch").setViewName("resultat");
	    	/****Ajout***/
	    	 registry.addViewController("/SweetAddFlat").setViewName("addApart");
	    }
	    
		
	    @RequestMapping(value="/SweetHolidays", method=RequestMethod.GET)
	    public String showIndexGet(Client client_insc, Recherche rech, Model model) {
	    	
	    	// Effacement des répertoires pour des multiples recherches.
	    	locate.effaceRep();
	    	client.effaceRepClient();
	    	
	    	/******Création fictive pour test****/
	    	 List<Location> Loc = new ArrayList<Location>();
	    	//Récupération des résultats
	        Location loc1 =new Location("Sweet", 125.0, 4,"8 StrauStrasse", "F5", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic3.jpg", "Bern");
	        Location loc2 =new Location("SweetHome1", 185.0, 4,"8 StrauStrasse", "F8", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic4.jpg", "Berlin");
	        Location loc3 =new Location("SweetHome2", 225.0, 4,"8 StrauStrasse", "Chambre simple", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic7.jpg", "Hamburg");
	        Location loc4 =new Location("SweetHome3", 125.0, 4,"8 StrauStrasse", "Chambre double", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic3.jpg", "Paris");
	        Location loc5 =new Location("SweetHome4", 185.0, 4,"8 StrauStrasse", "F2", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic4.jpg", "Cologne");
	        Location loc6 =new Location("SweetHome5", 225.0, 4,"50 rue des fleurs", "F5", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic7.jpg", "Bern");
	        Location loc7 =new Location("SweetHome6", 225.0, 4,"Blablabla", "F8", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic4.jpg", "Berlin");
	        
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
	       		
	    	
	       		
	       		/* partie client 
	       		 * 
	       		 */
	       		Client c1 = new Client("AMPS", "Sevan", "sevan.amps@outlook.com","sevan1010") ;
	       		Client c2 = new Client("GRANDSIRE", "Alexandre", "zizi@outlook.com","j'aimelesvoitures") ;	       		
	       		Client c3 = new Client("KABAB", "Fahd", "fahd.kabab@uha.fr","fk") ;
	       		Client c4 = new Client("Muller", "Lagaffe", "gaston.lagaffe@uha.fr","jesuismaladedunez") ;
	       		
	       		client.saveClient(c1);
	       		client.saveClient(c2);
	       		client.saveClient(c3);
	       		client.saveClient(c4);
	       		
	       		System.out.println("c1 : "+c1.toString());
	       		System.out.println("c2 : "+c2.toString()); 
	       		System.out.println("Les clients ont étés sauvées");
	       		
	       		client.printRepClient(); 
	       		List<Client> list_cli = new ArrayList();
	       		
	       		list_cli = cli.findByName("AMPS"); 
	       		System.out.println("Find by name donne : "+list_cli.toString());
	       		
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
	    @RequestMapping(value="/SweetHolidays", method=RequestMethod.POST)
	    public String showIndexPost(Recherche rech, @Valid Client client_insc, BindingResult bindingResult, HttpServletRequest request) {
	    	 if (bindingResult.hasErrors()) {
		            return "index";
		        }
	    	 /* Création ou récupération de la session */
	    	 HttpSession session = request.getSession();
	    	 List<Client> liste_resultat = new ArrayList();
	    	 
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
	    	
	        return "rooms";
	    }
	    
	    
	    @RequestMapping(value="/SweetResa", method=RequestMethod.GET)
	    public String showResaGet(Client client_insc, Recherche rech,BindingResult bindingResult, Model model) {
	    	 if (bindingResult.hasErrors()) {
	    		 System.out.println(bindingResult.toString());
		            return "reservation";
		        }
	    	
	    	System.out.println("Récupération des informations et chargement par un GET De la page SweetResa");
	    	/**Récupération des location dans la BDD pour remplir les listes déroulantes**/
	    	List<Location> list_loc = new ArrayList<Location>();
	    	//Toutes les location dans la BDD
	    	list_loc = locate.printRep();
	    	System.out.println("List_loc : "+list_loc.toString());
	    	System.out.println("locate : "+locate.printRep());
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
	    @RequestMapping(value="/SweetResa", method=RequestMethod.POST)
	    public String showResaPost(@Valid Client client_insc, BindingResult bindingResult) {
	    	 if (bindingResult.hasErrors()) {
	    		 System.out.println(bindingResult.toString());
		            return "reservation";
		        }
	    	/*On récupère l'inscription du client sur la page et on l'enregistre dans la base*/
	    	client.saveClient(client_insc);
	    	
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
	    	 
	    	return "contact";
	    }
	    
	    
	    @RequestMapping(value="/SweetNew", method=RequestMethod.GET)
	    public String showNewGet(Client client_insc, Newflat newflat) {
	        return "new";
	    }
	    @RequestMapping(value="/SweetNew", method=RequestMethod.POST)
	    public String showNewPost(@Valid Client client_insc, BindingResult bindingResult) {
	    	 if (bindingResult.hasErrors()) {
		            return "new";
		        }
	    	/*On récupère l'inscription du client sur la page et on l'enregistre dans la base*/
	    	client.saveClient(client_insc);
	    	
	        return "new";
	    }
	    
	    
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
	    	 
	        return "details";
	    }
	     
	    
	     /*@RequestMapping(value="/SweetSearch", method=RequestMethod.GET)
		    public String showSearch(Client client_insc, Recherche rech) {
		        return "resultat";
		    }*/
	     @RequestMapping(value="/SweetSearch", method=RequestMethod.POST)
	     public String checkSearchInfo(@ModelAttribute Recherche recherche, @Valid Client client_insc, BindingResult bindingResult, Model model, HttpServletRequest request) {
		        if (bindingResult.hasErrors()) {
		        	System.out.println(bindingResult.toString());
		            return "index";
		        }
		        
		        
		        /* Création ou récupération de la session */
		    	 HttpSession session = request.getSession();
		    	 List<Client> liste_resultat1 = new ArrayList();
		    	 
		    	 //Si l'utilisateur effectue une connexion au lieu d'une inscription
		    	 if(client_insc.getName() == null) {
		    		 //On récupère les utilisateur ayant ce mail dans la bdd
		    		 liste_resultat1 = cli.findByEmail(client_insc.getEmail());
		    		 //On regarde si la liste contient qqun et si les mdp correpsondent
		    		 if(liste_resultat1.size() > 0 && liste_resultat1.get(0).getMdp().equals(client_insc.getMdp())) {
		    			 //Mise en session de l'utilisateur
		    			 session.setAttribute("AUTH", liste_resultat1.get(0));
		    		 }
		    	 }
		        
		        //Si la recherche est null c'est qu'il y a une inscription
		        if(recherche.getArrivalDate() == null) {
		        	//On réaffiche la recherche préinscription
		        	recherche = rech;
		        	 /*On récupère l'inscription du client sur la page et on l'enregistre dans la base*/
			    	  client.saveClient(client_insc);
			    	//On le met en session c'est considéré comme une connexion
			    	  session.setAttribute("AUTH", client_insc);
		        }
		        else {
		        	//Sinon on garde en mémoire la recherche actuel au cas ou il y a une inscri
		        	rech = recherche;
		        }

	       		/*
		        * 1.Récupérer les informations de la recherche 
		        * 2.Récupérer une liste de Location disponible selon les différentes paramètres de la recherche
		        * 3.Envoyez la liste résultat vers la page résultat
		        */
		        
		        //Récupération des paramètres de recherche
		        String date_arrivee = recherche.getArrivalDate() ; 
		        String date_debut = recherche.getDepartureDate() ; 
		        int nombre_de_personnes = recherche.getNumber_of_People() ;
		        //String type_de_chambre = recherche.getType_of_room(); 
		        String city = recherche.getCity() ; 
		        
		        //Récupération de la liste 
		       // List<Location> liste_resultat = loc.findByAccomodation_TypeAndCapacity_LocationAndCity(type_de_chambre, nombre_de_personnes, city);
		        List<Location> liste_resultat = loc.findByCapacity_LocationAndCity(nombre_de_personnes,city);
		        System.out.println("La liste résultat est : " + loc.toString());
		        
		        
		        //Envoyez la liste vers la page résultat
		        model.addAttribute("Loc_result", liste_resultat);
		        model.addAttribute("recherche", recherche);

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

	         System.out.println("loc1 : "+loc1.toString());
	         return "addApart";
	     }

	     /**************************************/
	   

	}
