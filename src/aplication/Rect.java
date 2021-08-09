package aplication;

import java.awt.Color;

public class Rect extends Shape {
	public Vector2 scale;

	public Rect(int x, int y, int w, int h, Color color) {
		super();
		this.color = color;
		this.position = new Vector2(x,y);
		this.scale = new Vector2(w,h);
	}
	
	public Rect(Vector2 position, Vector2 scale, Color color) {
		super();
		this.color = color;
		this.position = position;
		this.scale = scale;
	}

	public Rect(int y, Color color) {
		this.position.y = y;
		this.color = color;
	}

}