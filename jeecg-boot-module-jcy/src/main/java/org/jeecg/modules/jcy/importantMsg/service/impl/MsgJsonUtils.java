package org.jeecg.modules.jcy.importantMsg.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;

public class MsgJsonUtils {
	 
	protected final Log logger = LogFactory.getLog("JsonUtils");
 
    final ObjectMapper objectMapper;
 
    /**
     * 是否打印美观格式
     */
    //boolean isPretty = true;
 
    {
        //StdSerializerProvider sp = new StdSerializerProvider();
        //sp.setNullValueSerializer(new NullSerializer());
        //objectMapper = new ObjectMapper(null, sp, null);
    	objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
 
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
    /**
     * JSON串转换为Java泛型对象，可以是各种类型，此方法最为强大。用法看测试用例。
     * @param <T>
     * @param jsonString JSON字符串
    * @param tr TypeReference,例如: new TypeReference< List<FamousUser> >(){}
     * @return List对象列表
     *  
     *  List<DataInfo> list2 = JsonUtils.json2GenericObject(jsonStr, new TypeReference<List<DataInfo>>() {});
        
        Map<String, List<State>> map2 = JsonUtils.json2GenericObject(jsonStr, new TypeReference<Map<String, List<State>>>() {});

     */
    public <T> T json2GenericObject(String jsonString, TypeReference<T> tr) {
 
        if (jsonString == null || "".equals(jsonString)) {
            return null;
        } else {
            try {
                return (T) objectMapper.readValue(jsonString, tr);
            } catch (Exception e) {
            	e.printStackTrace();
                logger.warn("json error:" + e.getMessage() + " jsonString: " + jsonString);
            }
        }
        return null;
    }
 
    /**
     * Java对象转Json字符串
     * 
     * @param object Java对象，可以是对象，数组，List,Map等
     * @return json 字符串
     */
    public String object2Json(Object object) {
        String jsonString = "";
        try {
            /*if (isPretty) {
                //jsonString = objectMapper.defaultPrettyPrintingWriter().writeValueAsString(object);
                jsonString = objectMapper.writeValueAsString(object);
            } else {*/
                jsonString = objectMapper.writeValueAsString(object);
            //}
        } catch (Exception e) {
        	e.printStackTrace();
            logger.warn("json error:" + e.getMessage());
        }
        return jsonString;
    }
 
    /**
     * Json字符串转Java对象
     * 
     * @param jsonString
     * @param c
     * @return
     */
    public Object json2Object(String jsonString, String className) {
    	Object object = null;
    	try {
    		object = json2Object(jsonString, Class.forName(className));
    	}catch(Exception e) {
        	e.printStackTrace();
            logger.warn("json error:" + e.getMessage() + 
            		" className " + className + " jsonString: " + jsonString);
    		object = null;
    	}
    	return object;
    }

    	public Object json2Object(String jsonString, Class<?> c) {
 
        if (jsonString == null || "".equals(jsonString)) {
            return null;
        } else {
            try {
                return objectMapper.readValue(jsonString, c);
            } catch (Exception e) {
            	e.printStackTrace();
                logger.warn("json error:" + e.getMessage() + " jsonString: " + jsonString);
            }
         }
        return null;
    }
 
}

/* public class NullSerializer extends JsonSerializer<Object> {

public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
        JsonProcessingException {
    jgen.writeString("");
}*/