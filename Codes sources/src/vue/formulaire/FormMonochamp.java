package vue.formulaire;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class FormMonochamp<T> extends Formulaire<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected JLabel labelNom;
	protected JTextField champNom = new JTextField();
	
	public FormMonochamp(JFrame parent, String title, boolean modal, String nomDuChamp) {
		super(parent, title, modal);
		
		this.setSize(300, 180);
		this.setLocationRelativeTo(null);
		
		labelNom = new JLabel(nomDuChamp);
		
		initialiserComposants();
		agencerComposants();
		specifierEcouteurs();
	}
	
	@Override
	protected void initialiserComposants() {
		champNom.setPreferredSize(new Dimension(200, 20));
	}
	
	@Override
	protected void agencerComposants() {
		Container contentPane = this.getContentPane();
		
		JPanel panelCentre = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Insets getInsets() { // Redéfinition des marges
				Insets normal = super.getInsets();
				return new Insets(normal.top, normal.left + 10, normal.bottom,  normal.right + 10);
			}
		};
		JPanel panelBas = new JPanel();
		
		contentPane.setLayout(new BorderLayout(0, 20));
		contentPane.add(panelCentre, BorderLayout.CENTER);
		contentPane.add(panelBas, BorderLayout.SOUTH);
		
		panelCentre.setLayout(new GridLayout(2, 1, 5, 5));
		panelCentre.add(labelNom);
		panelCentre.add(champNom);
		
		panelBas.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		panelBas.add(btnValider);
		panelBas.add(btnAnnuler);
		
	}
}
