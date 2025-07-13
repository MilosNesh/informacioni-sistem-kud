package com.kud.ui_handler;

import java.sql.SQLException;

import com.kud.model.Clan;
import com.kud.service.ClanService;

public class ClanUIHandler {
	private static final ClanService clanService = new ClanService();
	
	public void handleClanMenu() {
		String answer;
		do {
			System.out.println("--------------------------------------------------------------------------------------------------------");
			System.out.println("\nOdaberite opciju za rad nad clanovima:");
			System.out.println("1 - Prikaz svih");
			System.out.println("2 - Prikaz po identifikatoru");
			System.out.println("3 - Prikaz po kudu");
			System.out.println("4 - Unos novog clana");
			System.out.println("5 - Izmena po identifikatoru");
			System.out.println("6 - Brisanje po identifikatoru");
			System.out.println("X - Izlazak iz rukovanja kudovima");

			answer = MainUIHandler.sc.nextLine();

			switch (answer) {
			case "1":
				showAll();
				break;
			case "2":
//				showById();
				break;
			case "3":
//				showAllByPostBrGrada();
				break;
			case "4":
//				insert();
				break;
			case "5":
//				update();
				break;
			case "6":
//				delete();
				break;
			}

		} while (!answer.equalsIgnoreCase("X"));
	}
	
	private void showAll() {
		System.out.println(Clan.getFormattedHeader());
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");

		try {
			for (Clan clan : clanService.getAll()) {
				System.out.println(clan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
}
