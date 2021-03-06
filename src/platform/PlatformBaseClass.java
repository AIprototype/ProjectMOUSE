package platform;

import camera_classes.CameraHandlerClass;
import custom_exceptions.PlatformDimensionException;
import processing.core.PApplet;
import processing.core.PImage;

import static constants.Constants.DEBUG_MODE;

abstract public class PlatformBaseClass implements Comparable<PlatformBaseClass> {
    protected float w, h, x, y;
    protected float original_w, original_h, original_x, original_y;
    protected String typeof;
    protected float halfWidth, halfHeight;
    protected PApplet pApplet;
    protected boolean isPlayerOnPlatform;
    protected boolean isPlatformDestroyed;
    protected int timePlayerLandedOnPlatform;
    protected int timePlayerLeftThePlatform;
    protected PImage[] platformSpriteImages;
    protected float countOfItemsOnPlatform;
    protected boolean isPlatformActive;
    protected CameraHandlerClass cameraHandlerClass;

    PlatformBaseClass(PImage[] platformSpriteImages, PApplet pApplet, float x, float y, float w, float h, String typeof, CameraHandlerClass cameraHandlerClass) {
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
        this.countOfItemsOnPlatform = 0;
        this.isPlatformActive = false;

        this.cameraHandlerClass = cameraHandlerClass;
    }

    protected void resetConstructor(PImage[] platformSpriteImages, PApplet pApplet, float x, float y, float w, float h, String typeof) {
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
        this.countOfItemsOnPlatform = 0;
    }

    public void setPlatformAsDeactivatedIfNotInCameraRange() {
        if(cameraHandlerClass != null)
            isPlatformActive = x >= cameraHandlerClass.getMinX() && x <= cameraHandlerClass.getMaxX();
        else
            System.out.println("Null !!");
    }

    @Override
    public int compareTo(PlatformBaseClass o) {
        if (countOfItemsOnPlatform < o.countOfItemsOnPlatform)
            return -1;
        else if (countOfItemsOnPlatform > o.countOfItemsOnPlatform)
            return 1;
        return 0;
    }

    public void display() throws PlatformDimensionException {
        if(isPlatformActive) {
            if (DEBUG_MODE) {
                pApplet.fill(0, 0, 255);
                pApplet.text(typeof, x, y);
                pApplet.rect(x, y, w, h);
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

    public boolean isPlayerOnPlatform() {
        return isPlayerOnPlatform;
    }

    public void setPlayerOnPlatform(boolean playerOnPlatform) {
        if (!this.isPlayerOnPlatform && playerOnPlatform) {
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

    public float getCountOfItemsOnPlatform() {
        return countOfItemsOnPlatform;
    }

    public void incrementCountOfItemsOnPlatform() {
        this.countOfItemsOnPlatform += 1;
    }

    public void incrementCountOfEnemiesOnPlatform() {
        this.countOfItemsOnPlatform += 1.5;
    }

    public void setCountOfItemsOnPlatform(int countOfItemsOnPlatform) {
        this.countOfItemsOnPlatform = countOfItemsOnPlatform;
    }

    public boolean isPlatformActive() {
        return isPlatformActive;
    }
}
