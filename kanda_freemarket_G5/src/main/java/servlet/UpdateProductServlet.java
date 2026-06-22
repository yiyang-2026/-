package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import bean.Product;
import dao.ProductDAO;
import bean.User;

@WebServlet("/updateProduct")
public class UpdateProductServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";
		String path = "";

		try {
			// 入力データの文字コードの指定
			request.setCharacterEncoding("UTF-8");

			//ProductDAOとProductオブジェクト生成
			ProductDAO productDao = new ProductDAO();
			Product product = new Product();

			//セッションからuserを取得する
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			if (user == null) {
				error = "セッション切れの為、購入出来ません。";
				cmd = "logout";
				return;
			}

			//パラメータを取得する

			//sellidを取得する
			String sellid = request.getParameter("sellid");
			product.setSellid(sellid);

			//配送料負担フラグ
			String loadflag = request.getParameter("loadflag");

			//配送方法
			String shippingmethod = request.getParameter("shippingmethod");

			//発送までの日数

			String shippingdays = request.getParameter("shippingdays");

			//商品名
			String productname = request.getParameter("productname");

			//商品代金
			String strsalesprice = request.getParameter("salesprice");

			//商品紹介
			String description = request.getParameter("description");

			//商品状態
			String condition = request.getParameter("icondition");

			//カテゴリー
			String category = request.getParameter("category");

			//サイズ
			String size = request.getParameter("size");

			//写真
			Part photo = request.getPart("image");

			//更新日時
			String sellUpdatedate = request.getParameter("sellUpdatedate");

			// エラーチェック
			if (productname == null || productname.equals("")) {
				error = "商品名が未入力の為、出品更新はできませんでした。";
				cmd = "list";
				return;

			}
			if (strsalesprice == null || strsalesprice.equals("")) {
				error = "販売価格が未入力の為、出品更新はできませんでした。";
				cmd = "list";
				return;

			}

			if (description == null || description.equals("")) {
				error = "商品説明が未入力の為、出品更新はできませんでした。";
				cmd = "list";
				return;

			}
			if (category == null || category.equals("")) {
				error = "商品カテゴリーが未入力の為、出品更新はできませんでした。";
				cmd = "list";
				return;

			}
			if (size == null || size.equals("")) {
				error = "商品サイズが未入力の為、出品更新はできませんでした。";
				cmd = "list";
				return;

			}
			if (shippingmethod == null || shippingmethod.equals("")) {
				error = "配送方法が未入力の為、出品更新はできませんでした。";
				cmd = "list";
				return;

			}

			//ファイル未選択時
			if (photo == null || photo.getSize() == 0) {

				//データを登録
				error = "ファイルが未選択です。";
				cmd = "list";
				path = "/view/error.jsp";

				//終了
				return;
			}

			// アップロードされたファイルの種類を取得する
			String contentType = photo.getContentType();

			// 元のファイル名を取得する
			String originalFileName = photo.getSubmittedFileName();
			//ファイル未選択時
			if (originalFileName == null || originalFileName.isEmpty()) {

				//データを登録
				error = "ファイル名が不正です。";
				cmd = "list";
				path = "/view/error.jsp";

				//終了
				return;
			}

			// 拡張子を取得する
			String extension = getExtension(originalFileName);

			if (!isAllowedExtension(extension)) {
				//データを登録
				error = "その拡張子は使えません。";
				cmd = "list";
				path = "/view/error.jsp";

				//終了
				return;
			}

			String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss"));
			/*
			 * 元のファイル名だけを取り出す。
			 * パス情報が混ざっていても、ファイル名部分だけを使う。
			 */

			String safeFileName = Path.of(originalFileName).getFileName().toString();

			String saveFileName = now + "_" + safeFileName;
			/*
			 * webapp/image フォルダの実際の保存先パスを取得する。
			 */
			String uploadPath = getServletContext().getRealPath("/image");

			Path uploadDir = Path.of(uploadPath);

			// imageフォルダが存在しない場合は作成する
			if (!Files.exists(uploadDir)) {
				Files.createDirectories(uploadDir);
			}

			// 保存先ファイルのパスを作成する
			Path savePath = uploadDir.resolve(saveFileName);

			// アップロードされたファイルを保存する
			try (InputStream inputStream = photo.getInputStream()) {
				Files.copy(inputStream, savePath);
			} catch (FileAlreadyExistsException e) {
				error = "同名ファイルが既に存在します。";
				cmd = "list";
				path = "/view/error.jsp";
				return;
			} catch (IOException e) {
				error = "ファイル保存ができません。";
				cmd = "list";
				path = "/view/error.jsp";
				return;
			}

			//Productオブジェクトに格納

			product.setLoadflag(loadflag);
			product.setShippingmethod(shippingmethod);
			product.setShippingdays(shippingdays);
			product.setProductname(productname);
			product.setSalesprice(Integer.parseInt(strsalesprice));
			product.setDescription(description);
			product.setCondition(condition);
			product.setCategory(category);
			product.setSize(size);
			product.setPhoto(saveFileName);
			product.setSellUpdatedate(sellUpdatedate);

			//Productオブジェクトに格納されたデータをデータベースに登録
			productDao.update(product);
			request.setAttribute("product", product);

			//価格が数値以外の時
		} catch (NumberFormatException e) {

			//データを登録
			error = "価格の値が不正の為、出品変更処理は行えませんでした。";
			cmd = "list";
			path = "/view/error.jsp";

			//終了
			return;

		} catch (IllegalStateException e) {

			//データを登録
			error = "DB接続エラーの為、出品変更処理は行えませんでした。";
			cmd = "logout";
			path = "/view/error.jsp";

		} catch (Exception e) {

			//データを共有
			error = "エラー";
			cmd = "logout";
			path = "/view/error.jsp";
		} finally {
			//
			if (error.equals("")) {
				// エラーが無い場合はListServletにフォワード
				path = "/view/updateConfirm.jsp";

			} else {
				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				path = "/view/error.jsp";
			}
			request.getRequestDispatcher(path).forward(request, response);
		}

	}

	/**
	 * ファイル名から拡張子を取得する
	 */
	private String getExtension(String fileName) {
		int dotIndex = fileName.lastIndexOf(".");

		if (dotIndex == -1) {
			return "";
		}

		return fileName.substring(dotIndex).toLowerCase();
	}

	private boolean isAllowedExtension(String extension) {
		return extension.equals(".jpg")
				|| extension.equals(".jpeg")
				|| extension.equals(".png")
				|| extension.equals(".gif");
	}

}
