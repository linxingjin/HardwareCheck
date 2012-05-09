package com.lenovo.check.microphone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class MicrophoneView extends View {
	int a = 0;
	int b = 0;
	int c = 0;
	float[] d;
	long[] e;
	private int f = 10;
	private int g = 10;
	private int h = 1 + this.f;
	private float i;
	private Paint j = new Paint();
	private Rect k;

	public MicrophoneView(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		this.j.setStyle(Paint.Style.FILL);
	}

	public MicrophoneView(Context paramContext, AttributeSet paramAttributeSet,
			int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		this.j.setStyle(Paint.Style.FILL);
	}

	protected void onDraw(Canvas paramCanvas) {
		super.onDraw(paramCanvas);
		this.k = paramCanvas.getClipBounds();
		int m = this.k.right;
		int n = this.k.bottom;
		int i1 = m - 2 * this.f;
		int i2 = n - 2 * this.g;
		int i3 = i2 / 2;
		this.c = i1;
		this.j.setAntiAlias(true);
		this.j.setColor(-1);
		this.j.setStyle(Paint.Style.STROKE);
		paramCanvas.drawLine(this.f, this.g, this.f, n - this.g, this.j);
		paramCanvas.drawLine(m - this.f, n - this.g, this.f, n - this.g, this.j);
		this.j.setTextSize(10.0F);
		paramCanvas.drawText("db", this.f / 2, this.g - 1, this.j);
		this.j.setStrokeWidth(1.0F);
		this.j.setColor(-1);
		if (this.d != null) {
			this.h = 1;
			for (int i4 = 0; i4 < this.c; i4++) {
				this.i = (this.d[i4] / 16384.0F);
				paramCanvas.drawLine(this.f + this.h, i2 + this.g - 1 - i3,
						this.f + this.h, i2 + this.g - 1 - i3 + this.i * i3,
						this.j);
				paramCanvas.drawLine(this.f + this.h, i2 + this.g - 1 - i3,
						this.f + this.h, i2 + this.g - 1 - i3 - this.i * i3,
						this.j);
				this.h = (1 + this.h);
			}
		}
	}
}
