package controleur;

import javax.swing.JOptionPane;

import modele.ModeleActu;
import modele.ModeleRedacteur;
import modele.ModeleRubrique;

public class CtrlReset {
	
	private ModeleRubrique modeleRubrique;
	private ModeleRedacteur modeleRedacteur;
	private ModeleActu modeleActu;
	
	public CtrlReset(ModeleRubrique modeleRubrique,
					ModeleRedacteur modeleRedacteur,
					ModeleActu modeleActu)
	{
		this.modeleRubrique = modeleRubrique;
		this.modeleRedacteur = modeleRedacteur;
		this.modeleActu = modeleActu;
	}
	
	public void reinitialiser() {
		String msg = "Etes-vous sûr de vouloir réinitialiser les données ?\n\n"
				+ "Toutes les données actuelles seront effacées définitivement.";
		String[] options = { "Oui", "Annuler" };
		
		int rangOption = JOptionPane.showOptionDialog(null, msg, "Confirmation",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
				options, options[1]);
		
		if (rangOption == 0) {
			modeleActu.toutSupprimer();
			modeleRedacteur.toutSupprimer();
			modeleRubrique.toutSupprimer();
		}
	}
	
}
