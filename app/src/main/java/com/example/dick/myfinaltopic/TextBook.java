package com.example.dick.myfinaltopic;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Dick on 2017/6/3.
 */

public class TextBook {
    public static String[] courseCode=new String[]{"A1","B1"};
    public  static int[] themeImage=new int[]{R.drawable.tp1,R.drawable.tw02};
    public static String[] courseLocation=new String[]{"台北車站","功維敘參觀"};
    public static String[] course_vocabulary=new String[]{"公車站"};
    public static String[] course_spell=new String[]{"Gōngchē zhàn"};
    public static String[] course_en=new String[]{"bus station"};

    public static LatLng[][] lesson_Attractions=new LatLng[][]{
            {new LatLng(25.047787,121.516863),new LatLng(25.047752,121.517062),new LatLng(25.048028,121.51673),new LatLng(25.048102,121.516449)},
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
            {"M00", "0", "0", "", "車票", "", "Chēpiào", "", "", "station", "", "", "", "", "ic_winter"},};
    static String[] book=new String[]{"這是台北車站廣場","這裡是台北火車站的買票口"};
    static String[] list =new String[] {"我要買車票","我要去苗栗","有車位嗎","下一班幾點的車","為什麼會誤點"};

}
