package camera_classes;

public class CameraHandlerClass {
    private float minX;
    private final float width;

    public CameraHandlerClass(float minX, float width) {
        this.minX = minX;
        this.width = width;

    }

    public float getMinX() {
        return minX;
    }

    public void setMinX(float minX) {
        this.minX = minX;
    }

    public float getMaxX() {
        return minX + width;
    }
}
