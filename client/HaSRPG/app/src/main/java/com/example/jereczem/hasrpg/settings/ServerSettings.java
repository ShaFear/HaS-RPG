package com.example.jereczem.hasrpg.settings;

import java.net.CookieManager;

/**
 * Created by jereczem on 05.07.15.
 */
public class ServerSettings {
    public static String SERVER_URL = "http://51.255.166.17:8080/";
    public static String SOCKET_SERVER_URL = "http://51.255.166.17:3000/";
    public static final Integer SOCKET_REJOINING_TIME = 3000; //miliseconds
    public static final String SOCKET_EVENT = "event";
    public static CookieManager cookieManager = new CookieManager();
    //public static final String SERVER_URL = "http://192.168.43.128/";
    //public static final String SOCKET_SERVER_URL = "http://192.168.43.128:3000/";
}
