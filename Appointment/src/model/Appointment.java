package model;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

public class Appointment 
          {
	       private Connection connect()
             {
              Connection con = null;
		      try 
              {
                 Class.forName("com.mysql.cj.jdbc.Driver");
                 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=" +TimeZone.getDefault().getID(),"root","");
               }
               catch(Exception e)
               {
                   e.printStackTrace();
               }
                 return con;
              }
	
	
public String readAppointments()
{
   String output = "";
   try
   {
   Connection con = connect();
   if (con == null)
   
  {return "Error while connecting to the database for reading."; }
          
// Prepare the html table to be displayed

   output = "<table border=\"1\"><tr><th>Patient Regno</th><th>Appointment Date</th><th>Appointment Time</th><th>Checkup Need</th><th>Doctor Name</th><th>Update</th><th>Remove</th></tr>"; 
   String query = "select * from appointments";
   Statement stmt = con.createStatement();
   ResultSet rs = stmt.executeQuery(query);
                                         
// iterate through the rows in the result set
    
   while (rs.next())
    {
     String appoID = Integer.toString(rs.getInt("appoID"));
     String patientRegNo = rs.getString("patientRegNo");
     java.util.Date appoDate = rs.getDate("appoDate");
     java.util.Date appoTime = rs.getTime("appoTime");
     String checkupNeed = rs.getString("checkupNeed");
     String docName = rs.getString("docName");
                                          
// Add into the html table
    output += "<tr><td>" + patientRegNo + "</td>";
    output += "<td>" + appoDate + "</td>";
    output += "<td>" + appoTime + "</td>";
    output += "<td>" + checkupNeed + "</td>";
    output += "<td>" + docName + "</td>";
                                          
// buttons
   output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"appointment.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"+ "<input name=\"appoID\" type=\"hidden\" value=\"" + appoID+ "\">" + "</form></td></tr>";
             
      }
           
    con.close(); 
           
//Complete the html table
  
 output += "</table>";
     }
  catch (Exception e)
     {
      output = "Error while reading the items.";
      System.err.println(e.getMessage());
     }
    return output;
     } 
                                                        
                                                                          
public String insertAppointment(String patientRegNo,String appoDate, String appoTime,String checkupNeed, String docName) 
{
  String output = "";
  try 
  {

Connection con = connect();
if(con == null)
	{return "Error while connecting to the databse for inserting.";}

//creating a prepared statement
String query =  " insert into appointments (`appoID`,`patientRegNo`,`appoDate`,`appoTime`,`checkupNeed`,`docName`)"
		 + " values (?, ?, ?, ?, ?,?)"; 
 
SimpleDateFormat test1 = new SimpleDateFormat("yyyy-MM-dd");
java.util.Date jdate = test1.parse(appoDate);

SimpleDateFormat time1 = new SimpleDateFormat("HH:mm:ss");
java.util.Date jtime = time1.parse(appoTime);


 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, patientRegNo);
 preparedStmt.setObject(3, jdate.toInstant().atZone(ZoneId.of( "Asia/Colombo" )).toLocalDate());
 preparedStmt.setObject(4, jtime.toInstant().atZone(ZoneId.of( "Asia/Colombo" )).toLocalTime());
 preparedStmt.setString(5, checkupNeed);
 preparedStmt.setString(6, docName); 
// execute the statement
 preparedStmt.execute();
 con.close(); 
 output = "Inserted successfully";
            }
       catch (Exception e)
           {
    output = "Error while inserting the item.";
       System.err.println(e.getMessage());
            }
              return output;
  }
          




public String updateAppointment(String appoID,String patientRegNo,String appoDate, String appoTime,String checkupNeed, String docName)
{
  String output = "";
  try
  {
	   
   Connection con = connect();
   if (con == null)
{return "Error while connecting to the database for updating."; }

 // create a prepared statement
 String query = "UPDATE appointments SET patientRegNo=?,appoDate=?,appoTime=?,checkupNeed=?,docName=?"
 		+ "WHERE appoID=?";
     
 SimpleDateFormat test1 = new SimpleDateFormat("yyyy-MM-dd");
 java.util.Date jdate = test1.parse(appoDate);
 	
 SimpleDateFormat time1 = new SimpleDateFormat("HH:mm:ss");
 java.util.Date jtime = time1.parse(appoTime);

  PreparedStatement preparedStmt = con.prepareStatement(query);
      
 // binding values
  preparedStmt.setString(1, patientRegNo);
  preparedStmt.setObject(2, jdate.toInstant().atZone(ZoneId.of( "Asia/Colombo" )).toLocalDate());
  preparedStmt.setObject(3, jtime.toInstant().atZone(ZoneId.of( "Asia/Colombo" )).toLocalTime());
  preparedStmt.setString(4, checkupNeed);
  preparedStmt.setString(5, docName);
  preparedStmt.setInt(6, Integer.parseInt(appoID)); 
 
  //execute the statement
  preparedStmt.execute();
   con.close();
    output = "Updated successfully";
       }
       catch (Exception e)
       {
        output = "Error while updating the appointments details.";
        System.err.println(e.getMessage());
        }
      return output;
   }														

public String deleteAppointment(String appoID)
{
  String output = "";
  try
  {
   Connection con = connect();
  if (con == null)
  {return "Error while connecting to the database for deleting."; }
  
// create a prepared statement
 String query = "delete from appointments where appoID=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 
// binding values
 preparedStmt.setInt(1, Integer.parseInt(appoID));

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





