package com.yh.ProtobufUtils;

import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.googlecode.protobuf.format.JsonFormat;
import com.googlecode.protobuf.format.ProtobufFormatter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProtobufUtils {
	private static final ProtobufFormatter jsonFormat = new JsonFormat();

	private static String generateSetFuncName(String filedName) {
		return "set" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
	}

	public static void fillMessageByPOJO(MessageOrBuilder builder, Object pojo) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		for (Field field : pojo.getClass().getDeclaredFields()) {
			Object o = field.get(pojo);
			if (null == o) {
				continue;
			}

			Method declaredMethod = builder.getClass().getDeclaredMethod(generateSetFuncName(field.getName()), field.getClass());
			declaredMethod.invoke(builder, o);
		}
	}

	public static String pb2Json(Message message) {
		if(null == message) {
			return "null";
		}
		return jsonFormat.printToString(message);
	}

	public static void json2Pb(String json, Message.Builder builder) throws IOException {
		jsonFormat.merge(new ByteArrayInputStream(json.getBytes()), builder);
	}
}
