package status_pages;

import processing.core.PApplet;
import processing.core.PImage;

import static constants.Constants.*;

public class VictoryPage {
    String victoryTitle;
    float victoryTitleWidth, victoryTitleHeight, victoryTitleTextSize;
    String totalScoreText;
    float totalScoreTextWidth, totalScoreTextHeight, totalScoreTextTextSize;
    String totalScoreValue;
    float totalScoreValueWidth, totalScoreValueHeight, totalScoreValueTextSize;
    PApplet pApplet;
    float x, y;
    PImage victoryLogo;
    PImage bronzeTrophy;
    PImage silverTrophy;
    PImage goldTrophy;

    public VictoryPage(PApplet pApplet, String victoryTitle, String totalScoreText) {
        this.pApplet = pApplet;
        this.victoryTitle = victoryTitle;
        this.totalScoreText = totalScoreText;
        this.victoryTitleTextSize = 40;
        this.totalScoreTextTextSize = 20;
        this.totalScoreValueTextSize = 35;
        this.x = pApplet.width / 2;
        this.y = pApplet.height / 2;
        this.victoryLogo = pApplet.loadImage("victory.png");
        victoryLogo.resize(600, 100);

        bronzeTrophy = pApplet.loadImage("bronze_trophy.png");
        bronzeTrophy.resize(TROPHY_WIDTH * 3, TROPHY_HEIGHT * 3);

        //silver trophy
        silverTrophy = pApplet.loadImage("silver_trophy.png");
        silverTrophy.resize(TROPHY_WIDTH * 3, TROPHY_HEIGHT * 3);

        //gold trophy
        goldTrophy = pApplet.loadImage("gold_trophy.png");
        goldTrophy.resize(TROPHY_WIDTH * 3, TROPHY_HEIGHT * 3);

        calculateStringParams();
    }

    public void setTotalScoreValue(String totalScoreValue) {
        this.totalScoreValue = totalScoreValue;
        pApplet.textSize(totalScoreValueTextSize);
        totalScoreValueWidth = pApplet.textWidth(totalScoreValue);
        float messageAscent = pApplet.textAscent();
        float messageDescent = pApplet.textDescent();
        totalScoreValueHeight = messageAscent + messageDescent;
    }

    private void calculateStringParams() {
        pApplet.textSize(victoryTitleTextSize);
        victoryTitleWidth = pApplet.textWidth(victoryTitle);
        float messageAscent = pApplet.textAscent();
        float messageDescent = pApplet.textDescent();
        victoryTitleHeight = messageAscent + messageDescent;

        pApplet.textSize(totalScoreTextTextSize);
        totalScoreTextWidth = pApplet.textWidth(totalScoreText);
        messageAscent = pApplet.textAscent();
        messageDescent = pApplet.textDescent();
        totalScoreTextHeight = messageAscent + messageDescent;
    }

    public void display() {
        pApplet.background(0);
        pApplet.image(victoryLogo, x - victoryLogo.width / 2, y - victoryLogo.height);

        if (Integer.parseInt(totalScoreValue) < BRONZE_MAX_SCORE) {
            pApplet.image(bronzeTrophy, x - bronzeTrophy.width / 2, y + 20);
        } else if (Integer.parseInt(totalScoreValue) < SILVER_MAX_SCORE && Integer.parseInt(totalScoreValue) >= BRONZE_MAX_SCORE) {
            pApplet.image(silverTrophy, x - silverTrophy.width / 2, y + 20);
        } else {
            pApplet.image(goldTrophy, x - goldTrophy.width / 2, y + 20);
        }

        pApplet.textSize(totalScoreTextTextSize);
        pApplet.fill(255, 255, 255);
        pApplet.text(totalScoreText, x - totalScoreTextWidth / 2, y + (victoryTitleHeight + totalScoreTextHeight + bronzeTrophy.height));

        pApplet.textSize(totalScoreValueTextSize);
        pApplet.fill(255, 255, 0);
        pApplet.text(totalScoreValue, x - totalScoreValueWidth / 2, y + (victoryTitleHeight + totalScoreTextHeight + totalScoreValueHeight + bronzeTrophy.height));
    }
}
