package game_characters;

import camera_classes.FrameObject;
import platform.PlatformBaseClass;
import processing.core.PApplet;
import processing.core.PImage;

public class ZombieMouseCharacter extends CharacterBaseClass {
    PlatformBaseClass platformWherePlaced;
    float leftEdge, rightEdge, ground;
    boolean isOnGround;
    PImage[] walkingLeftSprites;
    PImage[] walkingRightSprites;
    PImage[] enemyDeathLeftSprites;
    PImage[] enemyDeathRightSprites;
    int deathAnimationCount = 0;

    int walk_right_current_pos;
    int walk_left_current_pos;
    int right_walk_anim_speed;
    int left_walk_anim_speed;

    public ZombieMouseCharacter(PlatformBaseClass platform, int characterWidth, int characterHeight, PApplet pApplet,
                                PImage[] mouseSpriteImages, PImage[] walkingRightSprites, PImage[] walkingLeftSprites,
                                PImage[] enemyDeathLeftSprites, PImage[] enemyDeathRightSprites) {
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
        isOnGround = false;

        this.walkingLeftSprites = walkingLeftSprites;
        this.walkingRightSprites = walkingRightSprites;
        this.enemyDeathLeftSprites = enemyDeathLeftSprites;
        this.enemyDeathRightSprites = enemyDeathRightSprites;

        this.walk_right_current_pos = 0;
        this.walk_left_current_pos = 0;
        this.right_walk_anim_speed = 8;
        this.left_walk_anim_speed = 8;
    }

    @Override
    public void update(boolean left, boolean right, boolean up, boolean down, FrameObject gameWorld) {
        vy += gravity;
        if (vy > 3 * speedLimit) {
            vy = 3 * speedLimit;
        }
        if (PApplet.abs(vy) < 0.2) {
            vy = 0;
        }

        //move the character, updated method considering camera movements and extended game world
        x = Math.max(0, Math.min(x + vx, gameWorld.getW() - w));
        y = Math.max(0, Math.min(y + vy, gameWorld.getH() - h));

        if (platformWherePlaced.isPlatformDestroyed()) {
            ground = pApplet.height + h;
        } else {
            ground = platformWherePlaced.getY();
        }

//        if(isDead) {
//            ground = pApplet.height + h;
//        }

        checkBoundaries();
    }

    @Override
    public void display() {
        if (!isDead) {
            if (facingRight) {
                pApplet.image(walkingRightSprites[walk_right_current_pos], x, y);
            } else {
                pApplet.image(walkingLeftSprites[walk_left_current_pos], x, y);
            }
        } else if (deathAnimationCount < 3) {
            //slowly incrementing the image to be shown in each frame, 3 because size of death animation is 4
            if (pApplet.frameCount % 9 == 0) {
                System.out.println(deathAnimationCount);
                deathAnimationCount++;
            }
            if (facingRight) {
                pApplet.image(enemyDeathRightSprites[deathAnimationCount], x, y);
            } else {
                pApplet.image(enemyDeathLeftSprites[deathAnimationCount], x, y);
            }
        } else {
            //no need to show dead body, uncomment below lines if dead body has to be shown
//            if (facingRight) {
//                pApplet.image(enemyDeathRightSprites[3], x, y);
//            } else {
//                pApplet.image(enemyDeathLeftSprites[3], x, y);
//            }
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

    public void deathAnimation() {
        vx = 0;
        isDead = true;
        //ground = pApplet.height;
    }

    public void changeZombiePlatform(PlatformBaseClass platform) {
        this.platformWherePlaced = platform;
        x = platformWherePlaced.getX() + platformWherePlaced.getW() / 2 - halfWidth;
        y = platformWherePlaced.getY() - h;

        leftEdge = platformWherePlaced.getX();
        rightEdge = platformWherePlaced.getX() + platform.getW();
        ground = platformWherePlaced.getY();

        vx = (pApplet.random(10) < 5) ? -1 : 1;
        vy = 0;
        acc_x = 0;
        acc_y = 0;
    }

    void checkBoundaries() {
        if (x <= leftEdge) {
            vx *= -1;
            x = leftEdge;
            facingRight = true;
        }

        if (x >= rightEdge - w) {
            vx *= -1;
            x = rightEdge - w;
            facingRight = false;
        }

        if (y >= ground - h) {
            if (vy < 1) {
                vy = 0;
            } else {
                vy *= bounce / 2;
            }
            y = ground - h;
        }
    }
}
