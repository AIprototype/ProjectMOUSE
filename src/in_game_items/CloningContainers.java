package in_game_items;

import platform.PlatformBaseClass;
import processing.core.PApplet;
import processing.core.PImage;

import static constants.Constants.*;

public class CloningContainers extends InGameItemsBaseClass {
    PImage[] cloning_container_normal;
    PImage[] cloning_container_destroyed;
    boolean isDestroyed;
    final PlatformBaseClass platformToPlace;

    int currentNormalAnimationPos;
    final int maxNormalAnimPos;

    int currentDestroyedAnimationPos;
    final int maxDestroyedAnimPos;

    final int animationSpeedFactor;

    public CloningContainers(PApplet pApplet, PlatformBaseClass platformToPlace, PImage[] cloning_container_normal, PImage[] cloning_container_destroyed) {
        super(pApplet,
                pApplet.random(platformToPlace.getX(), platformToPlace.getX() + (platformToPlace.getW() - CLONING_CONTAINER_WIDTH)),
                platformToPlace.getY() - CLONING_CONTAINER_HEIGHT,
                CLONING_CONTAINER_WIDTH,
                CLONING_CONTAINER_HEIGHT);
        this.isDestroyed = false;
        this.platformToPlace = platformToPlace;
        this.cloning_container_normal = cloning_container_normal;
        this.cloning_container_destroyed = cloning_container_destroyed;
        this.animationSpeedFactor = 12;
        this.currentNormalAnimationPos = 0;
        this.maxNormalAnimPos = 10;
        this.currentDestroyedAnimationPos = 0;
        this.maxDestroyedAnimPos = 3;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    @Override
    public void display() {

        if(isDestroyed) {
            pApplet.image(cloning_container_destroyed[currentDestroyedAnimationPos], x, y);
        } else {
            pApplet.image(cloning_container_normal[currentNormalAnimationPos], x, y);
        }

        if (pApplet.frameCount % animationSpeedFactor == 0) {
            if (currentNormalAnimationPos == maxNormalAnimPos) {
                currentNormalAnimationPos = 0;
            } else {
                ++currentNormalAnimationPos;
            }
            if (currentDestroyedAnimationPos == maxDestroyedAnimPos) {
                currentDestroyedAnimationPos = 0;
            } else {
                ++currentDestroyedAnimationPos;
            }
        }
    }
}
