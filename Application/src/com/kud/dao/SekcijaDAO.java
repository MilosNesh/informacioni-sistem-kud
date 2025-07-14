package com.kud.dao;

import java.sql.SQLException;

import com.kud.enums.TipSekcije;
import com.kud.model.Sekcija;

public interface SekcijaDAO extends CRUDDAO<Sekcija, Integer>{
	public Sekcija findByTipAndKudId(TipSekcije tip, Integer kudId) throws SQLException;
}
