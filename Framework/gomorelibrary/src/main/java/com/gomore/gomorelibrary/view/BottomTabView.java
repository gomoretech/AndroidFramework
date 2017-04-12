package com.gomore.gomorelibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.gomore.gomorelibrary.R;


/**
 * 自定义底部导航栏View
 */

public class BottomTabView extends View {
    private int color;
    private Bitmap icon;
    private String text;
    private int textSize;
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Paint mPaint;
    private float mAlpha;
    private Rect mIconRect;
    private Rect mTextRect;
    private Paint mTextPaint;

    public BottomTabView(Context context) {
        this(context, null);
    }

    public BottomTabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomTabView);
        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int index = typedArray.getIndex(i);
            //在Android library中不能使用switch-case语句访问资源ID,因为此Id不是使用final修饰，故用if()else{}判断
            if (index == R.styleable.BottomTabView_bottom_icon) {
                BitmapDrawable drawable = (BitmapDrawable) typedArray.getDrawable(index);
                icon = drawable.getBitmap();
            } else if (index == R.styleable.BottomTabView_bottom_color) {
                color = typedArray.getColor(index, 0xFF303135);
            } else if (index == R.styleable.BottomTabView_bottom_text) {
                text = typedArray.getString(index);
            } else if (index == R.styleable.BottomTabView_bottom_text_size) {
                textSize = (int) typedArray.getDimension(index, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, getResources().getDisplayMetrics()));
            }
        }
        typedArray.recycle();
        mTextRect = new Rect();
        mTextPaint = new Paint();
        mTextPaint.setTextSize(textSize);
        mTextPaint.setAntiAlias(true);
        mTextPaint.getTextBounds(text, 0, text.length(), mTextRect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - mTextRect.height());
        int left = getMeasuredWidth() / 2 - iconWidth / 2;
        int top = (getMeasuredHeight() - mTextRect.height()) / 2 - iconWidth / 2;
        mIconRect = new Rect(left, top, left + iconWidth, top + iconWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(icon, null, mIconRect, null);
        int alpha = (int) Math.ceil(255 * mAlpha);
        setupTargetBitmap(alpha);
        drawSourceText(canvas, alpha);
        drawTargetText(canvas, alpha);
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    private void drawTargetText(Canvas canvas, int alpha) {
        mTextPaint.setColor(color);
        mTextPaint.setAlpha(alpha);
        int x = getMeasuredWidth() / 2 - mTextRect.width() / 2;
        int y = mIconRect.bottom + mTextRect.height();
        canvas.drawText(text, x, y, mTextPaint);
    }

    private void drawSourceText(Canvas canvas, int alpha) {
        mTextPaint.setColor(0xFF999999);
        mTextPaint.setAlpha(255 - alpha);
        int x = getMeasuredWidth() / 2 - mTextRect.width() / 2;
        int y = mIconRect.bottom + mTextRect.height();
        canvas.drawText(text, x, y, mTextPaint);
    }

    private void setupTargetBitmap(int alpha) {
        mBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setAlpha(alpha);
        mCanvas.drawRect(mIconRect, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAlpha(255);
        mCanvas.drawBitmap(icon, null, mIconRect, mPaint);
    }

    public void setIconAlpha(float alpha) {
        this.mAlpha = alpha;
        invalidateView();
    }

    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }
}
