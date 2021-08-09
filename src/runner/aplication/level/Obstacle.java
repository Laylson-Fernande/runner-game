package runner.aplication.level;

import java.awt.Color;
import java.awt.Rectangle;

import aplication.Rect;
import aplication.Vector2;

public class Obstacle {

	private Vector2 position;
	private Vector2 scale = new Vector2(100, 100);
	private Vector2 direction;
	private Color color;

	public void update() {
		position.sumVector(direction);
	}

	public Rect getRect() {
		return new Rect(position, scale, color);
	}
	
	public Rectangle bounds() {
		return new Rectangle(position.x, position.y, scale.x, scale.y);
	}
	
	public double[] getTransformArray() {
		double[] result = new double[4];
		result[0] = this.position.x;
		result[1] = this.position.y;
		result[2] = this.scale.x;
		result[3] = this.scale.y;
		return result;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Vector2 getScale() {
		return scale;
	}

	public void setScale(Vector2 scale) {
		this.scale = scale;
	}

}
