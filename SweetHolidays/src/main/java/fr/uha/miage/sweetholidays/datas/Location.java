package fr.uha.miage.sweetholidays.datas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Location")
public class Location implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id; 
	
	private String name ; 
	
	private Double price ;
	
	private Integer capacity_location; 
	
	private String address ;
	
	private String city ;
	
	private String accomodation_type ;
	
	private String accomadation_rules ;
	
	private String accomodation_description;
	
	private String img_Location;
	


	public Location(Long id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}

	/* Constructor */

	public Location() {
		super();
	}
	
	public Location(String name, Double price, Integer capacity_location,
			String address, String accomodation_type, String accomadation_Rules, String accomodation_description, String img_location) {
		super();
		this.name = name;
		this.price = price;
		this.capacity_location = capacity_location;
		this.address = address;
		this.accomodation_type = accomodation_type;
		this.accomadation_rules = accomadation_Rules;
		this.img_Location = img_location;
		this.accomodation_description = 	accomodation_description;	
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/* ToString redefiniton */
	@Override
	public String toString() {
		return "Location [Name=" + name + ", Price=" + price
				+ ", capacity_location=" + capacity_location + ", Address="
				+ address + ", Accomodation_type=" + accomodation_type
				+ ", Accomadation_Rules=" + accomadation_rules + "]";
	}

	/* Getter & Setter */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getCapacity_location() {
		return capacity_location;
	}
	public void setCapacity_location(Integer capacity_location) {
		this.capacity_location = capacity_location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAccomodation_type() {
		return accomodation_type;
	}
	public void setAccomodation_type(String accomodation_type) {
		this.accomodation_type = accomodation_type;
	}
	public String getAccomadation_Rules() {
		return accomadation_rules;
	}
	public void setAccomadation_Rules(String accomadation_Rules) {
		this.accomadation_rules = accomadation_Rules;
	}
	public String getAccomodation_Description() {
		return accomodation_description;
	}
	public void setAccomodation_Description(String accomodation_Description) {
		this.accomodation_description = accomodation_Description;
	}
	public String getImg_Location() {
		return img_Location;
	}
	public void setImg_Location(String img_Location) {
		this.img_Location = img_Location;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	
}
