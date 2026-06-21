package servlet;

import bean.Product;
import bean.Trade;
import bean.User;
import dao.ProductDAO;
import dao.TradeDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.SendMail;

import java.io.IOException;
import java.util.ArrayList;

//商品発送時のメール送信

@WebServlet("/shippingmail")
public class ShippingMail extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";
		String path = "";

		try {

			//エンコード
			request.setCharacterEncoding("UTF-8");

			String sellid = request.getParameter("sellid");

			ProductDAO productDao = new ProductDAO();
			UserDAO userDao = new UserDAO();
			TradeDAO tradeDao = new TradeDAO();

			Trade trade = tradeDao.selectBySellid(sellid);
			Product product = productDao.selectBySellid(sellid);
			User user = userDao.selectByUserid(trade.getPurchaser());
						
			//sellhistory_listの出品商品が発送したらメールで送信する
			//text:本文　name：ユーザー名　mail：メールアドレス total:合計金額
			String purchaser = "";
			String productname = "";
			String mail = "";
			

			purchaser = trade.getPurchaser();
			productname=product.getProductname();
			mail = user.getMail();

			
			SendMail sendMail = new SendMail();
			sendMail.sendMail(mail, purchaser, productname);

			

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
