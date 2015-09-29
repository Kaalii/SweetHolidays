package fr.uha.miage.sweetholidays.datas;

public class Recherche {

	private String ArrivalDate;
	private String DepartureDate; 
	
	private int Number_of_People ;

	public Recherche(String arrivalDate, String departureDate,
			int number_of_People) {
		super();
		ArrivalDate = arrivalDate;
		DepartureDate = departureDate;
		Number_of_People = number_of_People;
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
	
	
	
}
