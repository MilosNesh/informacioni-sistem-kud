package com.kud.dao;

import java.sql.SQLException;

import com.kud.model.Zaposleni;

public interface ZaposleniDAO extends CRUDDAO<Zaposleni, Integer> {
	public Iterable<Zaposleni> findAllByKudId(Integer id)  throws SQLException;
}
