package com.kud.ui_handler;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import com.kud.enums.TipZaposlenog;
import com.kud.model.Kud;
import com.kud.model.Zaposleni;
import com.kud.service.ZaposleniService;

public class ZaposleniUIHandler {
	private static final ZaposleniService zaposleniService = new ZaposleniService();

	public void handleZaposleniMenu() {
		String answer;
		do {
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\nOdaberite opciju za rad nad zaposlenima:");
			System.out.println("1 - Prikaz svih");
			System.out.println("2 - Prikaz po identifikatoru");
			System.out.println("3 - Prikaz po identifikaroru kuda");
			System.out.println("4 - Unos novog zaposlenog");
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
				showAllByKudId();
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
		System.out.println(Zaposleni.getFormattedHeader());
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
		try {			
			for(Zaposleni zaposleni: zaposleniService.getAll()) {
				System.out.println(zaposleni);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
        
		System.out.println(Zaposleni.getFormattedHeader());
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");

		try {
			Zaposleni zaposleni = zaposleniService.getById(id);
			System.out.println(zaposleni);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	private void insert() {
		System.out.println("Unesite ime zaposlenog: ");
		String ime = MainUIHandler.sc.nextLine();
		
		System.out.println("Unesite prezime zaposlenog: ");
		String prezime = MainUIHandler.sc.nextLine();
		
		System.out.println("Unesite jmbg zaposlenog: ");
		String jmbg = MainUIHandler.sc.nextLine();
		
		System.out.println("Unesite tip zaposlenog (Predsjednik, Sekretar, Koreograf): ");
		TipZaposlenog tip = TipZaposlenog.valueOf(MainUIHandler.sc.nextLine());
		
		System.out.println("Unesite broj telefona zaposlenog: ");
		String brojTelefona = MainUIHandler.sc.nextLine();
		
		System.out.println("Unesite ID kuda u kome zapoleni treba da radi: ");
		Integer kud = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		Zaposleni zaposleni = new Zaposleni(0, ime, prezime, jmbg, tip, Date.valueOf(LocalDate.now()), brojTelefona, kud);
		try {
			boolean isSaved = zaposleniService.save(zaposleni);
			if (isSaved) {
				System.out.println("Zaposleni uspjesno unesen u bazu!");
			}
		}  catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske. Zaposleni nije unesen.");
		}
	}
	
	private void update() {
		System.out.println("Unesite ID zaposlenog koga zelite da izmjenite: ");
		Integer id = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		Zaposleni stariZaposleni = null;
		try {
			stariZaposleni = zaposleniService.getById(id);
		} catch (SQLException e) {
			System.out.println("Nema zaposlenog sa unesenim ID.");
			return;
		}
		if(stariZaposleni == null) {
			System.out.println("Nema zaposlenog sa unesenim ID.");
			return;
		}
		System.out.println("Unesite ime zaposlenog: ");
		String ime = MainUIHandler.sc.nextLine();
		
		System.out.println("Unesite prezime zaposlenog: ");
		String prezime = MainUIHandler.sc.nextLine();
				
		System.out.println("Unesite tip zaposlenog (Predsjednik, Sekretar, Koreograf): ");
		TipZaposlenog tip = TipZaposlenog.valueOf(MainUIHandler.sc.nextLine());
		
		System.out.println("Unesite broj telefona zaposlenog: ");
		String brojTelefona = MainUIHandler.sc.nextLine();
	
		System.out.println("Unesite ID kuda u kome zapoleni treba da radi: ");
		Integer kud = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		Zaposleni zaposleni = new Zaposleni(stariZaposleni.getId(), ime, prezime, stariZaposleni.getJmbg(), tip, stariZaposleni.getDatumZaposlenja(), brojTelefona, kud);
		try {
			boolean isSaved = zaposleniService.save(zaposleni);
			if (isSaved) {
				System.out.println("Zaposleni uspjeno izmjenjen!");
			}
		}  catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Doslo je do greske. Zaposleni nije izmjenjen.");
		}
	}
	
	private void delete() {
		System.out.println("Unesite ID zaposlenog koga zelite da obrisete:  ");
		Integer id = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		try {
			boolean deleted = zaposleniService.deleteById(id);
			if(deleted) {
				System.out.println("Zaposleni uspjesno obrisan.");;
			}else {
				System.out.println("Doslo je do greske prilikom brisanja. Zaposleni sa zadatim ID ne postoji.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void showAllByKudId() {
	System.out.println("Unesite ID kuda za koji zelite da dobijete zaposlene: ");
		Integer id = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		System.out.println(Zaposleni.getFormattedHeader());
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
		try {			
			for(Zaposleni zaposleni: zaposleniService.getAllByKudId(id)) {
				System.out.println(zaposleni);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
