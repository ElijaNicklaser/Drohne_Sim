package ENNL;

import org.newdawn.slick.*;

import java.util.ArrayList;

public class Main extends BasicGame {
    private Image background;
    private AngelCodeFont font;
    private Nachbar nachbar;
    private Drohne drohne;
    private Bombe bombe;
    private Geschoss geschoss;
    private int hit=0;



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
        background = new Image("assets/Bilder/SpielHintergrund.png");
        geschoss = new Geschoss(1400,-100, new Image("assets/Bilder/Geschoss.png"),container.getInput());
        nachbar = new Nachbar(1400,940, new Image("assets/Bilder/Nachbar V2.jpg"),container.getInput(),geschoss);
        drohne = new Drohne(300,540, new Image("assets/Bilder/Drohne V1.gif"),container.getInput());
        bombe = new Bombe(300,540, new Image("assets/Bilder/Bombe.png"),container.getInput());
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {

        if (drohne.intersects(nachbar.getShape())) {
            drohne.moveback();
        }

        if (drohne.intersects(geschoss.getShape())){
            geschoss.setY(-180);
            hit ++;
        }

        if (nachbar.intersects(bombe.getShape())){
            drohne.moveback();
            hit ++;
        }

        if (hit >= 3){
            container.exit();
        }

        nachbar.update(delta);
        drohne.update(delta);
        geschoss.update(delta);
        bombe.setY(drohne.getY()+drohne.getHeight());
        bombe.setX(drohne.getX()-70);
        bombe.update(delta);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        background.draw();
        nachbar.draw(g);
        drohne.draw(g);
        geschoss.draw(g);
        bombe.draw(g);
    }
}