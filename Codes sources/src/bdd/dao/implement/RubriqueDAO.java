package bdd.dao.implement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import bdd.dao.DAO;
import metier.Rubrique;

public class RubriqueDAO extends DAO<Rubrique> {

	public RubriqueDAO(Connection connection) {
		super(connection);
	}

	@Override
	public TreeMap<Integer, Rubrique> findAll() {
		TreeMap<Integer, Rubrique> rubriques = new TreeMap<Integer, Rubrique>();
		
		try {
			ResultSet rst = execRequeteSelectToutesLesLignes("Rubrique");
			
			while (rst.next()) {
				int id = rst.getInt("id");
				String intitule = rst.getString("intitule");
				
				Rubrique rubrique = new Rubrique(id, intitule);
				
				rubriques.put(id, rubrique);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rubriques;
	}

	@Override
	public int create(Rubrique r) {
		int res = 0;
		String requete = "INSERT INTO Rubrique(intitule) "
				+ "VALUES (?)";
		Object[] params = { r.getIntitule() };
		
		try {
			res = execRequeteModification(requete, params);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int update(Rubrique r) {
		int res = 0;
		String requete = "UPDATE Rubrique "
				+ "SET intitule = ? "
				+ "WHERE id = ?";
		Object[] params = { r.getIntitule(), r.getId() };
		
		try {
			res = execRequeteModification(requete, params);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int delete(Rubrique r) {
		int res = 0;
		
		try {
			res = execRequeteSuppression("Rubrique", r.getId());
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int deleteAll() {
		int res = 0;
		String requete = "DELETE FROM Rubrique";
		
		try {
			res = execRequeteModification(requete, null);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return res;
	}

}
