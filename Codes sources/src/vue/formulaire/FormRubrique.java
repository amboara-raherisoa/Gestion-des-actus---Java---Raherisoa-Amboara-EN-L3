package vue.formulaire;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import metier.Rubrique;

public class FormRubrique extends FormMonochamp<Rubrique> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FormRubrique(JFrame parent, String title, boolean modal, String nomDuChamp) {
		super(parent, title, modal, nomDuChamp);
	}
	
	@Override
	public void remplirChampsParValeursCourantes(Rubrique r) {
		champNom.setText(r.getIntitule());
	}
	
	@Override
	protected void specifierEcouteurs() {
		super.specifierEcouteurs();
		
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String intitule = champNom.getText();
				
				donnee = new Rubrique(intitule);
				envoyerDonnee = true;
				setVisible(false);
			}
		});
	}
}
