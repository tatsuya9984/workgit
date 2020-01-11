<%@ page language = "java" contentType = "text / html; charset = UTF-8" pageEncoding = "UTF-8" %> 
<!DOCTYPE html PUBLIC "-// W3C // DTD HTML 4.01 Transitional // EN "" http://www.w3.org/TR/html4/loose.dtd "> 

<html>
<head>
<meta charset="UTF-8">
<title>SignUp</title>
</head>
<body>
<h1>新規ユーザ登録</h1>
<h2></h2>
<form th:action="@{/login}" method="post" accept-charset="UTF-8">
<table>
				<tr>
					<th>ユーザーID</th>
					<td>
						<div>
							<input type="text"/>
						</div>
					</td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td>
						<div>
							<input type="text"/>
						</div>
					</td>
				</tr>
				<tr>
					<th>ユーザー名</th>
					<td>
						<div>
							<input type="text"/>
						</div>
					</td>
				</tr>
				<tr>
					<th>誕生日</th>
					<td>
						<div>
							<input type="text" placeholder="yyyy/MM/dd"/>
						</div>
					</td>
				</tr>
				<tr>
					<th>年齢</th>
					<td>
						<div>
							<input type="text"/>
						</div>
					</td>
				</tr>
				<tr>
					<th>性別</th>
					<td>
						<div>
							<div th:each="item:${radioSex}">
								<input type="radio" name="radioSex" th:text="${item.key}" th:value="${item.value}"></input>
							</div>
						</div>
					</td>
				</tr>
			</table>
<button type="submit">ユーザー登録</button>

</form>
</body>
</html>