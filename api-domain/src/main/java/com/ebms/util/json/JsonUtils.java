package com.ebms.util.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class JsonUtils {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new GuavaModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .enable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID)
            .enable(SerializationFeature.INDENT_OUTPUT)
            .enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
            .setNodeFactory(JsonNodeFactory.withExactBigDecimals(true))
            .setSerializationInclusion(JsonInclude.Include.NON_ABSENT);

    public static String marshalObj(Object o){
        try{
            return OBJECT_MAPPER.writeValueAsString(o);
        }
        catch(IOException e){
            throw new RuntimeException("Problem converting to Json",e);
        }
    }

    public static <T> T unmarshalObj(String obj, Class<T> clazz){
        try{
            return OBJECT_MAPPER.readValue(obj,clazz);
        }
        catch(IOException e){
            throw new RuntimeException("Problem converting to Object",e);
        }
    }

    public static byte[] writeAsBytes(Object o){
        try{
            return OBJECT_MAPPER.writeValueAsBytes(o);
        }
        catch(IOException e){
            throw new RuntimeException("Problem converting to Bytes",e);
        }
    }

    public static <T> T readFromBytes(byte[] obj, Class<T> clazz){
        try{
            return OBJECT_MAPPER.readValue(new String(obj, "UTF-8"),clazz);
        }
        catch(IOException e){
            throw new RuntimeException("Problem converting to Bytes",e);
        }
    }
}
