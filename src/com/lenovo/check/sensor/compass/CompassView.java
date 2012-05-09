package com.lenovo.check.sensor.compass;

import java.io.InputStream;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.lenovo.check.R;

public class CompassView extends View {
	private Bitmap[] mBitmapArray = new Bitmap[6];
	private Paint mPaint = new Paint();
    InputStream is;
    private Context context;
    private float[] mValues;
    int[] mBitmapWidth = new int[6];
    int[] mBitmapHeight = new int[6];

    public CompassView(Context context) {
        this(context, null);
    }

    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        BitmapFactory.Options opts = new Options();
        opts.inJustDecodeBounds = false;
//        setBitMapArray(context, 0, opts, R.drawable.compass_needle);
//        setBitMapArray(context, 1, opts, R.drawable.caja_brujula);
        setBitMapArray(context, 0, opts, R.drawable.compass_panel);
        setBitMapArray(context, 1, opts, R.drawable.compass_needle);
        setBitMapArray(context, 2, opts, R.drawable.compass_degree);
    }

    /**
     * 设置bitmap数组个下标的值
     *
     * @param index
     * @param opts
     * @param resid
     */
    private void setBitMapArray(Context context, int index,
            BitmapFactory.Options opts, int resid) {
        is = context.getResources().openRawResource(resid);
        mBitmapArray[index] = BitmapFactory.decodeStream(is);
//        mBitmapWidth[index] = mBitmapArray[index].getWidth();
//        mBitmapHeight[index] = mBitmapArray[index].getHeight();
//        mBitmapArray[index + 2] = BitmapFactory.decodeStream(is, null, opts);
//        mBitmapHeight[index + 2] = mBitmapArray[index + 2].getHeight();
//        mBitmapWidth[index + 2] = mBitmapArray[index + 2].getWidth();
        mBitmapWidth[index] = mBitmapArray[index].getWidth();
        mBitmapHeight[index] = mBitmapArray[index].getHeight();
        mBitmapArray[index+3] = BitmapFactory.decodeStream(is, null, opts);
        mBitmapWidth[index+3] = mBitmapArray[index+3].getWidth();
        mBitmapHeight[index+3] = mBitmapArray[index+3].getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = mPaint;
        paint.setAntiAlias(true);

        int w = canvas.getWidth();
        int h = canvas.getHeight();
        int cx = w / 2;
        int cy = h / 2;

//        canvas.translate(cx, cy);
//        drawPictures(canvas, 0);
        int mCurrentOrientation = context.getResources().getConfiguration().orientation;
        if ( mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT ) {
     	   canvas.translate(cx, cy);
     	   drawPictures(canvas,2);
        } else if ( mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE ) {
     	   canvas.translate(cx, cy-20);
     	   drawPictures(canvas,3);
        }

    }

    private void drawPictures(Canvas canvas, int idDelta) {
    	 if (mValues != null) {
//          	Log.d(TAG, "mValues[0] = "+ mValues[0]);
              canvas.rotate(-mValues[0]);
              canvas.drawBitmap(mBitmapArray[0+idDelta], -mBitmapWidth[0+idDelta]/2, -mBitmapHeight[0+idDelta]/2, mPaint);
              canvas.drawBitmap(mBitmapArray[1+idDelta], -mBitmapWidth[1+idDelta]/2, -mBitmapHeight[1+idDelta]/2, mPaint);
              canvas.rotate(360+mValues[0]);
              canvas.drawBitmap(mBitmapArray[2+idDelta], -mBitmapWidth[2+idDelta]/2, -mBitmapHeight[2+idDelta]/2, mPaint);
          }
          else{
       	    canvas.drawBitmap(mBitmapArray[0+idDelta], -mBitmapWidth[0+idDelta]/2, -mBitmapHeight[0+idDelta]/2, mPaint);
              canvas.drawBitmap(mBitmapArray[1+idDelta], -mBitmapWidth[1+idDelta]/2, -mBitmapHeight[1+idDelta]/2, mPaint);
              canvas.drawBitmap(mBitmapArray[2+idDelta], -mBitmapWidth[2+idDelta]/2, -mBitmapHeight[2+idDelta]/2, mPaint);
          }
    }
    public void setValue(float[] value) {
        this.mValues = value;
    }

}
