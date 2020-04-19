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
import model.login;

//get login details
@Path("/login")
public class LoginService {
	login loginObj = new login();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String logincheck(@FormParam("userEmail") String email,
			@FormParam("password") String password)
			 {
		
		String output = loginObj.logincheck(email, password);
		return output;
	}
}
