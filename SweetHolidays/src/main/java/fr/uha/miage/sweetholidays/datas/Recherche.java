package fr.uha.miage.sweetholidays.datas;

import javax.validation.constraints.NotNull;


public class Recherche {

	@NotNull
	private String ArrivalDate;
	@NotNull
	private String DepartureDate; 
	@NotNull
	private int Number_of_People ;
	@NotNull
	private String Type_of_room;
	@NotNull
	private String City;

	public Recherche(String arrivalDate, String departureDate,
			int number_of_People, String type_of_room, String city) {
		
		ArrivalDate = arrivalDate;
		DepartureDate = departureDate;
		Number_of_People = number_of_People;
		City = city;
		Type_of_room = type_of_room;	
	}

	public String getType_of_room() {
		return Type_of_room;
	}

	public void setType_of_room(String type_of_room) {
		Type_of_room = type_of_room;
	}

	public Recherche() {
		super();
	}

	public String getArrivalDate() {
		return ArrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		ArrivalDate = arrivalDate;
	}

	public String getDepartureDate() {
		return DepartureDate;
	}

	public void setDepartureDate(String departureDate) {
		DepartureDate = departureDate;
	}

	public int getNumber_of_People() {
		return Number_of_People;
	}

	public void setNumber_of_People(int number_of_People) {
		Number_of_People = number_of_People;
	} 
		
	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}
}
