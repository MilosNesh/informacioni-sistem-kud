package com.kud.dto;

import java.sql.Date;

public class PozajmicaDTO {
	private int idNosnje;
	private String nazivNosnje;
	private Date datumZaduzenja;
	private int idClana;
	private String jmbgClana;
	private String imeClana;
	private String prezimeClana;
	
	public PozajmicaDTO() {
		
	}
	
	public PozajmicaDTO(int idNosnje, String nazivNosnje, Date datumZaduzenja, int idClana, String jmbgClana,
			String imeClana, String prezimeClana) {
		super();
		this.idNosnje = idNosnje;
		this.nazivNosnje = nazivNosnje;
		this.datumZaduzenja = datumZaduzenja;
		this.idClana = idClana;
		this.jmbgClana = jmbgClana;
		this.imeClana = imeClana;
		this.prezimeClana = prezimeClana;
	}

	public int getIdNosnje() {
		return idNosnje;
	}

	public void setIdNosnje(int idNosnje) {
		this.idNosnje = idNosnje;
	}

	public String getNazivNosnje() {
		return nazivNosnje;
	}

	public void setNazivNosnje(String nazivNosnje) {
		this.nazivNosnje = nazivNosnje;
	}

	public Date getDatumZaduzenja() {
		return datumZaduzenja;
	}

	public void setDatumZaduzenja(Date datumZaduzenja) {
		this.datumZaduzenja = datumZaduzenja;
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

	public String getImeClana() {
		return imeClana;
	}

	public void setImeClana(String imeClana) {
		this.imeClana = imeClana;
	}

	public String getPrezimeClana() {
		return prezimeClana;
	}

	public void setPrezimeClana(String prezimeClana) {
		this.prezimeClana = prezimeClana;
	}
	
	@Override
	public String toString() {
		return String.format("%-10d %-40s %-20s %-10d %-20s %-30s %-30s", idNosnje, nazivNosnje, datumZaduzenja, idClana, jmbgClana, imeClana, prezimeClana);
	}
	
	public static String getFormattedHeader() {
		return String.format("%-10s %-40s %-20s %-10s %-20s %-30s %-30s", "ID NOSNJE", "NAZIV", "DATUM", "ID CLANA", "JMBG", "IME", "PREZIME");
	}
}
