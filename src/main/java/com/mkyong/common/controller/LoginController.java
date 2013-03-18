package com.mkyong.common.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mkyong.customer.Customer;
import com.mkyong.customer.CustomerDAO;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
@Controller
public class LoginController {
	
	private String name;
	private String roles;
	private String xml = "";
 
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String home(ModelMap model) {
 
		return "login";
 
	}
	
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
 
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		name = user.getUsername();
		roles = user.getAuthorities().toString();
		System.out.println(roles);
		if (roles.contains("ROLE_CLERK")){
			return "register";
		}
		//=====================
		ApplicationContext context = 
	    		new ClassPathXmlApplicationContext("spring-module.xml");
		 CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
	     Customer customer = new Customer(4562107, "testing","aaaaaa", 1);
	     customerDAO.insert(customer);
	     System.out.println("inserted");
	     Customer customer1 = customerDAO.findByCustomerId(104);
	     System.out.println(customer1);
		
		
		//=====================
		
		
		model.addAttribute("username", name);
		model.addAttribute("roles",  roles);
		
		
		
		
		// use username to return an array list of schedules from the database.
		// this information, which is used to connect to the database, is duplicated in file spring-database.xml
		// I don't know how to transfer information between these two files
		String url =
				 "jdbc:mysql://pharmis.ca:3306/hacklocal";
		String username = "hacklocal";
		String password = "thinkglobal";
		
		Connection con = null;
        Statement st = null;
        ResultSet rs = null;


        try {
        	con = DriverManager.getConnection(url, username, password);
        	st = con.createStatement();

        	rs = st.executeQuery("select a.description, a.id, a.appointment_type, a.provider_name, a.building, a.room, a.start_date_time, a.end_date_time from appointments a, users u where u.username = '" + name + "' and u.user_id = a.user_id");

        	// get info on ResultSet
        	ResultSetMetaData rsmd = rs.getMetaData();

        	// get number of columns
        	int numCols = rsmd.getColumnCount();
        	
        	System.out.println(numCols);        	

        	List<Appointment> a_list = new ArrayList<Appointment>();        	
        	
        	while(rs.next())
        	{
        		Appointment app = new Appointment();
        		app.appointment_type = rs.getString("appointment_type");
        		app.provider_name = rs.getString("provider_name");
        		app.building = rs.getString("building");
        		app.room = rs.getInt("room");
        		app.start_date_time = rs.getDate("start_date_time");
        		app.end_date_time = rs.getDate("end_date_time");
        		app.appoint_id = rs.getInt("id");
        		app.description = rs.getString("description");
        		a_list.add(app);
        	}

        	System.out.println(a_list.size());

        	xml = xml_write(a_list);        	

        } catch (SQLException ex) {
        	System.out.println("Message: " + ex.getMessage());
        }
        
        model.addAttribute("xml_test",xml);
        
        return "hello";
 
	}
	private String xml_write(List<Appointment> alist){
		String output = "";
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement( "data" );

		Iterator<Appointment> it = alist.iterator();
		while(it.hasNext()){
			
			Appointment ap = it.next();
			
			Element event = root.addElement( "event" )
				.addAttribute( "id", Integer.toString(ap.appoint_id) )
				.addAttribute( "start_date", ap.start_date_time.toString() )
				.addAttribute( "end_date", ap.end_date_time.toString() )
				.addAttribute( "text", ap.provider_name+", "+ap.building+", "+ap.appointment_type )
				.addAttribute( "details", ap.description );					
		}

		try{
			StringWriter stw = new StringWriter();
			// lets write to a file
			XMLWriter writer = new XMLWriter(
					stw, OutputFormat.createPrettyPrint()
					);
			writer.write( document );
			writer.close();
			output = stw.toString();
			output = output.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		}
		catch(Exception io){
			io.printStackTrace();
		}
		return output;
	}
 
	@RequestMapping(value="/notifysettings", method = RequestMethod.GET)
	public String notification(ModelMap model) {
 
		if (name == null) {
			return "login";
		}
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		name = user.getUsername();
		
		model.addAttribute("username", name);
		
		return "notifysettings";
 
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
 
		return "login";
 
	}
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 
		model.addAttribute("error", "true");
		return "login";
 
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		
		name = null;
 
		return "login";
 
	}
	
}