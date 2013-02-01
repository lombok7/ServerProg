<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Join Result</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./css/member.css" rel="stylesheet" type="text/css" /> 
		<script type="text/javascript" src="./js/jquery-1.8.2.min.js"></script>
	</head>
	<body>
		<div id="joinresult">
			<h3>* 다음 정보로 회원 가입에 성공하였습니다~^^</h3>
			<table>
				<tbody>
					<tr>
						<td class="image" rowspan="4"><img src="${filewebpath }" /></td>
						<th scope="row">아이디</th> 
						<td class="value">${member.id }</td>
					</tr>
					<tr>
						<th scope="row">비밀번호</th>
						<td class="value">${member.pwd }</td>
					</tr>
					<tr>
						<th scope="row">파일경로</th>
						<td class="value">${filelocalpath }</td>
					</tr>
					<tr>
						<th scope="row">파일크기(KB)</th>
						<td class="value">${member.filesize }</td>
					</tr>
					<tr>
						<td colspan="3" class="taright">[<a href="./index.jsp">로그인</a>]</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>