package com.robin.spi.annotation;

public class Const {
    private static final String PKG = "com.robin.spi.core.";

    public static final String NAME = "SpiLoader";

    // 生成的代码
    public static final String GEN_PKG = PKG + "generated";
    public static final String GEN_PKG_SERVICE = GEN_PKG + ".service";
    public static final String SPLITTER = "_";

    /**
     * ServiceLoader初始化
     */
    public static final String SERVICE_LOADER_INIT = GEN_PKG + ".ServiceLoaderInit";
    public static final char DOT = '.';
    public static final String INIT_METHOD = "init";

    public static final String URI_INTERCEPTOR_CLASS = PKG + "common.UriInterceptor";
    public static final String SERVICE_LOADER_CLASS = PKG + "service.ServiceLoader";
    // Android中的类名
    public static final String ACTIVITY_CLASS = "android.app.Activity";
    // Android中的类名
    public static final String FRAGMENT_CLASS = "android.app.Fragment";
    public static final String FRAGMENT_V4_CLASS = "android.support.v4.app.Fragment";
}
