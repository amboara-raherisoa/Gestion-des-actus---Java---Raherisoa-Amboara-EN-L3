package bdd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.TreeMap;

public abstract class DAO<T> {
	
	protected Connection connection = null;
	
	public DAO(Connection connection) {
		this.connection = connection;
	}
	
	public abstract TreeMap<Integer, T> findAll();
	
	public abstract int create(T obj);
	
	public abstract int update(T obj);
	
	public abstract int delete(T obj);
	
	public abstract int deleteAll();
	
	protected ResultSet execRequeteSelect(String rqt) throws SQLException {
		return connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(rqt);
	}

	private PreparedStatement preparerRequete(String rqt) throws SQLException {
		return connection.prepareStatement(rqt,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
	}

	protected ResultSet execRequeteSelectSelonId(String rqt, int id) throws SQLException {
		PreparedStatement prepStmt = preparerRequete(rqt);
		
		prepStmt.setInt(1, id);
		
		return prepStmt.executeQuery();
	}
	
	protected ResultSet execRequeteSelectToutesLesLignes(String nomTable) throws SQLException {
		String requete = "SELECT * FROM " + nomTable + " "
				+ "ORDER BY id";
		
		return execRequeteSelect(requete);
	}
	
	protected int execRequeteModification(String rqt, Object[] params) throws SQLException {
		
		PreparedStatement prepStmt = preparerRequete(rqt);
		
		if (params != null) {
			int nbParams = params.length;
			
			for (int i = 1; i <= nbParams; i++) {
				if (params[i - 1].equals("NULL")) {
					prepStmt.setNull(i, Types.INTEGER);
				} else {				
					prepStmt.setObject(i, params[i - 1]);
				}
			}
		}
		
		prepStmt.executeUpdate();
		
		return 0;
	}
	
	protected int execRequeteSuppression(String nomTable, int id) throws SQLException {
		String requete = "DELETE FROM " + nomTable + " "
				+ "WHERE id = ?";
		PreparedStatement prepStmt = preparerRequete(requete);
		
		prepStmt.setInt(1, id);
		prepStmt.executeUpdate();
		
		return 0;
	}
	
	public int obtenirDernierIdGenere() {
		int dernierId = 0;
		String requete = "SELECT LAST_INSERT_ID() FROM DUAL";
		
		try {
			ResultSet rst = execRequeteSelect(requete);
			
			if (rst.first()) {
				dernierId = rst.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dernierId;
	}
}
