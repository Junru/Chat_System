import java.io.*;
import java.net.*;



public class Controller {

	private InetAddress group;
	private int portGroup = 4000;
	private MulticastSocket multicastSocket;
	private Model model;
	private IHMLogin ihmLogin;
	private IHMConnected ihmConnected;
	private IHMConversation ihmConversation;
	private SendAliveSocket sendThread;
	private ReceiveAliveSocket receiveThread;
	
	public Controller (String host) throws UnknownHostException {
			this.group = InetAddress.getByName(host);
			this.ihmLogin = new IHMLogin(this);
			this.ihmLogin.setVisible(true);
	}
	public void connexion(String pseudo, int portLocal) {
		
		try {
			multicastSocket = new MulticastSocket(portGroup);
			multicastSocket.joinGroup(group);
			//multicastSocket.setLoopbackMode(false);
			//create model for receive message
			this.model = new Model();
			//local ip address
			InetAddress local = InetAddress.getLocalHost();
			//Construire le packet MessageUser à envoyer dans le groupe
			MessageUser hello;
			hello = new MessageUser(pseudo, local, portLocal, MessageUser.typeConnect.CONNECTED);
			//Cacher le fenetre de connexion
			ihmLogin.setVisible(false);
			
			//Ouvrir autre fenetre de IHM en connecté
			this.ihmConnected = new IHMConnected(pseudo, local.getHostAddress(), Integer.toString(portLocal), this);
			this.ihmConnected.setVisible(true);
			//Start thread for send alive User message to another group
			sendThread = new SendAliveSocket(hello, multicastSocket, group, portGroup);
			sendThread.start();
			
			//Start thread for receive alive User message from another group
			receiveThread = new ReceiveAliveSocket(model, multicastSocket);
			receiveThread.start();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
			if (multicastSocket != null) {
				try {
					multicastSocket.leaveGroup(group);
					multicastSocket.close();
					//stop sendThread
					sendThread.exit = true;
					sendThread.join();	
					//stop receiveThread
					receiveThread.exit = true;
					receiveThread.join();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				catch (InterruptedException e2) {
					e2.printStackTrace();
				}
				
			}
		} /*finally {
			if (multicastSocket != null) {
				try {
					multicastSocket.leaveGroup(group);
					multicastSocket.close();
					//stop sendThread
					sendThread.exit = true;
					sendThread.join();	
					//stop receiveThread
					receiveThread.exit = true;
					receiveThread.join();
				} catch (IOException e) {
					e.printStackTrace();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}*/
		
	}
	
	public void deconnect(String pseudo, int portLocal) {
		
		InetAddress local = multicastSocket.getInetAddress();
		MessageUser bye = new MessageUser(pseudo, local, portLocal, MessageUser.typeConnect.DECONNECTED);
		sendThread.setUser(bye);
		try {
			//stop sendThread
			sendThread.exit = true;
			sendThread.join();
			//stop receiveThread
			receiveThread.exit = true;
			receiveThread.join();
		}
		catch (InterruptedException e1) {
			e1.printStackTrace();
		}	
		if (this.multicastSocket != null) {
			try {
				multicastSocket.leaveGroup(group);
				multicastSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.exit(0);
	}
	
}
