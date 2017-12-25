package com.gyg.learning.customview;

/**
 * Created by gyg on 2017/10/31.
 */

enum  TickRateEnum {

    SLOW(6,4,2),
    NORMAL(12,6,4),
    FAST(20,16,8);


    public static final int RATE_MODE_SLOW=0;//慢速率
    public static final int RATE_MODE_NORMAL=1;//正常速率
    public static final int RATE_MODE_FAST=2;//快速率

    private int ringCounterUnit;//圆环进度增加的单位
    private int circleCounterUnit;//白色圆收缩的进度单位
    private int scaleCounterUnit;//圆放大的进度单位



    TickRateEnum(int ringCounterUnit,int circleCounterUnit,int scaleCounterUnit){
        this.ringCounterUnit=ringCounterUnit;
        this.circleCounterUnit=circleCounterUnit;
        this.scaleCounterUnit=scaleCounterUnit;
    }

    public int getRingCounterUnit() {
        return ringCounterUnit;
    }

    public TickRateEnum setRingCounterUnit(int ringCounterUnit) {
        this.ringCounterUnit = ringCounterUnit;
        return this;
    }

    public int getCircleCounterUnit() {
        return circleCounterUnit;
    }

    public TickRateEnum setCircleCounterUnit(int circleCounterUnit) {
        this.circleCounterUnit = circleCounterUnit;
        return this;
    }

    public int getScaleCounterUnit() {
        return scaleCounterUnit;
    }

    public TickRateEnum setScaleCounterUnit(int scaleCounterUnit) {
        this.scaleCounterUnit = scaleCounterUnit;
        return this;
    }

    public static TickRateEnum getTickRate(int rateMode){
        TickRateEnum rateEnum;
        switch (rateMode){
            case 0:
                rateEnum=TickRateEnum.SLOW;
                break;
            case 1:
                rateEnum=TickRateEnum.NORMAL;
                break;
            case 2:
                rateEnum=TickRateEnum.FAST;
                break;
            default:
                rateEnum=TickRateEnum.NORMAL;
                break;
        }
        return rateEnum;
    }
}
