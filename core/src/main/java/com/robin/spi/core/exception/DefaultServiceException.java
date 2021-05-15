package com.robin.spi.core.exception;

public class DefaultServiceException extends RuntimeException {

    public DefaultServiceException(String msg){
        super(msg);
    }

    public static DefaultServiceException foundMoreThanOneImpl(Class service) {
        return new DefaultServiceException("因为" + service.getCanonicalName() + "有多个实现类,Loader无法决定默认使用哪个来构造实例;"
                + "你可以通过@ISpiLoader的defaultImpl参数设置一个默认的实现类");
    }
}
