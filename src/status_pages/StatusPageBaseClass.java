package status_pages;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PShape;

import static processing.core.PApplet.max;
import static processing.core.PApplet.min;

public abstract class StatusPageBaseClass {
    String title;
    String message;
    final PApplet pApplet;
    PFont font;
    int titleTexSize, messageTextSize;

    float titleWidth, messageWidth;
    float titleHeight, messageHeight;
    float titleAscent, messageAscent;
    float titleDescent, messageDescent;

    float x, y;

    public StatusPageBaseClass(String title, String message, PApplet pApplet, int titleTexSize, int messageTextSize) {
        this.title = title;
        this.message = message;
        this.pApplet = pApplet;
        this.font = pApplet.createFont("Arial", 96, true);

        pApplet.textSize(titleTexSize);
        this.titleWidth = pApplet.textWidth(title);
        titleAscent = pApplet.textAscent();
        titleDescent = pApplet.textDescent();
        titleHeight = titleAscent + titleDescent;

        pApplet.textSize(messageTextSize);
        this.messageWidth = pApplet.textWidth(message);
        messageAscent = pApplet.textAscent();
        messageDescent = pApplet.textDescent();
        messageHeight = titleAscent + titleDescent;

        this.x = pApplet.width / 2;
        this.y = pApplet.height / 2;

        this.titleTexSize = titleTexSize;
        this.messageTextSize = messageTextSize;
    }

    public void display() {
        pApplet.background(0);
        pApplet.textSize(titleTexSize);
        pApplet.fill(255, 255, 255);
        pApplet.text(title, x - titleWidth/2, y);
        pApplet.textSize(messageTextSize);
        pApplet.fill(123, 123, 123);
        pApplet.text(message, x - messageWidth/2, y + titleHeight);
    }
}
