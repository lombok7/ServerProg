<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Join / Login Member Page</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./css/member.css" rel="stylesheet" type="text/css" /> 
		<script type="text/javascript" src="./js/jquery-1.8.2.min.js"></script>
		<script type="text/javascript">
			function chk_form(process) {
				if ($("#id").val() == "") {
					alert('아이디를 입력하세요.');
					return false;	
				}
				
				if ($("#pwd").val() == "") {
					alert('비밀번호를 입력하세요.');
					return false;
				}				
				
				if (process == "join") {
					
					document.frm_member.action = "./member.do?cmd=insert";
				
				} else if (process == "login") {
				
					document.frm_member.encoding = "";
					document.frm_member.action = "./member.do?cmd=login";
					
				}
				
				document.frm_member.submit();
			}
			
		</script>	
	</head>
	<body>
		<div id="memberfirst">
			<h3>회원 가입 / 로그인</h3>
			<p>*표시는 필수 항목입니다.</p>
			<form name="frm_member" method="post" enctype="multipart/form-data">
				<fieldset> 
					<table>
						<tbody>
							<tr>
								<th scope="row">*아이디</th>
								<td><input id="id" name="id" type="text" maxlength="10" required="required"></td>
							</tr>
							<tr>
								<th scope="row">*비밀번호</th>
								<td><input id="pwd" name="pwd" type="password" maxlength="10" required="required"></td> 
							</tr>
							<tr>
								<th scope="row">이미지 (1M 이하)</th>
								<td><input id="upimage" name="upimage" type="file" accept="image/*"></td>
							</tr>
							<tr>
								<td colspan="2" class="btn_area">
									<input id="btn_join"  type="button" value="회원가입" onClick="javascript:chk_form('join');"> 
									<input id="btn_login" type="button" value="로그인" onClick="javascript:chk_form('login');">
								</td>
							</tr>
						</tbody>
					</table>
				</fieldset>	
			</form>
		</div>
	</body>
</html>