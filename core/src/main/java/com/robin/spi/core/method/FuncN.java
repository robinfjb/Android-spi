package com.robin.spi.core.method;

public interface FuncN<R> extends Function {
    R call(Object... args);
}
