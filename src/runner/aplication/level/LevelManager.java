package runner.aplication.level;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aplication.Frame;
import aplication.Vector2;

public class LevelManager {

	private List<Obstacle> obstacles;

	public LevelManager() {
		obstacles = new ArrayList<Obstacle>();
	}
	
	public void reset() {
		obstacles.clear();
	}

	public void update(Frame frame) {
		spawnObstacles();
		updateObstacles();
		destroyObstacle();
		paintObstacles(frame);
	}

	Random random = new Random();
	public int minDistance = 350;
	int spawn_x = 1100;

	public void spawnObstacles() {
		int y = random.nextInt(100);
		Vector2 spawnPosition = new Vector2(spawn_x, y);
		if (!obstacles.isEmpty()) {
			Obstacle lastSpawn = obstacles.get(obstacles.size() - 1);
			if (lastSpawn.getPosition().euclideanDistance(spawnPosition) > minDistance) {
				Obstacle obstacle = new Obstacle();
				obstacle.setPosition(spawnPosition);
				obstacle.setDirection(Vector2.LEFT);
				obstacle.setColor(Color.red);
				obstacles.add(obstacle);
			}
		} else {
			Obstacle obstacle = new Obstacle();
			obstacle.setPosition(spawnPosition);
			obstacle.setDirection(Vector2.LEFT);
			obstacle.setColor(Color.red);
			obstacles.add(obstacle);
		}
	}

	private void updateObstacles() {
		for (Obstacle obstacle : obstacles) {
			obstacle.update();
		}
	}

	private void destroyObstacle() {
		if (!obstacles.isEmpty()) {
			Obstacle obstacle = obstacles.get(0);
			Vector2 position = obstacle.getPosition();
			Vector2 scale = obstacle.getScale();
			if ((position.x + scale.x) < 0) {
				obstacles.remove(0);
			}
		}
	}

	private void paintObstacles(Frame frame) {
		for (Obstacle obstacle : obstacles) {
			frame.toRenderer.add(obstacle.getRect());
		}
	}

	public double[] getInputs(double[] input, int limit) {
		if (obstacles.size() > 3) {
			int index = 0;
			Obstacle obstacle = obstacles.get(index);
			Vector2 position = obstacle.getPosition();
			Vector2 scale = obstacle.getScale();
			if ((position.x + scale.x) < limit) {
				index++;
				obstacle = obstacles.get(index);
				position = obstacle.getPosition();
				scale = obstacle.getScale();
			}

			input[4] = position.x;
			input[5] = position.y;
			input[6] = scale.x;
			input[7] = scale.y;
			
			index++;
			obstacle = obstacles.get(index);
			position = obstacle.getPosition();
			scale = obstacle.getScale();
			
			input[8] = position.x;
			input[9] = position.y;
			input[10] = scale.x;
			input[11] = scale.y;
		}

		return input;
	}

	public List<Obstacle> getObstacles() {
		return obstacles;
	}
}
