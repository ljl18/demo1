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
//		String username = (String) session.getAttribute("UserName");// ����ȡ�Ķ���ǿ������ת��Ϊ�ַ���
//		loginSer.context = getServletContext();
//		loginSer.context.getAttribute("username");
		User user = new User();
		try {
			session.setAttribute("UserName",user.returnName(request,response));
		} catch (SQLException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		out.println("<html><body>");
		out.println("<lable>��ǰ�û���" + loginSer.context.getAttribute("username") + "</lable><br>");
		out.println("<form action=\"ChatRoomServlet\" method=\"post\">");// ���ύ�������dopost()�����ύ��Messagebox��processRequest()��
		out.println("<div><textarea name=\"userMessages\" cols=\"50\" style=\"height:80%;\"></textarea></div>");
		out.println("<div><input type=\"submit\" id=\"submit\" value=\"����\"/>");
		out.println("<input type=\"reset\" id=\"reset\" value=\"�������\"/></div>");
		out.println("</form>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		ServletContext application = this.getServletContext();// ��ȡ�����Ķ���

		String username = (String)loginSer.context.getAttribute("username");// ����ȡ�Ķ���ǿ������ת��Ϊ�ַ���

		PrintWriter out = response.getWriter();

		String mywords = request.getParameter("userMessages");// ��ȡ��������ݸ�mywords

		application.log(mywords);// �ڿ���̨�н��ύ��������ʾ����
		if (mywords == null) {
			mywords = "ϵͳ��ʾ����������";
			application.setAttribute("words", mywords + "\n");
		} else if (mywords != null) {
			// int len_mywords = mywords.length();
			// application.log("�ֽڳ��ȣ�"+len_mywords);
			mywords = username + ":" + mywords;// �û�����������Ϣ
			application.log(mywords);// �ڿ���̨��Ϣ�в鿴����
			Object obj = application.getAttribute("words");// ��wordsԭ�����ݱ�����obj
			if (obj == null) {
				application.setAttribute("words", mywords + "\n");// �����Ķ�����û��String����words�����words
			} else {
				application.setAttribute("words", obj.toString() + mywords + "\n");// words����������������Ϣ��������ǰ�����Ϣ��
			}
		}

		out.println("<html><body>");
		User user = new User();
		try {
			session.setAttribute("UserName",user.returnName(request,response));
		} catch (SQLException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		out.println("<lable>��ǰ�û���" + loginSer.context.getAttribute("username") + "</lable><br>");
		out.println("<form action=\"ChatRoomServlet\" method=\"post\">");// ���ύ�������dopost()�����ύ��Messagebox��processRequest()��
		out.println("<div><textarea name=\"userMessages\" cols=\"50\" style=\"height:80%;\"></textarea></div>");
		out.println("<div><input type=\"submit\" id=\"submit\" value=\"����\"/>");
		out.println("<input type=\"reset\" id=\"reset\" value=\"�������\"/></div>");
		out.println("</form>");
		out.println("</body></html>");

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
