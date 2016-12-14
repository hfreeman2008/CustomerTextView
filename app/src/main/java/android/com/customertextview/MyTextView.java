package android.com.customertextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyTextView extends View{

    private final String TAG = "MyTextView";


    private final int update_color_first = 1;
    private final int update_color_second = 2;
    private int color_type = 1;
    private int flash_time = 200;

    private String firstTitle;
    private String secondTitle;
    private int firstColor = Color.RED;
    private int secondColor = Color.RED;
    private float firstTitleSize = 10;
    private float secondTitleSize = 10;
    private float mFirstTextWidth;
    private float mFirstTextHeight;
    private float mSecondTextWidth;
    private float mSecondTextHeight;

    private Drawable backDrawable;
    private TextPaint mTextPaint;

    public MyTextView(Context context) {
        super(context);
        init(null, 0);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MyTextView, defStyle, 0);

        firstTitle = a.getString(R.styleable.MyTextView_firstTitle);
        secondTitle = a.getString(R.styleable.MyTextView_secondTitle);

        firstColor = a.getColor(R.styleable.MyTextView_firstColor, firstColor);
        secondColor = a.getColor(R.styleable.MyTextView_secondColor, secondColor);

        firstTitleSize = a.getDimension(R.styleable.MyTextView_firstTitleSize, firstTitleSize);
        secondTitleSize = a.getDimension(R.styleable.MyTextView_secondTitleSize, secondTitleSize);

        if (a.hasValue(R.styleable.MyTextView_backDrawable)) {
            backDrawable = a.getDrawable(R.styleable.MyTextView_backDrawable);
            backDrawable.setCallback(this);
        }

        a.recycle();

        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        myHandler.sendEmptyMessageDelayed(update_color_second, flash_time);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        // Draw the example drawable on top of the text.
//        if (backDrawable != null) {
//            backDrawable.setBounds(paddingLeft, paddingTop,
//                    paddingLeft + contentWidth, paddingTop + contentHeight);
//            backDrawable.draw(canvas);
//        }

        if(color_type == update_color_first){
            mTextPaint.setColor(firstColor);
        }else if(color_type == update_color_second){
            mTextPaint.setColor(secondColor);
        }

        mTextPaint.setTextSize(firstTitleSize);
        mFirstTextWidth = mTextPaint.measureText(firstTitle);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mFirstTextHeight = fontMetrics.bottom;

        // Draw the text.
        canvas.drawText(firstTitle,
                paddingLeft + (contentWidth - mFirstTextWidth) / 2,
                paddingTop + (contentHeight + mFirstTextHeight) / 2,
                mTextPaint);

        // Draw the second text.
        mTextPaint.setColor(secondColor);
        mTextPaint.setTextSize(secondTitleSize);
        mSecondTextWidth = mTextPaint.measureText(secondTitle);
        mSecondTextHeight = fontMetrics.bottom;
        canvas.drawText(secondTitle,
                paddingLeft + (contentWidth - mSecondTextWidth) / 2,
                paddingTop+(contentHeight + mSecondTextHeight),
                mTextPaint);
    }

    private  final Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case update_color_first:
                    Log.i(TAG,"update_color_first:update_color_first");
                    color_type = update_color_first;
                    invalidate();
                    myHandler.sendEmptyMessageDelayed(update_color_second, flash_time);
                    break;
                case update_color_second:
                    Log.i(TAG,"update_color_second:update_color_second");
                    color_type = update_color_second;
                    invalidate();
                    myHandler.sendEmptyMessageDelayed(update_color_first, flash_time);
                    break;
                default:
                    break;
            }
        }
    };

    public String getFirstTitle() {
        return firstTitle;
    }

    public void setFirstTitle(String firstTitle) {
        this.firstTitle = firstTitle;
    }

    public String getSecondTitle() {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
    }

    public int getFirstColor() {
        return firstColor;
    }

    public void setFirstColor(int firstColor) {
        this.firstColor = firstColor;
    }

    public int getSecondColor() {
        return secondColor;
    }

    public void setSecondColor(int secondColor) {
        this.secondColor = secondColor;
    }

    public float getFirstTitleSize() {
        return firstTitleSize;
    }

    public void setFirstTitleSize(float firstTitleSize) {
        this.firstTitleSize = firstTitleSize;
    }

    public float getSecondTitleSize() {
        return secondTitleSize;
    }

    public void setSecondTitleSize(float secondTitleSize) {
        this.secondTitleSize = secondTitleSize;
    }

    public Drawable getBackDrawable() {
        return backDrawable;
    }

    public void setBackDrawable(Drawable backDrawable) {
        this.backDrawable = backDrawable;
    }
}