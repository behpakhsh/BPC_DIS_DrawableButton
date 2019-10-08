package bpc.dis.drawablebutton;

public enum DisTextStyle {

    NORMAL(0),
    BOLD(1),
    ITALIC(2),
    BOLD_ITALIC(3);

    private int value;

    DisTextStyle(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}