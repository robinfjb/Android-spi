package com.robin.serviceloader;

import com.robin.spi.annotation.ISpiLoader;

@ISpiLoader(interfaces = TestInterface.class, singleton = true, defaultImpl = true)
public class TestImplOne implements TestInterface{
    @Override
    public String method1() {
        return TestImplOne.class.getName();
    }
}
