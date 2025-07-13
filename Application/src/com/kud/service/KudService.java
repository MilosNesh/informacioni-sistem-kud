package com.kud.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.kud.dao.KudDAO;
import com.kud.dao.impl.KudDAOImpl;
import com.kud.model.Kud;

public class KudService {
	private static final KudDAO kudDao = new KudDAOImpl();
	
	public ArrayList<Kud> getAll() throws SQLException {
		return (ArrayList<Kud>) kudDao.findAll();
	}
	
	public Kud getById(Integer id) throws SQLException {
		return kudDao.findById(id);
	}
	
	public boolean save(Kud kud) throws SQLException {
		return kudDao.save(kud);
	}
	
	public boolean existsById(int id) throws SQLException {
		return kudDao.existsById(id);
	}
	
	public boolean deleteById(int id) throws SQLException {
		return kudDao.deleteById(id);
	}
	
	public ArrayList<Kud> getAllByPostBrGrada(Integer postBr) throws SQLException {
		return (ArrayList<Kud>) kudDao.findAllByPostBrGrada(postBr);
	}
}
