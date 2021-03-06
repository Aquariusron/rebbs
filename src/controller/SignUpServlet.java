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

import beans.Branch;
import beans.Position;
import beans.User;
import service.BranchService;
import service.PositionService;
import service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
		//サーブレットクラスを継承
		private static final long serialVersionUID = 1L;
		//

		/**
		 *
		 * doGetメソッドでリクエストされた値を格納するrequest,
		 * レスポンスの際に返却するための値responseを宣言
		 * doGetはIOExceptionを起こす可能性がある
		 */
		@Override
		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws IOException,ServletException {


			List<Branch> branches = new BranchService().getBranches();
			request.setAttribute("branches", branches);

			List<Position> positions = new PositionService().getPositions();
			request.setAttribute("positions", positions);

			request.getRequestDispatcher("/signup.jsp").forward(request, response);
		}//top.jspへrequestされた内容、responseする内容の処理を移す

		@Override
		protected void doPost(HttpServletRequest request,
				HttpServletResponse response) throws IOException, ServletException {
			List messages = new ArrayList<String>();
			//このmessagesにはエラー文を入れる？

			HttpSession session = request.getSession();

			//ユーザーがリクエスト(入力)した名前の値などを保持する。
			User user = new User();//ユーザークラスのインスタンスを宣言
			user.setName(request.getParameter("name"));
			user.setLoginId(request.getParameter("loginId"));
			user.setPassword(request.getParameter("password"));


			user.setBranchId(Integer.parseInt(request.getParameter("branchId")));
			user.setPostId(Integer.parseInt(request.getParameter("positionId")));


			//セッションの開始
			if(isValid(request, messages) == true){
				//自作メソッド。
				//入力の内容が正しければ以下をユーザークラスに保持する？
				new UserService().register(user);

				response.sendRedirect("./");
				//./で書かれたパスのurlをレスポンスする？
			} else {
				session.setAttribute("errorMessages", messages);
				//もし値が確かでなければエラーメッセージをセッションに設定する
				List<Branch> branches = new BranchService().getBranches();
				request.setAttribute("branches", branches);

				List<Position> positions = new PositionService().getPositions();
				request.setAttribute("positions", positions);

				request.setAttribute("editUser", user);
				//追記：バリデーションして、値がDBと一致せずともフォームに保持できるようにしたい。
				//getParameter内の値が使えるように、else内にUserクラスに取ってきた値をセットするプログラムを書く。
				//今やりたいのはjspに表示させるようにどんな記述をするか。
				request.getRequestDispatcher("/signup.jsp").forward(request, response);
				//response.sendRedirect("signup");
				//signupページに遷移するようレスポンスする
			}
		}

		private boolean isValid(HttpServletRequest request,
				List<String> messages) {
			//3つの条件分岐を内包したisValidメソッド
			String loginId = request.getParameter("loginId");
			//リクエストパラメータの取得
			//accountにユーザーが最初に入力した値を送り込む
			String password = request.getParameter("password");
			//passwordにユーザーが最初に入力した値を送り込む

			String passwordConfirm = request.getParameter("password_confirm");

			User user = new User();
			String rsLoginId = new UserService().setLoginId(loginId).toString();

			System.out.println(rsLoginId);
			//すでに使われているIDを重複しないようにする
			if(StringUtils.isEmpty(loginId) == true) {
				messages.add("ログインIDを入力してください");
				//入力フォームに何も打ち込まれていなかったら
			}
			//入力されたユーザーIdが存在しているかを条件式にする
			if(rsLoginId != null) {
				messages.add("このログインIDは使用済みです");
				//すでに使われているIDを重複しないようにする
			}
			if(6 > loginId.length() || loginId.length() < 20){
				messages.add("6文字以上20文字以内で入力してください：ログインID");
			}
			if(StringUtils.isEmpty(password) == true) {
				messages.add("パスワードを入力してください");
			}
			if(!password.equals(passwordConfirm)) {
				messages.add("パスワードを確認してください");
			}
			if(6 > password.length() || password.length() > 255) {
				messages.add("6文字以上255文字以内で入力してください：パスワード");
			}
			if(6 > loginId.length() || loginId.length() > 20) {
				messages.add("6文字以上20文字以内で入力してください：ログインID");
			}
			if(messages.size() == 0){
			//入力抜けがなければ処理を抜ける
				return true;
			} else {
				return false;
			//入力抜けがあったらfalseを返す
			}
		}
	}

