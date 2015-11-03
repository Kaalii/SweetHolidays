package fr.uha.miage.sweetholidays.datas;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Reservation")
public class Reservation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id ;
	
	@Column private long id_client ;
	
	@Column private long id_logement ; 
	
	@Column private String arrival_date ; 
	
	@Column private String departure_date ; 
	
	@Column private int number_of_people ;
	
	
	private static int number_of_reservation = 0 ; 

	/**
	 * @param id
	 * @param id_client
	 * @param id_logement
	 * @param arrival_date
	 * @param departure_date
	 * @param number_of_people
	 */
	public Reservation( long id_client, long id_logement, String arrival_date, String departure_date,
			int number_of_people) {
		super();
		this.id = number_of_reservation;
		number_of_reservation = number_of_reservation + 1 ; 
		this.id_client = id_client;
		this.id_logement = id_logement;
		this.arrival_date = arrival_date;
		this.departure_date = departure_date;
		this.number_of_people = number_of_people;
	}
	
	

	public Reservation() {
		super();
	}


	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the id_client
	 */
	public long getId_client() {
		return id_client;
	}

	/**
	 * @param id_client the id_client to set
	 */
	public void setId_client(long id_client) {
		this.id_client = id_client;
	}

	/**
	 * @return the id_logement
	 */
	public long getId_logement() {
		return id_logement;
	}

	/**
	 * @param id_logement the id_logement to set
	 */
	public void setId_logement(long id_logement) {
		this.id_logement = id_logement;
	}

	/**
	 * @return the arrival_date
	 */
	public String getArrival_date() {
		return arrival_date;
	}

	/**
	 * @param arrival_date the arrival_date to set
	 */
	public void setArrival_date(String arrival_date) {
		this.arrival_date = arrival_date;
	}

	/**
	 * @return the departure_date
	 */
	public String getDeparture_date() {
		return departure_date;
	}

	/**
	 * @param departure_date the departure_date to set
	 */
	public void setDeparture_date(String departure_date) {
		this.departure_date = departure_date;
	}

	/**
	 * @return the number_of_people
	 */
	public int getNumber_of_people() {
		return number_of_people;
	}

	/**
	 * @param number_of_people the number_of_people to set
	 */
	public void setNumber_of_people(int number_of_people) {
		this.number_of_people = number_of_people;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", id_client=" + id_client + ", id_logement=" + id_logement + ", arrival_date="
				+ arrival_date + ", departure_date=" + departure_date + ", number_of_people=" + number_of_people + "]";
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
	
	
}
