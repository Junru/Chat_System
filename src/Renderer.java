import java.awt.Component;
import java.awt.Image;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


public class Renderer extends DefaultListCellRenderer implements
		ListCellRenderer<Object> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list,
			Object value, int index, boolean isSelected, boolean cellHasFocus) {
		
		UserRemote user = (UserRemote) value;
		String text = "<html>" + user.getName() + "<br/>" +user.getStatut()+"<html/>";
		setText(text);
		setIcon(user.getImageStatus());
		
		if(isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		}
		else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		
		setEnabled(true);
		setFont(list.getFont());
		
		return this;
		
		
		
		
	}

}
