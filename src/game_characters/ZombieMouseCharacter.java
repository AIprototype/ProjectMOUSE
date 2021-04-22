package game_characters;

import camera_classes.FrameObject;
import platform.PlatformBaseClass;
import processing.core.PApplet;
import processing.core.PImage;

public class ZombieMouseCharacter extends CharacterBaseClass {
    PlatformBaseClass platformWherePlaced;
    float leftEdge, rightEdge, ground;

    public ZombieMouseCharacter(PlatformBaseClass platform, int characterWidth, int characterHeight, PApplet pApplet, PImage[] mouseSpriteImages) {
        super(characterWidth, characterHeight, pApplet, mouseSpriteImages);
        platformWherePlaced = platform;
        x = platformWherePlaced.getX() + platformWherePlaced.getW() / 2 - halfWidth;
        y = platformWherePlaced.getY() - h - 100;

        leftEdge = platformWherePlaced.getX();
        rightEdge = platformWherePlaced.getX() + platform.getW();
        ground = platformWherePlaced.getY();

        vx = (pApplet.random(10) < 5) ? -1 : 1;
        vy = 0;
        acc_x = 0;
        acc_y = 0;
    }

    @Override
    public void update(boolean left, boolean right, boolean up, boolean down, boolean space, FrameObject gameWorld) {
        vy += gravity;
        if(vy > 3 * speedLimit){
            vy = 3 * speedLimit;
        }
        if(PApplet.abs(vy) < 0.2) {
            vy = 0;
        }

        //move the character, updated method considering camera movements and extended game world
        x = Math.max(0, Math.min(x + vx, gameWorld.getW() - w));
        y = Math.max(0, Math.min(y + vy, gameWorld.getH() - h));

        checkBoundaries();
    }


    void checkBoundaries() {
        if(x <= leftEdge) {
            vx *= -1;
            x = leftEdge;
        }

        if(x >= rightEdge - w) {
            vx *= -1;
            x = rightEdge - w;
        }

        if(y >= ground - h) {
            if(vy < 1) {
                vy = 0;
            } else {
                vy *= bounce/2;
            }
            y = ground - h;
        }
    }
}
