<%@page contentType="text/html; charset=UTF-8"%>
<html>

<head>
<meta charset="UTF-8">
<title>会員登録</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style_user.css">
</head>


<body>
	<header>
		<h1>神田雑貨店</h1>
		<h3>会員登録</h3>

	</header>
	<main>

		<h4 style="text-align: left">
			 <a href="<%=request.getContextPath()%>/list">【一覧に戻る】</a>
		</h4>

		<form action="<%=request.getContextPath()%>/userInser" method="post">
			<table style="margin: 0 auto">
				<tr>
					<td style="width: 150; background-color: #ffc0cb">ログインID:</td>
					<td><input type="text" size="80" name="loginin"></td>
				</tr>
				<tr>
					<td style="width: 150; background-color: #ffc0cb">パスワード:</td>
					<td><input type="text" size="80" name="password"></td>
				</tr>
				<tr>
					<td style="width: 150; background-color: #ffc0cb">名前（姓）:</td>
					<td><input type="text" size="80" name="lastname"></td>
				</tr>
				<tr>
					<td style="width: 150; background-color: #ffc0cb">姓（フリガナ）:</td>
					<td><input type="text" size="80" name="lastnameKana"></td>
				</tr>
				<tr>

					<td style="width: 150; background-color: #ffc0cb">名前（名）:</td>
					<td><input type="text" size="80" name="firstname"></td>
				</tr>
				<tr>
					<td style="width: 150; background-color: #ffc0cb">名（フリガナ）:</td>
					<td><input type="text" size="80" name="firstnameKana"></td>
				</tr>
				<tr>
					<td style="width: 150; background-color: #ffc0cb">姓（フリガナ）:</td>
					<td><input type="text" size="80" name="lastnameKana"></td>
				</tr>
				<tr>
					<td style="width: 150; background-color: #ffc0cb">ニックネーム:</td>
					<td><input type="text" size="80" name="nickname"></td>
				</tr>
				<tr>
					<td style="width: 150; background-color: #ffc0cb">生年月日:</td>
					<td><input type="text" size="80" name="birthdate"></td>
				</tr>
				<tr>
					<td style="width: 150; background-color: #ffc0cb">性別:</td>
					<td>
					<input type="radio"  name="pay[]" value="0" checked>男姓
						<input type="radio"  name="gender[]" value="1">女姓
						<input type="radio"  name="gender[]" value="2">その他
					</td>
				</tr>
				<tr>
					<td style="width: 150; background-color: #ffc0cb">職業:</td>
					<td><input type="text" size="80" name="job"></td>
				</tr>
				<tr>
					<td style="width: 150; background-color: #ffc0cb">郵便番号:</td>
					<td><input type="text" size="80" name="postalCode"></td>
				</tr>
				<tr>
					<td style="width: 150; background-color: #ffc0cb">都道府県:</td>
					<td><input type="text" size="80" name="prefecture"></td>
				</tr>
				<tr>
					<td style="width: 150; background-color: #ffc0cb">市区町村:</td>
					<td>
						<select name="munipulity">
							<option value="1">北海道</option>
							<option value="2">青森県</option>
							<option value="3">岩手県</option>
							<option value="4">宮城県</option>
							<option value="5">秋田県</option>
							<option value="6">山形県</option>
							<option value="7">福島県</option>
							<option value="8">茨城県</option>
							<option value="9">栃木県</option>
							<option value="10">群馬県</option>
							<option value="11">埼玉県</option>
							<option value="12">千葉県</option>
							<option value="13">東京都</option>
							<option value="14">神奈川県</option>
							<option value="15">新潟県</option>
							<option value="16">富山県</option>
							<option value="17">石川県</option>
							<option value="18">福井県</option>
							<option value="19">山梨県</option>
							<option value="20">長野県</option>
							<option value="21">岐阜県</option>
							<option value="22">静岡県</option>
							<option value="23">愛知県</option>
							<option value="24">三重県</option>
							<option value="25">滋賀県</option>
							<option value="26">京都府</option>
							<option value="27">大阪府</option>
							<option value="28">兵庫県</option>
							<option value="29">奈良県</option>
							<option value="30">和歌山県</option>
							<option value="31">鳥取県</option>
							<option value="32">島根県</option>
							<option value="33">岡山県</option>
							<option value="34">広島県</option>
							<option value="35">山口県</option>
							<option value="36">福島県</option>
							<option value="37">香川県</option>
							<option value="38">愛媛県</option>
							<option value="39">高知県</option>
							<option value="40">福岡県</option>
							<option value="41">佐賀県</option>
							<option value="42">長崎県</option>
							<option value="43">熊本県</option>
							<option value="44">大分県</option>
							<option value="45">宮崎県</option>
							<option value="46">鹿児島県</option>
							<option value="47">沖縄県</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="width: 150; background-color: #ffc0cb">番地:</td>
					<td><input type="text" size="80" name="housenumber"></td>
				</tr>
				<tr>
					<td style="width: 150; background-color: #ffc0cb">建物名/部屋番号:</td>
					<td><input type="text" size="80" name="buildnumber"></td>
				</tr>
				<tr>
					<td style="width: 150; background-color: #ffc0cb">電話番号:</td>
					<td><input type="text" size="80" name="phonenumber"></td>
				</tr>
				<tr>
					<td style="width: 150; background-color: #ffc0cb">メールアドレス:</td>
					<td><input type="text" size="80" name="mail"></td>
				</tr>
				<tr>
					<td style="width: 150; background-color: #ffc0cb">支払方法:</td>
					<td>
						<input type="radio"  name="pay[]" value="0" checked>クレジットカード
						<input type="radio"  name="pay[]" value="1">銀行振込
						<input type="radio"  name="pay[]" value="2">コンビニ払い
						<input type="radio"  name="pay[]" value="3">バーコード決済
					</td>
				</tr>
			</table>

			<p style="text-align: center">
				<input type="submit" value="会員登録">
			</p>
		</form>


	</main>

</body>

</html>