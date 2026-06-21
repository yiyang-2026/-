package servlet;

import java.io.IOException; 

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Product;
import dao.ProductDAO;
import bean.User;
import dao.UserDAO;

@WebServlet("/updateProduct")
public class UpdateProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";
		String path = "";

		try {
			// 入力データの文字コードの指定
			request.setCharacterEncoding("UTF-8");

			//パラメータを取得する
			
			
			//ユーザー管理ID
			String userid = request.getParameter("userid");	
			
			
			
			//商品名
			String productname = request.getParameter("productname");
			
			//カテゴリー
			String category = request.getParameter("category");
			
			//商品価格
			String salesprice = request.getParameter("salesprice");
			
			//写真
			String photo = request.getParameter("photo");
			
			//商品状態
			String condition = request.getParameter("condition");
			
			//配送方法
			String shippingmethod = request.getParameter("shippingmethod");
			
			
			//発送元の地域
			String region = request.getParameter("prefecture");
		
			//商品説明
			String description = request.getParameter("description");
			
			
			//配送料負担（出品側or購入側）
			String loadflag = request.getParameter("loadflag");
			
			
			//発送までの日数
			String shippingdays = request.getParameter("shippingdays");
			
			//商品のサイズ
			String size = request.getParameter("size");
			
			
			
			//出品登録日時String sellInsertdate = request.getParameter("sellInsertdate");
			//出品変更日時String sellUpdatedate = request.getParameter("sellUpdatedate");			
			//購入日時String purchasedate = request.getParameter("purchasedate");
			//発送日時String shippingdate = request.getParameter("shippingdate");			
			//出品状態フラグString sellstateflag = request.getParameter("sellstateflag");
			//入金フラグString paymentflag = request.getParameter("paymentflag");
			//発送フラグString shippingflag = request.getParameter("shippingflag");
			//配送料String shippingfee = request.getParameter("shippingfee");
			

			// エラーチェック
			if (title == null || title.equals("")) {
				error = "タイトルが未入力の為、書籍更新処理は行えませんでした。";
				cmd = "list";
				return;

			}
			if (strPrice == null || strPrice.equals("")) {
				error = "価格が未入力の為、書籍更新処理は行えませんでした。";
				cmd = "list";
				return;

			}
			//価格の文字列を数字にする
			int price;
			price = Integer.parseInt(strPrice);

			// BookDAOオブジェクト生成
			BookDAO objDao = new BookDAO();

			// 入力されたISBNの存在チェック
			if (objDao.selectByIsbn(isbn).getIsbn() == null) {
				error = "更新対象が存在しない為、書籍更新処理は行えませんでした。";
				cmd = "list";
				return;
			}

			// Bookのオブジェクトを生成し、isbn,title,priceを設定する。
			Book book = new Book();
			book.setIsbn(isbn);
			book.setTitle(title);
			book.setPrice(price);

			// 書籍を更新するメソッド
			objDao.update(book);

		} catch (NumberFormatException e) {
			error = "価格の値が不正の為、書籍更新処理は行えませんでした。";
			cmd = "list";

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、書籍更新処理は行えませんでした。";
			cmd = "logout";
		} finally {

			if (error.equals("")) {
				// エラーが無い場合はListServletにフォワード
				path = "/list";

			} else {
				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				path = "/view/error.jsp";
			}
			request.getRequestDispatcher(path).forward(request, response);
		}
	}

}
