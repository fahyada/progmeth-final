package entity.monsters;

import Interface.IRenderable;
import entity.Entity;
import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import panes.GamePanel;

public abstract class MonsterBase extends Entity {
    private int hp;
    private int maxHp;
    private int atk;
    private int range;
    private Image attackUp1;
    private Image attackUp2;
    private Image attackDown1;
    private Image attackDown2;
    private Image attackLeft1;
    private Image attackLeft2;
    private Image attackRight1;
    private Image attackRight2;

    // constructor and methods
    public MonsterBase(int hp, int maxHp, int atk, int range, int speed, double size) {
        super(hp, maxHp, atk, range, speed, size);
        setDirection("right");
        setRandomCoordinate();
        setSolidArea(new Rectangle(getX(),getY(),size,size));
    }

    public void setRandomCoordinate() {
        setX(Math.random() * GamePanel.ScreenWidth);
        setY(Math.random() * GamePanel.ScreenHeight);
    }

    // methods; attacking and being attacked
    public void checkAttack(Player player) {
        double dx = player.getX() - getX();
        double dy = player.getY() - getY();
        double distance = Math.sqrt((dx * dx - 36) + (dy * dy - 36));
        if (distance <= (getRange() - 10)) {
            setAttacking(true);
        }
    }

    public void attack(Player player) {
        setSpriteCounter(getSpriteCounter() + 1);
        if (getSpriteCounter() <= 12) {
            setSpriteNum(1);
        }
        if (getSpriteCounter() > 12 && getSpriteCounter() <= 36) {
            setSpriteNum(2);
        }
        if (getSpriteCounter() > 36) {
            setSpriteNum(1);
            setSpriteCounter(0);
            player.setHp(player.getHp() - getAtk());
            setAttacking(false);
        }
    }
    public abstract Rectangle getBounds();

    public void draw(GraphicsContext gc, Player player) {
        Image image = null;
        Image attackEffect = null;
        switch (getDirection()) {
            case "up":
                if (isAttacking()) {
                    if (getSpriteNum() == 1) {
                        image = getAttackUp1();
                        attackEffect = getAttackEffect1();
                    }
                    if (getSpriteNum() == 2) {
                        image = getAttackUp2();
                        attackEffect = getAttackEffect2();
                    }
                } else {
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
                    if (getSpriteNum() == 1) {
                        image = getAttackDown1();
                        attackEffect = getAttackEffect1();
                    }
                    if (getSpriteNum() == 2) {
                        image = getAttackDown2();
                        attackEffect = getAttackEffect2();
                    }
                } else {
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
                    if (getSpriteNum() == 1) {
                        image = getAttackLeft1();
                        attackEffect = getAttackEffect1();
                    }
                    if (getSpriteNum() == 2) {
                        image = getAttackLeft2();
                        attackEffect = getAttackEffect2();
                    }
                } else {
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
                    if (getSpriteNum() == 1) {
                        image = getAttackRight1();
                        attackEffect = getAttackEffect1();
                    }
                    if (getSpriteNum() == 2) {
                        image = getAttackRight2();
                        attackEffect = getAttackEffect2();
                    }
                } else {
                    if (getSpriteNum() == 1) {
                        image = getRight1();
                    }
                    if (getSpriteNum() == 2) {
                        image = getRight2();
                    }
                }
                break;
            }
        //monster HP bar
        double oneScale = (double) GamePanel.tileSize/getMaxHp();
        double hpBarValue = oneScale*getHp();

        gc.setFill(Color.BLACK);
        gc.fillRect(getX()+4, getY() - 16, GamePanel.tileSize+2,

                12);
        gc.setFill(Color.RED);
        gc.fillRect(getX()+5, getY() - 15, hpBarValue, 10);

        //draw monster
        gc.drawImage(image, getX(), getY(), getSize(), getSize());
        //draw effect
        gc.drawImage(attackEffect, player.getX(), player.getY(), player.getSize(), player.getSize());
    }

    // other getters setters
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0,hp);
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public Image getAttackUp1() {
        return attackUp1;
    }

    public void setAttackUp1(Image attackUp1) {
        this.attackUp1 = attackUp1;
    }

    public Image getAttackUp2() {
        return attackUp2;
    }

    public void setAttackUp2(Image attackUp2) {
        this.attackUp2 = attackUp2;
    }

    public Image getAttackDown1() {
        return attackDown1;
    }

    public void setAttackDown1(Image attackDown1) {
        this.attackDown1 = attackDown1;
    }

    public Image getAttackDown2() {
        return attackDown2;
    }

    public void setAttackDown2(Image attackDown2) {
        this.attackDown2 = attackDown2;
    }

    public Image getAttackLeft1() {
        return attackLeft1;
    }

    public void setAttackLeft1(Image attackLeft1) {
        this.attackLeft1 = attackLeft1;
    }

    public Image getAttackLeft2() {
        return attackLeft2;
    }

    public void setAttackLeft2(Image attackLeft2) {
        this.attackLeft2 = attackLeft2;
    }

    public Image getAttackRight1() {
        return attackRight1;
    }

    public void setAttackRight1(Image attackRight1) {
        this.attackRight1 = attackRight1;
    }

    public Image getAttackRight2() {
        return attackRight2;
    }

    public void setAttackRight2(Image attackRight2) {
        this.attackRight2 = attackRight2;
    }

    public boolean getAttacking() {
        return isAttacking();
    }
}
