package com.robin.spi.core.utils;

import android.os.SystemClock;

import com.robin.spi.core.common.Debugger;

public abstract class LazyInitHelper {

    private final String mTag;
    private boolean mHasInit = false;

    public LazyInitHelper(String tag) {
        mTag = tag;
    }

    /**
     * 此初始化方法的调用不是必须的。
     * 使用时会按需初始化；但也可以提前调用并初始化，使用时会等待初始化完成。
     * 本方法线程安全。
     */
    public void lazyInit() {
        performInit();
    }

    /**
     * 使用时确保已经初始化；如果正在初始化，则等待完成。
     */
    public void ensureInit() {
        performInit();
    }

    private void performInit() {
        if (!mHasInit) {
            synchronized (this) {
                if (!mHasInit) {
                    long ts = 0;
                    final boolean enableLog = Debugger.isEnableLog();
                    if (enableLog) {
                        ts = SystemClock.uptimeMillis();
                    }
                    try {
                        doInit();
                        mHasInit = true;
                    } catch (Throwable t) {
                        Debugger.fatal(t);
                    }
                    if (enableLog) {
                        Debugger.i("%s init cost %s ms", mTag,
                                SystemClock.uptimeMillis() - ts);
                    }
                }
            }
        }
    }

    protected abstract void doInit();
}