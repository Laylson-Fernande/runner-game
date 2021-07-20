package aplication;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import runner.aplication.MainGame;

public class Frame extends JFrame {

	public List<LineShape> lineList = new ArrayList<LineShape>();
	public List<RectShape> rectList = new ArrayList<RectShape>();
	public List<RectShape> bodyList = new ArrayList<RectShape>();

	final int y_padding = 30;
	int h = 0;
	int w = 0;
	
	public Frame(int h, int w) {
		this.h = h;
		this.w = w;
		this.setSize(h, w);
		this.setBounds(10, 10, h, w + 10 + y_padding);
		//this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void paint(Graphics g) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0 + y_padding, h, w);
		int lines = 0;
		for (LineShape line : lineList) {
			lines++;
			g.setColor(line.color);
			g.drawLine(line.x1, line.y1 + y_padding, line.x2, line.y2  + y_padding);
		}
		
		int rects = 0;
		for (RectShape rect : rectList) {
			rects++;
			g.setColor(rect.color);
			g.fillRect(rect.x1, rect.y1 + y_padding, rect.w, rect.h);
		}
		int bodys = 0;
		Random random = new Random();
		if(bodyList != null) {
			for (RectShape rect : bodyList) {
				bodys++;
				rect = prepareRectBody(rect, random);
				g.setColor(rect.color);
				g.fillRect(rect.x1, rect.y1 + y_padding, rect.w, rect.h);
			}
		}
		// g.drawRect(0, 0, 250, 250);
	}
	
	public RectShape prepareRectBody(RectShape rect, Random random) {
		int x = random.nextInt(MainGame.SLOT_SIZE - MainGame.BODY_SIZE);
		int y = random.nextInt(MainGame.SLOT_SIZE - MainGame.BODY_SIZE);
		y = y + (rect.y1 * MainGame.SLOT_SIZE);
		
		rect = new RectShape(x, y, MainGame.BODY_SIZE, MainGame.BODY_SIZE, rect.color);
		return rect;
	}
}
