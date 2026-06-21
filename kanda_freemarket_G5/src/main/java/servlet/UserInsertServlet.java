package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDAO;

@WebServlet("/userInsert")
public class UserInsertServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";

		//検索した書籍情報を格納するArrayList
		User user = new User();

		//DAOオブジェクトの宣言
		UserDAO userDao = new UserDAO();

		//パスの宣言
		String errorPath = null;
		
		String cmd = "";

		try {

			//入力情報を受け取るためのエンコード
			request.setCharacterEncoding("UTF-8");

			//入力情報を取得します
			String loginid = request.getParameter("loginid");
			String password = request.getParameter("password");
			String lastname = request.getParameter("lastname");
			String lastnamekana = request.getParameter("lastnamekana");
			String firstname = request.getParameter("firstname");
			String firstnamekana = request.getParameter("firstnamekana");
			String nickname = request.getParameter("nickname");
			String birthdate = request.getParameter("birthdate");
			String gender = request.getParameter("gender");
			String job = request.getParameter("job");
			String postalCode = request.getParameter("postalCode ");
			String prefecture = request.getParameter("prefecture");
			String munipulity = request.getParameter("munipulity");
			String housenumber = request.getParameter("housenumber");
			String buildnumber = request.getParameter("buildnumber");
			String phonenumber = request.getParameter("phonenumber");
			String mail = request.getParameter("mail");
			

			//格納
			user.setLoginid(loginid);
			user.setPassword(password);
			user.setLastname(lastname);
			user.setLastnameKana(lastnamekana);
			user.setFirstname(firstname);
			user.setFirstnameKana(firstnamekana);
			user.setNickname(nickname);
			user.setBirthdate(birthdate);
			user.setGender(gender);
			user.setJob(job);
			user.setPostalCode(postalCode);
			user.setPrefecture(prefecture);
			user.setMunipulity(munipulity);
			user.setHousenumber(housenumber);
			user.setBuildnumber(buildnumber);
			user.setPhonenumber(phonenumber);
			user.setMail(mail);

			//格納された書籍データをデータベースに登録
			userDao.insert(user);

			//検索結果を持ってlist.jspにフォワード
			request.setAttribute("userDao",userDao);

			errorPath = "/login";

		} catch (IllegalStateException e) {
			//DB接続ができているかチェック
			error = "DB接続エラーの為、書籍登録処理は行えませんでした。";
			cmd = "logout";
			errorPath = "/view/error.jsp";
		} catch (NumberFormatException e) {
			//数値が入力されているかチェック
			error = "価格の値が不正の為、書籍登録処理は行えませんでした。";
			cmd = "list";
			errorPath = "/view/error.jsp";
		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。";
		} finally {
			request.setAttribute("cmd", cmd);
			request.setAttribute("error", error);
			request.getRequestDispatcher(errorPath).forward(request, response);
		}
	}
}