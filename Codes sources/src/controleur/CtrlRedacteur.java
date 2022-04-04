package controleur;

import java.util.Iterator;

import javax.swing.JOptionPane;

import metier.Redacteur;
import modele.ModeleRedacteur;
import vue.formulaire.FormRedacteur;

public class CtrlRedacteur {

	private ModeleRedacteur modeleRedacteur;
	
	public CtrlRedacteur(ModeleRedacteur modeleRedacteur) {
		this.modeleRedacteur = modeleRedacteur;
	}
	
	public void afficherFormulaireAjout() {
		FormRedacteur fred = new FormRedacteur(null, "Rédacteur", true, "Nom");
		Redacteur redacteurAjoute = fred.showForm();
		
		if (redacteurAjoute != null) {
			
			if (nomValide(redacteurAjoute)) {
				if (nommagePasTropLong(redacteurAjoute.getNom())) {
					if (!existeDeja(redacteurAjoute)) {
						modeleRedacteur.ajouterElement(redacteurAjoute);
					} else {
						afficherMessageErreur("Ce rédacteur existe déjà");
					}
				} else {
					String msg = "Nommage trop long\n\n"
							+ "Le nom doit avoir au maximum 50 caractères.";
					afficherMessageErreur(msg);
				}
			} else {
				afficherMessageErreur("Le nom du rédacteur est invalide");
			}
			
		}
	}
	
	public void afficherFormulaireModif(Redacteur redacteurCourant) {
		if (redacteurCourant == null) {
			afficherMessageErreur("Aucun élément sélectionné");
		} else {
			FormRedacteur fred = new FormRedacteur(null, "Rédacteur", true, "Nom");
			fred.remplirChampsParValeursCourantes(redacteurCourant);
			Redacteur redacteurModifie = fred.showForm();
			
			if (redacteurModifie != null) {
				
				if (nomValide(redacteurModifie)) {
					if (nommagePasTropLong(redacteurModifie.getNom())) {
						if (redacteurModifie.equals(redacteurCourant) || !existeDeja(redacteurModifie)) {
							redacteurCourant.setNom(redacteurModifie.getNom());
							
							modeleRedacteur.modifierElement(redacteurCourant);
						} else {
							afficherMessageErreur("Ce rédacteur existe déjà");
						}
					} else {
						String msg = "Nommage trop long\n\n"
								+ "Le nom doit avoir au maximum 50 caractères.";
						afficherMessageErreur(msg);
					}
				} else {
					afficherMessageErreur("Le nom du rédacteur est invalide");
				}
			}
		}
	}
	
	public void supprimerUnRedacteur(Redacteur redacteurCourant) {
		if (redacteurCourant == null) {
			afficherMessageErreur("Aucun élément sélectionné");
		} else {
			String msg = "Etes-vous sûr de vouloir supprimer ce rédacteur ?";
			String[] options = { "Oui", "Annuler" };
			
			int rangOption = JOptionPane.showOptionDialog(null, msg, "Confirmation",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
					options, options[1]);
			
			if (rangOption == 0) {
				modeleRedacteur.supprimerElement(redacteurCourant);
			}
		}
	}
	
	private boolean nomValide(Redacteur redacteur) {
		boolean correct = false;
		String nom = redacteur.getNom().trim();
		
		correct = !nom.equals("");
		if (correct) {
			redacteur.setNom(nom);			
		}
		
		return correct;
	}
	
	private boolean nommagePasTropLong(String nom) {
		boolean correct = false;
		int longueurNom = nom.length();
		
		correct = (longueurNom <= 50);
		
		return correct;
	}
	
	private boolean existeDeja(Redacteur redacteur) {
		boolean existe = false;
		Iterator<Redacteur> it = modeleRedacteur.getDonneesActuelles().values().iterator();
		
		while (it.hasNext() && !existe) {
			Redacteur r = it.next();
			
			existe = redacteur.equals(r);
		}
		
		return existe;
	}
	
	private static void afficherMessageErreur(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
	
}
