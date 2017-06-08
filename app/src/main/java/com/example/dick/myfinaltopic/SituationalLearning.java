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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;

import andtinder.model.CardModel;
import andtinder.view.CardContainer;
import andtinder.view.SimpleCardStackAdapter;
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
    TextView textView;
    ListView listView;;
    //教材圖示
    ImageView note=null;
    RelativeLayout frame,linearLayout_main;
    int pt;
    Drawable drawable;
    boolean aBoolean=false;
    LayoutInflater inflater;
    View viewNpc;
    CTextView cTextView1;
    RelativeLayout.LayoutParams rejj;
    int count=0,pos;
    Button button;
    ArrayAdapter<String> listAdapter;
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
        button=(Button)findViewById(R.id.button2);
        frame=(RelativeLayout)findViewById(R.id.abc);
        linearLayout_main=(RelativeLayout)findViewById(R.id.activity_situation_word_card);
        textView=(TextView)findViewById(R.id.textView2);

        textView.setText(TextBook.courseLocation[pt]);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(SituationalLearning.this,PracticeSelection.class);
                startActivity(intent);
            }
        });

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
            pos=position;
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
            listAdapter=new ArrayAdapter<String>(this,R.layout.listview_item,TextBook.list);
            rejj.setMargins(0,400,0,0);
            viewNpc.setLayoutParams(rejj);
            linearLayout_main.addView(viewNpc);
            viewNpc.setOnClickListener(npcMess);
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

            if (count==0){
                cTextView1.setText(TextBook.book[pos], AnimationUtils.loadAnimation(SituationalLearning.this, R.anim.myanim),50);
                cTextView1.removeAllViews();
                count++;
            }else if(count==1){
                listView.setAdapter(listAdapter);
                listView.setOnItemClickListener(onItemClickListener);
            }
        }
    };
    AdapterView.OnItemClickListener onItemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(), "你選擇的是" + TextBook.list[position], Toast.LENGTH_SHORT).show();
            linearLayout_main.removeView(viewNpc);
            showNoteCard(position);
        }
    };

    void showNoteCard(int pt){

        CardContainer mCardContainer=(CardContainer)findViewById(R.id.laa);

        final Resources r = getResources();

        SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(this);

        for (int i = 0; i < 2; i++) {
            String uri = TextBook.Cards[i][14];
            int imageResource = getResources().getIdentifier(uri, "drawable", getPackageName());
            adapter.add(new CardModel(
                    TextBook.Cards[i][3],
                    TextBook.Cards[i][4],
                    TextBook.Cards[i][5],
                    TextBook.Cards[i][6],
                    TextBook.Cards[i][7],
                    TextBook.Cards[i][8],
                    TextBook.Cards[i][9],
                    TextBook.Cards[i][10],
                    TextBook.Cards[i][11],
                    TextBook.Cards[i][12],
                    TextBook.Cards[i][13],
                    r.getDrawable(imageResource)));
        }
        mCardContainer.setAdapter(adapter);
    }

    }