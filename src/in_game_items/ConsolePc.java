package in_game_items;

import platform.PlatformBaseClass;
import processing.core.PApplet;
import processing.core.PImage;

import static constants.Constants.CONSOLE_PC_HEIGHT;
import static constants.Constants.CONSOLE_PC_WIDTH;

public class ConsolePc extends InGameItemsBaseClass {
    PImage[] console_pc_sprites;
    int animationSpeedFactor;
    int currentAnimationPos;
    final int maxAnimPos = 4;
    final PlatformBaseClass platformToPlace;

    public ConsolePc(PApplet pApplet, PlatformBaseClass platformToPlace, PImage[] console_pc_sprites) {
        super(pApplet,
                pApplet.random(platformToPlace.getX(), platformToPlace.getX() + (platformToPlace.getW() - CONSOLE_PC_WIDTH)),
                platformToPlace.getY() - CONSOLE_PC_HEIGHT,
                CONSOLE_PC_WIDTH,
                CONSOLE_PC_HEIGHT);
        this.platformToPlace = platformToPlace;
        this.console_pc_sprites = console_pc_sprites;
        this.animationSpeedFactor = 15;
        this.currentAnimationPos = 0;
    }

    @Override
    public void display() {

        if (!platformToPlace.isPlatformDestroyed()) {
            pApplet.image(console_pc_sprites[currentAnimationPos], x, y);
        }

        if (pApplet.frameCount % animationSpeedFactor == 0) {
            if (currentAnimationPos == maxAnimPos) {
                currentAnimationPos = 0;
            } else {
                ++currentAnimationPos;
            }
        }
    }
}
