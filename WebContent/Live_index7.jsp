<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="jquery-3.3.1.js"></script>

<style>

 textarea {
 	width: 300px;
    height: 300px;
  }

</style>

</head>

<body>

<%
String user = request.getParameter("user"); 

String n = (String) request.getAttribute("n");

if (n == null){

	request.setAttribute("user", user);
	//RequestDispatcher rd1 = request.getRequestDispatcher("getAllFile.do");
	RequestDispatcher rd1 = request.getRequestDispatcher("getAllDB.do");
	rd1.forward(request, response);
	
	
	//response.sendRedirect("getAllFile.do");
}
%>
<%=user %>님
 <h3>Chat</h3>
 <textarea row = "20" col="50" readonly id = "ta1"><%=request.getAttribute("chat") %>-------<%=user%>님 입장하셨습니다-----<%="\n" %></textarea><br>
 <h3>Who?</h3>
 <input type = "Text" id = "who1" value = "<%=user%>" height = "300px" disabled>
 <h4>보낼 글</h4>
<input type = "text" id = "chat1" onkeyup="enterkey()" required>
<input type = "button" value = "가자" onclick="insertFile()">
 <br>
 귓속말시 /wnickname (chatting)을 해야합니다. 닉네임과 chatting에 빈공간이 없을시 귓속말이 안보내집니다.
<script>
	 
 
 	window.onbeforeunload = function(){
 		alert("종료전 이벤트");
 	};
 		
	function enterkey(){
		if(window.event.keyCode == 13){
			const chat = document.getElementById("chat1").value;
			
			if(chat == ""){
				alert("내용 입력하세요");
				return;
			}
			insertFile();
			kajaChool();
		}
	}
	
	function insertFile(){
		const name = document.getElementById("who1").value;
		const chat = document.getElementById("chat1").value;
		
		if(chat == ""){
			alert("내용 입력하세요");
			return;
		}
		
		
		$.ajax({ // $.ajax() $.get $.post
			url:"insertDB.do",
			//url:"insertFile.do",
			data: { 
				name : name,
				chat : chat
			}, // kaja.jsp?irum=hong&na2=35
			dataType:"text",
			type:"get",
			success: function(result1){ 
			},
			error: function(xhr1,status){
				alert("error: " + "\t" + xhr1.status);
			}
		});
		
		kajaChool();
	}
	
	function kajaChool(){
		document.getElementById("who1").disabled = true; 
		var tmp = document.getElementById("chat1").value;
		
		
		if(tmp.substring(0,2) != "/w"){
			ta1.value += "[" + who1.value + "]" + chat1.value + "\n";
			ws1.send("[" + who1.value + "]" + chat1.value);
		}else{
			var index = tmp.indexOf(" ") + 1;
			var toNickName = tmp.substring(2,index-1);
			
 			if(true){
 				ta1.value += "[" + who1.value + "]" + chat1.value + "\n";
				ws1.send("[" + who1.value + "]" + chat1.value);
			}else{
				alert("존재하지 않는 닉네임입니다.");
				chat1.value = "";
				chat1.focuse();
				return;
			}
		}
		/*
		if(tmp.indexOf("/w") == -1){
			ta1.value += "[" + who1.value + "]" + chat1.value + "\n";
		}else{
			var index = tmp.indexOf(" ") + 1;
			var toNickName = tmp.substring(2,index-1);
			ta1.value += "[" + who1.value + "]" + " >> " + "[" + toNickName + "]" + tmp.substring(index) + "\n";
		}
		*/
		// ws1.send("[" + who1.value + "]" + chat1.value);
		
		chat1.value = "";
		chat1.focuse();
	}


</script>

<script>

	const ws1 = new WebSocket("ws://localhost:8080/Live_Chat7/kaja");
	
	
	const ta1 = document.getElementById("ta1");
	
	ws1.onerror = function(aa){
		alert("error");
	};
	ws1.onopen = function(aa){
		alert("[chat]");
		who1.focus();
		who1.select();
	};
	
	ws1.onmessage = function(aa){ 
		const name = document.getElementById("who1").value;
		const arr = aa.data.split("]");
		const tmp = arr[1];
		const comp = tmp.substring(0,2) == "/w" ;
		const fromName = arr[0].substring(1)
		
		//ta1.value += comp + "\n"; 
		
		
		if(comp){
			var index = tmp.indexOf(" ") + 1;
			var toNickName = tmp.substring(2,index-1);
			if(name == toNickName){
				ta1.value += aa.data + "\n";
				notification(fromName);
			}
		}else{
			ta1.value += aa.data + "\n"; 
		}
	};
	
	function notification(fromName) {
		  // Let's check if the browser supports notifications
		  if (!("Notification" in window)) {
		    alert("지원하지 않는 브라우저 입니다");
		  }

		  // Let's check whether notification permissions have already been granted
		  else if (Notification.permission === "granted") {
		    // If it's okay let's create a notification
			  var notification = new Notification('귓속말 알림이요', {
				   icon: './pic/bell.jpg',
				   body: fromName +'님으로 부터 귓속말이 왔습니다',
				  });
				  /* notification.onclick = function() {
				   window.open('https://www.naver.com');
				  }; */
		  }

		  // Otherwise, we need to ask the user for permission
		  else if (Notification.permission !== "denied") {
		    Notification.requestPermission().then(function (permission) {
		      // If the user accepts, let's create a notification
		      if (permission === "granted") {
		    	  var notification = new Notification('귓속말 알림이요', {
					   icon: './pic/bell.jpg',
					   body: name +'님으로 부터 귓속말이 왔습니다',
					  });
					  /* notification.onclick = function() {
					   window.open('https://www.naver.com');
					  }; */
		      }
		    });
		  }

		  // At last, if the user has denied notifications, and you
		  // want to be respectful there is no need to bother them any more.
		}
	
	window.onload = function () {
		  // Let's check if the browser supports notifications
		  if (!("Notification" in window)) {
		    alert("지원하지 않는 브라우저 입니다");
		  }

		  // Let's check whether notification permissions have already been granted
		  else if (Notification.permission === "granted") {
		    // If it's okay let's create a notification
			  var notification = new Notification('어서오세요..!', {
				   icon: './pic/bell.jpg',
				   body: '어디론가 가버릴듯',
				  });
				  notification.onclick = function() {
				   window.open('https://www.naver.com');
				  };
		  }

		  // Otherwise, we need to ask the user for permission
		  else if (Notification.permission !== "denied") {
		    Notification.requestPermission().then(function (permission) {
		      // If the user accepts, let's create a notification
		      if (permission === "granted") {
		    	  var notification = new Notification('어서오세요..!', {
					   icon: './pic/bell.jpg',
					   body: '어디론가 가버릴듯',
					  });
					  notification.onclick = function() {
					   window.open('https://www.naver.com');
					  };
		      }
		    });
		  }

		  // At last, if the user has denied notifications, and you
		  // want to be respectful there is no need to bother them any more.
		  setTimeout(function(){
        	notification.close();
    		}, 5000);
		  
		  
		}
	
</script>


</body>
</html>