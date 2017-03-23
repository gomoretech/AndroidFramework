package com.gomore.gomorelibrary.utils;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.text.SimpleDateFormat;

public class JacksonJsonMapper {
	 private static volatile ObjectMapper objectMapper = null;
	 private static  String myDateFormatType = "yyyy-MM-dd HH:mm:ss";
	 private static SimpleDateFormat myDateFormat = new SimpleDateFormat(myDateFormatType); 

	  public static ObjectMapper getInstance()
	  {
	    if (objectMapper == null) {
	      synchronized (ObjectMapper.class) {
	        if (objectMapper == null) {
	          objectMapper = new ObjectMapper();

	          objectMapper.disable(new DeserializationConfig.Feature[] {DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES });
	          objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	          objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);   
	          objectMapper.setDateFormat(myDateFormat);  
	        }
	      }
	    }
	    return objectMapper;
	  }
	}