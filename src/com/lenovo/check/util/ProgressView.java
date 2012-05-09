package com.lenovo.check.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class ProgressView extends View {

	private int emptyNum;
	private int processNum;
	private int fullNum;
	private String percent;

	public ProgressView(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
	}

	public ProgressView(Context paramContext, AttributeSet paramAttributeSet,
			int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
	}

	private static void paintCanvas(Canvas paramCanvas, int paramInt1, int paramInt2,
			int paramInt3) {
		Paint localPaint = new Paint();
		localPaint.setStyle(Paint.Style.FILL);
		localPaint.setAntiAlias(true);
		int i = paramInt3 / 2;
		int j = paramInt1 - i;
		int k = paramInt1 + i;
		int i1 = 0;
		int n = 0;
		int m = 0;
		if (i > 0) {
			int i8 = -45 / i;
			int i9 = 189 / i;
			i1 = 18 / i;
			n = i8;
			m = i9;
		}
		for (int i2 = 0;; i2++) {
			if (i2 >= paramInt2 + 0)
				return;
			int i3 = 66;
			int i4 = j;
			int i5 = 0;
			int i6 = 45;
			while (true){
				if (i4 < paramInt1) {
					localPaint.setColor(Color.rgb(i6, i3, i5));
					paramCanvas.drawPoint(i2, i4, localPaint);
					i6 += n;
					i3 += m;
					i5 += i1;
					i4++;
					continue;
				}
				else{
					m = 0;
					n = 0;
					i1 = 0;
					break;
				}
			}
			localPaint.setColor(Color.rgb(0, 255, 18));
			for (int i7 = paramInt1; i7 <= k; i7++)
				paramCanvas.drawPoint(i2, i7, localPaint);
		}
	}

	public final void drawProgress(int paramInt1, int paramInt2, String paramString) {
		this.emptyNum = 0;
		this.processNum = paramInt1;
		this.fullNum = paramInt2;
		this.percent = paramString;
		invalidate();
	}

	protected void onDraw(Canvas paramCanvas) {
		super.onDraw(paramCanvas);
		paramCanvas.getHeight();
		paramCanvas.getWidth();
		Rect localRect = paramCanvas.getClipBounds();
		int i = localRect.right;
		int j = localRect.bottom;
		Paint localPaint = new Paint();
		localPaint.setStyle(Paint.Style.FILL);
		localPaint.setAntiAlias(true);
		localPaint.setColor(-1);
		paramCanvas.drawLine(0.0F, j, i, j, localPaint);
		if (this.fullNum > 0) {
			if (this.processNum == 0)
				this.processNum = 100;
			float f1 = i * this.fullNum / this.processNum;
			float f2 = 100 * this.fullNum / this.processNum;
			paintCanvas(paramCanvas, j / 2, (int) f1, j - 4);
			localPaint.setTextAlign(Paint.Align.CENTER);
			paramCanvas.drawText(f2 + this.percent, i / 2, 6 + j / 2, localPaint);
		}
	}

}
