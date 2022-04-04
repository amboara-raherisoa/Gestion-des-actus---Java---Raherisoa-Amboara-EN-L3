package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controleur.CtrlRubrique;
import metier.Rubrique;

public class VueRubrique extends MonPanel<Rubrique> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CtrlRubrique ctrlRubrique;
	
	public VueRubrique() {
		super();
		label.setText("Liste des rubriques");
	}
	
	public void setCtrlRubrique(CtrlRubrique ctrlRubrique) {
		this.ctrlRubrique = ctrlRubrique;
	}
	
	@Override
	protected void specifierEcouteurs() {
		
		btnAjouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrlRubrique.afficherFormulaireAjout();
			}
		});
		
		btnModifier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrlRubrique.afficherFormulaireModif(list.getSelectedValue());
			}
		});
		
		btnSupprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrlRubrique.supprimerUneRubrique(list.getSelectedValue());
			}
		});
		
	}

}
