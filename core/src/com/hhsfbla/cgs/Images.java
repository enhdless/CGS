package com.hhsfbla.cgs;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Images {
	static HashMap<String, TextureRegion> map = new HashMap<String, TextureRegion>();
	public static TextureRegion get(String s) {
		if (!map.containsKey(s)) {
			map.put(s, new TextureRegion(new Texture(Gdx.files.internal("images/"+s))));
		}
		return map.get(s);
	}
//	static TextureAtlas a = new TextureAtlas("packed/atlas.atlas");
//	public static TextureRegion get(String s) {
//		s = s.substring(0, s.length() - 4);
//		return a.findRegion(s);
//	}

	public static Animation getAnimation(String format, int i, int f, float duration) {
		Array<TextureRegion> keyFrames = new Array<TextureRegion>();
		int inc = (f > i) ? 1 : -1;
		for ( ; i != f; i += inc )
			keyFrames.add(Images.get(String.format(format, i)));
		return new Animation(duration, keyFrames);
	}

	public static Animation getAnimation(String format, int i, int f, float duration,
			PlayMode playMode) {
		Animation a = getAnimation(format, i, f, duration);
		a.setPlayMode(playMode);
		return a;
	}
}
