package com.kud.dao;

import java.sql.SQLException;

import com.kud.dto.PozajmicaDTO;
import com.kud.model.*;

public interface NosnjaDAO {
	public Iterable<Nosnja> findAllByKudIdAndKoreografijaId(Integer kudId, Integer koreografijaId) throws SQLException;
	public Iterable<Nosnja> findFreeByKudIdAndKoreografijaId(Integer kudId, Integer koreografijaId) throws SQLException;
	public Iterable<PozajmicaDTO> findBorrowed(Integer kudId) throws SQLException;
	public boolean take(PozajmicaDTO pozajmica) throws SQLException;
	public boolean returnCostume(PozajmicaDTO pozajmica) throws SQLException;
	public Iterable<Nosnja> findAllByKudId(Integer kudId) throws SQLException;
}
