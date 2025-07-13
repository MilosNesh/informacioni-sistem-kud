package com.kud.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kud.connection.ConnectionUtil_HikariCP;
import com.kud.dao.ClanDAO;
import com.kud.enums.PolClana;
import com.kud.model.Clan;

public class ClanDAOImpl implements ClanDAO {

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Clan entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int deleteAll() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Clan> findAll() throws SQLException {
		String query = "select idc, imec, prezc, jmbgc, brtelc, datrodjc, datupc, polc from clan order by idc";
		List<Clan> clanList = new ArrayList<Clan>();
 		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
 				PreparedStatement preparedStatement = connection.prepareStatement(query);
 				ResultSet resultSet = preparedStatement.executeQuery()){
 			
 			while(resultSet.next()) {
 				Clan clan = new Clan(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getDate(7), PolClana.valueOf(resultSet.getString(8)));
 				clanList.add(clan);
 			}
 			}
		return clanList;
	}

	@Override
	public Iterable<Clan> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Clan findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Clan entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveAll(Iterable<Clan> entities) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
