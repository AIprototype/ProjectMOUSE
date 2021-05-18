package platform;

import camera_classes.CameraHandlerClass;
import custom_exceptions.PlatformDimensionException;
import processing.core.PApplet;
import processing.core.PImage;

import static constants.Constants.*;

public class ElectricPlatform extends UnstablePlatform {
    PImage[] electricity_generator_sprites;
    PImage[] electric_sparks_sprites;

    //for electricity generator
    int gen_current_anim_pos;
    int gen_anim_speed;
    int gen_max_anim_cycle_length;

    //for electric sparks
    int sparks_current_anim_pos;
    int sparks_anim_speed;
    int sparks_max_anim_cycle_length;

    public ElectricPlatform(PImage[] platformSpriteImages, PApplet pApplet, float x, float y, float w, float h, String typeof, PImage[] electricity_generator_sprites, PImage[] electric_sparks_sprites, CameraHandlerClass cameraHandlerClass) {
        super(platformSpriteImages, pApplet, x, y, w, h, typeof, INITIAL_COST_UNSTABLE_PLATFORM, cameraHandlerClass);
        this.electric_sparks_sprites = electric_sparks_sprites;
        this.electricity_generator_sprites = electricity_generator_sprites;

        this.gen_current_anim_pos = 0;
        this.gen_anim_speed = 4;
        this.gen_max_anim_cycle_length = electricity_generator_sprites.length - 1;

        this.sparks_current_anim_pos = 0;
        this.sparks_anim_speed = 10;
        this.sparks_max_anim_cycle_length = electric_sparks_sprites.length - 1;
    }

    @Override
    public void display() throws PlatformDimensionException {
        super.display();

        int platformComponents = (int) (w / PLATFORM_WIDTH);
        for (int i = 0; i < platformComponents; ++i) {
            pApplet.image(electric_sparks_sprites[sparks_current_anim_pos], x - 15 + i * PLATFORM_WIDTH, y - 10);
        }

        //to control animation
        if (pApplet.frameCount % gen_anim_speed == 0) {
            if (gen_current_anim_pos == gen_max_anim_cycle_length) {
                gen_current_anim_pos = 0;
            } else {
                ++gen_current_anim_pos;
            }
        }

        if (pApplet.frameCount % sparks_anim_speed == 0) {
            if (sparks_current_anim_pos == sparks_max_anim_cycle_length) {
                sparks_current_anim_pos = 0;
            } else {
                ++sparks_current_anim_pos;
            }
        }
    }
}
