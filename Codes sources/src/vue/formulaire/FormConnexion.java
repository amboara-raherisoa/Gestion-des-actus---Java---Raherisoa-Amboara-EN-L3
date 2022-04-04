package vue.formulaire;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bdd.connexion.InfosConnexion;

public class FormConnexion extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel labelNomApp = new JLabel("Gestion d'actualités");
	
	private JLabel labelUser = new JLabel("Utilisateur");
	private JTextField champUser = new JTextField();
	
	private JLabel labelMdp = new JLabel("Mot de passe");
	private JPasswordField champMdp = new JPasswordField();
	
	private JButton btnSeConnecter = new JButton("Se connecter");
	
	private InfosConnexion donnee;
	
	private boolean envoyerDonnee = false;
	
	public FormConnexion(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		
		this.setSize(390,200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		initialiserComposants();
		agencerComposants();
		specifierEcouteurs();
		
	}
	
	private void initialiserComposants() {	
		champUser.setPreferredSize(new Dimension(200, 30));
		champMdp.setPreferredSize(new Dimension(200, 30));
	}
	
	private void agencerComposants() {
		Container contentPane = this.getContentPane();
		
		JPanel panelHaut = new JPanel();
		JPanel panelCentre = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Insets getInsets() { // Redéfinition des marges
				Insets normal = super.getInsets();
				return new Insets(normal.top + 5, normal.left + 10, normal.bottom,  normal.right + 10);
			}
		};
		JPanel panelBas = new JPanel();
		
		contentPane.setLayout(new BorderLayout(0, 10));
		contentPane.add(panelHaut, BorderLayout.NORTH);
		contentPane.add(panelCentre, BorderLayout.CENTER);
		contentPane.add(panelBas, BorderLayout.SOUTH);
		
		panelHaut.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
		panelHaut.add(labelNomApp);
		
		panelCentre.setLayout(new GridLayout(2, 2, 10, 10));
		panelCentre.add(labelUser);
		panelCentre.add(champUser);
		panelCentre.add(labelMdp);
		panelCentre.add(champMdp);
		
		panelBas.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
		panelBas.add(btnSeConnecter);
	}
	
	private void specifierEcouteurs() {
		btnSeConnecter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String user = champUser.getText();
				String mdp = String.valueOf(champMdp.getPassword());
				
				donnee = new InfosConnexion(user, mdp);
				envoyerDonnee = true;
				setVisible(false);
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
	}
	
	public InfosConnexion showForm() {
		InfosConnexion res = null;
		
		this.setVisible(true);
		
		if (envoyerDonnee) {
			res = donnee;
		}
		
		return res;
	}

}
