package com.example.dick.myfinaltopic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import us.feras.ecogallery.EcoGallery;

/**
 * Created by Dick on 2017/6/3.
 */

public class SituationGalleryAdapter extends BaseAdapter {
    private int screenw, screenh;
    private int selectItem;
    private ImageView[] scene;
    private EcoGallery.LayoutParams paramSelected;
    private EcoGallery.LayoutParams paramUnSelected;
    TextBook textBook;

    public SituationGalleryAdapter(Context mContext, int w1, int h1, int s, int l) {
        this.screenw = w1;
        this.screenh = h1;
        scene = new ImageView[l];
        for (int i = 0; i < l; i++) {
            scene[i] = new ImageView(mContext);
            scene[i].setImageResource(textBook.street_pic[s][i]);
        }
        paramSelected = new EcoGallery.LayoutParams(w1/2, h1/4);
        paramUnSelected = new EcoGallery.LayoutParams(w1/2,h1/4);
    }

    @Override
    public int getCount() {
        if (scene == null)
            return 0;
        return scene.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public void setSelectItem(int selectItem) {

        if (this.selectItem != selectItem) {
            this.selectItem = selectItem;
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (selectItem == position) {
            scene[position].setLayoutParams(paramSelected);
            // imageView.setImageResource(drawable1[position]);
        } else {
            scene[position].setLayoutParams(paramUnSelected);
        }

        return scene[position];
    }
}
