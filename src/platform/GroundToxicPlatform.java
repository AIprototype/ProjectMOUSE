package platform;

import custom_exceptions.PlatformDimensionException;
import processing.core.PApplet;
import processing.core.PImage;

import static constants.Constants.*;

public class GroundToxicPlatform extends StandardPlatform {
    PImage[] acid_bubbling_sprites;
    int current_anim_pos;
    int anim_speed;
    int max_anim_cycle_length;

    public GroundToxicPlatform(PImage[] platformSpriteImages, PApplet pApplet, float x, float y, float w, float h, String typeof, PImage[] acid_bubbling_sprites) {
        super(platformSpriteImages, pApplet, x, y, w, h, typeof, INITIAL_COST_STANDARD_PLATFORM);
        this.acid_bubbling_sprites = acid_bubbling_sprites;
        this.current_anim_pos = 0;
        this.anim_speed = 5;
        this.max_anim_cycle_length = acid_bubbling_sprites.length - 1;
        this.countOfItemsOnPlatform = INITIAL_COST_TOXIC_PLATFORM;
    }

    @Override
    public void display() throws PlatformDimensionException {
        super.display(); //normal platform display

        //show acid bubbles
        int platformComponents = (int) (w / PLATFORM_WIDTH);
        for (int i = 0; i < platformComponents; ++i) {
            pApplet.image(acid_bubbling_sprites[current_anim_pos], x + i * ACID_BUBBLE_WIDTH, (float) (y - ACID_BUBBLE_HEIGHT/1.5));
        }

        //to control animation
        if (pApplet.frameCount % anim_speed == 0) {
            if (current_anim_pos == max_anim_cycle_length) {
                current_anim_pos = 0;
            } else {
                ++current_anim_pos;
            }
        }
    }
}
