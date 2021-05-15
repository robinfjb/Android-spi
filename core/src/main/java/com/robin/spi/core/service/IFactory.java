package com.robin.spi.core.service;

import androidx.annotation.NonNull;

public interface IFactory {
    @NonNull
    <T> T create(@NonNull Class<T> clazz) throws Exception;
}
