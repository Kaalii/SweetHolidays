package fr.uha.miage.sweetholidays.datas;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Client")
public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id ; 
	
	@Column private String name ;
	 
	@Column private String prenom ; 
	
	@Column private String email ; 
	
	private static int numClient = 0 ; 
	
	private String mdp ;

	/**
	 * @param id
	 * @param name
	 * @param prenom
	 * @param email
	 * @param mdp
	 */
	public Client(String name, String prenom, String email, String mdp) {
		super();
		this.id = numClient ;
		numClient = numClient ++ ;
		this.name = name;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
	}

	/**
	 * 
	 */
	public Client() {
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}

	/**
	 * @param mdp the mdp to set
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", prenom=" + prenom + ", email=" + email + ", mdp=" + mdp + "]";
	} 
	
	
	
	
	
}
