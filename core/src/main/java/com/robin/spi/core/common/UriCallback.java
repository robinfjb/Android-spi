package com.robin.spi.core.common;

public interface UriCallback extends UriResult {

    /**
     * 处理完成，继续后续流程。
     */
    void onNext();

    /**
     * 处理完成，终止分发流程。
     *
     * @param resultCode 结果，可参考 {@link UriResult}
     */
    void onComplete(int resultCode);
}
