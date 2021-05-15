package com.robin.spi.core.service;

import android.content.Context;

import androidx.annotation.NonNull;

public class ContextFactory implements IFactory {

    private final Context mContext;

    public ContextFactory(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public <T> T create(@NonNull Class<T> clazz) throws Exception {
        return clazz.getConstructor(Context.class).newInstance(mContext);
    }
}
