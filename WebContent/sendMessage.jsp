<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<script type="text/javascript" src="./js/jquery-1.8.2.min.js"></script>
		<link href="./css/send.css" rel="stylesheet" type="text/css" /> 
		<title>File Upload Result</title>
		<script type="text/javascript">
			function getTodayDateTime(type) {
				var today = new Date();
					
				if (type == "1") {
				
					var yyyy = today.getFullYear();
					var mm = today.getMonth() + 1;
					var dd = today.getDate();
						
					var todayYYYYMMDD = yyyy + "�� " + mm + "�� " + dd + "��"
						
					// document.getElementById('divtoday').innerText = today;
					$('#divtoday').text(todayYYYYMMDD);
				
				} else if (type == "2") {
					// ���� ��¥ �ð� form�� �Է�
					// document.frm_send.today.value = today.getTime();
					$('#today').val(today.getTime());		
				} 
			}	
					
			function send() {
				// �޺��ڽ� Ȯ��.
				var selected = document.frm_send.selection;//$("#selection");
				var selectedIndex = selected.options[selected.selectedIndex].value;
				if (selectedIndex == 0) {
					alert('�ɼ��� �����ϼ���');
					return false;
				}
				
				getTodayDateTime(2);
				
				document.frm_send.submit();
			}
		</script>

	</head>
	<body onload="javascrpit:getTodayDateTime(1);">
		<div id="send">
			<h3>�޽��� ������</h3>
			<form name="frm_send" method="post" action="./showResult2.jsp" >
				<fieldset>
					<table border="0">
						<tbody>
							<tr style="padding-top:100px;">
								<td style="padding-top:25px;">
									<select name="selection" id="selection">
										<option value="0">�����ϼ���.</option>
										<option value="1">�̺�Ʈ</option>
										<option value="2">�˸�</option>
										<option value="3">�׽�Ʈ</option>
									</select>
								</td>
								<td>
									<div id="divtoday"></div>  
								</td>
							</tr>
							<tr><td colspan="2"><b>Text1</b><br /><textarea name="contents1" id="contents1" rows="10" cols="100"></textarea></td></tr>
							<tr><td colspan="2"><b>Text2</b><br /><textarea name="contents2" id="contents2" rows="10" cols="100"></textarea></td></tr>
							<tr><td colspan="2"><b>Text3</b><br /><textarea name="contents3" id="contents3" rows="10" cols="100"></textarea></td></tr>
							<tr>
								<td colspan="2">
									<input name="upimage" id="upimage"  type="file" style="width:690px;" accept="image/*">
								</td> 
							</tr>
							<tr>
								<td colspan="2" class="input_btn">
									<input id="btn_join"  type="button" value="������" onClick="javascript:send();">
									<input id="btn_reset" type="button" value="����" onClick="javascript:reset();">
								</td>
							</tr>
						</tbody>
					</table>
				</fieldset>
				<input name="today" id="today" type="hidden" value="" />
			</form>
		</div>
	</body>
</html>