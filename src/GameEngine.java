import camera_classes.CameraHandlerClass;
import game_characters.PlayerMouseCharacter;
import game_characters.SmartZombieCharacter;
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
    PlayerMouseCharacter player;
    StandardPlatform startingPlatform;
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
    PImage[] electricity_generator_sprites;
    PImage[] electric_sparks_sprites;
    PImage[] door_opening_sprites;
    PImage[] right_smart_enemy_sprites;
    PImage[] left_smart_enemy_sprites;
    PImage bronze_trophy, silver_trophy, gold_trophy, health_icon;
    PriorityQueue<PlatformBaseClass> platformArray;
    ArrayList<InGameItemsBaseClass> collectableArray;
    ArrayList<CollectableHalloweenPumpkin> halloweenCollectibleList;
    ArrayList<CloningContainers> cloningContainerCollectibleList;
    ArrayList<ZombieMouseCharacter> enemyArray;
    ArrayList<EnergyBolt> playerEnergyBoltList;
    //Camera Handler Class
    CameraHandlerClass cameraHandlerClass;
    int userSelectedGameMode;

    public GameEngine(PApplet pApplet, PlayerMouseCharacter player, int userSelectedGameMode) {
        //loading platform sprites
        this.userSelectedGameMode = userSelectedGameMode;
        this.player = player;
        this.halloweenCollectibleList = new ArrayList<>();
        this.cloningContainerCollectibleList = new ArrayList<>();
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
        this.electricity_generator_sprites = new PImage[15];
        this.electric_sparks_sprites = new PImage[6];
        this.door_opening_sprites = new PImage[8];
        this.right_smart_enemy_sprites = new PImage[3];
        this.left_smart_enemy_sprites = new PImage[3];
        this.pApplet = pApplet;
        for (int i = 0; i < standardPlatformImages.length; ++i) {
            PImage std_img = pApplet.loadImage("platform" + PApplet.nf(i + 1, 4) + ".png");
            PImage uns_img = pApplet.loadImage("unstable_platform" + PApplet.nf(i + 1, 4) + ".png");
            std_img.resize(PLATFORM_WIDTH, PLATFORM_HEIGHT);
            uns_img.resize(PLATFORM_WIDTH, PLATFORM_HEIGHT);
            standardPlatformImages[i] = std_img;
            unstablePlatformImages[i] = uns_img;
        }
        for (int i = 0; i < wallSeparationPlatformImages.length; ++i) {
            String fileName = "wall_platform" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(PLATFORM_WIDTH, PLATFORM_HEIGHT);
            wallSeparationPlatformImages[i] = img;
        }
        //for enemy walk left
        for (int i = 0; i < enemyWalkLeft.length; ++i) {
            String fileName = "enemy_left_walk/black_rat_walk" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(ENEMY_WIDTH, ENEMY_HEIGHT);
            enemyWalkLeft[i] = img;
        }
        //for enemy walk right
        for (int i = 0; i < enemyWalkRight.length; ++i) {
            String fileName = "enemy_right_walk/enemy_right_walk" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(ENEMY_WIDTH, ENEMY_HEIGHT);
            enemyWalkRight[i] = img;
        }
        //enemy death left
        for (int i = 0; i < enemyDeathLeft.length; ++i) {
            String fileName = "black_mouse_death_left/black_mouse_death" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(ENEMY_WIDTH, ENEMY_HEIGHT);
            enemyDeathLeft[i] = img;
        }
        //enemy death right
        for (int i = 0; i < enemyDeathRight.length; ++i) {
            String fileName = "black_mouse_death_right/black_mouse_death" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(ENEMY_WIDTH, ENEMY_HEIGHT);
            enemyDeathRight[i] = img;
        }
        //for energy bolt red
        for (int i = 0; i < energyBoltRed.length; ++i) {
            String fileName = "energy_bolt_red/bolt" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize((int) (ENERGY_BOLT_WIDTH * 1.5), (int) (ENERGY_BOLT_HEIGHT * 1.5));
            energyBoltRed[i] = img;
        }
        //for console pc sprites
        for (int i = 0; i < consolePcSprites.length; ++i) {
            String fileName = "console_pc/console_" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(CONSOLE_PC_WIDTH, CONSOLE_PC_HEIGHT);
            consolePcSprites[i] = img;
        }
        //for cloning containers normal
        for (int i = 0; i < cloning_container_normal.length; ++i) {
            String fileName = "cloning_containers/blue_lab_tube_chamber_on_" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(CLONING_CONTAINER_WIDTH, CLONING_CONTAINER_HEIGHT);
            cloning_container_normal[i] = img;
        }
        //for cloning containers destroyed
        for (int i = 0; i < cloning_container_destroyed.length; ++i) {
            String fileName = "cloning_containers/blue_lab_tube_chamber_broken_" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(CLONING_CONTAINER_WIDTH, CLONING_CONTAINER_HEIGHT);
            cloning_container_destroyed[i] = img;
        }
        //for acid bubbling anim
        for (int i = 0; i < acid_bubbling_sprites.length; ++i) {
            String fileName = "red_acid_bubling/bubble_" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(ACID_BUBBLE_WIDTH, ACID_BUBBLE_HEIGHT);
            acid_bubbling_sprites[i] = img;
        }
        //for test tube acid breakage
        for (int i = 0; i < acid_test_tube_breakage_sprites.length; ++i) {
            String fileName = "red_test_tube_acid/__red_spill_bubbling_" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(ACID_BUBBLE_WIDTH, ACID_BUBBLE_HEIGHT);
            acid_test_tube_breakage_sprites[i] = img;
        }
        //for monster behind sprites
        for (int i = 0; i < monster_behind_sprites.length; ++i) {
            String fileName = "monster_behind_door/__monster_behind_door_door_opning_closing_" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(MONSTER_BEHIND_DOOR_WIDTH, MONSTER_BEHIND_DOOR_HEIGHT);
            monster_behind_sprites[i] = img;
        }
        //for electricity generator sprites
        for (int i = 0; i < electricity_generator_sprites.length; ++i) {
            String fileName = "electricity_generator/__electricity_bomb_about_to_explode_" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(ELECTRICITY_GENERATOR_WIDTH, ELECTRICITY_GENERATOR_HEIGHT);
            electricity_generator_sprites[i] = img;
        }
        //for electric sparks sprites
        for (int i = 0; i < electric_sparks_sprites.length; ++i) {
            String fileName = "electric_sparks/top_sparks_" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(ELECTRICITY_SPARK_WIDTH, ELECTRICITY_SPARK_HEIGHT);
            electric_sparks_sprites[i] = img;
        }
        //for door opening sprites
        for (int i = 0; i < door_opening_sprites.length; ++i) {
            String fileName = "door_open_close/lab_door_" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(MONSTER_BEHIND_DOOR_WIDTH, MONSTER_BEHIND_DOOR_HEIGHT);
            door_opening_sprites[i] = img;
        }
        //for right smart enemy sprites
        for (int i = 0; i < right_smart_enemy_sprites.length; ++i) {
            String fileName = "smart_enemy/right_smart_enemy" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(SMART_ENEMY_WIDTH, SMART_ENEMY_HEIGHT);
            right_smart_enemy_sprites[i] = img;
        }
        //for left smart enemy sprites
        for (int i = 0; i < left_smart_enemy_sprites.length; ++i) {
            String fileName = "smart_enemy/left_smart_enemy" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(SMART_ENEMY_WIDTH, SMART_ENEMY_HEIGHT);
            left_smart_enemy_sprites[i] = img;
        }

        //bronze trophy
        String fileName = "bronze_trophy.png";
        PImage img = pApplet.loadImage(fileName);
        img.resize(TROPHY_WIDTH, TROPHY_HEIGHT);
        bronze_trophy = img;

        //silver trophy
        fileName = "silver_trophy.png";
        img = pApplet.loadImage(fileName);
        img.resize(TROPHY_WIDTH, TROPHY_HEIGHT);
        silver_trophy = img;

        //gold trophy
        fileName = "gold_trophy.png";
        img = pApplet.loadImage(fileName);
        img.resize(TROPHY_WIDTH, TROPHY_HEIGHT);
        gold_trophy = img;

        //heart sprite
        fileName = "face_on_heart.png";
        img = pApplet.loadImage(fileName);
        img.resize(HEART_WIDTH, HEART_HEIGHT);
        health_icon = img;

        //for storing the minX and maxX of camera frame
        cameraHandlerClass = new CameraHandlerClass(0, pApplet.width);
    }

    public void updatePlayer(PlayerMouseCharacter player) {
        this.player = player;
    }

    public PriorityQueue<PlatformBaseClass> createLevelOnePlatforms() {
        platformArray.clear();
        ArrayList<PlatformBaseClass> platforms = new ArrayList<>();
        startingPlatform = new StandardPlatform(standardPlatformImages, pApplet,
                2 * PLATFORM_WIDTH, 28 * PLATFORM_HEIGHT,
                2 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe", INITIAL_COST_STANDARD_PLATFORM, cameraHandlerClass);
        startingPlatform.setCountOfItemsOnPlatform(INITIAL_COST_PLAYER_START_PLATFORM);
        //place player on the starting platform
        player.resetCharacterLocation(startingPlatform);
        platforms.add(startingPlatform);
        //rest of the platforms
        try {
            platforms.add(new StandardPlatform(standardPlatformImages, pApplet,
                    5 * PLATFORM_WIDTH, 26 * PLATFORM_HEIGHT,
                    3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe1", INITIAL_COST_STANDARD_PLATFORM, cameraHandlerClass));
            platforms.add(new StandardPlatform(standardPlatformImages, pApplet,
                    10 * PLATFORM_WIDTH, 24 * PLATFORM_HEIGHT,
                    3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe2", INITIAL_COST_STANDARD_PLATFORM, cameraHandlerClass));
            platforms.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                    13 * PLATFORM_WIDTH, 21 * PLATFORM_HEIGHT,
                    5 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe3", INITIAL_COST_UNSTABLE_PLATFORM, cameraHandlerClass));
            platforms.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                    9 * PLATFORM_WIDTH, 17 * PLATFORM_HEIGHT,
                    4 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe4", INITIAL_COST_UNSTABLE_PLATFORM, cameraHandlerClass));
            platforms.add(new StandardPlatform(standardPlatformImages, pApplet,
                    4 * PLATFORM_WIDTH, 14 * PLATFORM_HEIGHT,
                    4 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe5", INITIAL_COST_STANDARD_PLATFORM, cameraHandlerClass));
            platforms.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                    21 * PLATFORM_WIDTH, 19 * PLATFORM_HEIGHT,
                    5 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe6", INITIAL_COST_UNSTABLE_PLATFORM, cameraHandlerClass));
            platforms.add(new WallSeparationPlatform(wallSeparationPlatformImages, pApplet,
                    PLATFORM_WIDTH * 29, PLATFORM_HEIGHT * 16,
                    "safe7", null, cameraHandlerClass));
            platforms.add(new WallSeparationPlatform(wallSeparationPlatformImages, pApplet,
                    PLATFORM_WIDTH * 31, PLATFORM_HEIGHT * 28,
                    "safe8", null, cameraHandlerClass));
            platforms.add(new WallSeparationPlatform(wallSeparationPlatformImages, pApplet,
                    PLATFORM_WIDTH * 36, PLATFORM_HEIGHT * 25,
                    "safe9", null, cameraHandlerClass));
            platforms.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                    38 * PLATFORM_WIDTH, 21 * PLATFORM_HEIGHT,
                    2 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe10", INITIAL_COST_UNSTABLE_PLATFORM, cameraHandlerClass));
            platforms.add(new StandardPlatform(standardPlatformImages, pApplet,
                    44 * PLATFORM_WIDTH, 24 * PLATFORM_HEIGHT,
                    3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe11", INITIAL_COST_STANDARD_PLATFORM, cameraHandlerClass));
            platforms.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                    49 * PLATFORM_WIDTH, 21 * PLATFORM_HEIGHT,
                    3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe12", INITIAL_COST_UNSTABLE_PLATFORM, cameraHandlerClass));
            platforms.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                    45 * PLATFORM_WIDTH, 19 * PLATFORM_HEIGHT,
                    3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe13", INITIAL_COST_UNSTABLE_PLATFORM, cameraHandlerClass));
            platforms.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                    49 * PLATFORM_WIDTH, 15 * PLATFORM_HEIGHT,
                    3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe14", INITIAL_COST_UNSTABLE_PLATFORM, cameraHandlerClass));
            platforms.add(new StandardPlatform(standardPlatformImages, pApplet,
                    44 * PLATFORM_WIDTH, 14 * PLATFORM_HEIGHT,
                    3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe15", INITIAL_COST_STANDARD_PLATFORM, cameraHandlerClass));
            platforms.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                    48 * PLATFORM_WIDTH, 10 * PLATFORM_HEIGHT,
                    3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe16", INITIAL_COST_UNSTABLE_PLATFORM, cameraHandlerClass));
            platforms.add(new StandardPlatform(standardPlatformImages, pApplet,
                    43 * PLATFORM_WIDTH, 8 * PLATFORM_HEIGHT,
                    2 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe17", INITIAL_COST_STANDARD_PLATFORM, cameraHandlerClass));
            platforms.add(new StandardPlatform(standardPlatformImages, pApplet,
                    35 * PLATFORM_WIDTH, 5 * PLATFORM_HEIGHT,
                    4 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe18", INITIAL_COST_STANDARD_PLATFORM, cameraHandlerClass));

            //Creating a wall climb platform
            PlatformBaseClass temp_platform = new StandardPlatform(standardPlatformImages, pApplet,
                    58 * PLATFORM_WIDTH, 25 * PLATFORM_HEIGHT,
                    4 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe19", (INITIAL_COST_STANDARD_PLATFORM + 1), cameraHandlerClass);
            platforms.add(temp_platform);
            platforms.add(new WallSeparationPlatform(wallSeparationPlatformImages, pApplet,
                    PLATFORM_WIDTH * 60, PLATFORM_HEIGHT * 7,
                    "safe20", temp_platform, cameraHandlerClass));
            platforms.add(new StandardPlatform(standardPlatformImages, pApplet,
                    56 * PLATFORM_WIDTH, 29 * PLATFORM_HEIGHT,
                    8 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe21", INITIAL_COST_STANDARD_PLATFORM, cameraHandlerClass));
            platforms.add(new WallSeparationPlatform(wallSeparationPlatformImages, pApplet,
                    PLATFORM_WIDTH * 67, PLATFORM_HEIGHT * 9,
                    "safe22", null, cameraHandlerClass));

            //alternate electric path (If no wall climb used)
            platforms.add(new ElectricPlatform(unstablePlatformImages, pApplet,
                    54 * PLATFORM_WIDTH, 17 * PLATFORM_HEIGHT,
                    3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "semi-death23", electricity_generator_sprites, electric_sparks_sprites, cameraHandlerClass));

            //higher level platforms
            platforms.add(new StandardPlatform(standardPlatformImages, pApplet,
                    2 * PLATFORM_WIDTH, 5 * PLATFORM_HEIGHT,
                    6 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe24", INITIAL_COST_STANDARD_PLATFORM, cameraHandlerClass));

            //reference wall
            temp_platform = new StandardPlatform(standardPlatformImages, pApplet,
                    10 * PLATFORM_WIDTH, 11 * PLATFORM_HEIGHT,
                    5 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe25", (INITIAL_COST_STANDARD_PLATFORM + 1), cameraHandlerClass);
            platforms.add(temp_platform);
            platforms.add(new WallSeparationPlatform(wallSeparationPlatformImages, pApplet,
                    PLATFORM_WIDTH * 13, PLATFORM_HEIGHT * 4,
                    "safe26", temp_platform, cameraHandlerClass));

            //continuing from safe22, wall platform
            platforms.add(new StandardPlatform(standardPlatformImages, pApplet,
                    69 * PLATFORM_WIDTH, 9 * PLATFORM_HEIGHT,
                    9 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe27", INITIAL_COST_STANDARD_PLATFORM, cameraHandlerClass));
            temp_platform = new StandardPlatform(standardPlatformImages, pApplet,
                    78 * PLATFORM_WIDTH, 25 * PLATFORM_HEIGHT,
                    5 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe28", INITIAL_COST_STANDARD_PLATFORM, cameraHandlerClass);
            platforms.add(temp_platform);
            platforms.add(new WallSeparationPlatform(wallSeparationPlatformImages, pApplet,
                    78 * PLATFORM_WIDTH, PLATFORM_HEIGHT * 9,
                    "safe29", temp_platform, cameraHandlerClass));
            platforms.add(new ElectricPlatform(unstablePlatformImages, pApplet,
                    87 * PLATFORM_WIDTH, 8 * PLATFORM_HEIGHT,
                    3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "semi-death30", electricity_generator_sprites, electric_sparks_sprites, cameraHandlerClass));
            platforms.add(new StandardPlatform(standardPlatformImages, pApplet,
                    85 * PLATFORM_WIDTH, 13 * PLATFORM_HEIGHT,
                    5 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe31", INITIAL_COST_STANDARD_PLATFORM, cameraHandlerClass));
            platforms.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                    80 * PLATFORM_WIDTH, 17 * PLATFORM_HEIGHT,
                    3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe32", INITIAL_COST_STANDARD_PLATFORM, cameraHandlerClass));
            platforms.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                    86 * PLATFORM_WIDTH, 21 * PLATFORM_HEIGHT,
                    4 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe33", INITIAL_COST_STANDARD_PLATFORM, cameraHandlerClass));
            platforms.add(new WallSeparationPlatform(wallSeparationPlatformImages, pApplet,
                    73 * PLATFORM_WIDTH, PLATFORM_HEIGHT * 23,
                    "safe34", null, cameraHandlerClass));
            platforms.add(new ElectricPlatform(unstablePlatformImages, pApplet,
                    69 * PLATFORM_WIDTH, 28 * PLATFORM_HEIGHT,
                    4 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe35", electricity_generator_sprites, electric_sparks_sprites, cameraHandlerClass));
            platforms.add(new StandardMovingPlatforms(standardPlatformImages, pApplet,
                    77 * PLATFORM_WIDTH, 29 * PLATFORM_HEIGHT,
                    5 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe36", INITIAL_COST_MOVING_PLATFORM, cameraHandlerClass, userSelectedGameMode));
            //winning platform
            platforms.add(new ExitPlatform(standardPlatformImages, pApplet,
                    72 * PLATFORM_WIDTH, 18 * PLATFORM_HEIGHT,
                    4 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                    "safe36", INITIAL_COST_STANDARD_PLATFORM + 2, door_opening_sprites, true, cameraHandlerClass));

            //Ground death platform
            platforms.add(new GroundToxicPlatform(standardPlatformImages, pApplet,
                    PLATFORM_WIDTH, (float) (30 * PLATFORM_HEIGHT),
                    GAME_MAX_X_GRID * PLATFORM_WIDTH, 2 * PLATFORM_HEIGHT,
                    "death0", acid_test_tube_breakage_sprites, cameraHandlerClass));
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
        //clearing any previous values
        collectableArray.clear();
        halloweenCollectibleList.clear();
        cloningContainerCollectibleList.clear();

        collectableArray.add(new CollectableHalloweenPumpkin(pApplet, 14 * PLATFORM_WIDTH, PLATFORM_HEIGHT * 4 - COLLECTABLE_HEIGHT, cameraHandlerClass));
        collectableArray.add(new CollectableHalloweenPumpkin(pApplet, 10 * PLATFORM_WIDTH, PLATFORM_HEIGHT, cameraHandlerClass));
        collectableArray.add(new CollectableHalloweenPumpkin(pApplet, PLATFORM_WIDTH * 33, PLATFORM_HEIGHT * 13, cameraHandlerClass));
        collectableArray.add(new CollectableHalloweenPumpkin(pApplet, 43 * PLATFORM_WIDTH, PLATFORM_HEIGHT, cameraHandlerClass));
        collectableArray.add(new CollectableHalloweenPumpkin(pApplet, PLATFORM_WIDTH * 34, PLATFORM_HEIGHT, cameraHandlerClass));
        collectableArray.add(new CollectableHalloweenPumpkin(pApplet, 73 * PLATFORM_WIDTH, PLATFORM_HEIGHT * 23 - COLLECTABLE_HEIGHT, cameraHandlerClass));
        collectableArray.add(new CollectableHalloweenPumpkin(pApplet, 69 * PLATFORM_WIDTH, PLATFORM_HEIGHT * 25 - COLLECTABLE_HEIGHT, cameraHandlerClass));
        for (InGameItemsBaseClass item : collectableArray) {
            if (item instanceof CollectableHalloweenPumpkin) {
                halloweenCollectibleList.add((CollectableHalloweenPumpkin) item);
            }
        }
        //console pc
        ArrayList<ConsolePc> consolePcList = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            consolePcList.add(new ConsolePc(pApplet, getPlatformToPlaceItem(true, false, true, false), consolePcSprites, cameraHandlerClass));
        }
        collectableArray.addAll(consolePcList);

        //Cloning container for every console PC
        for (ConsolePc pc : consolePcList) {
            CloningContainers ccn = new CloningContainers(pApplet,
                    pc.getPlatformToPlace(),
                    cloning_container_normal,
                    cloning_container_destroyed,
                    pc, cameraHandlerClass);
            collectableArray.add(ccn);
            cloningContainerCollectibleList.add(ccn);
        }
        //Adding a monster behind door game item
        collectableArray.add(new MonsterBehindDoor(pApplet, monster_behind_sprites, getPlatformToPlaceItem(false, true, true, false), player, cameraHandlerClass));
        collectableArray.add(new MonsterBehindDoor(pApplet, monster_behind_sprites, getPlatformToPlaceItem(false, true, true, false), player, cameraHandlerClass));
        collectableArray.add(new MonsterBehindDoor(pApplet, monster_behind_sprites, getPlatformToPlaceItem(false, true, true, false), player, cameraHandlerClass));
        return collectableArray;
    }

    public ArrayList<ZombieMouseCharacter> createLevelOneEnemies() throws Exception {
        //clearing previous values
        enemyArray.clear();
        //Normal enemies
        int numOfEnemies = 7;
        if (userSelectedGameMode == EXTREME_DIFFICULTY_MODE) {
            numOfEnemies *= 2;
        }
        for (int i = 0; i < numOfEnemies; ++i) {
            if (i % 2 == 0) {
                enemyArray.add(new ZombieMouseCharacter(
                        getPlatformToPlaceItem(true, false, false, true),
                        ENEMY_WIDTH,
                        ENEMY_HEIGHT,
                        pApplet,
                        null,
                        enemyWalkRight,
                        enemyWalkLeft, enemyDeathLeft, enemyDeathRight, cameraHandlerClass));
            } else {
                enemyArray.add(new SmartZombieCharacter(
                        getPlatformToPlaceItem(true, false, false, true),
                        SMART_ENEMY_WIDTH,
                        SMART_ENEMY_HEIGHT,
                        pApplet,
                        null,
                        right_smart_enemy_sprites,
                        left_smart_enemy_sprites, enemyDeathLeft, enemyDeathRight, cameraHandlerClass));
            }
        }
        return enemyArray;
    }

    public ArrayList<EnergyBolt> createLevelOnePlayerEnergyBolts() {
        playerEnergyBoltList.clear();
        int numOfBolts = 3;
        if(userSelectedGameMode == EXTREME_DIFFICULTY_MODE) {
            numOfBolts = 2;
        }
        for (int i = 0; i < numOfBolts; ++i) {
            playerEnergyBoltList.add(new EnergyBolt(pApplet, energyBoltRed));
        }
        return playerEnergyBoltList;
    }

    public Timer getEnergyBoltTimerForLevelOne() {
        return new Timer(pApplet, 200);
    }

    public PApplet getpApplet() {
        return pApplet;
    }

    public PImage getBronze_trophy() {
        return bronze_trophy;
    }

    public PImage getSilver_trophy() {
        return silver_trophy;
    }

    public PImage getGold_trophy() {
        return gold_trophy;
    }

    public PImage getHealth_icon() {
        return health_icon;
    }

    public ArrayList<CollectableHalloweenPumpkin> getHalloweenCollectibleList() {
        return halloweenCollectibleList;
    }

    public ArrayList<CloningContainers> getCloningContainerCollectibleList() {
        return cloningContainerCollectibleList;
    }

    public PImage[] getEnergyBoltRed() {
        return energyBoltRed;
    }

    public CameraHandlerClass getCameraHandlerClass() {
        return cameraHandlerClass;
    }
}
