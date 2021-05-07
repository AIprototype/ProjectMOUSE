package constants;

final public class Constants {
    public static final boolean DEBUG_MODE = false;

    public static final int PLATFORM_WIDTH = 55;
    public static final int PLATFORM_HEIGHT = 35;
    public static final int PLAYER_WIDTH = 60;
    public static final int PLAYER_HEIGHT = 75;

    public static final int ENEMY_WIDTH = 80;
    public static final int ENEMY_HEIGHT = 35;

    public static final float PLAYER_JUMP_FORCE = -10f;
    public static final float PLAYER_SPEED_LIMIT = 6f;
    public static final float PLAYER_FRICTION = 0.99f;
    public static final float PLAYER_BOUNCE = -0.2f;
    public static final float PLAYER_GRAVITY = 0.3f;

    public static final int CAM_MAX_X_GRID = 22;
    public static final int CAM_MAX_Y_GRID = 22; // increase to show more black space above

    public static final int BG_IMAGE_MAX_X_GRID = 45;
    public static final int BG_IMAGE_MAX_Y_GRID = 22;

    public static final int GAME_MAX_X_GRID = 2*BG_IMAGE_MAX_X_GRID;
    public static final int GAME_MAX_Y_GRID = 24;

    public static final int TIME_FOR_UNSTABLE_TO_DESTROY = 1500;
    public static final int TIME_FOR_UNSTABLE_TO_RECONSTRUCT = 1500;

    public static final int COLLECTABLE_WIDTH = 45;
    public static final int COLLECTABLE_HEIGHT = 45;

    public static final int CONSOLE_PC_WIDTH = 45;
    public static final int CONSOLE_PC_HEIGHT = 65;

    public static final int CLONING_CONTAINER_WIDTH = 60;
    public static final int CLONING_CONTAINER_HEIGHT = 95;

    public static final int ACID_BUBBLE_WIDTH = PLATFORM_WIDTH * 2;
    public static final int ACID_BUBBLE_HEIGHT = PLATFORM_HEIGHT * 2;

    public static final int MONSTER_BEHIND_DOOR_WIDTH = 100;
    public static final int MONSTER_BEHIND_DOOR_HEIGHT = 115;

    public static final int ENERGY_BOLT_WIDTH = 45;
    public static final int ENERGY_BOLT_HEIGHT = 20;

    public static final int INITIAL_COST_STANDARD_PLATFORM = 0; //the first goto choice to place items
    public static final int INITIAL_COST_UNSTABLE_PLATFORM = 1; //to make sure unstable platform gets filled only after standard platforms are filled
    public static final int INITIAL_COST_TOXIC_PLATFORM = 10; //the least preferred choice, hence a very high cost
    public static final int INITIAL_COST_PLAYER_START_PLATFORM = 3; //the least preferred choice, hence a very high cost
    public static final int INITIAL_COST_WALL_PLATFORM = 2; //the least preferred choice, hence a very high cost

    public static final int ELECTRICITY_GENERATOR_WIDTH = 55;
    public static final int ELECTRICITY_GENERATOR_HEIGHT = 55;

    public static final int ELECTRICITY_SPARK_WIDTH = 85;
    public static final int ELECTRICITY_SPARK_HEIGHT = 55;
}
