package com.kud.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kud.connection.ConnectionUtil_HikariCP;
import com.kud.dao.SekcijaDAO;
import com.kud.enums.TipSekcije;
import com.kud.model.Sekcija;

public class SekcijaDAOImpl implements SekcijaDAO{

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Sekcija entity) throws SQLException {
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
	public Iterable<Sekcija> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Sekcija> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sekcija findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Sekcija entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveAll(Iterable<Sekcija> entities) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Sekcija findByTipAndKudId(TipSekcije tip, Integer kudId) throws SQLException {
		String query = "select ids, nazs, tips, kud_idkud from sekcija where tips = ? and kud_idkud = ?";
		Sekcija sekcija = null;
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)){
			
			String tipS;
			if(tip.equals(TipSekcije.Skolica_folkolora))
				tipS = "Skolica folklora";
			else
				tipS = tip.toString();
			
			preparedStatement.setString(1, tipS);
			preparedStatement.setInt(2, kudId);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()){
				if(resultSet.isBeforeFirst()) {
					resultSet.next();
					
					TipSekcije tips;
					if(resultSet.getString(3).equals("Skolica folklora"))
						tips = TipSekcije.Skolica_folkolora;
					else
						tips = TipSekcije.valueOf(resultSet.getString(3));
						
					sekcija = new Sekcija(resultSet.getInt(1), resultSet.getString(2), tips, resultSet.getInt(4));
				}
			}
			
		}
		return sekcija;
	}

}
