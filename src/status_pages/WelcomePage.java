package status_pages;

import processing.core.PApplet;
import status_pages.models.MainMenuOptionsModel;

import java.util.ArrayList;

public class WelcomePage {
    String gameTitle;
    float gameTitleWidth, gameTitleHeight, gameTitleTextSize;
    ArrayList<MainMenuOptionsModel> gameOptions;
    String gameVer;
    PApplet pApplet;
    float x, y;

    public WelcomePage(PApplet pApplet, String gameTitle, ArrayList<MainMenuOptionsModel> gameOptions, String gameVer) {
        this.pApplet = pApplet;
        this.gameTitle = gameTitle;
        this.gameOptions = gameOptions;
        this.gameVer = gameVer;
        this.gameTitleTextSize = 20;
        this.x = pApplet.width / 2;
        this.y = pApplet.height / 2;
        calculateStringParams(gameTitleTextSize);
    }

    private void calculateStringParams(float gameTitleTextSize) {
        pApplet.textSize(gameTitleTextSize);
        this.gameTitleWidth = pApplet.textWidth(gameTitle);
        float messageAscent = pApplet.textAscent();
        float messageDescent = pApplet.textDescent();
        this.gameTitleHeight = messageAscent + messageDescent;
    }

    public void display() {
        pApplet.background(0);
        pApplet.textSize(gameTitleTextSize);
        pApplet.fill(255, 255, 255);
        pApplet.text(gameTitle, x - gameTitleWidth/2, y);
//        pApplet.textSize(messageTextSize);
//        pApplet.fill(123, 123, 123);
//        pApplet.text(message, x - messageWidth/2, y + titleHeight);
    }
}
