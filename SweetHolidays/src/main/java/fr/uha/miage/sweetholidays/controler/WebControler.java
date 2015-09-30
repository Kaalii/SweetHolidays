package fr.uha.miage.sweetholidays.controler;


	import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
	    public String showNew() {
	        return "new";
	    }
	    
	    @RequestMapping(value="/SweetDetails", method=RequestMethod.GET)
	    public String showDetail() {
	        return "details";
	    }

	    @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	        registry.addViewController("/SweetSearch").setViewName("resultat");
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
		        model.addAttribute("recherche", recherche);
		        return "resultat";
		    }	
	   

	}
