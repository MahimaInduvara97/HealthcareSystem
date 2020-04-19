package model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.ZoneId;

public class Admin {

	//A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.cj.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/admindb", "root", "nasa@35351");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		
		 } 
		
		public String insertAdmin(String code, String dName, String count, String room, String hosp, String date, String desc )
		 {
		 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
			 // create a prepared statement
			 String query = " insert into admin (`number`,`doctorID`,`doctorName`,`userCount`,`roomNo`, `hospital`,`aDate`,`description`)"
			 + " values (?, ?, ?, ?, ?, ?, ?, ?)";
			 
			 SimpleDateFormat test1 = new SimpleDateFormat("yyyy-MM-dd");
			 java.util.Date jdate = test1.parse(date);
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, code);
			 preparedStmt.setString(3, dName);
			 preparedStmt.setDouble(4, Double.parseDouble(count));
			 preparedStmt.setString(5, room); 
			 preparedStmt.setString(6, hosp); 
			 preparedStmt.setObject(7, jdate.toInstant().atZone(ZoneId.of("Africa/Tunis")).toLocalDate());
			 preparedStmt.setString(8, desc); 
			
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the Admin.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
		public String readAdmin()
		 {
		 String output = "";
		 
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		 output = "<table border=\"1\"> " 
		 		+ "<tr><th>Doctor ID</th>"
		 		+ "<th>Doctor Name</th>"
		 		+ "<th>User Count</th>"
		 		+ "<th>room No</th>"
		 		+ "<th>Hospital</th>"
		 		+ "<th>Date</th>"
		 		+ "<th>Apoointment Description</th><th>Update</th><th>Remove</th></tr>";
		 String query = "select * from admin";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
			 String number = Integer.toString(rs.getInt("number"));
			 String doctorID = rs.getString("doctorID");
			 String doctorName = rs.getString("doctorName");
			 String userCount = Double.toString(rs.getDouble("userCount"));
			 String roomNo = rs.getString("roomNo");
			 String hospital = rs.getString("hospital");
			 java.util.Date aDate = rs.getDate("aDate");
			 String description = rs.getString("description");
			 
			 // Add into the html table
			 output += "<tr><td>" + doctorID + "</td>";
			 output += "<td>" + doctorName + "</td>";
			 output += "<td>" + userCount + "</td>";
			 output += "<td>" + roomNo + "</td>";
			 output += "<td>" + hospital + "</td>";
			 output += "<td>" + aDate + "</td>";
			 output += "<td>" + description + "</td>";
			 // buttons
			 output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
			 + "<td><form method=\"post\" action=\"admin.jsp\">" + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
			 + "<input name=\"number\" type=\"hidden\" value=\"" + number 
			 + "\">" + "</form></td></tr>";
			 }
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
			 }
			 catch (Exception e)
			 {
			 output = "Error while reading the admin.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 } 
			public String updateAdmin(String no, String code, String dName, String count, String room, String hosp, String date, String desc )
			 {
			 String output = "";
			 
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 // create a prepared statement
			 String query = "UPDATE admin SET doctorID=?,doctorName=?,userCount=?,roomNo=?,hospital=?,aDate=?,description=? WHERE number=?";
			 
			 SimpleDateFormat test1 = new SimpleDateFormat("yyyy-MM-dd");
			 java.util.Date jdate = test1.parse(date);
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, code);
			 preparedStmt.setString(2, dName);
			 preparedStmt.setDouble(3, Double.parseDouble(count));
			 preparedStmt.setString(4, room);
			 preparedStmt.setString(5, hosp);
			 preparedStmt.setObject(6, jdate.toInstant().atZone(ZoneId.of("Africa/Tunis")).toLocalDate());
			 preparedStmt.setString(7, desc);
			 
			 preparedStmt.setInt(8, Integer.parseInt(no));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Updated successfully";
			 
			 }
			 catch (Exception e)
			 {
			 output = "Error while updating the admin.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
			public String deleteadmin(String number)
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
			 // create a prepared statement
			 String query = "delete from admin where number=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(number));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Deleted successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while deleting the admin.";
			 System.err.println(e.getMessage());
			 }
			 
			 return output;
			 
			 }
			
}
