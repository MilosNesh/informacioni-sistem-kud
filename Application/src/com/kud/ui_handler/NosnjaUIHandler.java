package com.kud.ui_handler;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.kud.dto.PozajmicaDTO;
import com.kud.enums.PolClana;
import com.kud.enums.TipNosnje;
import com.kud.model.*;
import com.kud.service.*;

public class NosnjaUIHandler {
	private static final ClanService clanService = new ClanService();
	private static final SekcijaService sekcijaService = new SekcijaService();
	private static final ZaposleniService zaposleniService = new ZaposleniService();
	private static final KoreografijaService koreografijaService = new KoreografijaService();
	private static final NosnjaService nosnjaService = new NosnjaService();
	
	public Zaposleni zaposleni;
	
	public void handleNosnjaMenu() {
		login();
		String answer;
		do {
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\nOdaberite opciju za rad nad nosnjama:");
			System.out.println("1 - Prikaz svih");
			System.out.println("2 - Prikaz po koreografiji");
			System.out.println("3 - Prikaz slobodnih nosnji");
			System.out.println("4 - Prikaz zaduzenja(pozajmica)");
			System.out.println("5 - Zadizi nosnju");
			System.out.println("6 - Razduzi nosnju");
			System.out.println("X - Izlazak iz rukovanja kudovima");

			answer = MainUIHandler.sc.nextLine();

			switch (answer) {
			case "1":
				showAll();
				break;
			case "2":
				showByKoreografija();
				break;
			case "3":
				showFree();
				break;
			case "4":
				showBorrowed();
				break;
			case "5":
				take();
				break;
			case "6":
				returnCostume();
				break;
			}

		} while (!answer.equalsIgnoreCase("X") && zaposleni != null);
	}
	
	private void login() {
		do {			
			System.out.println("Unesite ime zaposleng: ");
			String ime = MainUIHandler.sc.nextLine();
			
			System.out.println("Unesite prezime zaposlenog: ");
			String prezime = MainUIHandler.sc.nextLine();
			
			try {
				zaposleni = zaposleniService.getByImeAndPrezime(ime, prezime);
				if (zaposleni == null) {
					System.out.println("Losi kredencijali pokusaj ponovo.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} while (zaposleni == null);
	}
	
	private void showAll() {
		try {
			System.out.println(Nosnja.getFormattedHeader());
			System.out.println("----------------------------------------------------------------------------------------");

			for(Nosnja n: nosnjaService.getAllByKudId(zaposleni.getKud())) {
				System.out.println(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void showByKoreografija() {
		System.out.println(Koreografija.getFormattedHeader());
		System.out.println("----------------------------------------------------------------------------------------");
		try {
			for(Koreografija k: koreografijaService.getAllByKudId(zaposleni.getKud())) {
				System.out.println(k);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("Izaberite ID koreografije za koju zelite da vidite nosnje: ");
		Integer id = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		try {
			System.out.println(Nosnja.getFormattedHeader());
			System.out.println("----------------------------------------------------------------------------------------");

			for(Nosnja n: nosnjaService.getAllByKudIdKoreografijaId(zaposleni.getKud(), id)) {
				System.out.println(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void showFree() {
		System.out.println(Koreografija.getFormattedHeader());
		System.out.println("----------------------------------------------------------------------------------------");
		try {
			for(Koreografija k: koreografijaService.getAllByKudId(zaposleni.getKud())) {
				System.out.println(k);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("Izaberite ID koreografije za koju zelite da vidite SLOBODNE nosnje: ");
		Integer id = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		try {
			System.out.println(Nosnja.getFormattedHeader());
			System.out.println("----------------------------------------------------------------------------------------");

			for(Nosnja n: nosnjaService.getFreeByKudIdKoreografijaId(zaposleni.getKud(), id)) {
				System.out.println(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void showBorrowed() {
		try {
			System.out.println(PozajmicaDTO.getFormattedHeader());
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");

			for(PozajmicaDTO p: nosnjaService.getBorrowed(zaposleni.getKud())) {
				System.out.println(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void take() {
		
		System.out.println(Clan.getFormattedHeader());
		ArrayList<Clan> clanovi = new ArrayList<Clan>();
		try {
			clanovi = clanService.getAllByKud(zaposleni.getKud());
			for(Clan c: clanovi) {
				System.out.println(c);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("Unesite ID clana koji zaduzuje nosnju:");
		Integer clanId = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		Clan clan = null;
		
		for(Clan c : clanovi) {
			if(c.getId() == clanId)
				clan = c;
		}
		
		System.out.println();
		
		System.out.println(Koreografija.getFormattedHeader());
		System.out.println("----------------------------------------------------------------------------------------");
		try {
			for(Koreografija k: koreografijaService.getAllByKudId(zaposleni.getKud())) {
				System.out.println(k);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("Izaberite ID koreografije za koju zelite da zaduzite nosnju: ");
		Integer koreografijaId = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		ArrayList<Nosnja> nosnje = new ArrayList<Nosnja>();
		
		try {
			System.out.println(Nosnja.getFormattedHeader());
			System.out.println("----------------------------------------------------------------------------------------");

			nosnje = nosnjaService.getFreeByKudIdKoreografijaId(zaposleni.getKud(), koreografijaId);
			for(Nosnja n: nosnje) {
				System.out.println(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		System.out.println();
		System.out.println("Unesite ID nosnje koju zelite da zaduzite:");
		Integer nosnjaId = Integer.parseInt(MainUIHandler.sc.nextLine());
		Nosnja nosnja = null;
		for(Nosnja n: nosnje) {
			if(n.getId() == nosnjaId)
				nosnja = n;
		}
		
		if(nosnja.getTip() == TipNosnje.Muska && clan.getPol() == PolClana.Zenski) {
			System.out.println("Ne moze zenska osoba zaduziti musku nosnju.");
			return;
		}
		
		if(nosnja.getTip() == TipNosnje.Zenska && clan.getPol() == PolClana.Muski) {
			System.out.println("Ne moze muska osoba zaduziti zensku nosnju.");
			return;
		}
		
		try {
			PozajmicaDTO pozajmica = new PozajmicaDTO(nosnjaId, nosnja.getNaziv(), Date.valueOf(LocalDate.now()), clan.getId(), clan.getJmbg(), clan.getIme(), clan.getPrezime());
			boolean inserted = nosnjaService.take(pozajmica);
			if(inserted)
				System.out.println("Nosnja upsjesno zaduzenja.");
			else
				System.out.println("Doslo je do greske priliko zaduzenja nosnje.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void returnCostume() {
		
		ArrayList<PozajmicaDTO> pozajmice = new ArrayList<PozajmicaDTO>();
		try {
			System.out.println(PozajmicaDTO.getFormattedHeader());
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");

			pozajmice = nosnjaService.getBorrowed(zaposleni.getKud());
			
			for(PozajmicaDTO p: pozajmice) {
				System.out.println(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("Unesite ID clana koji treba da razduzi nosnju: ");
		Integer clanId = Integer.valueOf(MainUIHandler.sc.nextLine());
		
		System.out.println("Unesite ID nosnje koju treba da razduziti: ");
		Integer nosnjaId = Integer.valueOf(MainUIHandler.sc.nextLine());
		
		PozajmicaDTO pozajmica = null;
		for(PozajmicaDTO p : pozajmice) {
			if (p.getIdClana() == clanId && p.getIdNosnje() == nosnjaId)
				pozajmica = p;
		}
		
		try {
			boolean returned = nosnjaService.returnCostume(pozajmica);
			if (returned)
				System.out.println("Nosnja uspjesno razduzena.");
			else
				System.out.println("Doslo je do greske prilikom razduzenja nosnje.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
