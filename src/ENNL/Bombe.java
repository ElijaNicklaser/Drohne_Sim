package ENNL;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
public class Bombe extends SpielObjekt{
    private Input input;
    private Rectangle shape;
    private float geschwindigkeit = 30;
    private int rotation =0;
    private float time = 0;
    private int[] rotationArray = new int[] {0,1,0,2};
    private Animation animation;



    @Override
    public void draw(Graphics g)
    {
        this.animation.draw(this.getX(), this.getY());
    }
    public Bombe(int x, int y, Image image, Input input)
    {
        super(x, y, image);
        this.input = input;
       shape = new  Rectangle(x, y, image.getWidth(), image.getHeight());

        animation = new Animation();
        PackedSpriteSheet pss = null;
        try {pss = new PackedSpriteSheet("assets/Animation/pack-result Laser V2/texture.def");}
        catch (SlickException e) {
            throw new RuntimeException(e);
        }
        for (int i=0;i<=3;i++){
            animation.addFrame(pss.getSprite("Laser_"+i+".png"),100);}
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
            setRotation(rotation,delta,true);
        }

        if (input.isKeyDown(Input.KEY_SPACE)) {
            setRotation(rotation,delta,true);
            this.setY(this.getY() - (int) this.geschwindigkeit);
            shape.setCenterX(this.getX());
            shape.setCenterY(this.getY());
        }
        shape.setCenterX(this.getX());
        shape.setCenterY(this.getY());
    }


    private void setRotation(int rotation,int delta,boolean moving) {
        if (this.time > 998) {
            switch (rotationArray[rotation]) {
                case 0:
                    this.getImage().setRotation(0f);
                    break;
                case 1:
                    this.getImage().setRotation(45f);
                    break;
                case 2:
                    this.getImage().setRotation(-45f);
                    break;
            }
            rotation++;
            if (rotation > 3) {
                rotation =0;
            }
        }
    }


    public boolean intersects(Shape shape) {
        if (shape != null) {
            return this.getShape().intersects(shape);
        }
        return false;
    }

}
