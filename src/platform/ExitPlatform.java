package platform;

import custom_exceptions.PlatformDimensionException;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.Arrays;
import java.util.Collections;

import static constants.Constants.*;

public class ExitPlatform extends StandardPlatform {
    boolean exitAtRightEnd;
    PImage[] door_opening_sprites;
    int openingAnimCount;
    int closingAnimCount;

    public ExitPlatform(PImage[] platformSpriteImages, PApplet pApplet, float x, float y, float w, float h, String typeof, int initialPlatformCost, PImage[] door_opening_sprites, boolean exitAtRightEnd) {
        super(platformSpriteImages, pApplet, x, y, w, h, typeof, initialPlatformCost);

        this.door_opening_sprites = door_opening_sprites;

        this.openingAnimCount = 0;
        this.closingAnimCount = 0;

        this.exitAtRightEnd = exitAtRightEnd;
    }

    @Override
    public void display() throws PlatformDimensionException {
        super.display();
        if (isPlayerOnPlatform() && openingAnimCount <= (door_opening_sprites.length - 1)) {
            if(exitAtRightEnd)
                pApplet.image(door_opening_sprites[pApplet.frameCount % door_opening_sprites.length], x + MONSTER_BEHIND_DOOR_WIDTH, y - MONSTER_BEHIND_DOOR_HEIGHT);
            else
                pApplet.image(door_opening_sprites[pApplet.frameCount % door_opening_sprites.length], x, y - MONSTER_BEHIND_DOOR_HEIGHT);
            ++openingAnimCount;
        } else if (isPlayerOnPlatform() && openingAnimCount > (door_opening_sprites.length - 1)) {
            if(exitAtRightEnd)
                pApplet.image(door_opening_sprites[door_opening_sprites.length - 1], x + MONSTER_BEHIND_DOOR_WIDTH, y - MONSTER_BEHIND_DOOR_HEIGHT);
            else
                pApplet.image(door_opening_sprites[door_opening_sprites.length - 1], x, y - MONSTER_BEHIND_DOOR_HEIGHT);
        }

        if(!isPlayerOnPlatform()) {
            openingAnimCount = 0;
        } else {
            closingAnimCount = 0;
        }

        if (!isPlayerOnPlatform() && closingAnimCount <= (door_opening_sprites.length - 1)) {
            if(exitAtRightEnd)
                pApplet.image(door_opening_sprites[door_opening_sprites.length - 1 - pApplet.frameCount % door_opening_sprites.length], x + MONSTER_BEHIND_DOOR_WIDTH, y - MONSTER_BEHIND_DOOR_HEIGHT);
            else
                pApplet.image(door_opening_sprites[door_opening_sprites.length - 1 - pApplet.frameCount % door_opening_sprites.length], x, y - MONSTER_BEHIND_DOOR_HEIGHT);
            ++closingAnimCount;
        } else if (!isPlayerOnPlatform() && closingAnimCount > (door_opening_sprites.length - 1)) {
            if(exitAtRightEnd)
                pApplet.image(door_opening_sprites[0], x + MONSTER_BEHIND_DOOR_WIDTH, y - MONSTER_BEHIND_DOOR_HEIGHT);
            else
                pApplet.image(door_opening_sprites[0], x, y - MONSTER_BEHIND_DOOR_HEIGHT);
        }
    }
}
