package poly.websocket.client;

import java.io.IOException;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class TextChatClientEndpoint {
	@OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to endpoint: " + session.getBasicRemote());
        try {
            String name = "Duke";
            System.out.println("Sending message to endpoint: " + name);
            session.getBasicRemote().sendText(name);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

	@OnMessage
    public void onMessage(String message) {
        System.out.println("Received message in client: " + message);
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }
}