package com.example.dick.myfinaltopic;

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

/**
 * Created by Dick on 2017/6/4.
 */
//NPC對畫動畫
public class CTextView extends ViewGroup {
    private Context context;
    public CTextView(Context context) {
        super(context);
        this.context = context;
    }

    public CTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public CTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 对所有子view进行测量，触发所有子view的onMeasure函数
       /* measureChildren(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(widthSize, heightSize);*/
        int measureWidth = measureWidth(0, widthMeasureSpec);
        int measureHeight = measureHeight(0, heightMeasureSpec);
        // 计算自定义的ViewGroup中所有子控件的大小
        // 首先判断params.width的值是多少，有三种情况。
        //
        // 如果是大于零的话，及传递的就是一个具体的值，那么，构造MeasupreSpec的时候可以直接用EXACTLY。
        //
        // 如果为-1的话，就是MatchParent的情况，那么，获得父View的宽度，再用EXACTLY来构造MeasureSpec。
        //
        // 如果为-2的话，就是wrapContent的情况，那么，构造MeasureSpec的话直接用一个负数就可以了。
        // measureChildren(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);

            int widthSpec = 0;
            int heightSpec = 0;
            LayoutParams params = v.getLayoutParams();
            if (params.width > 0) {
                widthSpec = MeasureSpec.makeMeasureSpec(params.width,
                        MeasureSpec.EXACTLY);
            } else if (params.width == -1) {
                widthSpec = MeasureSpec.makeMeasureSpec(measureWidth,
                        MeasureSpec.EXACTLY);
            } else if (params.width == -2) {
                widthSpec = MeasureSpec.makeMeasureSpec(measureWidth,
                        MeasureSpec.AT_MOST);
            }

            if (params.height > 0) {
                heightSpec = MeasureSpec.makeMeasureSpec(params.height,
                        MeasureSpec.EXACTLY);
            } else if (params.height == -1) {
                heightSpec = MeasureSpec.makeMeasureSpec(measureHeight,
                        MeasureSpec.EXACTLY);
            } else if (params.height == -2) {
                heightSpec = MeasureSpec.makeMeasureSpec(measureWidth,
                        MeasureSpec.AT_MOST);
            }
            v.measure(widthSpec, heightSpec);

        }
        // 设置自定义的控件MyLayout的大小
        setMeasuredDimension(measureWidth, measureHeight);
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e("count", "" + changed);
        Log.e("l", "" + l);
        Log.e("t", "" + t);
        Log.e("r", "" + r);
        Log.e("b", "" + b);
        int childLeft = 0;
        int lengthy=0;
        int childCount = getChildCount();
        int measureHeight;
        int measuredWidth;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            // 獲取在onMeasure中計算的視圖尺寸
            measureHeight = childView.getMeasuredHeight();
            measuredWidth = childView.getMeasuredWidth();
            //將他們橫向排列
            childView.layout(childLeft, lengthy, childLeft+measuredWidth, lengthy+measureHeight);
            childLeft += measuredWidth;
            Log.e("childLeft", "" + childLeft);
            Log.e("lengthy", "" + lengthy);
            Log.e("measuredWidth", "" + measuredWidth);
            Log.e("measureHeight", "" + measureHeight);
            if(childLeft>r){
                lengthy+=measureHeight;
                childLeft=0;
            }
        }
    }
    private int measureWidth(int size, int pWidthMeasureSpec) {
        int result = size;
        int widthMode = MeasureSpec.getMode(pWidthMeasureSpec);// 得到模式
        int widthSize = MeasureSpec.getSize(pWidthMeasureSpec);// 得到尺寸

        switch (widthMode) {
            /**
             * mode共有三种情况，取值分别为MeasureSpec.UNSPECIFIED, MeasureSpec.EXACTLY,
             * MeasureSpec.AT_MOST。
             *
             *
             * MeasureSpec.EXACTLY是精确尺寸，
             * 当我们将控件的layout_width或layout_height指定为具体数值时如andorid
             * :layout_width="50dip"，或者为FILL_PARENT是，都是控件大小已经确定的情况，都是精确尺寸。
             *
             *
             * MeasureSpec.AT_MOST是最大尺寸，
             * 当控件的layout_width或layout_height指定为WRAP_CONTENT时
             * ，控件大小一般随着控件的子空间或内容进行变化，此时控件尺寸只要不超过父控件允许的最大尺寸即可
             * 。因此，此时的mode是AT_MOST，size给出了父控件允许的最大尺寸。
             *
             *
             * MeasureSpec.UNSPECIFIED是未指定尺寸，这种情况不多，一般都是父控件是AdapterView，
             * 通过measure方法传入的模式。
             */
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = widthSize;
                break;
        }
        return result;
    }

    private int measureHeight(int size, int pHeightMeasureSpec) {
        int result = size;

        int heightMode = MeasureSpec.getMode(pHeightMeasureSpec);
        int heightSize = MeasureSpec.getSize(pHeightMeasureSpec);

        switch (heightMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = heightSize;
                break;
        }
        return result;
    }
    public void setText(String text, final Animation animation, int duration) {
        int time = 0;
        if(text != null && !text.isEmpty()) {
            char[] characters = text.toCharArray();

            for(char c : characters) {
                final TextView t = new TextView(context);
                //遍历传入的字符串的每个字符，生成一个TextView，并设置它的动画
                t.setText(String.valueOf(c));
                t.setTextSize(28);
                t.setTextColor(ContextCompat.getColor(context, R.color.white));
                Handler h = new Handler();
                //每隔duration时间，播放下一个TextView的动画
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addView(t);
                        t.setAnimation(animation);
                    }
                }, time);

                time += duration;
            }
        }
    }
}

