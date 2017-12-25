package com.gyg.learning.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by gyg on 2017/10/31.
 */

public class CusView extends View{

    private Paint mPaint;//画笔

    public CusView(Context context) {
        this(context,null);
    }

    public CusView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint=new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
         mPaint.setColor(Color.BLACK);
         //画圆
        //canvas.drawCircle(100,100,90,mPaint);
        //画弧形
//        RectF mRectF=new RectF(0,0,100,100);
//        canvas.drawRect(mRectF,mPaint);
//        mPaint.setColor(Color.BLACK);
//        canvas.drawArc(mRectF,0,270,true,mPaint);
        //画颜色
        //canvas.drawColor(Color.RED);
        //画线条
//        mPaint.setStrokeWidth(2.5f);
//        canvas.drawLine(0,0,50,50,mPaint);
//        canvas.drawLine(50,0,0,50,mPaint);
        //画椭圆
//        RectF mRectF=new RectF(0,0,200,100);
//        canvas.drawOval(mRectF,mPaint);
        //按照既定点绘制文本内容
//        canvas.drawPosText("蓋亞光",new float[]{
//                10,10,
//                50,50,
//                90,90,
//        },mPaint);
        //画圆角矩形
//        RectF mRectF=new RectF(0,0,100,100);
//        canvas.drawRoundRect(mRectF,10,10,mPaint);
        //Path使用
//        Path path=new Path();
//        path.moveTo(50,50);
//        path.lineTo(100,100);
//        path.lineTo(100,50);
//        path.lineTo(50,100);
//        canvas.drawPath(path,mPaint);
        Path path=new Path();
        path.moveTo(50,50);
        path.lineTo(70,70);
        path.lineTo(90,90);
        canvas.drawTextOnPath("蓋亞光",path,10,10,mPaint);
    }
}
