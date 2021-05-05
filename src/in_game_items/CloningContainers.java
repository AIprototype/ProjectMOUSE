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
    final ConsolePc pcAttachedWith;

    int currentNormalAnimationPos;
    final int maxNormalAnimPos;

    int currentDestroyedAnimationPos;
    final int maxDestroyedAnimPos;

    final int animationSpeedFactor;

    public CloningContainers(PApplet pApplet,
                             PlatformBaseClass platformToPlace,
                             PImage[] cloning_container_normal,
                             PImage[] cloning_container_destroyed,
                             ConsolePc pcAttachedWith) {
        super(pApplet,
                pcAttachedWith.getX(),
                platformToPlace.getY() - CLONING_CONTAINER_HEIGHT,
                CLONING_CONTAINER_WIDTH,
                CLONING_CONTAINER_HEIGHT);
        //normal inits
        this.isDestroyed = false;
        this.platformToPlace = platformToPlace;
        this.pcAttachedWith = pcAttachedWith;
        this.cloning_container_normal = cloning_container_normal;
        this.cloning_container_destroyed = cloning_container_destroyed;
        this.animationSpeedFactor = 12;
        this.currentNormalAnimationPos = 0;
        this.maxNormalAnimPos = 10;
        this.currentDestroyedAnimationPos = 0;
        this.maxDestroyedAnimPos = 3;

        //modify the X co-ordinate to account for conditions when cloning container is outside the platform
        this.x = getXCoordinate();
    }

    private float getXCoordinate() {
        if((pcAttachedWith.getX() + pcAttachedWith.getW() + this.getW()) > (platformToPlace.getX() + platformToPlace.getW())) {
            return pcAttachedWith.getX() - pcAttachedWith.getW();
        } else {
            return pcAttachedWith.getX() + pcAttachedWith.getW();
        }
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    @Override
    public void display() {

        if(!platformToPlace.isPlatformDestroyed()) {
            if(isDestroyed) {
                pApplet.image(cloning_container_destroyed[currentDestroyedAnimationPos], x, y);
            } else {
                pApplet.image(cloning_container_normal[currentNormalAnimationPos], x, y);
            }
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
