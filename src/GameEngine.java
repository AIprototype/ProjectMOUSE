import game_characters.PlayerMouseCharacter;
import game_characters.ZombieMouseCharacter;
import in_game_items.*;
import platform.*;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import static constants.Constants.*;

public class GameEngine {
    //Platform sprite images
    PApplet pApplet;
    final PlayerMouseCharacter player;
    PImage[] standardPlatformImages;
    PImage[] unstablePlatformImages;
    PImage[] wallSeparationPlatformImages;
    PImage[] enemyWalkLeft;
    PImage[] enemyWalkRight;
    PImage[] enemyDeathLeft;
    PImage[] enemyDeathRight;
    PImage[] energyBoltRed;
    PImage[] consolePcSprites;
    PImage[] cloning_container_normal;
    PImage[] cloning_container_destroyed;
    PImage[] acid_bubbling_sprites;
    PImage[] acid_test_tube_breakage_sprites;
    PImage[] monster_behind_sprites;
    PriorityQueue<PlatformBaseClass> platformArray;
    ArrayList<InGameItemsBaseClass> collectableArray;
    ArrayList<ZombieMouseCharacter> enemyArray;
    ArrayList<EnergyBolt> playerEnergyBoltList;

    public GameEngine(PApplet pApplet, PlayerMouseCharacter player) {
        //loading platform sprites
        this.player = player;
        this.platformArray = new PriorityQueue<>();
        this.collectableArray = new ArrayList<>();
        this.enemyArray = new ArrayList<>();
        this.playerEnergyBoltList = new ArrayList<>();
        this.standardPlatformImages = new PImage[3];
        this.unstablePlatformImages = new PImage[3];
        this.wallSeparationPlatformImages = new PImage[4];
        this.enemyWalkLeft = new PImage[12];
        this.enemyWalkRight = new PImage[12];
        this.enemyDeathLeft = new PImage[4];
        this.enemyDeathRight = new PImage[4];
        this.energyBoltRed = new PImage[6];
        this.consolePcSprites = new PImage[5];
        this.cloning_container_normal = new PImage[11];
        this.cloning_container_destroyed = new PImage[4];
        this.acid_bubbling_sprites = new PImage[7];
        this.acid_test_tube_breakage_sprites = new PImage[20];
        this.monster_behind_sprites = new PImage[15];
        this.pApplet = pApplet;
        for (int i = 0; i < 3; ++i) {
            PImage std_img = pApplet.loadImage("platform" + PApplet.nf(i + 1, 4) + ".png");
            PImage uns_img = pApplet.loadImage("unstable_platform" + PApplet.nf(i + 1, 4) + ".png");
            std_img.resize(PLATFORM_WIDTH, PLATFORM_HEIGHT);
            uns_img.resize(PLATFORM_WIDTH, PLATFORM_HEIGHT);
            standardPlatformImages[i] = std_img;
            unstablePlatformImages[i] = uns_img;
        }
        for (int i = 0; i < 4; ++i) {
            String fileName = "wall_platform" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(PLATFORM_WIDTH, PLATFORM_HEIGHT);
            wallSeparationPlatformImages[i] = img;
        }
        //for enemy walk left
        for (int i = 0; i < 12; ++i) {
            String fileName = "enemy_left_walk/black_rat_walk" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(ENEMY_WIDTH, ENEMY_HEIGHT);
            enemyWalkLeft[i] = img;
        }
        //for enemy walk right
        for (int i = 0; i < 12; ++i) {
            String fileName = "enemy_right_walk/enemy_right_walk" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(ENEMY_WIDTH, ENEMY_HEIGHT);
            enemyWalkRight[i] = img;
        }
        //enemy death left
        for (int i = 0; i < 4; ++i) {
            String fileName = "black_mouse_death_left/black_mouse_death" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(ENEMY_WIDTH, ENEMY_HEIGHT);
            enemyDeathLeft[i] = img;
        }
        //enemy death right
        for (int i = 0; i < 4; ++i) {
            String fileName = "black_mouse_death_right/black_mouse_death" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(ENEMY_WIDTH, ENEMY_HEIGHT);
            enemyDeathRight[i] = img;
        }
        //for energy bolt red
        for (int i = 0; i < 6; ++i) {
            String fileName = "energy_bolt_red/bolt" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize((int) (ENERGY_BOLT_WIDTH * 1.5), (int) (ENERGY_BOLT_HEIGHT * 1.5));
            energyBoltRed[i] = img;
        }
        //for console pc sprites
        for (int i = 0; i < 5; ++i) {
            String fileName = "console_pc/console_" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(CONSOLE_PC_WIDTH, CONSOLE_PC_HEIGHT);
            consolePcSprites[i] = img;
        }
        //for cloning containers normal
        for (int i = 0; i < 11; ++i) {
            String fileName = "cloning_containers/blue_lab_tube_chamber_on_" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(CLONING_CONTAINER_WIDTH, CLONING_CONTAINER_HEIGHT);
            cloning_container_normal[i] = img;
        }
        //for cloning containers destroyed
        for (int i = 0; i < 4; ++i) {
            String fileName = "cloning_containers/blue_lab_tube_chamber_broken_" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(CLONING_CONTAINER_WIDTH, CLONING_CONTAINER_HEIGHT);
            cloning_container_destroyed[i] = img;
        }
        //for acid bubbling anim
        for (int i = 0; i < 7; ++i) {
            String fileName = "red_acid_bubling/bubble_" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(ACID_BUBBLE_WIDTH, ACID_BUBBLE_HEIGHT);
            acid_bubbling_sprites[i] = img;
        }
        //for test tube acid breakage
        for (int i = 0; i < 20; ++i) {
            String fileName = "red_test_tube_acid/__red_spill_bubbling_" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(ACID_BUBBLE_WIDTH, ACID_BUBBLE_HEIGHT);
            acid_test_tube_breakage_sprites[i] = img;
        }
        //for monster behind sprites
        for (int i = 0; i < 15; ++i) {
            String fileName = "monster_behind_door/__monster_behind_door_door_opning_closing_" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(MONSTER_BEHIND_DOOR_WIDTH, MONSTER_BEHIND_DOOR_HEIGHT);
            monster_behind_sprites[i] = img;
        }
    }

    public PriorityQueue<PlatformBaseClass> createLevelOnePlatforms() {
        ArrayList<PlatformBaseClass> platforms = new ArrayList<>();
        StandardPlatform starterPlatform = new StandardPlatform(standardPlatformImages, pApplet,
                2 * PLATFORM_WIDTH, 17 * PLATFORM_HEIGHT,
                2 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe");
        starterPlatform.setCountOfItemsOnPlatform(INITIAL_COST_PLAYER_START_PLATFORM);
        //place player on the starting platform
        player.resetCharacterLocation(starterPlatform);
        platforms.add(starterPlatform);
        //rest of the platforms
        try {
            platforms.add(new StandardPlatform(standardPlatformImages, pApplet,
                    5 * PLATFORM_WIDTH, 15 * PLATFORM_HEIGHT,
                    3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe1"));
            platforms.add(new StandardPlatform(standardPlatformImages, pApplet,
                    10 * PLATFORM_WIDTH, 13 * PLATFORM_HEIGHT,
                    3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe2"));
            platforms.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                    13 * PLATFORM_WIDTH, 10 * PLATFORM_HEIGHT,
                    5 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe3"));
            platforms.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                    9 * PLATFORM_WIDTH, 6 * PLATFORM_HEIGHT,
                    4 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe4"));
            platforms.add(new StandardPlatform(standardPlatformImages, pApplet,
                    4 * PLATFORM_WIDTH, 3 * PLATFORM_HEIGHT,
                    4 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe5"));
            platforms.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                    21 * PLATFORM_WIDTH, 8 * PLATFORM_HEIGHT,
                    5 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe6"));
            platforms.add(new WallSeparationPlatform(wallSeparationPlatformImages, pApplet,
                    PLATFORM_WIDTH * 29, PLATFORM_HEIGHT * 5,
                    "safe7", null));
            platforms.add(new WallSeparationPlatform(wallSeparationPlatformImages, pApplet,
                    PLATFORM_WIDTH * 31, PLATFORM_HEIGHT * 17,
                    "safe8", null));
            platforms.add(new WallSeparationPlatform(wallSeparationPlatformImages, pApplet,
                    PLATFORM_WIDTH * 36, PLATFORM_HEIGHT * 14,
                    "safe9", null));
            platforms.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                    38 * PLATFORM_WIDTH, 10 * PLATFORM_HEIGHT,
                    2 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe10"));
            platforms.add(new StandardPlatform(standardPlatformImages, pApplet,
                    44 * PLATFORM_WIDTH, 13 * PLATFORM_HEIGHT,
                    3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe11"));
            platforms.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                    49 * PLATFORM_WIDTH, 10 * PLATFORM_HEIGHT,
                    3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe12"));
            platforms.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                    45 * PLATFORM_WIDTH, 8 * PLATFORM_HEIGHT,
                    3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe13"));
            platforms.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                    49 * PLATFORM_WIDTH, 4 * PLATFORM_HEIGHT,
                    3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe14"));
            platforms.add(new StandardPlatform(standardPlatformImages, pApplet,
                    44 * PLATFORM_WIDTH, 3 * PLATFORM_HEIGHT,
                    3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe15"));

            //Creating a wall climb platform
            PlatformBaseClass temp_platform = new StandardPlatform(standardPlatformImages, pApplet,
                    58 * PLATFORM_WIDTH, 15 * PLATFORM_HEIGHT,
                    4 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe16");
            platforms.add(temp_platform);
            platforms.add(new WallSeparationPlatform(wallSeparationPlatformImages, pApplet,
                    PLATFORM_WIDTH * 60, PLATFORM_HEIGHT * 3,
                    "safe17", temp_platform));
            platforms.add(new StandardPlatform(standardPlatformImages, pApplet,
                    56 * PLATFORM_WIDTH, 18 * PLATFORM_HEIGHT,
                    8 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe18"));
            platforms.add(new WallSeparationPlatform(wallSeparationPlatformImages, pApplet,
                    PLATFORM_WIDTH * 65, PLATFORM_HEIGHT * 5,
                    "safe19", null));

            //Ground death platform
            platforms.add(new GroundToxicPlatform(standardPlatformImages, pApplet,
                    PLATFORM_WIDTH, (float) (19.5 * PLATFORM_HEIGHT),
                    GAME_MAX_X_GRID * PLATFORM_WIDTH, 2 * PLATFORM_HEIGHT,
                    "death0", acid_test_tube_breakage_sprites));
            //Shuffle for randomness
            Collections.shuffle(platforms);
            platformArray.addAll(platforms);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
        return platformArray;
    }

    private PlatformBaseClass getPlatformToPlaceItem(boolean anyPlatform, boolean standardOnly, boolean itemPlacement, boolean enemyPlacement) {
        PlatformBaseClass platformToReturn = null;
        if (anyPlatform) {
            PlatformBaseClass platformToPlace = platformArray.poll(); //priority queue, always gives me platform with the least number of items
            if (platformToPlace != null) {
                if (itemPlacement)
                    platformToPlace.incrementCountOfItemsOnPlatform(); //increment the count cost
                else
                    platformToPlace.incrementCountOfEnemiesOnPlatform(); //increment by number of enemies on platform
                platformArray.offer(platformToPlace); //adding it back to the queue with modified costs
            }
            platformToReturn = platformToPlace;
        } else if (standardOnly) {
            ArrayList<PlatformBaseClass> polledPlatforms = new ArrayList<>();
            PlatformBaseClass platformToPlace = null;
            while (true) {
                platformToPlace = platformArray.poll();
                if (platformToPlace instanceof StandardPlatform) {
                    polledPlatforms.add(platformToPlace);
                    break;
                } else {
                    polledPlatforms.add(platformToPlace);
                }
            }
            //while loop broken, because we found a standard platform with relatively lower item count
            platformArray.addAll(polledPlatforms);
            if (itemPlacement)
                platformToPlace.incrementCountOfItemsOnPlatform(); //increment the count cost
            else
                platformToPlace.incrementCountOfEnemiesOnPlatform(); //increment by number of enemies on platform
            platformToReturn = platformToPlace;
        }
        return platformToReturn;
    }

    public ArrayList<InGameItemsBaseClass> createLevelOneCollectables() {
        //halloween collectible
        collectableArray.add(new CollectableHalloweenPumpkin(pApplet, 3 * PLATFORM_WIDTH, 2 * PLATFORM_HEIGHT));
        collectableArray.add(new CollectableHalloweenPumpkin(pApplet, PLATFORM_WIDTH * 33, PLATFORM_HEIGHT * 13));
        collectableArray.add(new CollectableHalloweenPumpkin(pApplet, 43 * PLATFORM_WIDTH, PLATFORM_HEIGHT));
        collectableArray.add(new CollectableHalloweenPumpkin(pApplet, PLATFORM_WIDTH * 34, PLATFORM_HEIGHT));
        //console pc
        ArrayList<ConsolePc> consolePcList = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            consolePcList.add(new ConsolePc(pApplet, getPlatformToPlaceItem(true, false, true, false), consolePcSprites));
        }
        collectableArray.addAll(consolePcList);

        //Cloning container for every console PC
        for (ConsolePc pc : consolePcList) {
            collectableArray.add(new CloningContainers(pApplet,
                    pc.getPlatformToPlace(),
                    cloning_container_normal,
                    cloning_container_destroyed,
                    pc));
        }
        //Adding a monster behind door game item
        collectableArray.add(new MonsterBehindDoor(pApplet, monster_behind_sprites, getPlatformToPlaceItem(false, true, true, false)));
        return collectableArray;
    }

    public ArrayList<ZombieMouseCharacter> createLevelOneEnemies() throws Exception {
        //Normal enemies
        for (int i = 0; i < 5; ++i) {
            enemyArray.add(new ZombieMouseCharacter(
                    getPlatformToPlaceItem(true, false, false, true),
                    ENEMY_WIDTH,
                    ENEMY_HEIGHT,
                    pApplet,
                    null,
                    enemyWalkRight,
                    enemyWalkLeft, enemyDeathLeft, enemyDeathRight));
        }
        return enemyArray;
    }

    public ArrayList<EnergyBolt> createLevelOnePlayerEnergyBolts() {
        for (int i = 0; i < 2; ++i) {
            playerEnergyBoltList.add(new EnergyBolt(pApplet, energyBoltRed));
        }
        return playerEnergyBoltList;
    }

    public Timer getEnergyBoltTimerForLevelOne() {
        return new Timer(pApplet, 200);
    }
}
