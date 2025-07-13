package com.kud.model;

public class Kud {
	private int id;
	private String naziv;
	private String email;
	private int grad;
	
	public Kud() {
		super();
	}
	public Kud(int id, String naziv, String email, int grad) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.email = email;
		this.grad = grad;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getGrad() {
		return grad;
	}
	public void setGrad(int grad) {
		this.grad = grad;
	}
	
	@Override
	public String toString() {
		return String.format("%-8d %-35.35s %-45s %-6d", id, naziv, email, grad);
	}

	public static String getFormattedHeader() {
		return String.format("%-8s %-35s %-45s %-6s", "ID", "NAZIV", "EMAIL KUDA", "POSTBR GRADA");
	}
}
