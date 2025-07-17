package com.kud.dao.impl;

import java.sql.*;
import java.util.ArrayList;

import com.kud.connection.ConnectionUtil_HikariCP;
import com.kud.dao.*;
import com.kud.model.Koreografija;
public class KoreografijaDAOImpl implements KoreografijaDAO{

	@Override
	public Iterable<Koreografija> findAllByKudId(Integer kudId) throws SQLException {
		String query = "select idk, nazk, krek, trk from koreografija k, posjeduje p where k.idk = p.koreografija_idk and p.kud_idkud = ?";
		ArrayList<Koreografija> list = new ArrayList<Koreografija>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)){
			
			preparedStatement.setInt(1, kudId);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Koreografija koreografija = new Koreografija(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getFloat(4));
					list.add(koreografija);
				}
			}
		}
		return list;
	}

}
