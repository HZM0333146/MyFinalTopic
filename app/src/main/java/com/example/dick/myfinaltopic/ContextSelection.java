package com.example.dick.myfinaltopic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContextSelection extends AppCompatActivity {
    SimpleAdapter simpleAdapter;
    private LayoutInflater mInflater;
    public List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
    ListView list;
    int[] image=TextBook.themeImage;
    String[] point=TextBook.courseLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_selection);
        list=(ListView)findViewById(R.id.lV);
        for (int i=0;i<image.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("P_Image",image[i]);
            map.put("P_Title",point[i]);
            data.add(map);
        }

        simpleAdapter = new SimpleAdapter(
                ContextSelection.this,
                data,
                R.layout.situation_model,
                new String[]{"P_Title","P_Image"},
                new int[]{R.id.tv_Title, R.id.img_Picture}) {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                if (v == null) {
                    v = mInflater.inflate(R.layout.situation_model, parent, false);
                }
                return v;
            }
        };
        list.setAdapter(simpleAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(ContextSelection.this,SituationalLearning.class);
                Bundle bundle=new Bundle();
                bundle.putInt("position",position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
