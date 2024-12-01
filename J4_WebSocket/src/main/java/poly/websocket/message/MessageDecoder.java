package poly.websocket.message;

import java.io.IOException;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageDecoder implements Decoder.Text<Message> {
	private ObjectMapper mapper = new ObjectMapper();
	@Override
	public void destroy() {}
	@Override
	public void init(EndpointConfig config) {}
	@Override
	public Message decode(String json) throws DecodeException {
		try {
			return mapper.readValue(json, Message.class);
		} catch (IOException e) {
			throw new DecodeException(json, "Unable to decode json!");
		}
	}
	@Override
	public boolean willDecode(String json) {
		return json.contains("type") && json.contains("text");
	}
}