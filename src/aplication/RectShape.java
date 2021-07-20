package aplication;

import java.awt.Color;

public class RectShape {
	public Color color;
	public int x1, y1, w, h;
	public RectShape(int x1, int y1, int w, int h,Color color) {
		super();
		this.color = color;
		this.x1 = x1;
		this.y1 = y1;
		this.w = w;
		this.h = h;
	}
	public RectShape(int y1, Color color) {
		this.y1 = y1;
		this.color = color;
	}
	
}