<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Form Result</title>
	</head>
	<body>
		<%!
			public String convertCharacter(String str) {
			
				str = str.replaceAll("<", "&lt;");
				str = str.replaceAll(">", "&gt;");
				str = str.replaceAll("\r\n", "<br />");
					
				return str;
			}		
		%>
		
		<%
			String selection = request.getParameter("selection");
			String today = request.getParameter("today");
			
			String contents1 = convertCharacter(request.getParameter("contents1"));
			String contents2 = convertCharacter(request.getParameter("contents2"));
			String contents3 = convertCharacter(request.getParameter("contents3"));
			
			String upimage = request.getParameter("upimage");				
		%>
		<table border="1">
			<tr>
				<td colspan="2" style="text-align:center;">=== ���� ���� �Ѿ� �Խ��ϴ�. ===</td>
			</tr>
			<tr>
				<td>����</td>
				<td><%= selection %></td>
			</tr>
			<tr>
				<td>Today</td>
				<td><%= today %></td> 
			</tr>
			<tr>
				<td>����1</td>
				<td><%= contents1 %></td> 
			</tr>
			<tr>
				<td>����2</td>
				<td><%= contents2 %></td> 
			</tr>
			<tr>
				<td>����3</td>
				<td><%= contents3 %></td> 
			</tr>
			<tr>
				<td>�̹���</td>
				<td><%= upimage %></td> 
			</tr>
		</table>
	</body>
</html>