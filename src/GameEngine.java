import in_game_items.CollectableHalloweenPumpkin;
import in_game_items.InGameItemsBaseClass;
import platform.PlatformBaseClass;
import platform.StandardPlatform;
import platform.UnstablePlatform;
import platform.WallSeparationPlatform;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

import static constants.Constants.PLATFORM_HEIGHT;
import static constants.Constants.PLATFORM_WIDTH;

public class GameEngine {
    //Platform sprite images
    PApplet pApplet;
    PImage[] standardPlatformImages;
    PImage[] unstablePlatformImages;
    PImage[] wallSeparationPlatformImages;
    ArrayList<PlatformBaseClass> platformArray;
    ArrayList<InGameItemsBaseClass> collectableArray;

    public GameEngine(PApplet pApplet) {
        //loading platform sprites
        this.platformArray = new ArrayList<>();
        this.collectableArray = new ArrayList<>();
        this.standardPlatformImages = new PImage[3];
        this.unstablePlatformImages = new PImage[3];
        this.wallSeparationPlatformImages = new PImage[4];
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
    }

    public ArrayList<PlatformBaseClass> createLevelOnePlatforms() {
        platformArray.add(new StandardPlatform(standardPlatformImages, pApplet,
                PLATFORM_WIDTH, 17 * PLATFORM_HEIGHT,
                2 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new StandardPlatform(standardPlatformImages, pApplet,
                4 * PLATFORM_WIDTH, 15 * PLATFORM_HEIGHT,
                3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new StandardPlatform(standardPlatformImages, pApplet,
                9 * PLATFORM_WIDTH, 13 * PLATFORM_HEIGHT,
                3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                12 * PLATFORM_WIDTH, 10 * PLATFORM_HEIGHT,
                5 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                8 * PLATFORM_WIDTH, 6 * PLATFORM_HEIGHT,
                4 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new StandardPlatform(standardPlatformImages, pApplet,
                3 * PLATFORM_WIDTH, 3 * PLATFORM_HEIGHT,
                4 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                20 * PLATFORM_WIDTH, 8 * PLATFORM_HEIGHT,
                5 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new WallSeparationPlatform(wallSeparationPlatformImages, pApplet,
                PLATFORM_WIDTH * 28, PLATFORM_HEIGHT*5,
                "safe"));
        platformArray.add(new WallSeparationPlatform(wallSeparationPlatformImages, pApplet,
                PLATFORM_WIDTH * 30, PLATFORM_HEIGHT*17,
                "safe"));
        platformArray.add(new WallSeparationPlatform(wallSeparationPlatformImages, pApplet,
                PLATFORM_WIDTH * 35, PLATFORM_HEIGHT*14,
                "safe"));
        platformArray.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                37 * PLATFORM_WIDTH, 10 * PLATFORM_HEIGHT,
                2 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new StandardPlatform(standardPlatformImages, pApplet,
                43 * PLATFORM_WIDTH, 13 * PLATFORM_HEIGHT,
                3 * PLATFORM_WIDTH, 2 * PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                48 * PLATFORM_WIDTH, 10 * PLATFORM_HEIGHT,
                3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                44 * PLATFORM_WIDTH, 8 * PLATFORM_HEIGHT,
                3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                48 * PLATFORM_WIDTH, 4 * PLATFORM_HEIGHT,
                3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new StandardPlatform(standardPlatformImages, pApplet,
                43 * PLATFORM_WIDTH, 2 * PLATFORM_HEIGHT,
                3 * PLATFORM_WIDTH, 2 * PLATFORM_HEIGHT,
                "safe"));
        return platformArray;
    }

    public ArrayList<InGameItemsBaseClass> createLevelOneCollectables() {
        collectableArray.add(new CollectableHalloweenPumpkin(pApplet, 3 * PLATFORM_WIDTH, 2 * PLATFORM_HEIGHT));
        collectableArray.add(new CollectableHalloweenPumpkin(pApplet, PLATFORM_WIDTH * 30, PLATFORM_HEIGHT*16));
        collectableArray.add(new CollectableHalloweenPumpkin(pApplet, 43 * PLATFORM_WIDTH, PLATFORM_HEIGHT));
        collectableArray.add(new CollectableHalloweenPumpkin(pApplet, PLATFORM_WIDTH * 34, PLATFORM_HEIGHT));
        return collectableArray;
    }

    public ArrayList<PlatformBaseClass> getPlatformArray() {
        return platformArray;
    }
}
