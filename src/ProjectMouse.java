import game_characters.PlayerMouseCharacter;
import platform.StandardPlatform;
import processing.core.PApplet;

public class ProjectMouse extends PApplet {

    boolean left, right, up, down, space;
    PlayerMouseCharacter player;
    StandardPlatform platform;

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

        player = new PlayerMouseCharacter(this);
        platform = new StandardPlatform(this, 300, 460, 200, 25, "safe");
    }

    @Override
    public void draw() {
        background(0);
        player.update(left, right, up, down, space);
        player.setCollisionSide(rectangleCollision(player, platform));
        player.display();
        platform.display();

        //for getting details on screen
        displayPositionData();
    }

    String rectangleCollision(PlayerMouseCharacter r1, StandardPlatform r2) {

        //Disable if the player cannot pass through platforms,
        //if enabled, the player can pass from below the platform
//        if (r1.getVy() < 0) {
//            return "none";
//        }

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
                        return "top";
                    } else {
                        r1.setY(r1.getY() - overlapY);
                        return "bottom";
                    }
                } else {
                    if(dx > 0) {
                        r1.setX(r1.getX() + overlapX);
                        return "left";
                    } else {
                        r1.setX(r1.getX() - overlapX);
                        return "right";
                    }
                }
            } else {
                //collision failed on the y axis
                return "none";
            }
        } else {
            //collision failed on the x axis
            return "none";
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
            if (keyCode == TAB)
                space = true;
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
            if (keyCode == TAB)
                space = false;
        }
    }
}
