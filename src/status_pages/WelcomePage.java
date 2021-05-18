package status_pages;

import processing.core.PApplet;
import processing.core.PImage;
import status_pages.models.MainMenuOptionsModel;

import java.util.ArrayList;

import static constants.Constants.*;

public class WelcomePage {
    String gameTitle;
    float gameTitleWidth, gameTitleHeight, gameTitleTextSize;
    ArrayList<MainMenuOptionsModel> gameOptions;
    String gameVer;
    PApplet pApplet;
    float x, y;
    float gameOptionTextSize;
    private int userSelectedGameMode;
    private int currSelectedPos;
    private PImage bgImage;

    public WelcomePage(PApplet pApplet, String gameTitle, String gameVer) {
        this.pApplet = pApplet;
        this.gameTitle = gameTitle;
        this.gameVer = gameVer;
        this.gameTitleTextSize = 45;
        this.x = pApplet.width / 2;
        this.y = pApplet.height / 2;
        this.gameOptionTextSize = 17;
        this.userSelectedGameMode = -1;
        this.gameOptions = new ArrayList<>();
        calculateStringParams(gameTitleTextSize);
        initGameOptions(gameOptionTextSize);

        bgImage = pApplet.loadImage("background.png");
        bgImage.resize(pApplet.width, pApplet.height);
    }

    private void initGameOptions(float gameOptionTextSize) {
        //normal mode start
        String option = "Normal Mode";
        pApplet.textSize(gameOptionTextSize);
        float optionWidth = pApplet.textWidth(option);
        float optionAscent = pApplet.textAscent();
        float optionDescent = pApplet.textDescent();
        float optionHeight = optionAscent + optionDescent;
        gameOptions.add(new MainMenuOptionsModel(option, optionWidth, optionHeight, NORMAL_MODE_OPTION_ID, true));
        this.userSelectedGameMode = NORMAL_MODE_OPTION_ID;
        this.currSelectedPos = 0;


        option = "Bouncy Mode";
        pApplet.textSize(gameOptionTextSize);
        optionWidth = pApplet.textWidth(option);
        optionAscent = pApplet.textAscent();
        optionDescent = pApplet.textDescent();
        optionHeight = optionAscent + optionDescent;
        gameOptions.add(new MainMenuOptionsModel(option, optionWidth, optionHeight, BOUNCY_MODE_OPTION_ID, false));

        option = "Slippery Mode";
        pApplet.textSize(gameOptionTextSize);
        optionWidth = pApplet.textWidth(option);
        optionAscent = pApplet.textAscent();
        optionDescent = pApplet.textDescent();
        optionHeight = optionAscent + optionDescent;
        gameOptions.add(new MainMenuOptionsModel(option, optionWidth, optionHeight, SLIPPERY_MODE_OPTION_ID, false));

        option = "!! WARNING !! Hard Mode !! WARNING !!";
        pApplet.textSize(gameOptionTextSize);
        optionWidth = pApplet.textWidth(option);
        optionAscent = pApplet.textAscent();
        optionDescent = pApplet.textDescent();
        optionHeight = optionAscent + optionDescent;
        gameOptions.add(new MainMenuOptionsModel(option, optionWidth, optionHeight, EXTREME_DIFFICULTY_MODE, false));
    }

    private void calculateStringParams(float gameTitleTextSize) {
        pApplet.textSize(gameTitleTextSize);
        this.gameTitleWidth = pApplet.textWidth(gameTitle);
        float messageAscent = pApplet.textAscent();
        float messageDescent = pApplet.textDescent();
        this.gameTitleHeight = messageAscent + messageDescent;
    }

    public void display() {
        pApplet.background(bgImage);
        pApplet.textSize(gameTitleTextSize);
        pApplet.fill(255, 255, 255);
        pApplet.text(gameTitle, x - gameTitleWidth / 2, y - 50);
        for (int i = 0; i < gameOptions.size(); ++i) {
            pApplet.textSize(gameOptionTextSize);
            if(gameOptions.get(i).isSelected()) {
                pApplet.fill(255, 255, 0);
            } else {
                pApplet.fill(255, 255, 255);
            }
            pApplet.text(gameOptions.get(i).getOptionName(),
                    x - (gameOptions.get(i).getOptionNameWidth()/2),
                    y + gameTitleHeight + i * (gameOptions.get(i).getOptionNameHeight() + 15));
        }
    }

    public ArrayList<MainMenuOptionsModel> getGameOptions() {
        return gameOptions;
    }

    public void moveUpAnOption() {
        if(currSelectedPos == 0) {
            currSelectedPos = gameOptions.size() - 1;
        } else {
            --currSelectedPos;
        }
        //make all not selected
        for(MainMenuOptionsModel menu : gameOptions) {
            menu.setSelected(false);
        }
        gameOptions.get(currSelectedPos).setSelected(true);
        userSelectedGameMode = gameOptions.get(currSelectedPos).getOptionId();
    }

    public void moveDownAnOption() {
        if(currSelectedPos == gameOptions.size() - 1) {
            currSelectedPos = 0;
        } else {
            ++currSelectedPos;
        }
        //make all not selected
        for(MainMenuOptionsModel menu : gameOptions) {
            menu.setSelected(false);
        }
        gameOptions.get(currSelectedPos).setSelected(true);
        userSelectedGameMode = gameOptions.get(currSelectedPos).getOptionId();
    }

    public int getUserSelectedGameMode() {
        return userSelectedGameMode;
    }
}
