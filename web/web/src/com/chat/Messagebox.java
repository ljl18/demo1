package com.chat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/Messagebox")
public class Messagebox extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {

			String temp = (String) getServletConfig().getServletContext().getAttribute("online");
			if (temp == null)
				temp = "0";
			ServletContext application = this.getServletContext();// 获取上下文对象
			String words = (String) application.getAttribute("words");// 强制类型转换，将上下文对象的words存储的聊天记录取出

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet messagebox</title>");
			out.println("<meta http-equiv=\"refresh\" content =\"2;url=Messagebox\">");
			out.println("</head>");
			out.println("<body>");
//			out.println("<p>当前在线：");
//			out.println(temp);
//			out.println("人</p>");
			out.println("<textarea name=\"messagebox\" cols=\"100\" words=\"400\" style=\"height:60%;width=100%;\">");
			out.println(words);
			out.println("</textarea>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}