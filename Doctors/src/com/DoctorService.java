package com;

import model.Doctor;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 

@Path("/DoctorsT") 
public class DoctorService {
	
	Doctor doc1 = new Doctor();
	//view
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readDoctorList()
	 {
	 return doc1.readDoctorList();
	 }
	//insert
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertDoctor(@FormParam("dname") String dname,
		 @FormParam("dphone") String dphone,
		 @FormParam("demail") String demail,
		 @FormParam("dspecial") String dspecial,
		 @FormParam("dhospital") String dhospital,
		 @FormParam("ddate") String ddate,
		 @FormParam("dstatus") String dstatus)
		{
		 String output = doc1.insertDoctor(dname,dphone,demail,dspecial,dhospital,ddate,dstatus);
		return output;
		}

}
