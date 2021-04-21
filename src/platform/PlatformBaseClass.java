package platform;

import custom_exceptions.PlatformDimensionException;
import processing.core.PApplet;
import processing.core.PImage;

abstract public class PlatformBaseClass {
    float w, h, x, y;
    final float original_w, original_h, original_x, original_y;
    String typeof;
    float halfWidth, halfHeight;
    PApplet pApplet;
    boolean isPlayerOnPlatform;
    boolean isPlatformDestroyed;
    int timePlayerLandedOnPlatform;
    int timePlayerLeftThePlatform;
    PImage[] platformSpriteImages;

    PlatformBaseClass(PImage[] platformSpriteImages, PApplet pApplet, float x, float y, float w, float h, String typeof) {
        this.pApplet = pApplet;
        this.x = x;
        this.original_x = x;
        this.y = y;
        this.original_y = y;
        this.w = w;
        this.original_w = w;
        this.h = h;
        this.original_h = h;
        this.typeof = typeof;
        this.platformSpriteImages = platformSpriteImages;

        halfWidth = w / 2;
        halfHeight = h / 2;

        this.isPlayerOnPlatform = false;
        this.isPlatformDestroyed = false;
        this.timePlayerLandedOnPlatform = -1;
        this.timePlayerLeftThePlatform = -1;
    }

    public void display() throws PlatformDimensionException {
        pApplet.fill(0, 0, 255);
        pApplet.rect(x, y, w, h);
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

    public boolean isPlayerOnPlatform() {
        return isPlayerOnPlatform;
    }

    public void setPlayerOnPlatform(boolean playerOnPlatform) {
        if(!this.isPlayerOnPlatform && playerOnPlatform) {
            //For telling the platform the player is on top,
            //no need to tell always, it will be set to false when he lands on another platform
            timePlayerLandedOnPlatform = pApplet.millis();
            timePlayerLeftThePlatform = -1;
            isPlayerOnPlatform = true;
        } else if (this.isPlayerOnPlatform && !playerOnPlatform) {
            //For informing the platform the player has landed on another platform
            //Only when player has landed on another platform
            timePlayerLandedOnPlatform = -1;
            timePlayerLeftThePlatform = pApplet.millis();
            isPlayerOnPlatform = false;
        }
    }

    public boolean isPlatformDestroyed() {
        return isPlatformDestroyed;
    }

    void setPlatformDestroyed(boolean platformDestroyed) {
        isPlatformDestroyed = platformDestroyed;
    }
}
