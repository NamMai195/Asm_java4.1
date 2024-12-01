package poly.websocket.client;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class TextChatClient {
	final static CountDownLatch messageLatch = new CountDownLatch(1);
	public static void main(String[] args) {
		try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            URI uri = URI.create("ws://localhost:8080/websocket/simple/chat");
            System.out.println("Connecting to " + uri);
            container.connectToServer(TextChatClientEndpoint.class, uri);
            messageLatch.await(100, TimeUnit.SECONDS);
        } catch (DeploymentException | IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
	}
}
