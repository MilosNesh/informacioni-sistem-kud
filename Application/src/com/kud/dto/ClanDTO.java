package com.kud.dto;

public class ClanDTO {
	private int id;
	private String ime;
	private String prezime;
	private String nazivSekcije;
	
	public ClanDTO() {
		
	}
	
	public ClanDTO(int id, String ime, String prezime, String nazivSekcije) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.nazivSekcije = nazivSekcije;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getNazivSekcije() {
		return nazivSekcije;
	}

	public void setNazivSekcije(String nazivSekcije) {
		this.nazivSekcije = nazivSekcije;
	}
	
	@Override
	public String toString() {
		return String.format("%-5d %-20s %-20s %-30s", id, ime, prezime, nazivSekcije);
	}
	
	public static String getFormattedHeader() {
		return String.format("%-5S %-20s %-20s %-30s", "ID", "IMW", "PREZIME", "NAZIV SEKCIJE");
	}
}
