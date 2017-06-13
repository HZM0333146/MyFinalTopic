package com.example.dick.myfinaltopic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//漢字認知的選擇頁面
public class ChineseCharacterSelection extends AppCompatActivity {
    private ImageView back;
    private TextView structuralLearning,partsLearning,toneExercises;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_character_selection);

        back=(ImageView)findViewById(R.id.chinesecharacterselectionback);
        structuralLearning=(TextView)findViewById(R.id.strLearning);
        partsLearning=(TextView)findViewById(R.id.parLearning);
        toneExercises=(TextView)findViewById(R.id.tonExercises);

        structuralLearning.setText("結構學習");
        partsLearning.setText("部件學習");
        toneExercises.setText("音調練習");
        back.setOnClickListener(onback);
        structuralLearning.setOnClickListener(onStr);
        partsLearning.setOnClickListener(onPar);
        toneExercises.setOnClickListener(onTone);
    }
    private View.OnClickListener onback=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent();
            i.setClass(ChineseCharacterSelection.this,MainActivity.class);
            startActivity(i);
            finish();

        }
    };
    private  View.OnClickListener onStr=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent();
            i.setClass(ChineseCharacterSelection.this,StructuralLearning.class);
            startActivity(i);
            finish();

        }
    };
    private View.OnClickListener onPar=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent();
            i.setClass(ChineseCharacterSelection.this,PartsLearning.class);
            startActivity(i);
            finish();
        }
    };
    private View.OnClickListener onTone=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent();
            i.setClass(ChineseCharacterSelection.this,ToneExercises.class);
            startActivity(i);
            finish();
        }
    };
}
