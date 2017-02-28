package scau.com.lprapm.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by 钟锐锋 on 2017/1/6.
 * 结余jackson的json工具类
 */
public class  JsonUtil{
    private static final Logger LOG= (Logger) LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper objectMapper;
    static {
        objectMapper=new ObjectMapper();
        /*去掉默认的时间戳格式*/
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
//        设置时区
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES,false);
//        空值不序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        反序列化，属性不存在的兼容处理
        objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        序列化日期的统一格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
//        空对象处理不报错
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
//        未知属性处理不报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
//        单引号处理
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,true);
    }

    public static <T> T toObject(String json,Class<T> clazz){
        try{
            return objectMapper.readValue(json,clazz);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(e.getMessage(),e);
        }
        return null;
    }

    public static <T> T toObject(Reader read,Class<T> clazz){
        try{
            return objectMapper.readValue(read,clazz);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(e.getMessage(),e);
        }
        return null;
    }
    /**
     * 对象转json字符串
     * @param entity
     * @return
     */
    public static <T> String toJson(T entity){
        try{
            return objectMapper.writeValueAsString(entity);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(e.getMessage(),e);
        }
        return null;
    }

    public static <T> T toCollection(String json, TypeReference<T> typeReference){
        try{
            return objectMapper.readValue(json,typeReference);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(e.getMessage(),e);
        }
        return null;
    }

    public static <T> T toCollection(Reader read, TypeReference<T> typeReference){
        try{
            return objectMapper.readValue(read,typeReference);
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(e.getMessage(),e);
        }
        return null;
    }


}