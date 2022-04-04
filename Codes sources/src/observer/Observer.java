package observer;

import java.util.ArrayList;

public interface Observer<T> {
	
	public void update(ArrayList<T> donnees);

}
