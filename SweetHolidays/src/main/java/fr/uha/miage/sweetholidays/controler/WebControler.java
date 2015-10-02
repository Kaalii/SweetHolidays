package fr.uha.miage.sweetholidays.controler;


	import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import fr.uha.miage.sweetholidays.datas.Location;
import fr.uha.miage.sweetholidays.datas.Newflat;
import fr.uha.miage.sweetholidays.datas.Recherche;


	@Controller
	public class WebControler extends WebMvcConfigurerAdapter {

	    @RequestMapping(value="/SweetHolidays", method=RequestMethod.GET)
	    public String showIndex(Recherche rech) {
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
		        List<Location> Loc = new ArrayList<Location>();
		       
		        //Effectuer la requête SQl
		        
		        //Récupération des résultats
		        Location loc1 =new Location("SweetHome", 125.0, 4,"8 StrauStrasse", "F5", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic3.jpg");
		        Location loc2 =new Location("SweetHome1", 185.0, 4,"8 StrauStrasse", "F8", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic4.jpg");
		        Location loc3 =new Location("SweetHome2", 225.0, 4,"8 StrauStrasse", "Chambre simple", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic7.jpg");
		        Location loc4 =new Location("SweetHome3", 125.0, 4,"8 StrauStrasse", "Chambre double", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic3.jpg");
		        Location loc5 =new Location("SweetHome4", 185.0, 4,"8 StrauStrasse", "F2", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic4.jpg");
		        Location loc6 =new Location("SweetHome5", 225.0, 4,"8 StrauStrasse", "F6", "Réglement intérieur", "Description visuelle de l'appart", "ser_pic7.jpg");

		       		Loc.add(loc1);
		       		Loc.add(loc2);
		       		Loc.add(loc3);
		       		Loc.add(loc4);
		       		Loc.add(loc5);
		       		Loc.add(loc6);
		        
		        
		        //Créer un list a envoyer pour récupérer les résultat de recherche
		        
		        model.addAttribute("recherche", recherche);
		        model.addAttribute("Loc_result", Loc);
		        
		        
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
