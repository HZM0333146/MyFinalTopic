package com.example.dick.myfinaltopic;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MeaningChoice extends AppCompatActivity implements View.OnClickListener{
    Button[] button = new Button[4];
    TextView Question,a2a,time;
    int rand, count=0, correct=0, wrong=0,cou=30;
    String [] vocmean=new String[]{null};
    String [] vocabulary=new String[]{null};
    String [] buttonAnswer=new String[]{null};
    String Situation=null, answer=null;
    Handler handler=new Handler();
    Random run=new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meaning_choice);
        findView();
        setSituation();
        setQuestion();
        setButton(); // button塞值
        //countDownTimer(); //倒數計時器
        for (int i = 0; i < 4; i++) {
            button[i].setOnClickListener(this);
        }
    }
    void findView(){
        Question = (TextView) findViewById(R.id.Question);
        button[0] = (Button)findViewById(R.id.button3);
        button[1] = (Button)findViewById(R.id.button4);
        button[2] = (Button)findViewById(R.id.button5);
        button[3] = (Button)findViewById(R.id.button6);
    }
    void setSituation(){
        Resources res = getResources();
        vocmean = res.getStringArray (R.array.easy_food_arry);
        vocabulary = res.getStringArray (R.array.easy_food_vocabulary);
    }
    void setQuestion(){
        rand=run.nextInt(vocabulary.length);
        Question.setText(vocabulary[rand]);
        answer=vocmean[rand];
    }
    void setButton(){
        buttonAnswer = new String[4];
        buttonAnswer[0] = answer;
        buttonAnswer[1] = vocmean[run.nextInt(vocmean.length)];
        buttonAnswer[2] = vocmean[run.nextInt(vocmean.length)];
        buttonAnswer[3] = vocmean[run.nextInt(vocmean.length)];
        a2a.setText(answer);
        RandomArray(buttonAnswer);
        for(int i=0;i<4;i++){
            button[i].setText(buttonAnswer[i]);
            button[i].setTag(buttonAnswer[i]);
        }
    }
    @Override
    public void onClick(View v) {
        String a = String.valueOf(v.getTag());
        if (a.equals(answer)) {
            Toast.makeText(getApplicationContext(),"正確",Toast.LENGTH_SHORT).show();
            // showToastMessage("正確",tine );
            correct++;
        }else{
            Toast.makeText(getApplicationContext(),"錯誤",Toast.LENGTH_SHORT).show();
            wrong++;
        }
        count++;
        setQuestion();
        setButton();
    }
    public static String[] RandomArray(String[] mArray){
        int mLength = mArray.length,mRandom ;
        String mNumber;
        for(int i = 0; i < mLength; i++) {
            mRandom = (int)(Math.random() * mLength);
            mNumber = mArray[i];
            mArray[i] = mArray[mRandom];
            mArray[mRandom] = mNumber;
        }
        return mArray;
    }

}
