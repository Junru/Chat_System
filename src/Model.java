import java.util.*;




public class Model {

	private HashSet <MessageUser> listUser;
	
	public Model() {
		this.listUser = new HashSet <MessageUser> ();
	}
	
	public void setUser(MessageUser user) {

			this.listUser.add(user);
		
		
		//Confirm double same user message and update list
		//this.listUser = updateUser(listUser);	
	}
	
	private ArrayList <MessageUser> updateUser (ArrayList <MessageUser> oldList) {
		LinkedHashSet <MessageUser> set = new LinkedHashSet <MessageUser> (oldList);
		ArrayList <MessageUser> newList = new ArrayList <MessageUser> (set);
		return newList;
	}

	public HashSet <MessageUser> getListUser() {
		return listUser;
	}
	
	
}
