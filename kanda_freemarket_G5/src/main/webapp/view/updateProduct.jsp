<%@page contentType="text/html; charset=UTF-8" %>
	<%@ page import="java.util.Date" %>
		<%@page import="bean.Product" %>
			<%@ page import="java.text.SimpleDateFormat" %>

				<% Date now=new Date(); SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); String
					sellUpdatedate=sdf.format(now); %>

					<% Product product=(Product) request.getAttribute("product"); %>

						<html>

						<head>
							<meta charset="UTF-8">
							<title>出品画面</title>
							<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style_user.css">
						</head>


						<body>
							<header>
								<h1>神田雑貨店</h1>
								<h3>商品変更</h3>

							</header>
							<main>

								<h4 style="text-align: left">
									<a href="<%=request.getContextPath()%>/productList">【一覧に戻る】</a>
								</h4>
								<form action="<%=request.getContextPath()%>/updateProduct" method="post" enctype="multipart/form-data">

									<table style="margin: 0 auto">
										<tr>

											<td><input type="hidden" name="sellid" value="<%=product.getSellid()%>"></td>
										</tr>
										<tr>
											<td><input type="hidden" name="sellUpdatedate" value="<%=sellUpdatedate%>"></td>
										</tr>


										<tr>
											<td style="width: 150; background-color: #ffc0cb">画像</td>
											<td><input type=FILE name="image"></td>
										</tr>
										<tr>
											<td style="width: 150; background-color: #ffc0cb">商品名</td>
											<td><input type="text" size="80" name="productname" value="<%=product.getProductname()%>"></td>
										</tr>
										<tr>
											<td style="width: 150; background-color: #ffc0cb">販売価格</td>
											<td><input type="text" size="80" name="salesprice" value="<%=product.getSalesprice()%>"></td>
										</tr>
										<tr>
											<td style="width: 150; background-color: #ffc0cb">商品説明</td>
											<td><textarea name="description" rows="4" cols="82"><%=product.getDescription()%></textarea></td>
										</tr>
										<tr>
											<td style="width: 150; background-color: #ffc0cb">商品の状態</td>
											<td style="size: 80;"><select name="condition">
													<option value="1">新品</option>
													<option value="2">やや傷や汚れあり</option>
													<option value="3">全体的に状態が悪い</option>
												</select></td>
										</tr>
										<tr>
											<td style="width: 150; background-color: #ffc0cb">商品カテゴリー</td>
											<td><input type="text" size="80" name="category" value="<%=product.getCategory()%>"></td>
										</tr>
										<tr>
											<td style="width: 150; background-color: #ffc0cb">商品サイズ</td>
											<td><input type="text" size="80" name="size" value="<%=product.getSize()%>"></td>
										</tr>

										<tr>
											<td style="width: 150; background-color: #ffc0cb">配送方法</td>
											<td style="size: 80;"><select name="shippingmethod">
													<option value="1">ヤマト</option>
													<option value="2">郵便局</option>
													<option value="3">コンビニ</option>
													<option value="4">その他</option>
												</select></td>
										</tr>
										<tr>
											<td style="width: 150; background-color: #ffc0cb">配送までの日数</td>
											<td><select name="shippingdays">
													<option value="1">1日で発送</option>
													<option value="2">1日～2日で発送</option>
													<option value="3">2日で発送</option>
													<option value="4">2日～3日で発送</option>
													<option value="5">3日で発送</option>
													<option value="6">3日～4日で発送</option>
													<option value="7">4日で発送</option>
													<option value="8">4日～5日で発送</option>
													<option value="9">5日で発送</option>
													<option value="10">5日～6日で発送</option>
													<option value="11">6日で発送</option>
													<option value="12">6日～7日で発送</option>
													<option value="13">7日で発送</option>
													<option value="14">7日～8日で発送</option>
													<option value="15">8日で発送</option>
													<option value="16">8日～9日で発送</option>
													<option value="17">9日で発送</option>
													<option value="18">9日～10日で発送</option>
													<option value="19">10日で発送</option>
												</select></td>
										</tr>
										<tr>
											<td style="width: 150; background-color: #ffc0cb">配送料の負担</td>
											<td><input type="radio" name="loadflag" value="0" checked>出品者
												<input type="radio" name="loadflag" value="1">購入者
										</tr>

									</table>
									<p style="text-align: center">
										<input type="submit" value="更新">

									</p>
								</form>
							</main>

						</body>

						</html>
