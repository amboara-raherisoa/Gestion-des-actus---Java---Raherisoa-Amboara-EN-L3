package modele;

import java.util.ArrayList;
import java.util.Set;

import bdd.dao.DAOFactory;
import metier.Actu;
import metier.Redacteur;

public class ModeleRedacteur extends ModeleGenerique<Redacteur> {

	private ModeleActu modeleActu;
	
	public ModeleRedacteur() {
		dao = DAOFactory.getRedacteurDAO();
	}
	
	public void setModeleActu(ModeleActu modeleActu) {
		this.modeleActu = modeleActu;
	}
	
	@Override
	public void chargerDonnees() {
		super.chargerDonnees();		
		notifyObserver(new ArrayList<Redacteur>(donneesActuelles.values()));
	}
	
	@Override
	public int ajouterElement(Redacteur redacteur) {
		int res = 0;
		
		res = super.ajouterElement(redacteur);
		notifyObserver(new ArrayList<Redacteur>(donneesActuelles.values()));
		
		return res;
	}
	
	@Override
	public int modifierElement(Redacteur redacteur) {
		int res = 0;
		
		res = super.modifierElement(redacteur);
		notifyObserver(new ArrayList<Redacteur>(donneesActuelles.values()));
		
		if (!redacteur.getActusRedigees().isEmpty()) {
			modeleActu.notifyObserver(new ArrayList<Actu>(modeleActu.getDonneesActuelles().values()));
		}
		
		return res;
	}
	
	@Override
	public int supprimerElement(Redacteur redacteur) {
		int res = 0;
		
		desassocierActus(redacteur);
		res = super.supprimerElement(redacteur);
		notifyObserver(new ArrayList<Redacteur>(donneesActuelles.values()));
		
		return res;
	}
	
	private void desassocierActus(Redacteur redacteur) {
		Set<Actu> actusRedigees = redacteur.getActusRedigees();
		
		if (!actusRedigees.isEmpty()) {
			for (Actu actu : actusRedigees) {
				actu.setRedacteur(null);
			}
			
			modeleActu.notifyObserver(new ArrayList<Actu>(modeleActu.getDonneesActuelles().values()));
		}
		
	}
	
}
