package com.kud.dao;

import java.sql.SQLException;

import com.kud.model.*;

public interface KoreografijaDAO {

	public Iterable<Koreografija> findAllByKudId(Integer kudId) throws SQLException;
}
