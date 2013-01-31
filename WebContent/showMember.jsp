<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String loginid = (String)session.getAttribute("loginid");
	String loginfilename = (String)session.getAttribute("loginfilename");

	System.out.println("loginid : " + loginid);
	System.out.println("loginfilename : " + loginfilename);	
	
	if (loginid == null) {
		
		out.print("<script>");
		out.print("alert('로그인이 필요한 페이지입니다.');");
		out.print("location.href='./index.jsp';");
		out.print("</script>");
		
	}
%>    
<!DOCTYPE html>
<html>
	<head>
		<title>Member List</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./css/member.css" rel="stylesheet" type="text/css" /> 
		<script type="text/javascript" src="./js/jquery-1.8.2.min.js"></script>
	</head>
	<body>
		<div id="memberlist">
			<h3>회원 목록</h3>
			<p /><span class="loginid"><%= loginid %></span> [ <img src="./upimage/<%= loginfilename %>" /> ] 님이 로그인 중 입니다. <a href="./member.do?cmd=logout">[로그아웃]</a>
			<table>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">이미지</th>
						<th scope="col">크기</th>
						<th scope="col">아이디</th>
						<th scope="col">비밀번호</th>
						<th scope="col">가입일시</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="mvoList" items="${mvoList }" varStatus="status">
						<tr>					
							<td>${status.count }</td>	
							<td><img src="./upimage/${mvoList.filename }" /></td>
							<td><fmt:formatNumber value="${mvoList.filesize }" groupingUsed="true" /></td>
							<td>${mvoList.id }</td>
							<td>${mvoList.pwd }</td>
							<td>${mvoList.datetime }</td>
						</tr>
					</c:forEach>	
				</tbody>			
			</table>
		</div>
	</body>
</html>