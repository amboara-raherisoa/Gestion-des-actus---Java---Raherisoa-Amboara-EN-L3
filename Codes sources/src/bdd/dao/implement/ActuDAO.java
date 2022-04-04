package bdd.dao.implement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.TreeMap;

import bdd.dao.DAO;
import metier.Actu;

public class ActuDAO extends DAO<Actu> {

	public ActuDAO(Connection connection) {
		super(connection);
	}

	@Override
	public TreeMap<Integer, Actu> findAll() {
		TreeMap<Integer, Actu> actus = new TreeMap<Integer, Actu>();
		
		try {
			ResultSet rst = execRequeteSelectToutesLesLignes("Actu");
			
			while (rst.next()) {
				int id = rst.getInt("id");
				String theme = rst.getString("theme");
				String titre = rst.getString("titre");
				String breveDescription = rst.getString("breve_description");
				String contenu = rst.getString("contenu");
				Date datePublication = rst.getDate("date_publication");
				
				Actu actu = new Actu(id, theme, titre, breveDescription, contenu, datePublication);
				
				actus.put(id, actu);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return actus;
	}

	@Override
	public int create(Actu a) {
		int res = 0;
		String requete = "INSERT INTO Actu(theme, titre, breve_description, contenu, date_publication) "
				+ "VALUES (?, ?, ?, ?, ?)";
		Object[] params = { a.getTheme(), a.getTitre(), 
				a.getBreveDescription(), a.getContenu(), a.getDatePublication() };
		
		try {
			res = execRequeteModification(requete, params);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int update(Actu a) {
		int res = 0;
		String requete = "UPDATE Actu "
				+ "SET theme = ?, titre = ?, breve_description = ?, contenu = ?, "
				+ "id_rubrique = ?, id_redacteur = ? "
				+ "WHERE id = ?";
		Object param4 = (a.getRubrique() == null) ? ("NULL") : (a.getRubrique().getId());
		Object param5 = (a.getRedacteur() == null) ? ("NULL") : (a.getRedacteur().getId());
		Object[] params = { a.getTheme(), a.getTitre(), a.getBreveDescription(), a.getContenu(),
							param4, param5, a.getId() };
		
		try {
			res = execRequeteModification(requete, params);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int delete(Actu a) {
		int res = 0;
		
		try {
			res = execRequeteSuppression("Actu", a.getId());
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int deleteAll() {
		int res = 0;
		String requete = "DELETE FROM Actu";
		
		try {
			res = execRequeteModification(requete, null);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return res;
	}
	
	public int findIdRubrique(Actu a) {
		int idRubrique = 0;
		
		String requete = "SELECT id_rubrique FROM Actu "
				+ "WHERE id = ?";
		
		try {
			ResultSet rst = execRequeteSelectSelonId(requete, a.getId());
			
			if (rst.first()) {
				idRubrique = rst.getInt("id_rubrique");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return idRubrique;
	}
	
	public int findIdRedacteur(Actu a) {
		int idRedacteur = 0;
		
		String requete = "SELECT id_redacteur FROM Actu "
				+ "WHERE id = ?";
		
		try {
			ResultSet rst = execRequeteSelectSelonId(requete, a.getId());
			
			if (rst.first()) {
				idRedacteur = rst.getInt("id_redacteur");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return idRedacteur;
	}

	public int preciserIdRubrique(Actu a) {
		int res = 0;
		
		String requete = "UPDATE Actu SET id_rubrique = ? "
				+ "WHERE id = ?";
		
		Object[] params = { a.getRubrique().getId(), a.getId() };
		
		try {
			res = execRequeteModification(requete, params);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public int preciserIdRedacteur(Actu a) {
		int res = 0;
		
		String requete = "UPDATE Actu SET id_redacteur = ? "
				+ "WHERE id = ?";
		
		Object[] params = { a.getRedacteur().getId(), a.getId() };
		
		try {
			res = execRequeteModification(requete, params);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
}
