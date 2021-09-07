import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CoverageWindow extends JPanel{

	public Font HeaderFont;
	public int styledFont;
	
	public JLabel HReclamation;
	public JPanel PReclamation;
	
	public JLabel Code, Part, Price;
	public JPanel PCode, PPart, PPrice;
	public JTextField CodeInput, PartInput, PriceInput;
	
	public JTable JTable;
	public DefaultTableModel TModel;
	public String[]Col = {"Code","Part","Price in $"};
	public Object[][]Datat = {{}};
	public JScrollPane JSp;
	
	public JPanel PCalculate;
	public JButton Calculate;
	
	
	public JPanel PTotalPrice;
	public JLabel  TotalPrice;
	public JTextField TotalPriceInput;
	public ArrayList PriceList = new ArrayList();
	
	
	
	public JButton ClearTable, Submit, Clear;
	public JPanel PClearTable, PSubmit, PClear;
	
	public List<Component> CoverageComponents	= new ArrayList<Component>();
	
	
	public Map<String, String> CoverageData = new HashMap<String, String>();
	public String[]ComponentsNames = {"CodeInput","PartInput","PriceInput"};
	
	CoverageWindow(){
		
		super();
		setPreferredSize(new Dimension(550,195));
		setSize(new Dimension(550,195));
		setLayout(null);

		//initializing Map
		
		for(int i=0;i<3;i++) {
			CoverageData.put(ComponentsNames[i],"");
		}
		
		//
		
		// Part
		
		styledFont = Font.BOLD; 
		HeaderFont = new Font("Helvetica", styledFont , 20);
		HReclamation = new JLabel("Report Mecanicien");
		HReclamation.setFont(HeaderFont);
		PReclamation = new JPanel(new FlowLayout());
		PReclamation.add(HReclamation);
		
		
		
		
		Code = new JLabel("Code");
		CodeInput = new JTextField();
		CodeInput .setSize(100,20);
		PCode = new JPanel(new GridLayout(1,2));
		PCode.add(Code);
		PCode.add(CodeInput);
		
		CodeInput.addFocusListener(new NumbersOnly());  
		CodeInput.addKeyListener(new NumbersOnly()); 
		CodeInput.addActionListener(new NumbersOnly());
		CodeInput.addFocusListener(new GetData());
		
		
		
		
		Part = new JLabel("Part");
		PartInput = new JTextField();
		PartInput .setSize(100,20);
		PPart = new JPanel(new GridLayout(1,2));
		PPart.add(Part);
		PPart.add(PartInput);
		
		PartInput.addFocusListener(new GetData());
		
		
		
		
		Price = new JLabel("Price");
		PriceInput = new JTextField();
		PriceInput .setSize(100,20);
		PPrice = new JPanel(new GridLayout(1,2));
		PPrice.add(Price);
		PPrice.add(PriceInput);
		
		PriceInput.addFocusListener(new NumbersOnly());  
		PriceInput.addKeyListener(new NumbersOnly());   
		PriceInput.addActionListener(new NumbersOnly());
		PriceInput.addFocusListener(new GetData());
		
		
		//Total Price
		
		TotalPrice = new JLabel("Total Price");
		TotalPriceInput = new JTextField();
		TotalPriceInput.setEditable(false);
		TotalPriceInput .setSize(100,20);
		PTotalPrice = new JPanel(new GridLayout(1,2));
		PTotalPrice.add(TotalPrice);
		PTotalPrice.add(TotalPriceInput);
		
		
		
		//
		
		// Buttons
		
		ClearTable = new JButton("Clear Table");
		ClearTable.addActionListener(new ClearTableData());
		PClearTable = new JPanel(new FlowLayout());
		PClearTable.add(ClearTable);
		
		Submit = new JButton("Submit");
		Submit.addActionListener(new SubmitData());
		PSubmit = new JPanel(new FlowLayout());
		PSubmit.add(Submit);
		
		Clear = new JButton("Clear");
		Clear.addActionListener(new ClearAction());
		PClear = new JPanel(new FlowLayout());
		PClear.add(Clear);
		
		Calculate = new JButton("Calculate Total Price");
		Calculate.addActionListener(new CalculateTP());
		PCalculate = new JPanel(new FlowLayout());
		PCalculate.add(Calculate);
		
		//
		
		// adding components to List
		
		
		CoverageComponents.add(PCode.getComponent(1));
		CoverageComponents.add(PPart.getComponent(1));
		CoverageComponents.add(PPrice.getComponent(1));
		
		//
		
		// Table
		
		TModel = new DefaultTableModel(Datat,Col);
		JTable = new JTable(TModel);
		JTable.setPreferredScrollableViewportSize(new Dimension(260,80));
		JTable.setFillsViewportHeight(true);
		((DefaultTableModel)JTable.getModel()).removeRow(0);
		JSp = new JScrollPane(JTable);
		
		//
		
		add(PReclamation).setBounds(180,0,180,40);
		add(PCode).setBounds(10,40,200,20);
		add(PPart).setBounds(10,60,200,20);
		add(PPrice).setBounds(10,80,200,20);
		add(PTotalPrice).setBounds(300,160,200,20);
		add(PClearTable).setBounds(100,105,120,31);
		add(PSubmit).setBounds(-15,107,120,31);	
		add(PClear).setBounds(-20,152,120,31);
		add(PCalculate).setBounds(100,152,160,31);
		add(JSp).setBounds(260,40,260,80);
	}	
	
	public class ClearAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			CodeInput.setText("");
			PartInput.setText("");
			PriceInput.setText("");
			TotalPriceInput.setText("");
		}
	}
	
	public class SubmitData implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			
			if(CodeInput.getText().equals("") || PartInput.getText().equals("") || PriceInput.getText().equals("")) {
				 JOptionPane.showMessageDialog (null, "Please fill the inputs.",
				 "Input Error",JOptionPane.ERROR_MESSAGE );
			}
			else {
			String[]data= {CodeInput.getText(),PartInput.getText(),PriceInput.getText()};
			TModel.insertRow(0, data);
			}
		}
	}
			
	public class CalculateTP implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(JTable.getRowCount()>0) {
				TotalPriceInput.setText(CalculateTotalPrice() +" $");
				}
			}
		}
	
	public double CalculateTotalPrice() {
		double somme = 0.0;
		String price = "";
		for(int i=0;i<JTable.getRowCount();i++) {
			price = JTable.getModel().getValueAt(i, 2).toString();
			somme += Double.parseDouble(price);
			}
		return somme;
		}
	
	public class ClearTableData implements ActionListener{
		public void actionPerformed(ActionEvent event) {
		
			DefaultTableModel model = (DefaultTableModel) JTable.getModel();
			model.setRowCount(0);
			
		}
	}
	
	
	public String getTableData() {
		String ret = "";
		String part="";
		String state ="";
		String price = "";
		if (JTable.getRowCount()>0) {
			ret ="\nPart\t\t\tState\t\t\tPrice\n";
			for (int i=0;i<JTable.getRowCount();i++) {
				part = JTable.getModel().getValueAt(i, 0).toString();
				state = JTable.getModel().getValueAt(i, 1).toString();
				price = JTable.getModel().getValueAt(i, 2).toString();
				ret += part +"\t\t\t" +state +"\t\t\t"+price+"$\n";
			}
		}
			return ret;
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
	
	
	public class GetData implements FocusListener{

		public void focusGained(FocusEvent e) {}
		public void focusLost(FocusEvent e) {
			
			if(e.getSource()==PartInput) {
				CoverageData.replace("PartInput", PartInput.getText());
			}
			
			else if(e.getSource()==CodeInput) {
				CoverageData.replace("CodeInput", CodeInput.getText());
			}
			
			else if(e.getSource()==PriceInput) {
				CoverageData.replace("PriceInput", PriceInput.getText());
			}
			
		}
	}
}
