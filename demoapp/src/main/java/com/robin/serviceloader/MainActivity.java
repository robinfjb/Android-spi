package com.robin.serviceloader;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.robin.spi.core.SpiLoader;

import java.util.List;

public class MainActivity extends AppCompatActivity implements TestInterface{
    private TestInterface testInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(v -> {
            StringBuilder sb = new StringBuilder();
            List<TestInterface> testInterface = SpiLoader.getAllServices(TestInterface.class);
            sb.append("TestInterface size = " + testInterface.size() + "\n");

            TestInterface defaultInterface = SpiLoader.getService(TestInterface.class);
            sb.append("default Interface :" + defaultInterface.method1() + "\n");

            ((TextView)findViewById(R.id.txt)).setText(sb.toString());
        });

        findViewById(R.id.btn2).setOnClickListener(v -> {
           startService(new Intent(MainActivity.this, MainService.class));
        });
    }



    @Override
    public String method1() {
        return MainActivity.class.getName();
    }
}