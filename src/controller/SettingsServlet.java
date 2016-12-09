package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import beans.Branch;
import beans.Position;
import beans.User;
import exception.NoRowsUpdatedRuntimeException;
import service.BranchService;
import service.PositionService;
import service.UserService;

@WebServlet(urlPatterns = { "/settings" })
@MultipartConfig(maxFileSize = 100000)
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		List<Branch> branches = new BranchService().getBranches();
		session.setAttribute("branches", branches);

		List<Position> positions = new PositionService().getPositions();
		session.setAttribute("positions", positions);


		int hoge = Integer.parseInt(request.getParameter("id"));
		User editUser  = new UserService().getUser(hoge);
		session.setAttribute("editUser", editUser);
		request.getRequestDispatcher("settings.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();

		User editUser = getEditUser(request);
		session.setAttribute("editUser", editUser);


		if (isValid(request, messages) == true) {

			try {
				new UserService().update(editUser);
			} catch (NoRowsUpdatedRuntimeException e) {
				session.removeAttribute("editUser");
				messages.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
				session.setAttribute("errorMessages", messages);
//				response.sendRedirect("settings.jsp");

			}

//			session.setAttribute("loginUser", editUser);
			session.removeAttribute("editUser");

			response.sendRedirect("users");

		} else {

			session.setAttribute("errorMessages", messages);
//			session.setAttribute("loginUser", editUser);
			response.sendRedirect("settings.jsp");
//			request.getRequestDispatcher("settings.jsp").forward(request, response);
		}
	}

	private User getEditUser(HttpServletRequest request)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		User editUser = (User) session.getAttribute("editUser");

		String password = request.getParameter("password");

		editUser.setName(request.getParameter("name"));
		editUser.setLoginId(request.getParameter("account"));
		editUser.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		editUser.setPostId(Integer.parseInt(request.getParameter("positionId")));

		if(!(StringUtils.isEmpty(password) == true)){
			editUser.setPassword(request.getParameter("password"));
		}

		return editUser;
	}


	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("password_confirm");

		if (StringUtils.isEmpty(account) == true) {
			messages.add("ログインIDを入力してください");
		}
//		if (StringUtils.isEmpty(password) == true) {
//			messages.add("パスワードを入力してください");
//		}
//		if (StringUtils.isEmpty(password) == true) {
//			editUser.setPassword(editUser.getPassword());
//		} else {
//			editUser.setPassword(request.getParameter("password"));
//		}
		if (!(password.equals(passwordConfirm))) {
			messages.add("パスワードを確認してください");
		}
		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
