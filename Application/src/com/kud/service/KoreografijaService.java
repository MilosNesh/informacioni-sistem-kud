package com.kud.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.kud.dao.KoreografijaDAO;
import com.kud.dao.impl.KoreografijaDAOImpl;
import com.kud.model.*;

public class KoreografijaService {
	public static final KoreografijaDAO koreografijaDao = new KoreografijaDAOImpl();
	
	public ArrayList<Koreografija> getAllByKudId(Integer kudId) throws SQLException {
		return (ArrayList<Koreografija>) koreografijaDao.findAllByKudId(kudId);
	}
}
