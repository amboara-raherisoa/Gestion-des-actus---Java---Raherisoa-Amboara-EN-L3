package vue.formulaire;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import metier.Actu;
import metier.Redacteur;
import metier.Rubrique;

public class FormActu extends Formulaire<Actu> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel labelTheme = new JLabel("Thème");
	private JTextField champTheme = new JTextField();
	
	private JLabel labelTitre = new JLabel("Titre");
	private JEditorPane champTitre = new JEditorPane();
	
	private JLabel labelRubrique = new JLabel("Rubrique");
	private JComboBox<Rubrique> cbxRubrique = new JComboBox<>();
	
	private JLabel labelRedacteur = new JLabel("Rédacteur");
	private JComboBox<Redacteur> cbxRedacteur = new JComboBox<>();
	
	private ArrayList<Rubrique> rubriquesExistantes;
	private ArrayList<Redacteur> redacteursExistants;
	
	public FormActu(JFrame parent, String title, boolean modal,
			ArrayList<Rubrique> rubriquesExistantes,
			ArrayList<Redacteur> redacteursExistants)
	{
		super(parent, title, modal);
		
		this.rubriquesExistantes = rubriquesExistantes;
		this.redacteursExistants = redacteursExistants;
		
		this.setSize(500, 768);
		this.setLocationRelativeTo(null);
		
		initialiserComposants();
		agencerComposants();
		specifierEcouteurs();
		
		this.pack();
	}

	@Override
	protected void initialiserComposants() {
		for (Rubrique rubrique: rubriquesExistantes) {
			cbxRubrique.addItem(rubrique);
		}
		
		for (Redacteur redacteur: redacteursExistants) {
			cbxRedacteur.addItem(redacteur);
		}
		
		champTheme.setPreferredSize(new Dimension(440, 40));
		champTitre.setPreferredSize(new Dimension(440, 80));
	}

	@Override
	protected void agencerComposants() {
		Container contentPane = this.getContentPane();
		
		JPanel panelCentre= new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Insets getInsets() { // Redéfinition des marges
				Insets normal = super.getInsets();
				return new Insets(normal.top + 5, normal.left + 10, normal.bottom + 20, normal.right + 10);
			}
		};
		JPanel panelTheme = new JPanel();
		JPanel panelTitre = new JPanel();
		JPanel panelCbxs = new JPanel();
		JPanel panelBas = new JPanel();
		
		contentPane.setLayout(new BorderLayout(10, 5));
		contentPane.add(panelCentre, BorderLayout.CENTER);
		contentPane.add(panelBas, BorderLayout.SOUTH);
		
		panelCentre.setPreferredSize(new Dimension(480, 360));
		panelCentre.setLayout(new BorderLayout(0, 20));
		panelCentre.add(panelTheme, BorderLayout.NORTH);
		panelCentre.add(panelTitre, BorderLayout.CENTER);
		panelCentre.add(panelCbxs, BorderLayout.SOUTH);
		
		panelTheme.setLayout(new GridLayout(2, 0));
		panelTheme.add(labelTheme);
		panelTheme.add(champTheme);
		
		panelTitre.setLayout(new GridLayout(2, 0));
		panelTitre.add(labelTitre);
		panelTitre.add(new JScrollPane(champTitre));
		
		panelCbxs.setLayout(new GridLayout(2, 2, 5, 5));
		panelCbxs.add(labelRubrique);
		panelCbxs.add(cbxRubrique);
		panelCbxs.add(labelRedacteur);
		panelCbxs.add(cbxRedacteur);
		
		panelBas.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
		panelBas.add(btnValider);
		panelBas.add(btnAnnuler);
	}
	
	@Override
	protected void specifierEcouteurs() {
		super.specifierEcouteurs();
		
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String theme = champTheme.getText();
				String titre = champTitre.getText();
				Rubrique rubrique = cbxRubrique.getItemAt(cbxRubrique.getSelectedIndex());
				Redacteur redacteur = cbxRedacteur.getItemAt(cbxRedacteur.getSelectedIndex());
				
				donnee = new Actu(theme, titre, "", "");
				donnee.setRubrique(rubrique);
				donnee.setRedacteur(redacteur);
				
				envoyerDonnee = true;
				setVisible(false);
			}
		});
	}

	@Override
	public void remplirChampsParValeursCourantes(Actu a) {
		champTheme.setText(a.getTheme());
		champTitre.setText(a.getTitre());
		if (a.getRubrique() != null) {
			selectRubrique(a.getRubrique());
		}
		if (a.getRedacteur() != null) {
			selectRedacteur(a.getRedacteur());
		}
	}
	
	private void selectRubrique(Rubrique rubrique) {
		cbxRubrique.setSelectedIndex(rubriquesExistantes.indexOf(rubrique));;
	}

	private void selectRedacteur(Redacteur redacteur) {
		cbxRedacteur.setSelectedIndex(redacteursExistants.indexOf(redacteur));;
	}
	
}
