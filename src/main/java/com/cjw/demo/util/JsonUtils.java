package com.cjw.demo.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Set;

public class JsonUtils {
	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	
	@SuppressWarnings("unchecked")
	public static Object getValueByKey(String json,String key) throws Exception {
        Map<String, Object> maps =  new ObjectMapper().readValue(json, Map.class);
        Set<String> keys = maps.keySet();
        for(String k:keys){
        	if(k.equals(key)){	
        		return maps.get(k);
        	}    	
        }	        	   
	    return null;
	}
	@SuppressWarnings("unchecked")
	public static String put(String json,String key,Object value) throws Exception {
	        Map<String, Object> maps =  new ObjectMapper().readValue(json, Map.class);
	        maps.put(key,value);
    		return toJson(maps);      
	}
	public static <T> T fromJson(String str, Class<T> valueType) throws IOException {
		if(StringUtils.isBlank(str)){
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		// to prevent exception when encountering unknown property:
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		// to allow coercion of JSON empty String ("") to null Object value:
		//mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		return mapper.readValue(str, valueType);
	}
	
	public static String toJson(Object pojo){
		return toJson(pojo, true);
	}

	public static String toJson(Object pojo, boolean prettyPrint){
		return toJson(pojo, prettyPrint, true);
	}

	public static String toJson(Object pojo, boolean prettyPrint, boolean CustomObjectMap){
		try {
			ObjectMapper objectMapper = CustomObjectMap? new MyJacksonObjectMapper() : new ObjectMapper();
			if (prettyPrint) {
				// to enable standard indentation ("pretty-printing"):
				objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
				objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
				objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			}
			return objectMapper.writeValueAsString(pojo);
		}catch (JsonProcessingException ex){
			logger.error("toJson fail!", ex);
			throw new RuntimeException("toJson() fail!", ex);
		}
	}
	
	public static String toJsonDouble(Object pojo){
		return toJsonDouble(pojo, true);
	}
	public static String toJsonDouble(Object pojo, boolean prettyPrint){
		return toJsonDouble(pojo, prettyPrint, true);
	}
	public static String toJsonDouble(Object pojo, boolean prettyPrint, boolean CustomObjectMap){
		try {
			ObjectMapper objectMapper = CustomObjectMap? new MyJacksonObjectMapper() : new ObjectMapper();
			if (prettyPrint) {
				// to enable standard indentation ("pretty-printing"):
				objectMapper.setSerializationInclusion(Include.NON_NULL);
				objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
				objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
				objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			}
			return objectMapper.writeValueAsString(pojo);
		}catch (JsonProcessingException ex){
			logger.error("toJson fail!", ex);
			throw new RuntimeException("toJson() fail!", ex);
		}
	}
}

class MyNullSerializer extends JsonSerializer<Object> {
	public void serialize(Object value, JsonGenerator jgen,
						  SerializerProvider provider)
			throws IOException, JsonProcessingException
	{
		jgen.writeString("");
	}
}

class MyBooleanSerializer extends JsonSerializer<Boolean> {
	@Override
	public void serialize(Boolean value, JsonGenerator jgen,
						  SerializerProvider provider)
			throws IOException, JsonProcessingException
	{
		if(value==null){
			throw new NullPointerException("value can not be null!");
		}
		if (value){
			jgen.writeString("true");
		}else{
			jgen.writeString("false");
		}
	}
}

class MyJacksonObjectMapper extends ObjectMapper {
	public MyJacksonObjectMapper() {
		super();
		DefaultSerializerProvider.Impl sp = new DefaultSerializerProvider.Impl();
		sp.setNullValueSerializer(new MyNullSerializer());
		this.setSerializerProvider(sp);

		this.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);

		SimpleModule simpleModule = new SimpleModule("MyBooleanSerializerModule");
		simpleModule.addSerializer(Boolean.class, new MyBooleanSerializer());
		simpleModule.addSerializer(boolean.class, new MyBooleanSerializer());
		registerModule(simpleModule);
	}
}