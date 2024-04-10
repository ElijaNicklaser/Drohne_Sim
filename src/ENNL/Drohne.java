package ENNL;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Drohne extends SpielObjekt{
    private Input input;
    private Rectangle shape;
    private float speed = 15f;
    private float force = 9f;


    public Drohne(int x, int y, Image image, Input input) {
        super(x, y, image);
        this.input = input;
        shape = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    @Override
    public void draw(Graphics g) {
        this.getImage().drawCentered(this.getX(), this.getY());
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public void update(int delta) {
        if (input.isKeyDown(Input.
                KEY_W
        )) {
            this.setY(this.getY() - (int) this.speed);
            if ((this.getY() < this.getHeight() / 4)) this.setY(this.getHeight() / 4);
        }

        else{
            this.setY(this.getY() + (int) this.force);
            if ((this.getY() > (1080 - this.getHeight() / 4))) this.setY(1080 - this.getHeight() / 4);
        }
        shape.setCenterX(this.getX());
        shape.setCenterY(this.getY());
    }

    public boolean intersects(Shape shape) {
        if (shape != null) {
            return this.getShape().intersects(shape);
        }
        return false;
    }
}
