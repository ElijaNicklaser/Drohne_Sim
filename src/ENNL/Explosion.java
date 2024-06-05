package ENNL;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
public class Explosion extends SpielObjekt{
    private Input input;
    private Rectangle shape;
    private Animation animation;

    @Override
    public void draw(Graphics g)
    {
        this.animation.draw(this.getX(), this.getY());
    }
    public Explosion(int x, int y, Image image, Input input)
    {
        super(x, y, image);
        this.input = input;
        shape = new  Rectangle(x, y, image.getWidth(), image.getHeight());

            animation = new Animation();
            PackedSpriteSheet pss = null;
            try {
                pss = new PackedSpriteSheet("assets/Animation/pack-result explosion/texture.def");
            } catch (SlickException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i <= 2; i++) {
                animation.addFrame(pss.getSprite("explosion_" + i + ".png"), 100);
            }
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