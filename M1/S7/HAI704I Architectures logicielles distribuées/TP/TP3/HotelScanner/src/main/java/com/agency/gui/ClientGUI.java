package com.agency.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ClientGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField destinationInput;
	private JTextField personeNumberInput;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI frame = new ClientGUI();
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
	public ClientGUI() {
		Border whiteline, raisedetched, loweredetched,
	       raisedbevel, loweredbevel, empty;

		whiteline = BorderFactory.createLineBorder(Color.white);
		raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		loweredbevel = BorderFactory.createLoweredBevelBorder();
		empty = BorderFactory.createEmptyBorder();
		setTitle("HotelAdvisor - Comparateur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1143, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblComparateur = new JLabel("HotelAdvisor");
		lblComparateur.setVerticalAlignment(SwingConstants.TOP);
		lblComparateur.setHorizontalAlignment(SwingConstants.CENTER);
		lblComparateur.setForeground(new Color(255, 255, 255));
		lblComparateur.setFont(new Font("Gayathri Thin", Font.BOLD, 40));
		lblComparateur.setBounds(412, 26, 303, 48);
		contentPane.add(lblComparateur);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBounds(435, 75, 257, 10);
		contentPane.add(separator);
		TitledBorder title;
		title = BorderFactory.createTitledBorder(
		                       whiteline, "Destination");
		title.setTitleJustification(TitledBorder.CENTER);
		
		destinationInput = new JTextField();
		destinationInput.setHorizontalAlignment(SwingConstants.LEFT);
		destinationInput.setBounds(424, 168, 323, 28);
		contentPane.add(destinationInput);
		destinationInput.setColumns(10);
		destinationInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		destinationInput.setOpaque(false);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/home/e20190000683/Bureau/HAI704I-REST/Media/SearchBar.png"));
		lblNewLabel.setBounds(380, 153, 380, 57);
		contentPane.add(lblNewLabel);
		
		personeNumberInput = new JTextField();
		personeNumberInput.setBounds(430, 235, 165, 36);
		contentPane.add(personeNumberInput);
		personeNumberInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		personeNumberInput.setOpaque(false);


		personeNumberInput.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(424, 349, 199, 28);
		contentPane.add(textField_2);
		
		JComboBox starsSelector = new JComboBox();
		starsSelector.setModel(new DefaultComboBoxModel(new String[] {"1 étoile (★)", "2 étoiles (★★)", "3 étoiles (★★★)", "4 étoiles (★★★★)", "5 étoiles (★★★★★)"}));
		starsSelector.setBounds(452, 299, 171, 24);
		contentPane.add(starsSelector);
		
		JButton searchButton = new JButton("");
		searchButton.setForeground(new Color(255, 255, 255));
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				destinationInput.setText("test");
			}
		});
		
		JLabel input = new JLabel("");
		input.setIcon(new ImageIcon("/home/e20190000683/Bureau/HAI704I-REST/Media/input.png"));
		input.setBounds(412, 231, 200, 40);
		contentPane.add(input);
		
		JLabel lblIndiquezUneDestination = new JLabel("Indiquez une destination ...");
		lblIndiquezUneDestination.setForeground(new Color(255, 255, 255));
		lblIndiquezUneDestination.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndiquezUneDestination.setBounds(452, 121, 240, 42);
		contentPane.add(lblIndiquezUneDestination);
		searchButton.setContentAreaFilled(false);
		searchButton.setBorderPainted(false);
		searchButton.setBounds(490, 518, 148, 42);
		contentPane.add(searchButton);
		
		JLabel searchButtonBackground = new JLabel("");
		searchButtonBackground.setIcon(new ImageIcon("/home/e20190000683/Bureau/HAI704I-REST/Media/valider.png"));
		searchButtonBackground.setBounds(475, 520, 180, 40);
		contentPane.add(searchButtonBackground);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("/home/e20190000683/Bureau/HAI704I-REST/Media/background.jpg"));
		background.setBounds(0, 0, 1137, 709);
		contentPane.add(background);
	}
}
