package ENNL;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
public class Bombe extends SpielObjekt{
    private Input input;
    private Rectangle shape;
    private float geschwindigkeit = 30;
    private float acceleration = 0.0001f;
    private Animation animation;
    private Laser laser;

    @Override
    public void draw(Graphics g)
    {
        this.getImage().drawCentered(this.getX(),this.getY());
    }
    public Bombe(int x, int y, Image image, Input input, Laser laser)
    {
        super(x, y, image);
        this.input = input;
        this.laser = laser;
        shape = new  Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public void update(int delta)
    {
        if (this.getY() > (-100)) {
            this.geschwindigkeit = (delta * this.acceleration + geschwindigkeit);

            this.getImage().setRotation(45f);
            this.setY(this.getY() + (int) this.geschwindigkeit * (int) Math.cos(Math.toRadians(45f)));
            this.setX(this.getY() + (int) this.geschwindigkeit * (int) Math.sin(Math.toRadians(45f)));

            shape.setCenterX(this.getX());
            shape.setCenterY(this.getY());
        }

    }
    public boolean intersects(Shape shape)
    {
        if (shape != null) {
            return this.getShape().intersects(shape);
        }
        return false;
    }

}