package com.hhsfbla.cgs;

import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.Animation;

public class Trojan extends Enemy {

	@SuppressWarnings("serial")
	public Trojan() {

		setIdleSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, new Animation(0, Images.get("trojan-up.png")));
			put(DIR_DOWN, new Animation(0, Images.get("trojan-down.png")));
			put(DIR_LEFT, new Animation(0, Images.get("trojan-left.png")));
			put(DIR_RIGHT, new Animation(0, Images.get("trojan-right.png")));
			put(DIR_UP_LEFT, new Animation(0, Images.get("trojan-up-left.png")));
			put(DIR_UP_RIGHT, new Animation(0, Images.get("trojan-up-right.png")));
			put(DIR_DOWN_LEFT, new Animation(0, Images.get("trojan-down-left.png")));
			put(DIR_DOWN_RIGHT, new Animation(0, Images.get("trojan-down-right.png")));
		}});
		setMoveSprite(getIdleSprite());
		
		final Animation leftDie = Images.getAnimation("trojan-left-die-%d.png", 9, 0, 0.03f);
		final Animation rightDie = Images.getAnimation("trojan-right-die-%d.png", 9, 0, 0.03f);
        final Animation downDie = Images.getAnimation("trojan-down-die-%d.png", 9, 0, 0.03f);
        final Animation upDie = Images.getAnimation("trojan-up-die-%d.png", 9, 0, 0.03f);
        final Animation upRightDie = Images.getAnimation("trojan-up-right-die-%d.png", 9, 0, 0.03f);
        final Animation upLeftDie = Images.getAnimation("trojan-up-left-die-%d.png", 9, 0, 0.03f);
        final Animation downLeftDie = Images.getAnimation("trojan-down-left-die-%d.png", 9, 0, 0.03f);
        final Animation downRightDie = Images.getAnimation("trojan-down-right-die-%d.png", 9, 0, 0.03f);
        
		setDyingSprite(new TreeMap<Integer, Animation>() {{
			put(DIR_UP, upDie);
			put(DIR_DOWN, downDie);
			put(DIR_LEFT, leftDie);
			put(DIR_RIGHT, rightDie);
			put(DIR_UP_LEFT, upLeftDie);
			put(DIR_UP_RIGHT, upRightDie);
			put(DIR_DOWN_LEFT, downLeftDie);
			put(DIR_DOWN_RIGHT, downRightDie);
		}});
		
		setHurtSprite(new TreeMap<Integer, Animation>() {{
            put(DIR_UP, new Animation(0.05f, 
                    Images.get("trojan-up copy.png"),
                    Images.get("trojan-up.png")));
            put(DIR_DOWN, new Animation(0.05f, 
                    Images.get("trojan-down copy.png"),
                    Images.get("trojan-down.png")));
            put(DIR_LEFT, new Animation(0.05f, 
                    Images.get("trojan-left copy.png"),
                    Images.get("trojan-left.png")));
            put(DIR_RIGHT, new Animation(0.05f, 
                    Images.get("trojan-right copy.png"),
                    Images.get("trojan-right.png")));
            put(DIR_UP_LEFT, new Animation(0.05f, 
                    Images.get("trojan-up-left copy.png"),
                    Images.get("trojan-up-left.png")));
            put(DIR_UP_RIGHT, new Animation(0.05f, 
                    Images.get("trojan-up-right copy.png"),
                    Images.get("trojan-up-right.png")));
            put(DIR_DOWN_LEFT, new Animation(0.05f, 
                    Images.get("trojan-down-left copy.png"),
                    Images.get("trojan-down-left.png")));
            put(DIR_DOWN_RIGHT, new Animation(0.05f, 
                    Images.get("trojan-down-right copy.png"),
                    Images.get("trojan-down-right.png")));
    }});
	}
}
