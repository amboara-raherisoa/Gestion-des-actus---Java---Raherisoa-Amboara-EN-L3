package observer;

import java.util.ArrayList;

public interface Observable<T> {
	
	public void addObserver(Observer<T> observer);
	public void removeObserver();
	public void notifyObserver(ArrayList<T> donnees);
	
}
