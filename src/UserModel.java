import java.util.*;

import javax.swing.AbstractListModel;


public class UserModel extends AbstractListModel {

	private ArrayList <MessageUser> list;
	
	public UserModel () {
		this.list = new ArrayList <MessageUser> ();
	}
	@Override
	public int getSize() {
		return list.size();
	}

	@Override
	public Object getElementAt(int index) {
		return list.get(index);
	}
	
	public void addElement(Object element) {
		list.add((MessageUser) element);
		fireContentsChanged(this, 0, getSize());
	}
	public ArrayList<MessageUser> getList() {
		return list;
	}
	

}
