package fr.uha.miage.sweetholidays.controler;


	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
	import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


	@Controller
	public class WebControler extends WebMvcConfigurerAdapter {

	    @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	        registry.addViewController("/results").setViewName("results");
	    }

	    @RequestMapping(value="/SweetHolidays", method=RequestMethod.GET)
	    public String showIndex() {
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
	    
	    @RequestMapping(value="/SweetSearch", method=RequestMethod.GET)
	    public String showSearch() {
	        return "resultat";
	    }	    
	    
	    @RequestMapping(value="/SweetActivities", method=RequestMethod.GET)
	    public String showActi() {
	        return "activities";
	    }
	    
	    @RequestMapping(value="/SweetContact", method=RequestMethod.GET)
	    public String showContact() {
	        return "contact";
	    }
	    
	    @RequestMapping(value="/SweetDetails", method=RequestMethod.GET)
	    public String showDetail() {
	        return "details";
	    }


	    /*@RequestMapping(value="/", method=RequestMethod.POST)
	    public String checkPersonInfo(BindingResult bindingResult) {
	        if (bindingResult.hasErrors()) {
	            return "form";
	        }
	        return "redirect:/results";
	    }*/

	}
