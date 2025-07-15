package com.kud.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kud.connection.ConnectionUtil_HikariCP;
import com.kud.dao.ClanarinaDAO;
import com.kud.dto.ClanDTO;
import com.kud.dto.ClanarinaDTO;
import com.kud.model.Clanarina;

public class ClanarinaDAOImpl implements ClanarinaDAO {

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Clanarina entity) throws SQLException {
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
	public Iterable<Clanarina> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Clanarina> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Clanarina findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Clanarina entity) throws SQLException {
		String query = "insert into clanarina (idcl, izncl, datplcl, clan_idc, clan_jmbgc, sekretar_idz, sekretar_jmbgz) values (?, ?, ?, ?, ?, ?, ?)";
		
		
		if (entity.getId() == 0) {
			try(Connection connection = ConnectionUtil_HikariCP.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement("select max(idcl) from clanarina");
					ResultSet resultSet = preparedStatement.executeQuery()){
				if (resultSet.next()) {
					entity.setId(resultSet.getInt(1)+1);
				}
			}
		}
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)){
			
			preparedStatement.setInt(1, entity.getId());
			preparedStatement.setInt(2, entity.getIznos());
			preparedStatement.setDate(3, entity.getDatumPlacanja());
			preparedStatement.setInt(4, entity.getIdClana());
			preparedStatement.setString(5, entity.getJmbgClana());
			preparedStatement.setInt(6, entity.getIdSekretara());
			preparedStatement.setString(7, entity.getJmbgSekretara());
			
			int lineInserted = preparedStatement.executeUpdate();
			return lineInserted == 1;
		}
	
	}

	@Override
	public int saveAll(Iterable<Clanarina> entities) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<ClanarinaDTO> findAllDto() throws SQLException {
		String query = "select idcl, izncl, datplcl, imec, prezc, s.nazs, idc from clanarina cl, clan c, sekcija s, je j where cl.clan_idc = c.idc and j.clan_idc = c.idc and j.sekcija_ids = s.ids";
		ArrayList<ClanarinaDTO> clanarinaList = new ArrayList<ClanarinaDTO>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()){
			
			while(resultSet.next()) {				
				ClanarinaDTO clanarinaDto = new ClanarinaDTO(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7));
				clanarinaList.add(clanarinaDto);
			}
		}
		
		return clanarinaList;
	}

	@Override
	public Iterable<ClanarinaDTO> findAllDtoByClanId(Integer id) throws SQLException {
		String query = "select idcl, izncl, datplcl, imec, prezc, s.nazs, idc from clanarina cl, clan c, sekcija s, je j where cl.clan_idc = c.idc and j.clan_idc = c.idc and j.sekcija_ids = s.ids and c.idc = ?";
		ArrayList<ClanarinaDTO> clanarinaList = new ArrayList<ClanarinaDTO>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)
				){
			
			preparedStatement.setInt(1, id);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()){				
				while(resultSet.next()) {				
					ClanarinaDTO clanarinaDto = new ClanarinaDTO(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7));
					clanarinaList.add(clanarinaDto);
				}
			}
		}
		
		return clanarinaList;
	}

	@Override
	public Iterable<ClanarinaDTO> findAllDtoByDatum(String mjesecGodina) throws SQLException {
		String query = "select idcl, izncl, datplcl, imec, prezc, s.nazs, idc from clanarina cl, clan c, sekcija s, je j where cl.clan_idc = c.idc and j.clan_idc = c.idc and j.sekcija_ids = s.ids and datplcl between ? and ?";
		ArrayList<ClanarinaDTO> clanarinaList = new ArrayList<ClanarinaDTO>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)
				){
			
			preparedStatement.setDate(1, Date.valueOf(mjesecGodina+"-01"));
			preparedStatement.setDate(2, Date.valueOf(mjesecGodina+"-30"));
			
			try (ResultSet resultSet = preparedStatement.executeQuery()){				
				while(resultSet.next()) {				
					ClanarinaDTO clanarinaDto = new ClanarinaDTO(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7));
					clanarinaList.add(clanarinaDto);
				}
			}
		}
		
		return clanarinaList;
	}

	@Override
	public Iterable<ClanDTO> findDebtors(String mjesecGodina) throws SQLException {
		String query = "select distinct idc, imec, prezc, nazs from clanarina cl, clan c, je j, sekcija s where cl.clan_idc = c.idc and j.clan_idc = c.idc and j.sekcija_ids = s.ids and c.idc not in (select distinct clan_idc from clanarina where datplcl between ? and ?) order by idc";
		ArrayList<ClanDTO> clanList = new ArrayList<ClanDTO>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)
				){
			
			preparedStatement.setDate(1, Date.valueOf(mjesecGodina+"-01"));
			preparedStatement.setDate(2, Date.valueOf(mjesecGodina+"-30"));
			
			try (ResultSet resultSet = preparedStatement.executeQuery()){				
				while(resultSet.next()) {				
					ClanDTO clanDto = new ClanDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
					clanList.add(clanDto);
				}
			}
		}
		
		return clanList;
	}

}
