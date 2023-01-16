package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exception.ReservationException;
import webservice.Client;
import webservice.Hotel;
import webservice.HotelService;
import webservice.Room;

import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class connectedUser extends JDialog {

	public final JPanel contentPanel = new JPanel();
	static JTextField nameInput;
	private JTextField destinationInput;
	private JTextField startDateInput;
	private JTextField endDateInput;
	private JTextField minPriceInput;
	private JTextField maxPriceInput;
	private JTextField bedNumbersInput;
	private JTextField ratingInput;
	private JLabel endDateLabel;
	private JLabel priceLabel;
	private JLabel bedNumbersLabel;
	private JLabel ratingLabel;
	private JLabel minPriceLabel;
	private JLabel maxPriceLabel;
	private JButton searchBtn;
	static JTextField agencyInput;
	static JTextField loginUser;
	static JTextField passwordUser;
	static JTextField agencyDisplay;
	private JTextField foundHotelInput;
	private JLabel foundHotelLabel;
	private JLabel purchasedName;
	private JLabel purchasedNumber;
	private JLabel lblNewLabel_2;
	private JTextField purchasedRoomDisplay;
	private JTextField purchasedHotelDisplay;
	private JTextField purchasedDateDisplay;
	private JButton hotelViewBtn;
	private JLabel roomImage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			connectedUser dialog = new connectedUser();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public connectedUser() throws MalformedURLException, IOException {
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			nameInput = new JTextField();
			nameInput.setHorizontalAlignment(SwingConstants.LEFT);
			nameInput.setEditable(false);
			nameInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			nameInput.setBackground(new java.awt.Color(0, 0, 0, 1));
			nameInput.setBounds(6, 3, 92, 26);
			contentPanel.add(nameInput);
			nameInput.setColumns(10);
		}
		
		JButton logOutBtn = new JButton("Log Out");
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectedUser.this.dispose();
			}
		});
		logOutBtn.setBackground(new Color(255, 255, 255));
		logOutBtn.setForeground(UIManager.getColor("CheckBox.select"));
		logOutBtn.setBounds(344, 3, 100, 29);
		contentPanel.add(logOutBtn);
		
		destinationInput = new JTextField();
		destinationInput.setBounds(132, 44, 130, 26);
		contentPanel.add(destinationInput);
		destinationInput.setColumns(10);
		
		JLabel startDateLabel = new JLabel("Start Date *");
		startDateLabel.setForeground(new Color(255, 255, 255));
		startDateLabel.setBounds(34, 74, 86, 24);
		contentPanel.add(startDateLabel);
		
		JLabel destinationLabel = new JLabel("Destination *");
		destinationLabel.setForeground(new Color(255, 255, 255));
		destinationLabel.setBounds(34, 45, 86, 24);
		contentPanel.add(destinationLabel);
		
		startDateInput = new JTextField();
		startDateInput.setBounds(132, 73, 130, 26);
		contentPanel.add(startDateInput);
		startDateInput.setText("2022-01-01");
		
		endDateInput = new JTextField();
		endDateInput.setColumns(10);
		endDateInput.setBounds(132, 102, 130, 26);
		contentPanel.add(endDateInput);
		endDateInput.setText("2022-01-10");
		
		minPriceInput = new JTextField();
		minPriceInput.setColumns(10);
		minPriceInput.setBounds(142, 131, 68, 26);
		contentPanel.add(minPriceInput);
		
		maxPriceInput = new JTextField();
		maxPriceInput.setColumns(10);
		maxPriceInput.setBounds(266, 131, 68, 26);
		contentPanel.add(maxPriceInput);
		
		bedNumbersInput = new JTextField();
		bedNumbersInput.setColumns(10);
		bedNumbersInput.setBounds(132, 166, 130, 26);
		contentPanel.add(bedNumbersInput);
		
		ratingInput = new JTextField();
		ratingInput.setColumns(10);
		ratingInput.setBounds(142, 204, 130, 26);
		contentPanel.add(ratingInput);
		
		endDateLabel = new JLabel("End Date *");
		endDateLabel.setForeground(new Color(255, 255, 255));
		endDateLabel.setBounds(34, 103, 86, 24);
		contentPanel.add(endDateLabel);
		
		priceLabel = new JLabel("Price *");
		priceLabel.setForeground(new Color(255, 255, 255));
		priceLabel.setBounds(34, 132, 58, 24);
		contentPanel.add(priceLabel);
		
		bedNumbersLabel = new JLabel("Bed Numbers *");
		bedNumbersLabel.setForeground(new Color(255, 255, 255));
		bedNumbersLabel.setBounds(34, 168, 116, 24);
		contentPanel.add(bedNumbersLabel);
		
		ratingLabel = new JLabel("Minimum Rating");
		ratingLabel.setForeground(new Color(255, 255, 255));
		ratingLabel.setBounds(34, 204, 116, 24);
		contentPanel.add(ratingLabel);
		
		minPriceLabel = new JLabel("Min");
		minPriceLabel.setForeground(new Color(255, 255, 255));
		minPriceLabel.setBounds(104, 132, 86, 24);
		contentPanel.add(minPriceLabel);
		
		maxPriceLabel = new JLabel("Max *");
		maxPriceLabel.setForeground(new Color(255, 255, 255));
		maxPriceLabel.setBounds(224, 132, 86, 24);
		contentPanel.add(maxPriceLabel);
		
		JLabel searchMissingDisplay = new JLabel("");
		searchMissingDisplay.setBounds(34, 240, 238, 16);
		contentPanel.add(searchMissingDisplay);
		
		JSeparator separator = new JSeparator();
		separator.setVisible(false);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(224, 49, 19, 201);
		contentPanel.add(separator);
		
		roomImage = new JLabel("");
		roomImage.setVisible(false);
		roomImage.setBounds(274, 107, 144, 97);
		contentPanel.add(roomImage);
		
		JLabel roomInfosLabel = new JLabel("Room Image");
		roomInfosLabel.setVisible(false);
		roomInfosLabel.setForeground(new Color(255, 255, 255));
		roomInfosLabel.setHorizontalAlignment(SwingConstants.CENTER);
		roomInfosLabel.setBounds(274, 58, 139, 16);
		contentPanel.add(roomInfosLabel);
		
		JSeparator infosSeparator = new JSeparator();
		infosSeparator.setVisible(false);
		infosSeparator.setBounds(294, 86, 100, 12);
		contentPanel.add(infosSeparator);
		
		JComboBox hotelChoice = new JComboBox();
		hotelChoice.setMaximumRowCount(30);
		hotelChoice.setModel(new DefaultComboBoxModel(new String[] {}));
		hotelChoice.setVisible(false);
		hotelChoice.setBounds(34, 92, 169, 27);
		contentPanel.add(hotelChoice);

		JComboBox roomChoice = new JComboBox();
		roomChoice.setMaximumRowCount(30);
		roomChoice.setVisible(false);
		roomChoice.setBounds(34, 132, 169, 27);
		contentPanel.add(roomChoice);
		
		JButton returnToSearch = new JButton("Back");
		
		JButton purchaseBtn = new JButton("Purchase this room");
		purchaseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String city = destinationInput.getText();
				String startDate = startDateInput.getText();
				String endDate = endDateInput.getText();
				int bedNumbers = Integer.valueOf(bedNumbersInput.getText());
				int minPrice;
				if(minPriceInput.getText().length() != 0) {
					minPrice = Integer.valueOf(minPriceInput.getText());
				} else {
					minPrice = 0;
				}
				int maxPrice = Integer.valueOf(maxPriceInput.getText());
				Double rating;
				if(minPriceInput.getText().length() != 0) {
					rating = Double.valueOf(ratingInput.getText());
				} else {
					rating = 0.0;
				}
				String loginUsername = loginUser.getText();
				String loginPassword = passwordUser.getText();
				String agencyId = "";
				Agency agency2 = new Agency();
				agencyId = agencyInput.getText();
				Agency agency = null;
				if(agencyId.equals("HotelAdvisor.com")) {
					agency = MainFunctions.MakeAgence(1);
				} else if (agencyId.equals("Hotel.net")) {
					agency = MainFunctions.MakeAgence(2);
				} else {
					agency = MainFunctions.MakeAgence(3);
				}
				Client client = null;
				client = MainFunctions.connectClient(loginUsername, loginPassword, agency);
				
				if(client == null) {
					connectedUser.this.dispose();
				}
				
				HashMap<Hotel, Double> hotels = MainFunctions.research(agency, city, bedNumbers, startDate, endDate, minPrice, maxPrice, rating);
				
				Hotel selectedH = null;
				Room selectedR = null;
				
				String selectedHotel = (String)hotelChoice.getSelectedItem();
				for (Hotel key : hotels.keySet()) {
					if(key.getName().equals(selectedHotel)) {
						for(Room room : key.getRooms()) {
							roomChoice.addItem(room);
							selectedH = key;
							selectedR = room;
						}
					}
				}
				
				foundHotelInput.setVisible(false);
				foundHotelLabel.setVisible(false);
				purchaseBtn.setVisible(false);
				separator.setVisible(false);
				hotelChoice.setVisible(false);
				searchMissingDisplay.setVisible(false);
				
				roomInfosLabel.setVisible(false);
				infosSeparator.setVisible(false);
				roomImage.setVisible(false);
				roomChoice.setVisible(false);
				returnToSearch.setVisible(false);
				hotelViewBtn.setVisible(false);
				purchasedName.setVisible(true);
				purchasedNumber.setVisible(true);
				purchasedRoomDisplay.setVisible(true);
				purchasedHotelDisplay.setVisible(true);
				purchasedDateDisplay.setVisible(true);
				
				LocalDate ind = LocalDate.parse(startDate);
				LocalDate oud = LocalDate.parse(endDate);
				HashMap<HotelService, Double> agencyOffers = agency.getOffers();
				
				Double amount = 0.0;
				
				for (HotelService element : agencyOffers.keySet()) {
					if(element.getHotel().getName().equals(selectedH.getName())) {
						amount = agencyOffers.get(element);
					}
				}
				
				try {
					if(MainFunctions.makeReservationGUI(agency, client, ind, oud, selectedR, selectedH, amount) == 1) {
						
						String gps = "";
						try {
							gps = GPSMaker.gpsEncoder(selectedH.getAddress().toString());
						} catch (Exception exc) {
							exc.printStackTrace();
						}
						String link = "";
						if(gps !=null) {
							String[] arr = gps.split(" ");
							link = "http://maps.google.com/maps?z=12&t=m&q=loc:" + arr[0] + "+" + arr[1];
						}
						purchasedName.setText(client.getFirstname() + " " + client.getName());
						purchasedNumber.setText(client.getTelNumber());
						purchasedRoomDisplay.setText(roomChoice.getSelectedItem().toString());
						purchasedHotelDisplay.setText(selectedH.getName());
						purchasedDateDisplay.setText("From : " + startDate + " to : " + endDate);
						JOptionPane.showMessageDialog(null, 
		                        "A PDF with your reservation have been sent to " + System.getProperty("user.dir") +"/Reservation.pdf", "PDF sent", 
		                        JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, 
		                        "Purchased failed, not enough balance", 
		                        "Purchased Failed", 
		                        JOptionPane.WARNING_MESSAGE);
					}
				} catch (ReservationException e1) {
					e1.printStackTrace();
				}
			}
		});
		purchaseBtn.setVisible(false);
		purchaseBtn.setForeground(new Color(46, 139, 87));
		purchaseBtn.setBackground(Color.WHITE);
		purchaseBtn.setBounds(34, 178, 176, 26);
		contentPanel.add(purchaseBtn);
		
		
		returnToSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				destinationInput.setVisible(true);
				startDateInput.setVisible(true);
				endDateInput.setVisible(true);
				bedNumbersInput.setVisible(true);
				minPriceInput.setVisible(true);
				maxPriceInput.setVisible(true);
				ratingInput.setVisible(true);
				searchBtn.setVisible(true);
				
				destinationLabel.setVisible(true);
				startDateLabel.setVisible(true);
				endDateLabel.setVisible(true);
				bedNumbersLabel.setVisible(true);
				minPriceLabel.setVisible(true);
				maxPriceLabel.setVisible(true);
				ratingLabel.setVisible(true);
				priceLabel.setVisible(true);
				searchMissingDisplay.setVisible(true);
				
				foundHotelInput.setVisible(false);
				foundHotelLabel.setVisible(false);
				purchaseBtn.setVisible(false);
				separator.setVisible(false);
				hotelChoice.setVisible(false);
				
				roomInfosLabel.setVisible(false);
				infosSeparator.setVisible(false);
				roomImage.setVisible(false);
				roomChoice.setVisible(false);
				returnToSearch.setVisible(false);
				hotelViewBtn.setVisible(false);
				
				
			}
		});
		returnToSearch.setVisible(false);
		returnToSearch.setForeground(new Color(255, 64, 43));
		returnToSearch.setBackground(Color.WHITE);
		returnToSearch.setBounds(66, 229, 106, 26);
		contentPanel.add(returnToSearch);
				
		searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String city = destinationInput.getText();
				String startDate = startDateInput.getText();
				String endDate = endDateInput.getText();
				int bedNumbers = Integer.valueOf(bedNumbersInput.getText());
				int minPrice;
				if(minPriceInput.getText().length() != 0) {
					minPrice = Integer.valueOf(minPriceInput.getText());
				} else {
					minPrice = 0;
				}
				int maxPrice = Integer.valueOf(maxPriceInput.getText());
				Double rating;
				if(ratingInput.getText().length() != 0) {
					rating = Double.valueOf(ratingInput.getText());
				} else {
					rating = 0.0;
				}
				
				String loginUsername = loginUser.getText();
				String loginPassword = passwordUser.getText();
				String agencyId = "";
				Agency agency2 = new Agency();
				agencyId = agencyInput.getText();
				Agency agency = null;
				if(agencyId.equals("HotelAdvisor.com")) {
					agency = MainFunctions.MakeAgence(1);
				} else if (agencyId.equals("Hotel.net")) {
					agency = MainFunctions.MakeAgence(2);
				} else {
					agency = MainFunctions.MakeAgence(3);
				}
				Client client = null;
				client = MainFunctions.connectClient(loginUsername, loginPassword, agency);
				
				if(client == null) {
					connectedUser.this.dispose();
				}
				
				if(!destinationInput.getText().equals("") && !bedNumbersInput.getText().equals("") && !maxPriceInput.getText().equals("") && !startDateInput.getText().equals("")
						&& !endDateInput.getText().equals("")) 
				{
					HashMap<Hotel, Double> hotels = MainFunctions.research(agency, city, bedNumbers, startDate, endDate, minPrice, maxPrice, rating);
					if(hotels.isEmpty()) {
						JOptionPane.showMessageDialog(null, 
		                        "Erreur, aucune correspondance", 
		                        "Research Exception", 
		                        JOptionPane.WARNING_MESSAGE);
					} else {
						hotelChoice.removeAllItems();
						int cpt = 0;
						Hotel firstImage = (Hotel) hotels.keySet().toArray()[0];
						BufferedImage roomImg = null;
						try {
							roomImg = ImageIO.read(new URL(firstImage.getImageFolder() + "/" + "0" + ".jpg"));
							if(roomImg == null) {
								roomImg = ImageIO.read(new URL("http://hotelfinder.sc1samo7154.universe.wf/blurImage_563x373.jpeg"));
							}
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						roomImage.setIcon(new ImageIcon(roomImg));
						for (Hotel key : hotels.keySet()) {
							hotelChoice.addItem(key.getName());
							cpt++;
						}
						
						String selectedHotel = (String)hotelChoice.getSelectedItem();
						roomChoice.removeAllItems();
						
						for (Hotel key : hotels.keySet()) {
							if(key.getName().equals(selectedHotel)) {
								for(Room room : key.getRooms()) {
									roomChoice.addItem(room);
								}
							}
						}
						
						hotelChoice.addActionListener (new ActionListener () {
						    public void actionPerformed(ActionEvent e) {
						    	String selectedHotel = (String)hotelChoice.getSelectedItem();
						    	roomChoice.removeAllItems();
								
								for (Hotel key : hotels.keySet()) {
									if(key.getName().equals(selectedHotel)) {
										for(Room room : key.getRooms()) {
											roomChoice.addItem(room);
											BufferedImage roomImg = null;
											try {
												roomImg = ImageIO.read(new URL(key.getImageFolder() + "/" + "0" + ".jpg"));
												if(roomImg == null) {
													roomImg = ImageIO.read(new URL("http://hotelfinder.sc1samo7154.universe.wf/blurImage_563x373.jpeg"));
												}
											} catch (MalformedURLException e1) {
												e1.printStackTrace();
											} catch (IOException e1) {
												e1.printStackTrace();
											}
											roomImage.setIcon(new ImageIcon(roomImg));
										}
									}
								}
						    }
						});
						
						roomChoice.addActionListener (new ActionListener () {
						    public void actionPerformed(ActionEvent e) {
								
								String selectedHotel = (String)hotelChoice.getSelectedItem();
								String selectedRoom = String.valueOf(roomChoice.getSelectedItem());
								
								String number = "0";
								Matcher m = Pattern.compile("[^0-9]*([0-9]+).*").matcher(selectedRoom);
								if (m.matches()) {
								    number = m.group(1);
								}
								
								for (Hotel key : hotels.keySet()) {
									if(key.getName().equals(selectedHotel)) {
										for(Room room : key.getRooms()) {
											if(room.getRoomNumber() == (Integer.parseInt(number))) {
												BufferedImage roomImg = null;
												try {
													roomImg = ImageIO.read(new URL(key.getImageFolder() + "/" + String.valueOf(room.getRoomNumber()) + ".jpg"));
													if(roomImg == null) {
														roomImg = ImageIO.read(new URL("http://hotelfinder.sc1samo7154.universe.wf/blurImage_563x373.jpeg"));
													}
												} catch (MalformedURLException e1) {
													e1.printStackTrace();
												} catch (IOException e1) {
													e1.printStackTrace();
												}
												roomImage.setIcon(new ImageIcon(roomImg));
											}
										}
									}
								}
						    }
						});
						
						
									
						foundHotelInput.setText(String.valueOf(cpt));
						
						destinationInput.setVisible(false);
						startDateInput.setVisible(false);
						endDateInput.setVisible(false);
						bedNumbersInput.setVisible(false);
						minPriceInput.setVisible(false);
						maxPriceInput.setVisible(false);
						ratingInput.setVisible(false);
						searchBtn.setVisible(false);
						
						destinationLabel.setVisible(false);
						startDateLabel.setVisible(false);
						endDateLabel.setVisible(false);
						bedNumbersLabel.setVisible(false);
						minPriceLabel.setVisible(false);
						maxPriceLabel.setVisible(false);
						ratingLabel.setVisible(false);
						priceLabel.setVisible(false);
						
						foundHotelInput.setVisible(true);
						foundHotelLabel.setVisible(true);
						purchaseBtn.setVisible(true);
						separator.setVisible(true);
						hotelChoice.setVisible(true);
						
						roomInfosLabel.setVisible(true);
						infosSeparator.setVisible(true);
						roomImage.setVisible(true);
						roomChoice.setVisible(true);
						returnToSearch.setVisible(true);
						hotelViewBtn.setVisible(true);
					}
				} else {
					searchMissingDisplay.setText("Missing important fields");
				}
			}
		});
		searchBtn.setForeground(new Color(46, 139, 87));
		searchBtn.setBackground(Color.WHITE);
		searchBtn.setBounds(312, 224, 106, 26);
		contentPanel.add(searchBtn);
		
		agencyInput = new JTextField();
		agencyInput.setVisible(false);
		agencyInput.setBackground(new java.awt.Color(0, 0, 0, 1));
		agencyInput.setEnabled(false);
		agencyInput.setEditable(false);
		agencyInput.setBounds(423, 44, 19, 26);
		contentPanel.add(agencyInput);
		agencyInput.setColumns(10);
		
		loginUser = new JTextField();
		loginUser.setBackground(new java.awt.Color(0, 0, 0, 1));
		loginUser.setEnabled(false);
		loginUser.setVisible(false);
		loginUser.setEditable(false);
		loginUser.setColumns(10);
		loginUser.setBounds(425, 73, 19, 26);
		contentPanel.add(loginUser);
		
		passwordUser = new JTextField();
		passwordUser.setBackground(new java.awt.Color(0, 0, 0, 1));
		passwordUser.setVisible(false);
		passwordUser.setEnabled(false);
		passwordUser.setEditable(false);
		passwordUser.setColumns(10);
		passwordUser.setBounds(423, 102, 19, 26);
		contentPanel.add(passwordUser);
		
		agencyDisplay = new JTextField();
		agencyDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		agencyDisplay.setEditable(false);
		agencyDisplay.setColumns(10);
		agencyDisplay.setBounds(155, 3, 130, 26);
		contentPanel.add(agencyDisplay);
		agencyDisplay.setBackground(new java.awt.Color(0, 0, 0, 1));
		agencyDisplay.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		foundHotelInput = new JTextField();
		foundHotelInput.setForeground(new Color(255, 255, 255));
		foundHotelInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		foundHotelInput.setBackground(new java.awt.Color(0, 0, 0, 1));
		foundHotelInput.setVisible(false);
		foundHotelInput.setEditable(false);
		foundHotelInput.setColumns(10);
		foundHotelInput.setBounds(132, 49, 58, 26);
		contentPanel.add(foundHotelInput);
		
		foundHotelLabel = new JLabel("Hotels Found :");
		foundHotelLabel.setVisible(false);
		foundHotelLabel.setForeground(Color.WHITE);
		foundHotelLabel.setBounds(34, 49, 116, 24);
		contentPanel.add(foundHotelLabel);
		BufferedImage img = ImageIO.read(new URL("http://hotelfinder.sc1samo7154.universe.wf/blurImage_563x373.jpeg"));
		
		purchasedName = new JLabel("");
		purchasedName.setForeground(new Color(255, 255, 255));
		purchasedName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		purchasedName.setBackground(new java.awt.Color(0, 0, 0, 1));
		purchasedName.setHorizontalAlignment(SwingConstants.CENTER);
		purchasedName.setVisible(false);
		purchasedName.setBounds(166, 82, 133, 16);
		contentPanel.add(purchasedName);
		
		purchasedNumber = new JLabel("");
		purchasedNumber.setForeground(new Color(255, 255, 255));
		purchasedNumber.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		purchasedNumber.setBackground(new java.awt.Color(0, 0, 0, 1));
		purchasedNumber.setHorizontalAlignment(SwingConstants.CENTER);
		purchasedNumber.setVisible(false);
		purchasedNumber.setBounds(166, 112, 133, 16);
		contentPanel.add(purchasedNumber);
		
		purchasedRoomDisplay = new JTextField();
		purchasedRoomDisplay.setForeground(new Color(255, 255, 255));
		purchasedRoomDisplay.setVisible(false);
		purchasedRoomDisplay.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		purchasedRoomDisplay.setBackground(new java.awt.Color(0, 0, 0, 1));
		purchasedRoomDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		purchasedRoomDisplay.setEditable(false);
		purchasedRoomDisplay.setColumns(10);
		purchasedRoomDisplay.setBounds(34, 177, 379, 26);
		contentPanel.add(purchasedRoomDisplay);
		
		purchasedHotelDisplay = new JTextField();
		purchasedHotelDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		purchasedHotelDisplay.setVisible(false);
		purchasedHotelDisplay.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		purchasedHotelDisplay.setBackground(new java.awt.Color(0, 0, 0, 1));
		purchasedHotelDisplay.setEditable(false);
		purchasedHotelDisplay.setForeground(new Color(255, 255, 255));
		purchasedHotelDisplay.setBounds(34, 140, 379, 26);
		contentPanel.add(purchasedHotelDisplay);
		purchasedHotelDisplay.setColumns(10);
		
		purchasedDateDisplay = new JTextField();
		purchasedDateDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		purchasedDateDisplay.setVisible(false);
		purchasedDateDisplay.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		purchasedDateDisplay.setBackground(new java.awt.Color(0, 0, 0, 1));
		purchasedDateDisplay.setForeground(new Color(255, 255, 255));
		purchasedDateDisplay.setBounds(34, 204, 379, 26);
		contentPanel.add(purchasedDateDisplay);
		purchasedDateDisplay.setColumns(10);
		
		hotelViewBtn = new JButton("View Hotel");
		hotelViewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String city = destinationInput.getText();
				String startDate = startDateInput.getText();
				String endDate = endDateInput.getText();
				int bedNumbers = Integer.valueOf(bedNumbersInput.getText());
				int minPrice;
				if(minPriceInput.getText().length() != 0) {
					minPrice = Integer.valueOf(minPriceInput.getText());
				} else {
					minPrice = 0;
				}
				int maxPrice = Integer.valueOf(maxPriceInput.getText());
				Double rating;
				if(minPriceInput.getText().length() != 0) {
					rating = Double.valueOf(ratingInput.getText());
				} else {
					rating = 0.0;
				}
				String loginUsername = loginUser.getText();
				String loginPassword = passwordUser.getText();
				String agencyId = "";
				Agency agency2 = new Agency();
				agencyId = agencyInput.getText();
				Agency agency = null;
				if(agencyId.equals("HotelAdvisor.com")) {
					agency = MainFunctions.MakeAgence(1);
				} else if (agencyId.equals("Hotel.net")) {
					agency = MainFunctions.MakeAgence(2);
				} else {
					agency = MainFunctions.MakeAgence(3);
				}
				Client client = null;
				client = MainFunctions.connectClient(loginUsername, loginPassword, agency);
				
				if(client == null) {
					connectedUser.this.dispose();
				}
				
				HashMap<Hotel, Double> hotels = MainFunctions.research(agency, city, bedNumbers, startDate, endDate, minPrice, maxPrice, rating);
				
				Hotel selectedH = null;
				Room selectedR = null;
				
				String selectedHotel = (String)hotelChoice.getSelectedItem();
				for (Hotel key : hotels.keySet()) {
					if(key.getName().equals(selectedHotel)) {
						for(Room room : key.getRooms()) {
							roomChoice.addItem(room);
							selectedH = key;
							selectedR = room;
						}
					}
				}
				
				LocalDate ind = LocalDate.parse(startDate);
				LocalDate oud = LocalDate.parse(endDate);
				HashMap<HotelService, Double> agencyOffers = agency.getOffers();
				
				Double amount = 0.0;
				
				for (HotelService element : agencyOffers.keySet()) {
					if(element.getHotel().getName().equals(selectedH.getName())) {
						amount = agencyOffers.get(element);
					}
				}
				String gps = "";
				try {
					gps = GPSMaker.gpsEncoder(selectedH.getAddress().toString());
				} catch (Exception exc) {
					exc.printStackTrace();
				}
				String link = "";
				if(gps !=null) {
					String[] arr = gps.split(" ");
					link = "http://maps.google.com/maps?z=12&t=m&q=loc:" + arr[0] + "+" + arr[1];
				}
				
				try {
					Desktop.getDesktop().browse((new URL(link)).toURI());
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		hotelViewBtn.setVisible(false);
		hotelViewBtn.setForeground(new Color(0, 0, 0));
		hotelViewBtn.setBounds(284, 221, 117, 29);
		contentPanel.add(hotelViewBtn);
	
		JLabel connectedBackgroundImage = new JLabel("");
		connectedBackgroundImage.setBounds(0, 36, 450, 236);
		contentPanel.add(connectedBackgroundImage);
		BufferedImage img2 = ImageIO.read(new URL("http://hotelfinder.sc1samo7154.universe.wf/blurImage_563x373.jpeg"));
		connectedBackgroundImage.setIcon(new ImageIcon(img2));
		
	}
}
