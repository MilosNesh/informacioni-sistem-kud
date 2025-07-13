package com.kud.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kud.connection.ConnectionUtil_HikariCP;
import com.kud.dao.KudDAO;
import com.kud.model.Kud;


public class KudDAOImpl implements KudDAO {

	@Override
	public int count() throws SQLException {
		String query = "select count(*) from kud";
		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()){
			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				return -1;
			}
		}
	}

	@Override
	public boolean delete(Kud entity) throws SQLException {
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
		String query = "delete from kud where idkud=?";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, id);
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected == 1;
		}
	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		String query = "select * from kud where idkud=?";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}
	}

	@Override
	public Iterable<Kud> findAll() throws SQLException {
		String query = "select idkud, nazkud, adrkud, grad_postbrg from kud order by idkud";
		List<Kud> kudList = new ArrayList<Kud>();
 		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
 				PreparedStatement preparedStatement = connection.prepareStatement(query);
 				ResultSet resultSet = preparedStatement.executeQuery()){
 			
 			while(resultSet.next()) {
 				Kud kud = new Kud(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
 				kudList.add(kud);
 			}
 		}
		
		return kudList;
	}

	@Override
	public Iterable<Kud> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kud findById(Integer id) throws SQLException {
		String query = "select idkud, nazkud, adrkud, grad_postbrg from kud where idkud = ?";
		Kud kud = null;
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.isBeforeFirst()) {
					resultSet.next();
					kud = new Kud(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
				}
			}
		}
		return kud;
	}

	@Override
	public boolean save(Kud entity) throws SQLException {
		String insertQuery = "insert into kud (nazkud, adrkud, grad_postbrg, idkud) values (?, ?, ?, ?)";
		String updateQuery = "update kud set  nazkud=?, adrkud=?, grad_postbrg=? where idkud = ?";
		String query;
		
		if (entity.getId() == 0) {
			query = insertQuery;
			//Dobavljanje najveceg idkud
			try(Connection connection = ConnectionUtil_HikariCP.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement("select max(idkud) from kud");
					ResultSet resultSet = preparedStatement.executeQuery()){
				if (resultSet.next()) {
					entity.setId(resultSet.getInt(1)+1);
				}
				}
		} else {
			query = updateQuery;
		}
		
		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setString(1, entity.getNaziv());
			preparedStatement.setString(2, entity.getEmail());
			preparedStatement.setInt(3, entity.getGrad());
			preparedStatement.setInt(4, entity.getId());

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected == 1;
		}
	}

	@Override
	public int saveAll(Iterable<Kud> entities) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<Kud> findAllByPostBrGrada(Integer id) throws SQLException {
		String query = "select idkud, nazkud, adrkud, grad_postbrg from kud where grad_postbrg = ? order by idkud";
		List<Kud> kudList = new ArrayList<Kud>();
 		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
 				PreparedStatement preparedStatement = connection.prepareStatement(query);){
 			
 			preparedStatement.setInt(1, id);
 			
 			try (ResultSet resultSet = preparedStatement.executeQuery()){ 				
 				while(resultSet.next()) {
 					Kud kud = new Kud(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
 					kudList.add(kud);
 				}
 			}
 		}
		
		return kudList;
	}

}
