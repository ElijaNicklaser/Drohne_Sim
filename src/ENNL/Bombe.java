package ENNL;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
public class Bombe extends SpielObjekt{
    private Input input;
    private Rectangle shape;
    private float geschwindigkeit = 30;
    private Animation animation;
    private Laser laser;
    private boolean isFlying = false;

    private float rotation =0;


    public boolean isFlying() {
        return isFlying;
    }

    public void setFlying(boolean flying) {
        isFlying = flying;

    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public void draw(Graphics g)
    {
        this.getImage().drawCentered(this.getX(),this.getY());
    }
    public Bombe(int x, int y, Image image, Input input)
    {
        super(x, y, image);
        this.input = input;
        shape = new  Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public void update(int delta)
    {
        if (this.isFlying) {

            if (this.getRotation() == 0) {
                this.setY(this.getY() + (int)this.geschwindigkeit);
            }
            if (this.getRotation() == -45f) {
                this.setY(this.getY() + (int)this.geschwindigkeit);
                this.setX(this.getX() + (int)this.geschwindigkeit);
            }
            if (this.getRotation() == 45f) {
                this.setY(this.getY() + (int)this.geschwindigkeit);
                this.setX(this.getX() - (int)this.geschwindigkeit);
            }

        }

        if (this.getY() > 1500) {
            this.isFlying = false;

        }

        shape.setCenterX(this.getX());
        shape.setCenterY(this.getY());

    }
    public boolean intersects(Shape shape)
    {
        if (shape != null) {
            return this.getShape().intersects(shape);
        }
        return false;
    }

    public void setLaser(Laser laser) {
        this.laser = laser;
    }

}
