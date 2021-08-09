package aplication;

import java.awt.Color;

public class Line extends Shape {
	
	public Vector2 direction;
	public Line(int x, int y, int dx, int dy, Color color) {
		super();
		this.color = color;
		this.position = new Vector2(x, y);
		this.direction = new Vector2(dx, dy);
	}
	
	public Line(Vector2 position, Vector2 direction, Color color) {
		super();
		this.color = color;
		this.position = position;
		this.direction = direction;
	}
	
	
}
