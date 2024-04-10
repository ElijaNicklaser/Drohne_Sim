package ENNL;

import org.newdawn.slick.*;

import java.util.ArrayList;

public class Main extends BasicGame {

    private Image background;
    private AngelCodeFont font;
    private Nachbar nachbar;
    private Drohne drohne;


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
        nachbar = new Nachbar(1400,540, new Image("assets/Bilder/Nachbar V2.jpg"),container.getInput());
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {

    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        background.draw();
        nachbar.draw(g);
    }
}