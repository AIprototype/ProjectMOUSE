package game_characters;

import processing.core.PApplet;
import processing.core.PImage;

import static constants.Constants.*;

//TODO: platform, images, shooting, enemies
abstract public class CharacterBaseClass {
    float w, h, x, y, vx, vy, acc_x, acc_y, speedLimit;
    float friction, bounce, gravity;
    boolean isOnGround;
    float jumpForce;
    float halfWidth, halfHeight;
    String collisionSide;
    PApplet pApplet;

    //Image variables
    int currentFrame;
    boolean facingRight;
    int frameSequence;
    int frameOffset;
    PImage[] mouseSpriteImages;

    CharacterBaseClass(int characterWidth, int characterHeight, PApplet pApplet, PImage[] mouseSpriteImages) {
        this.w = characterWidth;
        this.h = characterHeight;
        this.x = 60;
        this.y = 130;
        this.vx = 0;
        this.vy = 0;
        this.acc_x = 0;
        this.acc_y = 0;
        this.speedLimit = PLAYER_SPEED_LIMIT;
        this.isOnGround = false;
        this.jumpForce = PLAYER_JUMP_FORCE;

        //environment values
        this.friction = PLAYER_FRICTION;
        this.bounce = PLAYER_BOUNCE;
        this.gravity = PLAYER_GRAVITY;
        this.halfWidth = w / 2;
        this.halfHeight = h / 2;

        this.collisionSide = "";
        this.pApplet = pApplet;

        this.mouseSpriteImages = mouseSpriteImages;
        currentFrame = 0;
        facingRight = true;
        frameSequence = 3; //number of frames in each animation sequence
        frameOffset = 0;
    }

    public void update(boolean left, boolean right, boolean up, boolean down, boolean space) {
        if (left && !right && isOnGround) {
            acc_x = -0.2f;
            friction = 1;
            facingRight = false;
        }
        if (right && !left && isOnGround) {
            acc_x = 0.2f;
            friction = 1;
            facingRight = true;
        }
        if (!left && !right) {
            acc_x = 0;
        }

        if (up && !down && isOnGround) {
            vy = jumpForce; //immediate jumping impulse
            isOnGround = false;
            friction = 1;
            //gravity = 0.15f;
        }

        if (down && !up) {
            //acc_y = 0.2f;
            //friction = 1;
        }

        if (!up & !down) {
            acc_y = 0;
        }

        //removing user acceleration needs to bring in a friction
        //friction = 1 means no friction
        if (!left && !right && !up && !down) {
            friction = 0.96f;
            //gravity = 0.3f;
        }

        //Get the velocity
        vx += acc_x;
        vy += acc_y;

        //apply friction
        vx *= friction;

        //apply gravity
        vy += gravity;

        //make sure they are within the speed limits
        if (vx > speedLimit) {
            vx = speedLimit;
        }
        if (vx < -speedLimit) {
            vx = -speedLimit;
        }
        //when gravity makes it fall down, so higher the character stronger the fall
        if (vy > 3 * speedLimit) {
            vy = 3 * speedLimit;
        }
        //no need speed limit when jumping
        if (vy < -speedLimit) {
            //vy = -speedLimit;
        }

        //move the character
        x += vx;
        y += vy;

        checkBoundaries();
        checkPlatforms();
    }

    void checkBoundaries() {
        //left
        if (x < 0) {
            vx *= bounce;
            x = 0;
        }
        //right
        if (x + w > pApplet.width) {
            vx *= bounce;
            x = pApplet.width - w;
        }
        //top
        if (y < 0) {
            vy *= bounce;
            y = 0;
        }
        //bottom
        if (y + h > pApplet.height) {
            if (vy < 1) {
                isOnGround = true; //if the vertical velocity is smaller than 1, then it is on ground
                vy = 0;
            } else {
                vy *= bounce / 2; //smaller bounce for floor
            }
            y = pApplet.height - h;
        }
    }

    void checkPlatforms() {
        if (collisionSide.trim().equalsIgnoreCase("bottom") && vy >= 0) {
            if (vy < 1) {
                isOnGround = true; //standing on platform
                vy = 0; //set velocity in y = 0
            } else {
                vy *= bounce / 2; //small bounce
            }
        } else if (collisionSide.trim().equalsIgnoreCase("top") && vy <= 0) {
            vy = 0;
        } else if (collisionSide.trim().equalsIgnoreCase("right") && vx >= 0) {
            vx = 0;
        } else if (collisionSide.trim().equalsIgnoreCase("left") && vx <= 0) {
            vx = 0;
        }

        if (!collisionSide.trim().equalsIgnoreCase("bottom") && vy > 0) {
            isOnGround = false;
        }
    }

    public void display() {
        pApplet.fill(0, 255, 0, 128);
        pApplet.rect(x, y, w, h);
    }

    public float getVx() {
        return vx;
    }

    public float getVy() {
        return vy;
    }

    public String getCollisionSide() {
        return collisionSide;
    }

    public float getW() {
        return w;
    }

    public float getH() {
        return h;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getHalfWidth() {
        return halfWidth;
    }

    public float getHalfHeight() {
        return halfHeight;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setCollisionSide(String collisionSide) {
        this.collisionSide = collisionSide;
    }
}
