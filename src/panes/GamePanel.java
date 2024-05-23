package panes;

import entity.Player;
import entity.monsters.MonsterBase;
import input.KeyHandler;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameLogic;
import utils.CollisionChecker;
import main.UI;
import tiles.TileManager;

public class GamePanel extends Canvas {
    // Screen settings
    public static final int tileSize = 48;

    // 3:4 screen
    public static final int maxScreenCol = 16;
    public static final int maxScreenRow = 12;
    public static final int ScreenWidth = tileSize * maxScreenCol; // 768
    public static final int ScreenHeight = tileSize * maxScreenRow; // 526

    // Set FPS
    private final int FPS = 60;
    private AnimationTimer gameLoop;
    private TileManager tileM = new TileManager();
    private KeyHandler keyH = new KeyHandler(this);
    private GameLogic gameLogic;
    private Player player = new Player(this, keyH);
    private CollisionChecker Cchecker = new CollisionChecker(this);
    private UI ui;
    private MediaPlayer sound;


    // edit method UI
    public GamePanel(double width, double height) {
        super(width, height);
        this.setStyle("-fx-background-color: black;");
        this.setFocusTraversable(true);

        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyH.keyPressed(event);
            }
        });

        setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyH.keyReleased(event);
            }
        });

        String soundFile = "Sound/Theme.wav";
        Media media = new Media(getClass().getClassLoader().getResource(soundFile).toExternalForm());
        sound = new MediaPlayer(media);
        sound.setCycleCount(MediaPlayer.INDEFINITE);
        sound.play();

        setGameLogic(new GameLogic(player));
        ui = new UI(this); // home here
    }

    public void startGameThread() {
        gameLogic.setUpGame();
        gameLoop = new AnimationTimer() {
            long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 1_000_000_000 / FPS) {
                    paintComponent();
                    gameLogic.update();
                    lastUpdate = now;
                }
            }
        };
        gameLoop.start();
    }

    public void paintComponent() {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.clearRect(0, 0, ScreenWidth, ScreenHeight);
        tileM.draw(gc);
        player.draw(gc);
        for (MonsterBase monster : gameLogic.getMonsterField().getMonsters()) {
            monster.draw(gc,player);
        }
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Retro Gaming", 30));
        gc.fillText("Wave" + gameLogic.getCurrentWave(), tileSize*7, 30);
        if (gameLogic.isGameOver() || gameLogic.isWinMessage()) {
            ui.draw(gc);
        }
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public TileManager getTileManager() {
        return this.tileM;
    }

    public CollisionChecker getCchecker() {
        return Cchecker;
    }

    public UI getUi() {
        return ui;
    }

    public MediaPlayer getSound() {
        return sound;
    }
}