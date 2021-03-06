package common;
import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

//WebSocket server를 이용할 거예요
@ServerEndpoint("/wsServer/{clientId}")

public class WebSocketServer {
	private volatile String clientId;
	private static ArrayList<Session> list=new ArrayList<Session>();
	//클라이언트가 websocket 프로토콜로 연결에 성공했을 때
	@OnOpen
	public void openServer(@PathParam("clientId") String id,Session session){
		System.out.println("클라리언트가 접속했어요!!:"+session);
		list.add(session);
		String enter=id+"님이 접속하셨습니다.";
		try {
			for(Session s: list){
				s.getBasicRemote().sendText(enter);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@OnClose
	public void closeClient(Session session){
		System.out.println("클라이언트가 끊어졌어요!!"+session);
		list.remove(session);
		System.out.println("size : " + list.size());
	}
	@OnMessage
	public void receiveMSG(String msg,Session session){
		System.out.println("받은 메시지:"+msg);
		//받은 메시지를 해당 client에게 다시 돌려줄꺼에요!
		System.out.println("size : " + list.size());
		try {
			for(Session s: list){
				s.getBasicRemote().sendText(msg);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
