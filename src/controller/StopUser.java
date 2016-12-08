package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import service.UserService;

@WebServlet(urlPatterns = { "/stopuser" })
public class StopUser extends HttpServlet {
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


//		User user = (User) session.getAttribute("loginUser");

		User user = new User();
		user.setId(Integer.parseInt(request.getParameter("id")));
		user.setStop(Boolean.valueOf(request.getParameter("stop")));

		new UserService().activeUpdate(user);//registar

		session.setAttribute("users", user);
		response.sendRedirect("users");

	}

//	private boolean isValid(HttpServletRequest request, List<String> messages) {
//
//		String message = request.getParameter("text");
//		String category = request.getParameter("category");
//		String subject = request.getParameter("subject");
//		if (StringUtils.isBlank(message) == true) {
//			messages.add("本文を入力してください");
//		}
//		if (StringUtils.isBlank(category) == true) {
//			messages.add("カテゴリーを入力してください");
//		}
//		if (StringUtils.isBlank(subject) == true) {
//			messages.add("件名を入力してください");
//		}
////		if (1000 < message.length()) {
////			messages.add("1000文字以下で入力してください");
////		}
//		System.out.println(message);
//		if (messages.size() == 0) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
}
