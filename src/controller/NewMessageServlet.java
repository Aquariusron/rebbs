package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import beans.Message;
import beans.User;
import service.MessageService;

@WebServlet(urlPatterns = { "/newMessage" })
public class NewMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("newMessage.jsp").forward(request, response);

	}


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		List<String> messages = new ArrayList<String>();


		User user = (User) session.getAttribute("loginUser");

		Message message = new Message();
		message.setSubject(request.getParameter("subject"));
		message.setCategory(request.getParameter("category"));
		message.setText(request.getParameter("text"));
		message.setUserId(user.getId());

		if (isValid(request, messages) == true) {
			new MessageService().register(message);
			response.sendRedirect("./");

		} else {
			session.setAttribute("message", message);
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("newMessage");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String message = request.getParameter("text");
		String category = request.getParameter("category");
		String subject = request.getParameter("subject");
		if (StringUtils.isBlank(message) == true) {
			messages.add("本文を入力してください");
		}
		if (StringUtils.isBlank(category) == true) {
			messages.add("カテゴリーを入力してください");
		}
		if (StringUtils.isBlank(subject) == true) {
			messages.add("件名を入力してください");
		}
//		if (1000 < message.length()) {
//			messages.add("1000文字以下で入力してください");
//		}
		System.out.println(message);
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
