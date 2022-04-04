package vue.formulaire;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controleur.CtrlActu;
import metier.Actu;
import vue.VueActu;

public class EditeurActu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EditeurActu cetteFenetre = this;
	
	private String titreEditeur;
	
	private JEditorPane editorPane = new JEditorPane();
	
	private JButton btnSauver = new JButton("Sauvegarder");
	private JButton btnAnnuler = new JButton("Annuler");
	
	private Actu actuCourante;
	private String texte;
	
	private CtrlActu ctrlActu;
	
	public EditeurActu(Actu actuCourante, String titreEditeur, CtrlActu ctrlActu) {
		super(titreEditeur);
		
		this.actuCourante = actuCourante;
		this.titreEditeur = titreEditeur;
		this.ctrlActu =  ctrlActu;
		
		if (titreEditeur.equals(VueActu.DESCRIPTION)) {
			texte = actuCourante.getBreveDescription();
		} else if (titreEditeur.equals(VueActu.CONTENU)) {
			texte = actuCourante.getContenu();
		}
		
		this.setSize(600, 360);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		initialiserComposants(texte);
		agencerComposants();
		specifierEcouteurs();
		
		this.setVisible(true);
	}

	private void initialiserComposants(String texte) {
		editorPane.setContentType("text/plain");
		editorPane.setText(texte);
	}
	
	private void agencerComposants() {
		Container contentPane = this.getContentPane();
		
		JPanel panelBas = new JPanel();
		
		contentPane.setLayout(new BorderLayout(10, 0));
		contentPane.add(new JScrollPane(editorPane), BorderLayout.CENTER);
		contentPane.add(panelBas, BorderLayout.SOUTH);
		
		panelBas.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		panelBas.add(btnSauver);
		panelBas.add(btnAnnuler);
	}
	
	private void specifierEcouteurs() {
		btnSauver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (titreEditeur.equals(VueActu.DESCRIPTION)) {
					actuCourante.setBreveDescription(editorPane.getText());
				} else if (titreEditeur.equals(VueActu.CONTENU)) {
					actuCourante.setContenu(editorPane.getText());
				}
				
				ctrlActu.sauverEdition(actuCourante);
				cetteFenetre.dispose();
			}
		});
		
		btnAnnuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cetteFenetre.dispose();
			}
		});
	}
}
