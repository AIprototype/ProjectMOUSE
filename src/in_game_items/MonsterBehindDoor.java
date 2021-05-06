package in_game_items;

import platform.PlatformBaseClass;
import processing.core.PApplet;
import processing.core.PImage;

import static constants.Constants.*;

public class MonsterBehindDoor extends InGameItemsBaseClass {
    PImage[] monster_behind_door_sprites;
    int current_anim_pos;
    int anim_speed;
    int max_anim_cycle_length;
    PlatformBaseClass platformToPlace;
    public MonsterBehindDoor(PApplet pApplet, PImage[] monster_behind_door_sprites, PlatformBaseClass platformToPlace) {
        super(pApplet,
                pApplet.random(platformToPlace.getX(), platformToPlace.getX() + (platformToPlace.getW() - MONSTER_BEHIND_DOOR_WIDTH)),
                platformToPlace.getY() - MONSTER_BEHIND_DOOR_HEIGHT,
                MONSTER_BEHIND_DOOR_WIDTH,
                MONSTER_BEHIND_DOOR_HEIGHT);
        this.platformToPlace = platformToPlace;
        this.monster_behind_door_sprites = monster_behind_door_sprites;
        this.current_anim_pos = 0;
        this.anim_speed = 8;
        this.max_anim_cycle_length = monster_behind_door_sprites.length - 1;
    }

    @Override
    public void display() {
        if (!platformToPlace.isPlatformDestroyed()) {
            pApplet.image(monster_behind_door_sprites[current_anim_pos], x, y + 27);
        }

        if (pApplet.frameCount % anim_speed == 0) {
            if (current_anim_pos == max_anim_cycle_length) {
                current_anim_pos = 0;
            } else {
                ++current_anim_pos;
            }
        }
    }
}
