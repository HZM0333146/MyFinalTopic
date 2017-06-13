package com.example.dick.myfinaltopic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PracticeSelection extends AppCompatActivity {
    TextView group,mean,tone;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_selection);
        group=(TextView)findViewById(R.id.group);
        mean=(TextView)findViewById(R.id.mean);
        tone=(TextView)findViewById(R.id.tone);
        back=(ImageView)findViewById(R.id.practiceselectionback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(PracticeSelection.this,SituationWordCard.class);
                startActivity(i);
                finish();
            }
        });
        group.setText("組字練習");
        mean.setText("詞義練習");
        tone.setText("音調練習");

        group.setOnClickListener(groupButton);
        mean.setOnClickListener(meanButton);
        tone.setOnClickListener(toneButton);
    }
    View.OnClickListener groupButton=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(PracticeSelection.this,GroupWordPractice.class);
            startActivity(intent);
            finish();
        }
    };
    View.OnClickListener meanButton=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(PracticeSelection.this,MeaningChoice.class);
            startActivity(intent);
            finish();

        }
    };

    View.OnClickListener toneButton=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(PracticeSelection.this,ToneExercises.class);
            startActivity(intent);
            finish();
        }
    };

}
