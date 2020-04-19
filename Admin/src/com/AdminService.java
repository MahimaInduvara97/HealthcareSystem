package com;

import model.Admin;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/AdminS")
public class AdminService {

	
	Admin adminObj = new Admin();

	@GET  
	@Path("/") 
	@Produces(MediaType.TEXT_HTML)  
	public String readAdmin()  
	    { 
		return adminObj.readAdmin();  
		}
	
	//insert
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAdmin(@FormParam("doctorID") String doctorID,
	 @FormParam("doctorName") String doctorName,
	 @FormParam("userCount") String userCount,
	 @FormParam("roomNo") String roomNo,
	 @FormParam("hospital") String hospital,
     @FormParam("aDate") String aDate,
     @FormParam("description") String description)
	{
	 String output = adminObj.insertAdmin(doctorID, doctorName, userCount, roomNo, hospital, aDate, description);
	return output;
	}
	//update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAdmin(String adminData)
	{
	//Convert the input string to a JSON object
	 JsonObject adminObject = new JsonParser().parse(adminData).getAsJsonObject();
	//Read the values from the JSON object
	 String number = adminObject.get("number").getAsString();
	 String doctorID = adminObject.get("doctorID").getAsString();
	 String doctorName = adminObject.get("doctorName").getAsString();
	 String userCount = adminObject.get("userCount").getAsString();
	 String roomNo = adminObject.get("roomNo").getAsString();
	 String hospital = adminObject.get("hospital").getAsString();
	 String aDate = adminObject.get("aDate").getAsString();
	 String description = adminObject.get("description").getAsString();
	 String output = adminObj.updateAdmin(number, doctorID , doctorName, userCount, roomNo, hospital, aDate, description );
	return output;
	}
	//delete
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAdmin(String adminData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(adminData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String number = doc.select("number").text();
	 String output = adminObj.deleteadmin(number);
	return output;
	}
	
	
}
