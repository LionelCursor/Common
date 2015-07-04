package com.cursor.common.widget.tabgroup;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * USER: ldx
 * DATE: 2015-06-21
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public class UnderlineTabView extends BaseTabView{

    public static final String TAG = "UnderlineTabView";

    public UnderlineTabView(Context context) {
        super(context);
    }

    public UnderlineTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int indexOfTab(MotionEvent event) {
        return 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
