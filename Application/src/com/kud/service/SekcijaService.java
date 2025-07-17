package com.kud.service;

import com.kud.dao.impl.SekcijaDAOImpl;
import com.kud.enums.TipSekcije;
import com.kud.model.Sekcija;

import java.sql.SQLException;
import java.util.ArrayList;

import com.kud.dao.SekcijaDAO;

public class SekcijaService {
	private static final SekcijaDAO sekcijaDao = new SekcijaDAOImpl();
	
	public Sekcija getByTipAndKudId(TipSekcije tip, Integer kudId) throws SQLException {
		return sekcijaDao.findByTipAndKudId(tip, kudId);
	}
	
	public ArrayList<Sekcija> getAllByKudId(Integer kudId) throws SQLException {
		return (ArrayList<Sekcija>) sekcijaDao.findAllByKudId(kudId);
	}
 }
