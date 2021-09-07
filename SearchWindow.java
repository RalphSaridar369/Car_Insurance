import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class SearchWindow extends JFrame{
	
	

	public Font HeaderFont;
	public int styledFont;
	
	
	public JPanel HPSearch;
	public JLabel HSearch;
	
	public JPanel PFile,PSearchF;
	public JLabel File;
	public JTextField FileInput;
	public JButton FileSearch;
	
	public JPanel PSubmit, PClear;
	public JButton Submit, Clear;
	
	public JPanel PIdRecu,PIdReglement, PIdClient, PIdInsurance;
	public JLabel IdRecu, IdReglement, IdClient, IdInsurance;
	public JTextField IdRecuInput, IdReglementInput, IdClientInput, IdInsuranceInput;
	
	public JTable JT;
	public DefaultTableModel TM;
	public String[]Col = {"Client Id","Client Name","Tel","Insurance Type","Accident Date","Prime","Coverage","Inventory"};
	public Object[][]Data = {{}};
	public JScrollPane JSP;
	
	SearchWindow(){
		
		setPreferredSize(new Dimension(500,500));
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		ImageIcon img = new ImageIcon("C:\\Users\\Ralph\\eclipse-workspace\\Project2\\src\\logoco.png");
		setIconImage(img.getImage());
		
		// Search
		
		styledFont = Font.BOLD; 
		HeaderFont = new Font("Helvetica", styledFont , 20);
		
		HSearch = new JLabel("Search Window",  SwingConstants.CENTER);
		HSearch.setFont(HeaderFont);
		HPSearch = new JPanel (new FlowLayout());
		HPSearch.add(HSearch);
		
		
		IdClient = new JLabel("Id Client", SwingConstants.CENTER);
		IdClientInput = new JTextField("");
		IdClientInput.setSize(400,20);
		PIdClient = new JPanel(new GridLayout(1,2));
		PIdClient.add(IdClient);
		PIdClient.add(IdClientInput);
		
		
		IdInsurance = new JLabel("Policy", SwingConstants.CENTER);
		IdInsuranceInput = new JTextField("");
		IdInsuranceInput.setSize(400,20);
		PIdInsurance = new JPanel(new GridLayout(1,2));
		PIdInsurance.add(IdInsurance);
		PIdInsurance.add(IdInsuranceInput);
		
		
		IdReglement = new JLabel("Reglement", SwingConstants.CENTER);
		IdReglementInput = new JTextField("");
		IdReglementInput.setSize(400,20);
		PIdReglement = new JPanel(new GridLayout(1,2));
		PIdReglement.add(IdReglement);
		PIdReglement.add(IdReglementInput);
		
		
		IdRecu = new JLabel("Recu", SwingConstants.CENTER);
		IdRecuInput = new JTextField("");
		IdRecuInput.setSize(400,20);
		PIdRecu = new JPanel(new GridLayout(1,2));
		PIdRecu.add(IdRecu);
		PIdRecu.add(IdRecuInput);
		
		
		
		File = new JLabel("File Search", SwingConstants.CENTER);
		FileInput = new JTextField();
		PFile = new JPanel(new GridLayout(1,2));
		PFile.add(File);
		PFile.add(FileInput);
		
		//
		
		//Buttons
		
		Submit = new JButton("Search");
		PSubmit = new JPanel(new FlowLayout());
		PSubmit.add(Submit);
		
		Clear= new JButton("Clear");
		Clear.addActionListener(new ClearAction());
		PClear = new JPanel(new FlowLayout());
		PClear.add(Clear);

		FileSearch = new JButton("Search");
		FileSearch.addActionListener(new SearchFile());
		PSearchF = new JPanel(new FlowLayout());
		PSearchF.add(FileSearch);
		
		//
		
		//Table
		
		
		TM = new DefaultTableModel(Data,Col);
		JT = new JTable(TM);
		JT.setPreferredScrollableViewportSize(new Dimension(400,60));
		JT.setFillsViewportHeight(true);
		JSP = new JScrollPane(JT);
		
		//
		
		add(HPSearch).setBounds(40,10,400,40);
		add(PIdClient).setBounds(10,60,400,20);
		add(PIdInsurance).setBounds(10,80,400,20);
		add(PIdReglement).setBounds(10,100,400,20);
		add(PIdRecu).setBounds(10,120,400,20);
		add(PFile).setBounds(10,190,400,20);
		add(PSearchF).setBounds(197,215,100,31);
		add(PSubmit).setBounds(197,150,100,31);
		add(PClear).setBounds(327,150,100,31);
		add(JSP).setBounds(20,260,440,160);
	}	
	
	
	public class ClearAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			IdClientInput.setText("");
			IdRecuInput.setText("");
			IdReglementInput.setText("");
			IdInsuranceInput.setText("");
		
			DefaultTableModel model = (DefaultTableModel) JT.getModel();
			model.setRowCount(0);
		}
	}
	
	public class SearchFile implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String FileName = FileInput.getText()+".txt";
			 
			 //if code doesn't work, The path should be changed to the local user.
			
			
			if(new File("C:\\Users\\Ralph\\eclipse-workspace\\ProjectJava\\src\\"+FileName).isFile()) {
				 JOptionPane.showMessageDialog (null, "This File Exists.",
				 "File Found",JOptionPane.INFORMATION_MESSAGE );
				 Desktop desktop = Desktop.getDesktop();  
				 try {
					desktop.open(new File("C:\\Users\\Ralph\\eclipse-workspace\\ProjectJava\\src\\"+FileName));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				 JOptionPane.showMessageDialog (null, "This File Doesn't Exist.",
				 "File Not Found",JOptionPane.ERROR_MESSAGE );
			}
		}
	}
}
