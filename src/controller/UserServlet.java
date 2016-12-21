package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Branch;
import beans.Position;
import beans.User;
import service.BranchService;
import service.PositionService;
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
		User user = (User)request.getSession().getAttribute("loginUser");

		request.setAttribute("users", users);

		HttpSession session = request.getSession();

		List<Branch> branches = new BranchService().getBranches();
		session.setAttribute("branches", branches);

		List<Position> positions = new PositionService().getPositions();
		session.setAttribute("positions", positions);

		request.getRequestDispatcher("/users.jsp").forward(request, response);
	}

}
