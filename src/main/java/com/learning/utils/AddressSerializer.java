package com.learning.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.learning.entity.Address;

public class AddressSerializer extends StdSerializer<Address> {

	public AddressSerializer() {
		this(null);
	}

	protected AddressSerializer(Class<Address> t) {
		super(t);
	}

	@Override
	public void serialize(Address value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeString(value.getAddress());
	}

}
