package com.example.dick.myfinaltopic;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StructuralLearning extends AppCompatActivity {
    // private Button  ;
    private ImageView iv_canvas,back,  btn_resume, btn_check;
    private Bitmap baseBitmap;
    private Canvas canvas;
    private Paint paint;

    TextView question, textView, explan, btn_next;
    String str_answer[] = {"上下結構","上下結構","上下結構","左右結構","左包右結構","上下結構","全包結構","左右結構","左右結構","左右結構"};
    String str_question[] = {"買","客","置","櫃","匱","貴","站","點","回","時","終"};
    String str_explan[] = {"買 : Buy\n罒 : Net\n貝 : Shells as money in ancient times"
            ,"客 : Guest / Customer\n宀 : Roof\n各 : Each"
            ,"置 : Put / Set\\n罒 : Net\\n直 : Straight","櫃 : Cabinet\\n木 : Wood\\n匱 : Lack","匱 : Lack\\n匚 : box\\n貴 : expensive / valuable"
            ,"貴 : expensive / valuable\\n臾 : moment\\n貝 : Shell、Shells as money in ancient times",
            "站 : Stand / Station / Stop\\n立 : Stand\\n占 : Observe","點 : Point\\n黑 : Black\\n占 : Observe","回 : Return","時 : Time","終 : End"};
    String text = " ";

    float startX,startY, downX,downY, stopX,stopY, upX,upY, maxY=0,maxX=0, minY=999,minX=999;  // 定義手指開始觸摸的坐標
    int jiaodu ,m ,i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_structural_learning);
        // 初始化一個畫筆，筆觸寬度為5，顏色為紅色
        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);
        back=(ImageView)findViewById(R.id.chinesecharacterselectionback);
        back.bringToFront();//控制按鍵到最上層
        back.setOnClickListener(onback);
        textView = (TextView) findViewById(R.id.textView);
        question = (TextView) findViewById(R.id.question);
        iv_canvas = (ImageView) findViewById(R.id.iv_canvas);
        btn_check = (ImageView) findViewById(R.id.btn_check);
        btn_check.setOnClickListener(click);
        btn_resume = (ImageView) findViewById(R.id.btn_resume);
        btn_resume.setOnClickListener(click);
        btn_next = (TextView) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(click);
        iv_canvas.setOnTouchListener(touch);
        question.setText(str_question[i]);
        explan.setText(str_explan[i]);
        explan = (TextView) findViewById(R.id.explan);

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


//                    textToSpeech.speak(speak[i], TextToSpeech.QUEUE_FLUSH, null);
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

                    if(stopY>downY && stopY>maxY){maxY=stopY;}
                    else if (downY>maxY){maxY=downY;}
                    if(stopX>downX && stopX>maxX){maxX=stopX;}
                    else if(downX>maxX){maxX=downX;}
                    if(stopY<downY && stopY<minY){minY=stopY;}
                    else if(downY<minY){minY=downY;}
                    if(stopX<downX && stopX<minX){minX=stopX;}
                    else if(downX<minX){minX=downX;}
                    break;

                case MotionEvent.ACTION_UP: // 放開
                    Log.d("onTouchEvent-ACTION_UP","UP");
                    upX = event.getX();
                    upY = event.getY();
                    return true;

                default:
                    break;
            }
            //   textView.setText(" stopX : "+Math.round(stopX)+", stopY "+Math.round(stopY)+", downX : "+Math.round(downX)+", downY :"+Math.round(downY)+", maxX:"+Math.round(maxX)+", maxY:"+Math.round(maxY)+",minX:"+Math.round(minX)+", minY:"+Math.round(minY));
            return true;

        }
    };
    private void check(){

        float x = Math.abs(upX-downX);
        float y = Math.abs(upY-downY);
        double z = Math.sqrt(x*x+y*y);
        jiaodu = Math.round((float)(Math.asin(y/z)/Math.PI*180));//角度
        m = Math.round((upY-downY)/(upX-downX));

        if (minY>=140 && minY<=185 && minX>=230 && minX <=280 && maxY>=275 && maxY<=340 && maxX>=380 && maxX<=430){text = "全包結構";}
        if (upY < downY && jiaodu>45) {//上

            Log.d("onTouchEvent-ACTION_UP","角度:"+jiaodu+", 動作:上"+m);

        }else if(upY > downY && jiaodu>45) {//下

            Log.d("onTouchEvent-ACTION_UP","角度:"+jiaodu+", 動作:下"+m);
            if(jiaodu>=80 && jiaodu<=90 ) {
                if (maxX >= 300 && maxX <= 350) {text = "左右結構";}
                if (minY>=200 && minY<=245) { text = "品字結構";}
                if (maxX >= 360 && maxX <= 400 ) { text = "左中右結構";}
                if (minY>=130 && minY<=170 && minX>=230 && minX <=285 && maxY>=305 && maxY<=350){text = "左包右結構";}
                if(jiaodu>=30 && jiaodu<=55 && (maxX-minX)>5){text = "左下包圍";}
            }
        }else if(upX < downX && jiaodu<=45) {//左

            Log.d("onTouchEvent-ACTION_UP","角度:"+jiaodu+", 動作:左"+m);
            if(jiaodu<=10 && maxY>=170 && maxY<=205){text = "上下結構";}

        }else if(upX > downX && jiaodu<=45) {//右

            Log.d("onTouchEvent-ACTION_UP","角度:"+jiaodu+", 動作:右"+m);
            if(jiaodu<=10 && maxY>=170 && maxY<=205){text = "上下結構";}
            else if(jiaodu<=10 && minY>=210 && minY<=250 && minX>=235 && minX <=285 && maxX>=385 && maxX<=430){text = "內外組合";}
            else if(jiaodu>=30 && jiaodu<=55 && (maxX-minX)>5){text = "左下包圍";}

        }

        if(text.equals( str_answer[i])){showDialog("Correct");
           }else showDialog("Wrong");
    }
    private void showDialog(String ans) {
        if(ans.equals("Correct")){
            new android.support.v7.app.AlertDialog.Builder(this)
                    .setMessage(ans)
                    .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            if (baseBitmap != null) {  //清空
                                baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(), iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
                                baseBitmap.eraseColor(Color.TRANSPARENT);
                                canvas = new Canvas(baseBitmap);
                                // canvas.drawColor(Color.WHITE);
                                iv_canvas.setImageBitmap(baseBitmap);
                                //Toast.makeText(MainActivity.this, "清除畫板成功，可以重新開始繪圖", 0).show();
                            }
                            btn_check.setImageResource(R.drawable.claer);
                            btn_resume.setImageResource(R.drawable.claer);
                            btn_next.setText("Next");
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
                case R.id.btn_check:
                    check();
                    maxY = 0; maxX = 0;
                    minY=999; minX=999;
                    break;
                case R.id.btn_resume:
                    maxY = 0; maxX = 0;
                    minY=999; minX=999;
                    resumeCanvas();
                    break;
                case R.id.btn_next:
                    explan.setText(" ");
                    btn_check.setImageResource(R.drawable.claer);
                    btn_resume.setImageResource(R.drawable.claer);
                    btn_next.setText(" ");
                    if (baseBitmap != null) {  //清空
                        baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(), iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
                        baseBitmap.eraseColor(Color.TRANSPARENT);
                        canvas = new Canvas(baseBitmap);
                        // canvas.drawColor(Color.WHITE);
                        iv_canvas.setImageBitmap(baseBitmap);
                        //Toast.makeText(MainActivity.this, "清除畫板成功，可以重新開始繪圖", 0).show();
                    }
                    if(i<str_question.length-1) {
                        i++;
                        question.setText(str_question[i]);
                        //   textToSpeech.speak(speak[i], TextToSpeech.QUEUE_FLUSH, null);
                    }else{
                        Toast.makeText(StructuralLearning.this, "已是最後一張",Toast.LENGTH_SHORT).show();
                    }
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
            // Toast.makeText(MainActivity.this, "清除畫板成功，可以重新開始繪圖", 0).show();

        }
    }
    private View.OnClickListener onback=new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent i=new Intent();
            i.setClass(StructuralLearning.this,ChineseCharacterSelection.class);
            startActivity(i);
            finish();
        }
    };
}
