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

import beans.Comment;
import beans.User;
import service.CommentService;
@WebServlet(urlPatterns = { "/comment" })//←ここに何が入るかを考える
public class CommentServlet extends HttpServlet {
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
		List<String> comments = new ArrayList<String>();

		User user = (User) session.getAttribute("loginUser");

		Comment comment = new Comment();
		comment.setComment(request.getParameter("text"));
		comment.setMessageId(Integer.parseInt(request.getParameter("message_id")));
		comment.setUserId(user.getId());

		if (isValid(request, comments) == true) {
			new CommentService().register(comment);
		} else {
			session.setAttribute("errorMessages", comments);
		}
		response.sendRedirect("./");

	}

	private boolean isValid(HttpServletRequest request, List<String> comments) {

		String comment = request.getParameter("text");
		if (StringUtils.isBlank(comment) == true) {
			comments.add("コメントを入力してください");
		}

//		if (500 < message.size()) {
//			comments.add("500文字以下で入力してください");
//		}
		System.out.println(comment);
		if (comments.size() == 0) {
			return true;
		} else {
			return false;
		}
	}


}
