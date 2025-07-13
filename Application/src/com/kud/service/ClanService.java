package com.kud.service;

import com.kud.dao.impl.ClanDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.kud.dao.ClanDAO;
import com.kud.model.Clan;

public class ClanService {
	private static final ClanDAO clanDao = new ClanDAOImpl();
	
	public ArrayList<Clan> getAll() throws SQLException{
		return (ArrayList<Clan>) clanDao.findAll();
	}
}
