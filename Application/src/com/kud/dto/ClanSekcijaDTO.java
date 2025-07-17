package com.kud.dto;

import com.kud.enums.TipSekcije;

public class ClanSekcijaDTO {
	private int idClana;
	private String imeClana;
	private String prezimeClana;
	private String jmbgClana;
	private int idSekcije;
	private String nazivSekcije;
	private TipSekcije tipSekcije;
	private int idKuda;
	
	public ClanSekcijaDTO() {
		
	}
	
	public ClanSekcijaDTO(int idClana, String imeClana, String prezimeClana, String jmbgClana, int idSekcije, String nazivSekcije,
			TipSekcije tipSekcije, int idKuda) {
		super();
		this.idClana = idClana;
		this.imeClana = imeClana;
		this.prezimeClana = prezimeClana;
		this.idSekcije = idSekcije;
		this.nazivSekcije = nazivSekcije;
		this.tipSekcije = tipSekcije;
		this.jmbgClana = jmbgClana;
		this.idKuda = idKuda;
	}

	public int getIdClana() {
		return idClana;
	}

	public void setIdClana(int idClana) {
		this.idClana = idClana;
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

	public int getIdSekcije() {
		return idSekcije;
	}

	public void setIdSekcije(int idSekcije) {
		this.idSekcije = idSekcije;
	}

	public String getNazivSekcije() {
		return nazivSekcije;
	}

	public void setNazivSekcije(String nazivSekcije) {
		this.nazivSekcije = nazivSekcije;
	}

	public TipSekcije getTipSekcije() {
		return tipSekcije;
	}

	public void setTipSekcije(TipSekcije tipSekcije) {
		this.tipSekcije = tipSekcije;
	}
	
	public String getJmbgClana() {
		return jmbgClana;
	}

	public void setJmbgClana(String jmbgClana) {
		this.jmbgClana = jmbgClana;
	}

	public int getIdKuda() {
		return idKuda;
	}

	public void setIdKuda(int idKuda) {
		this.idKuda = idKuda;
	}

	@Override
	public String toString() {
		return String.format("%-15d %-15s %-15s %-15d %-35s %-15s", idClana, imeClana, prezimeClana, idSekcije, nazivSekcije, tipSekcije.toString());
	}
	
	public static String getFormattedHeader() {
		return String.format("%-15s %-15s %-15s %-15s %-35s %-15s", "ID CLANA", "IME", "PREZIME", "ID SEKCIJE", "NAZIV", "TIP SEKCIJE");
	}
}
