package vue.formulaire;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public abstract class Formulaire<T> extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected JButton btnValider = new JButton("Valider");
	protected JButton btnAnnuler = new JButton("Annuler");
	
	protected T donnee;
	
	protected boolean envoyerDonnee = false;
	
	public Formulaire(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	protected abstract void initialiserComposants();
	protected abstract void agencerComposants();
	public abstract void remplirChampsParValeursCourantes(T obj);
	
	protected void specifierEcouteurs() {
		
		btnAnnuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				envoyerDonnee = false;
				setVisible(false);
			}
		});
		
	}
	
	public T showForm() {
		T res = null;
		
		this.setVisible(true);
		
		if (envoyerDonnee) {
			res = donnee;
		}
		
		return res;
	}

}
