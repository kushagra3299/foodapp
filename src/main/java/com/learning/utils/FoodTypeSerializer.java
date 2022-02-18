package com.learning.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.learning.entity.TypeOfFood;

public class FoodTypeSerializer extends StdSerializer<TypeOfFood>
{
	public FoodTypeSerializer() {
		this(null);
	}

	protected FoodTypeSerializer(Class<TypeOfFood> t) {
		super(t);
	}

	@Override
	public void serialize(TypeOfFood value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeString(value.getFoodType().toString());
		
	}
}