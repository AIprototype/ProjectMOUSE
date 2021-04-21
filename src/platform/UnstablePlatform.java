package platform;

import custom_exceptions.PlatformDimensionException;
import processing.core.PApplet;
import processing.core.PImage;

import static constants.Constants.PLATFORM_WIDTH;

public class UnstablePlatform extends PlatformBaseClass {
    float instabilityAcc_x = 0.1f;
    float instabilityVel_x = 0;

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
        instabilityVel_x += instabilityAcc_x;
        x += instabilityVel_x;
        instabilityAcc_x = pApplet.random(-0.1f, 0.1f);
    }

    void destroyPlatformIfPlayerOnTop(){
        if(isPlayerOnPlatform()){
            animatePlatformInstability();
            if(pApplet.millis() - timePlayerLandedOnPlatform > 2500) {
                setPlatformDestroyed(true);
                setPlayerOnPlatform(false);
            }
        } else {
            if(pApplet.millis() - timePlayerLeftThePlatform > 1000) {
                x = original_x;
                setPlatformDestroyed(false);
                setPlayerOnPlatform(false);
            }
        }
    }
}
