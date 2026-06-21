package util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import bean.Trade;
import bean.Product;
import bean.User;

public class SendMail {

	//出品発送メール
	public void sendMail(String mail, String purchaser, String productname) {

		try {
			Properties props = System.getProperties();

			// SMTPサーバのアドレスを指定（今回はxserverのSMTPサーバを利用）
			props.put("mail.smtp.host", "sv5215.xserver.jp");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.debug", "true");

			Session session = Session.getInstance(
					props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							//メールサーバにログインするメールアドレスとパスワードを設定
							return new PasswordAuthentication("test.sender@kanda-it-school-system.com",
									"kandaSender-2025");
						}
					});

			MimeMessage mimeMessage = new MimeMessage(session);

			// 送信元メールアドレスと送信者名を指定
			mimeMessage.setFrom(
					new InternetAddress("test.sender@kanda-it-school-system.com", "神田IT School", "iso-2022-jp"));

			// 送信先メールアドレスを指定（ご自分のメールアドレスに変更）
			mimeMessage.setRecipients(Message.RecipientType.TO, mail);

			// メールのタイトルを指定
			mimeMessage.setSubject("注文情報", "iso-2022-jp");

			// メールの内容を指定

			mimeMessage.setText(
					purchaser + "様\n"
							+ "ご購入ありがとうございます。" + "　"
							+ "ご注文の商品「" + productname + "」を発送いたしましたので、お知らせいたします。" + "　"
							+ "届くまでしばらくお待ちください。");

			// メールの形式を指定
			mimeMessage.setHeader("Content-Type", "text/plain; charset=iso-2022-jp");

			// 送信日付を指定
			mimeMessage.setSentDate(new Date());

			// 送信します
			Transport.send(mimeMessage);

			// 送信成功
			System.out.println("送信に成功しました。");

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("送信に失敗しました。\n" + e);
		}
	}

	//購入メール

	public void sendMail(String mail, String purchaser, String productname, int salesprice) {

		try {
			Properties props = System.getProperties();

			// SMTPサーバのアドレスを指定（今回はxserverのSMTPサーバを利用）
			props.put("mail.smtp.host", "sv5215.xserver.jp");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.debug", "true");

			Session session = Session.getInstance(
					props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							//メールサーバにログインするメールアドレスとパスワードを設定
							return new PasswordAuthentication("test.sender@kanda-it-school-system.com",
									"kandaSender-2025");
						}
					});

			MimeMessage mimeMessage = new MimeMessage(session);

			// 送信元メールアドレスと送信者名を指定
			mimeMessage.setFrom(
					new InternetAddress("test.sender@kanda-it-school-system.com", "神田IT School", "iso-2022-jp"));

			// 送信先メールアドレスを指定（ご自分のメールアドレスに変更）
			mimeMessage.setRecipients(Message.RecipientType.TO, mail);

			// メールのタイトルを指定
			mimeMessage.setSubject("ご購入ありがとうございます。", "iso-2022-jp");

			// メールの内容を指定

			mimeMessage.setText(
					purchaser + "様\n"
							+ "ご購入ありがとうございます。" + "　"
							+ "以下内容でご注文を受け付けましたので、ご連絡致します。" + "　"
							+ "商品：" + productname + "商品代金" + salesprice + "円" + "　"
							+ "またのご利用よろしくお願いします。");

			// メールの形式を指定
			mimeMessage.setHeader("Content-Type", "text/plain; charset=iso-2022-jp");

			// 送信日付を指定
			mimeMessage.setSentDate(new Date());

			// 送信します
			Transport.send(mimeMessage);

			// 送信成功
			System.out.println("送信に成功しました。");

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("送信に失敗しました。\n" + e);
		}

	}
}
