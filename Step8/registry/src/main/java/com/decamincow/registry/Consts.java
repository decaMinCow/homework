package com.decamincow.registry;

public class Consts {

    public static final String SERVICE_NAME = "mysql_cfg";

    public static final String REGISTER_PATH = "/registry";

    public static final String SERVICE_PATH = REGISTER_PATH + "/" + SERVICE_NAME;

    public static final String ZK_HOST = "127.0.0.1";

    public static final int ZK_PORT = 2181;

    public static final String ZK_CONNECT_STRING =  ZK_HOST + ":" + ZK_PORT;
}