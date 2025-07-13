package com.kud.model;

import java.sql.Date;

import com.kud.enums.TipZaposlenog;

public class Zaposleni {
	private int id;
	private String ime;
	private String prezime;
	private String jmbg;
	private TipZaposlenog tip;
	private Date datumZaposlenja;
	private String brojTelefona;
	private int kud;
	
	public Zaposleni() {
		super();
	}
	
	public Zaposleni(int id, String ime, String prezime, String jmbg, TipZaposlenog tip, Date datumZaposlenja,
			String brojTelefona, int kud) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.tip = tip;
		this.datumZaposlenja = datumZaposlenja;
		this.brojTelefona = brojTelefona;
		this.kud = kud;
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

	public TipZaposlenog getTip() {
		return tip;
	}

	public void setTip(TipZaposlenog tip) {
		this.tip = tip;
	}

	public Date getDatumZaposlenja() {
		return datumZaposlenja;
	}

	public void setDatumZaposlenja(Date datumZaposlenja) {
		this.datumZaposlenja = datumZaposlenja;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public int getKud() {
		return kud;
	}

	public void setKud(int kud) {
		this.kud = kud;
	}
	
	@Override
	public String toString() {
		return String.format("%-8d %-20s %-20s %-15s %-15s %-20s %-20s %-7d", id, ime, prezime, jmbg, tip, datumZaposlenja, brojTelefona, kud );		
	}
	
	public static String getFormattedHeader() {
		return String.format("%-8s %-20s %-20s %-15s %-15s %-20s %-20s %-7s", "ID", "IME", "PREZIME", "JMBG", "TIP ZAPOSLENOG", "DATUM ZAPOSLENJA", "BROJ TELEFONA", "ID KUDA" );
	}
}

