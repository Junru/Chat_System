import java.awt.Image;
import java.net.InetAddress;

import javax.swing.Icon;
import javax.swing.ImageIcon;




public class UserRemote {
	
	private String name;
	private String statut="";
	private  InetAddress ip;
	private  int port;
	private MessageUser.typeConnect etat;
	
	private Icon imageStatus;
	
	
	public UserRemote(String name, InetAddress ip,  int port, MessageUser.typeConnect typeConnect, String statut) {
		this.name = name;
		this.ip = ip;
		this.port = port;
		this.etat = typeConnect;
		this.statut = statut;
		//image of status
		ImageIcon status = new ImageIcon("F:\\OM2M\\ChatSystem\\src\\status1.jpg");
		status.setImage(status.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		this.imageStatus = status;
	}
	

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public InetAddress getIp() {
		return ip;
	}

	public void setIp(InetAddress ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public MessageUser.typeConnect getEtat() {
		return etat;
	}

	public void setEtat(MessageUser.typeConnect etat) {
		this.etat = etat;
	}

	public Icon getImageStatus() {
		return imageStatus;
	}

	public void setImageStatus(Icon imageStatus) {
		this.imageStatus = imageStatus;
	}

	@Override
	public String toString() {
		return "UserRemote [name=" + name + ", statut=" + statut + ", ip=" + ip
				+ ", port=" + port + ", etat=" + etat + "]";
	}

		//Rewrite methode for compare message user with Pseudo, if the same pseudo, we consider the same user who send the message
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o.getClass() == UserRemote.class) {
				UserRemote n = (UserRemote)o;
				return n.name.equals(name);
			}
			return false;
		}
		
		public int hashCode() {
			return name.hashCode();
		}
	//-------------------------------------------------------------


}
