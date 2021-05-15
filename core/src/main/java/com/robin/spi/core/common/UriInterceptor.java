package com.robin.spi.core.common;

import androidx.annotation.NonNull;

public interface UriInterceptor {
    /**
     * 处理完成后，要调用 {@link UriCallback#onNext()} 或 {@link UriCallback#onComplete(int)} 方法
     */
    void intercept(@NonNull UriRequest request, @NonNull UriCallback callback);
}
