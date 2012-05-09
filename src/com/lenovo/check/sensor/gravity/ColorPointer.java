package com.lenovo.check.sensor.gravity;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class ColorPointer {

	private FloatBuffer oneBuffer;
	private FloatBuffer twoBuffer;
	private FloatBuffer threeBuffer;
	private FloatBuffer fourBuffer;
	private FloatBuffer fiveBuffer;
	private float oneFloat = 0.04F;
	private float twoFloat = 0.9F;
	private float threeFloat = 0.12F;
	private float fourFloat = 0.2F;
	private float[] oneFloats = new float[12];
	private float[] twoFloats = new float[12];
	private float[] threeFloats = new float[24];
	private float[] fourFloats = new float[15];

	public ColorPointer() {
		oneFloats[0] = 0.0F;
		oneFloats[1] = 0.0F;
		oneFloats[2] = this.oneFloat;
		oneFloats[3] = 0.0F;
		oneFloats[4] = this.oneFloat;
		oneFloats[5] = 0.0F;
		oneFloats[6] = 0.0F;
		oneFloats[7] = (-this.oneFloat);
		oneFloats[8] = 0.0F;
		oneFloats[9] = 0.0F;
		oneFloats[10] = 0.0F;
		oneFloats[11] = (-this.oneFloat);
		twoFloats[0] = this.twoFloat;
		twoFloats[1] = 0.0F;
		twoFloats[2] = this.threeFloat;
		twoFloats[3] = this.twoFloat;
		twoFloats[4] = (-this.threeFloat);
		twoFloats[5] = 0.0F;
		twoFloats[6] = this.twoFloat;
		twoFloats[7] = this.threeFloat;
		twoFloats[8] = 0.0F;
		twoFloats[9] = this.twoFloat;
		twoFloats[10] = 0.0F;
		twoFloats[11] = (-this.threeFloat);
		threeFloats[0] = 0.0F;
		threeFloats[1] = 0.0F;
		threeFloats[2] = this.oneFloat;
		threeFloats[3] = this.twoFloat;
		threeFloats[4] = 0.0F;
		threeFloats[5] = this.oneFloat;
		threeFloats[6] = 0.0F;
		threeFloats[7] = this.oneFloat;
		threeFloats[8] = 0.0F;
		threeFloats[9] = this.twoFloat;
		threeFloats[10] = this.oneFloat;
		threeFloats[11] = 0.0F;
		threeFloats[12] = 0.0F;
		threeFloats[13] = 0.0F;
		threeFloats[14] = (-this.oneFloat);
		threeFloats[15] = this.twoFloat;
		threeFloats[16] = 0.0F;
		threeFloats[17] = (-this.oneFloat);
		threeFloats[18] = 0.0F;
		threeFloats[19] = (-this.oneFloat);
		threeFloats[20] = 0.0F;
		threeFloats[21] = this.twoFloat;
		threeFloats[22] = (-this.oneFloat);
		threeFloats[23] = 0.0F;
		fourFloats[0] = (this.twoFloat + this.fourFloat);
		fourFloats[1] = 0.0F;
		fourFloats[2] = 0.0F;
		fourFloats[3] = this.twoFloat;
		fourFloats[4] = 0.0F;
		fourFloats[5] = this.threeFloat;
		fourFloats[6] = this.twoFloat;
		fourFloats[7] = this.threeFloat;
		fourFloats[8] = 0.0F;
		fourFloats[9] = this.twoFloat;
		fourFloats[10] = (-this.threeFloat);
		fourFloats[11] = 0.0F;
		fourFloats[12] = this.twoFloat;
		fourFloats[13] = 0.0F;
		fourFloats[14] = (-this.threeFloat);
		ByteBuffer localByteBuffer1 = ByteBuffer
				.allocateDirect(4 * this.oneFloats.length);
		localByteBuffer1.order(ByteOrder.nativeOrder());
		this.oneBuffer = localByteBuffer1.asFloatBuffer();
		this.oneBuffer.put(this.oneFloats);
		this.oneBuffer.position(0);
		ByteBuffer localByteBuffer2 = ByteBuffer
				.allocateDirect(4 * this.twoFloats.length);
		localByteBuffer2.order(ByteOrder.nativeOrder());
		this.twoBuffer = localByteBuffer2.asFloatBuffer();
		this.twoBuffer.put(this.twoFloats);
		this.twoBuffer.position(0);
		ByteBuffer localByteBuffer3 = ByteBuffer
				.allocateDirect(4 * this.threeFloats.length);
		localByteBuffer3.order(ByteOrder.nativeOrder());
		this.threeBuffer = localByteBuffer3.asFloatBuffer();
		this.threeBuffer.put(this.threeFloats);
		this.threeBuffer.position(0);
		ByteBuffer localByteBuffer4 = ByteBuffer
				.allocateDirect(4 * this.fourFloats.length);
		localByteBuffer4.order(ByteOrder.nativeOrder());
		this.fourBuffer = localByteBuffer4.asFloatBuffer();
		this.fourBuffer.put(this.fourFloats);
		this.fourBuffer.position(0);
		ByteBuffer localByteBuffer5 = ByteBuffer
				.allocateDirect(4 * this.fourFloats.length);
		localByteBuffer5.order(ByteOrder.nativeOrder());
		this.fiveBuffer = localByteBuffer5.asFloatBuffer();
		this.fiveBuffer.put(this.fourFloats);
		this.fiveBuffer.position(0);
	}

	public final void drawPointer(GL10 paramGL10) {
		paramGL10.glFrontFace(GL10.GL_CW);
		paramGL10.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		paramGL10.glVertexPointer(3, 5126, 0, this.oneBuffer);
		paramGL10.glDrawArrays(5, 0, 4);
		paramGL10.glVertexPointer(3, 5126, 0, this.twoBuffer);
		paramGL10.glDrawArrays(5, 0, 4);
		paramGL10.glVertexPointer(3, 5126, 0, this.threeBuffer);
		paramGL10.glDrawArrays(5, 0, 8);
		paramGL10.glVertexPointer(3, 5126, 0, this.fourBuffer);
		paramGL10.glDrawArrays(5, 1, 4);
		paramGL10.glVertexPointer(3, 5126, 0, this.fiveBuffer);
		paramGL10.glDrawArrays(6, 0, 5);
		paramGL10.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
}
