package modele;

import java.util.ArrayList;
import java.util.TreeMap;

import bdd.dao.DAO;
import metier.Entity;
import observer.Observable;
import observer.Observer;

public abstract class ModeleGenerique <T extends Entity> implements Observable<T> {
	
	protected TreeMap<Integer, T> donneesActuelles;
	
	protected DAO<T> dao;
	
	protected ArrayList<Observer<T>> listObservers = new ArrayList<Observer<T>>();
	
	public void chargerDonnees() {
		donneesActuelles = dao.findAll();
	}
	
	public int ajouterElement(T element) {
		int res = 0;
		
		res = dao.create(element);
		
		element.setId(dao.obtenirDernierIdGenere());
		donneesActuelles.put(element.getId(), element);
		
		return res;
	}
	
	public int modifierElement(T element) {
		int res = 0;
		
		res = dao.update(element);
		
		return res;
	}
	
	public int supprimerElement(T element) {
		int res = 0;
		
		donneesActuelles.remove(element.getId());
		res = dao.delete(element);
		
		return res;
	}
	
	public int toutSupprimer() {
		int res = 0;
		
		donneesActuelles.clear();
		notifyObserver(new ArrayList<T>(donneesActuelles.values()));
		res = dao.deleteAll();
		
		return res;
	}
	
	public TreeMap<Integer, T> getDonneesActuelles() {
		return donneesActuelles;
	}
	
	@Override
	public void addObserver(Observer<T> observer) {
		listObservers.add(observer);
	}
	
	@Override
	public void notifyObserver(ArrayList<T> donnees) {
		donnees.sort(null);
		
		for (Observer<T> observer: listObservers) {
			observer.update(donnees);
		}
	}
	
	@Override
	public void removeObserver() {
		listObservers = new ArrayList<Observer<T>>();
	}

}
