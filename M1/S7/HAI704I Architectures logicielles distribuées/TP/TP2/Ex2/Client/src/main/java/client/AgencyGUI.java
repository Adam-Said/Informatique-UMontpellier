package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import webservice.Client;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;

public class AgencyGUI extends JFrame {

	private JPanel contentPane;
	JTextField usernameInput;
	JPasswordField passwordInput;
	private JTextField credentialAlert;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgencyGUI frame = new AgencyGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public AgencyGUI() throws IOException {
		setTitle("Hotel Finder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JComboBox agencySelector = new JComboBox();
		agencySelector.setModel(new DefaultComboBoxModel(new String[] {"HotelAdvisor.com", "Hotel.net", "DuoVago"}));
		agencySelector.setMaximumRowCount(3);
		agencySelector.setBounds(215, 112, 167, 27);
		contentPane.add(agencySelector);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setForeground(Color.white);
		usernameLabel.setBounds(133, 162, 91, 16);
		contentPane.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.white);
		passwordLabel.setBounds(133, 209, 91, 16);
		contentPane.add(passwordLabel);
		
		JLabel loginLabel = new JLabel("Login Page");
		loginLabel.setForeground(Color.white);
		loginLabel.setBackground(new Color(255, 255, 255));
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setFont(new Font("KufiStandardGK", Font.BOLD, 20));
		loginLabel.setBounds(190, 58, 181, 29);
		contentPane.add(loginLabel);
		
		usernameInput = new JTextField();
		usernameInput.setBounds(215, 156, 167, 29);
		contentPane.add(usernameInput);
		usernameInput.setColumns(10);
		
		passwordInput = new JPasswordField();
		passwordInput.setBackground(new Color(255, 255, 255));
		passwordInput.setBounds(215, 201, 167, 29);
		contentPane.add(passwordInput);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String loginUsername = usernameInput.getText();
				String loginPassword = String.valueOf(passwordInput.getPassword());
				String agencyId = "";
				agencyId = (String)agencySelector.getSelectedItem();
				Agency agency = null;
				if(agencyId.equals("HotelAdvisor.com")) {
					agency = MainFunctions.MakeAgence(1);
				} else if (agencyId.equals("Hotel.net")) {
					agency = MainFunctions.MakeAgence(2);
				} else {
					agency = MainFunctions.MakeAgence(3);
				}
				Client client = new Client();
				client = MainFunctions.connectClient(loginUsername, loginPassword, agency);
				
				if(client == null) {
					credentialAlert.setText("Wrong credentials, unable to connect");
				} else {
			        connectedUser connectedUser = null;
					try {connectedUser = new connectedUser();} catch (MalformedURLException e1) {e1.printStackTrace();} catch (IOException e1) {e1.printStackTrace();}
			        
					if(agencyId.equals("HotelAdvisor.com")) {
						connectedUser.agencyDisplay.setText("HotelAdvisor.com");
					} else if (agencyId.equals("Hotel.net")) {
						connectedUser.agencyDisplay.setText("Hotel.net");
					} else {
						connectedUser.agencyDisplay.setText("DuoVago");
					}
			        connectedUser.nameInput.setText(client.getFirstname() + " " + client.getName());
			        connectedUser.agencyInput.setText(agencyId);
			        connectedUser.loginUser.setText(loginUsername);
			        connectedUser.passwordUser.setText(loginPassword);
			        connectedUser.setVisible(true);
				}
			}
		});
		loginBtn.setBackground(new Color(243, 254, 255));
		loginBtn.setBounds(229, 271, 117, 29);
		contentPane.add(loginBtn);
		
		credentialAlert = new JTextField();
		credentialAlert.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		credentialAlert.setHorizontalAlignment(SwingConstants.CENTER);
		credentialAlert.setBackground(new java.awt.Color(0, 0, 0, 1));
		credentialAlert.setForeground(Color.red);
		credentialAlert.setEditable(false);
		credentialAlert.setBounds(133, 242, 318, 26);
		contentPane.add(credentialAlert);
		credentialAlert.setColumns(10);
		
		JLabel agencyLabel = new JLabel("Agency");
		agencyLabel.setForeground(Color.WHITE);
		agencyLabel.setBounds(133, 116, 91, 16);
		contentPane.add(agencyLabel);
		
		JButton exitBtn = new JButton("Exit");
		exitBtn.setForeground(new Color(255, 0, 0));
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitBtn.setBackground(new Color(243, 254, 255));
		exitBtn.setBounds(482, 338, 75, 29);
		contentPane.add(exitBtn);
		
		JButton registerBtn = new JButton("Create an account");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAccount create = new createAccount();
				create.setVisible(true);
			}
		});
		registerBtn.setForeground(new Color(0, 0, 0));
		registerBtn.setBackground(new Color(243, 254, 255));
		registerBtn.setBounds(23, 338, 157, 29);
		contentPane.add(registerBtn);
		
		JLabel backgroundImage = new JLabel("");
		BufferedImage img = ImageIO.read(new URL("http://hotelfinder.sc1samo7154.universe.wf/blurImage_563x373.jpeg"));
		backgroundImage.setIcon(new ImageIcon(img));
		backgroundImage.setBounds(6, 6, 563, 373);
		contentPane.add(backgroundImage);
	}
}
