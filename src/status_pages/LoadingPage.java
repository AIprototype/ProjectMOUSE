package status_pages;

import constants.StringConstants;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Collections;

import static constants.Constants.*;

public class LoadingPage extends StatusPageBaseClass {
    final PImage[] loading_sprites;
    int current_anim_pos;
    int max_anim_pos;
    int anim_speed;
    int loading_hints_pos;
    int loading_hints_max_pos;
    ArrayList<String> loading_hints;
    int curr_time;
    int max_time_to_show_hint = 1500;

    public LoadingPage(String title, String message, PApplet pApplet) {
        super(title, message, pApplet, 25, 18);
        this.loading_sprites = new PImage[28];
        this.current_anim_pos = 0;
        this.max_anim_pos = loading_sprites.length - 1;
        this.anim_speed = 3;
        this.loading_hints_pos = 0;
        this.loading_hints = new StringConstants().getLoadingHints();
        Collections.shuffle(loading_hints);
        this.loading_hints_max_pos = loading_hints.size() - 1;

        for (int i = 0; i < 28; ++i) {
            String fileName = "loading_animation/frame-" + i + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(LOADING_SPRITE_WIDTH, LOADING_SPRITE_HEIGHT);
            loading_sprites[i] = img;
        }
        this.curr_time = pApplet.millis();
    }

    private void recalculateMessageParams() {
        pApplet.textSize(messageTextSize);
        this.messageWidth = pApplet.textWidth(message);
        messageAscent = pApplet.textAscent();
        messageDescent = pApplet.textDescent();
        messageHeight = titleAscent + titleDescent;
    }

    @Override
    public void display() {
        message = loading_hints.get(loading_hints_pos);
        recalculateMessageParams();
        super.display();
        pApplet.image(loading_sprites[current_anim_pos], 30, pApplet.height - 30 - LOADING_SPRITE_HEIGHT);

        if (pApplet.frameCount % anim_speed == 0) {
            if (current_anim_pos >= max_anim_pos) {
                current_anim_pos = 0;
            } else {
                ++current_anim_pos;
            }
        }

        if(pApplet.millis() - curr_time >= max_time_to_show_hint) {
            curr_time = pApplet.millis();
            if(loading_hints_pos >= loading_hints_max_pos) {
                loading_hints_pos = 0;
            } else {
                ++loading_hints_pos;
            }
        }
    }
}
