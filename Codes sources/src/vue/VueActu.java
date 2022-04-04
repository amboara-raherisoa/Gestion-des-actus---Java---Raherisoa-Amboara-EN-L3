package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controleur.CtrlActu;
import metier.Actu;
import metier.Rubrique;
import observer.Observer;

public class VueActu extends JPanel implements Observer<Actu> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String DESCRIPTION = "Brève description";
	public static final String CONTENU = "Contenu";
	
	private JLabel labelComboRubriques = new JLabel("Rubrique");
	private JComboBox<Rubrique> cbxRubriques = new JComboBox<>();
	
	private JLabel labelListActus = new JLabel("Liste des actualités");
	private JList<Actu> listActus = new JList<>();
	private DefaultListModel<Actu> listModelParDefaut;
	private DefaultListModel<Actu> listModelSpecifique;
	
	private JButton btnAjouter = new JButton("Ajouter");
	private JButton btnModifier = new JButton("Modifier infos");
	private JButton btnDescription = new JButton(DESCRIPTION);
	private JButton btnContenu = new JButton(CONTENU);
	private JButton btnSupprimer = new JButton("Supprimer");
	
	private JLabel labelText = new JLabel("Informations");
	private JTextArea texteInfos = new JTextArea("Aucun élément sélectionné");
	
	private JScrollPane scrollActus = new JScrollPane(listActus);
	
	private Dimension dimBlocs = new Dimension(640, 220);
	
	private CtrlActu ctrlActu;
	
	public VueActu() {
		configurerComposants();
		agencerComposants();
		specifierEcouteurs();
	}
	
	public void setCtrlActu(CtrlActu ctrlActu) {
		this.ctrlActu = ctrlActu;
	}
	
	private void configurerComposants() {
		cbxRubriques.setPreferredSize(new Dimension(500, 30));
		
		listActus.setPreferredSize(dimBlocs);
		
		texteInfos.setPreferredSize(dimBlocs);
		texteInfos.setEditable(false);
		
		listActus.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	private void agencerComposants() {
		JPanel cePanneau = this;
		
		JPanel panelHaut = new JPanel();
		JPanel panelPpal = new JPanel();
		
		JPanel panelActus = new JPanel();
		JPanel panelInfos = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Insets getInsets() { // Redéfinition des marges
				Insets normal = super.getInsets();
				return new Insets(normal.top, normal.left + 20, normal.bottom + 20, normal.right + 20);
			}
		};

		JPanel panelList = new JPanel();
		JPanel panelBoutons = new JPanel();
		
		
		Dimension dimPanels = new Dimension(680, 270);
		
		cePanneau.setLayout(new BorderLayout(0, 5));
		cePanneau.add(panelHaut, BorderLayout.NORTH);
		cePanneau.add(panelPpal, BorderLayout.CENTER);
		
		panelHaut.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		panelHaut.add(labelComboRubriques);
		panelHaut.add(cbxRubriques);
		
		panelPpal.setLayout(new GridLayout(2, 1, 0, 5));
		panelPpal.add(panelActus);
		panelPpal.add(panelInfos);
		
		panelActus.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		panelActus.add(panelList);
		panelActus.add(panelBoutons);
		
		panelList.setPreferredSize(dimPanels);
		panelList.setLayout(new BorderLayout(10, 10));
		panelList.add(labelListActus, BorderLayout.NORTH);
		panelList.add(scrollActus, BorderLayout.CENTER);
		
		panelBoutons.setLayout(new GridLayout(5, 1, 0, 10));
		panelBoutons.add(btnAjouter);
		panelBoutons.add(btnModifier);
		panelBoutons.add(btnDescription);
		panelBoutons.add(btnContenu);
		panelBoutons.add(btnSupprimer);
		
		panelInfos.setPreferredSize(dimPanels);
		panelInfos.setLayout(new BorderLayout(10, 10));
		panelInfos.add(labelText, BorderLayout.NORTH);
		panelInfos.add(texteInfos, BorderLayout.CENTER);
		
	}

	private void specifierEcouteurs() {
		btnAjouter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrlActu.afficherFormulaireAjout();
			}
		});
		
		btnModifier.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrlActu.afficherFormulaireModifInfos(listActus.getSelectedValue());
			}
		});
		
		btnDescription.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrlActu.afficherEditeur(listActus.getSelectedValue(), DESCRIPTION);
			}
		});
		
		btnContenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrlActu.afficherEditeur(listActus.getSelectedValue(), CONTENU);
			}
		});
		
		btnSupprimer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrlActu.supprimerUneActu(listActus.getSelectedValue());
			}
		});
		
		cbxRubriques.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = cbxRubriques.getSelectedIndex();
				
				if (index == 0) { // "Toutes les rubriques"
					listActus.setModel(listModelParDefaut);
				} else if (index > 0) {
					Rubrique rubriqueSelectionnee = cbxRubriques.getModel().getElementAt(index);
					
					listModelSpecifique = new DefaultListModel<Actu>();
					
					for (Actu actu: rubriqueSelectionnee.getActusConcernees()) {
						listModelSpecifique.addElement(actu);
					}
					listActus.setModel(listModelSpecifique);
				}
				
				texteInfos.setText("Aucun élément sélectionné");
			}
		});
		
		listActus.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Actu actuSelectionnee = listActus.getSelectedValue();
				
				if (actuSelectionnee != null) {
					String[] infosActu = actuSelectionnee.toString().split("-");
					String titre = actuSelectionnee.getTitre();
					String titreRaccourci = (titre.length() > 100) ? (titre.substring(0, 100) + "...") : titre;
					
					String affichage = "Thème : " + actuSelectionnee.getTheme() + "\n\n"
							+ "Titre : " + titreRaccourci + "\n\n"
							+ "Date de publication : " + infosActu[0] + "\n\n"
							+ "Rubrique : " 
							+ ((actuSelectionnee.getRubrique() != null)
									? actuSelectionnee.getRubrique().toString()
									: " - ") + "\n\n"
							+ "Rédacteur : " 
							+ ((actuSelectionnee.getRedacteur() != null)
									? actuSelectionnee.getRedacteur().toString()
									: " - ") + "\n\n\n"
							+ "(Cliquer sur \"Brève description\" et \"Contenu\" "
							+ "pour voir et modifier les informations correspondantes)";
					
					texteInfos.setText(affichage);
				}
			}
		});
	}
	
	@Override
	public void update(ArrayList<Actu> donnees) {
		DefaultComboBoxModel<Rubrique> cbxModel = new DefaultComboBoxModel<Rubrique>();
		Set<Rubrique> rubriquesDispo = new TreeSet<Rubrique>();

		listModelParDefaut = new DefaultListModel<Actu>();
		
		for (Actu actu: donnees) {
			listModelParDefaut.addElement(actu);
			if (actu.getRubrique() != null) {				
				rubriquesDispo.add(actu.getRubrique());
			}
		}
		
		listActus.setModel(listModelParDefaut);
		
		cbxModel = new DefaultComboBoxModel<Rubrique>();
		cbxModel.addElement(new Rubrique("Toutes les rubriques"));
		for (Rubrique rubrique : rubriquesDispo) {
			cbxModel.addElement(rubrique);
		}
		cbxRubriques.setModel(cbxModel);
		
		texteInfos.setText("Aucun élément sélectionné");
	}

}
