package com.decamincow.practice.util;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * @ClassName TokenGen
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/27 4:00 PM
 * @Version 1.0
 **/
public class TokenGen {
    private final static String accessKeyId = "osdjfoasjf";
    public static String genSign(String params) {
        String md5_16 = DigestUtils.md5Hex(params + accessKeyId);
        return md5_16;
    }
}
