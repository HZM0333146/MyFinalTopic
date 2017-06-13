package com.example.dick.myfinaltopic;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;

import us.feras.ecogallery.EcoGallery;
import us.feras.ecogallery.EcoGalleryAdapterView;

public class SituationalLearning extends AppCompatActivity implements StreetViewPanorama.OnStreetViewPanoramaChangeListener,StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener {
    private LatLng latLng[][]=TextBook.lesson_Attractions;
    private StreetViewPanorama svp;
    DisplayMetrics dm = new DisplayMetrics();
    int screenw, screenh;
    EcoGallery ecoGallery;
    SituationGalleryAdapter adapter;
    Bundle inBundle;
    TextView textView,textView2;
    ListView listView;;
    //教材圖示
    ImageView note=null;
    RelativeLayout frame,linearLayout_main;
    int pt;
    Drawable drawable;
    LayoutInflater inflater;
    View viewNpc;
    CTextView cTextView1;
    RelativeLayout.LayoutParams rejj;
    int count=0,pos,con=0,sel=0;
    int npcOne=0,npcTwo=0,npcThree=0,npcFour=0;
    ArrayAdapter<String> listAdapter;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situational_learning);
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenw = dm.widthPixels;
        screenh = dm.heightPixels;

        if(getIntent().getExtras()!=null){
            inBundle=getIntent().getExtras();
            pt=inBundle.getInt("position");
        }
        frame=(RelativeLayout)findViewById(R.id.abc);
        linearLayout_main=(RelativeLayout)findViewById(R.id.activity_situation_word_card);
        textView=(TextView)findViewById(R.id.textView2);
        textView2=(TextView)findViewById(R.id.textView3);

        back=(ImageView)findViewById(R.id.situationallearningback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(SituationalLearning.this,ContextSelection.class);
                startActivity(i);
                finish();
            }
        });
        textView.setText(TextBook.courseLocation[pt]);
        textView2.setText("卡片");

        //實力化NPC頁面
        inflater = (LayoutInflater) SituationalLearning.this.getSystemService(LAYOUT_INFLATER_SERVICE);


        SupportStreetViewPanoramaFragment streetViewPanoramaFragment =
                (SupportStreetViewPanoramaFragment)
                        getSupportFragmentManager().findFragmentById(R.id.streetviewpanorama);
        Resources res=getResources();
        drawable=res.getDrawable(R.drawable.menu_image);
        //**
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(
                new OnStreetViewPanoramaReadyCallback() {
                    @Override
                    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
                        svp = panorama;
                        svp.setOnStreetViewPanoramaChangeListener(SituationalLearning.this);
                        svp.setOnStreetViewPanoramaCameraChangeListener(SituationalLearning.this);
                        //Sets the preference for whether zoom gestures should be enabled or disabled.
                        svp.setZoomGesturesEnabled(false);
                        // Only set the panorama to SYDNEY on startup (when no panoramas have been只有在啟動時才將全景圖設置為SYDNEY（沒有全景照片）
                        // loaded which is when the savedInstanceState is null).
                        svp.setPosition(latLng[pt][0]);
                        //根據經緯度判斷搜尋教材
                        //mM.selectNotes( latLng[pt][0].latitude, latLng[pt][0].longitude);
                        //顯示教材
                        //showNote(svp.getPanoramaCamera().tilt,svp.getPanoramaCamera().bearing);
                        //顯示教材擺放地點
                        ecoGallery=(EcoGallery)findViewById(R.id.galleryImages1);
                        adapter = new SituationGalleryAdapter(getApplicationContext(), screenw,screenh, pt, TextBook.lesson_Attractions[pt].length);
                        ecoGallery.setAdapter(adapter);
                        ecoGallery.setSpacing((screenw /30));
                        ecoGallery.setOnItemSelectedListener(onItemSelectedListener);
                    }
                });
    }

    @Override
    public void onStreetViewPanoramaChange(StreetViewPanoramaLocation streetViewPanoramaLocation) {
        LatLng ll = streetViewPanoramaLocation.position;
        Log.v("latitude",String.valueOf(ll.latitude));
        Log.v("longitude",String.valueOf(ll.longitude));

    }

    @Override
    public void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera Camera) {
        if (note == null) {
            //showNote(Camera.tilt, Camera.bearing);
        }
    }
    private EcoGallery.OnItemSelectedListener onItemSelectedListener=new EcoGallery.OnItemSelectedListener() {
        @Override
        public void onItemSelected(EcoGalleryAdapterView<?> parent, View view, int position, long id) {
            changePic(position);
            showNPC(TextBook.lesson_Attractions[pt][position].latitude,TextBook.lesson_Attractions[pt][position].longitude,position);

            if(position>npcOne){
                npcOne=position;count=0;npcThree=0;npcFour=0;
            }
        }

        @Override
        public void onNothingSelected(EcoGalleryAdapterView<?> parent) {

        }
    };
    private void changePic(int p) {
        adapter.setSelectItem(p);
        svp.setPosition(TextBook.lesson_Attractions[pt][p]);
    }
    //*
    //顯式教材
   /* private void showNote(float tilt, float bearing) {
        Log.v("bearing",String.valueOf(bearing));
        Log.v("tilt",String.valueOf(tilt));
        //ArrayList<String[]> pList = mM.showNotes(tilt,bearing);
        DecimalFormat df = new DecimalFormat("0");
        if (df.format(TextBook.bear).equals(df.format(bearing))||df.format(TextBook.tilt).equals(df.format(tilt))){
            Log.v("go","10000000000000000000000000000000000000000");
            RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(screenw/2,screenh/2);
            params.setMargins(screenw/3*2,0,0,screenh/3);
            note = new ImageView(getApplicationContext());
            note.setImageDrawable(drawable);
            note.setId(Integer.parseInt("11"));//物件ID
            note.setLayoutParams(params);//大小位置
            frame.addView(note);//新增物件
            note.setOnClickListener(clickNote);//設定聆聽
        }
    }*/
    //顯示NPC
    private void showNPC(Double lat, Double lon,int positionNPC) {
        Log.v("latitude",String.valueOf(lat));
        Log.v("longitude",String.valueOf(lon));
        //ArrayList<String[]> pList = mM.showNotes(tilt,bearing);
        if (TextBook.lesson_Attractions[pt][positionNPC].latitude==lat&& TextBook.lesson_Attractions[pt][positionNPC].longitude==lon) {
            rejj = new RelativeLayout.LayoutParams(screenw, screenh);
            viewNpc=inflater.inflate(R.layout.npcone, null);
            cTextView1=(CTextView) viewNpc.findViewById(R.id.cTextView);
            listView=(ListView)viewNpc.findViewById(R.id.list);
            rejj.setMargins(0,400,0,0);
            viewNpc.setLayoutParams(rejj);
            linearLayout_main.addView(viewNpc);
            cTextView1.setOnClickListener(npcMess);
        }else{
            linearLayout_main.removeView(viewNpc);
        }
    }

    View.OnClickListener clickNote=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent();
            i.setClass(SituationalLearning.this,SituationWordCard.class);
            startActivity(i);
            finish();
        }
    };
    View.OnClickListener npcMess=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.v("ONE",String.valueOf(npcOne));
            Log.v("count",String.valueOf(count));
            Log.v("npcThree",String.valueOf(npcThree));
            Log.v("npcFour",String.valueOf(npcFour));

            if (con==0){
                cTextView1.setText(TextBook.dialogue[npcOne][count][npcThree][npcFour], AnimationUtils.loadAnimation(SituationalLearning.this, R.anim.myanim),50);
                cTextView1.removeAllViews();
                count=count+1;
                con++;
            }else if(con==1){
                listAdapter=new ArrayAdapter<String>(SituationalLearning.this,R.layout.listview_item,TextBook.dialogue[npcOne][count][sel]);
                listView.setAdapter(listAdapter);
                listView.setOnItemClickListener(onItemClickListener);
                count=count+1;
                con=0;
            }
        }
    };
    AdapterView.OnItemClickListener onItemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.e("hhhhhhhhh",String.valueOf(count));
            if(count>=TextBook.dialogue[npcOne].length){
                linearLayout_main.removeView(viewNpc);
            }
            if(count==2){
                npcThree=position;
            }
            else{
                npcFour=position;
            }
            Log.e("AAA",String.valueOf(npcThree));
           // Toast.makeText(getApplicationContext(), "你選擇的是" + TextBook.dialogue[0][2][1][0], Toast.LENGTH_SHORT).show();

            cTextView1.setText(TextBook.dialogue[npcOne][count][npcThree][npcFour], AnimationUtils.loadAnimation(SituationalLearning.this, R.anim.myanim),50);
            cTextView1.removeAllViews();
            count=count+1;
            listAdapter=new ArrayAdapter<String>(SituationalLearning.this,R.layout.listview_item,TextBook.dialogue[npcOne][count][npcThree]);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(onItemClickListener);
            count=count+1;
        }
    };
}