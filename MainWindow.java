import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.border.Border;

public class MainWindow extends JFrame {
	
	public JMenuBar menuBar;
	public JMenu file, edit, help;
	public JMenuItem New, save, exit, ClearAllItem, website;
	
	public ClientWindow clw;
	public CoverageWindow cow;
	public ReportWindow rew;
	public VehicleWindow vew;
	
	public JPanel client;
	public JPanel coverage;
	public JPanel report;
	public JPanel vehicle;


    public Border blackline;
	public JPanel  PClear;
	public JButton Clear;
	
	
	public JPanel PFooter;
	public JButton Save, ClearAll, ClearFooter, Search, CalculeAll;
	public JPanel  PSave, PClearAll, PSearch, PClearFooter, PCalculeAll;
	
	public JPanel PPrime, PCoverage, PMecanicien, PCurrency;
	public JLabel Prime, Coverage, Mecanicien, Currency;
	public JTextField PrimeInput, CoverageInput, MecanicienInput;
	
	public Font FooterFont;
	public int styledFFont;
	
	public String[] IterateOverReportData = {"AccidentLocationInput","AccidentDateInput","AccidentTimeInput","AccidentTypeInput","AccidentExpertInput"};
	
	public MainWindow(){

		super("VIC");
		setPreferredSize(new Dimension(1500,820));
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon("C:\\Users\\Ralph\\eclipse-workspace\\Project2\\src\\logoco.png");
		setIconImage(img.getImage());
		
		menuBar = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");
		ClearAllItem= new JMenuItem("Clear All");
		ClearAllItem.addActionListener(new ClearAllAction());
		help = new JMenu("Help");
		website = new JMenuItem("Visit Website");
		website.addActionListener(new OpenWebsite());
		New= new JMenuItem("New");
		save= new JMenuItem("Save");
		exit= new JMenuItem("Exit");
		
		setJMenuBar(menuBar);
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(help);
		file.add(New);
		file.add(save);
		file.add(exit);
		edit.add(ClearAllItem);
		help.add(website);
		
		// client
		
		blackline = BorderFactory.createLineBorder(Color.black);
		clw = new ClientWindow();
		client = new JPanel(new FlowLayout());
		client.setBorder(blackline);
		client.add(clw);
		
		//
		
		// vehicle
		
		vew = new VehicleWindow();
		vehicle = new JPanel(new FlowLayout());
		vehicle.setBorder(blackline);
		vehicle.add(vew);
		
		//
		
		// report
		
		rew = new ReportWindow();
		report = new JPanel(new FlowLayout());
		report.setBorder(blackline);
		report.add(rew);
		
		//
		
		// coverage
		
		cow = new CoverageWindow();
		coverage = new JPanel(new FlowLayout());
		coverage.setBorder(blackline);
		coverage.add(cow);
		
		
		//
		
		//Footer
		
		
		Save = new JButton ("Save");
		PSave = new JPanel();
		PSave.add(Save);
		
		
		
		CalculeAll= new JButton("Calculate Prime/Coverage");
		CalculeAll.addActionListener(new calculePrime());
		PCalculeAll = new JPanel();
		PCalculeAll.add(CalculeAll);
		
		
		
		ClearAll= new JButton("Clear All");
		ClearAll.addActionListener(new ClearAllAction());
		PClearAll = new JPanel();
		PClearAll.add(ClearAll);
		
		
		
		Search = new JButton("Search");
		Search.addActionListener(new OpenSearchWindow());
		PSearch = new JPanel();
		PSearch.add(Search);
		

		styledFFont = Font.BOLD; 
		FooterFont = new Font("Helvetica", styledFFont , 15);
		
		
		Prime = new JLabel("Premium", SwingConstants.CENTER);
		Prime.setFont(FooterFont);
		PrimeInput = new JTextField();
		PrimeInput.setEditable(false);
		PPrime = new JPanel(new GridLayout(1,2));
		PPrime.add(Prime);
		PPrime.add(PrimeInput);
		
		
		
		Coverage = new JLabel("Coverage", SwingConstants.CENTER);
		Coverage.setFont(FooterFont);
		CoverageInput = new JTextField();
		CoverageInput.setEditable(false);
		PCoverage = new JPanel(new GridLayout(1,2));
		PCoverage.add(Coverage);
		PCoverage.add(CoverageInput);
		
		
		
		Mecanicien = new JLabel("Mecanic Fee", SwingConstants.CENTER);
		Mecanicien.setFont(FooterFont);
		MecanicienInput = new JTextField("0");
		MecanicienInput.addFocusListener(new MechanicPlaceHolder());
		PMecanicien = new JPanel(new GridLayout(1,2));
		PMecanicien.add(Mecanicien);
		PMecanicien.add(MecanicienInput);
		Currency = new JLabel("$");
		Currency.setFont(FooterFont);
		PCurrency = new JPanel( new GridLayout(1,1));
		PCurrency.add(Currency);
		
		ClearFooter = new JButton("Clear");
		PClearFooter = new JPanel(new GridLayout(1,1));
		ClearFooter.addActionListener(new ClearFooter());
		PClearFooter.add(ClearFooter);
		
		PFooter = new JPanel(null);
		PFooter.add(PMecanicien).setBounds(550,10,300,31);
		PFooter.add(PCurrency).setBounds(870,10,50,31);
		PFooter.add(PCoverage).setBounds(350,10,200,31);
		PFooter.add(PPrime).setBounds(150,10,200,31);
		PFooter.add(PClearAll).setBounds(930,8,100,31);
		PFooter.add(PClearFooter).setBounds(20,10,100,31);
		PFooter.add(PSave).setBounds(1030,8,100,31);
		PFooter.add(PSearch).setBounds(1130,8,100,31);
		PFooter.add(PCalculeAll).setBounds(1230,8,200,31);
		
		
		
		PFooter.setBorder(blackline);
		
		
		//
		
		add(client).setBounds(20,20,410,660);
		add(vehicle).setBounds(450,20,430,660);
		add(report).setBounds(900,20,560,438);
		add(coverage).setBounds(900,478,560,202);
		add(PFooter).setBounds(20,700,1440,50);
		
		//this.setBorder(blackline);
		New.addActionListener(new ClearAllAction());
		save.addActionListener(new saveButton());
		Save.addActionListener(new saveButton());
		exit.addActionListener(new exitButton());
		
	}	
	
	private class calculePrime implements FocusListener, ActionListener{
		
		int[]check = new int[5];
		JCheckBox[]CheckBox = new JCheckBox[5];
		
		public void focusGained(FocusEvent e) {}
		
		public void focusLost(FocusEvent e) {}
		
		public void actionPerformed(ActionEvent e) {
			
			System.out.println(clw.Ch);
			
			String[]IP = {"Compulsory insurance", "Comprehensive coverage",
					"Driver's personal insurance", "L'assistance", "All-risk insurance without and with deductible"};
			

			if(vew.VehiclePriceInput.getText().equals("") || (clw.Ch.isEmpty()) || cow.JTable.getRowCount() ==0) {
				 JOptionPane.showMessageDialog (null, "Please insert Vehicle Price/Insurance Type/Bought Parts.",
				 "Input Error",JOptionPane.ERROR_MESSAGE );
			}
			
			else {
				
				double sommePrime = 0.000, ratePrime = 0.000;
				double sommeCover = 0.000, rateCover = 0.000;
			
				if(clw.Ch.contains(IP[0])){
					ratePrime += 0.040;
					rateCover = 0.600;
				}
		
				if(clw.Ch.contains(IP[1])){
					ratePrime += 0.040;
					rateCover = 0.600;
				}
		
				if(clw.Ch.contains(IP[2])){
					ratePrime += 0.050;
					rateCover = 0.700;
				}
		
				if(clw.Ch.contains(IP[3])){
					ratePrime += 0.035;
					rateCover = 0.450;
				}
		
				if(clw.Ch.contains(IP[4])){
					ratePrime += 0.075;
					rateCover = 1.000;
				}
				
				sommePrime = ratePrime * Double.parseDouble(vew.VehiclePriceInput.getText());
				double sommePrimeFinal = Double.parseDouble(new DecimalFormat("##.###").format(sommePrime));
				PrimeInput.setText(sommePrimeFinal+ " $");
				
				sommeCover = rateCover * (cow.CalculateTotalPrice() + Double.parseDouble(MecanicienInput.getText()));
				double sommeCoverFinal = Double.parseDouble(new DecimalFormat("##.###").format(sommeCover));
				CoverageInput.setText(sommeCoverFinal+ " $");
				
				System.out.println("prime:  " + ratePrime + "  " + sommePrime);
				System.out.println("cover:  " + rateCover + "  " + sommeCover);
				
				
			}
		}
	}
	
	private class OpenWebsite implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String web = "https://www.google.com";
			try {
				java.awt.Desktop.getDesktop().browse(java.net.URI.create(web));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private class saveButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			boolean checkEntries =true;
			
			// checking inputs of client details
		    Iterator cw = clw.ClientData.entrySet().iterator();
		    while(cw.hasNext()) {
		        Map.Entry pair = (Map.Entry)cw.next();
		        if(pair.getValue()=="") {
		        	checkEntries = false;
		        	}
		    	}
			//
			
			// checking inputs of vehicle details
		    Iterator vd = vew.VehicleData.entrySet().iterator();
		    while(vd.hasNext()) {
		        Map.Entry pair = (Map.Entry)vd.next();
		        if(pair.getValue()=="") {
		        	checkEntries = false;
		        	}
		    	}
			//
		    
			// checking inputs of accident report
		    Iterator rd = rew.ReportData.entrySet().iterator();
		    while(rd.hasNext()) {
		        Map.Entry pair = (Map.Entry)rd.next();
		        if(pair.getValue()=="") {
		        	checkEntries = false;
		        	}
		    	}
		    
		    if(rew.JTable.getRowCount()==0) {
	        	checkEntries = false;
		    }
		    
		    if(cow.JTable.getRowCount()==0) {
	        	checkEntries = false;
		    }
		    
		    if(MecanicienInput.getText().equals("")){
		    	checkEntries = false;
		    }
		    
		    if(PrimeInput.getText().equals("")) {
		    	checkEntries = false;
		    }
		    
		    if(CoverageInput.getText().equals("")) {
		    	checkEntries = false;
		    }
		    
		    //
		    
		    if(checkEntries==false) {
				 JOptionPane.showMessageDialog (null, "Please fill all input fields.",
				 "Input Error",JOptionPane.ERROR_MESSAGE );
		    }
		    
		    else {
				 String Fn =  JOptionPane.showInputDialog (null, "Enter file name: ");
				 String Rc =  JOptionPane.showInputDialog (null, "Enter number receipt: ");
				 Fn += ".txt";
				 
				 //if code doesn't work, The path should be changed to the local user.
				 
				 File fn = new File("C:\\Users\\Ralph\\eclipse-workspace\\ProjectJava\\src\\"+Fn);
				 try {
					
					// Write for Client 

					FileWriter fw = new FileWriter(fn);
					fw.write("Receipt Number:    "+Rc+"\n");
					for(int i=0;i<15;i++) {
						fw.write("=");
					}
					
					fw.write("\n\n\nClient\n"); 
					
					for(int i=0;i<15;i++) {
						fw.write("=");
					}
					
					fw.write("\n\n\n\nFirst Name:   " + clw.FNameInput.getText() +"\n");
					fw.write("Last Name:   " + clw.LNameInput.getText() +"\n");
					fw.write("Mother's fullname:   " + clw.MotherInput.getText() +"\n");
					fw.write("Gender:   "); if(clw.Male.isSelected())fw.write("Male\n");else fw.write("Female\n");
					fw.write("Date of birth:   " + clw.DDNInput.getText() +"\n");
					fw.write("Nationality:   " + clw.NationaliteInput.getSelectedItem().toString() +"\n");
					fw.write("Client's Id:   " + clw.IDInput.getText() +"\n");
					fw.write("City:   " + clw.CityInput.getText() +"\n");
					fw.write("Client's Record Number:   " + clw.RecordNumberInput.getText() +"\n");
					fw.write("Telephone:   " + clw.TelephoneInput.getText() +"\n");
					
					//
					
					
					// Write for Insurance

					fw.write("\n\n\n\nInsurance\n");
					
					for(int i=0;i<15;i++) {
						fw.write("=");
					}	
					
					fw.write("\n\n\n\nInsurance Number:    " + clw.InsuranceNumberInput.getText() + "\n");
					fw.write("Insurance Date:    " + clw.InsuranceDateInput.getText() + "\n");
					fw.write("Insurance Start Date:    " + clw.DateStartInput.getText() + "\n");
					fw.write("Insurance End Date:    " + clw.DateEndInput.getText() + "\n");
					fw.write("Days Left:    " + clw.TimeLeftInput.getText() + "\n");
					fw.write("Insurance Plan(s):   \n\n" + clw.getCheckbox() + "\n");
					
					//
					
					
					
					
					// Write for Vehicle

					fw.write("\n\n\n\nVehicle\n");
					
					for(int i=0;i<15;i++) {
						fw.write("=");
					}	

					fw.write("\n\nVehicle Brand:    " + vew.VehicleBrandInput.getSelectedItem().toString() + "\n");
					fw.write("Vehicle Model:    " + vew.VehicleModelInput.getText() + "\n");
					fw.write("Vehicle Brand:    " + vew.VehicleTypeInput.getSelectedItem().toString() + "\n");
					fw.write("Year of Manufactor:    " + vew.YearManufactorInput.getText() + "\n");
					fw.write("Vehicle Price:    " + vew.VehiclePriceInput.getText() + "$\n");
					fw.write("Motor Number:    " + vew.MotorNumberInput.getText() + "\n");
					fw.write("Motor Power:    " + vew.MotorPowerInput.getText() + "Kw\n");
					fw.write("Motor Number:    " + vew.MotorHorsePowerInput.getText() + "Hp\n");
					fw.write("Motor Power:    " + vew.MotorPistonsNumberInput.getText() + "\n");
					fw.write("\nExisting Damages:    \n\n" + vew.getTableData() + "\n\n");
					fw.write("Licence Number:    " + vew.LicenceNumberInput.getText() + "\n");
					fw.write("Licence Type:    " + vew.LicenceTypeInput.getSelectedItem().toString() + "\n");
					fw.write("Licence Date:    " + vew.LicenceDateInput.getText() + "\n");
					
					//
					
					
					
					// Write for Accident Report 

					fw.write("\n\n\n\nAccident\n");
					
					for(int i=0;i<15;i++) {
						fw.write("=");
					}

					fw.write("\n\nAccident Location:    " + rew.AccidentLocationInput.getText() + "\n");
					fw.write("Accident Date:    " + rew.AccidentDateInput.getText() + "\n");
					fw.write("Accident Time:    " + rew.AccidentTimeInput.getText() + "\n");
					fw.write("Accident Type:    " + rew.AccidentTypeInput.getSelectedItem().toString() + "\n");
					fw.write("Accident Expert:    " + rew.AccidentExpertInput.getText() + "\n");
					fw.write("\nDamaged Parts:   \n\n" + rew.getTableData() + "\n");
					//
					
					
					
					// Write for Mecanic Report

					fw.write("\n\n\n\nMecanic\n");
					
					for(int i=0;i<15;i++) {
						fw.write("=");
					}

					fw.write("\n\nBought Parts:    \n\n" + cow.getTableData() + "\n\n");
					fw.write("Mecancic Fees:    " + MecanicienInput.getText() + "$\n\n");
					
					//
					
					// Write for Payments

					fw.write("\n\n\n\nPremium/Coverage\n");
					
					for(int i=0;i<15;i++) {
						fw.write("=");
					}

					fw.write("\n\nPremium:    " + PrimeInput.getText() + "\n");
					fw.write("Coverage:    " + CoverageInput.getText() + "\n");
					
					
					//
					
					fw.close();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
		      } 
			}
		}
	
	private class exitButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		}
	
	public class ClearFooter implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			MecanicienInput.setText("");
			PrimeInput.setText("");
			CoverageInput.setText("");
		}
	}
	
	private class ClearAllAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			String Message="";
			Object ob = e.getSource();
			if(ob==ClearAll || ob==ClearAllItem) {
				Message = "Are you sure you want to clear the window ?";
			}
			
			if(ob==New) {
				Message = "Are you sure you want to open a new file ?";
			}
			int choice = JOptionPane.showConfirmDialog(null,Message);
			
			if(choice == JOptionPane.YES_OPTION) {

			if(ob==ClearAll || ob==ClearAllItem) 
				setVisible(false);
            try {
				Thread.sleep(1000);
            	} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
            
			MainWindow main = new MainWindow();
			main.setVisible(true);
			main.setSize(new Dimension(1500,820));
			main.setPreferredSize(new Dimension(1500,820));
			main.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			
				}
			}
		
		
		}
	
	private class OpenSearchWindow implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				SearchWindow d = new SearchWindow();
				d.setVisible(true);
				d.setSize (500,500);
				}
			}
	
	private class MechanicPlaceHolder implements FocusListener{
		
		public void focusLost(FocusEvent e) {
			if(MecanicienInput.getText().equals("")) {
				MecanicienInput.setText("0");
				}
			}
		
			public void focusGained(FocusEvent e) {
				MecanicienInput.setText("");
				}
			}
	}
	

