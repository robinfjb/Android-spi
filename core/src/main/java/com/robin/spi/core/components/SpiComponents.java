package com.robin.spi.core.components;

import androidx.annotation.NonNull;

import com.robin.spi.core.service.DefaultFactory;
import com.robin.spi.core.service.IFactory;

public class SpiComponents {
    @NonNull
    private static IFactory sDefaultFactory = DefaultFactory.INSTANCE;


    public static void setDefaultFactory(IFactory factory) {
        sDefaultFactory = factory == null ? DefaultFactory.INSTANCE : factory;
    }

    @NonNull
    public static IFactory getDefaultFactory() {
        return sDefaultFactory;
    }
}
