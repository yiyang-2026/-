<%@page contentType="text/html; charset=UTF-8"%>
<%@page
	import="java.util.ArrayList,bean.Trade,bean.Product,bean.User,util.MyFormat"%>
<%--出品履歴一覧 --%>


<%
//リクエストスコープのデータを取得する。
ArrayList<Trade> list = (ArrayList<Trade>) request.getAttribute("sellhistory_list");
String error = (String) request.getAttribute("error");
MyFormat myFormat = new MyFormat();
%>

<html>

<head>
<meta charset="UTF-8">
<title>出品履歴一覧</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style_user.css">
</head>


<body>
	<header>
		<h1>神田雑貨店</h1>
		<h3>出品履歴一覧</h3>

	</header>
	<main>

		<table>
			<tr>

				<th>商品名</th>
				<th>金額</th>
				<th>購入日時</th>
				<th>商品発送通知</th>

			</tr>
			<%
			if (list != null) {
				for (Trade trade : list) {
			%>
			<tr>
				<td style="text-align: center; width: 60px"><%=trade.getPurchaser()%></td>
				<td style="text-align: center; width: 60px"><%=trade.getPrice()%></td>
				<td style="text-align: center; width: 60px"><%=trade.getPurchasedate()%></td>
				<td>
					<form action="<%=request.getContextPath()%>/shippingmail" method="get">
						<input type="hidden" name="sellid" value="<%=trade.getSellid()%>">
						<input type="submit" value="発送通知送信">
					</form>
				</td>
			</tr>
			<%
			}
			}
			%>
		</table>




		<table>
			<tr>
				<td><a href="list.html">【一覧に戻る】</a></td>
			</tr>
		</table>

	</main>

</body>

</html>