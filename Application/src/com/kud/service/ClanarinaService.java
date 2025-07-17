package com.kud.service;

import com.kud.dao.impl.ClanarinaDAOImpl;
import com.kud.model.Clanarina;
import com.kud.dto.*;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kud.dao.ClanarinaDAO;

public class ClanarinaService {
	private static final ClanarinaDAO clanarinaDao = new ClanarinaDAOImpl();
	
	public boolean save(Clanarina clanarina) throws SQLException {
		return clanarinaDao.save(clanarina);
	}
	
	public ArrayList<ClanarinaDTO> getAllDto() throws SQLException {
		return (ArrayList<ClanarinaDTO>) clanarinaDao.findAllDto();
	}
	
	public ArrayList<ClanarinaDTO> getAllDtoByClanId(Integer id) throws SQLException {
		return (ArrayList<ClanarinaDTO>) clanarinaDao.findAllDtoByClanId(id);
	}
	
	public ArrayList<ClanarinaDTO> getAllDtoByDatum(Integer mjesec, Integer godina, Integer kudId) throws SQLException {
		return (ArrayList<ClanarinaDTO>) clanarinaDao.findAllDtoByDatum(mjesec, godina, kudId);
	}
	
	public ArrayList<ClanDTO> getDebtors(Integer mjesec, Integer godina, Integer kudId) throws SQLException {
		return (ArrayList<ClanDTO>) clanarinaDao.findDebtors(mjesec, godina, kudId);
	}
}
