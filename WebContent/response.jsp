<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Welcome to join us!</title>
	</head>
	<body>
		<%
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
		%>
		<table border="0">
				<tr>
					<td colspan="2" style="text-align:center;">=== 다음 정보로 가입하셨습니다. ===</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td><%= id %></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><%= pwd %></td> 
				</tr>
			</table>
	</body>
</html>