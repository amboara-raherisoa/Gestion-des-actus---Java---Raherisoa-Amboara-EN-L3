package vue;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controleur.CtrlActu;
import controleur.CtrlRedacteur;
import controleur.CtrlReset;
import controleur.CtrlRubrique;

public class Vue extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Vue cetteFenetre = this;
	
	// Menu
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu jmFichier = new JMenu("Fichier");
	private JMenuItem jmiReinitialiser = new JMenuItem("Réinitialiser les données");
	private JMenuItem jmiQuitter = new JMenuItem("Quitter");
	
	private JMenu jmAide = new JMenu("Aide");
	private JMenuItem jmiAPropos = new JMenuItem("A propos");
	
	// Onglets
	private JTabbedPane tabbedPane = new JTabbedPane();
	
	private VueRubrique vueRubrique = new VueRubrique();
	private VueRedacteur vueRedacteur = new VueRedacteur();
	private VueActu vueActu = new VueActu();
	
	private CtrlReset ctrlReset;
	
	public Vue(CtrlRubrique ctrlRubrique, CtrlRedacteur ctrlRedacteur, CtrlActu ctrlActu, CtrlReset ctrlReset) {
		this.setTitle("Gestion d'actualités");
		this.setSize(1024, 768);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.ctrlReset = ctrlReset;
		
		vueRubrique.setCtrlRubrique(ctrlRubrique);
		vueRedacteur.setCtrlRedacteur(ctrlRedacteur);
		vueActu.setCtrlActu(ctrlActu);
		
		initialiserComposants();
		organiserMenu();
		ajouterComposants();
		specifierEcouteurs();
		
		this.pack();
	}
	
	public VueRubrique getVueRubrique() {
		return vueRubrique;
	}
	
	public VueRedacteur getVueRedacteur() {
		return vueRedacteur;
	}
	
	public VueActu getVueActu() {
		return vueActu;
	}
	
	private void initialiserComposants() {
		JPanel panelRubEtRedac = new JPanel();
		
		panelRubEtRedac.setLayout(new GridLayout(2, 1));
		panelRubEtRedac.add(vueRubrique);
		panelRubEtRedac.add(vueRedacteur);
		
		tabbedPane.addTab("Actualités", vueActu);
		tabbedPane.addTab("Rubriques et Rédacteurs", panelRubEtRedac);
	}
	
	private void organiserMenu() {
		this.setJMenuBar(menuBar);
		
		menuBar.add(jmFichier);
		jmFichier.add(jmiReinitialiser);
		jmFichier.addSeparator();
		jmFichier.add(jmiQuitter);
		
		menuBar.add(jmAide);
		jmAide.add(jmiAPropos);
	}
	
	private void ajouterComposants() {
		Container contentPane = this.getContentPane();
		
		contentPane.add(tabbedPane);
	}
	
	private void specifierEcouteurs() {
		
		// Quitter
		jmiQuitter.addActionListener(new ActionListener() {
			
			private String[] options = { "Quitter", "Annuler" };
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int rangOption = JOptionPane.showOptionDialog(cetteFenetre, "Quitter le programme ?",
						"Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						options, options[1]);
				
				if (rangOption == 0) {
					System.exit(0);
				}
			}
			
		});
		
		// A propos
		jmiAPropos.addActionListener(new ActionListener() {
			
			private String texte = "Gestion d'actualités\n\n"
					+ "Ce programme a été réalisé\n"
					+ "par RAHERISOA Amboara Mendrika (EN L3)\n";
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(cetteFenetre, texte, "A propos", JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		
		// Reset
		jmiReinitialiser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrlReset.reinitialiser();
			}
		});
	}

}
