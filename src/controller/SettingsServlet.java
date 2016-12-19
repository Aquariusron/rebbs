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

		//ifで数値かを判定
		int hoge = Integer.parseInt(request.getParameter("id"));
		User editUser  = new UserService().getUser(hoge);
		session.setAttribute("editUser", editUser);
		request.getRequestDispatcher("settings.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<String> messages = new ArrayList<>();

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
			}
			session.removeAttribute("editUser");
			response.sendRedirect("users");
		} else {
			session.setAttribute("errorMessages", messages);
//			response.sendRedirect("settings");
			request.getRequestDispatcher("/settings.jsp").forward(request , response);
			//値をsettingsに投げる
		}
	}

	private User getEditUser(HttpServletRequest request)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		User editUser = (User) session.getAttribute("editUser");

		String password = request.getParameter("password");

		editUser.setName(request.getParameter("name"));
		editUser.setLoginId(request.getParameter("account"));
		try {
			editUser.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		} catch(NumberFormatException e) {
			editUser.setBranchId(0);
		}
		try {
			editUser.setPassword(request.getParameter("password"));
		} catch(NumberFormatException e) {
			editUser.setPostId(0);
		}

		return editUser;
	}


	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String id = request.getParameter("id");
		int userId = Integer.parseInt(id);
		String name = request.getParameter("name");
		String loginId = request.getParameter("account");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("password_confirm");

		//userIdを比較する
		User loginUser = new UserService().setLoginId(loginId);
		System.out.println(loginUser);

		if(StringUtils.isBlank(name)){
			messages.add("名前を入力してください");
		}
		if (10 < name.length()) {
			messages.add("10文字以内で入力してください:名前");
		}
		if (StringUtils.isBlank(loginId) == true) {
			messages.add("ログインIDを入力してください");
		}
		if (6 > loginId.length() || loginId.length() > 20) {
			messages.add("6文字以上20文字以内で入力してください：ログインID");
		}
		if(loginUser != null && loginUser.getId() != userId){
			messages.add("このログインIDは使用済みです");
		}
		if(!StringUtils.isBlank(password) || !StringUtils.isBlank(passwordConfirm)){
			if (StringUtils.isBlank(password) == true) {
				messages.add("パスワードを入力してください");
			}
			if (!(password.equals(passwordConfirm))) {
				messages.add("パスワードを確認してください");
			}
			if (6 > password.length() || password.length() > 255) {
				messages.add("6文字以上255文字以下で入力してください：パスワード");
			}
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
