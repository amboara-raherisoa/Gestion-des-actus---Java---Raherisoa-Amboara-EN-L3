package bdd.connexion;

public class InfosConnexion {
	
	private String user;
	private String mdp;
	
	public InfosConnexion(String user, String mdp) {
		this.user = user;
		this.mdp = mdp;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getMdp() {
		return mdp;
	}
	
}
