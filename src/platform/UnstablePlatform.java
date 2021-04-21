package platform;

import custom_exceptions.PlatformDimensionException;
import processing.core.PApplet;
import processing.core.PImage;

import static constants.Constants.*;

public class UnstablePlatform extends PlatformBaseClass {
    float instabilityFwdVel_x = 4f;
    float instabilityRevVel_x = -4f;
    boolean isCurrentInstabilityVelFwd = false;

    public UnstablePlatform(PImage[] platformSpriteImages, PApplet pApplet, float x, float y, float w, float h, String typeof) {
        super(platformSpriteImages, pApplet, x, y, w, h, typeof);
    }

    @Override
    public void display() throws PlatformDimensionException {
        if(w/PLATFORM_WIDTH < 2){
            throw new PlatformDimensionException("UnstablePlatform: \nWidth = (N * PLATFORM_WIDTH) \nHeight = (PLATFORM_HEIGHT)");
        }
        destroyPlatformIfPlayerOnTop();

        if(!isPlatformDestroyed){
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
    }

    void animatePlatformInstability(){
        if(pApplet.frameCount % 3 == 0) {
            if(isCurrentInstabilityVelFwd) {
                x += instabilityFwdVel_x;
                isCurrentInstabilityVelFwd = false;
            } else {
                x += instabilityRevVel_x;
                isCurrentInstabilityVelFwd = true;
            }
        }
    }

    void destroyPlatformIfPlayerOnTop(){
        if(isPlayerOnPlatform()){
            animatePlatformInstability();
            if(pApplet.millis() - timePlayerLandedOnPlatform > TIME_FOR_UNSTABLE_TO_DESTROY) {
                setPlatformDestroyed(true);
                setPlayerOnPlatform(false);
            }
        } else {
            if(pApplet.millis() - timePlayerLeftThePlatform > TIME_FOR_UNSTABLE_TO_RECONSTRUCT) {
                x = original_x;
                setPlatformDestroyed(false);
                setPlayerOnPlatform(false);
            }
        }
    }
}
