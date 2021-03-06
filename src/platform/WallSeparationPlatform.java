package platform;

import camera_classes.CameraHandlerClass;
import custom_exceptions.PlatformDimensionException;
import processing.core.PApplet;
import processing.core.PImage;

import static constants.Constants.*;

public class WallSeparationPlatform extends PlatformBaseClass {
    protected PlatformBaseClass platform;
    public WallSeparationPlatform(PImage[] platformSpriteImages, PApplet pApplet, float x, float y, String typeof, PlatformBaseClass referencePlatform, CameraHandlerClass cameraHandlerClass) throws Exception {
        //Height from ground can be calculated using,
        //(MAX_Y_GRID - (int)y/PLATFORM_HEIGHT) * PLATFORM_HEIGHT
        super(platformSpriteImages, pApplet, x, y, 2 * PLATFORM_WIDTH, (GAME_MAX_Y_GRID - (int)y/PLATFORM_HEIGHT) * PLATFORM_HEIGHT, typeof, cameraHandlerClass);
        if(referencePlatform != null) {
            if (referencePlatform.getY() < y) {
                throw new Exception("Wall should come above reference platform !!");
            }
            resetConstructor(platformSpriteImages, pApplet, x, y, 2 * PLATFORM_WIDTH, referencePlatform.getY() - y, typeof);
        }
        this.platform = referencePlatform;
        this.countOfItemsOnPlatform = INITIAL_COST_WALL_PLATFORM;
    }

    @Override
    public void display() throws PlatformDimensionException {
        super.display();
        if (!isPlatformDestroyed) {
            //the y given here should be a multiple of PLATFORM_HEIGHT
            if(h%PLATFORM_HEIGHT != 0){
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
