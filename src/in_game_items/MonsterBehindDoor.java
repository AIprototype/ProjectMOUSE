package in_game_items;

import camera_classes.CameraHandlerClass;
import constants.StringConstants;
import game_characters.PlayerMouseCharacter;
import platform.PlatformBaseClass;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Collections;

import static constants.Constants.*;

public class MonsterBehindDoor extends InGameItemsBaseClass {
    PImage[] monster_behind_door_sprites;
    int current_anim_pos;
    int anim_speed;
    int max_anim_cycle_length;
    PlatformBaseClass platformToPlace;
    PlayerMouseCharacter player;
    ArrayList<String> monsterChatList;
    int currentChatPos;
    int maxChatPos;

    public MonsterBehindDoor(PApplet pApplet, PImage[] monster_behind_door_sprites, PlatformBaseClass platformToPlace, PlayerMouseCharacter playerMouseCharacter, CameraHandlerClass cameraHandlerClass) {
        super(pApplet,
                pApplet.random(platformToPlace.getX(), platformToPlace.getX() + (platformToPlace.getW() - MONSTER_BEHIND_DOOR_WIDTH)),
                platformToPlace.getY() - MONSTER_BEHIND_DOOR_HEIGHT,
                MONSTER_BEHIND_DOOR_WIDTH,
                MONSTER_BEHIND_DOOR_HEIGHT, cameraHandlerClass);
        this.platformToPlace = platformToPlace;
        this.monster_behind_door_sprites = monster_behind_door_sprites;
        this.current_anim_pos = 0;
        this.anim_speed = 8;
        this.max_anim_cycle_length = monster_behind_door_sprites.length - 1;
        this.player = playerMouseCharacter;
        monsterChatList = new StringConstants().getMonsterChatList();
        Collections.shuffle(monsterChatList);
        this.currentChatPos = 0;
        this.maxChatPos = monsterChatList.size() - 1;
    }

    private static int countLines(String str) {
        String[] lines = str.split("\r\n|\r|\n");
        return lines.length;
    }

    private void displayChatBubble(String message, float textSize) {
        pApplet.textSize(textSize);
        float titleWidth = pApplet.textWidth(message);
        float titleAscent = pApplet.textAscent();
        float titleDescent = pApplet.textDescent();
        float titleHeight = titleAscent + titleDescent;

        pApplet.fill(255, 255, 255, 191);
        pApplet.stroke(0, 0, 0, 191);
        pApplet.ellipse(x + 5, y + 10, 5, 5);
        pApplet.ellipse(x - 5, y + 5, 10, 10);
        pApplet.ellipse(x - 15, y - 5, 15, 15);
        pApplet.ellipse(x - 20, y - 50, titleWidth + 15, 3 * titleHeight * countLines(message));
        pApplet.fill(0, 0, 0, 255);
        pApplet.text(message, x - 20 - (titleWidth) / 2, (y - 45));
    }

    @Override
    public void display() {
        if (!platformToPlace.isPlatformDestroyed()) {
            pApplet.image(monster_behind_door_sprites[current_anim_pos], x, y + 27);
        }

        if (player.getPlatformBeingUsed() != null && player.getPlatformBeingUsed().equals(platformToPlace)) {
            displayChatBubble(monsterChatList.get(currentChatPos), 15);
        } else {
            if (pApplet.frameCount % 60 == 0) {
                if (currentChatPos < maxChatPos) {
                    ++currentChatPos;
                } else {
                    currentChatPos = 0;
                }
            }
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
