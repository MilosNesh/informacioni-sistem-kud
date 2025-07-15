package com.kud.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kud.connection.ConnectionUtil_HikariCP;
import com.kud.dao.ZaposleniDAO;
import com.kud.enums.TipZaposlenog;
import com.kud.model.Zaposleni;

public class ZaposleniDAOImpl implements ZaposleniDAO{

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Zaposleni entity) throws SQLException {
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
		String query = "delete from zaposleni where idz=?";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, id);
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected == 1;
		}
	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Zaposleni> findAll() throws SQLException {
		String query = "select idz, imez, prezz, jmbgz, tipz, datzapz, brtelz, kud_idkud from zaposleni order by idz";
		List<Zaposleni> zaposleniList = new ArrayList<Zaposleni>();
 		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
 				PreparedStatement preparedStatement = connection.prepareStatement(query);
 				ResultSet resultSet = preparedStatement.executeQuery()){
 			
 			while(resultSet.next()) {
 				Zaposleni zaposleni = new Zaposleni(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), TipZaposlenog.valueOf(resultSet.getString(5)), resultSet.getDate(6), resultSet.getString(7), resultSet.getInt(8));
 				zaposleniList.add(zaposleni);
 			}
 			}
		return zaposleniList;
	}

	@Override
	public Iterable<Zaposleni> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Zaposleni findById(Integer id) throws SQLException {
		String query = "select idz, imez, prezz, jmbgz, tipz, datzapz, brtelz, kud_idkud from zaposleni where idz = ?";
		Zaposleni zaposleni = null;
		
		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
 				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			
			preparedStatement.setInt(1, id);
			
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				if(resultSet.isBeforeFirst()) {
					resultSet.next();
					zaposleni = new Zaposleni(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), TipZaposlenog.valueOf(resultSet.getString(5)), resultSet.getDate(6), resultSet.getString(7), resultSet.getInt(8));
				}
			}
		}
		return zaposleni;
	}

	@Override
	public boolean save(Zaposleni entity) throws SQLException {
		String insertQuery = "insert into zaposleni (imez, prezz, jmbgz, tipz, datzapz, brtelz, kud_idkud, idz) values (?, ?, ?, ?, ?, ?, ?, ?)";
		String updateQuery = "update zaposleni set imez=?, prezz=?, jmbgz=?, tipz=?, datzapz=?, brtelz=?, kud_idkud=? where idz=?";
		String query;
		
		if (entity.getId() == 0) {
			query = insertQuery;
			//Dobavljanje najveceg idz
			try(Connection connection = ConnectionUtil_HikariCP.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement("select max(idz) from zaposleni");
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
			preparedStatement.setString(1, entity.getIme());
			preparedStatement.setString(2, entity.getPrezime());
			preparedStatement.setString(3, entity.getJmbg());
			preparedStatement.setString(4, entity.getTip().toString());
			preparedStatement.setDate(5, entity.getDatumZaposlenja());
			preparedStatement.setString(6, entity.getBrojTelefona());
			preparedStatement.setInt(7, entity.getKud());
			preparedStatement.setInt(8, entity.getId());

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected == 1;
		}
	}

	@Override
	public int saveAll(Iterable<Zaposleni> entities) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<Zaposleni> findAllByKudId(Integer id)  throws SQLException{
		String query = "select idz, imez, prezz, jmbgz, tipz, datzapz, brtelz, kud_idkud from zaposleni where kud_idkud = ? order by idz";
		List<Zaposleni> zaposleniList = new ArrayList<Zaposleni>();
 		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
 				PreparedStatement preparedStatement = connection.prepareStatement(query);){
 			
 			preparedStatement.setInt(1, id);
 			
 			try(ResultSet resultSet = preparedStatement.executeQuery()){ 				
 				while(resultSet.next()) {
 					Zaposleni zaposleni = new Zaposleni(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), TipZaposlenog.valueOf(resultSet.getString(5)), resultSet.getDate(6), resultSet.getString(7), resultSet.getInt(8));
 					zaposleniList.add(zaposleni);
 				}
 			}
 			}
		return zaposleniList;
	}

	@Override
	public Zaposleni findByImeAndPrezime(String ime, String prezime) throws SQLException {
		String query = "select idz, imez, prezz, jmbgz, tipz, datzapz, brtelz, kud_idkud from zaposleni where imez = ? and prezz = ?";
		Zaposleni zaposleni = null;
		
		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
 				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			
			preparedStatement.setString(1, ime);
			preparedStatement.setString(2, prezime);
			
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				if(resultSet.isBeforeFirst()) {
					resultSet.next();
					zaposleni = new Zaposleni(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), TipZaposlenog.valueOf(resultSet.getString(5)), resultSet.getDate(6), resultSet.getString(7), resultSet.getInt(8));
				}
			}
		}
		return zaposleni;
	}

	@Override
	public Zaposleni findByImeAndPrezimeAndTip(String ime, String prezime, String tip) throws SQLException {
		String query = "select idz, imez, prezz, jmbgz, tipz, datzapz, brtelz, kud_idkud from zaposleni where imez = ? and prezz = ? and tipz = ?";
		Zaposleni zaposleni = null;
		
		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
 				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			
			preparedStatement.setString(1, ime);
			preparedStatement.setString(2, prezime);
			preparedStatement.setString(3, tip);
			
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				if(resultSet.isBeforeFirst()) {
					resultSet.next();
					zaposleni = new Zaposleni(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), TipZaposlenog.valueOf(resultSet.getString(5)), resultSet.getDate(6), resultSet.getString(7), resultSet.getInt(8));
				}
			}
		}
		return zaposleni;
	}

}
