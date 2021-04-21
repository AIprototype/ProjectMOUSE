package platform;

import custom_exceptions.PlatformDimensionException;
import processing.core.PApplet;
import processing.core.PImage;

import static constants.Constants.PLATFORM_WIDTH;

public class StandardPlatform extends PlatformBaseClass {
    public StandardPlatform(PImage[] platformSpriteImages, PApplet pApplet, float x, float y, float w, float h, String typeof) {
        super(platformSpriteImages, pApplet, x, y, w, h, typeof);
    }

    @Override
    public void display() throws PlatformDimensionException {
        if(w/PLATFORM_WIDTH < 2){
            throw new PlatformDimensionException("StandardPlatforms: \nWidth = (N * PLATFORM_WIDTH) \nHeight = (PLATFORM_HEIGHT)");
        }
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
