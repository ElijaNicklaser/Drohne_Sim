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

    @Override
        public void draw(Graphics g) {
            this.getImage().drawCentered(this.getX(), this.getY());
        }

        public Nachbar(int x, int y, Image image, Input input) {
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

            if (pressed) {
                acceleration += delta;
                if (acceleration > 8) acceleration = 8;
            } else {
                acceleration = 0.001f;
            }
            shape.setCenterX(this.getX());
            shape.setCenterY(this.getY());
        }

        public void setNachbarPosition(){
        this.setX(this.getX());
        this.setY(this.getY());
        }

        public boolean intersects(Shape shape) {
            if (shape != null) {
                return this.getShape().intersects(shape);
            }
            return false;
        }
}