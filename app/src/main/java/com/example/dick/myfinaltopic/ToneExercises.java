package com.example.dick.myfinaltopic;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class ToneExercises extends AppCompatActivity {
    private ImageView back,imageView6;

    private Button btn_resume;
    private ImageView iv_canvas,background, question_background,btn_sound;
    private Bitmap baseBitmap;
    private Canvas canvas;
    private Paint paint;
TextView word;
    TextToSpeech textToSpeech;
    int answe_int[] = {1,2,4,0,4,1,4,2,3,4,4,1,3,1,4};
    String speak[] = {"區","來","或","個","這","推","進","回","指","但","變","安","保","消","愛"};
    String text[] = {"Qū","lái","huò","gè","zhè","tuī","jìn","huí","zhǐ","dàn","biàn","ān","bǎo","xiāo","ài"};
    int img[] = {R.drawable.dv1, R.drawable.dv2, R.drawable.dv3, R.drawable.dv4, R.drawable.dv5, R.drawable.dv6, R.drawable.dv7, R.drawable.dv8, R.drawable.dv9, R.drawable.dv10, R.drawable.dv11, R.drawable.dv12, R.drawable.dv13, R.drawable.dv14, R.drawable.dv15} ;
    float startX,startY, downX,downY,stopX,stopY,upX,upY,lowY=0;  // 定義手指開始觸摸的坐標
    int jiaodu ,m ,i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tone_exercises);
        imageView6=(ImageView)findViewById(R.id.imageView6);
        word=(TextView)findViewById(R.id.exercise);
        back=(ImageView)findViewById(R.id.tone_exercises_back);
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ToneExercises.this,StructuralLearning.class);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(ToneExercises.this,HandTonePractice.class);
                startActivity(i);
                ToneExercises.this.finish();
            }
        });
        // 初始化一個畫筆，筆觸寬度為5，顏色為紅色
        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(Color.WHITE);

        word = (TextView)findViewById(R.id.question);
        iv_canvas = (ImageView) findViewById(R.id.iv_canvas);
        background = (ImageView) findViewById(R.id.imageView2);
        question_background = (ImageView) findViewById(R.id.question_background);
        btn_resume = (Button) findViewById(R.id.btn_resume);
        btn_sound = (ImageView) findViewById(R.id.btn_sound);
        btn_resume.setOnClickListener(click);
        iv_canvas.setOnTouchListener(touch);

        background.setImageResource(R.drawable.blackboard);
        question_background.setImageResource(R.drawable.frame);
        btn_sound.setImageResource(R.drawable.volume);
        btn_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(speak[i], TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        textToSpeech = new TextToSpeech(ToneExercises.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setPitch(1.0f); // 音調
                    textToSpeech.setSpeechRate((float) 0.5); // 速度
                    textToSpeech.setLanguage(Locale.CHINESE);  //語言
                }
            }
        });
        word.setText(speak[i]);

    }

    private View.OnTouchListener touch = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {


            switch (event.getAction()) {
                // 用戶按下動作
                case MotionEvent.ACTION_DOWN:  // 按下
                    // 第一次繪圖初始化內存圖片，指定背景為白色
                    if (baseBitmap == null) {
                        baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(), iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
                        baseBitmap.eraseColor(Color.TRANSPARENT);
                        canvas = new Canvas(baseBitmap);
                        //  canvas.drawColor(Color.WHITE);

                    }
                    // 記錄開始觸摸的點的坐標
                    // 計算用的
                    downX = event.getX(); // 觸控的 X 軸位置
                    downY = event.getY(); // 觸控的 Y 軸位置
                    // 畫圖用的
                    startX = event.getX(); // 觸控的 X 軸位置
                    startY = event.getY(); // 觸控的 Y 軸位置

                    lowY =0;
                    textToSpeech.speak(speak[i], TextToSpeech.QUEUE_FLUSH, null);
                    return true;
                // 用戶手指在屏幕上移動的動作
                case MotionEvent.ACTION_MOVE:  // 拖曳
                    // 記錄移動位置的點的坐標
                    stopX = event.getX();
                    stopY = event.getY();

                    //根據兩點坐標，繪制連線
                    canvas.drawLine(startX, startY, stopX, stopY, paint);

                    // 更新開始點的位置
                    startX = event.getX();
                    startY = event.getY();

                    // 把圖片展示到ImageView中
                    iv_canvas.setImageBitmap(baseBitmap);

                    if(stopY>downY && stopY>lowY){lowY=stopY;}
                    break;
                case MotionEvent.ACTION_UP: // 放開
                    Log.d("onTouchEvent-ACTION_UP","UP");
                    //showDialog("輕聲");
                    upX = event.getX();
                    upY = event.getY();
                    float x = Math.abs(upX-downX);
                    float y = Math.abs(upY-downY);
                    double z = Math.sqrt(x*x+y*y);
                    jiaodu = Math.round((float)(Math.asin(y/z)/Math.PI*180));//角度
                    m = Math.round((upY-downY)/(upX-downX));
                    if (upY < downY && jiaodu>45) {//上
                        Log.d("onTouchEvent-ACTION_UP","角度:"+jiaodu+", 動作:上"+m);
                        if(jiaodu<70){check(2); }
                    }else if(upY > downY && jiaodu>45) {//下
                        Log.d("onTouchEvent-ACTION_UP","角度:"+jiaodu+", 動作:下"+m);
                        if(jiaodu<70 && m >0){check(4);}
                        else if(jiaodu >70){ check(0);}
                    }else if(upX < downX && jiaodu<=45) {//左
                        Log.d("onTouchEvent-ACTION_UP","角度:"+jiaodu+", 動作:左"+m);

                        // 原方向不是向右時，方向轉右
                      /*  if (mDirection != EAST) {
                                                    mNextDirection = WEST;
                                                }*/
                    }else if(upX > downX && jiaodu<=45) {//右
                        Log.d("onTouchEvent-ACTION_UP","角度:"+jiaodu+", 動作:右"+m);
                        // 原方向不是向左時，方向轉右
                        if (jiaodu<10 && (lowY-downY)<20 && (lowY-upY)<20){check(1);}
                        else if ((lowY-downY)>20 && (lowY-upY)>20){check(3);}
                        else if(jiaodu>20  && downY>upY){check(2);}
                        else if(jiaodu>20 && downY<upY){check(4);}
                        // else {check(3);}

                      /*  if (mDirection != WEST) {
                                                    mNextDirection = EAST;
                                                }*/
                    }
                    return true;

                default:
                    break;

            }
            return true;
        }
    };
    private void check(int a){
        if (a == answe_int[i]){
            showDialog("Correct");
        }else{
            showDialog("Wrong");
        }
    }
    private void showDialog(String ans) {
        if(ans.equals("Correct")){
            new android.support.v7.app.AlertDialog.Builder(this)
                    .setMessage(ans)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            if (baseBitmap != null) {  //清空
                                baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(), iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
                                baseBitmap.eraseColor(Color.TRANSPARENT);
                                canvas = new Canvas(baseBitmap);
                                // canvas.drawColor(Color.WHITE);
                                iv_canvas.setImageBitmap(baseBitmap);
                                //Toast.makeText(MainActivity.this, "清除畫板成功，可以重新開始繪圖", 0).show();
                            }
                           ;
                        }
                    })
                    .show();}else{
            new android.support.v7.app.AlertDialog.Builder(this)
                    .setMessage(ans)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            if (baseBitmap != null) {  //清空
                                baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(), iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
                                baseBitmap.eraseColor(Color.TRANSPARENT);
                                canvas = new Canvas(baseBitmap);
                                iv_canvas.setImageBitmap(baseBitmap);

                            }
                        }
                    }).show();}

    }
    private View.OnClickListener click = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_resume:
                    resumeCanvas();
                    break;
                default:
                    break;
            }
        }
    };


    /**
     * 清除畫板
     */
    protected void resumeCanvas() {
        // 手動清除畫板的繪圖，重新創建一個畫板
        if (baseBitmap != null) {
            baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(), iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
            baseBitmap.eraseColor(Color.TRANSPARENT);
            canvas = new Canvas(baseBitmap);
            // canvas.drawColor(Color.WHITE);
           iv_canvas.setImageBitmap(baseBitmap);
            Toast.makeText(ToneExercises.this, "清除畫板成功，可以重新開始繪圖",Toast.LENGTH_SHORT).show();
        }
    }
}
