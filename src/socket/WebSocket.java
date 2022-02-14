package socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;


/**
 * 
 * WebSocket (html5 websocket api)
 * 
 * (1) 지금까지의 연결 지속을 위한 polling 기법이 아닌 계속 연결이 유지되는 spec으로
 * web broweser와 webserver 사이 양방향 통신이 이루어지는 프로토콜
 * 
 * (2) 시작은 http 연결 > websocket에 의해 연결 유지 후 일정시간이 지나면 자동 해제
 *     일반적인 연결은 브라우저 - 웹서버 자료 요청 - .html 응답후 연결 해제 형식
 *     http는 stateless protocol로 client가 요청시 응답하는 one way 통신
 *     But websocket은 웹서버가 응답후에도 연결 유지, 연결이 지속상태이므로 clinet 요청이 없어도 자료 전송 
 *     (stateful protocol: 계속 요청신호를 주어 overhead 되는 polling 방식이 아님)
 * 			- 연결 유지를 위해 코드가 복잡해지고 hw적 비용  상승
 * 
 * 
 *
 */
@ServerEndpoint("/kaja") //client는 이주소로 서버에 접속
public class WebSocket {
	/**
	 * 
	 * session == socket
	 * 
	 * Session interface: 브라우저 (client)가 websocket 접속시 만들어지며
	 * 컬랙션으로 관리함 (즉, 자바의 socket 개념 .. 홀길동 socket 이도령 socket..)
	 * 
	 * 
	 * 
	 */
	//모든 session 정보
	private static List<Session> listclient = Collections.synchronizedList(new ArrayList<>());
	
	/**
	 * 
	 * 동기화 처리, 하나 처리 후 lock unlock 반복
	 * 
	 * thread-safe 컬랙션
	 * Vector, Hashtabel 은 synchronized method 로 multi - thread 문제 없음
	 * ArrayList, HashMap, HashSet 등은 multi-thread에서 not safe
	 * 
	 * #두개의 스레드가 섞일수 있음
	 * ex) hong : 안녕 lee: 방가
	 *     결과   : 안방녕가 
	 * 
	 * 
	 * 
	 * 그러브로 synchronizedList 로 safe
	 * 방법은 처리시 전체 lock
	 * (concurrent는 처리되는 해당 요소aks lock)
	 * (synchronizedMap < concurrent hashmap 속도가 빠름)
	 * @param args
	 */
	@OnOpen //client 접속시
	public void onOpen(Session ses1) {
		listclient.add(ses1);
	}
	
	
	@OnMessage //client 에서 chat 올때 
	public void onMessage(Session ses1, String chat2) throws IOException {
		
		//모든 session에 메시지 전송
		//thread-safe가 목적으로 해당 thread만 처리됨
		//위 두개 스레드 섞이는걸 방지
		synchronized(listclient) {
			
			for(Session imsi : listclient) {
				//채팅 본인 제외 모두에게 전송 (모두 전송이면, 나한테도 나오기 때문)
				if(!imsi.equals(ses1)) {
					imsi.getBasicRemote().sendText(chat2);
				}
			}
		}
	}
	
	@OnError //data 
	public void onError(Throwable e1) {
		e1.printStackTrace();
	}
	
	@OnClose // client 연결 끊어지면
	public void onClose(Session ses1) {
		listclient.remove(ses1);
		if(listclient.size() == 0) {
			//DB 삭제
			//file 삭제
		}
	}

}
