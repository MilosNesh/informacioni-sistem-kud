package com.kud.service;

import com.kud.dao.impl.ZaposleniDAOImpl;
import com.kud.model.Zaposleni;

import java.sql.SQLException;
import java.util.ArrayList;

import com.kud.dao.ZaposleniDAO;
public class ZaposleniService {
	private static final ZaposleniDAO zaposleniDao = new ZaposleniDAOImpl();
	
	public ArrayList<Zaposleni> getAll() throws SQLException {
		return (ArrayList<Zaposleni>) zaposleniDao.findAll();
	}
	
	public Zaposleni getById(Integer id) throws SQLException {
		return zaposleniDao.findById(id);
	}
	
	public boolean save(Zaposleni entity) throws SQLException {
		return zaposleniDao.save(entity);
	}
	
	public boolean deleteById(Integer id) throws SQLException {
		return zaposleniDao.deleteById(id);
	}
	
	public ArrayList<Zaposleni> getAllByKudId(Integer id) throws SQLException {
		return (ArrayList<Zaposleni>) zaposleniDao.findAllByKudId(id);
	}
}
