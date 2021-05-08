package in_game_items;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

import static constants.Constants.COLLECTABLE_HEIGHT;
import static constants.Constants.COLLECTABLE_WIDTH;

public class CollectableHalloweenPumpkin extends InGameItemsBaseClass {
    float instabilityDwnVel_y = 3f;
    float instabilityUpVel_y = -3f;
    boolean isCurrentInstabilityVelDwn = false;
    PImage pumpkinBg;
    PImage pumpkin_light;
    PImage pumpkin_dark;
    PImage pumpkin_no_face;
    boolean isCollected;

    public CollectableHalloweenPumpkin(PApplet pApplet, float x, float y) {
        super(pApplet, x, y, COLLECTABLE_WIDTH, COLLECTABLE_HEIGHT);
        pumpkin_no_face = pApplet.loadImage("pumpkin_no_face.png");
        pumpkin_no_face.resize(COLLECTABLE_WIDTH, COLLECTABLE_HEIGHT);
        pumpkin_light = pApplet.loadImage("pumpkin_light.png");
        pumpkin_light.resize(COLLECTABLE_WIDTH, COLLECTABLE_HEIGHT);
        pumpkin_dark = pApplet.loadImage("pumpkin_dark.png");
        pumpkin_dark.resize(COLLECTABLE_WIDTH, COLLECTABLE_HEIGHT);
        pumpkinBg = pApplet.loadImage("pumpkin_glow.png");
        pumpkinBg.resize(2 * COLLECTABLE_WIDTH, 2 * COLLECTABLE_HEIGHT);
        this.isCollected = false;
    }

    void animateCollectable() {
        if (pApplet.frameCount % 9 == 0) {
            if (isCurrentInstabilityVelDwn) {
                y += instabilityDwnVel_y;
                isCurrentInstabilityVelDwn = false;
            } else {
                y += instabilityUpVel_y;
                isCurrentInstabilityVelDwn = true;
            }
        }
    }

    public void setPumpkinIsCollected(boolean isCollected) {
        this.isCollected = isCollected;
    }

    public boolean isCollected() {
        return isCollected;
    }

    @Override
    public void display() {
        if (!isCollected) {
            animateCollectable();
            pApplet.image(pumpkinBg, x - COLLECTABLE_WIDTH / 2, y - COLLECTABLE_HEIGHT / 2);
            if (pApplet.frameCount % 13 == 0) {
                pApplet.image(pumpkin_dark, x, y);
            } else {
                pApplet.image(pumpkin_light, x, y);
            }
        }
    }
}
