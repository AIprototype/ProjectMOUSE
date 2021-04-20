package camera_classes;

import processing.core.PApplet;
import processing.core.PImage;

public class ImageObject {
    float w, h, x, y;
    PImage img;
    float halfWidth, halfHeight;
    PApplet pApplet;

    public ImageObject(PApplet pApplet, float w, float h, float x, float y, PImage img) {
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
        this.img = img;

        halfWidth = w / 2;
        halfHeight = h / 2;
        this.pApplet = pApplet;
    }

    public void display() {
        pApplet.image(img, x, y);
        pApplet.image(img, x + w, y);
        pApplet.image(img, x + 2 * w, y);
    }
}
