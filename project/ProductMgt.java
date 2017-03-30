
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.net.*;
import java.util.*;


class HPT32Frame extends JFrame implements ActionListener{
    
      
      WelcomeWindow window;
      JMenuItem home, add, modify, search, print, exit;
      JMenuBar mb=new JMenuBar();
      JInternalFrame addInternalFrame, modifyInternalFrame, searchInternalFrame;
      JLayeredPane jlayeredpane = new JLayeredPane();
      JLabel label,HomeLabel, slNoLabel, slNoSearchLabel, partNoLabel, partNoSearchLabel, discriptionLabel, timeBetweenOverallHoursLabel,	dateOfExpiryLabel, dateOfFirstInspectionLabel, aircraftRegNoLabel, aircraftRegNoSearchLabel;
      JTextField slNoTextField, slNoSearchTextField, partNoTextField, partNoSearchTextField, discriptionTextField, timeBetweenOverallHoursTextField, dateOfExpiryTextField, dateOfFirstInspectionTextField, aircraftRegNoTextField, aircraftRegNoSearchTextField;
      JTextField mslNoTextField, mslNoSearchTextField, mpartNoTextField, mpartNoSearchTextField, mdiscriptionTextField, mtimeBetweenOverallHoursTextField, mdateOfExpiryTextField, mdateOfFirstInspectionTextField, maircraftRegNoTextField, maircraftRegNoSearchTextField;
      JPanel addPanel, modifyPanel,modifySearchPanel, searchPanel,searchBySlNoPanel,searchByPartNoPanel,searchByAircraftRegNoPanel,searchAllPanel, searchTablePanel,  printPanel;
      JButton addButton,modifyButton,deleteButton, modifySearchButton, searchBySlNoPanelButton, searchByPartNoButton, searchByAircraftRegNoButton,searchAllButton;
      GridBagLayout grid = new GridBagLayout();
      GridBagConstraints gbc1 = new GridBagConstraints();
      JTable searchReportTable;
      int v= ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
      int h= ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
      JScrollPane jsp;
      String query1=" ";
      String text1=" ";
      String text2=" ";
      String text3=" ";
      String text4=" ";
      String text5=" ";
      String text6=" ";
      String text7=" ";
      int idno=0;
      final String[] colHeaders = { "Sl.No.", "Part.No.", "Description                  .. ", "T.B.O.Hours", "Dt.Of Expiry", "Dt.Of FirstInsp", "A/c RegNo" };
      Object[][] data;
      Object[][] data1={ };
      Vector Rows_Data = new  Vector();
      String[] row_data = new String[7];
      String connstr="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)}; DBQ=halhpt.mdb";
     String jdbcDriver="sun.jdbc.odbc.JdbcOdbcDriver";
	 /**
     * The constructor.
     */  
     public HPT32Frame() 
     {
        setTitle("HPT32 A/c Master Record");
        setSize(new Dimension(800, 580));
        Container cpane = getContentPane();
        
        home = new JMenuItem("Home",new ImageIcon("image/home.png"));
        mb.add(home);
    	add = new JMenuItem("Add ",new ImageIcon("image/plus.png"));
    	mb.add(add);
    	modify = new JMenuItem("Modify ",new ImageIcon("image/modify.png"));
    	mb.add(modify);
    	search = new JMenuItem("Search ",new ImageIcon("image/Search.png"));
    	mb.add(search);
    	exit = new JMenuItem("EXIT",new ImageIcon("image/stop.png"));
    	mb.add(exit);
    	setJMenuBar(mb);
    	
    	home.addActionListener(this);
    	add.addActionListener(this);
    	modify.addActionListener(this);
    	search.addActionListener(this);
    	exit.addActionListener(this);
    	
    	HomeLabel = new JLabel("",new ImageIcon("image/homeWallPaper.jpg"), JLabel.CENTER);
    	addInternalFrame = new JInternalFrame("Add Master Part Record");
    	addStatus();
    	modifyInternalFrame = new JInternalFrame("Modify Master Part Record");
    	modifyStatus();
    	searchInternalFrame = new JInternalFrame("Search Master Part Record");
    	searchStatus();
    	
    	HomeLabel.setOpaque(true);
    	addInternalFrame.setOpaque(true);
    	modifyInternalFrame.setOpaque(true);
    	searchInternalFrame.setOpaque(true);
    	
    	jlayeredpane.add(HomeLabel);
    	jlayeredpane.add(addInternalFrame);
    	jlayeredpane.add(modifyInternalFrame);
    	jlayeredpane.add(searchInternalFrame);
    	
    	cpane.add(jlayeredpane);
    	
    	HomeLabel.setBounds(0,0,800,515);
    	addInternalFrame.setBounds(0,0,800,515);
    	modifyInternalFrame.setBounds(0,0,800,515);
    	searchInternalFrame.setBounds(0,0,800,515);
    	
    	addInternalFrame.setVisible(false);
    	modifyInternalFrame.setVisible(false);
    	searchInternalFrame.setVisible(false);
    	
    	
        // Add window listener.
        this.addWindowListener
        (
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    
                    HPT32Frame.this.windowClosed();
                }
            }
        );
        
       
        window = new WelcomeWindow(this);
        window.setSize(700, 300);
        window.setLocation(50, 100);
        window.setVisible(true);
        try
        {
        	Thread.sleep(2000);
        }
        catch(InterruptedException e)
        {
        	JOptionPane.showMessageDialog(null, ""+ e +"","Interrupted Exception ",JOptionPane.INFORMATION_MESSAGE);
        }
        window.setVisible(false);
        remove(window);
        
        
    }
    void addStatus()
    {
    	addPanel = new JPanel();
		addPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
		addPanel.setLayout(grid);
		
    	slNoLabel = new JLabel("Sl.No.");
    	gbc1 = gridlayout(0,0,0,1);
    	grid.setConstraints(slNoLabel, gbc1);
    	addPanel.add(slNoLabel);
    	
    	slNoTextField = new JTextField();
    	gbc1 = gridlayout(200,0,1,1);
    	grid.setConstraints(slNoTextField, gbc1);
    	addPanel.add(slNoTextField);
    	
    	partNoLabel = new JLabel("Part No.");
    	gbc1 = gridlayout(0,0,0,2);
    	grid.setConstraints(partNoLabel, gbc1);
    	addPanel.add(partNoLabel);
    	
    	partNoTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,2);
    	grid.setConstraints(partNoTextField, gbc1);
    	addPanel.add(partNoTextField);
    	
    	discriptionLabel = new JLabel("Discription");
    	gbc1 = gridlayout(0,0,0,3);
    	grid.setConstraints(discriptionLabel, gbc1);
    	addPanel.add(discriptionLabel);
    	
    	discriptionTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,3);
    	grid.setConstraints(discriptionTextField, gbc1);
    	addPanel.add(discriptionTextField);
    	
    	timeBetweenOverallHoursLabel = new JLabel("Time Between Overall Hour's");
    	gbc1 = gridlayout(0,0,0,4);
    	grid.setConstraints(timeBetweenOverallHoursLabel, gbc1);
    	addPanel.add(timeBetweenOverallHoursLabel);
    	
    	timeBetweenOverallHoursTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,4);
    	grid.setConstraints(timeBetweenOverallHoursTextField, gbc1);
    	addPanel.add(timeBetweenOverallHoursTextField);
    	
    	dateOfExpiryLabel = new JLabel("Date Of Expiry");
    	gbc1 = gridlayout(0,0,0,5);
    	grid.setConstraints(dateOfExpiryLabel, gbc1);
    	addPanel.add(dateOfExpiryLabel);
    	
    	dateOfExpiryTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,5);
    	grid.setConstraints(dateOfExpiryTextField, gbc1);
    	addPanel.add(dateOfExpiryTextField);
    	
    	dateOfFirstInspectionLabel = new JLabel("Date Of First Inspection");
    	gbc1 = gridlayout(0,0,0,6);
    	grid.setConstraints(dateOfFirstInspectionLabel, gbc1);
    	addPanel.add(dateOfFirstInspectionLabel);
    	
    	dateOfFirstInspectionTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,6);
    	grid.setConstraints(dateOfFirstInspectionTextField, gbc1);
    	addPanel.add(dateOfFirstInspectionTextField);
    	
    	aircraftRegNoLabel = new JLabel("Aircraft Reg. No.");
    	gbc1 = gridlayout(0,0,0,7);
    	grid.setConstraints(aircraftRegNoLabel, gbc1);
    	addPanel.add(aircraftRegNoLabel);
    	
    	aircraftRegNoTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,7);
    	grid.setConstraints(aircraftRegNoTextField, gbc1);
    	addPanel.add(aircraftRegNoTextField);
    	
    	addButton = new JButton("Add Record");
    	gbc1 = gridlayout(100,0,2,8);
    	grid.setConstraints(addButton, gbc1);
    	addPanel.add(addButton);
    	addButton.addActionListener(this);
    	
    	addInternalFrame.add(addPanel);   	
    	
    	
    }
    void modifyStatus() 
    {
    	modifySearchPanel = new JPanel();
    	modifySearchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
    	modifySearchPanel.setLayout(grid);
    	
    	label = new JLabel("Enter the Serial number :");
    	modifySearchPanel.add(label);
    	slNoSearchLabel = new JLabel("                                        Sl.No.");
    	gbc1 = gridlayout(0,0,0,1);
    	grid.setConstraints(slNoSearchLabel, gbc1);
    	modifySearchPanel.add(slNoSearchLabel);
    	
    	mslNoSearchTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,1);
    	grid.setConstraints(mslNoSearchTextField, gbc1);
    	modifySearchPanel.add(mslNoSearchTextField);
    	
    	partNoSearchLabel = new JLabel("Part No.");
    	gbc1 = gridlayout(0,0,2,1);
    	grid.setConstraints(partNoSearchLabel, gbc1);
    	modifySearchPanel.add(partNoSearchLabel);
    	
    	mpartNoSearchTextField = new JTextField();
    	gbc1 = gridlayout(100,0,3,1);
    	grid.setConstraints(mpartNoSearchTextField, gbc1);
    	modifySearchPanel.add(mpartNoSearchTextField);
    	
    	modifySearchButton = new JButton("Search Record");
    	gbc1 = gridlayout(100,0,4,1);
    	grid.setConstraints(modifySearchButton, gbc1);
    	modifySearchPanel.add(modifySearchButton);
    	modifySearchButton.addActionListener(this);
    	
    	modifyInternalFrame.add(modifySearchPanel);
    	
    	modifySearchPanel.setBounds(0, 0, 800, 100);
    	
    	modifyPanel = new JPanel();
		modifyPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
		modifyPanel.setLayout(grid);
		
    	slNoLabel = new JLabel("Sl.No.");
    	gbc1 = gridlayout(0,0,0,1);
    	grid.setConstraints(slNoLabel, gbc1);
    	modifyPanel.add(slNoLabel);
    	
    	mslNoTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,1);
    	grid.setConstraints(mslNoTextField, gbc1);
    	modifyPanel.add(mslNoTextField);
    	
    	partNoLabel = new JLabel("Part No.");
    	gbc1 = gridlayout(0,0,0,2);
    	grid.setConstraints(partNoLabel, gbc1);
    	modifyPanel.add(partNoLabel);
    	
    	mpartNoTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,2);
    	grid.setConstraints(mpartNoTextField, gbc1);
    	modifyPanel.add(mpartNoTextField);
    	
    	discriptionLabel = new JLabel("Discription");
    	gbc1 = gridlayout(0,0,0,3);
    	grid.setConstraints(discriptionLabel, gbc1);
    	modifyPanel.add(discriptionLabel);
    	
    	mdiscriptionTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,3);
    	grid.setConstraints(mdiscriptionTextField, gbc1);
    	modifyPanel.add(mdiscriptionTextField);
    	
    	timeBetweenOverallHoursLabel = new JLabel("Time Between Overall Hour's");
    	gbc1 = gridlayout(0,0,0,4);
    	grid.setConstraints(timeBetweenOverallHoursLabel, gbc1);
    	modifyPanel.add(timeBetweenOverallHoursLabel);
    	
    	mtimeBetweenOverallHoursTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,4);
    	grid.setConstraints(mtimeBetweenOverallHoursTextField, gbc1);
    	modifyPanel.add(mtimeBetweenOverallHoursTextField);
    	
    	dateOfExpiryLabel = new JLabel("Date Of Expiry");
    	gbc1 = gridlayout(0,0,0,5);
    	grid.setConstraints(dateOfExpiryLabel, gbc1);
    	modifyPanel.add(dateOfExpiryLabel);
    	
    	mdateOfExpiryTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,5);
    	grid.setConstraints(mdateOfExpiryTextField, gbc1);
    	modifyPanel.add(mdateOfExpiryTextField);
    	
    	dateOfFirstInspectionLabel = new JLabel("Date Of First Inspection");
    	gbc1 = gridlayout(0,0,0,6);
    	grid.setConstraints(dateOfFirstInspectionLabel, gbc1);
    	modifyPanel.add(dateOfFirstInspectionLabel);
    	
    	mdateOfFirstInspectionTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,6);
    	grid.setConstraints(mdateOfFirstInspectionTextField, gbc1);
    	modifyPanel.add(mdateOfFirstInspectionTextField);
    	
    	aircraftRegNoLabel = new JLabel("Aircraft Reg. No.");
    	gbc1 = gridlayout(0,0,0,7);
    	grid.setConstraints(aircraftRegNoLabel, gbc1);
    	modifyPanel.add(aircraftRegNoLabel);
    	
    	maircraftRegNoTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,7);
    	grid.setConstraints(maircraftRegNoTextField, gbc1);
    	modifyPanel.add(maircraftRegNoTextField);
    	
    	modifyButton = new JButton("Modify Record");
    	gbc1 = gridlayout(100,0,1,8);
    	grid.setConstraints(modifyButton, gbc1);
    	modifyPanel.add(modifyButton);
    	modifyButton.addActionListener(this);
    	
    	deleteButton = new JButton("Delete Record");
    	gbc1 = gridlayout(100,0,1,9);
    	grid.setConstraints(deleteButton, gbc1);
    	modifyPanel.add(deleteButton);
    	deleteButton.addActionListener(this);
    	
		
		
    	modifyInternalFrame.add(modifyPanel); 
    	modifyPanel.setBounds(0, 100, 800, 415);  	
    }
    void searchStatus()
    {
    	
    	
    	searchBySlNoPanel = new JPanel();
    	searchBySlNoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
    	searchBySlNoPanel.setLayout(grid);
    	
    	slNoSearchLabel = new JLabel("  Sl.No.");
    	gbc1 = gridlayout(0,0,0,1);
    	grid.setConstraints(slNoSearchLabel, gbc1);
    	searchBySlNoPanel.add(slNoSearchLabel);
    	
    	slNoSearchTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,1);
    	grid.setConstraints(slNoSearchTextField, gbc1);
    	searchBySlNoPanel.add(slNoSearchTextField);
    	
    	searchBySlNoPanelButton = new JButton("Search");
    	gbc1 = gridlayout(0,0,1,2);
    	grid.setConstraints(searchBySlNoPanelButton, gbc1);
    	searchBySlNoPanel.add(searchBySlNoPanelButton);
    	searchBySlNoPanelButton.addActionListener(this);
    	
    	searchInternalFrame.add(searchBySlNoPanel);
    	searchBySlNoPanel.setBounds(0, 400, 200, 75);
    	
    	searchByPartNoPanel = new JPanel();
    	searchByPartNoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
    	searchByPartNoPanel.setLayout(grid);
    	
    	partNoSearchLabel = new JLabel("Part No.");
    	gbc1 = gridlayout(0,0,0,1);
    	grid.setConstraints(partNoSearchLabel, gbc1);
    	searchByPartNoPanel.add(partNoSearchLabel);
    	
    	partNoSearchTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,1);
    	grid.setConstraints(partNoSearchTextField, gbc1);
    	searchByPartNoPanel.add(partNoSearchTextField);
    	
    	searchByPartNoButton = new JButton("Search");
    	gbc1 = gridlayout(0,0,1,2);
    	grid.setConstraints(searchByPartNoButton, gbc1);
    	searchByPartNoPanel.add(searchByPartNoButton);
    	searchByPartNoButton.addActionListener(this);
    	
    	searchInternalFrame.add(searchByPartNoPanel);
    	searchByPartNoPanel.setBounds(200, 400, 200, 75);
    	
    	searchByAircraftRegNoPanel = new JPanel();
    	searchByAircraftRegNoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
    	searchByAircraftRegNoPanel.setLayout(grid);
    	
    	aircraftRegNoSearchLabel = new JLabel("Aircraft Reg. No.");
    	gbc1 = gridlayout(0,0,0,1);
    	grid.setConstraints(aircraftRegNoSearchLabel, gbc1);
    	searchByAircraftRegNoPanel.add(aircraftRegNoSearchLabel);
    	
    	aircraftRegNoSearchTextField = new JTextField();
    	gbc1 = gridlayout(100,0,1,1);
    	grid.setConstraints(aircraftRegNoSearchTextField, gbc1);
    	searchByAircraftRegNoPanel.add(aircraftRegNoSearchTextField);
    	
    	searchByAircraftRegNoButton = new JButton("Search");
    	gbc1 = gridlayout(0,0,1,2);
    	grid.setConstraints(searchByAircraftRegNoButton, gbc1);
    	searchByAircraftRegNoPanel.add(searchByAircraftRegNoButton);
    	searchByAircraftRegNoButton.addActionListener(this);
    	
    	searchInternalFrame.add(searchByAircraftRegNoPanel);
    	searchByAircraftRegNoPanel.setBounds(400, 400, 230, 75);
    	
    	searchAllPanel = new JPanel();
    	searchAllPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
    	searchAllPanel.setLayout(grid);
    	
		
		
    	searchAllButton = new JButton("Search All");
    	gbc1 = gridlayout(0,0,100,1);
    	grid.setConstraints(searchAllButton, gbc1);
    	searchAllPanel.add(searchAllButton);
    	searchAllButton.addActionListener(this);
		
		print = new JMenuItem("Print Table",new ImageIcon("image/printer.png"));
    	gbc1 = gridlayout(0,0,100,2);
		grid.setConstraints(print, gbc1);    	
		searchAllPanel.add(print);
    	print.addActionListener(this);
    	
		
    	
		searchInternalFrame.add(searchAllPanel);
    	searchAllPanel.setBounds(630,400, 150, 75);
    	
		
		
    	searchTablePanel = new JPanel();
    	searchTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
    	searchTablePanel.setLayout(grid);
    	
    	searchReportTable = new JTable(data1 ,colHeaders);
    	gbc1 = gridlayout(0, 0, 0, 0);
    	grid.setConstraints(searchReportTable, gbc1);
    	jsp = new JScrollPane(searchReportTable,v,h);
		gbc1 = gridlayout(750, 320, 0, 0);
    	grid.setConstraints(jsp, gbc1);
		searchTablePanel.add(jsp);
		jsp.setBounds(0, 0, 700, 400);
		jsp.setVisible(true);
							
							
    	searchInternalFrame.add(searchTablePanel);
    	
    	searchTablePanel.setBounds(0, 0, 800, 350);
    	searchTablePanel.setVisible(true);
    	  	
    	
    }
    void addRecord()
    {
    	if(text1.length()!=0)
    	{
    		if(text2.length()!=0)
    		{
    			try
    			{
					Class.forName(jdbcDriver);					
					Connection con=DriverManager.getConnection(connstr,"","");
					Statement st = con.createStatement();
					ResultSet result = st.executeQuery("select * from part where slno= '"+ text1 +"' and partno= '"+ text2 +"'");
					if(result.next())
					{
						JOptionPane.showMessageDialog(null, "SlNo. and Part No.already exist.","Warring ",JOptionPane.INFORMATION_MESSAGE);
						
					}
					else
					{
						st.executeUpdate(query1);
						slNoTextField.setText("");
    					partNoTextField.setText("");
    					discriptionTextField.setText("");
    					timeBetweenOverallHoursTextField.setText("");
    					dateOfExpiryTextField.setText("");
    					dateOfFirstInspectionTextField.setText("");
    					aircraftRegNoTextField.setText("");
    					JOptionPane.showMessageDialog(null, "New Record Add Successfully","Massage ",JOptionPane.INFORMATION_MESSAGE);
					}
					con.close(); 				
	    		}
	    		catch(SQLException ex)
	    		{
	   	 			JOptionPane.showMessageDialog(null, "Unable to access the Information"+ ex +"","Warring ",JOptionPane.INFORMATION_MESSAGE);
	   	 			
	    		}
	    		catch(ClassNotFoundException ex)
	    		{
	  	 			JOptionPane.showMessageDialog(null, "Class not found","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}
	    		catch(Exception ex)
	    		{
	  	  			JOptionPane.showMessageDialog(null, "Exception raised is:"+ ex +"","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}	    	
	    	}
	    	else
	    	{
	    		JOptionPane.showMessageDialog(null, "Part No Field is empty","Warring  ",JOptionPane.INFORMATION_MESSAGE);
    		}
     	}
     	else
     	{
    		JOptionPane.showMessageDialog(null, "Sl No Field is empty","Warring  ",JOptionPane.INFORMATION_MESSAGE);
    	}
    }
    void searchRecordForModify()
    {
    			try
    			{
					Class.forName(jdbcDriver);					
					Connection con=DriverManager.getConnection(connstr,"","");
					Statement st = con.createStatement();
					ResultSet result = st.executeQuery(query1);
					if(result.next())
					{
						idno=result.getInt(1);
						text1=result.getString(2);
						text2=result.getString(3);
						text3=result.getString(4);
						text4=result.getString(5);
						text5=result.getString(6);
						text6=result.getString(7);
						text7=result.getString(8);
						
						mslNoTextField.setText(text1);
    					mpartNoTextField.setText(text2);
    					mdiscriptionTextField.setText(text3);
    					mtimeBetweenOverallHoursTextField.setText(text4);
    					mdateOfExpiryTextField.setText(text5);
    					mdateOfFirstInspectionTextField.setText(text6);
    					maircraftRegNoTextField.setText(text7);
    					JOptionPane.showMessageDialog(null, "Record Found Successfully","Massage ",JOptionPane.INFORMATION_MESSAGE);
    		
					}
					else
					{
						JOptionPane.showMessageDialog(null, "There are no such Record exist","Massage ",JOptionPane.INFORMATION_MESSAGE);
					}
					con.close(); 				
	    		}
	    		catch(SQLException ex)
	    		{
	   	 			JOptionPane.showMessageDialog(null, "Unable to access the Information"+ ex +"","Warring ",JOptionPane.INFORMATION_MESSAGE);
	   	 			
	    		}
	    		catch(ClassNotFoundException ex)
	    		{
	  	 			JOptionPane.showMessageDialog(null, "Class not found","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}
	    		catch(Exception ex)
	    		{
	  	  			JOptionPane.showMessageDialog(null, "Exception raised is:"+ ex +"","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}		    	
	    	
    
    }
    void modifyRecord()
    {
    	if(text1.length() !=0)
    	{
    		if(text2.length() !=0)
    		{
    			try
    			{
					Class.forName(jdbcDriver);					
					Connection con=DriverManager.getConnection(connstr,"","");
					Statement st = con.createStatement();
					ResultSet result = st.executeQuery("select * from part where slno= '"+ text1 +"' and partno= '"+ text2 +"'");
					if(result.next())
					{
						JOptionPane.showMessageDialog(null, "SlNo. and Part No.already exist.","Warring  ",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						st.executeUpdate(query1);
						mslNoTextField.setText("");
    					mpartNoTextField.setText("");
    					mdiscriptionTextField.setText("");
    					mtimeBetweenOverallHoursTextField.setText("");
    					mdateOfExpiryTextField.setText("");
    					mdateOfFirstInspectionTextField.setText("");
    					maircraftRegNoTextField.setText("");
    					JOptionPane.showMessageDialog(null, "Record Update Successfully","Massage ",JOptionPane.INFORMATION_MESSAGE);
    				}
					con.close(); 				
	    		}
	    		catch(SQLException ex)
	    		{
	   	 			JOptionPane.showMessageDialog(null, "Unable to access the Information"+ ex +"","Warring ",JOptionPane.INFORMATION_MESSAGE);
	   	 			
	    		}
	    		catch(ClassNotFoundException ex)
	    		{
	  	 			JOptionPane.showMessageDialog(null, "Class not found","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}
	    		catch(Exception ex)
	    		{
	  	  			JOptionPane.showMessageDialog(null, "Exception raised is:"+ ex +"","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}	    	
	    	}
	    	else
	    	{
	    		JOptionPane.showMessageDialog(null, "Part No Field is empty","Warring  ",JOptionPane.INFORMATION_MESSAGE);
    		}
     	}
     	else
     	{
    		JOptionPane.showMessageDialog(null, "Sl No Field is empty","Warring  ",JOptionPane.INFORMATION_MESSAGE);
    	}	    	
    }
    void deleteRecord()
    {
    			try
    			{
					Class.forName(jdbcDriver);					
					Connection con=DriverManager.getConnection(connstr,"","");
					Statement st = con.createStatement();
					st.executeUpdate(query1);
					mslNoTextField.setText("");
    				mpartNoTextField.setText("");
    				mdiscriptionTextField.setText("");
    				mtimeBetweenOverallHoursTextField.setText("");
    				mdateOfExpiryTextField.setText("");
    				mdateOfFirstInspectionTextField.setText("");
    				maircraftRegNoTextField.setText("");
					con.close();
					JOptionPane.showMessageDialog(null, "Record Deleted Successfully","Massage ",JOptionPane.INFORMATION_MESSAGE); 				
	    		}
	    		catch(SQLException ex)
	    		{
	   	 			JOptionPane.showMessageDialog(null, "Unable to access the Information"+ ex +"","Warring ",JOptionPane.INFORMATION_MESSAGE);
	   	 			
	    		}
	    		catch(ClassNotFoundException ex)
	    		{
	  	 			JOptionPane.showMessageDialog(null, "Class not found","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}
	    		catch(Exception ex)
	    		{
	  	  			JOptionPane.showMessageDialog(null, "Exception raised is:"+ ex +"","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}	
    }
    
    void searchRecord()
    {
    			try
    			{
					Class.forName(jdbcDriver);					
					Connection con=DriverManager.getConnection(connstr,"","");
					Statement st = con.createStatement();
					ResultSet result = st.executeQuery(query1);
					Rows_Data.clear();
					int row=0;
					while(result.next())
					{
						row_data=new String[7];
						row=row+1;
						for(int i=0; i < 7; i++)
						{
							row_data[i]=result.getString(i+2);							
						}
						Rows_Data.addElement(row_data);
					}
					if(row != 0)
					{
					JOptionPane.showMessageDialog(null, "Record Found Successfully","Massage ",JOptionPane.INFORMATION_MESSAGE);
    		
					}
					else
					{
						JOptionPane.showMessageDialog(null, "There are no such Record exist","Massage ",JOptionPane.INFORMATION_MESSAGE);
					}
					con.close();
					data= new Object[row][7];
					Rows_Data.copyInto(data);
					jsp.setVisible(false);
					remove(searchReportTable);
					searchReportTable = new JTable(data ,colHeaders);
					jsp = new JScrollPane(searchReportTable,v,h);
					gbc1 = gridlayout(0, 0, 0, 0);
    				grid.setConstraints(searchReportTable, gbc1);
    				jsp = new JScrollPane(searchReportTable,v,h);
					gbc1 = gridlayout(750, 320, 0, 0);
    				grid.setConstraints(jsp, gbc1);
					searchTablePanel.add(jsp);
					jsp.setBounds(0, 0, 780, 400);
					jsp.setVisible(true);
					
	    		}
	    		catch(SQLException ex)
	    		{
	   	 			JOptionPane.showMessageDialog(null, "Unable to access the Information"+ ex +"","Warring ",JOptionPane.INFORMATION_MESSAGE);
	   	 			
	    		}
	    		catch(ClassNotFoundException ex)
	    		{
	  	 			JOptionPane.showMessageDialog(null, "Class not found","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}
	    		catch(Exception ex)
	    		{
	  	  			JOptionPane.showMessageDialog(null, "Exception raised is:"+ ex +"","Warring  ",JOptionPane.INFORMATION_MESSAGE);
	    		}	
    }
    
        
    /**
     * Shutdown procedure when run as an application.
     */
    protected void windowClosed() {
    	
    	// TODO: Check if it is safe to close the application
    	
        // Exit application.
        System.exit(0);
    }
    GridBagConstraints gridlayout(int ix, int iy, int gx, int gy)
    {
    		gbc1.fill= GridBagConstraints.BOTH;
    		gbc1.ipadx = ix;  
			gbc1.ipady = iy;      						
			gbc1.gridx = gx;       						     
			gbc1.gridy = gy; 
			return gbc1;
    }
    public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource()==home)
		{
		 	HomeLabel.setVisible(true);
		 	addInternalFrame.setVisible(false);
		 	modifyInternalFrame.setVisible(false);
		 	searchInternalFrame.setVisible(false);
		}
		if (ae.getSource()==add)
		{
		 	HomeLabel.setVisible(false);
		 	addInternalFrame.setVisible(true);
		 	modifyInternalFrame.setVisible(false);
		 	searchInternalFrame.setVisible(false);
		}
		if (ae.getSource()==modify)
		{
		 	HomeLabel.setVisible(false);
		 	addInternalFrame.setVisible(false);
		 	modifyInternalFrame.setVisible(true);
		 	searchInternalFrame.setVisible(false);
		}
		if (ae.getSource()==search)
		{
		 	HomeLabel.setVisible(false);
		 	addInternalFrame.setVisible(false);
		 	modifyInternalFrame.setVisible(false);
		 	searchInternalFrame.setVisible(true);
		}
		if (ae.getSource()==print)
		{
		 	
		 	MessageFormat headerFmt;
		 	MessageFormat footerFmt;
		 	JTable.PrintMode printMode = JTable.PrintMode.NORMAL;
		 	String text1= new String("HPT-32 Master Part Table");
		 	headerFmt = new MessageFormat(text1);
		 	String text2= new String("Page {0}");
		 	footerFmt = new MessageFormat(text2);
		 	try 
		 	{
		 		
		 		boolean status = searchReportTable.print(printMode, headerFmt, footerFmt);
		 		if (status)
		 		{
		 			JOptionPane.showMessageDialog(null, "Printing Completed","Massage ",JOptionPane.INFORMATION_MESSAGE);
		 		}
		 		else
		 		{
		 			JOptionPane.showMessageDialog(null, "Printing Incompleted","Warring  ",JOptionPane.INFORMATION_MESSAGE);
		 		}
		 	} 
		 	catch (PrinterException pe)
		 	{
		 		JOptionPane.showMessageDialog(null, ""+ pe +"","Warring  ",JOptionPane.INFORMATION_MESSAGE);
		 	}
		 	
		}		
		if (ae.getSource()==addButton)
		{
		 	text1=slNoTextField.getText();
    		text1=text1.trim();
    		text2=partNoTextField.getText();
    		text2=text2.trim();
    		text3=discriptionTextField.getText();
    		text3=text3.trim();
    		text4=timeBetweenOverallHoursTextField.getText();
    		text4=text4.trim();
    		text5=dateOfExpiryTextField.getText();
    		text5=text5.trim();
    		text6=dateOfFirstInspectionTextField.getText();
    		text6=text6.trim();
    		text7=aircraftRegNoTextField.getText(); 
    		text7=text7.trim();
    		query1 ="insert into part(slno, partno, desctext, tbohr, expdate, firstinsp, aircraftreg) values( '"+text1+"' , '"+text2+"' , '"+text3+"' , '"+text4+"' , '"+text5+"' , '"+ text6+"' , '"+text7+"' )";
		 	addRecord();
		}
		if (ae.getSource()==modifyButton)
		{
			
			text1=mslNoTextField.getText();
    		text1=text1.trim();
    		text2=mpartNoTextField.getText();
    		text2=text2.trim();
    		text3=mdiscriptionTextField.getText();
    		text3=text3.trim();
    		text4=mtimeBetweenOverallHoursTextField.getText();
    		text4=text4.trim();
    		text5=mdateOfExpiryTextField.getText();
    		text5=text5.trim();
    		text6=mdateOfFirstInspectionTextField.getText();
    		text6=text6.trim();
    		text7=maircraftRegNoTextField.getText(); 
    		text7=text7.trim();
    		query1 ="UPDATE part SET  slno= '"+ text1 +"', partno= '"+ text2 +"', desctext= '"+ text3 +"', tbohr= '"+text4+"' , expdate= '"+text5+"' , firstinsp= '"+text6+"' , aircraftreg= '"+text7+"'  where id="+idno; 
    		modifyRecord();
		 	
		}
		if (ae.getSource()==deleteButton)
		{
		 	query1 ="DELETE from part where id="+idno;
		 	deleteRecord();
		}
		if (ae.getSource()==modifySearchButton)
		{
			text1=mslNoSearchTextField.getText();
    		text1=text1.trim();
    		text2=mpartNoSearchTextField.getText();
    		text2=text2.trim();
    		query1 = "Select * from part where slno= '"+ text1 +"' and partno= '"+ text2 +"'";
		 	searchRecordForModify();
		}
		if (ae.getSource()==searchBySlNoPanelButton)
		{
		 	text1=slNoSearchTextField.getText();
    		text1=text1.trim();
    		query1 = "Select * from part where slno= '"+ text1 +"'";
    		searchRecord();
		}
		if (ae.getSource()==searchByPartNoButton)
		{
		 	text2=partNoSearchTextField.getText();
    	 	text2=text2.trim();
    	 	query1 = "Select * from part where  partno = '"+ text2 +"'";
    		searchRecord();
		}
		if (ae.getSource()==searchByAircraftRegNoButton)
		{
		 	text7=aircraftRegNoSearchTextField.getText();
			text7=text7.trim();
			query1 = "Select * from part where  aircraftreg = '"+ text7 +"'";
    		searchRecord();
		}
		if (ae.getSource()==searchAllButton)
		{
			query1 = "Select * from part";
		 	searchRecord();
		}
		if (ae.getSource()==exit)
		{
		 	System.exit(0);
		}
		
		
	}
	    
}
public class ProductMgt {
    
    public static void main(String[] args) {
    	
        
      	// Create application frame.
        HPT32Frame frame = new HPT32Frame();
        
        // Show frame.
        frame.show();
        
        
    }
}

class WelcomeWindow  extends Window 
{
	ImageIcon hallogo= new ImageIcon("image/HPT32Logo.JPG");
	JLabel WelcomeLabel = new JLabel("",hallogo,JLabel.CENTER);
    WelcomeWindow(HPT32Frame h)
    {
        super(h);
        setLayout(new BorderLayout());
        add(WelcomeLabel);	   
    }    
    
}


