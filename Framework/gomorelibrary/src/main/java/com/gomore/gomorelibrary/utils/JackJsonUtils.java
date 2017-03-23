package com.gomore.gomorelibrary.utils;

import android.text.TextUtils;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JackJsonUtils {
	public static <T> String toJson(T bean) {
		if (bean == null) {
			return null;
		}
		StringWriter sw = new StringWriter();
		try {
			JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
			JacksonJsonMapper.getInstance().writeValue(gen, bean);
			gen.close();
			return sw.toString();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T fromJson(String json, Class<T> clzz) throws Exception {
		if (TextUtils.isEmpty(json)) {
			return null;
		}

		T t = null;
		try {
			t = JacksonJsonMapper.getInstance().readValue(json, clzz);
		} catch (JsonParseException e) {
			throw e;
		} catch (JsonMappingException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
		return t;
	}

	public static Map<?, ?> jsonToMap(String json) {
		if (TextUtils.isEmpty(json))
			return null;
		try {
			return (Map) JacksonJsonMapper.getInstance().readValue(json,HashMap.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> List<T> json2List(String json, Class<T> mclass)throws Exception {
		if (TextUtils.isEmpty(json)) {
			return null;
		}
		JavaType javaType = getCollectionType(ArrayList.class,new Class[] { mclass });
		List lst = null;
		lst = (List) JacksonJsonMapper.getInstance().readValue(json, javaType);
		return lst;
	}

	public static JavaType getCollectionType(Class<?> collectionClass,Class<?>[] elementClasses) {
		return JacksonJsonMapper.getInstance().getTypeFactory()
				.constructParametricType(collectionClass, elementClasses);
	}
}