package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.User;

/*プログラム名 : フリマシステム
 *プログラムの説明 : UserのDAOを作成しました。
 * 作成者 : 田中涼真
 * 作成日 : 2026年6月18日
 */
public class UserDAO {
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

	public ArrayList<User> selectAll() {
		//ConnectionオブジェクトとStatementオブジェクトを生成し、初期化
		Connection con = null;
		Statement smt = null;

		//検索した書籍情報を格納するArrayListオブジェクトを生成
		ArrayList<User> list = new ArrayList<User>();

		//SQL文を文字列として定義
		String sql = "SELECT * FROM userinfo";
		try {
			//getConnection()メソッドを利用してConnectionオブジェクトを生成
			con = getConnection();

			//createStatement（）メソッドを利用してStatementオブジェクトを生成
			smt = con.createStatement();

			//executeQuery（）メソッドを利用して、SQL文を発行し結果セットを取得
			ResultSet rs = smt.executeQuery(sql);

			//果セットから書籍データを検索件数分全て取り出し、ArrayListオブジェクトにBookオブジェクトとして格納
			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getString("userid"));
				user.setLoginid(rs.getString("loginid"));
				user.setLastname(rs.getString("lastname"));
				user.setLastnameKana(rs.getString("lastname_kana"));
				user.setFirstname(rs.getString("firstname"));
				user.setFirstnameKana(rs.getString("firstname_kana"));
				user.setNickname(rs.getString("nickname"));
				user.setBirthdate(rs.getString("birthdate"));
				user.setGender(rs.getString("gender"));
				user.setJob(rs.getString("job"));
				user.setPostalCode(rs.getString("postal_code"));
				user.setPrefecture(rs.getString("prefecture"));
				user.setMunipulity(rs.getString("municipality"));
				user.setHousenumber(rs.getString("housenumber"));
				user.setBuildnumber(rs.getString("buildnumber"));
				user.setMail(rs.getString("mail"));
				user.setUserDeleteflag(rs.getString("user_deleteflag"));
				user.setUserInsertdate(rs.getString("user_insertdate"));
				user.setUserUpdatedate(rs.getString("user_updatedate"));
				user.setPaymentflag(rs.getString("paymentflag"));
				user.setPhonenumber(rs.getString("phonenumber"));
				list.add(user);
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
	public User selectByUserid(String userid) {
		//ConnectionオブジェクトとStatementオブジェクトを生成し、初期化
		Connection con = null;
		Statement smt = null;

		//検索した書籍情報を格納するBookオブジェクトを生成
		User user = new User();

		//SQL文を文字列として定義
		String sql = "SELECT * FROM userinfo WHERE userid = '" + userid + "'";
		try {
			//getConnection()メソッドを利用してConnectionオブジェクトを生成
			con = getConnection();

			//createStatement（）メソッドを利用してStatementオブジェクトを生成
			smt = con.createStatement();

			//executeQuery（）メソッドを利用して、結果セットを取得
			ResultSet rs = smt.executeQuery(sql);

			//結果セットから書籍データを取り出し、Bookオブジェクトに格納
			if (rs.next()) {
				user.setUserid(rs.getString("userid"));
				user.setLoginid(rs.getString("loginid"));
				user.setLastname(rs.getString("lastname"));
				user.setLastnameKana(rs.getString("lastname_kana"));
				user.setFirstname(rs.getString("firstname"));
				user.setFirstnameKana(rs.getString("firstname_kana"));
				user.setNickname(rs.getString("nickname"));
				user.setBirthdate(rs.getString("birthdate"));
				user.setGender(rs.getString("gender"));
				user.setJob(rs.getString("job"));
				user.setPostalCode(rs.getString("postal_code"));
				user.setPrefecture(rs.getString("prefecture"));
				user.setMunipulity(rs.getString("municipality"));
				user.setHousenumber(rs.getString("housenumber"));
				user.setBuildnumber(rs.getString("buildnumber"));
				user.setMail(rs.getString("mail"));
				user.setUserDeleteflag(rs.getString("user_deleteflag"));
				user.setUserInsertdate(rs.getString("user_insertdate"));
				user.setUserUpdatedate(rs.getString("user_updatedate"));
				user.setPaymentflag(rs.getString("paymentflag"));
				user.setPhonenumber(rs.getString("phonenumber"));
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
		return user;
	}
	
	public String nameSerch(String userid) {
		//ConnectionオブジェクトとStatementオブジェクトを生成し、初期化
		Connection con = null;
		Statement smt = null;

		//検索した書籍情報を格納するBookオブジェクトを生成
		User user = new User();
		String nickname = null;

		//SQL文を文字列として定義
		String sql = "SELECT nickname FROM userinfo WHERE userid = '" + userid + "'";
		try {
			//getConnection()メソッドを利用してConnectionオブジェクトを生成
			con = getConnection();

			//createStatement（）メソッドを利用してStatementオブジェクトを生成
			smt = con.createStatement();

			//executeQuery（）メソッドを利用して、結果セットを取得
			ResultSet rs = smt.executeQuery(sql);

			//結果セットから書籍データを取り出し、Bookオブジェクトに格納
			if (rs.next()) {
				nickname = rs.getString("nickname");
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
		return nickname;
	}
	



}