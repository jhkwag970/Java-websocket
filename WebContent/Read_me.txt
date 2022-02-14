프로젝트 시작전 확인사항


*** login.jsp > form action = "live_index#.jsp" live_index#.jsp와 맞추기
    (대부분 되어있겠지만, 프로젝트 실행전 확인해주면 좋다)


*** Live_Chat#(project) > webcontent >Live_index#.jsp

       하단 부분 const ws1 = new WebSocket("ws://localhost:8080/Live_Chat#/kaja");

    #끼리 번호가 같아야 한다.
    
    (대부분 되어있겠지만, 프로젝트 실행전 확인해주면 좋다)
    
*** 프로젝트 실행전 c:/Chat_Log/chat_log.txt 파일을 먼저 셍성해야 한다. 
    (ServletPath로 상대경로로 해결 가능 (metadata안에 파일 자동 생성됨), 프로젝트를 옮겨 다닐때 될지 확신 불가)
       해결 방안: 사실상 DB쓰면 전혀 문제가 되지 않는다.
       
       
 *** fileController getrequestdispatcher 수정