package game_characters;

import camera_classes.CameraHandlerClass;
import platform.PlatformBaseClass;
import processing.core.PApplet;
import processing.core.PImage;

public class SmartZombieCharacter extends ZombieMouseCharacter {
    boolean isInjured;
    public SmartZombieCharacter(PlatformBaseClass platform, int characterWidth, int characterHeight, PApplet pApplet, PImage[] mouseSpriteImages, PImage[] walkingRightSprites, PImage[] walkingLeftSprites, PImage[] enemyDeathLeftSprites, PImage[] enemyDeathRightSprites, CameraHandlerClass cameraHandlerClass) {
        super(platform, characterWidth, characterHeight, pApplet, mouseSpriteImages, walkingRightSprites, walkingLeftSprites, enemyDeathLeftSprites, enemyDeathRightSprites, cameraHandlerClass);
    }

    @Override
    public void display() {
        if (!isDead) {
            if (facingRight) {
                pApplet.image(walkingRightSprites[walk_right_current_pos], x, y + 5);
            } else {
                pApplet.image(walkingLeftSprites[walk_left_current_pos], x, y + 5);
            }
        }

        //updating walking anim
        if (pApplet.frameCount % right_walk_anim_speed == 0) {
            if (walk_right_current_pos == walkingRightSprites.length - 1) {
                walk_right_current_pos = 0;
            } else {
                ++walk_right_current_pos;
            }
        }

        if (pApplet.frameCount % left_walk_anim_speed == 0) {
            if (walk_left_current_pos == walkingLeftSprites.length - 1) {
                walk_left_current_pos = 0;
            } else {
                ++walk_left_current_pos;
            }
        }
    }

    @Override
    public void deathAnimation() {
        if(!isInjured) {
            isInjured = true;
            right_walk_anim_speed = 16;
            left_walk_anim_speed = 16;
            vx = vx/2;
        } else {
            isDead = true;
        }
    }
}
