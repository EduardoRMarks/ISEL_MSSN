package boids;

public class DNA {
	public float maxSpeed;
	public float maxForce;
	public float visionDistance;
	public float visionSafeDistance;
	public float visionAngle;
	public float deltaTPursuit;
	public float radiusArrive;
	public float deltaTWander;
	public float radiusWander;
	public float deltaPhiWander;

	public DNA(){
		//Physics
		maxSpeed = random(3, 5);
		maxForce = random(4, 7);
		//Vision
		visionDistance = random(2, 2);
		visionSafeDistance = 0.25f * visionDistance;
		visionAngle = (float)Math.PI* 0.8f;
		//Pursuit
		deltaTPursuit = random(0.5f, 1f);
		//Arrive
		radiusArrive = random(3, 5);
		//Wander
		deltaTWander = random(0.5f, 0.7f);
		radiusWander = random(1, 3);
		deltaPhiWander = (float) (Math.PI/4);
	}

	public float random(float min, float max){
		return (float)(min + (max-min)*Math.random());
	}
}
