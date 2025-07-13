package com.kud.ui_handler;

import java.sql.SQLException;
import java.util.Scanner;

import com.kud.model.Kud;
import com.kud.service.KudService;

public class KudUIHandler {
	private static final KudService kudService = new KudService();
	
	public void handleKudMenu() {
		String answer;
		do {
			System.out.println("--------------------------------------------------------------------------------------------------------");
			System.out.println("\nOdaberite opciju za rad nad kudovima:");
			System.out.println("1 - Prikaz svih");
			System.out.println("2 - Prikaz po identifikatoru");
			System.out.println("3 - Prikaz po gradovima");
			System.out.println("4 - Unos novog kuda");
			System.out.println("5 - Izmena po identifikatoru");
			System.out.println("6 - Brisanje po identifikatoru");
			System.out.println("X - Izlazak iz rukovanja kudovima");

			answer = MainUIHandler.sc.nextLine();

			switch (answer) {
			case "1":
				showAll();
				break;
			case "2":
				showById();
				break;
			case "3":
				showAllByPostBrGrada();
				break;
			case "4":
				insert();
				break;
			case "5":
				update();
				break;
			case "6":
				delete();
				break;
			}

		} while (!answer.equalsIgnoreCase("X"));
	}
	
	private void showAll() {
		System.out.println(Kud.getFormattedHeader());
		System.out.println("--------------------------------------------------------------------------------------------------------");

		try {
			for (Kud kud : kudService.getAll()) {
				System.out.println(kud);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	private void showById() {
		boolean validanUnos = false;
		Integer id=0;
		while (!validanUnos) {
			System.out.print("Unesite ID kuda za pretragu: ");
			String idS = MainUIHandler.sc.nextLine();
		    
			try {
				id = Integer.parseInt(idS);
				validanUnos = true;
			} catch (NumberFormatException e) {
				System.out.println("To nije broj! Pokušajte ponovo.");
			}
   		}
        
		System.out.println(Kud.getFormattedHeader());
		System.out.println("--------------------------------------------------------------------------------------------------------");

		try {
			Kud kud = kudService.getById(id);
			System.out.println(kud);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	private void insert() {
		System.out.println("Unesite naziv kuda: ");
		String naziv = MainUIHandler.sc.nextLine();
		
		System.out.println("Unesite email adresu kuda: ");
		String email = MainUIHandler.sc.nextLine();
		
		System.out.println("Unesite postanski broj grada u kome se kud nalazi: ");
		Integer postanskiBroj = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		Kud kud = new Kud(0, naziv, email, postanskiBroj);
		try {
			boolean isSaved = kudService.save(kud);
			if (isSaved) {
				System.out.println("Kud uspjesno unesen u bazu!");
			}
		}  catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske. Kud nije unesen.");
		}
	}
	
	private void update() {
		System.out.println("Unesite ID kuda koji zelite da izmjenite:  ");
		Integer id = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		try {
			boolean exist = kudService.existsById(id);
			if(!exist) {
				System.out.println("Nema kuda sa trazenim ID.");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		System.out.println("Unesite naziv kuda: ");
		String naziv = MainUIHandler.sc.nextLine();
		
		System.out.println("Unesite email adresu kuda: ");
		String email = MainUIHandler.sc.nextLine();
		
		System.out.println("Unesite postanski broj grada u kome se kud nalazi: ");
		Integer postanskiBroj = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		Kud kud = new Kud(id, naziv, email, postanskiBroj);
		try {
			boolean isUpdated = kudService.save(kud);
			if (isUpdated) {
				System.out.println("Kud uspjesno izmnjenjen!");
			}
		}  catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske. Kud nije izmjenjen.");
		}
		
	}
	
	private void delete() {
		System.out.println("Unesite ID kuda koji zelite da obrisete:  ");
		Integer id = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		try {
			boolean deleted = kudService.deleteById(id);
			if(deleted) {
				System.out.println("Kud uspjesno obrisan.");;
			}else {
				System.out.println("Doslo je do greske prilikom brisanja. Kud sa zadatim ID ne postoji.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void showAllByPostBrGrada() {
		System.out.println("Unesite postanski broj grada: ");
		Integer postBr = Integer.parseInt(MainUIHandler.sc.nextLine());
		System.out.println(Kud.getFormattedHeader());
		System.out.println("--------------------------------------------------------------------------------------------------------");

		try {
			for (Kud kud : kudService.getAllByPostBrGrada(postBr)) {
				System.out.println(kud);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
}
