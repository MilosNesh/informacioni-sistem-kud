package com.kud.model;

import com.kud.enums.TipSekcije;

public class Sekcija {
	private int id;
	private String naziv;
	private TipSekcije tip;
	private int kud;
	
	public Sekcija() {
		
	}
	
	public Sekcija(int id, String naziv, TipSekcije tip, int kud) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tip = tip;
		this.kud = kud;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public TipSekcije getTip() {
		return tip;
	}

	public void setTip(TipSekcije tip) {
		this.tip = tip;
	}

	public int getKud() {
		return kud;
	}

	public void setKud(int kud) {
		this.kud = kud;
	}
	
	@Override
	public String toString() {
		return String.format("%-5d %-50s %-10s", id, naziv, tip.toString());
	}
	
	public static String getFormattedHeader() {
		return String.format("%-5s %-50s %-10s", "ID", "NAZIV", "TIP");
	}
	
}
