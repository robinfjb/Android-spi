package com.robin.spi.core.common;

public interface UriResult {
    /**
     * 跳转成功
     */
    int CODE_SUCCESS = 200;
    /**
     * 重定向到其他URI，会再次跳转
     */
    int CODE_REDIRECT = 301;
    /**
     * 请求错误，通常是Context或URI为空
     */
    int CODE_BAD_REQUEST = 400;
    /**
     * 权限问题，通常是外部跳转时Activity的exported=false
     */
    int CODE_FORBIDDEN = 403;
    /**
     * 找不到目标(Activity或UriHandler)
     */
    int CODE_NOT_FOUND = 404;
    /**
     * 发生其他错误
     */
    int CODE_ERROR = 500;
}
