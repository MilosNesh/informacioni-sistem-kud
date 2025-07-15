package com.kud.dao;

import java.sql.SQLException;

import com.kud.dto.*;
import com.kud.model.Clanarina;

public interface ClanarinaDAO extends CRUDDAO<Clanarina, Integer> {
	public Iterable<ClanarinaDTO> findAllDto() throws SQLException;
	public Iterable<ClanarinaDTO> findAllDtoByClanId(Integer id) throws SQLException;
	public Iterable<ClanarinaDTO> findAllDtoByDatum(String mjesecGodina) throws SQLException;
	public Iterable<ClanDTO> findDebtors(String mjesecGodina) throws SQLException;
}
