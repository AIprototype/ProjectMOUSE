package game_characters;

import platform.*;
import processing.core.PApplet;
import processing.core.PImage;

import static constants.Constants.*;

public class PlayerMouseCharacter extends CharacterBaseClass {
    int facingRightImagePos;
    int facingLeftImagePos;
    int walkingAnimationSpeedFactor;
    int pointsGainedByPlayer;
    int countOfHalloweenCollectiblesCollected;
    int countOfCloningContainersDestroyed;
    float playerHealth;

    int curr_time;
    int timeAfterWhichPlatformHealthReductionTakesPlace;

    public PlayerMouseCharacter(int characterWidth, int characterHeight, PApplet pApplet, PImage[] mouseSpriteImages, int userSelectedGameMode) {
        super(characterWidth, characterHeight, pApplet, mouseSpriteImages, userSelectedGameMode);
        this.facingRightImagePos = 3;
        this.facingLeftImagePos = 0;
        this.walkingAnimationSpeedFactor = 8;

        this.pointsGainedByPlayer = 0;
        this.countOfHalloweenCollectiblesCollected = 0;
        this.countOfCloningContainersDestroyed = 0;

        this.curr_time = 0;
        this.timeAfterWhichPlatformHealthReductionTakesPlace = 1000;

        if(userSelectedGameMode == NORMAL_MODE_OPTION_ID) {
            this.friction = PLAYER_FRICTION;
            this.bounce = PLAYER_BOUNCE;
            this.playerHealth = 100;
        } else if (userSelectedGameMode == BOUNCY_MODE_OPTION_ID) {
            this.friction = PLAYER_FRICTION;
            this.bounce = PLAYER_BOUNCE_BOUNCY_MODE;
            this.playerHealth = 100;
        } else if (userSelectedGameMode == EXTREME_DIFFICULTY_MODE) {
            this.friction = PLAYER_FRICTION;
            this.bounce = PLAYER_BOUNCE;
            this.playerHealth = 85;
        }
    }

    public float getPlayerHealth() {
        return playerHealth;
    }

    public int getPointsGainedByPlayer() {
        return pointsGainedByPlayer;
    }

    public int getCountOfHalloweenCollectiblesCollected() {
        return countOfHalloweenCollectiblesCollected;
    }

    public int getCountOfCloningContainersDestroyed() {
        return countOfCloningContainersDestroyed;
    }

    public void addPointsGainedByPlayer(int pointsGainedByPlayer) {
        this.pointsGainedByPlayer += pointsGainedByPlayer;
    }

    public void incrementCountOfHalloweenCollectiblesCollected() {
        this.countOfHalloweenCollectiblesCollected += 1;
    }

    public void incrementCountOfCloningContainersDestroyed() {
        this.countOfCloningContainersDestroyed += 1;
    }

    public void addToPlayerHealth(float playerHealth) {
        this.playerHealth += playerHealth;
    }

    @Override
    public void setPlatformBeingUsed(PlatformBaseClass platform) {
        super.setPlatformBeingUsed(platform);
        if (curr_time == 0 || (pApplet.millis() - curr_time) >= timeAfterWhichPlatformHealthReductionTakesPlace) {
            if (platform instanceof ElectricPlatform) {
                playerHealth += HEALTH_REDUCED_BY_ELECTRIC_SPARK_PLATFORM;
                curr_time = pApplet.millis();
            } else if (platform instanceof GroundToxicPlatform) {
                playerHealth += HEALTH_REDUCED_BY_GROUND_TOXIC;
                curr_time = pApplet.millis();
            } else if (platform instanceof StandardMovingPlatforms) {
                if (pApplet.frameCount % ((StandardMovingPlatforms) platform).getVelFrameUpdateInterval() == 0) {
                    x += ((StandardMovingPlatforms) platform).getCurrentVelInUse();
                }
            }
        }
    }

    private void displayExitChatBubble(String message, float textSize) {
        pApplet.textSize(textSize);
        float titleWidth = pApplet.textWidth(message);
        float titleAscent = pApplet.textAscent();
        float titleDescent = pApplet.textDescent();
        float titleHeight = titleAscent + titleDescent;

        pApplet.fill(255, 255, 255);
        pApplet.ellipse(x + 5, y - 5, 5, 5);
        pApplet.ellipse(x - 5, y - 15, 10, 10);
        pApplet.ellipse(x - 15, y - 30, 15, 15);
        pApplet.ellipse(x - 20, y - 65, titleWidth + 15, 3 * titleHeight);
        pApplet.fill(0, 0, 0);
        pApplet.text(message, x - 20 - (titleWidth) / 2, (y - 60));
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
                pApplet.image(mouseSpriteImages[facingRightImagePos], x, y - 27);
            } else {
                //facing right and not moving
                pApplet.image(mouseSpriteImages[3], x, y - 27);
            }
        } else { //images from 0 to 2
            if (PApplet.abs(vx) > 0.3) {
                //facing left and moving
                pApplet.image(mouseSpriteImages[facingLeftImagePos], x, y - 27);
            } else {
                //facing left and not moving
                pApplet.image(mouseSpriteImages[0], x, y - 27);
            }
        }

        if (platformBeingUsed != null && platformBeingUsed instanceof ExitPlatform) {
            displayExitChatBubble(PRESS_ENTER_TO_PROCEED_STRING, 15);
        }

        //changing animation speed depending on speed gained by character
        walkingAnimationSpeedFactor = (int) (walkingAnimationSpeedFactor - PApplet.abs(vx));
        if (isOnGround) {
            if (pApplet.frameCount % walkingAnimationSpeedFactor == 0) {
                if (facingRightImagePos == 5) {
                    facingRightImagePos = 3;
                } else {
                    ++facingRightImagePos;
                }
                if (facingLeftImagePos == 2) {
                    facingLeftImagePos = 0;
                } else {
                    ++facingLeftImagePos;
                }
            }
        }
        resetAnimationSpeedFactor();
    }

    void resetAnimationSpeedFactor() {
        walkingAnimationSpeedFactor = 8;
    }
}
