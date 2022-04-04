package controleur;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JOptionPane;

import metier.Actu;
import metier.Redacteur;
import metier.Rubrique;
import modele.ModeleActu;
import modele.ModeleRedacteur;
import modele.ModeleRubrique;
import vue.formulaire.EditeurActu;
import vue.formulaire.FormActu;

public class CtrlActu {

	private ModeleActu modeleActu;
	
	private ModeleRubrique modeleRubrique;
	private ModeleRedacteur modeleRedacteur;
	
	private ArrayList<Rubrique> rubriquesExistantes;
	private ArrayList<Redacteur> redacteursExistants;
	
	public CtrlActu(ModeleActu modeleActu,
				ModeleRubrique modeleRubrique,
				ModeleRedacteur modeleRedacteur)
	{
		this.modeleActu = modeleActu;
		this.modeleRubrique = modeleRubrique;
		this.modeleRedacteur = modeleRedacteur;
	}
	
	public void afficherFormulaireAjout() {
		recupererDonneesDesModeles();
		
		FormActu fa = new FormActu(null, "Actualité", true,
								rubriquesExistantes, redacteursExistants);
		Actu actuAjoutee = fa.showForm();
				
		if (actuAjoutee != null) {
		
			actuAjoutee.setDatePublication(new Date());
			
			if (themeEtTitreValides(actuAjoutee)) {
				if (pasTropLong(actuAjoutee.getTheme(), actuAjoutee.getTitre())) {
					if (!existeDeja(actuAjoutee)) {
						
						if(actuAjoutee.getRubrique() != null) {							
							actuAjoutee.getRubrique().addActu(actuAjoutee);
						}
						
						if(actuAjoutee.getRedacteur() != null) {							
							actuAjoutee.getRedacteur().addActu(actuAjoutee);
						}
						
						modeleActu.ajouterElement(actuAjoutee);
					} else {
						afficherMessageErreur("Cette actualité existe déjà");
					}
				} else {
					String msg = "Informations trop longues\n\n"
							+ "Le thème doit avoir au maximum 50 caractères.\n"
							+ "Le titre doit avoir au maximum 150 caractères.";
					afficherMessageErreur(msg);
				}
			} else {
				afficherMessageErreur("Thème ou titre invalide");
			}
		}
	}
	
	public void afficherFormulaireModifInfos(Actu actuCourante) {
		if (actuCourante == null) {
			afficherMessageErreur("Aucun élément sélectionné");
		} else {
			recupererDonneesDesModeles();
			
			FormActu fa = new FormActu(null, "Actualité", true,
					rubriquesExistantes, redacteursExistants);
			fa.remplirChampsParValeursCourantes(actuCourante);
			Actu actuModifiee = fa.showForm();
						
			if (actuModifiee != null) {
			
				actuModifiee.setDatePublication(actuCourante.getDatePublication());
				
				if (themeEtTitreValides(actuModifiee)) {
					if (pasTropLong(actuModifiee.getTheme(), actuModifiee.getTitre())) {
						if (actuModifiee.equals(actuCourante) || !existeDeja(actuModifiee)) {
							actuCourante.setTheme(actuModifiee.getTheme());
							actuCourante.setTitre(actuModifiee.getTitre());
							
							if ( (actuModifiee.getRubrique() != null)
								&& !(actuModifiee.getRubrique()).equals(actuCourante.getRubrique()) )
							{
								if (actuCourante.getRubrique() != null) {									
									actuCourante.getRubrique().removeActu(actuCourante); //Ancienne
								}
								actuCourante.setRubrique(actuModifiee.getRubrique());
								actuCourante.getRubrique().addActu(actuCourante); //Nouvelle
							}
							
							if ( (actuModifiee.getRedacteur() != null)
								&& !(actuModifiee.getRedacteur()).equals(actuCourante.getRedacteur()) )
							{
								if (actuCourante.getRedacteur() != null) {									
									actuCourante.getRedacteur().removeActu(actuCourante);
								}
								actuCourante.setRedacteur(actuModifiee.getRedacteur());
								actuCourante.getRedacteur().addActu(actuCourante);
							}
							
							modeleActu.modifierElement(actuCourante);
						} else {
							afficherMessageErreur("Cette actualité existe déjà");
						}
					} else {
						String msg = "Informations trop longues\n\n"
								+ "Le thème doit avoir au maximum 50 caractères.\n"
								+ "Le titre doit avoir au maximum 150 caractères.";
						afficherMessageErreur(msg);
					}
				} else {
					afficherMessageErreur("Thème ou titre invalide");
				}
			}
		}
	}
	
	public void afficherEditeur(Actu actuCourante, String titreEditeur) {
		if (actuCourante == null) {
			afficherMessageErreur("Aucun élément sélectionné");
		} else {
			new EditeurActu(actuCourante, titreEditeur, this);
		}
	}
	
	public void sauverEdition(Actu actuCourante) {
		modeleActu.modifierElement(actuCourante);
	}
	
	public void supprimerUneActu(Actu actuCourante) {
		if (actuCourante == null) {
			afficherMessageErreur("Aucun élément sélectionné");
		} else {
			String msg = "Etes-vous sûr de vouloir supprimer cette actualité ?\n";
			String[] options = { "Oui", "Annuler" };
			
			int rangOption = JOptionPane.showOptionDialog(null, msg, "Confirmation",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
					options, options[1]);
			
			if (rangOption == 0) {
				modeleActu.supprimerElement(actuCourante);
			}
		}
	}
	
	private void recupererDonneesDesModeles() {
		rubriquesExistantes = new ArrayList<Rubrique>(modeleRubrique.getDonneesActuelles().values());
		redacteursExistants = new ArrayList<Redacteur>(modeleRedacteur.getDonneesActuelles().values());
		
		rubriquesExistantes.sort(null);
		redacteursExistants.sort(null);
	}
	
	private boolean themeEtTitreValides(Actu actu) {
		boolean correct = false;
		String theme = actu.getTheme().trim();
		String titre = actu.getTitre().trim();
		
		correct = !(theme.equals("")) && !(titre.equals(""));
		if (correct) {
			actu.setTheme(theme);
			actu.setTitre(titre);
		}
		
		return correct;
	}
	
	private boolean pasTropLong(String theme, String titre) {
		boolean correct = false;
		int longueurTheme = theme.length();
		int longueurTitre = titre.length();
		
		correct = (longueurTheme <= 50) && (longueurTitre <= 150);
		
		return correct;
	}
	
	private boolean existeDeja(Actu actu) {
		boolean existe = false;
		Iterator<Actu> it = modeleActu.getDonneesActuelles().values().iterator();
		
		while (it.hasNext() && !existe) {
			Actu a = it.next();
			
			existe = actu.equals(a);
		}
		
		return existe;
	}
	
	private static void afficherMessageErreur(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}
