package com.kud.service;

import com.kud.dao.impl.NosnjaDAOImpl;
import com.kud.dto.PozajmicaDTO;
import com.kud.model.Nosnja;

import java.sql.SQLException;
import java.util.ArrayList;

import com.kud.dao.*;
public class NosnjaService {
	private static final NosnjaDAO nosnjaDao = new NosnjaDAOImpl();
	
	public ArrayList<Nosnja> getAllByKudIdKoreografijaId(Integer kudId, Integer koreografijaId) throws SQLException {
		return (ArrayList<Nosnja>) nosnjaDao.findAllByKudIdAndKoreografijaId(kudId, koreografijaId);
	}
	
	public ArrayList<Nosnja> getFreeByKudIdKoreografijaId(Integer kudId, Integer koreografijaId) throws SQLException {
		return (ArrayList<Nosnja>) nosnjaDao.findFreeByKudIdAndKoreografijaId(kudId, koreografijaId);
	}
	
	public ArrayList<PozajmicaDTO> getBorrowed(Integer kudId) throws SQLException {
		return (ArrayList<PozajmicaDTO>) nosnjaDao.findBorrowed(kudId);
	}
	
	public boolean take(PozajmicaDTO pozajmica) throws SQLException {
		return nosnjaDao.take(pozajmica);
	}
	
	public boolean returnCostume(PozajmicaDTO pozajmica) throws SQLException {
		return nosnjaDao.returnCostume(pozajmica);
	}
	
	public ArrayList<Nosnja> getAllByKudId(Integer kudId) throws SQLException {
		return (ArrayList<Nosnja>) nosnjaDao.findAllByKudId(kudId);
	}
}
