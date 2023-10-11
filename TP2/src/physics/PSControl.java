package physics;

import processing.core.PVector;

public class PSControl {
	private float averageAngle;
	private float dispersionAngle;
	private float minVelocity;
	private float maxVelocity;
	private float minlifetime;
	private float maxlifetime;
	private float minradius;
	private float maxradius;
	private float flow;
	private int color;

	public PSControl(float[] velControl, float[] lifetime, float[] radius,float flow, int color) {
		setVelParams(velControl);
		setLifetimeParams(lifetime);
		setRadiusParams(radius);
		setFlow(flow);
		setColor(color);
	}

	public void setFlow(float flow) {
		this.flow = flow;
	}

	public float getFlow() {
		return flow;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getColor() {
		return color;
	}

	public void setVelParams(float[] velControl) {
		averageAngle = velControl[0];
		dispersionAngle = velControl[1];
		minVelocity = velControl[2];
		maxVelocity = velControl[3];
	}

	public void setLifetimeParams(float[] lifetime) {
		minlifetime = lifetime[0];
		maxlifetime = lifetime[1];
	}

	public float getRndLifetime() {
		return getRnd(minlifetime, maxlifetime);
	}

	public float getRndRadius() {
		return getRnd(minradius, maxradius);
	}

	public void setRadiusParams(float[] radius) {
		minradius = radius[0];
		maxradius = radius[1];
	}

	public PVector getRndVel() {
		float angle = getRnd(averageAngle - dispersionAngle/2, averageAngle + dispersionAngle/2);
		PVector v = PVector.fromAngle(angle);
		return v.mult(getRnd(minVelocity, maxVelocity));
	}

	public static float getRnd(float min, float max) {
		return (float)(min + Math.random()*(max - min));
	}
}
