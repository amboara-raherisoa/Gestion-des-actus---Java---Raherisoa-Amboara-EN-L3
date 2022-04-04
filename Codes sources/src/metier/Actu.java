package metier;

import java.util.Calendar;
import java.util.Date;

public class Actu extends Entity {
	
	private String theme;
	private String titre;
	private String breveDescription;
	private String contenu;
	private Date datePublication;
	private Rubrique rubrique;
	private Redacteur redacteur;
	
	public Actu(String theme, String titre, String breveDescription,
			String contenu) {
		this.theme = theme;
		this.titre = titre;
		this.breveDescription = breveDescription;
		this.contenu = contenu;
	}
	
	public Actu(int id, String theme, String titre, String breveDescription,
			String contenu, Date datePublication) {
		this(theme, titre, breveDescription, contenu);
		this.id = id;
		this.datePublication = datePublication;
	}
	
	public String getTheme() {
		return theme;
	}
	
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	public String getTitre() {
		return titre;
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getBreveDescription() {
		return breveDescription;
	}
	
	public void setBreveDescription(String breveDescription) {
		this.breveDescription = breveDescription;
	}
	
	public String getContenu() {
		return contenu;
	}
	
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	public Date getDatePublication() {
		return datePublication;
	}
	
	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}
	
	public Rubrique getRubrique() {
		return rubrique;
	}
	
	public void setRubrique(Rubrique rubrique) {
		this.rubrique = rubrique;
	}
	
	public Redacteur getRedacteur() {
		return redacteur;
	}
	
	public void setRedacteur(Redacteur redacteur) {
		this.redacteur = redacteur;
	}
	
	@Override
	public String toString() {
		String titreRaccourci = (titre.length() > 100) ? (titre.substring(0, 100) + "...") : titre;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(datePublication);
		int annee = cal.get(Calendar.YEAR);
		int mois = cal.get(Calendar.MONTH) + 1;
		int jour = cal.get(Calendar.DAY_OF_MONTH);
		String datePubli = ((jour < 10) ? ("0" + jour) : jour)
				+ "/" + ((mois < 10) ? ("0" + mois) : mois)
				+ "/" + annee;
		
		return datePubli + " - " + this.theme + " - " + titreRaccourci;
		
	}
	
	@Override
	public int compareTo(Entity o) {
		return -(super.compareTo(o));
	}
	
}
