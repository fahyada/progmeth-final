package entity;

import entity.monsters.MonsterBase;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class Entity {
    private int hp;
    private int maxHp;
    private int atk;
    private int range;
    private double x, y;
    private double size;
    private int speed;
    private Image up1, up2, down1, down2, left1, left2, right1, right2;
    private Image attackUp, attackDown, attackLeft, attackRight;
    private Image attackEffect1, attackEffect2;
    private String direction;
    private int spriteCounter = 0;
    private int spriteNum = 1;
    private Rectangle solidArea;
    private Rectangle attackArea;
    private boolean collisionOn = false;
    private boolean attacking = false;

    // constructor
    public Entity(int hp, int maxHp, int atk, int range, int speed, double size) {
        setHp(hp);
        setMaxHp(maxHp);
        setAtk(atk);
        setRange(range);
        setSpeed(speed);
        setSize(size);
        setMovementImages();
        setAttackImages();
    }

    // methods
    public boolean isAlive() {
        return getHp() > 0;
    }

    /*
    abstract methods
    1. updatePosition()

     */

    public void setMovementImages() {};
    public void setAttackImages() {};
    public void attack(MonsterBase monster) {};
    public void attack(Player player) {};

    // getter and setter
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Image getUp1() {
        return up1;
    }

    public void setUp1(Image up1) {
        this.up1 = up1;
    }

    public Image getUp2() {
        return up2;
    }

    public void setUp2(Image up2) {
        this.up2 = up2;
    }

    public Image getDown1() {
        return down1;
    }

    public void setDown1(Image down1) {
        this.down1 = down1;
    }

    public Image getDown2() {
        return down2;
    }

    public void setDown2(Image down2) {
        this.down2 = down2;
    }

    public Image getLeft1() {
        return left1;
    }

    public void setLeft1(Image left1) {
        this.left1 = left1;
    }

    public Image getLeft2() {
        return left2;
    }

    public void setLeft2(Image left2) {
        this.left2 = left2;
    }

    public Image getRight1() {
        return right1;
    }

    public void setRight1(Image right1) {
        this.right1 = right1;
    }

    public Image getRight2() {
        return right2;
    }

    public void setRight2(Image right2) {
        this.right2 = right2;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0, hp);
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

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Rectangle getAttackArea() {
        return attackArea;
    }

    public void setAttackArea(Rectangle attackArea) {
        this.attackArea = attackArea;
    }
    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public Image getAttackEffect1() {
        return attackEffect1;
    }

    public void setAttackEffect1(Image attackEffect1) {
        this.attackEffect1 = attackEffect1;
    }

    public Image getAttackEffect2() {
        return attackEffect2;
    }

    public void setAttackEffect2(Image attackEffect2) {
        this.attackEffect2 = attackEffect2;
    }

    public Image getAttackUp() {
        return attackUp;
    }

    public void setAttackUp(Image attackUp) {
        this.attackUp = attackUp;
    }

    public Image getAttackDown() {
        return attackDown;
    }

    public void setAttackDown(Image attackDown) {
        this.attackDown = attackDown;
    }

    public Image getAttackLeft() {
        return attackLeft;
    }

    public void setAttackLeft(Image attackLeft) {
        this.attackLeft = attackLeft;
    }

    public Image getAttackRight() {
        return attackRight;
    }

    public void setAttackRight(Image attackRight) {
        this.attackRight = attackRight;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }
}