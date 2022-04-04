package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controleur.CtrlRedacteur;
import metier.Redacteur;

public class VueRedacteur extends MonPanel<Redacteur> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CtrlRedacteur ctrlRedacteur;
	
	public VueRedacteur() {
		super();
		label.setText("Liste des rédacteurs");
	}
	
	public void setCtrlRedacteur(CtrlRedacteur ctrlRedacteur) {
		this.ctrlRedacteur = ctrlRedacteur;
	}
	
	@Override
	protected void specifierEcouteurs() {
		
		btnAjouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrlRedacteur.afficherFormulaireAjout();
			}
		});
		
		btnModifier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrlRedacteur.afficherFormulaireModif(list.getSelectedValue());
			}
		});
		
		btnSupprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrlRedacteur.supprimerUnRedacteur(list.getSelectedValue());
			}
		});
		
	}

}
