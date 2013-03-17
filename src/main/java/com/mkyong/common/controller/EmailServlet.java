package com.mkyong.common.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class EmailServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String email = request.getParameter("email");
	    String notify = request.getParameter("notifyflag");
	    User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    System.out.println("creating new notificationchanger");
	    NotificationChanger nc = new NotificationChanger(user.getUsername());
	    if (email != null) {
	    	nc.setEmail(email);
	    	System.out.println("email set to " + email);
	    } else {
	    	if (notify == null) {
	    		nc.removeNotification();
	    	} else {
	    		nc.setNotification();
	    	}
	    	System.out.println("notification set to " + notify);
	    }
	}

}
