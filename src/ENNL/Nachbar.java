package ENNL;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Nachbar extends SpielObjekt{
        private Input input;
        private Rectangle shape;
        private float acceleration = 0.1f;

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

            if (input.isKeyDown(Input.KEY_A)) {
                this.setX(this.getX() - (int) this.acceleration);
                if ((this.getX() < this.getWith() / 2)) this.setX(this.getWith() / 2);
                pressed = true;
            }
            if (input.isKeyDown(Input.KEY_D)) {
                this.setX(this.getX() + (int) this.acceleration);
                if ((this.getX() > (2000 - this.getWith() / 2))) this.setX(1024 - this.getWith() / 2);
                pressed = true;
            }

            if (pressed) {
                acceleration += delta;
                if (acceleration > 10) acceleration = 100;

            } else {
                acceleration = 0.01f;
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