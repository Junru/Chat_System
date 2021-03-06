import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;


public class IHMConnected extends JFrame implements Observer {

	private JPanel contentPane;
	private JTextField userName;
	private JTextField adressIP;
	private JTextField localPort;
	private JTextField statutText;
	private DefaultListModel lmName = new DefaultListModel();
	
	private List <UserRemote> display = new ArrayList <UserRemote> ();
	private JTextField statut;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IHMConnected frame = new IHMConnected("Huang", "127.0.0.1", "5000","statut", null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IHMConnected(String pseudo, String adress, String port, String statut,Controller controller) {
		
		//IHM component
		setTitle(pseudo + " : En ligne");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(368, 27, 300, 385);
		contentPane.add(scrollPane);
		
		//message bienvenue
		JOptionPane.showMessageDialog(null, "Hello "+pseudo+" Vous etes connecte");
		

		JList list = new JList();
		list.setModel(lmName);
		list.setCellRenderer(new Renderer());
		scrollPane.setViewportView(list);
		
		JLabel lblUtilisateurs = new JLabel("Utilisateurs");
		scrollPane.setColumnHeaderView(lblUtilisateurs);
		
		JButton btnChat = new JButton("Chat");
		btnChat.setBounds(39, 387, 97, 25);
		contentPane.add(btnChat);
		
		JButton btnLogOff = new JButton("Log off");
		btnLogOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deconnect(pseudo, Integer.parseInt(port));
			}
		});
		btnLogOff.setBounds(213, 387, 97, 25);
		contentPane.add(btnLogOff);
		
		JLabel lblUserName = new JLabel("User name :");
		lblUserName.setBounds(58, 50, 85, 16);
		contentPane.add(lblUserName);
		
		userName = new JTextField();
		userName.setEditable(false);
		userName.setBounds(169, 47, 116, 22);
		userName.setText(pseudo);
		contentPane.add(userName);
		userName.setColumns(10);
		
		JLabel lblIpAdress = new JLabel("IP Adress :");
		lblIpAdress.setBounds(58, 108, 74, 16);
		contentPane.add(lblIpAdress);
		
		adressIP = new JTextField();
		adressIP.setEditable(false);
		adressIP.setBounds(169, 105, 116, 22);
		adressIP.setText(adress);
		contentPane.add(adressIP);
		adressIP.setColumns(10);
		
		JLabel lblListeningPort = new JLabel("Listening port :");
		lblListeningPort.setBounds(58, 173, 97, 16);
		contentPane.add(lblListeningPort);
		
		localPort = new JTextField();
		localPort.setEditable(false);
		localPort.setBounds(169, 170, 116, 22);
		localPort.setText(port);
		contentPane.add(localPort);
		localPort.setColumns(10);
		
		JLabel lblStatut = new JLabel("Statut :");
		lblStatut.setBounds(58, 245, 56, 16);
		contentPane.add(lblStatut);
		
		statutText = new JTextField();
		statutText.setEditable(false);
		statutText.setBounds(169, 242, 116, 22);
		statutText.setText(statut);
		contentPane.add(statutText);
		statutText.setColumns(10);
		
		this.setVisible(true);
	}


	public void update(HashSet <UserRemote> newList) {
		this.display.removeAll(display);
		for (Iterator it = newList.iterator(); it.hasNext();) {
				//ImageIcon status = new ImageIcon("F:\\OM2M\\ChatSystem\\src\\status1.jpg");
				//status.setImage(status.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
				//this.display.add(new UserRemote(((UserRemote) it.next()).getName(), status));
				this.display.add((UserRemote) it.next());
		}
		
		
		lmName.removeAllElements();
		lmName.clear();
		for (UserRemote tmpName : display) {
			lmName.addElement(tmpName);
		}
		
	}
}
