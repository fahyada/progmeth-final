package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import panes.GamePanel;


public class UI {
    private GamePanel gp;
    private GraphicsContext gc;
    private int commandNumEnd = 0;
    Font retroGaming;
    public UI(GamePanel gp) {
        this.gp = gp;
    }
    public void draw(GraphicsContext gc) {
        this.gc = gc;
        if (gp.getGameLogic().isGameOver()) {
            drawGameOverScreen();
        }
        else if (gp.getGameLogic().isWinMessage()) {
            drawWinScreen();
        }
    }

    public void drawGameOverScreen() {
        gc.setFont(Font.font("Retro Gaming", 60));
        gc.setFill(Color.BLACK);
        gc.fillText("GAME OVER", gp.tileSize * 4, gp.tileSize * 6);
        gc.setFont(Font.font("Retro Gaming", 40));
        gc.fillText("BACK TO HOME", gp.tileSize * 4.5, gp.tileSize * 7);
        if (commandNumEnd == 0) {
            gc.fillText(">", gp.tileSize * 3.5, gp.tileSize * 7);
        }
        gc.fillText("QUIT", gp.tileSize * 6.75, gp.tileSize * 8);
        if (commandNumEnd == 1) {
            gc.fillText(">", gp.tileSize * 6, gp.tileSize * 8);
        }
    }
    public void drawWinScreen() {
        gc.setFont(Font.font("Retro Gaming", 60));
        gc.setFill(Color.BLACK);
        gc.fillText("YOU WIN", gp.tileSize * 5, gp.tileSize * 6);
        gc.setFont(Font.font("Retro Gaming", 40));
        gc.fillText("BACK TO HOME", gp.tileSize * 5, gp.tileSize * 7);
        if (commandNumEnd == 0) {
            gc.fillText(">", gp.tileSize * 4, gp.tileSize * 7);
        }
        gc.fillText("QUIT", gp.tileSize * 6.75, gp.tileSize * 8);
        if (commandNumEnd == 1) {
            gc.fillText(">", gp.tileSize * 6, gp.tileSize * 8);
        }
    }

    public int getCommandNumEnd() {
        return commandNumEnd;
    }

    public void setCommandNumEnd(int commandNumEnd) {
        this.commandNumEnd = commandNumEnd;
    }
}
