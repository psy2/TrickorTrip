package common;
import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

//WebSocket server를 이용할 거예요
@ServerEndpoint("/wsServer")

public class WebSocketServer {
	private static ArrayList<Session> list=new ArrayList<Session>();
	//클라이언트가 websocket 프로토콜로 연결에 성공했을 때
	@OnOpen
	public void openServer(Session session){
		System.out.println("클라리언트가 접속했어요!!:"+session);
		list.add(session);
		//여기서 session은 클라이언트의 socket이라고 생각하면 되요
	}
	@OnClose
	public void closeClient(Session session){
		System.out.println("클라이언트가 끊어졌어요!!"+session);
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
