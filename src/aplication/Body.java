package aplication;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

import runner.aplication.MainGame;

public class Body {

	public Vector2 position;
	public Vector2 scale;
	public Color color;
	public int speed = 1;

	private Vector2[] directions = new Vector2[2];

	public Body(Vector2 position, int scale, Color color, int speed) {
		this.position = position;
		this.scale = new Vector2(scale, scale);
		this.color = color;
		if (speed > 0) {
			this.speed = speed;
		}
		init();
	}

	public Body() {
		Random random = new Random();
		int y = random.nextInt(MainGame.SLOT_SIZE - MainGame.BODY_SIZE);

		int r = (int) (Math.random() * 256 - 0);
		int g = (int) (Math.random() * 256 - 0);
		int b = (int) (Math.random() * 256 - 0);
		this.position = new Vector2(50, y);
		this.scale = new Vector2(MainGame.BODY_SIZE, MainGame.BODY_SIZE);
		this.color = new Color(r, g, b);
		init();
	}

	public void init() {
		directions[0] = Vector2.UP;
		directions[1] = Vector2.DOWN;
	}

	public void move(int index) {
		Vector2 direction = directions[index];
		direction.x = direction.x * speed;
		direction.y = direction.y * speed;
		position.sumVector(direction);
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

	public boolean collision(Rectangle b) {
		boolean result = false;
		Rectangle a = this.bounds();
		result = a.intersects(b);
		return result;
	}

	public Rect getRect() {
		return new Rect(position, scale, color);
	}

	public double[] bodyInput(double[] input) {
		input[0] = position.x;
		input[1] = position.y;
		input[2] = scale.x;
		input[3] = scale.y;
		return input;
	}
}
