package com.hhsfbla.cgs;

import com.badlogic.gdx.graphics.g2d.Batch;

public class FileStack extends SlottedObstacle {

	public static final int MAX_HEALTH = 200;

	private int health = MAX_HEALTH;
	private HealthBar healthBar = new HealthBar();

	public FileStack() {
		super(1, 9);
		setOriginY(1/3f);
		updateOrientedSprite();
	}

	public void damage(int damage) {
		setHealth(health - damage);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
		updateOrientedSprite();
	}

	protected void updateOrientedSprite() {
		if (health > MAX_HEALTH * 3 / 4) {
			setSprite(Images.get("filestack-4.png"));
		} else if (health > MAX_HEALTH / 2) {
			setSprite(Images.get("filestack-3.png"));
		} else if (health > MAX_HEALTH / 4) {
			setSprite(Images.get("filestack-2.png"));
		} else if (health > 0) {
			setSprite(Images.get("filestack-1.png"));
		} else {
			setSprite(Images.get("filestack-0.png"));
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		healthBar.draw(batch, parentAlpha, getX(), getY(), health, MAX_HEALTH);
	}

}
