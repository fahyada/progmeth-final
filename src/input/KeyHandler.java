package input;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.Main;
import panes.GamePanel;


public class KeyHandler {
    private boolean up, down, left, right, spacePressed;
    private GamePanel gp;
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }


    public void keyPressed(KeyEvent e) {
        KeyCode code = e.getCode();
        if (!gp.getGameLogic().isGameOver() && !gp.getGameLogic().isWinMessage()) {
            if (code == KeyCode.W) {
                up = true;
            } else if (code == KeyCode.S) {
                down = true;
            } else if (code == KeyCode.A) {
                left = true;
            } else if (code == KeyCode.D) {
                right = true;
            } else if (code == KeyCode.SPACE) {
                spacePressed= true;
            }
        }
        else if (gp.getGameLogic().isGameOver() || gp.getGameLogic().isWinMessage()) {
            if (code == KeyCode.W) {
                gp.getUi().setCommandNumEnd(gp.getUi().getCommandNumEnd() - 1);
                if (gp.getUi().getCommandNumEnd() < 0) {
                    gp.getUi().setCommandNumEnd(1);
                }
            } else if (code == KeyCode.S) {
                gp.getUi().setCommandNumEnd(gp.getUi().getCommandNumEnd() + 1);
                if (gp.getUi().getCommandNumEnd() > 1) {
                    gp.getUi().setCommandNumEnd(0);
                }
            } else if (code == KeyCode.ENTER) {
                if (gp.getUi().getCommandNumEnd() == 0) {
                    gp.getSound().stop();
                    Main.restart();

                } else if(gp.getUi().getCommandNumEnd()== 1) {
                    System.exit(0);
                }
            }
        }
    }
    public void keyReleased(KeyEvent e) {
        KeyCode code = e.getCode();
        if (code == KeyCode.W) {
            up = false;
        } else if (code == KeyCode.S) {
            down = false;
        } else if (code == KeyCode.A) {
            left = false;
        } else if (code == KeyCode.D) {
            right = false;
        } else if (code == KeyCode.SPACE) {
            spacePressed = false;
        }
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isSpacePressed() {
        return spacePressed;
    }
}
