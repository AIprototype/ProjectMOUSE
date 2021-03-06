import camera_classes.FrameObject;
import game_characters.CharacterBaseClass;
import processing.core.PApplet;
import processing.core.PImage;

import static constants.Constants.ENERGY_BOLT_HEIGHT;
import static constants.Constants.ENERGY_BOLT_WIDTH;

public class EnergyBolt {
    protected float w, h, x, y;
    protected float halfWidth, halfHeight;
    protected float vx, vy;
    protected boolean inMotion;
    protected CharacterBaseClass firedBy;
    protected PApplet pApplet;
    protected PImage[] energyBolt;
    protected boolean isFacingRight;
    protected boolean isDeactivated;

    private int imagePosToDisplay;
    float leftBound, rightBound, lowerBound, upperBound;

    public EnergyBolt(PApplet pApplet, PImage[] energyBolt) {
        this.w = ENERGY_BOLT_WIDTH;
        this.h = ENERGY_BOLT_HEIGHT;
        this.x = 0;
        this.y = -h;
        this.halfWidth = w / 2;
        this.halfHeight = h / 2;
        this.vx = 0;
        this.vy = 0;
        this.inMotion = false;
        this.leftBound = 0;
        this.rightBound = 0;
        this.lowerBound = 0;
        this.upperBound = 0;
        this.firedBy = null;
        this.pApplet = pApplet;
        this.energyBolt = energyBolt;
        this.isFacingRight = false;
        this.isDeactivated = false;
    }

    public boolean isDeactivated() {
        return isDeactivated;
    }

    public boolean isInMotion() {
        return inMotion;
    }

    public void fire(CharacterBaseClass firedBy) {
        this.firedBy = firedBy;
        if (!inMotion && !isDeactivated) {
            y = firedBy.getY() - firedBy.getH() / 4; //to adjust from where the shot is coming from
            inMotion = true;
            if (firedBy.isFacingRight()) {
                isFacingRight = true;
                vx = 8;
                x = firedBy.getX() + firedBy.getW() - 35; //shift to right side of player
            } else {
                isFacingRight = false;
                vx = -8;
                x = firedBy.getX();
            }
        }
    }



    void reset() {
        this.x = 0;
        this.y = -h;
        this.vx = 0;
        this.vy = 0;
        this.inMotion = false;
    }

    public void update(FrameObject camera) {
        if (inMotion) {
            x += vx;
            y += vy;
            //Camera boundaries
            //max is used in the conditions where the player isn't in the center of screen
            rightBound = Math.max(camera.getW(), firedBy.getX() + firedBy.getHalfWidth() + camera.getW() / 2);
            leftBound = camera.getX();
            upperBound = camera.getY();
            lowerBound = Math.max(camera.getH(), firedBy.getY() + firedBy.getHalfHeight() + camera.getH() / 2);
            if (x < leftBound || x > rightBound || y < upperBound || y > lowerBound) {
                reset();
                //energy bolt missed all items
                isDeactivated = true;
                System.out.println("Reset !!");
            }
        }
    }

    public void display() {
        if (inMotion) {
            if (isFacingRight) {
                //pApplet.fill(255, 0, 0);
                //pApplet.rect(x, y, w, h);
                pApplet.push();
                pApplet.scale(-1, 1);
                pApplet.image(energyBolt[imagePosToDisplay], -x - firedBy.getW(), y);
                pApplet.pop();
            } else {
                pApplet.image(energyBolt[imagePosToDisplay], x, y);
            }

            if (pApplet.frameCount % 10 == 0) {
                if (imagePosToDisplay == (energyBolt.length - 1)) {
                    imagePosToDisplay = 0;
                } else {
                    ++imagePosToDisplay;
                }
            }
        }
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

    public CharacterBaseClass getFiredBy() {
        return firedBy;
    }
}
