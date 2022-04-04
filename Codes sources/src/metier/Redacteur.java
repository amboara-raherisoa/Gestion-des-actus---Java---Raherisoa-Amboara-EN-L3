package metier;

import java.util.Set;
import java.util.TreeSet;

public class Redacteur extends Entity {
	
	private String nom;
	private Set<Actu> actusRedigees = new TreeSet<>();
	
	public Redacteur(String nom) {
		this.nom = nom;
	}
	
	public Redacteur(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Set<Actu> getActusRedigees() {
		return actusRedigees;
	}
	
	public void setActusRedigees(Set<Actu> actusRedigees) {
		this.actusRedigees = actusRedigees;
	}
	
	public boolean addActu(Actu a) {
		return actusRedigees.add(a);
	}
	
	public boolean removeActu(Actu a) {
		return actusRedigees.remove(a);
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
}
