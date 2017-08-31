package com.shenme.mvp_kotlin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.shenme.mvp_kotlin.mvp.view.activity.AndroidActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_android).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AndroidActivity.class);
                intent.putExtra("request_type", "android");
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_ios).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AndroidActivity.class);
                intent.putExtra("request_type", "ios");
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_front).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AndroidActivity.class);
                intent.putExtra("request_type", "front");
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_expand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AndroidActivity.class);
                intent.putExtra("request_type", "expand");
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AndroidActivity.class);
                intent.putExtra("request_type", "video");
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_Images).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AndroidActivity.class);
                intent.putExtra("request_type", "images");
                startActivity(intent);
            }
        });
    }
}
