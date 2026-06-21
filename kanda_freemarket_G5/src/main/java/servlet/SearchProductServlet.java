package servlet;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.User;

@WebServlet("/searchProduct") //()内にアノテーションを記載
public class SearchProductServlet extends HttpServlet { //Servletの前にサーブレット名を記載
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//エラーメッセージ用変数error、遷移先指定用変数path、遷移先でのリンク指定用変数cmdを用意

		String error = null;
		String path = null;
		String cmd = null;
		
		HttpSession session = request.getSession();

		try {
                      
			request.setCharacterEncoding("UTF-8"); //エンコード用
			
			String area = request.getParameter("area");
			String type = request.getParameter("type");
			
			User user = (User)session.getAttribute("user");
			
			
			
		} catch (IllegalStateException e) {

			error = "";//"DB接続エラーの為、○○は行えませんでした。";　のように内容を記載
			path = ""; //"/view/error.jsp";のように、DB接続エラーが出た時の遷移先パスを指定
			cmd = ""; //エラー画面から遷移する画面をcmdの値で分岐させる

		} finally {

			if(error != null){//errorがnullでない場合、error、cmdをリクエストスコープに保存し、遷移先で必要な情報を格納
			
					request.setAttribute("error", error);
					request.setAttribute("cmd", cmd);

			}
			
			request.getRequestDispatcher(path).forward(request, response);

		}
	}

}