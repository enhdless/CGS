package com.hhsfbla.cgs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class OverworldPlayer extends Player {

	OverworldActor current;
	OverworldActor next;
	
	public OverworldPlayer() {
		super();
	}

	public void setOverworldActor(OverworldActor current) {
		this.current = current;
		this.next = current;
		setX(current.getX());
		setY(current.getY());
	}

	public class OverworldLevelInputListener extends InputListener {
		
		OverworldScreen os;
		
		public OverworldLevelInputListener(OverworldScreen os) {
			this.os = os;
		}
		public void tryConnection(OverworldConnection con) {
			if(con != null) {
				next = con.oa;
				con.sa.restart();
				addAction(con.sa);
			}
		}
		@Override
		public boolean keyDown(InputEvent event, int keycode) {
			if (getActions().size != 0)
				return true;
			current = next;
			if (keycode == Input.Keys.UP) {
				tryConnection(current.u);
				return true;
			} else if (keycode == Input.Keys.DOWN) {
				tryConnection(current.d);
				return true;
			} else if (keycode == Input.Keys.LEFT) {
				tryConnection(current.l);
				return true;
			} else if (keycode == Input.Keys.RIGHT) {
				tryConnection(current.r);
				return true;
			} else if (keycode == Input.Keys.ENTER) {
				System.out.println(current.getLevel());
				os.g.setScreen(new LevelScreen(os.getStage(), new TextureAtlas(), current.getLevel()));
			} else
				setIdle();
			return true;
		}
	}
}
