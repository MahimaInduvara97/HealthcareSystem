package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class login {
	
	private Connection connect()
	{
			Connection con = null;
	try
	{
			Class.forName("com.mysql.cj.jdbc.Driver");
				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test_user", "root", "root");
	}
	catch (Exception e)
	{e.printStackTrace();}
	return con;
	}
	public String logincheck(String email, String password)
	{
		String output = "";
		try
			{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for Login."; }
// create a prepared statement
			
String query = "select * from users WHERE userEmail=? and password=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding valuest
preparedStmt.setString(1,email);
preparedStmt.setString(2,password);
ResultSet rs=preparedStmt.executeQuery();  
while (rs.next())
{
		String emails = rs.getString("userEmail");
		String pswd = rs.getString("password");
		if(email.equals(emails) && password.equals(pswd)) {
			output = "User found!";
		}
		else {
			output = " Sorry Check your username and password again!";
			
		}
		
}

con.close();

		}
catch (Exception e)
		{
output = "Error while Checking User details on databse.";
System.err.println(e.getMessage());
		}
		return output;
	}
}
