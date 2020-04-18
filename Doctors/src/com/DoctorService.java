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
	

}
