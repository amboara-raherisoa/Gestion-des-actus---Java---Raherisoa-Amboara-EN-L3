package bdd.connexion;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

import vue.formulaire.FormConnexion;

public class MaConnexion {

	private static Connection connection;
	
	private String url = "jdbc:mysql://localhost:3306/actuamboara";
	private String user;
	private String mdp;
	
	// Constructeur priv�
	private MaConnexion() {
		
		FormConnexion fc = new FormConnexion(null, "Connexion � la base de donn�es", true);
		InfosConnexion infosConnexion = fc.showForm();
		
		user = infosConnexion.getUser();
		mdp = infosConnexion.getMdp();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Chargement du driver JDBC pour MySQL
			connection = DriverManager.getConnection(url, user, mdp); // Etablissement de la connexion
			
			
		} catch (Exception e) {
			String msg = "Echec de la connexion � la base de donn�es";
			
			JOptionPane.showMessageDialog(null, msg, "Erreur", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
	}
	
	// M�thode qui va retourner l'instance en la cr�ant si elle n'existe pas
	public static Connection getInstance() {
		if (connection == null) {
			new MaConnexion();
		}
		return connection;
	}
	
}
