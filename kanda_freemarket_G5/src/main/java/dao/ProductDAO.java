package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Product;

public class ProductDAO {
	//データベース接続情報
	private static String RDB_DRIVE = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost/fleamadb";
	private static String USER = "root";
	private static String PASS = "root123";

	//データベースに接続するメソッド
	public static Connection getConnection() {
		try {
			//Class.forNameメソッドを利用してJDBCドライバーをロードする。
			Class.forName(RDB_DRIVE);

			//DriverManager.getConnectionメソッドを利用してConnectionオブジェクトを生成する。
			Connection con = DriverManager.getConnection(URL, USER, PASS);

			//生成されたConnectionオブジェクトをリターンする。
			return con;

		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	public ArrayList<Product> selectAll() {
		//ConnectionオブジェクトとStatementオブジェクトを生成し、初期化
		Connection con = null;
		Statement smt = null;

		//検索した書籍情報を格納するArrayListオブジェクトを生成
		ArrayList<Product> list = new ArrayList<Product>();

		//SQL文を文字列として定義
		String sql = "SELECT * FROM sellinfo";
		try {
			//getConnection()メソッドを利用してConnectionオブジェクトを生成
			con = getConnection();

			//createStatement（）メソッドを利用してStatementオブジェクトを生成
			smt = con.createStatement();

			//executeQuery（）メソッドを利用して、SQL文を発行し結果セットを取得
			ResultSet rs = smt.executeQuery(sql);

			//果セットから書籍データを検索件数分全て取り出し、ArrayListオブジェクトにBookオブジェクトとして格納
			while (rs.next()) {
				Product product = new Product();
				product.setSellid("sellid");
				product.setSellerid(rs.getString("sellerid"));
				product.setPurchaserid(rs.getString("purchaserid"));
				product.setShippingfee(rs.getInt("shippingfee"));
				product.setLoadflag(rs.getString("loadflag"));
				product.setShippingmethod(rs.getString("shippingmethod"));
				product.setShippingdays(rs.getString("shippingdays"));
				product.setProductname(rs.getString("productname"));
				product.setSalesprice(rs.getInt("salesprice"));
				product.setDescription(rs.getString("description"));
				product.setCondition(rs.getString("condition"));
				product.setCategory(rs.getString("category"));
				product.setSize(rs.getString("size"));
				product.setPhoto(rs.getString("photo"));
				product.setSellInsertdate(rs.getString("sellInsertdate"));
				product.setSellUpdatedate(rs.getString("sellUpdatedate"));
				product.setPurchasedate(rs.getString("purchasedate"));
				product.setShippingdate(rs.getString("shippingdate"));
				product.setSellstateflag(rs.getString("sellstateflag"));
				product.setPaymentflag(rs.getString("paymentflag"));
				product.setShippingflag(rs.getString("shippingflag"));
				list.add(product);
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					//Statementオブジェクトをクローズ
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					//Connectionオブジェクトをクローズ
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return list;
	}


}