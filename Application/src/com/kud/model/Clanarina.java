package com.kud.model;

import java.sql.Date;

public class Clanarina {
	private int id;
	private double iznos;
	private Date datumPlacanja;
	private int idClana;
	private String jmbgClana;
	private int idSekretara;
	private String jmngSekretara;
	
	public Clanarina() {
		
	}
	
	public Clanarina(int id, double iznos, Date datumPlacanja, int idClana, String jmbgClana, int idSekretara,
			String jmngSekretara) {
		super();
		this.id = id;
		this.iznos = iznos;
		this.datumPlacanja = datumPlacanja;
		this.idClana = idClana;
		this.jmbgClana = jmbgClana;
		this.idSekretara = idSekretara;
		this.jmngSekretara = jmngSekretara;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public Date getDatumPlacanja() {
		return datumPlacanja;
	}

	public void setDatumPlacanja(Date datumPlacanja) {
		this.datumPlacanja = datumPlacanja;
	}

	public int getIdClana() {
		return idClana;
	}

	public void setIdClana(int idClana) {
		this.idClana = idClana;
	}

	public String getJmbgClana() {
		return jmbgClana;
	}

	public void setJmbgClana(String jmbgClana) {
		this.jmbgClana = jmbgClana;
	}

	public int getIdSekretara() {
		return idSekretara;
	}

	public void setIdSekretara(int idSekretara) {
		this.idSekretara = idSekretara;
	}

	public String getJmngSekretara() {
		return jmngSekretara;
	}

	public void setJmngSekretara(String jmngSekretara) {
		this.jmngSekretara = jmngSekretara;
	}
	
	
}
