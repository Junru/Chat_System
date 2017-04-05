import java.util.*;




public class Model extends Subject {

	private List <Observer> observers = new ArrayList <Observer> ();
	private HashSet <UserRemote> listUser;
	
	public Model() {
		this.listUser = new HashSet <UserRemote> ();
	}
	
	public void setUser(MessageUser user) {

			this.listUser.add(new UserRemote(user.getPseudo(), user.getIP(), user.getPort(), user.getEtat(), user.getStatut()));
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


	public HashSet <UserRemote> getListUser() {
		return listUser;
	}

	
	
	
	
}
