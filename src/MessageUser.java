import java.io.Serializable;
import java.net.InetAddress;


public class MessageUser implements Serializable {
	
	/**
	 * Le pseudo du user
	 */
	private  String pseudo;
	
	/**
	 * Statut de type : Content, Vive les vacances, J'aime le Botlan :-) 
	 */
	private String statut="";
	
	/**
	 * L'adresse IP du user
	 */
	private  InetAddress IP;
	
	/**
	 * Le port du user
	 */
	private  int port;

	public enum typeConnect {
		  CONNECTED,
		  DECONNECTED;
	}
	
	
	/**
	 * Enumeration qui indique l'état (Connecté ou deconnecté) du user
	 */
	private typeConnect etat; 
	
	/***
	 * 
	 * @param pseudo Pseudo que les autres utilisateurs verront
	 * @param iP L'adresse IP du user
	 * @param port Le port d'ecoute du user
	 * @param etat CONNECTION ou DECONNECTION
	 */
	public MessageUser(String pseudo, InetAddress iP, int port, typeConnect etat) {
		this.pseudo = pseudo;
		IP = iP;
		this.port = port;
		this.etat=etat;
	}

	
	public String getPseudo() {
		return this.pseudo;
	}


	public InetAddress getIP() {
		return IP;
	}



	public int getPort() {
		return port;
	}


	public typeConnect getEtat() {
		return etat;
	}


	public String getStatut() {
		return statut;
	}


	public void setStatut(String statut) {
		this.statut = statut;
	}


	public String toString() {
		return "MessageUser [pseudo=" + pseudo + ", statut=" + statut + ", IP="
				+ IP + ", port=" + port + ", etat=" + etat + "]";
	}
	
//Rewrite methode for compare message user with Pseudo, if the same pseudo, we consider the same user who send the message
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o.getClass() == MessageUser.class) {
			MessageUser n = (MessageUser)o;
			return n.pseudo.equals(pseudo);
		}
		return false;
	}
	
	public int hashCode() {
		return pseudo.hashCode();
	}
//-------------------------------------------------------------

}