package com.wang.custompaintbasedemo.evaluators;

import android.animation.TypeEvaluator;
import android.graphics.Point;

/**
 * Created by Wongerfeng on 2018/10/12.
 * 抛物运动转换器
 */

public class FallingBallEvaluator implements TypeEvaluator<Point> {

    private Point point = new Point();

    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        /// 控件在x方向的速度是均匀的
        point.x = (int) (startValue.x + fraction * (endValue.x - startValue.x));
        /// y方向是加速的
        if (fraction * 2 <= 1){
            point.y = (int) (startValue.y + fraction * 2 * (endValue.y - startValue.y));
        }else {
            point.y = endValue.y;
        }

        return point;
    }
}