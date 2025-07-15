package com.kud.dto;

import java.sql.Date;

public class ClanarinaDTO {
	private int idClanarine;
	private int iznosClanarine;
	private Date datumPlacanja;
	private int idClana;
	private String imeClana;
	private String prezimeClana;
	private String nazivSekcije;
	
	public ClanarinaDTO() {
		
	}
	
	
	public ClanarinaDTO(int idClanarine, int iznosClanarine, Date datumPlacanja, String imeClana, String prezimeClana,
			String nazivSekcije, int idClana) {
		super();
		this.idClanarine = idClanarine;
		this.iznosClanarine = iznosClanarine;
		this.datumPlacanja = datumPlacanja;
		this.imeClana = imeClana;
		this.prezimeClana = prezimeClana;
		this.nazivSekcije = nazivSekcije;
		this.idClana = idClana;
	}


	public int getIdClanarine() {
		return idClanarine;
	}

	public void setIdClanarine(int idClanarine) {
		this.idClanarine = idClanarine;
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

	public String getNazivSekcije() {
		return nazivSekcije;
	}

	public void setNazivSekcije(String nazivSekcije) {
		this.nazivSekcije = nazivSekcije;
	}

	public Date getDatumPlacanja() {
		return datumPlacanja;
	}

	public void setDatumPlacanja(Date datumPlacanja) {
		this.datumPlacanja = datumPlacanja;
	}

	public int getIznosClanarine() {
		return iznosClanarine;
	}

	public void setIznosClanarine(int iznosClanarine) {
		this.iznosClanarine = iznosClanarine;
	}
	
	
	public int getIdClana() {
		return idClana;
	}

	public void setIdClana(int idClana) {
		this.idClana = idClana;
	}


	@Override
	public String toString() {
		return String.format("%-15d %-2d %-1s %-20s %-10d %-15s %-15s %-40s", idClanarine, iznosClanarine, "KM      ", datumPlacanja, idClana, imeClana, prezimeClana, nazivSekcije);		
	}
	
	public static String getFormattedHeader() {
		return String.format("%-15s %-10s %-20s %-12s %-15s %-15s %-42s", "ID CLANARINE", "IZNOS", "DATUM PLACANJA", "ID CLANA", "IME", "PREZIME", "NAZIV SEKCIJE");
	}
	
	
}
