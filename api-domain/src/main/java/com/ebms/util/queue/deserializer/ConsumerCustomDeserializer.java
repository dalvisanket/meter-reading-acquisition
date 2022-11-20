package com.ebms.util.queue.deserializer;

import com.ebms.util.json.JsonUtils;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class ConsumerCustomDeserializer implements Deserializer {
    private final Map<String,Class<?>> topicClassMap;

    public ConsumerCustomDeserializer(Map<String,Class<?>> topicClassMap){
        this.topicClassMap = topicClassMap;
    }

    @Override
    public void configure(Map configs, boolean isKey) { }

    @Override
    public void close() { }

    @Override
    public Object deserialize(String topic, byte[] data) {
        Class<?> deserializedClass = topicClassMap.get(topic);
        try{
            return JsonUtils.readFromBytes(data,deserializedClass);
        }
        catch(Exception e){
            throw new RuntimeException("Failed while deserializing",e);
        }
    }
}
