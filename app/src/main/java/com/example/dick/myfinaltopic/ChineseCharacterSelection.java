package com.example.dick.myfinaltopic;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class ChineseCharacterSelection extends AppCompatActivity {
    private ImageView chin_img,sit_img ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_character_selection);
        chin_img=(ImageView)findViewById(R.id.chin_img);
        sit_img=(ImageView)findViewById(R.id.sit_img);


        //抓螢幕大小
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int cW = metrics.widthPixels;
        chin_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ChineseCharacterSelection.this, PartsLearning.class);
                startActivity(intent);
                ChineseCharacterSelection.this.finish();
            }
        });

        sit_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChineseCharacterSelection.this, StructuralLearning.class);
                ChineseCharacterSelection.this.startActivity(intent);
                ChineseCharacterSelection.this.finish();
            }
        });



        //button移動
        final ObjectAnimator play_Animator = ObjectAnimator.ofInt(new ChineseCharacterSelection.ImageViewAnimatorHelper(chin_img), "marginLeft", -700, -8*cW/100);
        final ObjectAnimator settingAnimator = ObjectAnimator.ofInt(new ChineseCharacterSelection.ImageViewAnimatorHelper(sit_img), "marginLeft", -900, -cW/11);

        play_Animator.setDuration(1000);
        play_Animator.setRepeatMode(ValueAnimator.REVERSE);
        play_Animator.setInterpolator(new LinearInterpolator());
        play_Animator.start();

        settingAnimator.setDuration(1125);
        settingAnimator.setRepeatMode(ValueAnimator.REVERSE);
        settingAnimator.setInterpolator(new LinearInterpolator());
        settingAnimator.start();
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
