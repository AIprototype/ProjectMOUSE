package status_pages.models;

public class MainMenuOptionsModel {
    String optionName;
    float optionNameWidth, optionNameHeight;
    int optionId;
    boolean isSelected;

    public MainMenuOptionsModel(String optionName, float optionNameWidth, float optionNameHeight, int optionId, boolean isSelected) {
        this.optionName = optionName;
        this.optionNameWidth = optionNameWidth;
        this.optionNameHeight = optionNameHeight;
        this.optionId = optionId;
        this.isSelected = isSelected;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public float getOptionNameWidth() {
        return optionNameWidth;
    }

    public void setOptionNameWidth(float optionNameWidth) {
        this.optionNameWidth = optionNameWidth;
    }

    public float getOptionNameHeight() {
        return optionNameHeight;
    }

    public void setOptionNameHeight(float optionNameHeight) {
        this.optionNameHeight = optionNameHeight;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
