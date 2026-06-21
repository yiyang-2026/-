package servlet;

import java.io.IOException;
import java.util.ArrayList;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



import bean.Product;
import bean.Trade;
import bean.User;
import dao.ProductDAO;
import dao.TradeDAO;
import util.SendMail;

//出品履歴一覧サーブレット

@WebServlet("/sellHistryList")
public class SellHistryListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";
		String path = "";

		try {

			//エンコード
			request.setCharacterEncoding("UTF-8");

			
			
			//tradeDAOのオブジェクトを生成して全部表示させる
			TradeDAO tradeDao = new TradeDAO();
			ArrayList<Trade> list = tradeDao.selectAll();

			//取得した出品履歴をListに追加して「sellhistory_list」としてリクエストスコープに格納する
			request.setAttribute("sellhistory_list", list);
			
	


			//sellHistryList.jspにフォワードする
			path = "/view/sellHistryList";



		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、出品履歴の確認は出来ません。";
			cmd = "userMypage";

		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。";
		} finally {

			//エラーの場合
			if (!error.equals("")) {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				path = "/view/error.jsp";

			} else {

				//sellHistryList.jspにフォワード
				path = "/view/sellHistryList.jsp";

			}
			request.getRequestDispatcher(path).forward(request, response);
		}

	}

}
