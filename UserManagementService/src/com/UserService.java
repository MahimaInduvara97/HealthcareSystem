package com;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;

import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.User;

@Path("/User")
public class UserService {
	User userObj = new User();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUser()
	{
	return userObj.readUser();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("userName") String name,
			@FormParam("userAge") String age,
			@FormParam("userGender") String gender,
			@FormParam("userPhone") String phone,
			@FormParam("userEmail") String email)
	{
		
		String output = userObj.insertUser(name, age, gender, phone, email);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData)
	{
	//Convert the input string to a JSON object
	JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
	//Read the values from the JSON object
	String UID = userObject.get("userID").getAsString();
	String Uname = userObject.get("userName").getAsString();
	String Uage = userObject.get("userAge").getAsString();
	String Ugender = userObject.get("userGender").getAsString();
	String Uphone = userObject.get("userPhone").getAsString();
	String Uemail = userObject.get("userEmail").getAsString();
	String output = userObj.updateUser(UID, Uname, Uage, Ugender, Uphone, Uemail);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String userData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(userData, "", Parser.xmlParser());
	//Read the value from the element <UserID>
	String userID = doc.select("userID").text();
	String output = userObj.deleteUser(userID);
	return output;
	}
	
}
