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
    private int rotation =0;
    private int i=0;
    private float time = 0;
    private int[] rotationArray = new int[] {0,1,0,2};

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
        this.time += delta;
        // 1000ms = 1Sekunde
        if (this.time > 1000) {
            setRotation(rotation,delta,false);
        }

        if (input.isKeyDown(Input.KEY_SPACE)) {
            //setRotation(rotation,true,delta);
        }
        shape.setCenterX(this.getX());
        shape.setCenterY(this.getY());
    }
    private void setRotation(int rotation,int delta,boolean moving) {
            switch (rotationArray[rotation]) {
                case 0:
                    this.setY(this.getY());
                    break;
                case 1:
                    this.setY(this.getY());
                    this.setX(this.getX());
                    break;
                case 2:
                    this.setX(this.getX());
                    break;
                case 3:
                    this.setY(this.getY());
                    this.setX(this.getX());
                    break;
            }
            rotation++;
            if (rotation > 3 ) rotation=0;

    }

    public boolean intersects(Shape shape) {
        if (shape != null) {
            return this.getShape().intersects(shape);
        }
        return false;
    }
}
