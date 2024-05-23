package entity;

import Interface.IRenderable;
import entity.monsters.MonsterBase;
import entity.monsters.MonsterField;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import input.KeyHandler;
import panes.GamePanel;

import static utils.GetData.getImage;

public class Player extends Entity implements IRenderable {
    private GamePanel gp;
    private KeyHandler keyH;
    //public boolean gameOver;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(100,100,10,30,4, 2 * GamePanel.tileSize);
        this.gp = gp;
        this.keyH = keyH;
        setX(20);
        setY(150);
        setDirection("right");
        setSolidArea(new Rectangle(0, 0, 48, 48));
    }

    // constructor related

    public void setMovementImages() {
        try {

            Image playerImage = new Image(ClassLoader.getSystemResource("Player/player.png").toString());

            PixelReader reader = playerImage.getPixelReader();

            setUp1(new WritableImage(reader, 255, 453, 100, 100));
            setUp2(new WritableImage(reader, 653, 453, 100, 100));
            setDown1(new WritableImage(reader, 646, 304, 100, 100));
            setDown2(new WritableImage(reader, 762, 305, 100, 100));
            setLeft1(new WritableImage(reader, 646, 304, 100, 100));
            setLeft2(new WritableImage(reader, 762, 305, 100, 100));
            setRight1(new WritableImage(reader, 255, 453, 100, 100));
            setRight2(new WritableImage(reader, 653, 453, 100, 100));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setAttackImages() {
        try {
            Image playerImage = new Image(ClassLoader.getSystemResource("Player/player.png").toString());

            PixelReader reader = playerImage.getPixelReader();
            setAttackUp(new WritableImage(reader, 362, 758, 100, 100));
            setAttackDown(new WritableImage(reader, 100, 600, 100, 100));
            setAttackLeft(new WritableImage(reader, 100, 600, 100, 100));
            setAttackRight(new WritableImage(reader, 362, 758, 100, 100));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // methods; attacking and being attacked

    public void updateAttackArea() {
        Rectangle attackArea = new Rectangle();
        double tileSize = GamePanel.tileSize;
        switch (getDirection()) {
            case "up":
                attackArea = new Rectangle(getX(),getY() - tileSize,getSize(),tileSize);
                break;
            case "down":
                attackArea = new Rectangle(getX(),getY() + getSize(),getSize(),tileSize);
                break;
            case "left":
                attackArea = new Rectangle(getX() - tileSize,getY(),tileSize,getSize());
                break;
            case "right":
                attackArea = new Rectangle(getX() + getSize(),getY(),tileSize,getSize());
                break;
        }
        super.setAttackArea(attackArea);
        attackArea.setFill(Color.BLACK);

    }

    public void attack(MonsterBase monster) {
        if (isAttacking()) {
            if (getAttackArea().intersects(monster.getSolidArea().getLayoutBounds())) {
                monster.setHp(monster.getHp() - getAtk());
            }
        }
    }

    // methods; graphics

    public void updatePosition() {
        if (keyH.isUp()) {
            setDirection("up");
        }
        else if (keyH.isDown()) {
            setDirection("down");
        }
        else if (keyH.isLeft()) {
            setDirection("left");
        }
        else if (keyH.isRight()) {
            setDirection("right");
        }

        if (keyH.isSpacePressed()) {
            setAttacking(true);
        }
        if(!keyH.isSpacePressed()) {
            setAttacking(false);
        }

        //collision
        setCollisionOn(false);
        gp.getCchecker().checkTile(this);
        if(!isCollisionOn()) {
            switch (getDirection()) {
                case "up":
                    if (keyH.isUp()) {
                        setY(getY() - getSpeed());
                        break;
                    }
                case "down":
                    if (keyH.isDown()) {
                        setY(getY() + getSpeed());
                        break;
                    }
                case "left":
                    if (keyH.isLeft()) {
                        setX(getX() - getSpeed());
                        break;
                    }
                case "right":
                    if (keyH.isRight()) {
                        setX(getX() + getSpeed());
                        break;
                    }
            }
        }
        setSpriteCounter(getSpriteCounter() + 1);
        if(getSpriteCounter() > 12) {
            if(getSpriteNum() == 1) {
                setSpriteNum(2);
            }
            else if(getSpriteNum() == 2) {
                setSpriteNum(1);
            }
            setSpriteCounter(0);
        }
        if(!isAlive()){
            gp.getGameLogic().setGameOver(true);
        }
    }

    public void draw(GraphicsContext gc) {
        Image image = null;
        switch (getDirection()) {
            case "up":
                if (isAttacking()) {
                    image = getAttackUp();
                }
                if (!isAttacking()) {
                    if (getSpriteNum() == 1) {
                        image = getUp1();
                    }
                    if (getSpriteNum() == 2) {
                        image = getUp2();
                    }
                }
                break;
            case "down":
                if (isAttacking()) {
                    image = getAttackDown();
                }
                if (!isAttacking()) {
                    if (getSpriteNum() == 1) {
                        image = getDown1();
                    }
                    if (getSpriteNum() == 2) {
                        image = getDown2();
                    }
                }
                break;
            case "left":
                if (isAttacking()) {
                    image = getAttackLeft();
                }
                if (!isAttacking()) {
                    if (getSpriteNum() == 1) {
                        image = getLeft1();
                    }
                    if (getSpriteNum() == 2) {
                        image = getLeft2();
                    }
                }
                break;
            case "right":
                if (isAttacking()) {
                    image = getAttackRight();
                }
                if (!isAttacking()) {
                    if (getSpriteNum() == 1) {
                        image = getRight1();
                    }
                    if (getSpriteNum() == 2) {
                        image = getRight2();
                    }
                }
                break;
        }
        //player HP bar
        double oneScale = (double) GamePanel.tileSize/getMaxHp();
        double hpBarValue = oneScale*getHp();

        gc.setFill(Color.BLACK);
        gc.fillRect(getX()+17, getY() - 10, GamePanel.tileSize+2, 12);
        gc.setFill(Color.BLUE);
        gc.fillRect(getX()+18, getY() - 9, hpBarValue, 10);

        //draw player
        gc.drawImage(image, getX(), getY(), getSize(), getSize());
    }
}