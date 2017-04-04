import java.util.*;


public abstract class Subject {
	
	private List <Observer> observers = new ArrayList <Observer> ();
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}
	
	public void removeObserver(Observer observer) {
		int index = observers.indexOf(observer);
		if (index >= 0) 
			observers.remove(observer);
	}
	public void notifyObserver(HashSet <MessageUser> newList) {
		for (Observer observer : observers) {
			observer.update(newList);
		}
	}
}
