package com.robin.serviceloader;

import com.robin.spi.annotation.ISpiLoader;

@ISpiLoader(interfaces = TestInterface.class, singleton = true)
public class TestImplTwo implements TestInterface {
    @Override
    public String method1() {
        return TestImplTwo.class.getName();
    }
}
