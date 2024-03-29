package com.robin.spi.core;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.robin.spi.annotation.ServiceImpl;
import com.robin.spi.core.common.Debugger;
import com.robin.spi.core.exception.DefaultServiceException;
import com.robin.spi.core.method.Func0;
import com.robin.spi.core.method.Func1;
import com.robin.spi.core.method.Func2;
import com.robin.spi.core.method.Func3;
import com.robin.spi.core.method.Func4;
import com.robin.spi.core.method.Func5;
import com.robin.spi.core.method.Func6;
import com.robin.spi.core.method.Func7;
import com.robin.spi.core.method.Func8;
import com.robin.spi.core.method.Func9;
import com.robin.spi.core.method.FuncN;
import com.robin.spi.core.service.IFactory;
import com.robin.spi.core.service.ServiceLoader;

import java.util.List;

public class SpiLoader {
    /**
     * 此初始化方法必须在主线程调用。
     */
    public static void init() {
        if (!Debugger.isLogSetting()) {
            Log.w(Debugger.LOG_TAG, "!!当前未设置Logger,建议通过 Debugger.setLogger()方法设置Logger");
            Log.w(Debugger.LOG_TAG, "!!并在测试环境通过 Debugger.EnableLog(true)方法开启日志");
            Log.w(Debugger.LOG_TAG, "!!通过Debugger.setEnableDebug(true)方法在测试环境及时抛出严重类型异常");
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Debugger.fatal("初始化方法init应该在主线程调用");
        }
    }

    /**
     * 此初始化方法的调用不是必须的。
     * 使用时会按需初始化；但也可以提前调用并初始化，使用时会等待初始化完成。
     * 本方法线程安全。
     */
    public static void lazyInit() {
        ServiceLoader.lazyInit();
    }

    /**
     * 根据接口获取 {@link ServiceLoader}
     */
    public static <T> ServiceLoader<T> loadService(Class<T> clazz) {
        return ServiceLoader.load(clazz);
    }

    /**
     * 创建 指定的clazz的默认实现类的实例，如果没有任何一个实现类指定了{@link },
     * 则会判断 指定的clazz的实现类是否只有一个，如果只有一个则会使用该实现类构造
     * 如果发现有多个 指定的clazz的实现类，则会抛出异常
     *
     * @return 找不到或获取、构造失败，则返回null
     */
    public static <I, T extends I> I getService(Class<I> clazz) {
        final I service = ServiceLoader.load(clazz).get(ServiceImpl.DEFAULT_IMPL_KEY);
        if (service != null) {
            return service;
        } else {
            final List<I> services = getAllServices(clazz);
            if (services.size() == 1) {
                return services.get(0);
            } else if (services.size() > 1) {
                Debugger.fatal(DefaultServiceException.foundMoreThanOneImpl(clazz));
            }
        }
        return null;
    }

    /**
     * 创建 指定的clazz的默认实现类的实例，使用context参数构造，如果没有任何一个实现类指定了{@link },
     * 则会判断 指定的clazz的实现类是否只有一个，如果只有一个则会使用该实现类构造
     * 如果发现有多个 指定的clazz的实现类，则会抛出异常
     *
     * @return 找不到或获取、构造失败，则返回null
     */
    public static <I, T extends I> I getService(Class<I> clazz, Context context) {
        final I service = ServiceLoader.load(clazz).get(ServiceImpl.DEFAULT_IMPL_KEY, context);
        if (service != null) {
            return service;
        } else {
            final List<I> services = getAllServices(clazz, context);
            if (services.size() == 1) {
                return services.get(0);
            } else if (services.size() > 1) {
                Debugger.fatal(DefaultServiceException.foundMoreThanOneImpl(clazz));
            }
        }
        return null;
    }

    /**
     * 创建 指定的clazz的默认实现类的实例，使用指定的Factory构造，如果没有任何一个实现类指定了{@link },
     * 则会判断 指定的clazz的实现类是否只有一个，如果只有一个则会使用该实现类构造
     * 如果发现有多个 指定的clazz的实现类，则会抛出异常
     *
     * @return 找不到或获取、构造失败，则返回null
     */
    public static <I, T extends I> I getService(Class<I> clazz, IFactory factory) {
        final I service = ServiceLoader.load(clazz).get(ServiceImpl.DEFAULT_IMPL_KEY, factory);
        if (service != null) {
            return service;
        } else {
            final List<I> services = getAllServices(clazz, factory);
            if (services.size() == 1) {
                return services.get(0);
            } else if (services.size() > 1) {
                Debugger.fatal(DefaultServiceException.foundMoreThanOneImpl(clazz));
            }
        }
        return null;
    }


    /**
     * 创建指定key的实现类实例，使用 {@link} 方法或无参数构造。对于声明了singleton的实现类，不会重复创建实例。
     *
     * @return 找不到或获取、构造失败，则返回null
     */
    public static <I, T extends I> T getService(Class<I> clazz, String key) {
        return ServiceLoader.load(clazz).get(key);
    }

    /**
     * 创建指定key的实现类实例，使用Context参数构造。对于声明了singleton的实现类，不会重复创建实例。
     *
     * @return 找不到或获取、构造失败，则返回null
     */
    public static <I, T extends I> T getService(Class<I> clazz, String key, Context context) {
        return ServiceLoader.load(clazz).get(key, context);
    }

    /**
     * 创建指定key的实现类实例，使用指定的Factory构造。对于声明了singleton的实现类，不会重复创建实例。
     *
     * @param factory 用于从Class构造实例
     * @return 找不到或获取、构造失败，则返回null
     */
    public static <I, T extends I> T getService(Class<I> clazz, String key, IFactory factory) {
        return ServiceLoader.load(clazz).get(key, factory);
    }

    /**
     * 创建所有实现类的实例，使用 {@link} 方法或无参数构造。对于声明了singleton的实现类，不会重复创建实例。
     *
     * @return 可能返回EmptyList，List中的元素不为空
     */
    public static <I, T extends I> List<T> getAllServices(Class<I> clazz) {
        return ServiceLoader.load(clazz).getAll();
    }

    /**
     * 创建所有实现类的实例，使用Context参数构造。对于声明了singleton的实现类，不会重复创建实例。
     *
     * @return 可能返回EmptyList，List中的元素不为空
     */
    public static <I, T extends I> List<T> getAllServices(Class<I> clazz, Context context) {
        return ServiceLoader.load(clazz).getAll(context);
    }

    /**
     * 创建所有实现类的实例，使用指定Factory构造。对于声明了singleton的实现类，不会重复创建实例。
     *
     * @return 可能返回EmptyList，List中的元素不为空
     */
    public static <I, T extends I> List<T> getAllServices(Class<I> clazz, IFactory factory) {
        return ServiceLoader.load(clazz).getAll(factory);
    }

    /**
     * 根据key获取实现类的Class。注意，对于声明了singleton的实现类，获取Class后还是可以创建新的实例。
     *
     * @return 找不到或获取失败，则返回null
     */
    public static <I, T extends I> Class<T> getServiceClass(Class<I> clazz, String key) {
        return ServiceLoader.load(clazz).getClass(key);
    }

    /**
     * 获取所有实现类的Class。注意，对于声明了singleton的实现类，获取Class后还是可以创建新的实例。
     *
     * @return 可能返回EmptyList，List中的元素不为空
     */
    public static <I, T extends I> List<Class<T>> getAllServiceClasses(Class<I> clazz) {
        return ServiceLoader.load(clazz).getAllClasses();
    }

    /**
     * 调用方法。方法应该实现 {@link Func0} ~ {@link FuncN} 接口，根据参数个数匹配接口。
     */
    @SuppressWarnings("unchecked")
    public static <T> T callMethod(String key, Object... args) {
        switch (args.length) {
            case 0:
                return (T) getService(Func0.class, key).call();
            case 1:
                return (T) getService(Func1.class, key).call(args[0]);
            case 2:
                return (T) getService(Func2.class, key).call(args[0], args[1]);
            case 3:
                return (T) getService(Func3.class, key).call(args[0], args[1], args[2]);
            case 4:
                return (T) getService(Func4.class, key).call(
                        args[0], args[1], args[2], args[3]);
            case 5:
                return (T) getService(Func5.class, key).call(
                        args[0], args[1], args[2], args[3], args[4]);
            case 6:
                return (T) getService(Func6.class, key).call(
                        args[0], args[1], args[2], args[3], args[4], args[5]);
            case 7:
                return (T) getService(Func7.class, key).call(
                        args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
            case 8:
                return (T) getService(Func8.class, key).call(
                        args[0], args[1], args[2], args[3],
                        args[4], args[5], args[6], args[7]);
            case 9:
                return (T) getService(Func9.class, key).call(
                        args[0], args[1], args[2], args[3],
                        args[4], args[5], args[6], args[7], args[8]);
            default:
                return (T) getService(FuncN.class, key).call(args);
        }
    }
}
