package com.robin.serviceloader;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.robin.spi.core.SpiLoader;

import java.util.List;

public class MainService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        List<TestInterface> testInterface = SpiLoader.getAllServices(TestInterface.class);
        Log.e("robin", "testInterface=" + testInterface.size());
        return super.onStartCommand(intent, flags, startId);
    }
}
