package com.kud.service;

import com.kud.dao.impl.ClanDAOImpl;
import com.kud.enums.TipSekcije;

import java.sql.SQLException;
import java.util.ArrayList;

import com.kud.dao.ClanDAO;
import com.kud.model.Clan;
import com.kud.model.Sekcija;

public class ClanService {
	private static final ClanDAO clanDao = new ClanDAOImpl();
	
	public ArrayList<Clan> getAll() throws SQLException {
		return (ArrayList<Clan>) clanDao.findAll();
	}
	
	public ArrayList<Clan> getAllByKud(Integer kud) throws SQLException {
		return (ArrayList<Clan>) clanDao.findByKud(kud);
	}
	
	public ArrayList<Clan> getAllByKudAndSekcija(Integer kud, String tip) throws SQLException {
		return (ArrayList<Clan>) clanDao.findByKudAndSekcija(kud, tip);
	}
	
	public boolean register(Clan clan, Sekcija sekcija) throws SQLException {
		return clanDao.register(clan, sekcija);
	}
	
	public Clan getByIdAndKudId(Integer id, Integer kudId) throws SQLException {
		return clanDao.findByIdAndKudId(id, kudId);
	}
}
