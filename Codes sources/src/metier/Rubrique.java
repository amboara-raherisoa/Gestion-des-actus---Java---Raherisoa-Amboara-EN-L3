package metier;

import java.util.Set;
import java.util.TreeSet;

public class Rubrique extends Entity {
	
	private String intitule;
	private Set<Actu> actusConcernees = new TreeSet<>();

	public Rubrique(String intitule) {
		this.intitule = intitule;
	}
	
	public Rubrique(int id, String intitule) {
		this.id = id;
		this.intitule = intitule;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	
	public Set<Actu> getActusConcernees() {
		return actusConcernees;
	}
	
	public void setActusConcernees(Set<Actu> actusConcernees) {
		this.actusConcernees = actusConcernees;
	}
	
	public boolean addActu(Actu a) {
		return actusConcernees.add(a);
	}
	
	public boolean removeActu(Actu a) {
		return actusConcernees.remove(a);
	}
	
	@Override
	public String toString() {
		return intitule;
	}
	
}
