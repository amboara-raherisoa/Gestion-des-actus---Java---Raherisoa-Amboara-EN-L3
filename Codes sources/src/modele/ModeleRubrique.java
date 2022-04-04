package modele;

import java.util.ArrayList;
import java.util.Set;

import bdd.dao.DAOFactory;
import metier.Actu;
import metier.Rubrique;

public class ModeleRubrique extends ModeleGenerique<Rubrique> {

	private ModeleActu modeleActu;
	
	public ModeleRubrique() {
		dao = DAOFactory.getRubriqueDAO();
	}
	
	public void setModeleActu(ModeleActu modeleActu) {
		this.modeleActu = modeleActu;
	}
	
	@Override
	public void chargerDonnees() {
		super.chargerDonnees();
		notifyObserver(new ArrayList<Rubrique>(donneesActuelles.values()));
	}
	
	@Override
	public int ajouterElement(Rubrique rubrique) {
		int res = 0;
		
		res = super.ajouterElement(rubrique);
		notifyObserver(new ArrayList<Rubrique>(donneesActuelles.values()));
		
		return res;
	}
	
	@Override
	public int modifierElement(Rubrique rubrique) {
		int res = 0;
		
		res = super.modifierElement(rubrique);
		notifyObserver(new ArrayList<Rubrique>(donneesActuelles.values()));
		
		if (!rubrique.getActusConcernees().isEmpty()) {
			modeleActu.notifyObserver(new ArrayList<Actu>(modeleActu.getDonneesActuelles().values()));
		}
		
		return res;
	}
	
	@Override
	public int supprimerElement(Rubrique rubrique) {
		int res = 0;
		
		desassocierActus(rubrique);
		res = super.supprimerElement(rubrique);
		notifyObserver(new ArrayList<Rubrique>(donneesActuelles.values()));
		
		return res;
	}
	
	private void desassocierActus(Rubrique rubrique) {
		Set<Actu> actusConcernees = rubrique.getActusConcernees();
		
		if (!actusConcernees.isEmpty()) {
			for (Actu actu : actusConcernees) {
				actu.setRubrique(null);
			}
			
			modeleActu.notifyObserver(new ArrayList<Actu>(modeleActu.getDonneesActuelles().values()));
		}
		
	}
}
