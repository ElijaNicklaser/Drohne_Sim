package ENNL;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
public class Bombe extends SpielObjekt{
    private Input input;
    private Rectangle shape;
    private float acceleration = 0.001f;
    private float geschwindigkeit = 30;

    @Override
    public void draw(Graphics g)
    {
        this.getImage().drawCentered(this.getX(), this.getY());
    }
    public Bombe(int x, int y, Image image, Input input)
    {
        super(x, y, image);
        this.input = input;
        shape = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public void update(int delta) {
        if (input.isKeyDown(Input.KEY_SPACE)) {
            this.geschwindigkeit = (delta * this.acceleration + geschwindigkeit);
            this.setX(this.getX() + (int) this.geschwindigkeit);
            shape.setCenterX(this.getX());
            shape.setCenterY(this.getY());
        }
        else {
            shape.setCenterX(this.getX());
            shape.setCenterY(this.getY());
        }
    }

    public boolean intersects(Shape shape) {
        if (shape != null) {
            return this.getShape().intersects(shape);
        }
        return false;
    }
}
