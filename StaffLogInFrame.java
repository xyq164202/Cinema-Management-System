package com.db;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class StaffLogInFrame extends JFrame implements ActionListener {
	private String user;
	private JLabel lbAccount = new JLabel("Input your account:");
	private JTextField tfAccount = new JTextField(10);
	private JLabel lbPassword = new JLabel("Input your password:");
	private JPasswordField pfPassword = new JPasswordField(10);
	private JButton btLogin = new JButton("Login");
	private JButton btReturn = new JButton("Return");
	
	public StaffLogInFrame()
	{
		super("STAFF LOG IN");
		this.user = user;
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(lbAccount);
		this.add(tfAccount);
		this.add(lbPassword);
		this.add(pfPassword);
		this.add(btLogin);
		this.add(btReturn);
		this.setSize(300, 150);
		GUIUtil.toCenter(this);
		this.setResizable(false);
		this.setVisible(true);
		btLogin.addActionListener(this);
		btReturn.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btLogin)
		{
			String passwordTemp = new String(pfPassword.getPassword());
			if(new StaffConfirm(tfAccount.getText(), passwordTemp).userAuthentication())
			{
				this.dispose();
				new StaffActionFrame().setVisible(true);			}
			else
			{
				JOptionPane.showMessageDialog(this, "Incorrect password! Please try again.");
			}
			
		}else
		{
			this.dispose();
			new UserChooseFrame();
		}
	}

}

class StaffConfirm
{
	private String user,
				   password;
	
	public StaffConfirm(String user, String password)
	{
		this.user =user;
		this.password =password;
	}
	
	public boolean userAuthentication()
	{
		String passwordSQL="";
		try
		{
			Connection conn = DBConnection.GetConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT PASSWORDS "
					                         + "FROM EMPLOYEES "
					                         + "WHERE ID_EMPLOYEE=" + user);//要修改！
			while (rset.next())
				passwordSQL = rset.getString(1);
		}catch (SQLException e)
		{
			e.printStackTrace();
		}catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			return  password.equals(passwordSQL);
		}
	}
	
}
