package com.gyg.learning.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.gyg.learning.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by gyg on 2017/10/26.
 */

public class CusTextView extends View {

    private String cText;//文本内容
    private int cTextColor;//文本颜色
    private int cTextSize;//文本大小

    private Rect cTextBound;//文本区域
    private Paint cPaint;//文本画笔

    public CusTextView(Context context) {
        this(context,null);
    }

    public CusTextView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CusTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta=context.getTheme().obtainStyledAttributes(attrs, R.styleable.CusTextView,defStyleAttr,0);
        int count=ta.getIndexCount();
        for(int i=0;i<count;i++){
            int attr=ta.getIndex(i);
            switch (attr){
                case R.styleable.CusTextView_text:
                    cText=ta.getString(attr);
                    break;
                case R.styleable.CusTextView_textColor:
                    cTextColor=ta.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CusTextView_textSize:
                    cTextSize=ta.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16,getResources().getDisplayMetrics()));
                    break;
            }
        }
        ta.recycle();
        cPaint=new Paint();
        cTextBound=new Rect();
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                cText=randomText();
                postInvalidate();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        cPaint.setTextSize(cTextSize);
        cPaint.getTextBounds(cText,0,cText.length(),cTextBound);

        if(widthMode==MeasureSpec.EXACTLY){
            width=widthSize;
        }else{
            int textWidth=cTextBound.width();
            width=getPaddingLeft()+textWidth+getPaddingRight();
        }

        if(heightMode==MeasureSpec.EXACTLY){
            height=heightSize;
        }else{
            int textHeight=cTextBound.height();
            height=getPaddingTop()+textHeight+getPaddingBottom();
        }

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cPaint.setColor(getResources().getColor(R.color.colorPrimary));
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),cPaint);
        cPaint.setColor(cTextColor);
        canvas.drawText(cText,getWidth()/2-cTextBound.width()/2,getHeight()/2+cTextBound.height()/2,cPaint);
    }

    //生成随机文本
    private String randomText(){
        Random random=new Random();
        Set<Integer> set=new HashSet<>();
        while(set.size()<4){
            int randomInt=random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer strBuffer=new StringBuffer();
        for(int i:set){
            strBuffer.append(""+i);
        }
        return strBuffer.toString();
    }
}
