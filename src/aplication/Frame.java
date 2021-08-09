package aplication;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame {

	public List<Line> lineList = new ArrayList<Line>();
	public List<Rect> rectList = new ArrayList<Rect>();
	public List<Rect> bodyList = new ArrayList<Rect>();

	public List<Shape> toRenderer = new ArrayList<Shape>();

	final int y_padding = 30;
	public int h = 0;
	public int w = 0;

	public Frame(int h, int w) {
		this.h = h;
		this.w = w;
		this.setSize(h, w);
		this.setBounds(10, 10, h, w + 10 + y_padding);
		// this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void reset() {
		if(!toRenderer.isEmpty()) {
			toRenderer.clear();
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0 + y_padding, h, w);
		g.setColor(Color.GREEN);
		g.drawLine(75, 0, 75, 300);
		for(Shape shape: toRenderer) {
			if(shape instanceof Line) {
				Line line = (Line) shape;
				g.setColor(shape.color);
				g.drawLine(line.position.x, line.position.y + y_padding, line.direction.x, line.direction.y + y_padding);
			} else if (shape instanceof Rect) {
				Rect rect = (Rect) shape;
				g.setColor(rect.color);
				g.fillRect(rect.position.x, rect.position.y + y_padding, rect.scale.x, rect.scale.y);
			}
		}
		if (bodyList != null) {
			int count = 0;
			for (Rect rect : bodyList) {
				if(count > 5) {
					break;
				}
				count++;
				g.setColor(rect.color);
				g.fillRect(rect.position.x, rect.position.y + y_padding, rect.scale.x, rect.scale.y);
			}
		}
		// g.drawRect(0, 0, 250, 250);
	}
}
