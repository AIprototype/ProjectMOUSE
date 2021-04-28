import processing.core.PApplet;

public class Timer {
    private int startTime;
    private final int interval;
    private final PApplet pApplet;

    public Timer(PApplet pApplet, int timeInterval) {
        this.pApplet = pApplet;
        interval = timeInterval;
    }

    public void start() {
        startTime = pApplet.millis();
    }

    public boolean complete() {
        int elapsedTime = pApplet.millis() - startTime;
        return elapsedTime > interval;
    }
}
