package com.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.*;

public class User {

	public User() {
		try {
			// 注册jdbc驱动
			Class.forName(onlinetest.JDBC_DRIVER);
			// 连接数据库
			onlinetest.conn = DriverManager.getConnection(onlinetest.DB_URL, onlinetest.USER, onlinetest.PASS);
			// 实例化Statement对象
			onlinetest.stmt = onlinetest.conn.createStatement();
//			ServletContext context ;
//			context = loginSer.context;
			
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ClassNotFoundException, SQLException, ServletException {
		response.setCharacterEncoding("gb2312");
		response.setContentType("text/html;charset=gb2312");
		String username = request.getParameter("username");
		username = new String(username.getBytes("iso8859-1"), "gb2312");
		String password = request.getParameter("password");
		password = new String(password.getBytes("iso8859-1"), "gb2312");
		
		// 判断用户是否存在。
		String sql;
		sql = "SELECT password, name,ID " + "FROM user " + "where  name = '" + username + "'";
		try {
			ResultSet rs = onlinetest.stmt.executeQuery(sql);

			RequestDispatcher view;

			while (rs.next()) {
				if (rs.getString("password").equals(password) && rs.getString("name").equals(username)) {
					request.setAttribute("num", rs.getString("ID"));
					view = request.getRequestDispatcher("static/main.jsp");
					view.forward(request, response);
					return;
				}
			}
			
			response.sendRedirect("static/login.html");
			return;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	public void register(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		response.setCharacterEncoding("gb2312");
		response.setContentType("text/html;charset=gb2312");
		String username = request.getParameter("username");
		username = new String(username.getBytes("iso8859-1"), "gb2312");
		String password = request.getParameter("password");
		password = new String(password.getBytes("iso8859-1"), "gb2312");
		// 在数据库插入用户
		String sql;
		// 返回用户人数
//		sql = "SELECT password, name " + "FROM user " + "where  name = '" + username + "'";
		sql = "SELECT COUNT(*) as num FROM user";
		ResultSet rs = onlinetest.stmt.executeQuery(sql);
		// 创造ID
		int num = 0;
		while (rs.next()) {
			num = rs.getInt("num") + 1;
		}
System.out.println(num);
		sql = "INSERT INTO user VALUES ('" + num + "','" + username + "','" + password + "')";
		onlinetest.stmt.executeUpdate(sql);
		request.setAttribute("num", num);
		RequestDispatcher view = request.getRequestDispatcher("static/main.jsp");
		view.forward(request, response);
//		response.sendRedirect("static/main.jsp");
	}

	
	
	
	
	
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		response.setCharacterEncoding("gb2312");
		response.setContentType("text/html;charset=gb2312");
		String ID = request.getParameter("ID");
		ID = new String(ID.getBytes("iso8859-1"), "gb2312");
		String MyID = request.getParameter("MyID");
//		String  MyID = Integer.parseInt(request.getParameter("MyID"));
		MyID = new String(MyID.getBytes("iso8859-1"), "gb2312");
		// 获取朋友信息
		System.out.println("朋友ID  " + ID);
		System.out.println("我ID   " + MyID);

		String sql= "select id,name from user  where id ='" + ID + "'";;
//		sql = "SELECT ID,name FROM user  where ID ='" + ID + "'";
		
		String name = null;
		String temp = null;
		ResultSet rs = onlinetest.stmt.executeQuery(sql);// rs.last();
		
		while (rs.next()) {
			temp=rs.getString("id");
			name =rs.getString("name");
			
		}
		System.out.println("pengyouID   " + temp);
		System.out.println("pengyouname   " + name);
		sql = "INSERT INTO users VALUES ('" + MyID + "','" + temp + "','" + name+ "')";
		onlinetest.stmt.executeUpdate(sql);
		// 获取自己信息

//		sql = "SELECT name,id FROM user  where ID ='" + MyID + "'";
//		ResultSet rs1 = onlinetest.stmt.executeQuery(sql);
////		rs.last();
//		
//		System.out.println("自己");
//		while (rs1.next()) {
//			temp= rs1.getString("id");
//			name =rs1.getString("name");
//			
//		}
//		sql = "inster into users values (" + ID + "," + temp + "," + name + ")";
//		onlinetest.stmt.executeUpdate(sql);
//		System.out.println("我ID   " + MyID);
//		System.out.println("pengyou ID   " + temp);
//
		request.setAttribute("num", MyID);
		// 刷新主界面
		System.out.println("刷新");
		RequestDispatcher view =request.getRequestDispatcher("static/main.jsp");
		
	      view.forward(request, response);
//		response.sendRedirect("static/main.jsp");
	}
	public String returnName(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String sql= "select name from user  where id ='" + request.getAttribute("num")+"'";
//		sql = "SELECT ID,name FROM user  where ID ='" + ID + "'";
		System.out.println(request.getAttribute("username"));
		String name = null;
		ResultSet rs = onlinetest.stmt.executeQuery(sql);// rs.last();
		
		while (rs.next()) {
			name =rs.getString("name");
		}
		System.out.println(name);
		return name;
	}
	

}