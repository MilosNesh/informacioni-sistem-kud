package com.kud.dao;
import java.sql.SQLException;

import com.kud.model.Kud;

public interface KudDAO extends CRUDDAO<Kud, Integer>{
	public Iterable<Kud> findAllByPostBrGrada(Integer id)  throws SQLException;
}
