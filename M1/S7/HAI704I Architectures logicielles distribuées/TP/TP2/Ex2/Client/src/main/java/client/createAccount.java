package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import webservice.CreditCard;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class createAccount extends JFrame {

	private JPanel contentPane;
	private JTextField passwordInput;
	private JTextField userNameInput;
	private JTextField phoneNumberInput;
	private JTextField errorMessageInput;
	private JTextField ageInput;
	private JTextField cardNumber1;
	private JTextField cardNumber2;
	private JTextField cardNumber3;
	private JTextField cardNumber4;
	private JTextField cvcInput;
	private JTextField expirationDateInput;
	private JLabel agencyChoiceLabel;
	private JLabel adressLabel;
	private JTextField adressInput;
	private JLabel nameLabel;
	private JLabel firstNameLabel;
	private JTextField nameInput;
	private JTextField firstNameInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createAccount frame = new createAccount();
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
	public createAccount() {
		setTitle("Create an Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(35, 29, 106, 16);
		contentPane.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(35, 57, 106, 16);
		contentPane.add(passwordLabel);
		
		passwordInput = new JTextField();
		passwordInput.setBounds(119, 52, 130, 26);
		contentPane.add(passwordInput);
		passwordInput.setColumns(10);
		
		userNameInput = new JTextField();
		userNameInput.setColumns(10);
		userNameInput.setBounds(119, 24, 130, 26);
		contentPane.add(userNameInput);
		
		JLabel phoneNumberLabel = new JLabel("Phone Number");
		phoneNumberLabel.setBounds(35, 85, 167, 16);
		contentPane.add(phoneNumberLabel);
		
		JLabel ageInputLabel = new JLabel("Age");
		ageInputLabel.setBounds(35, 113, 106, 16);
		contentPane.add(ageInputLabel);
		
		phoneNumberInput = new JTextField();
		phoneNumberInput.setColumns(10);
		phoneNumberInput.setBounds(139, 80, 130, 26);
		contentPane.add(phoneNumberInput);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"HotelAdvisor", "Hotel.net", "DuoVago"}));
		comboBox.setBounds(296, 53, 133, 27);
		contentPane.add(comboBox);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(adressInput.getText().length()==0 || firstNameInput.getText().length()==0 || nameInput.getText().length()==0 || userNameInput.getText().length()==0 || passwordInput.getText().length()==0 || phoneNumberInput.getText().length()==0 || 
						ageInput.getText().length()==0) {
					errorMessageInput.setText("Missing informations in user fields");
				}
				if(cardNumber1.getText().length()==0 || cardNumber2.getText().length()==0 || cardNumber3.getText().length()==0 || 
						cardNumber4.getText().length()==0 || cvcInput.getText().length() == 0 || expirationDateInput.getText().length() == 0) {
					errorMessageInput.setText("Missing informations in credit card fields");
				}
				
				if(firstNameInput.getText().length()!=0 && adressInput.getText().length()!=0 && nameInput.getText().length()!=0 && userNameInput.getText().length()!=0 && passwordInput.getText().length()!=0 && phoneNumberInput.getText().length()!=0 && 
						ageInput.getText().length()!=0 && cardNumber1.getText().length()==4 && cardNumber2.getText().length()==4 && cardNumber3.getText().length()==4 && 
						cardNumber4.getText().length()==4 && cvcInput.getText().length()==3 && expirationDateInput.getText().length()==10) {
					
					try {
						Class.forName("com.mysql.jdbc.Driver");
                        Connection con=DriverManager.getConnection(
                        "jdbc:mysql://dakota.o2switch.net:3306/sc1samo7154_hotelfinderdb", "sc1samo7154_hotelupdate","hotelupdate34");
                                java.sql.Statement stmt=con.createStatement();
                                java.sql.PreparedStatement preparedStmt = con.prepareStatement(
                                        "INSERT INTO Client (ID, Name, FirstName, Login, Password, Age, Adress, Tel, CreditCard) "
                                + "VALUES (NULL, '" + nameInput.getText() + "', '" + firstNameInput.getText() + "', '"+ userNameInput.getText() +"', '"+ passwordInput.getText() + "', '"
                                        		+ ageInput.getText()+ "', " + "NULL" + ", '" + phoneNumberInput.getText() + "', NULL" + ")"
                          ); // A FINIR
                          preparedStmt.execute();
                          
                          ResultSet rs = stmt.executeQuery("SELECT ID FROM Client WHERE "
									+ "Name='"+ nameInput.getText() +"' AND Firstname='" + firstNameInput.getText() +"'");
							int clientID = 0; 
							if(rs.next()) {
								clientID = rs.getInt("ID");
						  }
                         
                          preparedStmt = con.prepareStatement(
                                  "INSERT INTO CreditCard (ID, Number, CVV, Expiration, Amount, Client) "
                          + "VALUES (NULL, '" + cardNumber1.getText()+cardNumber2.getText()+cardNumber3.getText()+cardNumber4.getText() + "', '" 
                                		  + cvcInput.getText() + "', '" + expirationDateInput.getText() + "', " + 1000 + ", "
                                  		 + clientID + ")"
	                    ); // A FINIR
	                    preparedStmt.execute();
	                    
	                    rs = stmt.executeQuery("SELECT ID FROM CreditCard WHERE "
								+ "Number='" + cardNumber1.getText()+cardNumber2.getText()+cardNumber3.getText()+cardNumber4.getText() + "'");
						int cardID = 0; 
						if(rs.next()) {
							cardID = rs.getInt("ID");
					    }
	                    
                        preparedStmt = con.prepareStatement(
                                "UPDATE `Client` SET `CreditCard` = '"+String.valueOf(cardID) +"' WHERE `Client`.`ID` =" +String.valueOf(clientID)
	                    ); // A FINIR
	                    preparedStmt.execute();
	                    
	                    String agencyName = comboBox.getSelectedItem().toString();
	                    rs = stmt.executeQuery("SELECT ID FROM `Agency` WHERE `Name` LIKE '"+agencyName+"'");
						int agencyID = 0; 
						if(rs.next()) {
							agencyID = rs.getInt("ID");
					    }
	                    
	                    preparedStmt = con.prepareStatement(
                                "INSERT INTO ListeClients (ID, Client, Agency) "
                        + "VALUES (NULL, " + clientID + ", " + agencyID + ")"
	                    ); // A FINIR
	                    preparedStmt.execute();
                          
					}
					catch (Exception e1) {
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, 
	                        "Your account have been succesfuly created\n" + "Username : " + userNameInput.getText() + "\n" + 
	                        		"Password : " + passwordInput.getText(), 
	                        "Account Created reminder", 
	                        JOptionPane.WARNING_MESSAGE);
					createAccount.this.dispose();
				}
			}
		});
		registerBtn.setBounds(312, 234, 117, 29);
		contentPane.add(registerBtn);
		
		errorMessageInput = new JTextField();
		errorMessageInput.setEditable(false);
		errorMessageInput.setHorizontalAlignment(SwingConstants.CENTER);
		errorMessageInput.setForeground(new Color(255, 36, 0));
		errorMessageInput.setColumns(10);
		errorMessageInput.setBounds(13, 234, 287, 26);
		errorMessageInput.setBackground(new java.awt.Color(0, 0, 0, 1));
		errorMessageInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(errorMessageInput);
		
		
		JLabel creditCardZone = new JLabel("Credit card informations ");
		creditCardZone.setBounds(35, 147, 192, 16);
		contentPane.add(creditCardZone);
		
		JLabel cardNumberLabel = new JLabel("Card Number");
		cardNumberLabel.setBounds(35, 175, 97, 16);
		contentPane.add(cardNumberLabel);
		
		JLabel cvcLabel = new JLabel("CVC");
		cvcLabel.setBounds(35, 206, 39, 16);
		contentPane.add(cvcLabel);
		
		JLabel expirationDateLabel = new JLabel("Expiration Date");
		expirationDateLabel.setBounds(162, 206, 117, 16);
		contentPane.add(expirationDateLabel);
		
		ageInput = new JTextField();
		ageInput.setColumns(10);
		ageInput.setBounds(72, 108, 58, 26);
		contentPane.add(ageInput);
		
		cardNumber1 = new JTextField();
		cardNumber1.setColumns(10);
		cardNumber1.setBounds(130, 170, 58, 26);
		contentPane.add(cardNumber1);
		
		cardNumber2 = new JTextField();
		cardNumber2.setColumns(10);
		cardNumber2.setBounds(200, 170, 58, 26);
		contentPane.add(cardNumber2);
		
		cardNumber3 = new JTextField();
		cardNumber3.setColumns(10);
		cardNumber3.setBounds(267, 170, 58, 26);
		contentPane.add(cardNumber3);
		
		cardNumber4 = new JTextField();
		cardNumber4.setColumns(10);
		cardNumber4.setBounds(337, 170, 58, 26);
		contentPane.add(cardNumber4);
		
		cvcInput = new JTextField();
		cvcInput.setColumns(10);
		cvcInput.setBounds(74, 201, 58, 26);
		contentPane.add(cvcInput);
		
		expirationDateInput = new JTextField();
		expirationDateInput.setColumns(10);
		expirationDateInput.setBounds(271, 201, 86, 26);
		contentPane.add(expirationDateInput);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(200, 151, 229, 12);
		contentPane.add(separator);
		
		
		agencyChoiceLabel = new JLabel("Agency");
		agencyChoiceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		agencyChoiceLabel.setBounds(305, 29, 106, 16);
		contentPane.add(agencyChoiceLabel);
		
		adressLabel = new JLabel("Adress");
		adressLabel.setBounds(143, 113, 106, 16);
		contentPane.add(adressLabel);
		
		adressInput = new JTextField();
		adressInput.setColumns(10);
		adressInput.setBounds(200, 108, 229, 26);
		contentPane.add(adressInput);
		
		nameLabel = new JLabel("Name");
		nameLabel.setBounds(35, 6, 106, 16);
		contentPane.add(nameLabel);
		
		firstNameLabel = new JLabel("FirstName");
		firstNameLabel.setBounds(200, 6, 106, 16);
		contentPane.add(firstNameLabel);
		
		nameInput = new JTextField();
		nameInput.setColumns(10);
		nameInput.setBounds(82, 1, 86, 26);
		contentPane.add(nameInput);
		
		firstNameInput = new JTextField();
		firstNameInput.setColumns(10);
		firstNameInput.setBounds(281, 1, 97, 26);
		contentPane.add(firstNameInput);
	}
}
