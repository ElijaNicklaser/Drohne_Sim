package ENNL;

import org.newdawn.slick.*;

import java.util.ArrayList;

public class Main extends BasicGame {
    private Image background;
    private AngelCodeFont font;
    private Nachbar nachbar;
    private Drohne drohne;
    private Bombe bombe;
    private Katze katze;
    private Laser laser;
    private Geschoss geschoss;
    private Explosion explosion;
    private int hitn=0;
    private int hitd=0;
    private Music music;
    private Sound sound;
    private float time = 0;



    public Main() {
        super("Drohne_Sim");
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer container = new AppGameContainer(new Main());
        container.setDisplayMode(1920, 1080, false);
        //container.setClearEachFrame(false);
        container.setMinimumLogicUpdateInterval(25);
        container.setTargetFrameRate(60);
        container.setShowFPS(true);
        container.start();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        font = new AngelCodeFont("testdata/demo2.fnt","testdata/demo2_00.tga");
        music = new Music("assets/Musik/Epic.wav",true);


        background = new Image("assets/Bilder/SpielHintergrund.png");
        geschoss = new Geschoss(1400,-100, new Image("assets/Bilder/Geschoss.png"),container.getInput());
        nachbar = new Nachbar(1400,800, new Image("assets/Bilder/Nachbar V3.png"),container.getInput(),geschoss);
        drohne = new Drohne(300,540, new Image("assets/Bilder/Drohne V2.png"),container.getInput(),bombe);
        bombe = new Bombe(300,1200, new Image("assets/Bilder/2.png"),container.getInput());
        katze = new Katze(300,540, new Image("assets/Bilder/Bombe.png"),container.getInput());
        laser = new Laser(300,540, new Image("assets/Bilder/3.png"),container.getInput(), bombe);
        bombe.setLaser(laser);
        explosion = new Explosion(-100,-100, new Image("assets/Bilder/Bombe.png"),container.getInput());
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        this.time += delta;

        if (drohne.intersects(nachbar.getShape())) {
            drohne.moveback();
        }

        if (drohne.intersects(geschoss.getShape())){
            explosion.setY(drohne.getY());
            explosion.setX(drohne.getX());
            geschoss.setY(-180);
            hitd ++;
            this.time = 0;
        }

        if (nachbar.intersects(bombe.getShape())){
            drohne.moveback();
            explosion.setY(bombe.getY());
            explosion.setX(bombe.getX());
            bombe.setY(1500);
            hitn ++;
            this.time = 0;
        }


        nachbar.update(delta);
        drohne.update(delta);
        geschoss.update(delta);
        bombe.update(delta);
        laser.update(delta);
        laser.setY(drohne.getY()+235);
        laser.setX(drohne.getX()+95);
        katze.setY(nachbar.getY()+110);
        katze.setX(nachbar.getX()-10);

        if (this.time >= 100.0)
            explosion.setY(-100);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        background.draw();
        nachbar.draw(g);
        drohne.draw(g);
        geschoss.draw(g);
        bombe.draw(g);
        katze.draw(g);
        laser.draw(g);
        explosion.draw(g);
        font.drawString(80, 5, hitd + "  Hit's Drohne", Color.red);
        font.drawString(80, 30, hitn + " Hit's Nachbar ", Color.red);

    }
}