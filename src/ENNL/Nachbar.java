package ENNL;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Nachbar extends SpielObjekt{
        private Input input;
        private Rectangle shape;
        private float acceleration = 0.001f;
        private Geschoss geschoss;
        private Animation animation;

    public Nachbar(int x, int y, Image image, Input input, Geschoss geschoss) {
        super(x, y, image);
        this.input = input;
        shape = new Rectangle(x, y, image.getWidth(), image.getHeight());


        animation = new Animation();
        PackedSpriteSheet pss = null;
        try {
            pss = new PackedSpriteSheet("assets/Animation/pack-result nachbar/texture.def");
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
        for (int i=0;i<=3;i++) {
            animation.addFrame(pss.getSprite("nachbar_" + i + ".png"), 100);
        }
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
            if (geschoss.getY() < 0){
                if (input.isKeyDown(Input.KEY_UP)) {
                    geschoss.setX(this.getX());
                    geschoss.setY(this.getY() - 200 + this.getHeight() / 2);
                }
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


        public boolean intersects(Shape shape) {
            if (shape != null) {
                return this.getShape().intersects(shape);
            }
            return false;
        }
}