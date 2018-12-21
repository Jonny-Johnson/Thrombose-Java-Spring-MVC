package com.john.websocket;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ChatMessageSerializer extends StdSerializer<ChatMessage> {


	public ChatMessageSerializer() {
		this(null);
	}

	public ChatMessageSerializer(Class<ChatMessage> t) {
		super(t);
	}

	@Override
	public void serialize(ChatMessage value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
	}
	
	
}
