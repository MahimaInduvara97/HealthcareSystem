package model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.ZoneId;


public class Notification {
	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1", "root", "Mlu1996@");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 } 
	public String insertItem(String userType, String notificationType, String validDate, String time, String offerDesc)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 
	 // create a prepared statement
	 String query = " insert into notifications (`notID`,`nuserType`,`notifitype`,`nvaliddate`,`ntime`,`nofferdesc`)"
	 + " values (?, ?, ?, ?, ?, ?)";
	 
	 SimpleDateFormat test1 = new SimpleDateFormat("yyyy-MM-dd");
	 java.util.Date jdate = test1.parse(validDate);
	 
	 SimpleDateFormat time1 = new SimpleDateFormat("HH:mm:ss");
	 java.util.Date jtime = time1.parse(time);
	 
	 
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, userType);
	 preparedStmt.setString(3, notificationType);
	 preparedStmt.setObject(4, jdate.toInstant().atZone(ZoneId.of("Africa/Tunis")).toLocalDate());
	 preparedStmt.setObject(5, jtime.toInstant().atZone(ZoneId.of("Africa/Tunis")).toLocalTime());
	 preparedStmt.setString(6, offerDesc);
	 
	 
	 
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
	
	public String readItems()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 
	 output = "<table border=\"1\"><tr><th>User Type</th><th>Notification Type</th><th>Valid Date</th><th>Time</th><th>Notification Description</th></tr>";
	 String query = "select * from notifications";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 // iterate through the rows in the result set
	 
	 while (rs.next())
	 {
	 String notID = Integer.toString(rs.getInt("notID"));
	 String nuserType = rs.getString("nuserType");
	 String notifitype = rs.getString("notifitype");
	 java.util.Date nvaliddate = rs.getDate("nvaliddate");
	 java.util.Date ntime= rs.getTime("ntime");
	 String nofferdesc = rs.getString("nofferdesc");
	 
	 // Add into the html table
	 output += "<tr><td>" + nuserType + "</td>";
	 output += "<td>" + notifitype + "</td>";
	 output += "<td>" + nvaliddate + "</td>";
	 output += "<td>" + ntime + "</td>";
	 output += "<td>" + nofferdesc + "</td>";
	 
	 
	 // buttons
	 output += "<td><input userType=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"notifications.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"+ "<input name=\"notID\" type=\"hidden\" value=\"" + notID+ "\">" + "</form></td></tr>";
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
	public String updateItem(String ID, String userType, String notificationType, String validDate, String time, String offerDesc )
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE notifications SET nuserType=?,notifitype=?,nvaliddate=?,ntime=?,nofferdesc=? WHERE notID=?";
	 
	 SimpleDateFormat test1 = new SimpleDateFormat("yyyy-MM-dd");
	 java.util.Date jdate = test1.parse(validDate);
	 
	 SimpleDateFormat time1 = new SimpleDateFormat("HH:mm:ss");
	 java.util.Date jtime = time1.parse(time);
	 
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, userType);
	 preparedStmt.setString(2, notificationType);
	 preparedStmt.setObject(3, jdate.toInstant().atZone(ZoneId.of("Africa/Tunis")).toLocalDate());
	 preparedStmt.setObject(4, jtime.toInstant().atZone(ZoneId.of("Africa/Tunis")).toLocalTime());
	 preparedStmt.setString(5, offerDesc);
	 preparedStmt.setInt(6, Integer.parseInt(ID));
	 
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
	public String deleteItem(String notID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from notifications where notID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(notID));
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
