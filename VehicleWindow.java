import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VehicleWindow extends JPanel {
	
	public Font HeaderFont;
	public int styledFont;
	public int choiceComponent;
	
	public JLabel HVehicleBrand,HMotorDetails, HLicenceDetails;
	
	public JLabel VehicleBrand, VehicleModel, VehicleNumber, VehicleType, YearManufactor, VehicleColor, VehiclePrice, Currency;
	public JTextField  VehicleNumberInput, VehicleModelInput, YearManufactorInput, VehicleColorInput, VehiclePriceInput;
	public JPanel HVehiclePanel;
	
	
	public JPanel PVehicleBrand, PVehicleModel, PVehicleNumber, PVehicleType, PYearManufactor, PVehicleColor, PVehiclePrice, PCurrency;
	public String[] VehicleBrands= {"","Alfa Romeo", "Audi", "BMW", "Bentley", "Buick", "Cadillac", "Chevrolet", "Chrysler", "Dodge", "Fiat", "Ford", "GMC", "Genesis", "Honda", "Hyundai", "Infiniti", "Jaguar", "Jeep", "Kia",
			"Land Rover", "Lexus" ,"Lincoln", "Lotus",  "Maserati", "Mazda", "Mercedes-Benz", "Mercury", "Mini-Cooper" ,"Mitsubishi", "Nikola", "Nissan", "Polestar", "Pontiac", "Porsche", "Ram", "Rivian", "Rolls-Royce",
			"Saab", "Saturn", "Scion", "Smart", "Subaru", "Suzuki", "Tesla", "Toyota", "Volkswagen",  "Volvo"};
	
	public String[] VehicleTypes= {"","Sedan","Coupe","Sports Car","Station Wagon","Hatchback","Convertible","Sport-utility Vehicle","Minivan","Pickup Truck"};
	public JComboBox VehicleBrandInput, VehicleTypeInput; 
	

	public JPanel HMotorPanel;
	public JLabel MotorNumber, MotorPower, MotorHorsePower, MotorPistonsNumber;
	public JTextField  MotorNumberInput, MotorPowerInput, MotorHorsePowerInput, MotorPistonsNumberInput;
	public JPanel  PMotorNumber, PMotorPower, PMotorHorsePower, PMotorPistonsNumber;
	
	
	public JPanel PDamages, PPart, PState, PSubmit, PClearTable;
	public JButton Submit,ClearTable;
	public JLabel Damages, Part, State;
	public JTextField PartInput, StateInput;
	public JTable JT;
	public DefaultTableModel TM;
	public String[]Col = {"Part","State"};
	public Object[][]Data = {{}};
	public JScrollPane JSP;
	
	
	public JPanel HLicencePanel;
	public JLabel LicenceNumber, LicenceType, LicenceDate;
	public JTextField  LicenceNumberInput, LicenceDateInput; 
	public JPanel PLicenceNumber, PLicenceType, PLicenceDate;
	public JComboBox LicenceTypeInput; 
	public String[] LicenceTypes= {"","Public","Private", "Rent"};
	

	public JPanel  PClear;
	public JButton Clear;
	
	
	public List<Component> VehicleComponents = new ArrayList<Component>();
	
	
	public Map<String, String> VehicleData = new HashMap<String, String>();
	public String[]ComponentsNames = {"VehicleBrandInput","VehicleModelInput","VehicleTypeInput","YearManufactorInput","VehiclePriceInput","MotorNumberInput",
			"MotorPowerInput","MotorHorsePowerInput","MotorPistonsNumberInput","LicenceNumberInput","LicenceTypeInput",
			"LicenceDateInput"};
	
	
	VehicleWindow(){
		
		super();
		setPreferredSize(new Dimension(420,645));
		setSize(new Dimension(420,645));
		setLayout(null);
		
		
		//initializing Map
		
		for(int i=0;i<ComponentsNames.length;i++) {
			VehicleData.put(ComponentsNames[i],"");
		}
		
		//
		
		// Vehicle
		
		styledFont = Font.BOLD; 
		HeaderFont = new Font("Helvetica", styledFont , 20);
		HVehicleBrand = new JLabel("Vehicle Details",  SwingConstants.CENTER);
		HVehicleBrand.setFont(HeaderFont);
		HVehiclePanel = new JPanel(new FlowLayout());
		HVehiclePanel.add(HVehicleBrand);
		
		
		VehicleBrand = new JLabel("Vehicle Brand",  SwingConstants.CENTER);
		VehicleBrandInput = new JComboBox(VehicleBrands);
		VehicleBrandInput.setSize(400,20);
		PVehicleBrand = new JPanel(new GridLayout(1,2));
		PVehicleBrand.add(VehicleBrand);
		PVehicleBrand.add(VehicleBrandInput);
		
		VehicleBrandInput.addActionListener(new GetData());
		
		
		VehicleModel = new JLabel("Vehicle Model",  SwingConstants.CENTER);
		VehicleModelInput = new JTextField();
		VehicleModelInput.setSize(400,20);
		PVehicleModel = new JPanel(new GridLayout(1,2));
		PVehicleModel.add(VehicleModel);
		PVehicleModel.add(VehicleModelInput);
		
		VehicleModelInput.addFocusListener(new GetData());
		
		
		
		VehicleType = new JLabel("Vehicle Type",  SwingConstants.CENTER);
		VehicleTypeInput = new JComboBox(VehicleTypes);	
		VehicleTypeInput.setSize(400,20);
		PVehicleType = new JPanel(new GridLayout(1,2));
		PVehicleType.add(VehicleType);
		PVehicleType.add(VehicleTypeInput);
		
		VehicleTypeInput.addActionListener(new GetData());
		
		
		
		YearManufactor = new JLabel("Year of Manufactor",  SwingConstants.CENTER);
		YearManufactorInput = new JTextField();
		YearManufactor.setSize(400,20);
		PYearManufactor = new JPanel(new GridLayout(1,2));
		PYearManufactor.add(YearManufactor);
		PYearManufactor.add(YearManufactorInput);
		
		YearManufactorInput.addFocusListener(new NumbersOnly());  
		YearManufactorInput.addKeyListener(new NumbersOnly()); 
		YearManufactorInput.addActionListener(new NumbersOnly());
		YearManufactorInput.addFocusListener(new GetData());
		
		
		
		VehicleColor = new JLabel("Vehicle Color",  SwingConstants.CENTER);
		VehicleColorInput = new JTextField();
		VehicleColor.setSize(400,20);
		PVehicleColor = new JPanel(new GridLayout(1,2));
		PVehicleColor.add(YearManufactor);
		PVehicleColor.add(YearManufactorInput);
		
		VehicleColorInput.addFocusListener(new GetData());
		
		
		
		VehiclePrice = new JLabel("Vehicle Price",  SwingConstants.CENTER);
		Currency = new JLabel("$");
		VehiclePriceInput = new JTextField();
		VehiclePrice.setSize(400,20);
		PVehiclePrice = new JPanel(new GridLayout(1,2));
		PVehiclePrice.add(VehiclePrice);
		PVehiclePrice.add(VehiclePriceInput);
		PCurrency = new JPanel( new GridLayout(1,1));
		PCurrency.add(Currency);
		
		VehiclePriceInput.addFocusListener(new NumbersOnly());  
		VehiclePriceInput.addKeyListener(new NumbersOnly());
		VehiclePriceInput.addActionListener(new NumbersOnly());
		VehiclePriceInput.addFocusListener(new GetData());
		
		//
		
		//Motor
		
		HMotorDetails = new JLabel("Motor Details",  SwingConstants.CENTER);
		HMotorDetails.setFont(HeaderFont);
		HMotorPanel = new JPanel(new FlowLayout());
		HMotorPanel.add(HMotorDetails);
		
		
		
		
		MotorNumber = new JLabel("Motor Number",  SwingConstants.CENTER);
		MotorNumberInput = new JTextField();
		MotorNumberInput.setSize(400,20);
		PMotorNumber = new JPanel(new GridLayout(1,2));
		PMotorNumber.add(MotorNumber);
		PMotorNumber.add(MotorNumberInput);
		
		MotorNumberInput.addFocusListener(new NumbersOnly());  
		MotorNumberInput.addKeyListener(new NumbersOnly());
		MotorNumberInput.addActionListener(new NumbersOnly());
		MotorNumberInput.addFocusListener(new GetData()); 
		
		
		
		
		MotorPower = new JLabel("Motor Power",  SwingConstants.CENTER);
		MotorPowerInput = new JTextField();
		MotorPowerInput.setSize(400,20);
		PMotorPower = new JPanel(new GridLayout(1,2));
		PMotorPower.add(MotorPower);
		PMotorPower.add(MotorPowerInput);
		
		MotorPowerInput.addFocusListener(new NumbersOnly());  
		MotorPowerInput.addKeyListener(new NumbersOnly());
		MotorPowerInput.addActionListener(new NumbersOnly());
		MotorPowerInput.addFocusListener(new GetData());  
		
		
		
		
		
		
		MotorHorsePower = new JLabel("Motor Horse Power",  SwingConstants.CENTER);
		MotorHorsePowerInput = new JTextField();
		MotorHorsePowerInput.setSize(400,20);
		PMotorHorsePower = new JPanel(new GridLayout(1,2));
		PMotorHorsePower.add(MotorHorsePower);
		PMotorHorsePower.add(MotorHorsePowerInput);
		
		MotorHorsePowerInput.addFocusListener(new NumbersOnly());  
		MotorHorsePowerInput.addKeyListener(new NumbersOnly());
		MotorHorsePowerInput.addActionListener(new NumbersOnly());
		MotorHorsePowerInput.addFocusListener(new GetData()); 
		
		
		
		MotorPistonsNumber= new JLabel("Pistons",  SwingConstants.CENTER);
		MotorPistonsNumberInput = new JTextField();
		MotorPistonsNumberInput.setSize(400,20);
		PMotorPistonsNumber = new JPanel(new GridLayout(1,2));
		PMotorPistonsNumber.add(MotorPistonsNumber);
		PMotorPistonsNumber.add(MotorPistonsNumberInput);
		
		MotorPistonsNumberInput.addFocusListener(new NumbersOnly());
		MotorPistonsNumberInput.addKeyListener(new NumbersOnly()); 
		MotorPistonsNumberInput.addActionListener(new NumbersOnly()); 
		MotorPistonsNumberInput.addFocusListener(new GetData());
		
		
		
		Damages = new JLabel("Existing Damages",  SwingConstants.CENTER);
		PDamages = new JPanel(new FlowLayout());
		PDamages.add(Damages);
		
		
		Part = new JLabel("Part", SwingConstants.RIGHT);
		PartInput = new JTextField();
		PPart = new JPanel(new GridLayout(1,2));
		PPart.add(Part);
		PPart.add(PartInput);
		
		
		State = new JLabel("State", SwingConstants.RIGHT);
		StateInput = new JTextField();
		PState = new JPanel(new GridLayout(1,2));
		PState.add(State);
		
		StateInput.addFocusListener(new LettersOnly());  
		StateInput.addKeyListener(new LettersOnly());
		StateInput.addActionListener(new LettersOnly());
		
		PState.add(StateInput);
		
		
		Submit = new JButton("Submit");
		Submit.addActionListener(new Submit());
		PSubmit = new JPanel(new FlowLayout());
		PSubmit.add(Submit);
		
		ClearTable = new JButton("Clear Table");
		ClearTable.addActionListener(new ClearTableData());
		PClearTable = new JPanel(new FlowLayout());
		PClearTable.add(ClearTable);
		
		
		TM = new DefaultTableModel(Data,Col);
		JT = new JTable(TM);
		JT.setPreferredScrollableViewportSize(new Dimension(400,60));
		JT.setFillsViewportHeight(true);
		((DefaultTableModel)JT.getModel()).removeRow(0);
		JSP = new JScrollPane(JT);
		
		//
		
		//Licence

		
		HLicenceDetails = new JLabel("Licence Details",  SwingConstants.CENTER);
		HLicenceDetails.setFont(HeaderFont);
		HLicencePanel = new JPanel(new FlowLayout());
		HLicencePanel.add(HLicenceDetails);
		
		
		
		
		LicenceNumber= new JLabel("Licence Number",  SwingConstants.CENTER);
		LicenceNumberInput = new JTextField();
		LicenceNumberInput.setSize(400,20);
		PLicenceNumber = new JPanel(new GridLayout(1,2));
		PLicenceNumber.add(LicenceNumber);
		PLicenceNumber.add(LicenceNumberInput);

		LicenceNumberInput.addFocusListener(new GetData());
		
		
		
		LicenceType= new JLabel("Licence Type",  SwingConstants.CENTER);
		LicenceTypeInput = new JComboBox(LicenceTypes);
		LicenceTypeInput.setSize(400,20);
		PLicenceType = new JPanel(new GridLayout(1,2));
		PLicenceType.add(LicenceType);
		PLicenceType.add(LicenceTypeInput);
		
		LicenceTypeInput.addActionListener(new GetData());
		
		
		
		
		LicenceDate= new JLabel("Licence Date",  SwingConstants.CENTER);
		LicenceDateInput = new JTextField("dd-mm-yyyy");
		LicenceDateInput.setSize(400,20);
		PLicenceDate = new JPanel(new GridLayout(1,2));
		PLicenceDate.add(LicenceDate);
		PLicenceDate.add(LicenceDateInput);
		
		LicenceDateInput.addFocusListener(new PlaceHolderDate());
		LicenceDateInput.addFocusListener(new GetData());
		LicenceDateInput.addFocusListener(new ValidateDateLicence()); 
		LicenceDateInput.addKeyListener(new PlaceHolderDate());
		LicenceDateInput.addActionListener(new NumbersOnly());
			
		//
		
		//Buttons 
		
		Clear = new JButton("Clear");
		Clear.addActionListener(new ClearAction());
		PClear = new JPanel(new FlowLayout());
		PClear.add(Clear);
		
		
		
		
		add(HVehiclePanel).setBounds(0,0,400,40);
		add(PVehicleBrand).setBounds(-40,50,400,20);
		add(PVehicleModel).setBounds(-40,70,400,20);
		add(PYearManufactor).setBounds(-40,90,400,20);
		add(PVehicleType).setBounds(-40,110,400,20);
		add(PVehicleColor).setBounds(-40,130,400,20);
		add(PVehiclePrice).setBounds(-40,150,400,20);
		add(PCurrency).setBounds(365,150,400,20);	
		
		

		
		//add(HMotorPanel).setBounds(50,170,400,40);
		add(PMotorNumber).setBounds(-40,190,400,20);
		add(PMotorPower).setBounds(-40,210,400,20);
		add(PMotorHorsePower).setBounds(-40,230,400,20);
		add(PMotorPistonsNumber).setBounds(-40,250,400,20);
		add(PDamages).setBounds(0,300,400,20);
		add(PPart).setBounds(-40,340,150,30);
		add(PState).setBounds(70,340,150,30);
		add(PSubmit).setBounds(220,337,80,32);
		add(PClearTable).setBounds(300,337,100,32);
		add(JSP).setBounds(10,380,390,60);
		
		

		
		add(HLicencePanel).setBounds(0,440,400,40);
		add(PLicenceNumber).setBounds(-40,520,400,20);
		add(PLicenceType).setBounds(-40,540,400,20);
		add(PLicenceDate).setBounds(-40,560,400,20);
		
		add(PClear).setBounds(320,610,100,40);
		
	}
	

	
	
	public class ClearAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			VehicleModelInput.setText("");
		 	VehicleBrandInput.setSelectedItem("");
		 	VehicleTypeInput.setSelectedItem("");
		 	VehiclePriceInput.setText("");
			YearManufactorInput.setText("");
			VehicleColorInput.setText("");
			MotorNumberInput.setText("");
			MotorPowerInput.setText("");
			MotorHorsePowerInput.setText("");
			MotorPistonsNumberInput.setText("");
			PartInput.setText("");
			StateInput.setText("");
			LicenceNumberInput.setText("");
		 	LicenceTypeInput.setSelectedItem("");
			LicenceDateInput.setText("dd-mm-yyyy");

		}
	}
	
	
	public String getTableData() {
		String ret = "";
		String part="";
		String state ="";
		if (JT.getRowCount()>0) {
			ret +="\nPart\t\t\tState\n";
			for (int i=0;i<JT.getRowCount();i++) {
				part = JT.getModel().getValueAt(i, 0).toString();
				state = JT.getModel().getValueAt(i, 1).toString();
				ret += part +"\t\t\t" +state +"\n";
			}
			return ret;
		}
		
		else {
			ret = "\nNo existing damages";
			return ret;
		}
	}
	
	
	public class PlaceHolderDate implements FocusListener, KeyListener{
		
		public void focusLost(FocusEvent e) {
			if(LicenceDateInput.getText().isEmpty()) {
				LicenceDateInput.setText("dd-mm-yyyy");
			}
		}
		
		public void focusGained(FocusEvent e) {
			LicenceDateInput.setText("");
		}
		
		public void keyReleased(KeyEvent k) {}
		public void keyTyped(KeyEvent k) {}
		public void keyPressed(KeyEvent k) {}
		
	}
	
	
	public class NumbersOnly implements  FocusListener, KeyListener, ActionListener{
		
		public void actionPerformed(ActionEvent event) {}
		
		public void focusGained(FocusEvent e) {}
		public void focusLost(FocusEvent e) {}
		public void keyReleased(KeyEvent k) {}
		public void keyTyped(KeyEvent k) {
					if(!Character.isDigit(k.getKeyChar())) {
						k.consume();
					}
		}
		public void keyPressed(KeyEvent k) {}
	}
	
	
	
	public class LettersOnly implements  FocusListener, KeyListener, ActionListener{
		
		public void actionPerformed(ActionEvent event) {}
		
		public void focusGained(FocusEvent e) {}
		public void focusLost(FocusEvent e) {}
		public void keyReleased(KeyEvent k) {}
		public void keyTyped(KeyEvent k) {
					if(Character.isDigit(k.getKeyChar())) {
						k.consume();
					}
		}
		public void keyPressed(KeyEvent k) {}
	}
	
	public class Submit implements ActionListener{
		public void actionPerformed(ActionEvent event) {
		
			if(PartInput.getText().equals("") || StateInput.getText().equals("")) {
				 JOptionPane.showMessageDialog (null, "Please fill the inputs.",
				 "Input Error",JOptionPane.ERROR_MESSAGE );
			}
			
			else{
				String[]data= {PartInput.getText(),StateInput.getText()};
				TM.insertRow(0, data);
			}
			
		}
	}
	
	public class ClearTableData implements ActionListener{
		public void actionPerformed(ActionEvent event) {
		
			DefaultTableModel model = (DefaultTableModel) JT.getModel();
			model.setRowCount(0);
			
		}
	}
	
	public class ValidateDateLicence implements FocusListener{

		public void focusLost(FocusEvent e) {
			String dt = LicenceDateInput.getText();
			DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-uuuu");
			try {
				f.parse(dt);
			}
			
			catch(Exception ev) {
				System.out.println("Error in Licence Date");
				LicenceDateInput.setText("dd-mm-yyyy");
			}
		}
		
		public void focusGained(FocusEvent e) {
			LicenceDateInput.setText("");
			}
		
	}
	
	public class GetData implements FocusListener,ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==VehicleBrandInput) {
				VehicleData.replace("VehicleBrandInput", VehicleBrandInput.getSelectedItem().toString());
			}
			
			else if(e.getSource()==VehicleTypeInput) {
				VehicleData.replace("VehicleTypeInput", VehicleTypeInput.getSelectedItem().toString());
			}
			
			else if(e.getSource()==LicenceTypeInput) {
				VehicleData.replace("LicenceTypeInput", LicenceTypeInput.getSelectedItem().toString());
			}
			
		}
		
		public void focusGained(FocusEvent e) {}
		public void focusLost(FocusEvent e) {
			
			if(e.getSource()==VehicleModelInput) {
				VehicleData.replace("VehicleModelInput", VehicleModelInput.getText());
			}
			
			else if(e.getSource()==YearManufactorInput) {
				VehicleData.replace("YearManufactorInput", YearManufactorInput.getText());
			}
			
			else if(e.getSource()==VehiclePriceInput) {
				VehicleData.replace("VehiclePriceInput", VehiclePriceInput.getText());
			}
			
			else if(e.getSource()==MotorNumberInput) {
				VehicleData.replace("MotorNumberInput", MotorNumberInput.getText());
			}
			
			else if(e.getSource()==MotorPowerInput) {
				VehicleData.replace("MotorPowerInput", MotorPowerInput.getText());
			}
			
			else if(e.getSource()==MotorHorsePowerInput) {
				VehicleData.replace("MotorHorsePowerInput", MotorHorsePowerInput.getText());
			}
			
			else if(e.getSource()==MotorPistonsNumberInput) {
				VehicleData.replace("MotorPistonsNumberInput", MotorPistonsNumberInput.getText());
			}
			
			else if(e.getSource()==LicenceNumberInput) {
				VehicleData.replace("LicenceNumberInput", LicenceNumberInput.getText());
			}
			
			else if(e.getSource()==LicenceDateInput) {
				VehicleData.replace("LicenceDateInput", LicenceDateInput.getText());
			}
			
		}
	}
}