package model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.ZoneId;

public class Doctor {
	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.cj.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/doctormng", "root", "Ashi@2020");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 } 
	public String insertDoctor(String name, String phone, String email, String speacialization, String hospital, String date, String status)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into doctors (`docID`,`dname`,`dphone`,`demail`,`dspecial`,`dhospital`,`ddate`,`dstatus`)"
	 + " values (?, ?, ?, ?, ?, ?, ?, ?)";
	 
	 SimpleDateFormat test1 = new SimpleDateFormat("yyyy-MM-dd");
	 java.util.Date jdate = test1.parse(date);
	 
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, name);
	 preparedStmt.setString(3, phone);
	 preparedStmt.setString(4, email);
	 preparedStmt.setString(5, speacialization);
	 preparedStmt.setString(6, hospital);
	 preparedStmt.setObject(7, jdate.toInstant().atZone(ZoneId.of("Africa/Tunis")).toLocalDate());
	 preparedStmt.setString(8, status);
	 
	 
	// execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	public String readDoctorList()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th>Doctor Name</th><th>Phone No</th><th>Email</th><th>Specialization</th><th>Hospital</th><th>Date</th><th>Status</th><th>Update</th><th>Remove</th></tr>";
	 String query = "select * from doctors";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String docID = Integer.toString(rs.getInt("docID"));
	 String dname = rs.getString("dname");
	 String dphone = rs.getString("dphone");
	 String demail = rs.getString("demail");
	 String dspecial = rs.getString("dspecial");
	 String dhospital = rs.getString("dhospital");
	 java.util.Date ddate = rs.getDate("ddate");
	 String dstatus = rs.getString("dstatus");
	 // Add into the html table
	 output += "<tr><td>" + dname + "</td>";
	 output += "<td>" + dphone + "</td>";
	 output += "<td>" + demail + "</td>";
	 output += "<td>" + dspecial + "</td>";
	 output += "<td>" + dhospital + "</td>";
	 output += "<td>" + ddate + "</td>";
	 output += "<td>" + dstatus + "</td>";
	 
	 // buttons
	 output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"doctors.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"+ "<input name=\"docID\" type=\"hidden\" value=\"" + docID+ "\">" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the details.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	public String updateDoctor(String ID, String name, String phone, String email, String speacialization, String hospital, String date, String status )
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE doctors SET dname=?,dphone=?,demail=?,dspecial=?,dhospital=?,ddate=?,dstatus=? WHERE docID=?";
	 
	 SimpleDateFormat test1 = new SimpleDateFormat("yyyy-MM-dd");
	 java.util.Date jdate = test1.parse(date);
	 
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, name);
	 preparedStmt.setString(2, phone);
	 preparedStmt.setString(3, email);
	 preparedStmt.setString(4, speacialization);
	 preparedStmt.setString(5, hospital);
	 preparedStmt.setObject(6, jdate.toInstant().atZone(ZoneId.of("Africa/Tunis")).toLocalDate());
	 preparedStmt.setString(7, status);
	 
	 preparedStmt.setInt(8, Integer.parseInt(ID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	public String deleteDoctor(String docID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from doctors where docID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(docID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
}
