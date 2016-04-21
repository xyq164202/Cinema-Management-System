package com.db;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;


public class MemberActionFrame extends JFrame {

	private JPanel contentPane;
	//checkbox
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberActionFrame frame = new MemberActionFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		});
	}

	
	//connection with sql
	Connection connection = null;
	private JMenuBar menuBar;
	private JTextField textFieldDirectorSearch;
	private JTextField textFieldWriterSearch;
	private JTable tableMovies;
	private JTextField textFieldGenreSearch;
	private JTextField textFieldTitleSearch;
	private JTextField textFieldActorSearch;
	private JTextField textFieldDate;
	private JTable tableShowings;
	private JTable table;
	private JTable receiptIfoTable;
	private JTextField NumOfTickets;
	//refresh Table
	
	public void refreshTable1()
	{
		try {
			
			String query = "SELECT TITLE, RELEASE_DATE, MOVIE_LENGTH,GENRES FROM MOVIES";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableMovies.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//fillcombobox for name
	
	
	public void loadList()
	{
		try {
			String query = "SELECT * FROM EMPLOYEES";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			DefaultListModel DLM = new DefaultListModel();
			while(rs.next())
			{
				DLM.addElement(rs.getString("Name_Employee"));	
				
			}
			pst.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public MemberActionFrame() {
		//connection = sqliteConnection.dbConnector();
		//Connection conn = DBConnection.GetConnection();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1086, 634);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnNew = new JMenu("New");
		mnNew.setIcon(new ImageIcon("/Users/shiyangli/Documents/workspace/cs425project/img/ok.png"));
		mnFile.add(mnNew);
		
		JMenu mnJavaProject = new JMenu("Java Project");
		mnNew.add(mnJavaProject);
		
		JMenu mnProject = new JMenu("Project");
		mnNew.add(mnProject);
		
		JMenuItem mntmOpenFile = new JMenuItem("Open File");
		mnFile.add(mntmOpenFile);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mnFile.add(mntmClose);
		
		JMenuItem mntmCloseAll = new JMenuItem("Close All");
		mnFile.add(mntmCloseAll);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 1074, 578);
		contentPane.add(tabbedPane);
		
		JPanel panelMovies = new JPanel();
		tabbedPane.addTab("Movies", null, panelMovies, null);
		panelMovies.setLayout(null);
		
		textFieldDirectorSearch = new JTextField();
		textFieldDirectorSearch.setBounds(492, 32, 134, 28);
		panelMovies.add(textFieldDirectorSearch);
		textFieldDirectorSearch.setColumns(10);
		
		textFieldWriterSearch = new JTextField();
		textFieldWriterSearch.setBounds(702, 32, 134, 28);
		panelMovies.add(textFieldWriterSearch);
		textFieldWriterSearch.setColumns(10);
		
		tableMovies = new JTable();
		tableMovies.setBounds(24, 134, 1010, 356);
		panelMovies.add(tableMovies);
		
		
		
		textFieldGenreSearch = new JTextField();
		textFieldGenreSearch.setBounds(900, 32, 134, 28);
		panelMovies.add(textFieldGenreSearch);
		textFieldGenreSearch.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Title");
		lblNewLabel.setBounds(24, 38, 61, 16);
		panelMovies.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Actor");
		lblNewLabel_1.setBounds(218, 38, 61, 16);
		panelMovies.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Director");
		lblNewLabel_2.setBounds(419, 38, 61, 16);
		panelMovies.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Writer");
		lblNewLabel_3.setBounds(638, 38, 61, 16);
		panelMovies.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Genre");
		lblNewLabel_4.setBounds(848, 38, 61, 16);
		panelMovies.add(lblNewLabel_4);
		
		textFieldTitleSearch = new JTextField();
		textFieldTitleSearch.setBounds(72, 32, 134, 28);
		panelMovies.add(textFieldTitleSearch);
		textFieldTitleSearch.setColumns(10);
		
		textFieldActorSearch = new JTextField();
		textFieldActorSearch.setBounds(273, 32, 134, 28);
		panelMovies.add(textFieldActorSearch);
		textFieldActorSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sqlTitle, sqlActor, sqlDirector, sqlWriter, sqlGenre;
				try {
				    //String selection = (String)comboBoxSelection.getSelectedItem();  
				    //if(chckbxActor.getText()){}
					int i = 0, j= 0,q =0; 
					if(textFieldTitleSearch.getText()==null || textFieldTitleSearch.getText().equals("")){
						i = 11;
						sqlTitle = " /*OR MS.TITLE LIKE NULL*/ " //"OR TITLE LIKE NULL"
								;
						}
					else { 
					
					sqlTitle = " AND LOWER(MS.TITLE) LIKE '%"+ textFieldTitleSearch.getText().toLowerCase() + "%'" ;
					i = 12;
					}
					System.out.println(i);
					System.out.println(textFieldTitleSearch.getText());
					

					if(textFieldActorSearch.getText() == null || textFieldActorSearch.getText().equals("")){
						j=21;
						sqlActor = " /* OR POF.NAME_PERSON LIKE NULL */ "  //"OR Actor LIKE NULL"
								;
					}
					else { 
						j=22;
						sqlActor = " AND LOWER(POF.NAME_PERSON) LIKE '%"+ textFieldActorSearch.getText().toLowerCase() + "%' AND CM.ROLES_CAST_MOVIE LIKE '%Actor%'" ;
						
							;
					}
					System.out.println(j);
					System.out.println(textFieldActorSearch.getText());
					
					if(textFieldDirectorSearch.getText() == null || textFieldDirectorSearch.getText().equals("")){
						q=31;
						sqlDirector = " /**/ "//"OR Director LIKE NULL"
								;
					}
					else {
						q=32;
						sqlDirector = " AND LOWER(POF.NAME_PERSON) LIKE '%"+ textFieldDirectorSearch.getText().toLowerCase() + "%' AND CM.ROLES_CAST_MOVIE LIKE '%Director%'" ;

							;
					}
					System.out.println(q);
					System.out.println(textFieldDirectorSearch.getText());
					

					if(textFieldWriterSearch.getText() == null || textFieldWriterSearch.getText().equals("")){
						sqlWriter = " /**/ " ;//"OR Writer LIKE NULL"
					}
					else {
						sqlWriter = " AND LOWER(POF.NAME_PERSON) LIKE '%"+ textFieldWriterSearch.getText().toLowerCase() + "%' AND CM.ROLES_CAST_MOVIE LIKE '%Writer%'"; 
						
							;
					}
					if(textFieldGenreSearch.getText() == null || textFieldGenreSearch.getText().equals("")){
						sqlGenre = " /**/ "; //"OR Genre LIKE NULL"
					}
					else { sqlGenre = " AND LOWER(MS.GENRES) LIKE '%"+ textFieldGenreSearch.getText().toLowerCase() + "%'" ;
						
							;
					}
					System.out.println(textFieldGenreSearch.getText());
					
				String query = "SELECT MS.TITLE, MS.RELEASE_DATE, MS.MOVIE_LENGTH, POF.NAME_PERSON, POF.GENDER, CM.ROLES_CAST_MOVIE, POF.DATE_OF_BIRTH, MS.MPAA_RATING, MS.GENRES"
				+ " From CAST_MOVIE CM, PEOPLE_OF_FILM POF, MOVIES MS"
				+ " WHERE CM.NAME_PERSON = POF.NAME_PERSON AND CM.DATE_OF_BIRTH = POF.DATE_OF_BIRTH AND MS.TITLE = CM.TITLE"
				+ 
				//" AND LOWER(MS.TITLE) LIKE '%"+ textFieldTitleSearch.getText().toLowerCase() + "%'"
				sqlTitle 
				+ sqlActor 
				+ sqlDirector 
				+ sqlWriter 
				+ sqlGenre
				;
				

				    System.out.println(query);
				    //String query = "SELECT * FROM EMPLOYEEINFO WHERE '"+ selection +"' = ? ", where if use SELECT * FROM EMPLOYEEINFO WHERE 'Name' = ?
				        PreparedStatement pst = connection.prepareStatement(query);
				    //pst.setString(1, textFieldTitleSearch.getText() ); 
				    ResultSet rs = pst.executeQuery();
				    tableMovies.setModel(DbUtils.resultSetToTableModel(rs));

				    
				    pst.close();
				    
				    }catch (Exception e1) {
				    e1.printStackTrace();
				    }
				
			}
		});
		btnSearch.setBounds(910, 72, 117, 29);
		panelMovies.add(btnSearch);
		
		
		
		
		
		
		
		
		JLabel lblNewLabel_5 = new JLabel("TITLE                 RELEASE DATE          MOVIE LENGTH  NAME PERSON  GENDER                ROLES/ CAST MOVIE    DATE OF BIRTH    MPAA RATING         GENRES");
		lblNewLabel_5.setBounds(24, 116, 1010, 16);
		panelMovies.add(lblNewLabel_5);
		
		JPanel panelShowings = new JPanel();
		tabbedPane.addTab("Showings", null, panelShowings, null);
		panelShowings.setLayout(null);
		
		JLabel lblDate = new JLabel("Date (DD-MM-YY)");
		lblDate.setBounds(44, 44, 134, 16);
		panelShowings.add(lblDate);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(190, 38, 134, 28);
		panelShowings.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JButton btnDateSearch = new JButton("Search");
		btnDateSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String sqlDate;

				try {
				    if(textFieldDate.getText() == null || textFieldDate.getText().equals("")){
				    	sqlDate = " /**/ " ;//"OR Writer LIKE NULL"
					}
					else {
						sqlDate = " AND SH.SHOW_DATE LIKE '%" + textFieldDate.getText().toUpperCase() +"%'"
							;
					}
				String query = "SELECT MS.TITLE, SH.START_TIME, MS.MOVIE_LENGTH, SH.TICKETS_SOLD"
				+ " FROM SHOWINGS SH, MOVIES MS"
				+ " WHERE MS.TITLE = SH.TITLE"
				+ sqlDate; 
				 
				    //System.out.println(query);
				    
				    //String query = "SELECT * FROM EMPLOYEEINFO WHERE '"+ selection +"' = ? ", where if use SELECT * FROM EMPLOYEEINFO WHERE 'Name' = ?
				        PreparedStatement pst = connection.prepareStatement(query);
				    //pst.setString(1, textFieldTitleSearch.getText() ); 
				    ResultSet rs = pst.executeQuery();
				    tableShowings.setModel(DbUtils.resultSetToTableModel(rs));

				    
				    pst.close();
				    
				    }catch (Exception e1) {
				    e1.printStackTrace();
				    }
			
			
			
			
			
			}
		});
		btnDateSearch.setBounds(488, 39, 117, 29);
		panelShowings.add(btnDateSearch);
		
		tableShowings = new JTable();
		tableShowings.setCellSelectionEnabled(true);
		tableShowings.setSurrendersFocusOnKeystroke(true);
		tableShowings.setFillsViewportHeight(true);
		tableShowings.setColumnSelectionAllowed(true);
		tableShowings.setBounds(44, 118, 970, 175);
		panelShowings.add(tableShowings);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(648, 40, 123, 27);
		panelShowings.add(comboBox);
		
		JLabel lblegdec = new JLabel("(Eg: 14-DEC-14)");
		lblegdec.setFont(new Font("Lucida Grande", Font.ITALIC, 11));
		lblegdec.setBounds(336, 44, 123, 16);
		panelShowings.add(lblegdec);
		
		JButton btnBuy = new JButton("BUY");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Num=Integer.parseInt(NumOfTickets.getText());
				try{
				String query = " UPDATE SHOWINGS "
						+ " SET TICKET_SOLD = TICKET_SOLD "
						+ Num;
				String query1=" SELECT * FROM TICKET_RECEIPT "
						;
				PreparedStatement pst = connection.prepareStatement(query);
				PreparedStatement pst1 = connection.prepareStatement(query1);

			    ResultSet rs = pst1.executeQuery();
			    receiptIfoTable.setModel(DbUtils.resultSetToTableModel(rs));
			    pst.close();}
				catch (Exception e2) {
					e2.printStackTrace();
				}
				}
		});
		btnBuy.setBounds(44, 319, 117, 29);
		panelShowings.add(btnBuy);
		
		table = new JTable();
		table.setBounds(44, 378, 1, 1);
		panelShowings.add(table);
		
		receiptIfoTable = new JTable();
		receiptIfoTable.setBounds(57, 378, 472, 96);
		panelShowings.add(receiptIfoTable);
		
		NumOfTickets = new JTextField();
		NumOfTickets.setBounds(190, 318, 39, 28);
		panelShowings.add(NumOfTickets);
		NumOfTickets.setColumns(10);
		
		JLabel lblTickets = new JLabel("TICKETS");
		lblTickets.setBounds(263, 324, 61, 16);
		panelShowings.add(lblTickets);
		
		//refreshTable();
		//refreshTable1();
		loadList();
	}
}

