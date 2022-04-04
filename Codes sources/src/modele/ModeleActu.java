package modele;

import java.util.ArrayList;

import bdd.dao.DAOFactory;
import bdd.dao.implement.ActuDAO;
import metier.Actu;
import metier.Redacteur;
import metier.Rubrique;

public class ModeleActu extends ModeleGenerique<Actu> {
	
	private ModeleRubrique modeleRubrique;
	private ModeleRedacteur modeleRedacteur;
	
	public ModeleActu(ModeleRubrique modeleRubrique, ModeleRedacteur modeleRedacteur) {
		this.modeleRubrique = modeleRubrique;
		this.modeleRedacteur = modeleRedacteur;
		
		modeleRubrique.setModeleActu(this);
		modeleRedacteur.setModeleActu(this);
		
		dao = DAOFactory.getActuDAO();
	}

	@Override
	public void chargerDonnees() {
		super.chargerDonnees();
		
		recupererRubriqueDesActus();
		recupererRedacteurDesActus();
		
		notifyObserver(new ArrayList<Actu>(donneesActuelles.values()));
	}
	
	@Override
	public int ajouterElement(Actu element) {
		int res = 0;
		
		res = super.ajouterElement(element);
		
		if (element.getRubrique() != null) {
			((ActuDAO) dao).preciserIdRubrique(element);
			modeleRubrique.notifyObserver(new ArrayList<Rubrique>(modeleRubrique.getDonneesActuelles().values()));
		}
		
		if (element.getRedacteur() != null) {
			((ActuDAO) dao).preciserIdRedacteur(element);
			modeleRedacteur.notifyObserver(new ArrayList<Redacteur>(modeleRedacteur.getDonneesActuelles().values()));
		}
		
		notifyObserver(new ArrayList<Actu>(donneesActuelles.values()));
		
		return res;
	}
	
	@Override
	public int modifierElement(Actu element) {
		int res = 0;
		
		res = super.modifierElement(element);
		notifyObserver(new ArrayList<Actu>(donneesActuelles.values()));
		
		if (element.getRubrique() != null) {			
			modeleRubrique.notifyObserver(new ArrayList<Rubrique>(modeleRubrique.getDonneesActuelles().values()));
		}
		
		if (element.getRedacteur() != null) {			
			modeleRedacteur.notifyObserver(new ArrayList<Redacteur>(modeleRedacteur.getDonneesActuelles().values()));
		}
		
		return res;
	}
	
	@Override
	public int supprimerElement(Actu element) {
		int res = 0;
		
		desassocierRubriqueEtRedacteur(element);
		res = super.supprimerElement(element);
		notifyObserver(new ArrayList<Actu>(donneesActuelles.values()));
		
		return res;
	}
	
	private void recupererRubriqueDesActus() {
		for (Actu actu: donneesActuelles.values()) {
			int idRubrique = ((ActuDAO) dao).findIdRubrique(actu);
			
			if (idRubrique != 0) {
				Rubrique rubrique = modeleRubrique.getDonneesActuelles().get(idRubrique);
				
				actu.setRubrique(rubrique);
				rubrique.addActu(actu);
			}
		}
		
		modeleRubrique.notifyObserver(new ArrayList<Rubrique>(modeleRubrique.getDonneesActuelles().values()));
	}
	
	private void recupererRedacteurDesActus() {
		for (Actu actu: donneesActuelles.values()) {
			int idRedacteur = ((ActuDAO) dao).findIdRedacteur(actu);
			
			if (idRedacteur != 0) {
				Redacteur redacteur = modeleRedacteur.getDonneesActuelles().get(idRedacteur);
				
				actu.setRedacteur(redacteur);
				redacteur.addActu(actu);
			}
		}
		
		modeleRedacteur.notifyObserver(new ArrayList<Redacteur>(modeleRedacteur.getDonneesActuelles().values()));
	}
	
	private void desassocierRubriqueEtRedacteur(Actu actu) {
		if (actu.getRubrique() != null) {			
			actu.getRubrique().removeActu(actu);
			modeleRubrique.notifyObserver(new ArrayList<Rubrique>(modeleRubrique.getDonneesActuelles().values()));
		}
		
		if (actu.getRedacteur() != null) {			
			actu.getRedacteur().removeActu(actu);
			modeleRedacteur.notifyObserver(new ArrayList<Redacteur>(modeleRedacteur.getDonneesActuelles().values()));
		}
	}
	
}
