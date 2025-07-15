package com.kud.model;

import java.sql.Date;

public class Clanarina {
	private int id;
	private int iznos;
	private Date datumPlacanja;
	private int idClana;
	private String jmbgClana;
	private int idSekretara;
	private String jmbgSekretara;
	
	public Clanarina() {
		
	}
	
	public Clanarina(int id, int iznos, Date datumPlacanja, int idClana, String jmbgClana, int idSekretara,
			String jmbgSekretara) {
		super();
		this.id = id;
		this.iznos = iznos;
		this.datumPlacanja = datumPlacanja;
		this.idClana = idClana;
		this.jmbgClana = jmbgClana;
		this.idSekretara = idSekretara;
		this.jmbgSekretara = jmbgSekretara;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIznos() {
		return iznos;
	}

	public void setIznos(int iznos) {
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

	public String getJmbgSekretara() {
		return jmbgSekretara;
	}

	public void setJmbgSekretara(String jmbgSekretara) {
		this.jmbgSekretara = jmbgSekretara;
	}
	
	
}
