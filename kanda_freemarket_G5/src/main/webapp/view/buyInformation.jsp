<%@page contentType="text/html; charset=UTF-8"%>
<%@page
	import="java.util.ArrayList,bean.Product,bean.Trade,bean.User,util.MyFormat"%>


<%--購入情報 --%>

<%
//リクエストスコープから商品のデータを取得する。
Product product = (Product) request.getAttribute("product");
String error = (String) request.getAttribute("error");

//セッションからユーザーのデータを取得する。
User user = (User) session.getAttribute("user");

MyFormat myFormat = new MyFormat();
%>


<html>

<head>
<meta charset="UTF-8">
<title>購入</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style_user.css">
</head>


<body>
	<header>
		<h1>神田雑貨店</h1>
		<h3>購入手続き</h3>

	</header>
	<main>

		<table>
			<tr>
				<th>支払い方法</th>
				<td><%=user.getPaymentflag()%></td>
				<th>商品代金</th>
				<td><%=product.getSalesprice()%></td>
				<th>配送先</th>
				<td><%=user.getPostalCode()%> <%=user.getPrefecture()%> <%=user.getMunipulity()%>
					<%=user.getHousenumber()%> <%=user.getBuildnumber()%></td>


				<th>発送までの日数</th>
				<td><%=product.getShippingdate()%></td>

				<td>
					<form action="<%=request.getContextPath()%>/buyInformation"
						method="get">

						<input type="hidden" name="userid" value="<%=user.getUserid()%>">
						<input type="hidden" name="sellid"
							value="<%=product.getSellid()%>"> 
							
						<input type="submit" value="購入確認へ">
					</form>
				</td>
			</tr>
		</table>

		<table>
			<tr>
				<td><a href="list.html">【一覧に戻る】</a></td>
			</tr>
		</table>

	</main>

</body>

</html>
