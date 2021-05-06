package platform;

import custom_exceptions.PlatformDimensionException;
import processing.core.PApplet;
import processing.core.PImage;

import static constants.Constants.*;

public class WallSeparationPlatform extends PlatformBaseClass {

    public WallSeparationPlatform(PImage[] platformSpriteImages, PApplet pApplet, float x, float y, String typeof) {
        //Height from ground can be calculated using,
        //(MAX_Y_GRID - (int)y/PLATFORM_HEIGHT) * PLATFORM_HEIGHT
        super(platformSpriteImages, pApplet, x, y, 2 * PLATFORM_WIDTH, (CAM_MAX_Y_GRID - (int)y/PLATFORM_HEIGHT) * PLATFORM_HEIGHT, typeof);
        this.countOfItemsOnPlatform = INITIAL_COST_STANDARD_PLATFORM;
    }

    @Override
    public void display() throws PlatformDimensionException {
        if (!isPlatformDestroyed) {
            //the y given here should be a multiple of PLATFORM_HEIGHT
            if(y%PLATFORM_HEIGHT != 0){
                throw new PlatformDimensionException("WallSeparationPlatform: \nY = (N * PLATFORM_HEIGHT), the height will be auto calculated based on given y");
            }
            int platformHeightComponents = (int) (h / PLATFORM_HEIGHT);
            //draw the width part
            pApplet.image(platformSpriteImages[1], x, y);
            pApplet.image(platformSpriteImages[2], x + PLATFORM_WIDTH, y);
            --platformHeightComponents; // the top block took height a bit
            for (int i = 1; i <= platformHeightComponents; ++i) {
                pApplet.image(platformSpriteImages[0], x, y + i * PLATFORM_HEIGHT);
                pApplet.image(platformSpriteImages[3], x + PLATFORM_WIDTH, y + i * PLATFORM_HEIGHT);
            }
        }
    }
}
