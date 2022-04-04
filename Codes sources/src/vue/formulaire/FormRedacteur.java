package vue.formulaire;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import metier.Redacteur;

public class FormRedacteur extends FormMonochamp<Redacteur> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FormRedacteur(JFrame parent, String title, boolean modal, String nomDuChamp) {
		super(parent, title, modal, nomDuChamp);
	}
	
	@Override
	public void remplirChampsParValeursCourantes(Redacteur r) {
		champNom.setText(r.getNom());
	}
	
	@Override
	protected void specifierEcouteurs() {
		super.specifierEcouteurs();
		
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nom = champNom.getText();
				
				donnee = new Redacteur(nom);
				envoyerDonnee = true;
				setVisible(false);
			}
		});
	}

}
