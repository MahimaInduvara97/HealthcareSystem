package model;
import java.sql.*;

public class User {
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
	public String insertUser(String name, String age, String gender, String phone, String email, String pwd)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for inserting."; }
	// create a prepared statement
	String query = " insert into users(`userID`,`userName`,`userAge`,`userGender`,`userPhone`,`userEmail`,`password`)"
	+ " values (?, ?, ?, ?, ?, ?, ?)";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, age);
			preparedStmt.setString(4, gender);
			preparedStmt.setString(5, phone);
			preparedStmt.setString(6, email);
			preparedStmt.setString(7, pwd);
	
	// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
	}
	catch (Exception e)
		{
			output = "Error while inserting the user.";
			System.err.println(e.getMessage());
		}
	return output;
	}
	public String readUser()
		{
	String output = "";
	try
			{
	Connection con = connect();
	if (con == null)
	{
		return "Error while connecting to the database for reading."; }
	// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>User Name</th><th>Age</th><th>Gender</th><th>Phone Number</th><th>Email</th><th>Password</th><th>Update</th><th>Remove</th></tr>";
				String query = "select * from users";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
	// iterate through the rows in the result set
				while (rs.next())
	{
					String UserID = Integer.toString(rs.getInt("userID"));
					String name = rs.getString("userName");
					String age = rs.getString("userAge");
					String gender = rs.getString("userGender");
					String phone = rs.getString("userPhone");
					String email = rs.getString("userEmail");
					String pwd = rs.getString("password");
	// Add into the html table
					output += "<tr><td>" + name + "</td>";
					output += "<td>" + age + "</td>";
					output += "<td>" + gender + "</td>";
					output += "<td>" + phone + "</td>";
					output += "<td>" + email + "</td>";
					output += "<td>" + pwd + "</td>";
	// buttons
					output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
									+ "<td><form method=\"post\" action=\"items.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
											+ "<input name=\"itemID\" type=\"hidden\" value=\"" + UserID
											+ "\">" + "</form></td></tr>";
	}
				con.close();
	// Complete the html table
	output += "</table>";
			}
		catch (Exception e)
				{
				output = "Error while reading the user.";
				System.err.println(e.getMessage());
				}
	return output;
	}
	
			public String updateUser(String UID, String Uname, String Uage, String Ugender, String Uphone, String Uemail, String pwd)
				{
					String output = "";
					try
						{
						Connection con = connect();
						if (con == null)
						{
							return "Error while connecting to the database for updating."; }
	// create a prepared statement
	String query = "UPDATE users SET userName=?,userAge=?,userGender=?,userPhone=?,userEmail=?,password=?WHERE userID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
		preparedStmt.setString(1, Uname);
		preparedStmt.setString(2, Uage);
		preparedStmt.setString(3, Ugender);
		preparedStmt.setString(4, Uphone);
		preparedStmt.setString(5, Uemail);
		preparedStmt.setString(6, pwd);
		preparedStmt.setInt(7, Integer.parseInt(UID));
	// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
						}
	catch (Exception e)
					{
			output = "Error while updating the user.";
			System.err.println(e.getMessage());
					}
					return output;
				}
	public String deleteUser(String UID)
			{
			String output = "";
	try
				{
			Connection con = connect();
	if (con == null)
		{
		return "Error while connecting to the database for deleting."; }
	// create a prepared statement
			String query = "delete from users where userID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
			preparedStmt.setInt(1, Integer.parseInt(UID));
	// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
				}
	catch (Exception e)
		{
		output = "Error while deleting the user.";
		System.err.println(e.getMessage());
		}
		return output;
			}

}

