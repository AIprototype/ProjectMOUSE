package in_game_items;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class InGameItemsBaseClass {
    float x, y, w, h;
    PImage img;
    float halfWidth, halfHeight;
    PApplet pApplet;
    boolean hasPlayerCollectedItem;

    public InGameItemsBaseClass(PApplet pApplet, PImage img, float x, float y, float w, float h) {
        this.pApplet = pApplet;
        this.img = img;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.halfWidth = w/2;
        this.halfHeight = h/2;
    }

    public void display() {
        pApplet.fill(0, 0, 255);
        pApplet.rect(x, y, w, h);
    }
}
