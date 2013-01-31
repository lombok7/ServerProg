<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
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