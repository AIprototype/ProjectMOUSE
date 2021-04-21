import camera_classes.FrameObject;
import camera_classes.ImageObject;
import game_characters.PlayerMouseCharacter;
import platform.PlatformBaseClass;
import platform.StandardPlatform;
import platform.UnstablePlatform;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

import static constants.Constants.*;

public class ProjectMouse extends PApplet {

    boolean left, right, up, down, space;
    PlayerMouseCharacter player;
    ArrayList<PlatformBaseClass> platformArray;
    int frames;
    PImage[] mouseSpriteImages;
    FrameObject camera, gameWorld;
    ImageObject backImage;
    PImage bgImage;

    public static void main(String[] args) {
        PApplet.main("ProjectMouse");
        System.out.println("Welcome to Project MOUSE !!");
    }

    @Override
    public void settings() {
        size(1000, 600);
    }

    @Override
    public void setup() {
        left = false;
        right = false;
        up = false;
        down = false;
        space = false;

        //background image & camera
        bgImage = loadImage("background.jpg");
        backImage = new ImageObject(this, 1024, 768, 0, 0, bgImage);
        gameWorld = new FrameObject(0, 0, backImage.getW() * 3, backImage.getH());
        camera = new FrameObject(0, 0, width, height);
        camera.setX((gameWorld.getX() + gameWorld.getW() / 2) - camera.getW() / 2);
        camera.setY((gameWorld.getY() + gameWorld.getH() / 2) - camera.getH() / 2);

        //mouse images
        frames = 6;
        mouseSpriteImages = new PImage[frames];
        for (int i = 0; i < frames; ++i) {
            PImage img = loadImage("mouse" + nf(i + 1, 4) + ".png");
            img.resize(PLAYER_WIDTH, PLAYER_HEIGHT);
            mouseSpriteImages[i] = img;
        }

        player = new PlayerMouseCharacter(PLATFORM_WIDTH, PLATFORM_HEIGHT, this, mouseSpriteImages);
        platformArray = new ArrayList<>();
        platformArray.add(new StandardPlatform(this, 20,
                630, PLATFORM_WIDTH * 2, PLATFORM_HEIGHT, "safe"));
        platformArray.add(new StandardPlatform(this, 210,
                490, PLATFORM_WIDTH * 3, PLATFORM_HEIGHT, "safe"));
        platformArray.add(new StandardPlatform(this, 490,
                460, PLATFORM_WIDTH * 3, PLATFORM_HEIGHT, "safe"));
        platformArray.add(new UnstablePlatform(this, 700,
                360, PLATFORM_WIDTH * 5, PLATFORM_HEIGHT, "safe"));
    }

    @Override
    public void draw() {
        //background(0);
        player.update(left, right, up, down, space, gameWorld);

        //Move the camera
        camera.setX(floor(player.getX() + player.getHalfWidth() - (camera.getW() / 2)));
        camera.setY(floor(player.getY() + player.getHalfHeight() - (camera.getH() / 2)));
        //keeping camera within  game world boundaries
        if (camera.getX() < gameWorld.getX()) {
            camera.setX(gameWorld.getX());
        }
        if (camera.getY() < gameWorld.getY()) {
            camera.setY(gameWorld.getY());
        }
        if ((camera.getX() + camera.getW()) > (gameWorld.getX() + gameWorld.getW())) {
            camera.setX(gameWorld.getX() + gameWorld.getW() - camera.getW());
        }
        if ((camera.getY() + camera.getH()) > gameWorld.getH()) {
            camera.setY(gameWorld.getH() - camera.getH());
        }

        //push stores the original state
        pushMatrix();
        translate(-camera.getX(), -camera.getY());
        backImage.display();

        rectangleCollision(player, platformArray);
        player.display();

        for (PlatformBaseClass platform : platformArray) {
            platform.display();
        }

        //the push and pop isolates the translation done
        //pops out the original stored state
        popMatrix();

        //for getting details on screen
        //doesnt move with the screen as it is after popMatrix()
        displayPositionData();
    }

    void rectangleCollision(PlayerMouseCharacter r1, ArrayList<PlatformBaseClass> platformList) {
        //Disable if the player cannot pass through platforms,
        //if enabled, the player can pass from below the platform
//        if (r1.getVy() < 0) {
//            return "none";
//        }
        String nonNoneCollision = "none";
        for (PlatformBaseClass r2 : platformList) {
            float dx = (r1.getX() + r1.getW() / 2) - (r2.getX() + r2.getW() / 2);
            float dy = (r1.getY() + r1.getH() / 2) - (r2.getY() + r2.getH() / 2);

            float combinedHalfWidths = r1.getHalfWidth() + r2.getHalfWidth();
            float combinedHalfHeights = r1.getHalfHeight() + r2.getHalfHeight();

            if (abs(dx) < combinedHalfWidths) {
                //Collision happened on the x axis
                //now check y axis
                if (abs(dy) < combinedHalfHeights) {
                    //Collision detected
                    //determine the overlap on each axis
                    float overlapX = combinedHalfWidths - abs(dx);
                    float overlapY = combinedHalfHeights - abs(dy);
                    //collision happened on the axis with the smallest overlap
                    if (overlapX >= overlapY) {
                        if (dy > 0) {
                            //move the rectangle back to cover up the overlap
                            //before calling its display to prevent drawing
                            //object inside each other
                            r1.setY(r1.getY() + overlapY);
                            nonNoneCollision = "top";
                        } else {
                            r1.setY(r1.getY() - overlapY);
                            nonNoneCollision = "bottom";
                        }
                    } else {
                        if (dx > 0) {
                            r1.setX(r1.getX() + overlapX);
                            nonNoneCollision = "left";
                        } else {
                            r1.setX(r1.getX() - overlapX);
                            nonNoneCollision = "right";
                        }
                    }
                } else {
                    //collision failed on the y axis
                }
            } else {
                //collision failed on the x axis
            }
            r1.setCollisionSide(nonNoneCollision);
        }
    }

    void displayPositionData() {
        fill(255);
        textSize(15);
        String s = "\nvx:" + player.getVx() + "  vy:" + player.getVy() + "  \ncollision side:" + player.getCollisionSide();
        text(s, 20, 20);
    }

    @Override
    public void keyPressed() {
        if (key == CODED) {
            if (keyCode == LEFT)
                left = true;
            if (keyCode == RIGHT)
                right = true;
            if (keyCode == UP)
                up = true;
            if (keyCode == DOWN)
                down = true;
        }
        if (key == ' ') {
            up = true;
        }
    }

    @Override
    public void keyReleased() {
        if (key == CODED) {
            if (keyCode == LEFT)
                left = false;
            if (keyCode == RIGHT)
                right = false;
            if (keyCode == UP || keyCode == TAB)
                up = false;
            if (keyCode == DOWN)
                down = false;
        }
        if (key == ' ') {
            up = false;
        }
    }
}
