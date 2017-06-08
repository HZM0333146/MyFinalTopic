package com.example.dick.myfinaltopic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PracticeSelection extends AppCompatActivity {
    Button group,mean,tone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_selection);
        group=(Button)findViewById(R.id.group);
        mean=(Button)findViewById(R.id.mean);
        tone=(Button)findViewById(R.id.tone);

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

        }
    };
    View.OnClickListener meanButton=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(PracticeSelection.this,MeaningChoice.class);
            startActivity(intent);
        }
    };

    View.OnClickListener toneButton=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(PracticeSelection.this,ToneExercises.class);
            startActivity(intent);
        }
    };

}
