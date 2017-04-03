import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;




public class SendAliveSocket extends Thread {
	private MessageUser user;
	private MulticastSocket multicastSocket;
	private InetAddress group;
	private int port ;
	//Flag of thread for stop
	public volatile boolean exit = false;
	
	public SendAliveSocket(MessageUser hello, MulticastSocket mcs, InetAddress group, int port) {
		user = hello;
		multicastSocket = mcs;
		this.group = group;
		this.port = port;
	}
	
	public void run() {
		while (!exit) {
			keepalive();
			try {
				sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void keepalive(){
			try {
				//Encapsuler le MessageUser dans le datagramPacket pour envoyer
				//Objet -> ObjectOutputStream -> ByteArrayOutpoutStream -> DatagramPacket 
				//-> Envoie sur le réseau
				ByteArrayOutputStream byteStream = new ByteArrayOutputStream(5000);
				ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
				os.flush();
				os.writeObject(user);
				os.flush();
				byte[] sendBuf = byteStream.toByteArray();
				DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, group, port);
				multicastSocket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}

	public void setUser(MessageUser user) {
		this.user = user;
	}
	
}
