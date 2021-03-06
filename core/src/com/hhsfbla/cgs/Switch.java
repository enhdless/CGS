package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Switch extends Obstacle {
	private boolean on;
	private TreeMap<Integer, Animation> offSprite;
	private TreeMap<Integer, Animation> onSprite;
	private Array<SwitchListener> listeners;

	public Switch() {
		this(false);
	}

	public Switch(boolean on) {
		this.on = on;
		listeners = new Array<SwitchListener>();
		setBlocked(false);
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
		updateOrientedSprite();

		for (SwitchListener listener : listeners) {
			listener.onSwitchStateChanged(on);
		}
	}

	public TreeMap<Integer, Animation> getOffSprite() {
		return offSprite;
	}

	public TreeMap<Integer, Animation> getOnSprite() {
		return onSprite;
	}

	public void setOffSprite(TreeMap<Integer, Animation> offSprite) {
		this.offSprite = offSprite;
		updateOrientedSprite();
	}

	public void setOffSprite(final Animation offSprite) {
		setOffSprite(new TreeMap<Integer, Animation>(){{ put(0, offSprite); }});
	}

	public void setOffSprite(TextureRegion offSprite) {
		setOffSprite(new Animation(0, offSprite));
	}

	public void setOnSprite(TreeMap<Integer, Animation> onSprite) {
		this.onSprite = onSprite;
		updateOrientedSprite();
	}

	public void setOnSprite(final Animation onSprite) {
		setOnSprite(new TreeMap<Integer, Animation>(){{ put(0, onSprite); }});
	}

	public void setOnSprite(TextureRegion onSprite) {
		setOnSprite(new Animation(0, onSprite));
	}

	protected void updateOrientedSprite() {
		setSprite(on ? onSprite : offSprite);
	}

	public Array<SwitchListener> getSwitchListeners() {
		return listeners;
	}

	public void addSwitchListener(SwitchListener listener) {
		listeners.add(listener);
	}

	public void removeSwitchListener(SwitchListener listener) {
		listeners.removeValue(listener, true);
	}
}
