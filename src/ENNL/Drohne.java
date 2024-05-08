package ENNL;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Drohne extends SpielObjekt{
    private Input input;
    private Rectangle shape;
    private float speed = 5f;
    private float force = 1f;
    private float acceleration = 12f;
    private Point oldLocation;


    public Drohne(int x, int y, Image image, Input input) {
        super(x, y, image);
        this.input = input;
        shape = new Rectangle(x, y, image.getWidth(), image.getHeight());
        this.oldLocation = new Point(this.getX(), this.getY());
    }

    @Override
    public void draw(Graphics g) {
        this.getImage().drawCentered(this.getX(), this.getY());
    }


    @Override
    public Shape getShape() {return shape;}


    @Override
    public void update(int delta) {
        this.oldLocation = new Point(this.getX(), this.getY());
            if (input.isKeyDown(Input.KEY_W)) {
                this.setY(this.getY() - (int) this.speed);
            } else if (input.isKeyDown(Input.KEY_S)) {
                this.setY(this.getY() + (int) this.speed);
            } else {
                this.setY(this.getY() + (int) this.force);
            }

            boolean pressed = false;
            if (input.isKeyDown(Input.KEY_A)) {
                this.setX(this.getX() - (int) this.acceleration);
            }
            if (input.isKeyDown(Input.KEY_D)) {
                this.setX(this.getX() + (int) this.acceleration);

            }
            //unten
            if ((this.getY() > (1080 - this.getHeight() / 2))) this.setY(1080 - this.getHeight() / 2);
            //oben
            if ((this.getY() < (this.getHeight() / 2))) this.setY(this.getHeight() / 2);
            //rechts
            if ((this.getX() > (1920 - this.getWith() / 2))) this.setX(1920 - (this.getWith() / 2));
            //links
            if ((this.getX() < (this.getWith() / 2))) this.setX(this.getWith() / 2);
            shape.setCenterX(this.getX());
            shape.setCenterY(this.getY());
        }
    public boolean intersects(Shape shape) {
        if (shape != null) {
            return this.getShape().intersects(shape);
        }

        return false;
    }

    public void moveback() {
        this.setX((int) this.oldLocation.getX());
        this.setY((int) this.oldLocation.getY());
    }
}
