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
	
	private String Name ; 
	
	private Double Price ;
	
	private Integer capacity_location; 
	
	private String Address ;
	
	private String Accomodation_type ;
	
	private String Accomadation_Rules ;
	
	private String Accomodation_Description;
	
	private String Img_Location;
	


	public Location(Long id, String name, String address) {
		super();
		this.id = id;
		Name = name;
		Address = address;
	}

	/* Constructor */

	public Location() {
		super();
	}
	
	public Location(String name, Double price, Integer capacity_location,
			String address, String accomodation_type, String accomadation_Rules, String accomodation_description, String img_location) {
		super();
		Name = name;
		Price = price;
		this.capacity_location = capacity_location;
		Address = address;
		Accomodation_type = accomodation_type;
		Accomadation_Rules = accomadation_Rules;
		Img_Location = img_location;
		Accomodation_Description = 	accomodation_description;	
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
		return "Location [Name=" + Name + ", Price=" + Price
				+ ", capacity_location=" + capacity_location + ", Address="
				+ Address + ", Accomodation_type=" + Accomodation_type
				+ ", Accomadation_Rules=" + Accomadation_Rules + "]";
	}

	/* Getter & Setter */
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Double getPrice() {
		return Price;
	}
	public void setPrice(Double price) {
		Price = price;
	}
	public Integer getCapacity_location() {
		return capacity_location;
	}
	public void setCapacity_location(Integer capacity_location) {
		this.capacity_location = capacity_location;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getAccomodation_type() {
		return Accomodation_type;
	}
	public void setAccomodation_type(String accomodation_type) {
		Accomodation_type = accomodation_type;
	}
	public String getAccomadation_Rules() {
		return Accomadation_Rules;
	}
	public void setAccomadation_Rules(String accomadation_Rules) {
		Accomadation_Rules = accomadation_Rules;
	}
	public String getAccomodation_Description() {
		return Accomodation_Description;
	}
	public void setAccomodation_Description(String accomodation_Description) {
		Accomodation_Description = accomodation_Description;
	}
	public String getImg_Location() {
		return Img_Location;
	}
	public void setImg_Location(String img_Location) {
		Img_Location = img_Location;
	}

	
	
	
	
}
