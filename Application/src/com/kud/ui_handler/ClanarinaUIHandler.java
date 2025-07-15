package com.kud.ui_handler;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import com.kud.dto.ClanDTO;
import com.kud.dto.ClanarinaDTO;
import com.kud.model.Clan;
import com.kud.model.Clanarina;
import com.kud.model.Zaposleni;
import com.kud.service.ClanService;
import com.kud.service.ClanarinaService;
import com.kud.service.ZaposleniService;

public class ClanarinaUIHandler {
	private static final ClanarinaService clanarinaService = new ClanarinaService();
	private static final ZaposleniService zaposleniService = new ZaposleniService();
	private static final ClanService clanService = new ClanService();
	Zaposleni sekretar;
	
	public void handleClanarinaMenu() {
		login();
		String answer;
		do {
			System.out.println("--------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\nOdaberite opciju za rad nad clanarinama:");
			System.out.println("1 - Prikaz svih");
			System.out.println("2 - Prikaz po identifikatoru clana");
			System.out.println("3 - Prikaz po datumu");
			System.out.println("4 - Prikazi duznike za tekuci mjesec");
			System.out.println("5 - Placanje clanarine");
			System.out.println("X - Izlazak iz rukovanja kudovima");

			answer = MainUIHandler.sc.nextLine();

			switch (answer) {
			case "1":
				showAll();
				break;
			case "2":
				showByClanId();
				break;
			case "3":
				showAllByDatum();
				break;
			case "4":
				showDeptor();
				break;
			case "5":
				pay();
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
				sekretar = zaposleniService.getByImeAndPrezimeAndTip(ime, prezime, "Sekretar");
				if (sekretar == null) {
					System.out.println("Losi kredencijali pokusaj ponovo.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} while (sekretar == null);
	}
	
	private void pay() {
		Clan clan = null;
		
		do {			
			System.out.println("Unesite ID clana koji placa clanarinu: ");
			Integer id = Integer.parseInt(MainUIHandler.sc.nextLine());
			try {
				clan = clanService.getByIdAndKudId(id, sekretar.getKud());
				if (clan == null)
					System.out.println("Nema clana sa trazenim ID u Vasem kudu.\n");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} while (clan == null);
		
		System.out.println("Unesite iznos clanarinu u KM: ");
		Integer iznos = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		Clanarina clanarina = new Clanarina(0, iznos, Date.valueOf(LocalDate.now()), clan.getId(), clan.getJmbg(), sekretar.getId(), sekretar.getJmbg());
		
		try {
			boolean payed = clanarinaService.save(clanarina);
			if (payed)
				System.out.println("Clanarina uspjesno placena.");
			else
				System.out.println("Doslo je do greske prilikom placanja clanarine.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private void showAll(){
		System.out.println(ClanarinaDTO.getFormattedHeader());
		System.out.println("------------------------------------------------------------------------------------------------------------------------");

		try {
			for(ClanarinaDTO clanarina : clanarinaService.getAllDto()) {
				System.out.println(clanarina);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void showByClanId() {
		System.out.println("Unesi ID clana za pretragu clanarina: ");
		Integer id = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		System.out.println(ClanarinaDTO.getFormattedHeader());
		System.out.println("------------------------------------------------------------------------------------------------------------------------");

		try {
			for(ClanarinaDTO clanarina : clanarinaService.getAllDtoByClanId(id)) {
				System.out.println(clanarina);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void showAllByDatum() {
		System.out.println("Unesi mjesec za pretragu clanarina (1, 2, 3...): ");
		String mjesec = MainUIHandler.sc.nextLine();
		
		System.out.println("Unesi godinu za pretragu clanarina: ");
		String godina = MainUIHandler.sc.nextLine();
		
		System.out.println(ClanarinaDTO.getFormattedHeader());
		System.out.println("------------------------------------------------------------------------------------------------------------------------");

		try {
			for(ClanarinaDTO clanarina : clanarinaService.getAllDtoByDatum(godina+"-"+mjesec)) {
				System.out.println(clanarina);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void showDeptor() {
		System.out.println(ClanDTO.getFormattedHeader());
		System.out.println("------------------------------------------------------------------------------------------------------------------------");

		try {
			for(ClanDTO clan : clanarinaService.getDebtors(LocalDate.now().getYear()+"-"+LocalDate.now().getMonthValue())) {
				System.out.println(clan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
