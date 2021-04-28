package game_characters;

import processing.core.PApplet;
import processing.core.PImage;

public class PlayerMouseCharacter extends CharacterBaseClass {
    int facingRightImagePos;
    int facingLeftImagePos;

    public PlayerMouseCharacter(int characterWidth, int characterHeight, PApplet pApplet, PImage[] mouseSpriteImages) {
        super(characterWidth, characterHeight, pApplet, mouseSpriteImages);
        this.facingRightImagePos = 3;
        this.facingLeftImagePos = 0;
    }

    @Override
    public void display() {
        ////////////////////////////////////////
        //The rectangle body with no graphics///
        //super.display();                    //
        ////////////////////////////////////////
        if (facingRight) { //images from 3 to 5
            if (PApplet.abs(vx) > 0.3) {
                //facing right and moving
                pApplet.image(mouseSpriteImages[facingRightImagePos], x, y-27);
            } else {
                //facing right and not moving
                pApplet.image(mouseSpriteImages[3], x, y-27);
            }
        } else { //images from 0 to 2
            if (PApplet.abs(vx) > 0.3) {
                //facing left and moving
                pApplet.image(mouseSpriteImages[facingLeftImagePos], x, y-27);
            } else {
                //facing left and not moving
                pApplet.image(mouseSpriteImages[0], x, y-27);
            }
        }
        if(isOnGround) {
            if(pApplet.frameCount % 7 == 0) {
                if(facingRightImagePos == 5){
                    facingRightImagePos = 3;
                } else {
                    ++facingRightImagePos;
                }
                if(facingLeftImagePos == 2) {
                    facingLeftImagePos = 0;
                } else {
                    ++facingLeftImagePos;
                }
            }
        }
    }
}
