package com.cursor.common.widget.tabgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cursor.common.R;
import com.cursor.common.utils.DisplayUtils;

/**
 * USER: ldx
 * DATE: 2015/6/3
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 * <p>
 * Imitate the tabs of QQ.
 */
public class RoundCornerTabView extends BaseTabView {

    private static final String TAG = "RoundCornerTabView";

    private int mStrokeWidth = DisplayUtils.dip2px(2);    //px

    private int mArcMinorAxis = DisplayUtils.dip2px(15);  //px

    private int mArcMajorAxis = DisplayUtils.dip2px(15);  //px

    private int mTabWidth = DisplayUtils.dip2px(40);      //px

    public int mSelectedColor = 0xffffffff;

    public int mUnSelectedTabColor = 0x00000000;

    private Paint mPaintBackground = new Paint();

    private Paint mPaintWords = new Paint();

    public RoundCornerTabView(Context context) {
        super(context);
        init();
    }

    public RoundCornerTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundCornerTabView);
        mArcMinorAxis = (int) array.getDimension(R.styleable.RoundCornerTabView_minorAxis, mArcMinorAxis);
        mArcMajorAxis = ((int) array.getDimension(R.styleable.RoundCornerTabView_majorAxis, mArcMajorAxis));
        array.recycle();
        init();
    }

    private void init() {
        mPaintBackground.setAntiAlias(true);
        mPaintBackground.setColor(mSelectedColor);
        mPaintBackground.setStrokeWidth(mStrokeWidth);
        mPaintWords.setAntiAlias(true);
        mPaintWords.setColor(mSelectedColor);
        mPaintWords.setTextSize(mTabTitleTextSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTabs(canvas);
    }

    @Override
    protected void onTabSelected() {
    }

    //My code experience is really poor. Please use WRAP_CONTENT
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(
                measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int widthSpec) {
        int result;
        int expectWidth = result = mTabWidth * getTabCount();
        final int mode = MeasureSpec.getMode(widthSpec);
        final int size = MeasureSpec.getSize(widthSpec);
        switch (mode) {
            case MeasureSpec.EXACTLY:
            case MeasureSpec.AT_MOST:
                if (expectWidth > size) {
                    result = size;
                } else {
                    result = expectWidth;
                }
                break;
        }
        return result;
    }

    private int measureHeight(int heightSpec) {
        int result;
        int expectHeight = result = mArcMajorAxis * 2 ;
        final int mode = MeasureSpec.getMode(heightSpec);
        final int size = MeasureSpec.getSize(heightSpec);
        switch (mode) {
            case MeasureSpec.EXACTLY:
            case MeasureSpec.AT_MOST:
                if (expectHeight > size) {
                    result = size;
                } else {
                    result = expectHeight;
                }
                break;
        }
        return result;
    }

    private void drawTabs(Canvas canvas) {
        int endX = mTabWidth * getTabCount();
        int endY = mArcMajorAxis * 2;

        mPaintBackground.setStyle(Paint.Style.STROKE);

        RectF ovalStart = new RectF(0, 0, mArcMinorAxis * 2, endY);
        RectF ovalEnd = new RectF(endX - mArcMinorAxis * 2, 0, endX, endY);
        // draw border
        canvas.drawArc(ovalStart, 90, 180, false, mPaintBackground);
        canvas.drawLine(
                mArcMinorAxis, 0,
                endX - mArcMinorAxis, 0, mPaintBackground);
        canvas.drawLine(
                mArcMinorAxis, endY,
                endX - mArcMinorAxis, endY, mPaintBackground);
        canvas.drawArc(ovalEnd, -90, 180, false, mPaintBackground);

        //draw all words
        int wordStartX = 0;
        int wordPaddingX = (mTabWidth - mTabTitleTextSize) / 2;
        Log.e(TAG,"wordPaddingX"+wordPaddingX);
        int wordPaddingY = (endY - mTabTitleTextSize) / 2;
        Log.e(TAG,"endY"+endY);
        Log.e(TAG,"mTabTitleTextSize"+mTabTitleTextSize);
        for (int i = 0; i < getTabCount(); i++) {
            wordStartX = i * mTabWidth + wordPaddingX;
            canvas.drawText(((TextTab) getTabs().get(i)).getTitle(), wordStartX, wordPaddingY+mTabTitleTextSize, mPaintWords);
            Log.e(TAG,"wordPaddingY"+wordPaddingY);
        }

        //draw selected
        int indexOfSlt = mCurPosSlt;
        mPaintBackground.setStyle(Paint.Style.FILL_AND_STROKE);
        //draw selected background
        if (indexOfSlt == 0) {
            //draw first tab background
            canvas.drawArc(ovalStart, 90, 180, true, mPaintBackground);
            canvas.drawRect(mArcMinorAxis, 0, mTabWidth, endY, mPaintBackground);
        } else if (indexOfSlt == getTabCount() - 1) {
            //draw last tab background
            canvas.drawArc(ovalEnd, -90, 180, true, mPaintBackground);
            canvas.drawRect(endX - mTabWidth, 0,
                    endX - mArcMinorAxis, endY, mPaintBackground);
        } else {
            //draw tab middle
            canvas.drawRect(mTabWidth * indexOfSlt, 0,
                    mTabWidth * (indexOfSlt + 1), endY, mPaintBackground);
        }

        mPaintWords.setColor(0xff000000);

        //draw selected tab title text
        canvas.drawText(((TextTab) getTabs().get(indexOfSlt)).getTitle(),
                mTabWidth * indexOfSlt + wordPaddingX, wordPaddingY+mTabTitleTextSize, mPaintWords);

        mPaintWords.setColor(mSelectedColor);
    }

    @Override
    protected int indexOfTab(MotionEvent event) {
        //This is a busy method. So prefer use binary search
        for (int i = 0; i < getTabCount(); i++) {
            if (event.getX() < (i + 1) * mTabWidth) {
                return i;
            }
        }
        return 0;
    }

    //==============================GETTER AND SETTER==============================

    public int getmStrokeWidth() {
        return mStrokeWidth;//px
    }

    public void setmStrokeWidth(int mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;//px
        init();
        invalidate();
    }

    public int getmSelectedColor() {
        return mSelectedColor;
    }

    public void setmSelectedColor(int mSelectedColor) {
        this.mSelectedColor = mSelectedColor;
        init();
        invalidate();
    }

    public int getmUnSelectedTabColor() {
        return mUnSelectedTabColor;
    }

    public void setmUnSelectedTabColor(int mUnSelectedTabColor) {
        this.mUnSelectedTabColor = mUnSelectedTabColor;
        init();
        invalidate();
    }

    public Tab newTab(String title) {
        return new TextTab(title);
    }

    public ViewPager.OnPageChangeListener getViewPagerListener() {
        return new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                selectTabView(position);
            }
        };
    }


}