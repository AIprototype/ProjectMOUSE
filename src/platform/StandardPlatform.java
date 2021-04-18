package platform;

import processing.core.PApplet;
import processing.core.PImage;

public class StandardPlatform extends PlatformBaseClass {
    public StandardPlatform(PImage[] platformSpriteImages, PApplet pApplet, float x, float y, float w, float h, String typeof) {
        super(platformSpriteImages, pApplet, x, y, w, h, typeof);
    }
}
