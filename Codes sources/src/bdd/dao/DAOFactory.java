package bdd.dao;

import java.sql.Connection;

import bdd.connexion.MaConnexion;
import bdd.dao.implement.ActuDAO;
import bdd.dao.implement.RedacteurDAO;
import bdd.dao.implement.RubriqueDAO;

public class DAOFactory {
	
protected final static Connection CONNECTION = MaConnexion.getInstance();
	
	public static ActuDAO getActuDAO() {
		return new ActuDAO(CONNECTION);
	}
	
	public static RedacteurDAO getRedacteurDAO() {
		return new RedacteurDAO(CONNECTION);
	}
	
	public static RubriqueDAO getRubriqueDAO() {
		return new RubriqueDAO(CONNECTION);
	}
	
}
