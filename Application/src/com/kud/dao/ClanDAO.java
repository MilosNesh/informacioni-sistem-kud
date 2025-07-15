package com.kud.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.kud.enums.TipSekcije;
import com.kud.model.Clan;
import com.kud.model.Sekcija;

public interface ClanDAO extends CRUDDAO<Clan, Integer>{
	public Iterable<Clan> findByKud(Integer kud) throws SQLException;
	public Iterable<Clan> findByKudAndSekcija(Integer kud, String tip) throws SQLException;
	public boolean register(Clan clan, Sekcija sekcija) throws SQLException;
	public Clan findByIdAndKudId(Integer id, Integer kudId) throws SQLException;
}
