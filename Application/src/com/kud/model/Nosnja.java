package com.kud.model;

import com.kud.enums.TipNosnje;

public class Nosnja {
	private int id;
	private String naziv;
	private TipNosnje tip;
	
	public Nosnja() {
		
	}
	
	public Nosnja(int id, String naziv, TipNosnje tip) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tip = tip;
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

	public TipNosnje getTip() {
		return tip;
	}

	public void setTip(TipNosnje tip) {
		this.tip = tip;
	}

	@Override
	public String toString() {
		return String.format("%-5d %-40s %-10s", id, naziv, tip);
	}
	
	public static String getFormattedHeader() {
		return String.format("%-5s %-40s %-10s", "ID", "NAZIV", "TIP");
	}
}
