package platform;

import processing.core.PApplet;
import processing.core.PImage;

import static constants.Constants.PLATFORM_WIDTH;

abstract public class PlatformBaseClass {
    float w, h, x, y;
    String typeof;
    float halfWidth, halfHeight;
    PApplet pApplet;
    PImage[] platformSpriteImages;

    PlatformBaseClass(PImage[] platformSpriteImages, PApplet pApplet, float x, float y, float w, float h, String typeof) {
        this.pApplet = pApplet;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.typeof = typeof;
        this.platformSpriteImages = platformSpriteImages;

        halfWidth = w / 2;
        halfHeight = h / 2;
    }

    public void display() {
        //pApplet.fill(0, 0, 255);
        //pApplet.rect(x, y, w, h);
        int platformComponents = (int) (w / PLATFORM_WIDTH);
        if (platformComponents >= 2) {
            if (platformComponents == 2) {
                pApplet.image(platformSpriteImages[0], x, y);
                pApplet.image(platformSpriteImages[2], x + PLATFORM_WIDTH, y);
            } else {
                for (int i = 0; i < platformComponents; ++i) {
                    if (i == 0)
                        pApplet.image(platformSpriteImages[0], x, y);
                    else if (i < platformComponents - 1) {
                        pApplet.image(platformSpriteImages[1], (x + PLATFORM_WIDTH * i), y);
                    } else {
                        pApplet.image(platformSpriteImages[2], x + PLATFORM_WIDTH * i, y);
                    }
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

    public String getTypeof() {
        return typeof;
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
}
