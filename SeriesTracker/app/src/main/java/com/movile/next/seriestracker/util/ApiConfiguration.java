package com.movile.next.seriestracker.util;

/**
 * Created by movile on 20/06/15.
 */
public abstract class ApiConfiguration {

    public static final String URL_BASE = "https://api-v2launch.trakt.tv";
    public static final String URL_EPISODE = "/shows/{0}/seasons/{1}/episodes/{2}";
    public static final String URL_QUERY_IMAGE = "extended=full,images";
    public static final String API_VERSION = "2";
    //public static final String API_KEY = "b052b415a85d7431ead0196a463b822f17e3f7ee4ea2cfb47c9306fe5849a";
    public static final String API_KEY = "a82fc07cd7c1d02dec9e97ecda6ea5498d56eef15b6fcc3963035711604fa4ae";

    public static final Long TIMEOUT_READ = 10000l;
    public static final Long TIMEOUT_CONNECT = 15000l;


    public static final String API_URL_UPDATES = "https://movile-up-android.firebaseio.com";

    public static final String BROADCAT_ACTION_SHOW_UPDATE = "com.movile.next.seriestracker.action.SHOW_UPDATE";
}
