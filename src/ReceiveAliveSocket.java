import java.io.*;
import java.net.*;
import java.util.Iterator;




public class ReceiveAliveSocket extends Thread {

	private Model model;
	private MulticastSocket multicastSocket;
	//Flag of thread for stop
	public volatile boolean exit = false;
	public static int i=0;
	
	public ReceiveAliveSocket(Model model, MulticastSocket mcs) {
		this.model = model;
		this.multicastSocket = mcs;
	}
	
	public void run() {
		while(!exit) {
			MessageUser recvMessage = receiveAlive();
			if (recvMessage != null) {
				model.setUser(recvMessage);
				i++;
				System.out.println(i);
				for(Iterator <MessageUser> it = model.getListUser().iterator(); it.hasNext(); ){
					System.out.println(it.next());
				}	
			}
		}
	}

	private MessageUser receiveAlive() {
		try {
			byte[] recvBuf = new byte[5000];
			DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
			//receive packet User message from another system
			multicastSocket.receive(packet);
			
			
			//decapsuler le packet recu vers MessageUser 
			ByteArrayInputStream byteStream = new ByteArrayInputStream(recvBuf);
			ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(byteStream));
			MessageUser received = (MessageUser) is.readObject();
			is.close();
			return(received);
			
		} catch (IOException e) {
			e.printStackTrace();
			return(null);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return(null);
		}
	}
}
