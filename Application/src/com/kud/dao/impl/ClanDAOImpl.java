package com.kud.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kud.connection.ConnectionUtil_HikariCP;
import com.kud.dao.ClanDAO;
import com.kud.dto.ClanSekcijaDTO;
import com.kud.enums.PolClana;
import com.kud.enums.TipSekcije;
import com.kud.model.Clan;
import com.kud.model.Sekcija;

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

	@Override
	public Iterable<Clan> findByKud(Integer kud) throws SQLException {
		String query = "select idc, imec, prezc, jmbgc, brtelc, datrodjc, datupc, polc from clan c, je j, sekcija s where c.idc = j.clan_idc and s.ids = j.sekcija_ids and s.kud_idkud = ?";
		List<Clan> clanList = new ArrayList<Clan>();
 		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
 				PreparedStatement preparedStatement = connection.prepareStatement(query)){
 			
 			preparedStatement.setInt(1, kud);
 			
 			try (ResultSet resultSet = preparedStatement.executeQuery()){ 				
 				while(resultSet.next()) {
 					Clan clan = new Clan(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getDate(7), PolClana.valueOf(resultSet.getString(8)));
 					clanList.add(clan);
 				}
 			}
 			}
		return clanList;
	}

	@Override
	public Iterable<Clan> findByKudAndSekcija(Integer kud, String tip) throws SQLException {
		String query = "select idc, imec, prezc, jmbgc, brtelc, datrodjc, datupc, polc from clan c, je j, sekcija s where c.idc = j.clan_idc and s.ids = j.sekcija_ids and s.kud_idkud = ? and s.tips = ?";
		List<Clan> clanList = new ArrayList<Clan>();
 		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
 				PreparedStatement preparedStatement = connection.prepareStatement(query)){
 			
 			
 			preparedStatement.setInt(1, kud);
 			preparedStatement.setString(2, tip);
 			
 			try (ResultSet resultSet = preparedStatement.executeQuery()){ 				
 				while(resultSet.next()) {
 					Clan clan = new Clan(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getDate(7), PolClana.valueOf(resultSet.getString(8)));
 					clanList.add(clan);
 				}
 			}
 			}
		return clanList;
	}

	@Override
	public boolean register(Clan clan, Sekcija sekcija) throws SQLException {
		String query1 = "insert into clan (idc, imec, prezc, jmbgc, brtelc, datrodjc, datupc, polc) values (?, ?, ?, ?, ?, ?, ?, ?)";
		String query2 = "insert into je (clan_idc, clan_jmbgc, sekcija_ids, sekcija_kud_idkud) values (?, ?, ?, ?)";
		
		if (clan.getId() == 0) {
			try(Connection connection = ConnectionUtil_HikariCP.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement("select max(idc) from clan");
					ResultSet resultSet = preparedStatement.executeQuery()){
				if (resultSet.next()) {
					clan.setId(resultSet.getInt(1)+1);
				}
			}
		}
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()){
			
			connection.setAutoCommit(false);
			
		    try (PreparedStatement preparedStatement1 = connection.prepareStatement(query1)) {
		        preparedStatement1.setInt(1, clan.getId());
		        preparedStatement1.setString(2, clan.getIme());
		        preparedStatement1.setString(3, clan.getPrezime());
		        preparedStatement1.setString(4, clan.getJmbg());
		        preparedStatement1.setString(5, clan.getBrojTelefona());
		        preparedStatement1.setDate(6, clan.getDatumRodjenja());
		        preparedStatement1.setDate(7, clan.getDatumUpisa());
		        preparedStatement1.setString(8, clan.getPol().toString());
		        
		        preparedStatement1.executeUpdate();
		    } catch (SQLException e) {
				System.out.println(e.getMessage());
				connection.rollback();
				return false;
			}
		    
		    try (PreparedStatement preparedStatement2 = connection.prepareStatement(query2)) {
		        preparedStatement2.setInt(1, clan.getId());
		        preparedStatement2.setString(2, clan.getJmbg());
		        preparedStatement2.setInt(3, sekcija.getId());
		        preparedStatement2.setInt(4, sekcija.getKud());
		        
		        preparedStatement2.executeUpdate();
		    } catch (SQLException e) {
				System.out.println(e.getMessage());
				connection.rollback();
				return false;
			}

		    connection.commit();
		    return true;
		}
	
	}

	@Override
	public Clan findByIdAndKudId(Integer id, Integer kudId) throws SQLException {
		String query = "select idc, imec, prezc, jmbgc, brtelc, datrodjc, datupc, polc from clan c, je j, sekcija s where c.idc = j.clan_idc and s.ids = j.sekcija_ids and s.kud_idkud = ? and c.idc = ?";
		Clan clan = null;
 		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
 				PreparedStatement preparedStatement = connection.prepareStatement(query)){
 			
 			preparedStatement.setInt(1, kudId);
 			preparedStatement.setInt(2, id);
 			
 			try (ResultSet resultSet = preparedStatement.executeQuery()){

	 			if (resultSet.isBeforeFirst()) {
	 				resultSet.next();
	 				clan = new Clan(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getDate(7), PolClana.valueOf(resultSet.getString(8)));
	 	
	 			}
 			}
 			}
		return clan;
	}

	@Override
	public boolean changeSection(ClanSekcijaDTO clan, Integer idSekcijaNovi) throws SQLException {
		String queryDelete = "delete from je where clan_idc = ? and sekcija_ids = ?";
		String queryInsert = "insert into je (clan_idc, clan_jmbgc, sekcija_ids, sekcija_kud_idkud) values (?, ?, ?, ?)";
		
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()){
			connection.setAutoCommit(false);
			try (PreparedStatement preparedStatement = connection.prepareStatement(queryDelete)){
				preparedStatement.setInt(1, clan.getIdClana());
				preparedStatement.setInt(2, clan.getIdSekcije());
				
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				connection.rollback();
				return false;
			}
			try (PreparedStatement preparedStatement = connection.prepareStatement(queryInsert)){
				
				preparedStatement.setInt(1, clan.getIdClana());
				preparedStatement.setString(2, clan.getJmbgClana());
				preparedStatement.setInt(3, idSekcijaNovi);
				preparedStatement.setInt(4, clan.getIdKuda());
				
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				connection.rollback();
				return false;
			}
			connection.commit();
			return true;
		}
	}

	@Override
	public Iterable<ClanSekcijaDTO> findDTOsByIdAndKudId(Integer id, Integer kudId) throws SQLException {
		String query = "select idc, imec, prezc, jmbgc, ids, nazs, tips, s.kud_idkud from clan c, je j, sekcija s where c.idc = j.clan_idc and j.sekcija_ids = s.ids and idc = ? and s.kud_idkud = ?";
		ArrayList<ClanSekcijaDTO> list = new ArrayList<ClanSekcijaDTO>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)){
			
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, kudId);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()){
				while (resultSet.next()) {
					TipSekcije tips;
					if(resultSet.getString(7).equals("Skolica folklora"))
						tips = TipSekcije.Skolica_folkolora;
					else
						tips = TipSekcije.valueOf(resultSet.getString(7));
					ClanSekcijaDTO clan = new ClanSekcijaDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6), tips, resultSet.getInt(8));
					list.add(clan);
				}
			}
		}
		return list;
	}

}
