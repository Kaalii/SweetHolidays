package fr.uha.miage.sweetholidays.controler;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		
	    @RequestMapping(value="/SweetHolidays", method=RequestMethod.GET)
	    public String showIndex(Recherche rech, Model model) {
	    	
	    	
	    	/******Création fictive pour test****/
	    	 List<Location> Loc = new ArrayList<Location>();
	    	//Récupération des résultats
	        Location loc1 =new Location("Sweet", 125.0, 4,"8 StrauStrasse", "F5", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic3.jpg", "Bern");
	        Location loc2 =new Location("SweetHome1", 185.0, 4,"8 StrauStrasse", "F8", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic4.jpg", "Berlin");
	        Location loc3 =new Location("SweetHome2", 225.0, 4,"8 StrauStrasse", "Chambre simple", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic7.jpg", "Hamburg");
	        Location loc4 =new Location("SweetHome3", 125.0, 4,"8 StrauStrasse", "Chambre double", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic3.jpg", "Paris");
	        Location loc5 =new Location("SweetHome4", 185.0, 4,"8 StrauStrasse", "F2", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic4.jpg", "Cologne");
	        Location loc6 =new Location("SweetHome5", 225.0, 4,"50 rue des fleurs", "F5", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic7.jpg", "Bern");

	       		Loc.add(loc1);
	       		Loc.add(loc2);
	       		Loc.add(loc3);
	       		Loc.add(loc4);
	       		Loc.add(loc5);
	       		Loc.add(loc6);	  
	       		
	       		locate.saveLoc(loc1);
	       		locate.saveLoc(loc2);
	       		locate.saveLoc(loc3);
	       		locate.saveLoc(loc4);
	       		locate.saveLoc(loc5);
	       		locate.saveLoc(loc6);
	    	
	       		
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
	       		
	       		System.out.println("Les clients ont étés sauvées");
	       		
	       		client.printRepClient(); 
	       		
	       		Client clName = cli.findByName("AMPS"); 
	       		System.out.println("Find by name donne : "+clName.toString());
	       		
	    	/**Récupération des location dans la BDD pour remplir les listes déroulantes**/
	    	List<Location> list_loc = new ArrayList<Location>();
	    	//Toutes les location dans la BDD
	    	list_loc = locate.printRep();
	    	
	    	//On extrait les type de location (appart chambre ...)
	    	HashSet<String> AccoSet = new HashSet<String>();
	    	for (int i = 0; i < list_loc.size(); i++) {
	    		AccoSet.add(list_loc.get(i).getAccomodation_type());
	    	}
	    	List<String> accomodation_type = new ArrayList<String>(AccoSet);
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
	    	
	    	
	    	model.addAttribute("list_acco_load", accomodation_type);
	    	model.addAttribute("list_city_load", City);
	    	model.addAttribute("list_capacity_load", Loca_number);
	    	
	        return "index";
	    }
	    
	    @RequestMapping(value="/SweetPlace", method=RequestMethod.GET)
	    public String showPlaces() {
	        return "rooms";
	    }
	    
	    @RequestMapping(value="/SweetResa", method=RequestMethod.GET)
	    public String showResa() {
	        return "reservation";
	    }    
	    
	    @RequestMapping(value="/SweetActivities", method=RequestMethod.GET)
	    public String showActi() {
	        return "activities";
	    }
	    
	    @RequestMapping(value="/SweetContact", method=RequestMethod.GET)
	    public String showContact() {
	        return "contact";
	    }
	    
	    @RequestMapping(value="/SweetNew", method=RequestMethod.GET)
	    public String showNew(Newflat newflat) {
	        return "new";
	    }
	    
	    @RequestMapping(value="/SweetDetails", method=RequestMethod.GET)
	    public String showDetail() {
	        return "details";
	    }

	    @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	    	registry.addViewController("/SweetSearch").setViewName("resultat");
	    	/****Ajout***/
	    	 registry.addViewController("/SweetAddFlat").setViewName("addApart");
	    }
	     
	     /*@RequestMapping(value="/SweetSearch", method=RequestMethod.GET)
		    public String showSearch(Recherche rech) {
		        return "resultat";
		    }	*/
	     
	     @RequestMapping(value="/SweetSearch", method=RequestMethod.POST)
	     public String checkSearchInfo(@ModelAttribute @Valid Recherche recherche, BindingResult bindingResult, Model model) {
		        if (bindingResult.hasErrors()) {
		            return "index";
		        }
		       
		       
		        /* Partie Base de données : locate gert le repository et loc s'occupe d'effectuer les findByX *
		         * 
		         */

		        //Effectuer la requête SQl
		        /***A décommenter pour tester provoque une erreur si on refresh la page index 
		         * c'est normal tant que la partie de Fahd n'est pas faite et qu'on déclare les location en statique**/
		        /*List<Location> result = new ArrayList<Location>();
		        result = loc.findByAccomodation_TypeAndCapacity_LocationAndCity(recherche.getType_of_room(), recherche.getNumber_of_People(), recherche.getCity());

		        model.addAttribute("Loc_result", result);*/
		        
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
	         return "addApart";
	     }

	     /**************************************/
	   

	}
