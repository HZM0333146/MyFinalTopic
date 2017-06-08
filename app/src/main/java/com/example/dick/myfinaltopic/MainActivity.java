package com.example.dick.myfinaltopic;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView play_img,setting_img ,exit_img;
    boolean chang_btn_color = true;
    //button移動
     ObjectAnimator play_Animator;
     ObjectAnimator settingAnimator ;
     ObjectAnimator exitAnimator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play_img = (ImageView) findViewById(R.id.play_img);
        setting_img = (ImageView) findViewById(R.id.setting_img);
        exit_img = (ImageView) findViewById(R.id.exit_img);
        //抓螢幕大小
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int cW = metrics.widthPixels;
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
/*
        play_Animator = ObjectAnimator.ofInt(new ImageViewAnimatorHelper(play_img), "marginLeft", -700, -8*cW/100);
        play_Animator.setDuration(1000);
        play_Animator.setRepeatMode(ValueAnimator.REVERSE);
        play_Animator.setInterpolator(new LinearInterpolator());
        settingAnimator = ObjectAnimator.ofInt(new ImageViewAnimatorHelper(setting_img), "marginLeft", -900, -cW/11);
        settingAnimator.setDuration(1125);
        settingAnimator.setRepeatMode(ValueAnimator.REVERSE);
        settingAnimator.setInterpolator(new LinearInterpolator());
        exitAnimator = ObjectAnimator.ofInt(new ImageViewAnimatorHelper(exit_img), "marginLeft", -1100,  -cW/10);
        exitAnimator.setDuration(1350);
        exitAnimator.setRepeatMode(ValueAnimator.REVERSE);
        exitAnimator.setInterpolator(new LinearInterpolator());*/
    }
    @Override
    protected void onResume(){
        super.onResume();
       /* play_Animator.start();
        settingAnimator.start();
        exitAnimator.start();*/
    }

    //Helper class for button animation
    private static class ImageViewAnimatorHelper {

        final ImageView mView;

        public ImageViewAnimatorHelper(final ImageView button) {
            mView = button;
        }

        public void setMarginLeft(final int margin) {
            final ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mView.getLayoutParams();

            params.rightMargin = margin;

            mView.setLayoutParams(params);
        }
    }
}
