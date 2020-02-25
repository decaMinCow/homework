package com.decamincow.io;

import java.io.InputStream;

/**
 * @ClassName Resources
 * @Description TODO
 * @Author decamincow
 * @Date 25/02/2020 3:57 PM
 * @Version 1.0
 **/
public class Resources {

    /**
     * 根据配置文件的路径，将配置文件加载橙子姐输入流，存储在内存中
     * @param path
     * @return
     */
    public static InputStream getResourceAsSteam(String path){
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;
    }
}
