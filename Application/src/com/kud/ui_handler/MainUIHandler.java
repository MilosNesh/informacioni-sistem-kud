package com.kud.ui_handler;

import java.util.Scanner;

public class MainUIHandler {
	public static Scanner sc = new Scanner(System.in);
	private static final KudUIHandler kudUIHandler = new KudUIHandler();
	private static final ZaposleniUIHandler zaposleniUIHandler = new ZaposleniUIHandler();
	private static final ClanUIHandler clanUIHandler = new ClanUIHandler();
	public void handleMainMenu() {

		String answer;
		do {
			System.out.println("--------------------------------------------------------------------------------------------------");			System.out.println("\nOdaberite opciju:");
			System.out.println("1 - Rukovanje kudovima");
			System.out.println("2 - Rukovanje zaposlenima");
			System.out.println("3 - Rukovanje clanovima");
			System.out.println("X - Izlazak iz programa");

			answer = sc.nextLine();

			switch (answer) {
			case "1":
				kudUIHandler.handleKudMenu();
				break;
			case "2":
				zaposleniUIHandler.handleZaposleniMenu();
				break;
			case "3":
				clanUIHandler.handleClanMenu();
				break;
			}

		} while (!answer.equalsIgnoreCase("X"));

		sc.close();
	}
}
