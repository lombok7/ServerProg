<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Error Page</title>
	</head>
	<body>
		<script type="text/javascript">
			alert("${errMsg }");
			history.back();
		</script>
	</body>
</html>