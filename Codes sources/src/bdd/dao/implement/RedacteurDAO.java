package bdd.dao.implement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import bdd.dao.DAO;
import metier.Redacteur;

public class RedacteurDAO extends DAO<Redacteur> {

	public RedacteurDAO(Connection connection) {
		super(connection);
	}

	@Override
	public TreeMap<Integer, Redacteur> findAll() {
		TreeMap<Integer, Redacteur> redacteurs = new TreeMap<Integer, Redacteur>();
		
		try {
			ResultSet rst = execRequeteSelectToutesLesLignes("Redacteur");
			
			while (rst.next()) {
				int id = rst.getInt("id");
				String nom = rst.getString("nom");
				
				Redacteur redacteur = new Redacteur(id, nom);
				
				redacteurs.put(id, redacteur);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return redacteurs;
	}

	@Override
	public int create(Redacteur r) {
		int res = 0;
		String requete = "INSERT INTO Redacteur(nom) "
				+ "VALUES (?)";
		Object[] params = { r.getNom() };
		
		try {
			res = execRequeteModification(requete, params);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int update(Redacteur r) {
		int res = 0;
		String requete = "UPDATE Redacteur "
				+ "SET nom = ? "
				+ "WHERE id = ?";
		Object[] params = { r.getNom(), r.getId() };
		
		try {
			res = execRequeteModification(requete, params);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int delete(Redacteur r) {
		int res = 0;
		
		try {
			res = execRequeteSuppression("Redacteur", r.getId());
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int deleteAll() {
		int res = 0;
		String requete = "DELETE FROM Redacteur";
		
		try {
			res = execRequeteModification(requete, null);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return res;
	}

}
