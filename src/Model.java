import java.util.*;




public class Model extends Subject {

	private List <Observer> observers = new ArrayList <Observer> ();
	
	private HashSet <MessageUser> listUser;
	
	public Model() {
		this.listUser = new HashSet <MessageUser> ();
	}
	
	public void setUser(MessageUser user) {

			this.listUser.add(user);
			//when user changed, observer IHM will change
			this.notifyObserver(this.listUser);
		
		//Confirm double same user message and update list
		//this.listUser = updateUser(listUser);	
	}
	
	/*private ArrayList <MessageUser> updateUser (ArrayList <MessageUser> oldList) {
		LinkedHashSet <MessageUser> set = new LinkedHashSet <MessageUser> (oldList);
		ArrayList <MessageUser> newList = new ArrayList <MessageUser> (set);
		return newList;
	}*/


	public HashSet <MessageUser> getListUser() {
		return listUser;
	}

	
	
	
	
}
