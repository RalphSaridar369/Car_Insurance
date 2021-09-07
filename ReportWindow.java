import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReportWindow extends JPanel{

	public Font HeaderFont;
	public int styledFont;
	public int i = 1;
	
	public JPanel PAccident, PDamaged;
	public JLabel HAccident, HDamaged;
	
	
	public JLabel AccidentLocation, AccidentDate, AccidentTime, AccidentType, AccidentExpert;
	public JTextField  AccidentLocationInput, AccidentDateInput, AccidentTimeInput, AccidentExpertInput;
	
	
	public JComboBox AccidentTypeInput;
	public String[]AccidentTypes = {"","Vehicle roll over","Head-on collision","Rear-end collision","Side Impact Collision"};
	
	public JPanel PAccidentLocation, PAccidentDate, PAccidentTime, PAccidentExpert, PAccidentType;
	public JPanel  PClear;
	public JButton Clear;
	
	public JPanel PDamagedPart, PDamagedState, PSubmit, PClearTable;
	public JButton Submit, ClearTable;
	public JLabel DamagedPart, DamagedState;
	public JTextField DamagedPartInput;
	public JComboBox DamagedStateInput;
	public String[]DamagedStates = {"","Good Condition","Bad Condition","Very Bad Condition"};
	
	public JTable JTable;
	public DefaultTableModel TModel;
	public String[]Col = {"Part","State"};
	public Object[][]Datat = {{}};
	public JScrollPane JSp;
	
	
	public List<Component> ReportComponents = new ArrayList<Component>();
	
	
	public Map<String, String> ReportData = new HashMap<String, String>();
	public String[]ComponentsNames = {"AccidentLocationInput","AccidentDateInput","AccidentTimeInput","AccidentTypeInput","AccidentExpertInput"};
	
	
	ReportWindow(){
		
		super();
		setPreferredSize(new Dimension(550,430));
		setSize(new Dimension(550,430));
		setLayout(null);
		
		
		//initializing Map
		
		for(int i=0;i<ComponentsNames.length;i++) {
			ReportData.put(ComponentsNames[i],"");
		}
		
		//
		
		
		ClientWindow abc = new ClientWindow();
		
		// Accident
		
		styledFont = Font.BOLD; 
		HeaderFont = new Font("Helvetica", styledFont , 20);
		HAccident = new JLabel("Accident Details",  SwingConstants.CENTER);
		HAccident.setFont(HeaderFont);
		PAccident= new JPanel(new FlowLayout());
		PAccident.add(HAccident);
		
		
		
		
		
		AccidentLocation = new JLabel("Location of the Accident",  SwingConstants.CENTER);
		AccidentLocationInput = new JTextField();
		AccidentLocationInput .setSize(400,20);
		PAccidentLocation = new JPanel(new GridLayout(1,2));
		PAccidentLocation.add(AccidentLocation);
		PAccidentLocation.add(AccidentLocationInput);
		
		AccidentLocationInput.addFocusListener(new GetData());
		
		
		
		
		
		AccidentDate = new JLabel("Date",  SwingConstants.CENTER);
		AccidentDateInput = new JTextField("dd-mm-yyyy");
		AccidentDateInput .setSize(400,20);
		PAccidentDate = new JPanel(new GridLayout(1,2));
		PAccidentDate.add(AccidentDate);
		PAccidentDate.add(AccidentDateInput);

		
		AccidentDateInput.addFocusListener(new GetData());
		AccidentDateInput.addFocusListener(new ValidateAccidentDate());
		
		
		
		
		
		AccidentTime = new JLabel("Time",  SwingConstants.CENTER);
		AccidentTimeInput = new JTextField("hh:mm");
		AccidentTimeInput .setSize(400,30);
		PAccidentTime = new JPanel(new GridLayout(1,2));
		PAccidentTime.add(AccidentTime);
		PAccidentTime.add(AccidentTimeInput);

		AccidentTimeInput.addFocusListener(new GetData());
		AccidentTimeInput.addFocusListener(new ValidateAccidentTime());  
		
		
		AccidentType = new JLabel("Accident Type",  SwingConstants.CENTER);	
		AccidentTypeInput = new JComboBox(AccidentTypes);
		PAccidentType = new JPanel(new GridLayout(1,2));
		PAccidentType.add(AccidentType);
		PAccidentType.add(AccidentTypeInput);
		
		AccidentTypeInput.addActionListener(new GetData());
		
		
		
		
		AccidentExpert = new JLabel("Dealt By Expert",  SwingConstants.CENTER);	
		AccidentExpertInput = new JTextField();
		PAccidentExpert = new JPanel(new GridLayout(1,2));
		PAccidentExpert.add(AccidentExpert);
		PAccidentExpert.add(AccidentExpertInput);
		
		AccidentExpertInput.addFocusListener(new LettersOnly());  
		AccidentExpertInput.addKeyListener(new LettersOnly()); 
		AccidentExpertInput.addActionListener(new LettersOnly());
		AccidentExpertInput.addFocusListener(new GetData());
		
		//
		
		//Damaged Parts
		
		
		HDamaged = new JLabel("Damaged Parts",  SwingConstants.RIGHT);
		HDamaged.setFont(HeaderFont);
		PDamaged= new JPanel(new FlowLayout());
		PDamaged.add(HDamaged);
		
		
		
		
		
		DamagedPart = new JLabel("Part",  SwingConstants.CENTER);
		DamagedPartInput = new JTextField();
		DamagedPartInput .setSize(400,20);
		PDamagedPart = new JPanel(new GridLayout(1,2));
		PDamagedPart.add(DamagedPart);
		PDamagedPart.add(DamagedPartInput);
		
		
		
		
		DamagedState = new JLabel("State",  SwingConstants.CENTER);
		DamagedStateInput = new JComboBox(DamagedStates);
		DamagedStateInput .setSize(400,20);
		PDamagedState = new JPanel(new GridLayout(1,2));
		PDamagedState.add(DamagedState);
		PDamagedState.add(DamagedStateInput);
		
		
		Submit = new JButton("Submit");
		Submit.addActionListener(new SubmitData());
		PSubmit = new JPanel(new FlowLayout());
		PSubmit.add(Submit);
		
		ClearTable = new JButton("Clear Table");
		ClearTable.addActionListener(new ClearTableData());
		PClearTable = new JPanel(new FlowLayout());
		PClearTable.add(ClearTable);
		
		
		TModel = new DefaultTableModel(Datat,Col);
		JTable = new JTable(TModel);
		JTable.setPreferredScrollableViewportSize(new Dimension(400,80));
		JTable.setFillsViewportHeight(true);
		((DefaultTableModel)JTable.getModel()).removeRow(0);
		JSp = new JScrollPane(JTable);
		
		
		//
		
		//Buttons
		
		Clear = new JButton("Clear");
		Clear.addActionListener(new ClearAction());
		PClear = new JPanel(new FlowLayout());
		PClear.add(Clear);
		
		//
		
		add(PAccident).setBounds(150,0,200,40);
		add(PAccidentLocation).setBounds(-20,50,400,20);
		add(PAccidentDate).setBounds(-20,70,400,20);
		add(PAccidentTime).setBounds(-20,90,400,30);
		add(PAccidentType).setBounds(-20,125,400,20);
		add(PAccidentExpert).setBounds(-20,150,400,20);
		
		
		
		add(PDamaged).setBounds(50,180,400,40);
		add(PDamagedPart).setBounds(-30,220,400,20);
		add(PDamagedState).setBounds(-30,240,400,20);
		add(PSubmit).setBounds(158,271,100,32);
		add(PClearTable).setBounds(270,270,100,32);
		add(JSp).setBounds(45,310,400,80);
		add(PClear).setBounds(470,0,80,40);
		
		
	}
	
	public class NumbersOnly implements  FocusListener, KeyListener, ActionListener{
		
		public void actionPerformed(ActionEvent event) {
		}
		
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
		
		public void actionPerformed(ActionEvent event) {
		}
		
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
	
	public class ClearAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			AccidentLocationInput.setText("");
			AccidentDateInput.setText("dd-mm-yyyy");
			AccidentTimeInput.setText("hh:mm");
			AccidentExpertInput.setText("");
			DamagedPartInput.setText("");
			AccidentTypeInput.setSelectedItem("");
			DamagedStateInput.setSelectedItem(""); 
		}
	}
	
	public class SubmitData implements ActionListener{
		public void actionPerformed(ActionEvent event) {

			if(DamagedPartInput.getText()!="" && String.valueOf(DamagedStateInput.getSelectedItem())!="") {
			String[]data= {DamagedPartInput.getText(),String.valueOf(DamagedStateInput.getSelectedItem())};
			TModel.insertRow(0, data);
	        }
			else {
				 JOptionPane.showMessageDialog (null, "Please fill the inputs.",
				 "Input Error",JOptionPane.ERROR_MESSAGE );
			}
		}
	}
	
	
	public String getTableData() {
		String ret = "";
		String part="";
		String state ="";
		if (JTable.getRowCount()>0) {
			ret +="\nPart\t\t\tState\n";
			for (int i=0;i<JTable.getRowCount();i++) {
				part = JTable.getModel().getValueAt(i, 0).toString();
				state = JTable.getModel().getValueAt(i, 1).toString();
				ret += part +"\t\t\t" +state +"\n";
			}
		}
			return ret;
	}
	
	public class ClearTableData implements ActionListener{
		public void actionPerformed(ActionEvent event) {
		
			DefaultTableModel model = (DefaultTableModel) JTable.getModel();
			model.setRowCount(0);
			
		}
	}
	
	public class ValidateAccidentDate implements FocusListener{

		public void focusLost(FocusEvent e) {
			String dt = AccidentDateInput.getText();
			DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-uuuu");
			try {
				f.parse(dt);
			}
			
			catch(Exception ev) {
				System.out.println("Error in Licence Date");
				AccidentDateInput.setText("dd-mm-yyyy");
			}
		}
		
		public void focusGained(FocusEvent e) {
			AccidentDateInput.setText("");
			}
	}
	
	public class ValidateAccidentTime implements FocusListener{

		public void focusLost(FocusEvent e) {
			String dt = AccidentTimeInput.getText();
			try {
				LocalTime.parse(dt);
			}
			
			catch(Exception ev) {
				System.out.println("Error in Accident Time");
				AccidentTimeInput.setText("hh:mm");
			}
		}
		
		public void focusGained(FocusEvent e) {
			AccidentTimeInput.setText("");
			}
	}
	
	public class GetData implements FocusListener,ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==AccidentTypeInput) {
				ReportData.replace("AccidentTypeInput", AccidentTypeInput.getSelectedItem().toString());
			}
			
		}
		
		public void focusGained(FocusEvent e) {}
		public void focusLost(FocusEvent e) {
			
			if(e.getSource()==AccidentLocationInput) {
				ReportData.replace("AccidentLocationInput", AccidentLocationInput.getText());
			}
			
			else if(e.getSource()==AccidentDateInput) {
				ReportData.replace("AccidentDateInput", AccidentDateInput.getText());
			}
			
			else if(e.getSource()==AccidentTimeInput) {
				ReportData.replace("AccidentTimeInput", AccidentTimeInput.getText());
			}
			
			else if(e.getSource()==AccidentExpertInput) {
				ReportData.replace("AccidentExpertInput", AccidentExpertInput.getText());
			}
			
			
		}
	}
}