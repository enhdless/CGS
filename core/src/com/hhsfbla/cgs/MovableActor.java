package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class MovableActor extends AnimatedActor {
	private float speed;
	private TreeMap<Integer, Animation> idleSprite;
	private TreeMap<Integer, Animation> moveSprite;
	private TreeMap<Integer, Animation> appearSprite;
	private TreeMap<Integer, Animation> disappearSprite;
	private boolean moving;
	private boolean canMove;
	private Array<AnimatedActor> collisions;
	private boolean canCollide = true;

	public MovableActor() {
		this(DIR_RIGHT);
	}

	public MovableActor(int direction) {
		collisions = new Array<>();
		canMove = true;
		setSize(5/6f, 5/6f);
		setSpeed(1);
		setDirection(direction);
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public TreeMap<Integer, Animation> getIdleSprite() {
		return idleSprite;
	}

	public TreeMap<Integer, Animation> getMoveSprite() {
		return moveSprite;
	}

	public void setIdleSprite(TreeMap<Integer, Animation> idleSprite) {
		this.idleSprite = idleSprite;
		updateOrientedSprite();
	}

	public void setIdleSprite(final Animation sprite) {
		setIdleSprite(new TreeMap<Integer, Animation>(){{ put(0, sprite); }});
	}

	public void setIdleSprite(TextureRegion sprite) {
		setIdleSprite(new Animation(0, sprite));
	}

	public void setMoveSprite(TreeMap<Integer, Animation> moveSprite) {
		this.moveSprite = moveSprite;
		updateOrientedSprite();
	}

	public void setMoveSprite(final Animation sprite) {
		setMoveSprite(new TreeMap<Integer, Animation>(){{ put(0, sprite); }});
	}

	public void setMoveSprite(TextureRegion sprite) {
		setMoveSprite(new Animation(0, sprite));
	}

	public TreeMap<Integer, Animation> getAppearSprite() {
		return appearSprite;
	}

	public void setAppearSprite(TreeMap<Integer, Animation> appearSprite) {
		this.appearSprite = appearSprite;
	}

	public TreeMap<Integer, Animation> getDisappearSprite() {
		return disappearSprite;
	}

	public void setDisappearSprite(
			TreeMap<Integer, Animation> disappearSprite) {
		this.disappearSprite = disappearSprite;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(int direction) {
		moving = true;
		setDirection(direction);
		updateOrientedSprite();
	}

	public void setIdle() {
		moving = false;
		updateOrientedSprite();
	}

	public boolean canMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	protected void updateOrientedSprite() {
		if (getAnimatedAction() != null) return;
		setSprite(moving ? getMoveSprite() : getIdleSprite());
	}

	protected boolean detectCollisions(float dx, float dy) {
		if (getHitbox() == null || !canCollide)
			return false;

		final Hitbox newBounds = new Hitbox(getHitbox()).translate(dx, dy);
		collisions.clear();

		for (AnimatedActor actor : getLevel().getActors()) {
			if (actor != this && actor.getHitbox() != null
					&& actor.getHitbox().overlaps(newBounds)) {
				if (actor instanceof Obstacle && ((Obstacle) actor).isBlocked()) {
					actor.resolveCollision(this);
					return ((Obstacle) actor).isBlocked();
				}

				collisions.add(actor);
			}
		}

		for (AnimatedActor actor : collisions) actor.resolveCollision(this);
		return false;
	}

	@Override
	public void act(float delta) {
		if (moving && canMove && getAnimatedAction() == null) {
			final double r = Math.toRadians(getDirection());
			final float d = speed * Gdx.graphics.getDeltaTime();
			final float dx = d * (float) Math.cos(r);
			final float dy = d * (float) Math.sin(r);
			if (!detectCollisions(dx, dy)) setPosition(getX() + dx, getY() + dy);
		}

		super.act(delta);
	}

	public boolean isCanCollide() {
		return canCollide;
	}

	public void setCanCollide(boolean canCollide) {
		this.canCollide = canCollide;
	}

	public class AppearAction extends AnimatedAction {
		public AppearAction() {
			super(getAppearSprite());
		}
	}

	public class DisappearAction extends AnimatedAction {
		public DisappearAction() {
			super(getDisappearSprite());
		}
	}
}
