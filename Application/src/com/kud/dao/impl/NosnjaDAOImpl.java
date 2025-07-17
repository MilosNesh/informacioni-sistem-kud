package com.kud.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kud.connection.ConnectionUtil_HikariCP;
import com.kud.dao.NosnjaDAO;
import com.kud.dto.PozajmicaDTO;
import com.kud.enums.TipNosnje;
import com.kud.model.Nosnja;

public class NosnjaDAOImpl implements NosnjaDAO{

	@Override
	public Iterable<Nosnja> findAllByKudIdAndKoreografijaId(Integer kudId, Integer koreografijaId) throws SQLException {
		String query = "select idn, nazn, tipn from nosnja n, posjeduje p, pripada pr where n.idn = pr.nosnja_idn and pr.koreografija_idk = p.koreografija_idk and p.kud_idkud = ? and p.koreografija_idk = ?";
		ArrayList<Nosnja> list = new ArrayList<Nosnja>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)){
			
			preparedStatement.setInt(1, kudId);
			preparedStatement.setInt(2, koreografijaId);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()){
				while (resultSet.next()) {
					Nosnja nosnja = new Nosnja(resultSet.getInt(1), resultSet.getString(2), TipNosnje.valueOf(resultSet.getString(3)));
					list.add(nosnja);
				}
			}
		}
		return list;
	}

	@Override
	public Iterable<Nosnja> findFreeByKudIdAndKoreografijaId(Integer kudId, Integer koreografijaId) throws SQLException {
		String query = "select idn, nazn, tipn from nosnja where idn in (select idn from nosnja n, posjeduje p, pripada pr where n.idn = pr.nosnja_idn and pr.koreografija_idk = p.koreografija_idk and p.kud_idkud = ? and p.koreografija_idk = ?) and idn not in (select nosnja_idn from pozajmica) order by idn";
		ArrayList<Nosnja> list = new ArrayList<Nosnja>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)){
			
			preparedStatement.setInt(1, kudId);
			preparedStatement.setInt(2, koreografijaId);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()){
				while (resultSet.next()) {
					Nosnja nosnja = new Nosnja(resultSet.getInt(1), resultSet.getString(2), TipNosnje.valueOf(resultSet.getString(3)));
					list.add(nosnja);
				}
			}
		}
		return list;
	}

	@Override
	public Iterable<PozajmicaDTO> findBorrowed(Integer kudId) throws SQLException {
		String query = "select idn, nazn, datp, idc, jmbgc, imec, prezc from clan c, nosnja n, pozajmica p, posjeduje po, pripada pr  where n.idn = p.nosnja_idn and c.idc = p.clan_idc and po.koreografija_idk = pr.koreografija_idk and pr.nosnja_idn = n.idn and po.kud_idkud=?";
		ArrayList<PozajmicaDTO> list = new ArrayList<PozajmicaDTO>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)){
			
			preparedStatement.setInt(1, kudId);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()){
				while (resultSet.next()) {
					PozajmicaDTO pozajmica = new PozajmicaDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
					list.add(pozajmica);
				}
			}
		}
		return list;
	}

	@Override
	public boolean take(PozajmicaDTO pozajmica) throws SQLException {
		String query = "insert into pozajmica(datp, nosnja_idn, clan_idc,clan_jmbgc) values (?, ?, ?, ?)";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setDate(1, pozajmica.getDatumZaduzenja());
			preparedStatement.setInt(2, pozajmica.getIdNosnje());
			preparedStatement.setInt(3, pozajmica.getIdClana());
			preparedStatement.setString(4, pozajmica.getJmbgClana());
			
			int inserted = preparedStatement.executeUpdate();
			return inserted == 1;
		}
	}

	@Override
	public boolean returnCostume(PozajmicaDTO pozajmica) throws SQLException {
		String query = "delete from pozajmica where datp = ? and nosnja_idn = ? and clan_idc = ? and clan_jmbgc = ?";
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setDate(1, pozajmica.getDatumZaduzenja());
			preparedStatement.setInt(2, pozajmica.getIdNosnje());
			preparedStatement.setInt(3, pozajmica.getIdClana());
			preparedStatement.setString(4, pozajmica.getJmbgClana());
			
			int deleted = preparedStatement.executeUpdate();
			return deleted == 1;
		}
	}

	@Override
	public Iterable<Nosnja> findAllByKudId(Integer kudId) throws SQLException {
		String query = "select idn, nazn, tipn from nosnja n, posjeduje p, pripada pr where n.idn = pr.nosnja_idn and pr.koreografija_idk = p.koreografija_idk and p.kud_idkud = ?";
		ArrayList<Nosnja> list = new ArrayList<Nosnja>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)){
			
			preparedStatement.setInt(1, kudId);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()){
				while (resultSet.next()) {
					Nosnja nosnja = new Nosnja(resultSet.getInt(1), resultSet.getString(2), TipNosnje.valueOf(resultSet.getString(3)));
					list.add(nosnja);
				}
			}
		}
		return list;
	}

}
