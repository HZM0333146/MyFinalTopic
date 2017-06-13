package com.example.dick.myfinaltopic;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Dick on 2017/6/3.
 */

public class TextBook {
    public  static int[] themeImage=new int[]{R.drawable.tp1,R.drawable.tw02,R.drawable.sw03};
    public static String[] courseLocation=new String[]{"台北車站","功維敘參觀","龍騰斷橋"};

    public static LatLng[][] lesson_Attractions=new LatLng[][]{
            {new LatLng(25.047787,121.516863),new LatLng(25.0476067,121.5169772),new LatLng(25.048028,121.51673),new LatLng(25.048102,121.516449)},
            {new LatLng(24.570020,120.822343),new LatLng(24.569360,120.822909),new LatLng(24.557032,120.815298),new LatLng(24.556190,120.813441)}
    };
    public static int[][] street_pic ={
            {R.drawable.streetview008,R.drawable.streetview005,R.drawable.streetview006,R.drawable.streetview007},
            {R.drawable.streetview001, R.drawable.streetview002, R.drawable.streetview003, R.drawable.streetview004}
    };
    public static float bear = (float)96.35275;
    public static float tilt =  (float)-7.708808;
    public static String Cards[][] = {
            {"M00", "0", "0", " ", "公車站", "", "Gōngchē zhàn", "", "", "bus station", "", "", "", "", "img_bus_station"},
            {"M00", "0", "0", "", "服務", "", "fúwù", "", "", "service", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "搭車", "", "dāchē", "", "", "ride", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "人", "", "rén", "", "", "people", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "醫院", "", "yīyuàn", "", "", "hospital", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "迷路", "", "mílù", "", "", "get lost", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "名字", "", "míngzì", "", "", "first name", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "救護車", "", "jiùhù chē", "", "", "ambulance", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "台中", "", "táizhōng", "", "", "Taichung", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "苗栗", "", "miáolì", "", "", "Miaoli", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "屏東", "", "píng dōng", "", "", "Pingtung", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "止血", "", "zhǐxiě", "", "", "Hemostasis", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "搭乘", "", "dāchéng", "", "", "Take a ride", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "車票", "", "Chēpiào", "", "", "ticket", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "車種", "", "chēzhǒng", "", "", "Vehicles", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "廣播", "", "guǎngbò", "", "", "broadcast", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "售票台", "", "shòupiào tái", "", "", "Ticket desk", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "自強號", "", "zìqiáng hào", "", "", "Self-reliance", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "區間車", "", "qūjiān chē", "", "", "Interval car", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "普悠瑪號", "", "pǔ yōu mǎ hào", "", "", "Pu'erma", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "時間", "", "shíjiān", "", "", "time", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "早上", "", "zǎoshang", "", "", "morning", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "中午", "", "zhōngwǔ", "", "", "noon", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "下午", "", "xiàwǔ", "", "", "afternoon", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "總共", "", "zǒnggòng", "", "", "total", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "元", "", "qián", "", "", "yuan", "", "", "", "", "ic_winter"},
            {"M00", "0", "0", "", "謝謝", "", "xièxiè", "", "", "Thank you", "", "", "", "", "ic_winter"},};




    static String[][][][]dialogue=new String[][][][]{
            {
                {{"這裡是售票口,需要甚麼服務"}},
                {{"搭車","找人","送醫院"}},
                {{"好的請問要去哪裡"},{"請問迷路的人叫什麼名字"},{"我現在叫救護車"}},
                {{"台中","苗栗","屏東"},{"小明","曉華","安妮"},{"可以幫我止血嗎"}},
                {{"請問到台中搭乘甚麼車種","請問到苗栗搭乘甚麼車種","請問到屏東搭乘甚麼車種"},{"廣播：請小明請到售票台","廣播：請曉華請到售票台","廣播：請安妮請到售票台"}},
                {{"自強號","區間車","普悠瑪號"}},
                {{"請問要搭乘幾點的自強號","請問要搭乘幾點的區間車","請問要搭乘幾點的普悠瑪號"}},
                {{"早上10:00","中午 12:00","下午2:00"}},
                {{"總共是255元","此班車會誤點","已無座位"}},
                {{"好的謝謝","沒關系，不用了"}},
                {{"可以前往搭車地點"}},
                {{"好的"}}
            },{
                {{"這裡是搭乘路入口"}},
                {{"往南下","往北上"}}
            }
    };
}
