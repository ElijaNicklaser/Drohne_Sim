package ENNL;

import org.newdawn.slick.Image;

public class SpielObjekt {
    private int x;
    private int y;
    private int with;
    private int height;
    private Image image;

    public SpielObjekt(int x, int y, int with, int height, Image image) {
        this.x = x;
        this.y = y;
        this.with = with;
        this.height = height;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWith() {
        return with;
    }

    public void setWith(int with) {
        this.with = with;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
