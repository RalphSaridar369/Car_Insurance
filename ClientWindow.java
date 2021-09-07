import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

import javax.swing.*;

public class ClientWindow extends JPanel{

	public Font HeaderFont;
	public int styledFont;
	public int choiceComponent;
	
	public JLabel ClientHeader,InsuranceHeader;
	public JPanel ClientPanel,InsurancePanel;
	
	public JPanel PFName,PLName,PMother,PDDN,PNationalite,PID,PRecordNumber,PCity,PTelephone;
	
	public JLabel FName,LName,Mother,DDN,Gender,ID,Nationalite,City,RecordNumber,Telephone;
	public JTextField FNameInput,LNameInput,MotherInput,DDNInput,CityInput,IDInput,RecordNumberInput,TelephoneInput;
	public JComboBox NationaliteInput;
	public String[] NationalitePays = {"","Australia","Bahrain","Canada","Denmark","Egypt","France","Germany","Iraq","Lebanon","Monaco","Qatar","Saudi Arabia", "United Arab Emirates",
			"United Kingdom", "United States of America"};
	

	public JPanel PGender,PGenderInner,PGender1,PGender2;
	public ButtonGroup GenderGroup;
	public JRadioButton Male,Female;

	
	public JPanel PInsuranceNumber, PInsuranceDate, PInsurancePlan, PDateStart, PDateEnd, PTimeLeft, PCalculate;
	public JLabel InsuranceNumber, InsuranceDate, InsurancePlan, DateStart, DateEnd, TimeLeft;
	public JTextField InsuranceNumberInput, InsuranceDateInput, DateStartInput, DateEndInput, TimeLeftInput;
	public JButton Calculate;
	public String[]Insurances = {"Compulsory insurance", "Comprehensive coverage",
			"Driver's personal insurance", "L'assistance", "All-risk insurance without and with deductible"};
	
	public String[]InsurancePlans = {"<html>Compulsory insurance <br>Pre:4%   Cov:60%</html>", "<html>Comprehensive coverage <br>Pre:4%   Cov:60%</html>",
			"<html>Driver's personal insurance<br>Pre:5%   Cov:70%</html>", "<html>L'assistance<br>Pre:3.5%   Cov:45%</html>", "<html>All-risk insurance without and with deductible<br>Pre:7.5%   Cov:100%</html>"};
	
	public JCheckBox c1,c2,c3,c4,c5;
	public JPanel PC12,PC34,PC56;
	public JCheckBox []jcb = {c1,c2,c3,c4,c5};
	
	public JPanel MainClient, MainInsurance;

	public JPanel PClear;
	public JButton Clear;
	
	
	public List<Component> ClientComponents	= new ArrayList<Component>();
	
	public Set<String> Ch = new HashSet<String>();
	
	public Map<String, String> ClientData = new HashMap<String, String>();
	public String[]ComponentsNames = {"FNameInput","LNameInput","MotherInput","DDNInput","GenderInput","NationaliteInput","IDInput",
			"CityInput","RecordNumberInput","TelephoneInput","InsuranceNumberInput","InsuranceDateInput",
			"DateStartInput","DateEndInput","TimeLeftInput","InsurancePlanInput"};
	
	
	
	
	public 
	
	
	ClientWindow(){
		
		super();
		setPreferredSize(new Dimension(400,645));
		setLayout(null);
		
		
		//initializing Map
		
		for(int i=0;i<ComponentsNames.length;i++) {
			ClientData.put(ComponentsNames[i],"");
		}
		
		//
		
		//Client Basic
		
		styledFont = Font.BOLD; 
		HeaderFont = new Font("Helvetica", styledFont , 20);
		ClientHeader = new JLabel("Client Details",  SwingConstants.CENTER);
		ClientHeader.setFont(HeaderFont);
		ClientPanel = new JPanel(new FlowLayout());
		ClientPanel.add(ClientHeader);
		
		
		FName = new JLabel("First Name",  SwingConstants.CENTER);
		FName.setSize(50,20);
		FNameInput = new JTextField();
		FNameInput.setSize(400,20);
		
		FNameInput.addFocusListener(new LettersOnly());  
		FNameInput.addKeyListener(new LettersOnly()); 
		FNameInput.addActionListener(new LettersOnly());
		FNameInput.addFocusListener(new GetData());  
		
		
		
		
		
		PFName= new JPanel(new GridLayout(1,2));
		PFName.setSize(400,20);
		PFName.add(FName);
		PFName.add(FNameInput);		
		
		
		
		LName = new JLabel("Last Name",  SwingConstants.CENTER);
		LNameInput = new JTextField();
		LNameInput.setSize(400,20);
		PLName= new JPanel(new GridLayout(1,2));
		PLName.setSize(400,20);
		PLName.add(LName);
		PLName.add(LNameInput);	
		
		LNameInput.addFocusListener(new LettersOnly());  
		LNameInput.addKeyListener(new LettersOnly()); 
		LNameInput.addActionListener(new LettersOnly());
		LNameInput.addFocusListener(new GetData()); 
		
		
		
		Mother = new JLabel("Mother's Full Name",  SwingConstants.CENTER);
		MotherInput = new JTextField();
		MotherInput.setSize(400,20);
		PMother= new JPanel(new GridLayout(1,2));
		PMother.setSize(400,20);
		PMother.add(Mother);
		PMother.add(MotherInput);
		
		MotherInput.addFocusListener(new LettersOnly());  
		MotherInput.addKeyListener(new LettersOnly()); 
		MotherInput.addActionListener(new LettersOnly());
		MotherInput.addFocusListener(new GetData()); 
		
		
		
		DDN = new JLabel("Date of Birth",  SwingConstants.CENTER);
		DDNInput = new JTextField("dd-mm-yyyy");
		DDNInput.setSize(400,20);
		PDDN= new JPanel(new GridLayout(1,2));
		PDDN.setSize(400,20);
		PDDN.add(DDN);
		PDDN.add(DDNInput);

		
		DDNInput.addFocusListener(new ValidateBirthDate());  
		DDNInput.addFocusListener(new GetData());  

		

		Gender = new JLabel("Gender", SwingConstants.CENTER);
		Male = new JRadioButton("Male");
		Female = new JRadioButton("Female");
		GenderGroup = new ButtonGroup();
		PGender = new JPanel(new GridLayout(1,2));
		GenderGroup.add(Male);
		GenderGroup.add(Female);
		PGender.add(Gender);
		PGender1 = new JPanel( new FlowLayout());
		PGender2 = new JPanel( new FlowLayout());
		PGender1.add(Male).setBounds(120, 210, 400, 20);
		PGender2.add(Female).setBounds(400, 210, 400, 20);
		PGender.add(PGender1);
		PGender.add(PGender2);
		
		Male.addActionListener(new GetData());
		Female.addActionListener(new GetData());
		//
		
		//Client Addition
		
		Nationalite = new JLabel("Nationality",  SwingConstants.CENTER);
		NationaliteInput = new JComboBox(NationalitePays);
		NationaliteInput.setSize(400,20);
		PNationalite= new JPanel(new GridLayout(1,2));
		PNationalite.setSize(400,20);
		PNationalite.add(Nationalite);
		PNationalite.add(NationaliteInput);
		
		NationaliteInput.addActionListener(new GetData());
		
		ID = new JLabel("ID",  SwingConstants.CENTER);
		IDInput = new JTextField();
		IDInput.setSize(400,20);
		PID= new JPanel(new GridLayout(1,2));
		PID.setSize(400,20);
		PID.add(ID);
		PID.add(IDInput);

		
		IDInput.addFocusListener(new NumbersOnly());  
		IDInput.addKeyListener(new NumbersOnly()); 
		IDInput.addActionListener(new NumbersOnly());
		IDInput.addFocusListener(new GetData());
		
		
		
		City = new JLabel("City",  SwingConstants.CENTER);
		CityInput = new JTextField();
		CityInput.setSize(400,20);
		PCity= new JPanel(new GridLayout(1,2));
		PCity.setSize(400,20);
		PCity.add(City);
		PCity.add(CityInput);
		
		CityInput.addFocusListener(new LettersOnly());  
		CityInput.addKeyListener(new LettersOnly()); 
		CityInput.addActionListener(new LettersOnly());
		CityInput.addFocusListener(new GetData()); 
		
		
		
		RecordNumber = new JLabel("Record Number",  SwingConstants.CENTER);
		RecordNumberInput = new JTextField();
		RecordNumberInput.setSize(400,20);
		PRecordNumber= new JPanel(new GridLayout(1,2));
		PRecordNumber.setSize(400,20);
		PRecordNumber.add(RecordNumber);
		PRecordNumber.add(RecordNumberInput);

		
		RecordNumberInput.addFocusListener(new NumbersOnly());  
		RecordNumberInput.addKeyListener(new NumbersOnly()); 
		RecordNumberInput.addActionListener(new NumbersOnly());
		RecordNumberInput.addFocusListener(new GetData());  
		
		
		
		Telephone = new JLabel("Telephone",  SwingConstants.CENTER);
		TelephoneInput = new JTextField();
		TelephoneInput.setSize(400,20);
		PTelephone= new JPanel(new GridLayout(1,2));
		PTelephone.setSize(400,20);
		PTelephone.add(Telephone);
		PTelephone.add(TelephoneInput);

		
		TelephoneInput.addFocusListener(new NumbersOnly());  
		TelephoneInput.addKeyListener(new NumbersOnly()); 
		TelephoneInput.addActionListener(new NumbersOnly());
		TelephoneInput.addFocusListener(new GetData());
		
		
		//
		
		//Insurance
		
		
		InsuranceHeader = new JLabel("Insurance Details",  SwingConstants.CENTER);
		InsuranceHeader.setFont(HeaderFont);
		InsurancePanel = new JPanel(new FlowLayout());
		InsurancePanel.add(InsuranceHeader);
		
		
		
		InsuranceNumber = new JLabel("Insurance Number",  SwingConstants.CENTER);
		InsuranceNumberInput = new JTextField();
		InsuranceNumberInput.setSize(400,20);
		PInsuranceNumber = new JPanel(new GridLayout(1,2));
		PInsuranceNumber.add(InsuranceNumber);
		PInsuranceNumber.add(InsuranceNumberInput);

		
		InsuranceNumberInput.addFocusListener(new NumbersOnly());  
		InsuranceNumberInput.addKeyListener(new NumbersOnly()); 
		InsuranceNumberInput.addActionListener(new NumbersOnly());
		InsuranceNumberInput.addFocusListener(new GetData()); 
		
		
		
		InsuranceDate = new JLabel("Insurance Date",  SwingConstants.CENTER);
		InsuranceDateInput = new JTextField("dd-mm-yyyy");
		InsuranceDateInput.setSize(400,20);
		PInsuranceDate = new JPanel(new GridLayout(1,2));
		PInsuranceDate.add(InsuranceDate);
		PInsuranceDate.add(InsuranceDateInput);

		
		
		InsuranceDateInput.addFocusListener(new ValidateInsuranceDate()); 
		InsuranceDateInput.addFocusListener(new GetData()); 
		
		
		DateStart = new JLabel("Start Date",  SwingConstants.CENTER);
		DateStartInput = new JTextField("dd-mm-yyyy");
		DateStartInput.setSize(400,20);
		PDateStart = new JPanel(new GridLayout(1,2));
		PDateStart.add(DateStart);
		PDateStart.add(DateStartInput);

		
		
		DateStartInput.addFocusListener(new ValidateStartDate());
		DateStartInput.addFocusListener(new GetData()); 
		
		
		DateEnd = new JLabel("End Date",  SwingConstants.CENTER);
		DateEndInput = new JTextField("dd-mm-yyyy");
		DateEndInput.setSize(400,20);
		PDateEnd = new JPanel(new GridLayout(1,2));
		PDateEnd.add(DateEnd);
		PDateEnd.add(DateEndInput);

		
 
		DateEndInput.addFocusListener(new ValidateEndDate());  
		DateEndInput.addFocusListener(new GetData()); 
		
		
		TimeLeft = new JLabel("Insurance Validity",  SwingConstants.CENTER);
		TimeLeftInput = new JTextField();
		TimeLeftInput.setEditable(false);
		TimeLeftInput.setSize(400,20);
		PTimeLeft = new JPanel(new GridLayout(1,2));
		PTimeLeft.add(TimeLeft);
		PTimeLeft.add(TimeLeftInput);
		
		Calculate = new JButton("Calculate");
		Calculate.addActionListener(new CalculateTimeLeft());
		PCalculate = new JPanel(new FlowLayout());
		PCalculate.add(Calculate);
		
		
		
		InsurancePlan = new JLabel("Insurance Plan",  SwingConstants.CENTER);
		c1 = new JCheckBox(InsurancePlans[0]);
		c1.addActionListener(new GetData());
		c2 = new JCheckBox(InsurancePlans[1]);
		c2.addActionListener(new GetData());
		c3 = new JCheckBox(InsurancePlans[2]);
		c3.addActionListener(new GetData());
		c4 = new JCheckBox(InsurancePlans[3]);
		c4.addActionListener(new GetData());
		c5 = new JCheckBox(InsurancePlans[4]);
		c5.addActionListener(new GetData());
		PInsurancePlan = new JPanel(new GridLayout(1,1));
		PC12 = new JPanel(new GridLayout(1,2));
		PC34 = new JPanel(new GridLayout(1,2));
		PC56 = new JPanel(new GridLayout(1,2));
		PInsurancePlan.add(InsurancePlan);
		PC12.add(c1);
		PC12.add(c2);
		PC34.add(c3);
		PC34.add(c4);
		PC56.add(c5);
		
		
		
		//
		
		
		//Buttons
		
		Clear = new JButton("Clear");
		Clear.setSize(new Dimension(300,40));
		Clear.addActionListener(new ClearAction());
		PClear = new JPanel(new FlowLayout());
		PClear.add(Clear);
		
		
		//
		
		
		
		add(ClientPanel).setBounds(0,0,400,40);
		add(PFName).setBounds(-40,50,400,20);
		add(PLName).setBounds(-40,70,400,20);
		add(PMother).setBounds(-40,90,400,20);
		add(PGender).setBounds(-10,110,400,30);
		add(PDDN).setBounds(-40,150,400,20);
		add(PNationalite).setBounds(-40,170,400,20);
		add(PID).setBounds(-40,190,400,20);
		add(PCity).setBounds(-40,210,400,20);
		add(PRecordNumber).setBounds(-40,230,400,20);
		add(PTelephone).setBounds(-40,250,400,20);
		
		
		add(InsurancePanel).setBounds(0,280,400,40);
		add(PInsuranceNumber).setBounds(-30,340,400,20);
		add(PInsuranceDate).setBounds(-30,360,400,20);
		add(PDateStart).setBounds(-30,400,400,20);
		add(PDateEnd).setBounds(-30,420,400,20);
		add(PTimeLeft).setBounds(-30,440,400,20);
		add(PCalculate).setBounds(277,460,100,31);
		add(PInsurancePlan).setBounds(-20,500,400,20);
		add(PC12).setBounds(0,520,400,30);
		add(PC34).setBounds(0,560,400,30);
		add(PC56).setBounds(0,600,300,30);
		
		add(PClear).setBounds(290,610,100,40);
		
		
	}
	
	
	public class ClearAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			FNameInput.setText("");
			LNameInput.setText("");
			DDNInput.setText("dd-mm-yyyy");
			MotherInput.setText("");
			NationaliteInput.setSelectedItem("");
			TelephoneInput.setText("");
			CityInput.setText("");
			RecordNumberInput.setText("");
			IDInput.setText("");
			GenderGroup.clearSelection();
			InsuranceNumberInput.setText("");
			InsuranceDateInput.setText("dd-mm-yyyy");
			DateStartInput.setText("dd-mm-yyyy");
			DateEndInput.setText("dd-mm-yyyy");
			TimeLeftInput.setText("");
			c1.setSelected(false);
			c2.setSelected(false);
			c3.setSelected(false);
			c4.setSelected(false);
			c5.setSelected(false); 
		}
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
	
	public class CalculateTimeLeft implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			try {
				validateCalcul();
				ClientData.replace("TimeLeftInput", TimeLeftInput.getText());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void validateCalcul() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date1= sdf.parse(DateStartInput.getText());
		Date date2= sdf.parse(DateEndInput.getText());
		
		if(date1.compareTo(date2)>0) {
			TimeLeftInput.setText("Error in Dates' Values");
			DateStartInput.setText("");
			DateEndInput.setText("");
		}
		
		else {

			DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-uuuu");
			
			String dateBeforeString = DateStartInput.getText();
			String dateAfterString = DateEndInput.getText();

			LocalDate dateBefore = LocalDate.parse(dateBeforeString, f);
			LocalDate dateAfter = LocalDate.parse(dateAfterString, f);
			
			long NOD = ChronoUnit.DAYS.between(dateBefore, dateAfter);
			
			if(NOD == 0) {
				TimeLeftInput.setText("This insurance is no longer valid");
				DateStartInput.setText("");
				DateEndInput.setText("");
			}
			
			else {
				TimeLeftInput.setText(NOD +" days left");
			}
		}
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
	
	public class ValidateBirthDate implements FocusListener{

		public void focusLost(FocusEvent e) {
			if(DDNInput.getText().isEmpty()) {
				DDNInput.setText("dd-mm-yyyy");
				}
			String dt = DDNInput.getText();
			DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-uuuu");
			try {
				f.parse(dt);
			}
			
			catch(Exception ev) {
				System.out.println("Error in Birth Date");
				DDNInput.setText("dd-mm-yyyy");
			}
		}
		
		public void focusGained(FocusEvent e) {
			DDNInput.setText("");
			}
		
	}
	
	public class ValidateInsuranceDate implements FocusListener{

		public void focusLost(FocusEvent e) {
			if(InsuranceDateInput.getText().isEmpty()) {
				InsuranceDateInput.setText("dd-mm-yyyy");
				}
			String dt = InsuranceDateInput	.getText();
			DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-uuuu");
			try {
				f.parse(dt);
			}
			
			catch(Exception ev) {
				System.out.println("Error in Insurance Date");
				InsuranceDateInput.setText("dd-mm-yyyy");
			}
		}
		
		public void focusGained(FocusEvent e) {
			InsuranceDateInput.setText("");
			}
		
	}
	
	public class ValidateStartDate implements FocusListener{

		public void focusLost(FocusEvent e) {
			if(DateStartInput.getText().isEmpty()) {
				DateStartInput.setText("dd-mm-yyyy");
				}
			String dt = DateStartInput.getText();
			DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-uuuu");
			try {
				f.parse(dt);
			}
			
			catch(Exception ev) {
				System.out.println("Error in Insurance Start Date");
				DateStartInput.setText("dd-mm-yyyy");
			}
		}
		
		public void focusGained(FocusEvent e) {
			DateStartInput.setText("");
			}
		
	}
	
	public class ValidateEndDate implements FocusListener{

		public void focusLost(FocusEvent e) {
			
			if(DateEndInput.getText().isEmpty()) {
				DateEndInput.setText("dd-mm-yyyy");
				}
			
			String dt = DateEndInput.getText();
			DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-uuuu");
			
			try {
				f.parse(dt);
			}
			
			catch(Exception ev) {
				
				System.out.println("Error in Insurance End Date");
				DateEndInput.setText("dd-mm-yyyy");
				
			}
		}
		
		public void focusGained(FocusEvent e) {
			DateEndInput.setText("");
			}
		
	}
	
	
	public String getCheckbox() {
		String ret="";
			if (c1.isSelected()) ret+=Insurances[0] + "\n";
			if (c2.isSelected()) ret+=Insurances[1] + "\n";
			if (c3.isSelected()) ret+=Insurances[2] + "\n";
			if (c4.isSelected()) ret+=Insurances[3] + "\n";
			if (c5.isSelected()) ret+=Insurances[4] + "\n";
		return ret;
	}
	
	
	
	public class GetData implements FocusListener,ActionListener{
		public void actionPerformed(ActionEvent e) {
			String check="";
			if(e.getSource()==Male) {
				ClientData.replace("GenderInput", "Male");
			}
			
			if(e.getSource()==NationaliteInput) {
				ClientData.replace("NationaliteInput", NationaliteInput.getSelectedItem().toString());
			}
			
			if(e.getSource()==Female) {
				ClientData.replace("GenderInput", "Female");
			}
			
			if(e.getSource()==c1) {
				ClientData.replace("InsurancePlanInput", getCheckbox());
				Ch.add("Compulsory insurance");
			}
			
			else {
				if(!c1.isSelected()) 
					Ch.remove("Compulsory insurance");
			}
			
			if(e.getSource()==c2) {
				ClientData.replace("InsurancePlanInput", getCheckbox());
				Ch.add("Comprehensive coverage");
			}
			
			else {
				if(!c2.isSelected()) 
					Ch.remove("Comprehensive coverage");
			}
			
			if(e.getSource()==c3) {
				ClientData.replace("InsurancePlanInput", getCheckbox());
				Ch.add("Driver's personal insurance");
			}
			
			else {
				if(!c3.isSelected()) 
					Ch.remove("Driver's personal insurance");
			}
			
			if(e.getSource()==c4) {
				ClientData.replace("InsurancePlanInput", getCheckbox());
				Ch.add("L'assistance");
			}
			
			else {
				if(!c4.isSelected()) 
					Ch.remove("L'assistance");
			}
			
			if(e.getSource()==c5) {
				ClientData.replace("InsurancePlanInput", getCheckbox());
				Ch.add("All-risk insurance without and with deductible");
			}
			
			else {
				if(!c5.isSelected()) 
					Ch.remove("All-risk insurance without and with deductible");
			}
		}
		
		
		public void focusGained(FocusEvent e) {}
		public void focusLost(FocusEvent e) {
			
			if(e.getSource()==FNameInput) {
				ClientData.replace("FNameInput", FNameInput.getText());
			}
			
			else if(e.getSource()==LNameInput) {
				ClientData.replace("LNameInput", LNameInput.getText());
			}
			
			else if(e.getSource()==MotherInput) {
				ClientData.replace("MotherInput", MotherInput.getText());
			}
			
			else if(e.getSource()==DDNInput) {
				ClientData.replace("DDNInput", DDNInput.getText());
			}
			
			else if(e.getSource()==IDInput) {
				ClientData.replace("IDInput", IDInput.getText());
			}
			
			else if(e.getSource()==CityInput) {
				ClientData.replace("CityInput", CityInput.getText());
			}
			
			else if(e.getSource()==RecordNumberInput) {
				ClientData.replace("RecordNumberInput", RecordNumberInput.getText());
			}
			
			else if(e.getSource()==TelephoneInput) {
				ClientData.replace("TelephoneInput", TelephoneInput.getText());
			}
			
			else if(e.getSource()==InsuranceNumberInput) {
				ClientData.replace("InsuranceNumberInput", InsuranceNumberInput.getText());
			}
			
			else if(e.getSource()==InsuranceDateInput) {
				ClientData.replace("InsuranceDateInput", InsuranceDateInput.getText());
			}
			
			else if(e.getSource()==DateStartInput) {
				ClientData.replace("DateStartInput", DateStartInput.getText());
			}
			
			else if(e.getSource()==DateEndInput) {
				ClientData.replace("DateEndInput", DateEndInput.getText());
			}
			
			else if(e.getSource()==TimeLeftInput) {
				ClientData.replace("TimeLeftInput", TimeLeftInput.getText());
			}
			
		}
	}
	
}
