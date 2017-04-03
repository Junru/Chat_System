import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class IHMLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textName;
	private JTextField textPort;
	private Controller controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IHMLogin frame = new IHMLogin(null);
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
	public IHMLogin(Controller controller) {
		this.controller = controller;
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 296, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton logon = new JButton("Log on");
		logon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.connexion(textName.getText(), Integer.parseInt(textPort.getText()));
			}
		});
		logon.setBounds(79, 347, 97, 25);
		contentPane.add(logon);
		
		JLabel nickName = new JLabel("Pseudo :");
		nickName.setBounds(33, 32, 56, 16);
		contentPane.add(nickName);
		
		JLabel port = new JLabel("Port :");
		port.setBounds(33, 80, 56, 16);
		contentPane.add(port);
		
		textName = new JTextField();
		textName.setBounds(113, 29, 116, 22);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textPort = new JTextField();
		textPort.setBounds(113, 77, 116, 22);
		contentPane.add(textPort);
		textPort.setColumns(10);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(181, 347, 97, 25);
		contentPane.add(btnQuitter);
	}

}
