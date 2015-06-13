package com.cursor.common.widget.tabgroup;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * USER: ldx
 * DATE: 2015/6/3
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public class RoundCornerTabView extends BaseTabView{
    
    public int mStrokeColor = 0xffffffff;

    public int mStrokeWidth = 1; //px

    public int mSelectedColor = 0xffffffff;

    public int mUnSelectedTabColor = 0x00000000;


    public RoundCornerTabView(Context context) {
        super(context);
    }

    @Override
    protected int indexOfTab(MotionEvent event) {
        return 0;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

    }


}
