package com.gyg.learning.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.gyg.learning.R;

/**
 * Created by gyg on 2017/10/26.
 */
public class TickView extends View {

    private Context mContext;

    private boolean isCheck = false;//是否被选中

    private int checkBaseColor;//选中时基本颜色
    private int unCheckBaseColor;//未选中时基本颜色
    private int checkTickColor;//选中时对勾颜色
    private int radius;//圆半径
    private int rate;
    private TickRateEnum mTickRateEnum;//进度单位

    private int scaleRange;//圆放大的最大范围
    private int tickRadius;//对勾的半径
    private int tickOffset;//对勾的偏移

    private int circleX;//圆心x
    private int circleY;//圆心y

    private RectF mRectF = new RectF();//圆外切的矩形

    private int ringCounter = 0;//选中时画圆环计数器
    private int circleCounter = 0;//选中时白色计数器
    private int alphaCounter = 0;//对勾透明度计数器
    private int scaleCounter =45;//外圆缩放计数器

    private float[] mPoints = new float[8];//记录对勾路径的三个点坐标

    private Paint ringPaint;//圆环画笔
    private Paint circlePaint;//圆画笔
    private Paint tickPaint;//对勾画笔

    public TickView(Context context) {
        this(context, null);
    }

    public TickView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttr(attrs, defStyleAttr);
        init();
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setChecked(!isCheck);
            }
        });
    }

    private void initAttr(AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.TickView, defStyleAttr, 0);

        checkBaseColor = ta.getColor(R.styleable.TickView_checkBaseColor, getResources().getColor(R.color.tick_yellow));

        unCheckBaseColor = ta.getColor(R.styleable.TickView_unCheckBaseColor, getResources().getColor(R.color.tick_gray));

        checkTickColor = ta.getColor(R.styleable.TickView_checkTickColor, getResources().getColor(R.color.tick_white));
        radius = ta.getDimensionPixelSize(R.styleable.TickView_radius, dp2px(30f));
        rate= ta.getInt(R.styleable.TickView_rate,1);
        mTickRateEnum=TickRateEnum.getTickRate(rate);

        ta.recycle();

        scaleRange = dp2px(30f);
        tickRadius = dp2px(12f);
        tickOffset = dp2px(4f);
}

    private void init() {

        if (ringPaint == null) ringPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ringPaint.setStyle(Paint.Style.STROKE);
        ringPaint.setStrokeCap(Paint.Cap.ROUND);
        ringPaint.setStrokeWidth(dp2px(2.5f));

        if (circlePaint == null) circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStrokeWidth(dp2px(1.0f));

        if (tickPaint == null) tickPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        tickPaint.setStyle(Paint.Style.STROKE);
        tickPaint.setStrokeCap(Paint.Cap.ROUND);
        tickPaint.setStrokeWidth(dp2px(2.5f));
    }

    private int dp2px(float dpValue) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getSize(radius * 2 + scaleRange * 2, widthMeasureSpec);
        int height = getSize(radius * 2 + scaleRange * 2, heightMeasureSpec);
        width = height = Math.max(width, height);
        setMeasuredDimension(width, height);

        circleX = getMeasuredWidth() / 2;
        circleY = getMeasuredHeight() / 2;

        mRectF.set(circleX - radius, circleY - radius, circleX + radius, circleY + radius);

        mPoints[0] = circleY - tickRadius + tickOffset;
        mPoints[1] = circleY;
        mPoints[2] = circleX - tickRadius / 2 + tickOffset;
        mPoints[3] = circleY + tickRadius / 2;
        mPoints[4] = circleX - tickRadius / 2 + tickOffset;
        mPoints[5] = circleY + tickRadius / 2;
        mPoints[6] = circleX + tickRadius * 2 / 4 + tickOffset;
        mPoints[7] = circleY - tickRadius * 2 / 4;
    }

    private int getSize(int defaultValue, int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int value = defaultValue;
        switch (mode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                value = defaultValue;
                break;
            case MeasureSpec.EXACTLY:
                value = size;
                break;
        }
        return value;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isCheck) {//没有被选中
            ringPaint.setStrokeWidth(dp2px(2.5f));
            ringPaint.setColor(unCheckBaseColor);
            canvas.drawArc(mRectF, 90, 360, false, ringPaint);
            canvas.drawLines(mPoints, ringPaint);
            return;
        }
        ringCounter +=mTickRateEnum.getRingCounterUnit();
        if (ringCounter >= 360) {
            ringCounter = 360;
        }
        ringPaint.setColor(checkBaseColor);
        canvas.drawArc(mRectF, 90,ringCounter, false, ringPaint);
        if (ringCounter == 360) {
            circlePaint.setColor(checkBaseColor);
            canvas.drawCircle(circleX, circleY, radius, circlePaint);//画外围的圆

            circleCounter +=mTickRateEnum.getCircleCounterUnit();
            circlePaint.setColor(checkTickColor);
            canvas.drawCircle(circleX, circleY, radius - circleCounter, circlePaint);//画白色圆

            if (circleCounter > radius + 40) {
                alphaCounter += 20;
                if (alphaCounter >= 255) alphaCounter = 255;
                tickPaint.setAlpha(alphaCounter);
                tickPaint.setColor(checkTickColor);
                canvas.drawLines(mPoints, tickPaint);

                scaleCounter -=mTickRateEnum.getScaleCounterUnit();
                if (scaleCounter <= -scaleRange) scaleCounter = -scaleRange;
                float strokeWidth = ringPaint.getStrokeWidth()+(scaleCounter>0?dp2px(1):-dp2px(1));
                ringPaint.setStrokeWidth(strokeWidth);
                ringPaint.setColor(checkBaseColor);
                canvas.drawArc(mRectF,90,360,false,ringPaint);
            }
        }
        if (scaleCounter != -scaleRange) {
            postInvalidate();
        }
    }

    public void setChecked(boolean checked) {
        if (isCheck == checked) return;
        isCheck = checked;
        reset();
    }

    private void reset() {
        ringCounter = 0;
        circleCounter = 0;
        alphaCounter = 0;
        scaleCounter = 45;
        invalidate();
    }
}
