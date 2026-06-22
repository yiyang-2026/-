package servlet;

import java.io.IOException; 
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Product;
import bean.Trade;
import dao.ProductDAO;
import bean.User;
import dao.UserDAO;
import util.SendMail;

//購入サーブレット
@WebServlet("/buyInformation")

public class BuyInformationServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";
		String path = "";

		try {

			//エンコード
			request.setCharacterEncoding("UTF-8");

			//セッションからuserを取得する
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			if (user == null) {
				error = "セッション切れの為、購入出来ません。";
				cmd = "logout";
				return;
			}

			//buyInformation.jspからsellidを取得する
			String sellid = request.getParameter("sellid");
			ProductDAO productdao = new ProductDAO();
			Product product = productdao.selectBySellid(sellid);

			//取得したBookをListに追加して「book_list」としてリクエストスコープに格納する
			request.setAttribute("product", product);


			//商品購入したらメールで送信する
			//text:本文　name：ユーザー名　mail：メールアドレス total:合計金額
			String purchaser = "";
			String productname = "";
			String mail = "";
			int salesprice = 0;

			purchaser = user.getNickname();
			productname = product.getProductname();
			mail = user.getMail();
			salesprice = product.getSalesprice();

			SendMail sendMail = new SendMail();
			sendMail.sendMail(mail, purchaser, productname, salesprice);

		} catch (

		IllegalStateException e) {
			error = "DB接続エラーのため、購入処理を行えません。";
			cmd = "menu";

		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。";
		} finally {

			//エラーの場合
			if (!error.equals("")) {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				path = "/view/error.jsp";

			} else {

				//buyInformation.jspにフォワード
				path = "/view/buyInformation.jsp";

			}
			request.getRequestDispatcher(path).forward(request, response);
		}

	}

}
