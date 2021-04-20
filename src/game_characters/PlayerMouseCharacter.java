package game_characters;

import processing.core.PApplet;
import processing.core.PImage;

public class PlayerMouseCharacter extends CharacterBaseClass {
    public PlayerMouseCharacter(int characterWidth, int characterHeight, PApplet pApplet, PImage[] mouseSpriteImages) {
        super(characterWidth, characterHeight, pApplet, mouseSpriteImages);
    }

    @Override
    public void display() {
        ////////////////////////////////////////
        //The rectangle body with no graphics///
        //super.display();                    //
        ////////////////////////////////////////
        if (facingRight) {
            if (PApplet.abs(vx) > 0.3) {
                //facing right and moving
                pApplet.image(mouseSpriteImages[currentFrame + 3], x, y-27);
            } else {
                //facing right and not moving
                pApplet.image(mouseSpriteImages[3], x, y-27);
            }
        } else {
            if (PApplet.abs(vx) > 0.3) {
                //facing left and moving
                pApplet.image(mouseSpriteImages[currentFrame], x, y-27);
            } else {
                //facing left and not moving
                pApplet.image(mouseSpriteImages[0], x, y-27);
            }
        }
        if (isOnGround) {
            currentFrame = (currentFrame + 1) % frameSequence;
        } else {
            currentFrame = 0;
        }
    }
}
