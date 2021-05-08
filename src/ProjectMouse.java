import camera_classes.FrameObject;
import camera_classes.ImageObject;
import custom_exceptions.PlatformDimensionException;
import game_characters.PlayerMouseCharacter;
import game_characters.ZombieMouseCharacter;
import in_game_items.CloningContainers;
import in_game_items.InGameItemsBaseClass;
import platform.PlatformBaseClass;
import processing.core.PApplet;
import processing.core.PImage;
import status_pages.LoadingPage;

import java.util.ArrayList;
import java.util.PriorityQueue;

import static constants.Constants.*;

public class ProjectMouse extends PApplet {

    boolean left, right, up, down, space;
    PlayerMouseCharacter player;
    PriorityQueue<PlatformBaseClass> platformArray;
    ArrayList<InGameItemsBaseClass> collectableArray;
    ArrayList<ZombieMouseCharacter> enemyArray;
    int frames;
    PImage[] mouseSpriteImages;
    FrameObject camera, gameWorld;
    ImageObject backImage;
    PImage bgImage;
    GameEngine gameEngine;
    boolean isLoading;

    Timer firingTimer;
    ArrayList<EnergyBolt> energyBoltList;
    int posOfNextBulletToFire;

    //For loading Page
    LoadingPage loadingPage;

    public static void main(String[] args) {
        PApplet.main("ProjectMouse");
        System.out.println("Welcome to Project MOUSE !!");
    }

    @Override
    public void settings() {
        size(PLATFORM_WIDTH * CAM_MAX_X_GRID, PLATFORM_HEIGHT * CAM_MAX_Y_GRID);
        //fullScreen();
    }

    @Override
    public void setup() {
        loadingPage = new LoadingPage("Loading", "Please wait", this);
        thread("gameSetup");
    }

    public void gameSetup() throws Exception {
        //called in thread
        isLoading = true;

        left = false;
        right = false;
        up = false;
        down = false;
        space = false;

        //background image & camera
        bgImage = loadImage("background.png");
        backImage = new ImageObject(this, PLATFORM_WIDTH * BG_IMAGE_MAX_X_GRID, PLATFORM_HEIGHT * BG_IMAGE_MAX_Y_GRID, 0, 0, bgImage);
        gameWorld = new FrameObject(0, 0, PLATFORM_WIDTH * GAME_MAX_X_GRID, PLATFORM_HEIGHT * GAME_MAX_Y_GRID);
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

        //Initialise the GameEngine
        gameEngine = new GameEngine(this, player);
        //get level 1 platforms
        platformArray = gameEngine.createLevelOnePlatforms();
        //get level 1 collectables
        collectableArray = gameEngine.createLevelOneCollectables();
        //get level 1 enemies
        enemyArray = gameEngine.createLevelOneEnemies();
        //initialise firing timer
        firingTimer = gameEngine.getEnergyBoltTimerForLevelOne();
        firingTimer.start();
        //initialise energy bolts
        energyBoltList = gameEngine.createLevelOnePlayerEnergyBolts();
        posOfNextBulletToFire = 0;
        //set loading to false
        isLoading = false;
    }

    public void resetGame() throws Exception {
        //called in thread
        isLoading = true;

        left = false;
        right = false;
        up = false;
        down = false;
        space = false;

        //background image & camera
        bgImage = loadImage("background.png");
        backImage = new ImageObject(this, PLATFORM_WIDTH * BG_IMAGE_MAX_X_GRID, PLATFORM_HEIGHT * BG_IMAGE_MAX_Y_GRID, 0, 0, bgImage);
        gameWorld = new FrameObject(0, 0, PLATFORM_WIDTH * GAME_MAX_X_GRID, PLATFORM_HEIGHT * GAME_MAX_Y_GRID);
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

        //(Already initialised in setup, no change occurs here)
        //get level 1 platforms
        gameEngine.updatePlayer(player);
        platformArray = gameEngine.getPlatformArray();
        //get level 1 collectables
        collectableArray = gameEngine.getCollectableArray();
        //get level 1 enemies
        enemyArray = gameEngine.getEnemyArray();
        //initialise firing timer
        firingTimer = gameEngine.getEnergyBoltTimerForLevelOne();
        firingTimer.start();
        //initialise energy bolts
        energyBoltList = gameEngine.getPlayerEnergyBoltList();
        posOfNextBulletToFire = 0;
        //set loading to false
        isLoading = false;
    }

    @Override
    public void draw() {
        background(0);
        if (isLoading) {
//            background(0);
//            textSize(25);
//            text("Loading...", width / 2 - 50, height / 2);
            loadingPage.display();
        } else {
            player.update(left, right, up, down, gameWorld);

            //bullets
            if (space) {
                if(firingTimer.complete()) {
                    System.out.println("Firing");
                    energyBoltList.get(posOfNextBulletToFire).fire(player);
                    posOfNextBulletToFire = (posOfNextBulletToFire + 1) % energyBoltList.size();
                    firingTimer.start();
                }
            }
            for(EnergyBolt bolt : energyBoltList) {
                bolt.update(camera);
                //bolt enemy collision
                for(ZombieMouseCharacter enemy : enemyArray) {
                    if(!enemy.isDead() && enemyEnergyBoltCollision(bolt, enemy)) {
                        enemy.deathAnimation();
                        bolt.reset();
                    }
                }
                //bolt cloning container collision
                for(InGameItemsBaseClass item : collectableArray) {
                    if(item instanceof CloningContainers &&
                            !((CloningContainers)item).isDestroyed() &&
                            cloningContainerEnergyBoltCollision(bolt, ((CloningContainers)item))) {
                        ((CloningContainers)item).setDestroyed(true);
                        bolt.reset();
                    }
                }
            }

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
            //player.display();

            //display platforms
            for (PlatformBaseClass platform : platformArray) {
                try {
                    platform.display();
                } catch (PlatformDimensionException e) {
                    System.out.println(e.getMessage());
                }
            }

            //display collectables
            for (InGameItemsBaseClass collectable : collectableArray) {
                collectable.display();
            }

            //display enemies
            for (ZombieMouseCharacter enemy : enemyArray) {
                enemy.update(left, right, up, down, gameWorld);
                enemy.display();
            }

            //Player - Zombie collisions
            for (ZombieMouseCharacter enemy : enemyArray) {
                String collisionSide = playerZombieCollision(player, enemy);
                if (collisionSide.trim().equalsIgnoreCase("top")) {
                    enemy.deathAnimation();
                } else {
                    //enemy dies, but player looses lot of health
                }
            }

            //display energy bolts
            for(EnergyBolt bolt : energyBoltList) {
                bolt.display();
            }

            //for showing player on top of everything
            player.display();

            //the push and pop isolates the translation done
            //pops out the original stored state
            popMatrix();

            //for getting details on screen
            //doesnt move with the screen as it is after popMatrix()
            displayPositionData();
        }
    }

    void rectangleCollision(PlayerMouseCharacter r1, PriorityQueue<PlatformBaseClass> platformList) {
        //Disable if the player cannot pass through platforms,
        //if enabled, the player can pass from below the platform
//        if (r1.getVy() < 0) {
//            return "none";
//        }
        String nonNoneCollision = "none";
        for (PlatformBaseClass r2 : platformList) {
            if (!r2.isPlatformDestroyed() && !r1.isDead()) {
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
                        r1.setPlatformBeingUsed(r2); //collision detected with a platform
                        float overlapX = combinedHalfWidths - abs(dx);
                        float overlapY = combinedHalfHeights - abs(dy);
                        //collision happened on the axis with the smallest overlap
                        if (overlapX >= overlapY) {
                            if (dy > 0) {
                                //move the rectangle back to cover up the overlap
                                //before calling its display to prevent drawing
                                //object inside each other
                                r2.setPlayerOnPlatform(false);
                                r1.setY(r1.getY() + overlapY);
                                nonNoneCollision = "top";
                            } else {
                                //player is on top of platform,
                                //inform the platform class there is a player on top
                                r2.setPlayerOnPlatform(true);
                                r1.setY(r1.getY() - overlapY);
                                nonNoneCollision = "bottom";
                            }
                        } else {
                            if (dx > 0) {
                                r2.setPlayerOnPlatform(false);
                                r1.setX(r1.getX() + overlapX);
                                nonNoneCollision = "left";
                            } else {
                                r2.setPlayerOnPlatform(false);
                                r1.setX(r1.getX() - overlapX);
                                nonNoneCollision = "right";
                            }
                        }
                    } else {
                        //collision failed on the y axis
                        r2.setPlayerOnPlatform(false);
                        r1.setPlatformBeingUsed(null);
                    }
                } else {
                    //collision failed on the x axis
                    r2.setPlayerOnPlatform(false);
                    r1.setPlatformBeingUsed(null);
                }
            }
            r1.setCollisionSide(nonNoneCollision);
        }
    }

    String playerZombieCollision(PlayerMouseCharacter r2, ZombieMouseCharacter r1) {
        String playerHitZombieSide = "";
        if (!r2.isDead() && !r1.isDead()) {
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
                            //Zombie on TOP of another platform
                            playerHitZombieSide = "top";
                        } else {
                            playerHitZombieSide = "bottom";
                        }
                    } else {
                        if (dx > 0) {
                            playerHitZombieSide = "left";
                        } else {
                            playerHitZombieSide = "right";
                        }
                    }
                }  //collision failed on the y axis
            }  //collision failed on the x axis
        }
        return playerHitZombieSide;
    }

    boolean enemyEnergyBoltCollision(EnergyBolt r1, ZombieMouseCharacter r2) {
        float dx = (r1.getX() + r1.getW() / 2) - (r2.getX() + r2.getW() / 2);
        float dy = (r1.getY() + r1.getH() / 2) - (r2.getY() + r2.getH() / 2);
        float combinedHalfWidths = r1.getHalfWidth() + r2.getHalfWidth();
        float combinedHalfHeights = r1.getHalfHeight() + r2.getHalfHeight();
        if (abs(dx) < combinedHalfWidths) {
            //collision on x-axis
            if (abs(dy) < combinedHalfHeights) {
                //collision on y-axis
                return true;
            }
        }
        return false;
    }

    boolean cloningContainerEnergyBoltCollision(EnergyBolt r1, CloningContainers r2) {
        float dx = (r1.getX() + r1.getW() / 2) - (r2.getX() + r2.getW() / 2);
        float dy = (r1.getY() + r1.getH() / 2) - (r2.getY() + r2.getH() / 2);
        float combinedHalfWidths = r1.getHalfWidth() + r2.getHalfWidth();
        float combinedHalfHeights = r1.getHalfHeight() + r2.getHalfHeight();
        if (abs(dx) < combinedHalfWidths) {
            //collision on x-axis
            if (abs(dy) < combinedHalfHeights) {
                //collision on y-axis
                return true;
            }
        }
        return false;
    }

    void displayPositionData() {
        fill(255);
        textSize(15);
        String s = "\nvx:" + player.getVx()
                + "  vy:" + player.getVy()
                + "  \ncollision side:" + player.getCollisionSide()
                + "  \nFPS: " + frameRate;
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
            space = true;
        }
        if (key == '`') {
            thread("resetGame");
        }
    }

    @Override
    public void keyReleased() {
        if (key == CODED) {
            if (keyCode == LEFT)
                left = false;
            if (keyCode == RIGHT)
                right = false;
            if (keyCode == UP)
                up = false;
            if (keyCode == DOWN)
                down = false;
        }
        if (key == ' ') {
            space = false;
        }
    }
}
