package com.kud.model;

public class Koreografija {
	private int id;
	private String naziv;
	private String kreator;
	private double trajanje;
	
	public Koreografija() {
		
	}
	
	public Koreografija(int id, String naziv, String kreator, double trajenje) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.kreator = kreator;
		this.trajanje = trajenje;
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

	public String getKreator() {
		return kreator;
	}

	public void setKreator(String kreator) {
		this.kreator = kreator;
	}

	public double getTrajenje() {
		return trajanje;
	}

	public void setTrajenje(double trajenje) {
		this.trajanje = trajenje;
	}
	
	@Override
	public String toString() {
		return String.format("%-5d %-30s %-30s %-10f", id, naziv, kreator, trajanje);
	}
	
	public static String getFormattedHeader() {
		return String.format("%-5s %-30s %-30s %-10s", "ID", "NAZIV", "KREATOR", "TRAJANJE");
	}
}
