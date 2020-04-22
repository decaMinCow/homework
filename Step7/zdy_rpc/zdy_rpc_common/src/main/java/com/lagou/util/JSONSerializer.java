package com.lagou.util;

import com.alibaba.fastjson.JSON;

/**
 * @ClassName JSONSerializer
 * @Description TODO
 * @Author decamincow
 * @Date 2020/4/22 3:58 PM
 * @Version 1.0
 **/
public class JSONSerializer implements Serializer {

    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }

    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }
}