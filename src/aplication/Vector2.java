package aplication;

public class Vector2 {
	
	public static final Vector2 UP = new Vector2(0, -1);
	public static final Vector2 DOWN = new Vector2(0, 1);
	public static final Vector2 LEFT = new Vector2(-1, 0);
	public static final Vector2 RIGHT = new Vector2(1, 0);
	public static final Vector2 UP_LEFT = new Vector2(-1, -1);
	public static final Vector2 UP_RIGHT = new Vector2(1, -1);
	public static final Vector2 DOWN_LEFT = new Vector2(-1, 1);
	public static final Vector2 DOWN_RIGHT = new Vector2(1, 1);

	public int x;
	public int y;
	
	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2() {
	}

	public void sumVector(Vector2 vector) {
		this.x = this.x + vector.x;
		this.y = this.y + vector.y;
	}
	
	public Vector2 distance(Vector2 vector) {
		Vector2 result = new Vector2();
		result.x = vector.x - this.x;
		result.y = vector.y - this.y;
		return result;
	}
	
	public double euclideanDistance(Vector2 vector) {
		Vector2 distanceVector = this.distance(vector);
		double x = Math.pow(distanceVector.x, 2);
		double y = Math.pow(distanceVector.y, 2);
		double distance = Math.sqrt(x + y);
		return distance;
	}
}
