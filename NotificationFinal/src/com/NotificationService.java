package com;

import model.Notification;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 

@Path("/NotificationT") 
public class NotificationService {
	
	Notification not1 = new Notification();
	//view
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
	 return not1.readItems();
	 }
	//insert
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("nuserType") String nuserType,
	 @FormParam("notifitype") String notifitype,
	 @FormParam("nvaliddate") String nvaliddate,
	 @FormParam("ntime") String ntime,
	 @FormParam("nofferdesc") String nofferdesc)
	{
	 String output = not1.insertItem(nuserType,notifitype,nvaliddate,ntime,nofferdesc);
	return output;
	}
	//update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String nData) 
	{
	//Convert the input string to a JSON object
	 JsonObject notification = new JsonParser().parse(nData).getAsJsonObject();
	//Read the values from the JSON object
	 String notID = notification.get("notID").getAsString();
	 String nuserType = notification.get("nuserType").getAsString();
	 String notifitype = notification.get("notifitype").getAsString();
	 String nvaliddate = notification.get("nvaliddate").getAsString();
	 String ntime = notification.get("ntime").getAsString();
	 String nofferdesc = notification.get("nofferdesc").getAsString();
	
	 
	 String output = not1.updateItem(notID, nuserType, notifitype, nvaliddate, ntime, nofferdesc);
	return output;
	}
	//delete
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String nData)
	{
	//Convert the input string to an XML document
	 Document not = Jsoup.parse(nData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String notID = not.select("notID").text();
	 String output = not1.deleteItem(notID);
	return output;
	}

}
