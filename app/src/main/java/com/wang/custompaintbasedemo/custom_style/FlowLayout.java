package com.wang.custompaintbasedemo.custom_style;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Wongerfeng on 2019/2/25.
 */

public class FlowLayout extends ViewGroup {
    private static final String TAG = "FlowLayout";
    private int width = 0;
    private int height = 0;



    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    /*三步骤： 测量、布局、绘制 */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeightSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.i(TAG, "onMeasure:  measureWidthSize="+measureWidthSize+"---measureHeightSize="+measureHeightSize);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //累加行宽
        int lineHeight = 0;
        //累加行高
        int lineWidth = 0;

        //Returns the number of children in the group.
        int count = getChildCount();

        for(int i=0; i < count; i++){
            //获取子控件
            View childAt = getChildAt(i);
            //测量子控件尺寸
            measureChild(childAt, widthMeasureSpec,heightMeasureSpec);

            /*计算间距*/
            MarginLayoutParams lp = null;
            if (childAt.getLayoutParams() instanceof MarginLayoutParams){
                lp = (MarginLayoutParams) childAt.getLayoutParams();

            }
            //提取各方向margin ,添加到控件的尺寸中
            //获取测量得到的尺寸
            int childAtMeasuredHeight = childAt.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            int childAtMeasuredWidth = childAt.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            ///获取累加的高度，和最大的宽度
            height += childAtMeasuredHeight;
            width = Math.max(childAtMeasuredWidth, width);
            //需要换行
            if (lineWidth + childAtMeasuredWidth > measureWidthSize){
                width = Math.max(lineWidth, childAtMeasuredWidth);
                height += childAtMeasuredHeight;
                //因为要换行，所以要将当前行宽高初始化，房到下一行
                lineHeight = childAtMeasuredHeight;
                lineWidth = childAtMeasuredWidth;

            }else {
                //否则，累加行宽和行高
                lineHeight = Math.max(lineHeight, childAtMeasuredHeight);
                lineWidth += childAtMeasuredWidth;
            }
            //最后一行不会超宽,所以单独处理
            if (i == count - 1){
                height += lineHeight;
                width = Math.max(width, childAtMeasuredWidth);
            }

            Log.i(TAG, "onMeasure: i="+i +";    lp.topMargin="+lp.topMargin+"---lp.bottomMargin="+lp.bottomMargin);
            Log.i(TAG, "onMeasure:i="+i +";   childAtMeasuredWidth="+childAtMeasuredWidth+"---childAtMeasuredHeight="+childAtMeasuredHeight);
        }
        //
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? measureWidthSize : width,
                heightMode == MeasureSpec.EXACTLY ? measureHeightSize : height );

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        ///当前控件的top坐标
        int top = 0;
        //当前控件的left坐标
        int left = 0;
        //累加行宽
        int lineHeight = 0;
        //累加行高
        int lineWidth = 0;

        int count = getChildCount();
        for (int i=0;i < count;i++){
            View child = getChildAt(i);

            //提取间距尺寸
            MarginLayoutParams layoutParams = null;
            if (child.getLayoutParams() instanceof  MarginLayoutParams){
                layoutParams = (MarginLayoutParams) child.getLayoutParams();

            }
            //将间距的尺寸添加到控件在布局中的位置
            int childMeasuredWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int childMeasuredHeight = child.getMeasuredHeight()  + layoutParams.topMargin + layoutParams.bottomMargin ;

            if (childMeasuredWidth + lineWidth > getMeasuredWidth()){
                //需要换行
                top += lineHeight;
                left = 0;
                lineHeight = childMeasuredHeight;
                lineWidth = childMeasuredWidth;

            }else {
                //不换行，获取最大高度
                lineHeight = Math.max(lineHeight, childMeasuredHeight);
                //累加宽度
                lineWidth += childMeasuredWidth;

            }
            //计算child的上下左右坐标
            //left坐标+左边距 是科技的开始位置
            int lc = left + layoutParams.leftMargin;
            int tc = top + layoutParams.topMargin;
            int rc = lc + child.getMeasuredWidth();
            int bc = tc + child.getMeasuredHeight();

            child.layout(lc, tc, rc, bc);
            //left为下一个控件的开始点
            left += childMeasuredWidth;

            Log.i(TAG, "onLayout:i="+i +";   childMeasuredWidth="+childMeasuredWidth+"---childMeasuredHeight="+childMeasuredHeight);
            Log.i(TAG, "onLayout:i="+i +";   lp.topMargin="+layoutParams.topMargin+"---lp.bottomMargin="+layoutParams.bottomMargin);
            //给控件确定布局位置
//            child.layout(0, top, childMeasuredWidth, top + childMeasuredHeight);
//            top += childMeasuredHeight;
        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "onDraw: ");



    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }
}
