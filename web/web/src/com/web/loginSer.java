package com.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.*;
public class loginSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static ServletContext context;
	public void init() {
		context = getServletContext();
//		String temp = (String) getServletConfig().getServletContext().getAttribute("online");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//实例化user
		User user = new User();
		try {
			context.setAttribute("username",request.getParameter("username"));
			user.login(request,response);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}

}
