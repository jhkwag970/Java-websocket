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
<input type="submit">제출
</form>

<button onclick="aa()">출력</button>

<script>

function aa (){
	
	var str = '홍길동'

	alert(str.match('홍홍길동'));
	
}


</script>

</body>
</html>