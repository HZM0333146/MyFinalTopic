package com.example.dick.myfinaltopic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView play_img,setting_img ,exit_img;
    boolean chang_btn_color = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play_img = (TextView) findViewById(R.id.play_img);
        setting_img = (TextView) findViewById(R.id.setting_img);
        exit_img = (TextView) findViewById(R.id.exit_img);
        //抓螢幕大小
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int cW = metrics.widthPixels;
        play_img.setText("漢字認知");
        setting_img.setText("情境教學");
        exit_img.setText("離開");

        play_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //play_img.getBackground().setColorFilter(0xFF00FF00, android.graphics.PorterDuff.Mode.MULTIPLY);
                    Intent intent = new Intent(MainActivity.this, ChineseCharacterSelection.class);
                    MainActivity.this.startActivity(intent);
                    MainActivity.this.finish();
                    chang_btn_color = false;
            }
        });

        setting_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //setting_img.getBackground().setColorFilter(0xFFFF0000, android.graphics.PorterDuff.Mode.MULTIPLY);
                    chang_btn_color = false;
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, ContextSelection.class);
                    startActivity(intent);
                    MainActivity.this.finish();
            }
        });

        exit_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //exit_img.getBackground().setColorFilter(0xFFFFFF00, android.graphics.PorterDuff.Mode.MULTIPLY);
                    chang_btn_color = false;
                    MainActivity.this.finish();
            }
        });
    }
}
