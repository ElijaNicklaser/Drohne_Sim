package ENNL;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
public class Katze extends SpielObjekt{
    private Input input;
    private Rectangle shape;
    private Animation animation;

    @Override
    public void draw(Graphics g)
    {
        this.animation.draw(this.getX(), this.getY());
    }
    public Katze(int x, int y, Image image, Input input)
    {
        super(x, y, image);
        this.input = input;
        shape = new  Rectangle(x, y, image.getWidth(), image.getHeight());

        animation = new Animation();
        PackedSpriteSheet pss = null;
        try {pss = new PackedSpriteSheet("assets/Animation/pack-result cat/texture.def");}
        catch (SlickException e) {
            throw new RuntimeException(e);
        }
        for (int i=0;i<=5;i++){
            animation.addFrame(pss.getSprite("cat_"+i+".png"),100);}
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public void update(int delta)
    {
    }
    public boolean intersects(Shape shape)
    {
        if (shape != null) {
            return this.getShape().intersects(shape);
        }
        return false;
    }

}