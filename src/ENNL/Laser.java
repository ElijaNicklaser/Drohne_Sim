package ENNL;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


public class Laser extends SpielObjekt{

        private Input input;
        private Rectangle shape;
        private Bombe bombe;
        private float rotation =0f;



        public Laser(int x,int y,Image image,Input input, Bombe bombe){
                super(x,y,image);

                this.input = input;
                this.bombe = bombe;
                shape = new Rectangle(x,y,image.getWidth(),image.getHeight());
        }

        @Override
        public void draw(Graphics g){
                this.getImage().drawCentered(this.getX(),this.getY());
        }

        @Override
        public Shape getShape() {
                return shape;
        }

        @Override
        public void update(int delta){

                if (input.isKeyDown(Input.KEY_D)){
                        this.getImage().setRotation(-45f);
                        this.rotation = -45f;
                }

                else if(input.isKeyDown(Input.KEY_A)) {
                        this.getImage().setRotation(+45f);
                        this.rotation = 45f;
                }
                else{
                        this.getImage().setRotation(0);
                        this.rotation = 0;
                }

                if (input.isKeyPressed(Input.KEY_SPACE)) {
                        if (bombe.getY() > 1080) {
                                bombe.setX(this.getX());
                                bombe.setY(this.getY() - 50 + this.getHeight() / 2);
                                bombe.setRotation(this.rotation);
                                if (!bombe.isFlying()) bombe.setFlying(true);
                        }
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

        public float getRotation() {
                return rotation;
        }

        public void setRotation(float rotation) {
                this.rotation = rotation;
        }
}
