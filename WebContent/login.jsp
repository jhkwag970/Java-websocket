<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<form action="Live_index7.jsp">

<input type="text" name = "user">
<input type="submit">����
</form>

<button onclick="aa()">���</button>

<script>

function aa (){
	
	var str = 'ȫ�浿'

	alert(str.match('ȫȫ�浿'));
	
}


</script>

</body>
</html>