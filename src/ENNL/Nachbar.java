package ENNL;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Nachbar extends SpielObjekt{
        private Input input;
        private Rectangle shape;
        private float acceleration = 0.001f;
        private Geschoss geschoss;

    public Nachbar(int x, int y, Image image, Input input, Geschoss geschoss) {
        super(x, y, image);
        this.input = input;
        shape = new Rectangle(x, y, image.getWidth(), image.getHeight());
        this.geschoss = geschoss;
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
            boolean pressed = false;

            if (input.isKeyDown(Input.KEY_LEFT)) {
                this.setX(this.getX() - (int) this.acceleration);
                if ((this.getX()  < 40 + this.getWith() / 2)) this.setX(40+ this.getWith() / 2);
                pressed = true;
            }
            if (input.isKeyDown(Input.KEY_RIGHT)) {
                this.setX(this.getX() + (int) this.acceleration);
                if ((this.getX() > (1880 - this.getWith() / 2))) this.setX(1880 - this.getWith() / 2);
                pressed = true;
            }
            if (geschoss.getY() > 1880){
                if (input.isKeyDown(Input.KEY_UP)) {
                    geschoss.setX(this.getX());
                    geschoss.setY(this.getY() - 200 + this.getHeight() / 2);
                }
            }
            else geschoss.setY()=0;

            if (pressed) {
                acceleration += delta;
                if (acceleration > 8) acceleration = 8;
            } else {
                acceleration = 0.001f;
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