package com.kud.ui_handler;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import com.kud.enums.PolClana;
import com.kud.enums.TipSekcije;
import com.kud.model.Clan;
import com.kud.model.Sekcija;
import com.kud.model.Zaposleni;
import com.kud.service.*;

public class ClanUIHandler {
	private static final ClanService clanService = new ClanService();
	private static final SekcijaService sekcijaService = new SekcijaService();
	private static final ZaposleniService zaposleniService = new ZaposleniService();
	
	public Zaposleni sekretar;
	
	public void handleClanMenu() {
		login();
		String answer;
		do {
			System.out.println("--------------------------------------------------------------------------------------------------------");
			System.out.println("\nOdaberite opciju za rad nad clanovima:");
			System.out.println("1 - Prikaz svih");
			System.out.println("2 - Prikaz po identifikatoru");
			System.out.println("3 - Prikaz po sekciji");
			System.out.println("4 - Upis novog clana");
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
				showAllBySekcija();
				break;
			case "4":
				register();
				break;
			case "5":
//				update();
				break;
			case "6":
//				delete();
				break;
			}

		} while (!answer.equalsIgnoreCase("X") && sekretar != null);
	}
	
	private void login() {
		do {			
			System.out.println("Unesite ime sekretara: ");
			String ime = MainUIHandler.sc.nextLine();
			
			System.out.println("Unesite prezime sekretara: ");
			String prezime = MainUIHandler.sc.nextLine();
			
			try {
				sekretar = zaposleniService.getByImeAndPrezime(ime, prezime);
				if (sekretar == null) {
					System.out.println("Losi kredencijali pokusaj ponovo.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} while (sekretar == null);
	}
	
	private void showAll() {
		System.out.println(Clan.getFormattedHeader());
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");

		try {
			for (Clan clan : clanService.getAllByKud(sekretar.getKud())) {
				System.out.println(clan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	private void showById() {
		try {			
			Sekcija sekcija = sekcijaService.getByTipAndKudId(TipSekcije.Izvodjacka, 28);
			System.out.println(sekcija.getId() + " " + sekcija.getNaziv() + " " + sekcija.getTip() + " " + sekcija.getKud() );
		} catch (SQLException e ) {
			e.printStackTrace();
		}
	}
	
	private void showAllBySekcija() {
		System.out.println("Unesite tip sekcije('Izvodjacka', 'Pocetna', 'Pripremna', 'Rekreativna', 'Skolica folklora'): ");
		String tip = MainUIHandler.sc.nextLine();
		
		System.out.println(Clan.getFormattedHeader());
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");

		try {
			for (Clan clan : clanService.getAllByKudAndSekcija(sekretar.getKud(), tip)) {
				System.out.println(clan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	private void register() {
		System.out.println("Unesite ime clana: ");
		String ime = MainUIHandler.sc.nextLine();
		
		System.out.println("Unesite prezime clana: ");
		String prezime = MainUIHandler.sc.nextLine();
		
		System.out.println("Unesite jmbg clana: ");
		String jmbg = MainUIHandler.sc.nextLine();
		
		System.out.println("Unesite broj telefona clana: ");
		String brojTelefona = MainUIHandler.sc.nextLine();
		
		System.out.println("Unesite datum rodjenja clana(yyyy-mm-dd): ");
		String datumRodjenja = MainUIHandler.sc.nextLine();
		
		System.out.println("Unesite pol clana(Muski, Zenski): ");
		String pol = MainUIHandler.sc.nextLine();
		
		Clan clan = new Clan(0, ime, prezime, jmbg, brojTelefona, Date.valueOf(datumRodjenja), Date.valueOf(LocalDate.now()), PolClana.valueOf(pol));
	
		Sekcija sekcija = null;
		try {			
			do {		
				System.out.println("Unesite tip sekcije('Izvodjacka', 'Pocetna', 'Pripremna', 'Rekreativna', 'Skolica folklora'): ");
				String tip = MainUIHandler.sc.nextLine();
				
				TipSekcije tips;
				if(tip.equals("Skolica folklora"))
					tips = TipSekcije.Skolica_folkolora;
				else
					tips = TipSekcije.valueOf(tip);
				
				sekcija = sekcijaService.getByTipAndKudId(tips, sekretar.getKud());
				
				if (sekcija == null)
					System.out.println("Izabrana sekvcija ne postoji u Vasem kudu.");
				
			}while(sekcija == null);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			boolean inserted = clanService.register(clan, sekcija);
			if (inserted)
				System.out.println("Novi clan uspjesno upisan u " + sekcija.getTip() + " sekciju.");
			else
				System.out.println("Doslo je do greske prilikom upisa.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
