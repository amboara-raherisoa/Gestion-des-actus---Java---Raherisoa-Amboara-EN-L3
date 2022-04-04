package controleur;

import java.util.Iterator;

import javax.swing.JOptionPane;

import metier.Rubrique;
import modele.ModeleRubrique;
import vue.formulaire.FormRubrique;

public class CtrlRubrique {
	
	private ModeleRubrique modeleRubrique;
	
	public CtrlRubrique(ModeleRubrique modeleRubrique) {
		this.modeleRubrique = modeleRubrique;
	}
	
	public void afficherFormulaireAjout() {
		FormRubrique frub = new FormRubrique(null, "Rubrique", true, "Intitul�");
		Rubrique rubriqueAjoute = frub.showForm();
		
		if (rubriqueAjoute != null) {
			
			if (intituleValide(rubriqueAjoute)) {
				if (intitulePasTropLong(rubriqueAjoute.getIntitule())) {
					if (!existeDeja(rubriqueAjoute)) {
						modeleRubrique.ajouterElement(rubriqueAjoute);
					} else {
						afficherMessageErreur("Cette rubrique existe d�j�");
					}
				} else {
					String msg = "Nommage trop long\n\n"
							+ "L'intitul� doit avoir au maximum 50 caract�res.";
					afficherMessageErreur(msg);
				}
			} else {
				afficherMessageErreur("L'intitul� de la rubrique est invalide");
			}
			
		}
	}
	
	public void afficherFormulaireModif(Rubrique rubriqueCourante) {
		if (rubriqueCourante == null) {
			afficherMessageErreur("Aucun �l�ment s�lectionn�");
		} else {
			FormRubrique frub = new FormRubrique(null, "Rubrique", true, "Intitul�");
			frub.remplirChampsParValeursCourantes(rubriqueCourante);
			Rubrique rubriqueModifiee = frub.showForm();
			
			if (rubriqueModifiee != null) {
				
				if (intituleValide(rubriqueModifiee)) {
					if (intitulePasTropLong(rubriqueModifiee.getIntitule())) {
						if (rubriqueModifiee.equals(rubriqueCourante) || !existeDeja(rubriqueModifiee)) {
							rubriqueCourante.setIntitule(rubriqueModifiee.getIntitule());;
							
							modeleRubrique.modifierElement(rubriqueCourante);
						} else {
							afficherMessageErreur("Cette rubrique existe d�j�");
						}
					} else {
						String msg = "Nommage trop long\n\n"
								+ "L'intitul� doit avoir au maximum 50 caract�res.";
						afficherMessageErreur(msg);
					}
				} else {
					afficherMessageErreur("L'intitul� de la rubrique est invalide");
				}
			}
		}
	}
	
	public void supprimerUneRubrique(Rubrique rubriqueCourante) {
		if (rubriqueCourante == null) {
			afficherMessageErreur("Aucun �l�ment s�lectionn�");
		} else {
			String msg = "Etes-vous s�r de vouloir supprimer cette rubrique ?";
			String[] options = { "Oui", "Annuler" };
			
			int rangOption = JOptionPane.showOptionDialog(null, msg, "Confirmation",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
					options, options[1]);
			
			if (rangOption == 0) {
				modeleRubrique.supprimerElement(rubriqueCourante);
			}
		}
	}
	
	private boolean intituleValide(Rubrique rubrique) {
		boolean correct = false;
		String intitule = rubrique.getIntitule().trim();
		
		correct = !intitule.equals("");
		if (correct) {
			rubrique.setIntitule(intitule);			
		}
		
		return correct;
	}
	
	private boolean intitulePasTropLong(String intitule) {
		boolean correct = false;
		int longueurIntitule = intitule.length();
		
		correct = (longueurIntitule <= 50);
		
		return correct;
	}
	
	private boolean existeDeja(Rubrique rubrique) {
		boolean existe = false;
		Iterator<Rubrique> it = modeleRubrique.getDonneesActuelles().values().iterator();
		
		while (it.hasNext() && !existe) {
			Rubrique r = it.next();
			
			existe = rubrique.equals(r);
		}
		
		return existe;
	}
	
	private static void afficherMessageErreur(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
	
}
