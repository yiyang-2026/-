package util;

import java.text.DecimalFormat;

public class MyFormat {
	//引数に受け取った金額データを￥付き、三桁カンマ区切り、小数点第２位に形式に変換するメソッド
	public String moneyFormat (int price) {
		DecimalFormat format = new DecimalFormat("\u00A5###, ##0");
		return format.format(price);
	}

}
