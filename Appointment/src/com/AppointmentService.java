package com;

//import appointment 

import model.Appointment;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;

//For REST Service
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;
import com.mysql.cj.result.LocalDateTimeValueFactory;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
											
@Path("/Appointments")

public class AppointmentService
  {
Appointment appointmentObj = new Appointment();
 @GET
 @Path("/")
 @Produces(MediaType.TEXT_HTML)
public String readAppointments()
 {
 return appointmentObj.readAppointments(); 
 }
   
 
 @POST
 @Path("/")
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
 @Produces(MediaType.TEXT_PLAIN)
 public String insertAppointment(@FormParam("patientRegNo") String patientRegNo,
   @FormParam("appoDate") String appoDate,
   @FormParam("appoTime") String appoTime,
   @FormParam("checkupNeed") String checkupNeed,
   @FormParam("docName") String docName)
 {
  String output = appointmentObj.insertAppointment(patientRegNo, appoDate, appoTime,checkupNeed,docName);
  return output;
 }
 
 @PUT
 @Path("/")
 @Consumes(MediaType.APPLICATION_JSON)
 @Produces(MediaType.TEXT_PLAIN)
 public String updateAppointment(String appointmentData) throws ParseException
 {
 //Convert the input string to a JSON object
  JsonObject appointmentObject = new JsonParser().parse(appointmentData).getAsJsonObject();
 //Read the values from the JSON object
  String appoID = appointmentObject.get("appoID").getAsString();
  String patientRegNo = appointmentObject.get("patientRegNo").getAsString();
  String appoDate = appointmentObject.get("appoDate").getAsString();
  String appoTime = appointmentObject.get("appoTime").getAsString();
  String checkupNeed = appointmentObject.get("checkupNeed").getAsString();
  String docName = appointmentObject.get("docName").getAsString();
  String output = appointmentObj.updateAppointment(appoID, patientRegNo, appoDate, appoTime, checkupNeed,docName);
 return output;
 }
 
 
 @DELETE
 @Path("/")
 @Consumes(MediaType.APPLICATION_XML)
 @Produces(MediaType.TEXT_PLAIN)
 public String deleteAppointment(String appointmentData)
 {
 	
 //Convert the input string to an XML document
  Document doc = Jsoup.parse(appointmentData, "", Parser.xmlParser());

 //Read the value from the element <itemID>
  String appoID = doc.select("appoID").text();
  String output = appointmentObj.deleteAppointment(appoID);
  return output;
 }

  }