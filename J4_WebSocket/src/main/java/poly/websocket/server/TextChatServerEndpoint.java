package poly.websocket.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/text/chat")
public class TextChatServerEndpoint {
	// Duy trì danh sách session của các client đang kết nối
	private static Map<String, Session> sessions = new HashMap<>();
	
	// Gửi @message đến tất cả client đang kết nối
	private void broadcast(String message) {
		sessions.forEach((id, session) -> {
			try {
				session.getUserProperties();
				session.close();
				session.isOpen();
				session.getBasicRemote().sendText(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@OnOpen
	public void onOpen(Session session) {
		sessions.put(session.getId(), session);
		this.broadcast("Someone joined the chat!");
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		try {
			this.broadcast(message);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@OnClose
	public void onClose(Session session) {
		sessions.remove(session.getId());
		this.broadcast("Someone left the chat!");
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		try {
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}