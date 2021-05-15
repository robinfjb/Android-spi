package com.robin.spi.core.service;

import androidx.annotation.NonNull;

import com.robin.spi.core.utils.ProviderPool;

public class DefaultFactory implements IFactory {

    public static final DefaultFactory INSTANCE = new DefaultFactory();

    private DefaultFactory() {

    }

    @NonNull
    @Override
    public <T> T create(@NonNull Class<T> clazz) throws Exception {
        T t = ProviderPool.create(clazz);
        if (t != null) {
            return t;
        } else {
            return clazz.newInstance();
        }
    }
}
