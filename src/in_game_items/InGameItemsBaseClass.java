package in_game_items;

import camera_classes.CameraHandlerClass;
import processing.core.PApplet;

public abstract class InGameItemsBaseClass {
    float x, y, w, h;
    float halfWidth, halfHeight;
    PApplet pApplet;
    boolean isInGameItemActive;
    CameraHandlerClass cameraHandlerClass;

    public InGameItemsBaseClass(PApplet pApplet, float x, float y, float w, float h, CameraHandlerClass cameraHandlerClass) {
        this.pApplet = pApplet;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.halfWidth = w/2;
        this.halfHeight = h/2;
        this.cameraHandlerClass = cameraHandlerClass;
    }

    public void setInGameItemAsDeactivatedIfNotInCameraRange() {
        if(cameraHandlerClass != null)
            isInGameItemActive = x >= cameraHandlerClass.getMinX() && x <= cameraHandlerClass.getMaxX();
        else
            System.out.println("Null !!");
    }

    public void display() {
        pApplet.fill(0, 0, 255);
        pApplet.rect(x, y, w, h);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getW() {
        return w;
    }

    public float getH() {
        return h;
    }

    public float getHalfWidth() {
        return halfWidth;
    }

    public float getHalfHeight() {
        return halfHeight;
    }

    public boolean isInGameItemActive() {
        return isInGameItemActive;
    }
}
