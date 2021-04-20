package platform;

import processing.core.PApplet;
import processing.core.PImage;

import static constants.Constants.PLATFORM_HEIGHT;
import static constants.Constants.PLATFORM_WIDTH;

public class UnstablePlatform extends PlatformBaseClass {
    PImage[] platformSpriteImages;

    public UnstablePlatform(PApplet pApplet, float x, float y, float w, float h, String typeof) {
        super(pApplet, x, y, w, h, typeof);
        platformSpriteImages = new PImage[3];
        for (int i = 0; i < 3; ++i) {
            PImage img = pApplet.loadImage("unstable_platform" + PApplet.nf(i + 1, 4) + ".png");
            img.resize(PLATFORM_WIDTH, PLATFORM_HEIGHT);
            platformSpriteImages[i] = img;
        }
    }

    @Override
    public void display() {
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
