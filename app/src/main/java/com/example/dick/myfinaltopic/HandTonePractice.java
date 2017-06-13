package com.example.dick.myfinaltopic;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class HandTonePractice extends AppCompatActivity implements TextToSpeech.OnInitListener {
    ImageView back;
    private SensorManager sensorManager;
    private Vibrator vibrator;//手機的振動
    private EditText txt_content;
    private Button btn_delete, btn_cancle;
    private AlertDialog dialog;
    private Sensor sensor;
    private boolean hasShaked = false;// 判斷是否已經搖晃的標志位
    int number = 5, i_number = 0;
    int answe_int[] = {1, 2, 4, 0, 4, 1, 4, 2, 3, 4, 4, 1, 3, 1, 4};
    String speak[] = {"區", "來", "或", "個", "這", "推", "進", "回", "指", "但", "變", "安", "保", "消", "愛"};
    String DD;
    TextView word;
    TextView xViewA = null;
    TextView yViewA = null;
    TextView zViewA = null;
    TextView answer;
    private TextToSpeech tts;

    private SensorEventListener listener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            // hasShaked = false;
            // TODO Auto-generated method stub
            float x = event.values[0];// x軸方向的重力加速度
            float y = event.values[1];// y軸方向的重力加速度
            float z = event.values[2];// z軸方向的重力加速度
          /*  xViewA.setText("ORIENTATION_X: " + Math.round(x));
            yViewA.setText("ORIENTATION_Y: " + Math.round(y));
            zViewA.setText("ORIENTATION_Z: " + Math.round(z));*/
            int medumValue = 11;                    //   vibrator.vibrate(200);//設置振動的頻率
            if ((Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue) && hasShaked == false) {
                if (event.values[0] <= -1 && event.values[1] >= 8 && event.values[1] >= 12) {
                    //  showDialog("四聲");
                    number = 4;
                    check(number);
                } else if (event.values[0] >= -4 && event.values[1] < 13 && event.values[2] >= 4) {
                    //   showDialog("一聲");
                    number = 1;
                    check(number);
                } else if (event.values[2] > 0 && event.values[0] <= 2 && event.values[1] > 9) {
                    //  showDialog("輕聲");
                    number = 0;
                    check(number);
                } else if (event.values[2] > 2 && event.values[0] > 3 && event.values[1] >= 9) {
                    //  showDialog("二聲");
                    number = 2;
                    check(number);
                } else {
                    // showDialog("三聲");
                    number = 3;
                    check(number);
                }

                hasShaked = true;

            }

            /*


            //這裡設置的一個阈值為18，經測試比較滿足一般的搖晃，也可以自己按需定義修改
       /*     int medumValue = 18;
            if ((Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue) && hasShaked == false) {
                if ((!(txt_content.getText().toString().equals(""))) && hasShaked == false) {
                 //   vibrator.vibrate(200);//設置振動的頻率
                    showDialog();
                    hasShaked = true;
                }
            }*/
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub
            //   Log.d("Sensor_test","onAccuracyChanged: " + sensor + ", accuracy: " + accuracy);
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_tone_practice);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        word = (TextView) findViewById(R.id.speak);
        word.setText(speak[0]);
        tts = new TextToSpeech(this, this);
        //想暫停的時候
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            tts.speak(speak[0], TextToSpeech.QUEUE_FLUSH, null);     //發音
        }


        back = (ImageView) findViewById(R.id.handTonePracticeBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(HandTonePractice.this, ToneExercises.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void showDialog(String ans) {
        if(ans.equals("Correct")) {
            new android.support.v7.app.AlertDialog.Builder(this)
                    .setMessage(ans)
                    .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // 左方按鈕方法
                            // myview.setText("點擊左邊按鈕");
                            hasShaked = false;
                            i_number++;
                            if (i_number == speak.length) {
                                i_number = 0;
                            }
                            tts.speak(speak[i_number], TextToSpeech.QUEUE_FLUSH, null);     //發音
                            word.setText(speak[i_number]);
                        }
                    })
                    .show();
        } else{
            new android.support.v7.app.AlertDialog.Builder(this)
                    .setMessage(ans)
                    .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            hasShaked = false;
                            tts.speak(speak[i_number], TextToSpeech.QUEUE_FLUSH, null);     //發音
                        }
                    }).show();}
    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //注冊監聽事件
        if (sensorManager != null) {
            sensorManager.registerListener(listener, sensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    private void check(int a) {
        if (a == answe_int[i_number]) {
            showDialog("Correct");
        }else showDialog("Wrong");
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        //取消監聽
        if (sensorManager != null) {
            sensorManager.unregisterListener(listener);
        }
    }

    @Override
    public void onInit(int status) {
        // TODO Auto-generated method stub
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.TAIWAN);    //設定語言
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                tts.setPitch((float) 1);    //語調(1為正常語調；0.5比正常語調低一倍；2比正常語調高一倍)
                tts.setSpeechRate((float) 0.5);    //速度(1為正常速度；0.5比正常速度慢一倍；2比正常速度快一倍)
            }
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // shutdown tts
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}
