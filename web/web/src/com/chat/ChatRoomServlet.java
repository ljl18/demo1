package com.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.User;
import com.web.loginSer;

public class ChatRoomServlet extends HttpServlet {

	public ChatRoomServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
//		String username = (String) session.getAttribute("UserName");// 将获取的对象强制类型转换为字符串
//		loginSer.context = getServletContext();
//		loginSer.context.getAttribute("username");
		User user = new User();
		try {
			session.setAttribute("UserName",user.returnName(request,response));
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		out.println("<html><body>");
		out.println("<lable>当前用户：" + loginSer.context.getAttribute("username") + "</lable><br>");
		out.println("<form action=\"ChatRoomServlet\" method=\"post\">");// 先提交到本类的dopost()，再提交到Messagebox的processRequest()中
		out.println("<div><textarea name=\"userMessages\" cols=\"50\" style=\"height:80%;\"></textarea></div>");
		out.println("<div><input type=\"submit\" id=\"submit\" value=\"发送\"/>");
		out.println("<input type=\"reset\" id=\"reset\" value=\"清空输入\"/></div>");
		out.println("</form>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		ServletContext application = this.getServletContext();// 获取上下文对象

		String username = (String)loginSer.context.getAttribute("username");// 将获取的对象强制类型转换为字符串

		PrintWriter out = response.getWriter();

		String mywords = request.getParameter("userMessages");// 获取输入的内容给mywords

		application.log(mywords);// 在控制台中将提交的内容显示出来
		if (mywords == null) {
			mywords = "系统提示：可以聊天";
			application.setAttribute("words", mywords + "\n");
		} else if (mywords != null) {
			// int len_mywords = mywords.length();
			// application.log("字节长度："+len_mywords);
			mywords = username + ":" + mywords;// 用户名：输入信息
			application.log(mywords);// 在控制台信息中查看内容
			Object obj = application.getAttribute("words");// 将words原有内容保存至obj
			if (obj == null) {
				application.setAttribute("words", mywords + "\n");// 上下文对象中没有String对象words就添加words
			} else {
				application.setAttribute("words", obj.toString() + mywords + "\n");// words不仅具有新来的信息，还保存前面的信息，
			}
		}

		out.println("<html><body>");
		User user = new User();
		try {
			session.setAttribute("UserName",user.returnName(request,response));
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		out.println("<lable>当前用户：" + loginSer.context.getAttribute("username") + "</lable><br>");
		out.println("<form action=\"ChatRoomServlet\" method=\"post\">");// 先提交到本类的dopost()，再提交到Messagebox的processRequest()中
		out.println("<div><textarea name=\"userMessages\" cols=\"50\" style=\"height:80%;\"></textarea></div>");
		out.println("<div><input type=\"submit\" id=\"submit\" value=\"发送\"/>");
		out.println("<input type=\"reset\" id=\"reset\" value=\"清空输入\"/></div>");
		out.println("</form>");
		out.println("</body></html>");

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
