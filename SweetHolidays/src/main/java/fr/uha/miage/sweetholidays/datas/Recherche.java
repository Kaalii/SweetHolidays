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
	private String City;

	public Recherche(String arrivalDate, String departureDate,
			int number_of_People, String city) {
		
		ArrivalDate = arrivalDate;
		DepartureDate = departureDate;
		Number_of_People = number_of_People;
		City = city;	
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Recherche [ArrivalDate=" + ArrivalDate + ", DepartureDate=" + DepartureDate + ", Number_of_People="
				+ Number_of_People + ", City=" + City + "]";
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
