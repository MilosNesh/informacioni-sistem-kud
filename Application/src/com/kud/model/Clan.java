package com.kud.model;

import java.sql.Date;

import com.kud.enums.PolClana;

public class Clan {
	private int id;
	private String ime;
	private String prezime;
	private String jmbg;
	private String brojTelefona;
	private Date datumRodjenja;
	private Date datumUpisa;
	private PolClana pol;
	
	public Clan() {
		
	}
	
	public Clan(int id, String ime, String prezime, String jmbg, String brojTelefona, Date datumRodjenja,
			Date datumUpisa, PolClana pol) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.brojTelefona = brojTelefona;
		this.datumRodjenja = datumRodjenja;
		this.datumUpisa = datumUpisa;
		this.pol = pol;
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
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public Date getDatumUpisa() {
		return datumUpisa;
	}
	public void setDatumUpisa(Date datumUpisa) {
		this.datumUpisa = datumUpisa;
	}
	public PolClana getPol() {
		return pol;
	}
	public void setPol(PolClana pol) {
		this.pol = pol;
	}
	
	@Override
	public String toString() {
		return String.format("%-8d %-20s %-20s %-15s %-15s %-20s %-20s %-7s", id, ime, prezime, jmbg, brojTelefona, datumRodjenja, datumUpisa, pol.toString() );		
	}
	
	public static String getFormattedHeader() {
		return String.format("%-8s %-20s %-20s %-15s %-15s %-20s %-20s %-7s", "ID", "IME", "PREZIME", "JMBG", "BROJ TELEFONA", "DATUM RODJENJA", "DATUM UPISA", "POL" );
	}
	
}
