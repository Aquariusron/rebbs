package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import service.UserService;
@WebServlet(urlPatterns = { "/users" })
//indexから始まるパラメータを指定
public class UserServlet extends HttpServlet{
	//サーブレットクラスを継承
	private static final long serialVersionUID = 1L;
	//
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<User> users = new UserService().getUsers();

		request.setAttribute("users", users);
//		request.getParameter("edit", edit);
//		request.setAttribute("users", users);
		request.getRequestDispatcher("/users.jsp").forward(request, response);
	}

}
