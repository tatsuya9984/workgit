<%@ page language = "java" contentType = "text / html; charset = UTF-8" pageEncoding = "UTF-8" %> 
<!DOCTYPE html PUBLIC "-// W3C // DTD HTML 4.01 Transitional // EN "" http://www.w3.org/TR/html4/loose.dtd "> 

<html>
<head>
<meta charset="UTF-8">
<title>BlogLogin</title>
</head>
<body>
	<h1>Blogへようこそ</h1>
		<div align="center">
			<h2>Login</h2>
			<h3>下記の項目を入力してください</h3>
				<form action="/login" method="POST" accept-charset="UTF-8">
					<table border="1">
					<tr>
						<td>アカウント名</td>
					<td><input type = "text" name = "Account_ID"></td>
					</tr>
					<tr>
						<td>PassWord</td>
						<td><input type = "text" name = "password"></td>
					</tr>
					</table>
					<input type = "submit" value = "送信">
				</form>
				<br>
				<%= request.getAttribute("errorMsg") %>
				</br>
			<a href= "signup.html">ユーザー新規登録はこちら</a>	
		</div>
</body>
</html>