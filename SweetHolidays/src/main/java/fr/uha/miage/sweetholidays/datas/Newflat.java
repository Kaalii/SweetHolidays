package fr.uha.miage.sweetholidays.datas;

import javax.validation.constraints.NotNull;

public class Newflat {
	
	@NotNull
	private String Type_Log;
	@NotNull
	private Double Prix_Log;
	@NotNull
	private int Capa_Log; 
	@NotNull
	private String Nom_Log;
	@NotNull
	private String Adresse_Log;
	@NotNull
	private int CP_Log;
	@NotNull
	private String Ville_Log;
	@NotNull
	private String Desc_Log;
	@NotNull
	private String Regle_Log;
	

	public Newflat(String type_Log, Double prix_Log, int capa_Log, String nom_Log,String adresse_Log, int cP_Log, String ville_Log, String desc_Log,String regle_Log) {
		Type_Log = type_Log;
		Prix_Log = prix_Log;
		Capa_Log = capa_Log;
		Nom_Log = nom_Log;
		Adresse_Log = adresse_Log;
		CP_Log = cP_Log;
		Ville_Log = ville_Log;
		Desc_Log = desc_Log;
		Regle_Log = regle_Log;
	}

	public Newflat() {
		super();
	}	
	
	/*******************SETTER & GETTER**************/
	public String getType_Log() {
		return Type_Log;
	}
	public void setType_Log(String type_Log) {
		Type_Log = type_Log;
	}
	public Double getPrix_Log() {
		return Prix_Log;
	}
	public void setPrix_Log(Double prix_Log) {
		Prix_Log = prix_Log;
	}

	public int getCapa_Log() {
		return Capa_Log;
	}
	public void setCapa_Log(int capa_Log) {
		Capa_Log = capa_Log;
	}
	public String getNom_Log() {
		return Nom_Log;
	}
	public void setNom_Log(String nom_Log) {
		Nom_Log = nom_Log;
	}
	public String getAdresse_Log() {
		return Adresse_Log;
	}
	public void setAdresse_Log(String adresse_Log) {
		Adresse_Log = adresse_Log;
	}
	public int getCP_Log() {
		return CP_Log;
	}
	public void setCP_Log(int cP_Log) {
		CP_Log = cP_Log;
	}
	public String getVille_Log() {
		return Ville_Log;
	}
	public void setVille_Log(String ville_Log) {
		Ville_Log = ville_Log;
	}
	public String getDesc_Log() {
		return Desc_Log;
	}
	public void setDesc_Log(String desc_Log) {
		Desc_Log = desc_Log;
	}
	public String getRegle_Log() {
		return Regle_Log;
	}
	public void setRegle_Log(String regle_Log) {
		Regle_Log = regle_Log;
	}	

}
