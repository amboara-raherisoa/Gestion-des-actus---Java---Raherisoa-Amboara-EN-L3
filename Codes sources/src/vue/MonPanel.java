package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import observer.Observer;

public abstract class MonPanel<T> extends JPanel implements Observer<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected JLabel label = new JLabel();
	protected JList<T> list = new JList<>();
	
	protected JButton btnAjouter = new JButton("Ajouter");
	protected JButton btnModifier = new JButton("Modifier");
	protected JButton btnSupprimer = new JButton("Supprimer");
	
	public MonPanel() {
		configurerComposants();
		agencerComposants();
		specifierEcouteurs();
	}
	
	private void configurerComposants() {
		Dimension d = new Dimension(120, 25);
		
		btnAjouter.setPreferredSize(d);
		btnModifier.setPreferredSize(d);
		btnSupprimer.setPreferredSize(d);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void agencerComposants() {
		JPanel cePanneau = this;
		
		JPanel panelList = new JPanel();
		
		JPanel panelBoutons = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Insets getInsets() { // Redéfinition des marges
				Insets normal = super.getInsets();
				return new Insets(normal.top + 50, normal.left, normal.bottom, normal.right);
			}
		};
		
		cePanneau.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		cePanneau.add(panelList);
		cePanneau.add(panelBoutons);
		
		panelList.setPreferredSize(new Dimension(360, 250));
		panelList.setLayout(new BorderLayout(10, 10));
		panelList.add(label, BorderLayout.NORTH);
		panelList.add(new JScrollPane(list), BorderLayout.CENTER);
		
		panelBoutons.setPreferredSize(new Dimension(150, 250));
		panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
		panelBoutons.add(btnAjouter);
		panelBoutons.add(btnModifier);
		panelBoutons.add(btnSupprimer);
		
	}
	
	protected abstract void specifierEcouteurs();
	
	@Override
	public void update(ArrayList<T> donnees) {
		DefaultListModel<T> listModel = new DefaultListModel<T>();
		
		for (T element: donnees) {
			listModel.addElement(element);
		}
		
		list.setModel(listModel);
		
	}
	
}
