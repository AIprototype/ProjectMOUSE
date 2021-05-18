package constants;

final public class Constants {
    public static final boolean DEBUG_MODE = false;
    public static final int LOADING_SPRITE_WIDTH = 80;
    public static final int LOADING_SPRITE_HEIGHT = 80;

    public static final int PLATFORM_WIDTH = 55;
    public static final int PLATFORM_HEIGHT = 35;
    public static final int PLAYER_WIDTH = 60;
    public static final int PLAYER_HEIGHT = 75;

    public static final int ENEMY_WIDTH = 80;
    public static final int ENEMY_HEIGHT = 35;

    public static final int SMART_ENEMY_WIDTH = 65;
    public static final int SMART_ENEMY_HEIGHT = 80;

    public static final float PLAYER_JUMP_FORCE = -10f;
    public static final float PLAYER_SPEED_LIMIT = 6f;
    public static final float PLAYER_FRICTION = 0.99f;
    public static final float PLAYER_BOUNCE = -0.0f;
    public static final float PLAYER_GRAVITY = 0.3f;

    public static final int CAM_MAX_X_GRID = 22;
    public static final int CAM_MAX_Y_GRID = 22; // increase to show more black space above

    public static final int BG_IMAGE_MAX_X_GRID = 45;
    public static final int BG_IMAGE_MAX_Y_GRID = 31;

    public static final int GAME_MAX_X_GRID = 2*BG_IMAGE_MAX_X_GRID;
    public static final int GAME_MAX_Y_GRID = 31;

    public static final int TIME_FOR_UNSTABLE_TO_DESTROY = 800;
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
    public static final int INITIAL_COST_MOVING_PLATFORM = 70; //the least preferred choice, hence a very high cost

    public static final int ELECTRICITY_GENERATOR_WIDTH = 55;
    public static final int ELECTRICITY_GENERATOR_HEIGHT = 55;

    public static final int ELECTRICITY_SPARK_WIDTH = 85;
    public static final int ELECTRICITY_SPARK_HEIGHT = 55;

    public static final int HALLOWEEN_COLLECTIBLE_POINTS = 15;
    public static final int ENEMY_DESTROYED_USING_BOLTS_POINTS = 25;
    public static final int POINTS_LOST_PER_MINUTE = -1;
    public static final int POINTS_GAINED_DESTROYING_CLONING_CONTAINERS = 30;
    public static final int POINTS_LOST_DUE_TO_MISSED_ENERGY_BOLTS = -5;

    public static float HEALTH_REDUCED_BY_ELECTRIC_SPARK_PLATFORM = -15;
    public static float HEALTH_REDUCED_BY_ENEMY_TOUCH = -10;
    public static float HEALTH_REDUCED_BY_GROUND_TOXIC = -65;

    public static final int TROPHY_WIDTH = 25;
    public static final int TROPHY_HEIGHT = 30;

    public static final int HEART_WIDTH = 35;
    public static final int HEART_HEIGHT = 30;

    public static final float BRONZE_MAX_SCORE = 155;
    public static final float SILVER_MAX_SCORE = 300;

    public static final String LOADING_STRING = "Loading..";
    public static final String PRESS_ENTER_STRING = "Press Enter to continue..";
    public static final String PRESS_ENTER_TO_PROCEED_STRING = "Press Enter to proceed";

    public static final int WELCOME_GAME_STATE = 1;
    public static final int GAME_STARTED_GAME_STATE = 2;
}
