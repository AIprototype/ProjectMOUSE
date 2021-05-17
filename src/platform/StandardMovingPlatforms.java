package platform;

import custom_exceptions.PlatformDimensionException;
import processing.core.PApplet;
import processing.core.PImage;

import static constants.Constants.INITIAL_COST_MOVING_PLATFORM;

public class StandardMovingPlatforms extends StandardPlatform {
    float fwdVel_x = 1f;
    float revVel_x = -1f;
    float currentVelInUse = 0f;
    float maxVelAdditionOnEachDirection = 155;
    float currentVelFwdAdditionPos = 1;
    float currentVelRevAdditionPos = 0;
    float velFrameUpdateInterval = 2;

    public StandardMovingPlatforms(PImage[] platformSpriteImages, PApplet pApplet, float x, float y, float w, float h, String typeof, int initialPlatformCost) {
        super(platformSpriteImages, pApplet, x, y, w, h, typeof, initialPlatformCost);
        initialPlatformCost = INITIAL_COST_MOVING_PLATFORM;
    }

    @Override
    public void display() throws PlatformDimensionException {
        super.display();

        if (pApplet.frameCount % velFrameUpdateInterval == 0) {
            if (currentVelFwdAdditionPos < maxVelAdditionOnEachDirection && currentVelFwdAdditionPos != 0) {
                x += fwdVel_x;
                ++currentVelFwdAdditionPos;
                currentVelInUse = fwdVel_x;
            } else if (currentVelRevAdditionPos < maxVelAdditionOnEachDirection && currentVelRevAdditionPos != 0) {
                x += revVel_x;
                ++currentVelRevAdditionPos;
                currentVelInUse = revVel_x;
            }
        }

        if (currentVelFwdAdditionPos >= maxVelAdditionOnEachDirection) {
            currentVelFwdAdditionPos = 0;
            currentVelRevAdditionPos = 1;
        } else if (currentVelRevAdditionPos >= maxVelAdditionOnEachDirection) {
            currentVelRevAdditionPos = 0;
            currentVelFwdAdditionPos = 1;
        }

    }

    public float getCurrentVelInUse() {
        return currentVelInUse;
    }

    public float getVelFrameUpdateInterval() {
        return velFrameUpdateInterval;
    }
}
