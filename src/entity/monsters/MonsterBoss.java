package entity.monsters;

import javafx.scene.shape.Rectangle;
import panes.GamePanel;

import static utils.GetData.getImage;

public class MonsterBoss extends MonsterBase {
    public MonsterBoss() {
        super(500,500,10,80,2,2.5 * GamePanel.tileSize);
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) getX(), (int) getY(), 2* GamePanel.tileSize, 2*GamePanel.tileSize);
    }

    @Override
    public void setMovementImages() {
        setUp1(getImage("MonsterBoss/bossup1.png"));
        setUp2(getImage("MonsterBoss/bossup2.png"));
        setDown1(getImage("MonsterBoss/bossdown_left1.png"));
        setDown2(getImage("MonsterBoss/bossdown_left2.png"));
        setLeft1(getImage("MonsterBoss/bossdown_left1.png"));
        setLeft2(getImage("MonsterBoss/bossdown_left2.png"));
        setRight1(getImage("MonsterBoss/bossright1.png"));
        setRight2(getImage("MonsterBoss/bossright2.png"));
    }

    public void setAttackImages() {
        setAttackUp1(getImage("MonsterBoss/bossattackup1.png"));
        setAttackUp2(getImage("MonsterBoss/bossattackup2.png"));
        setAttackDown1(getImage("MonsterBoss/bossattackdown_left1.png"));
        setAttackDown2(getImage("MonsterBoss/bossattackdown_left2.png"));
        setAttackLeft1(getImage("MonsterBoss/bossattackdown_left1.png"));
        setAttackLeft2(getImage("MonsterBoss/bossattackdown_left2.png"));
        setAttackRight1(getImage("MonsterBoss/bossattackright1.png"));
        setAttackRight2(getImage("MonsterBoss/bossattackright2.png"));
        setAttackEffect1(getImage("MonsterBoss/bossattackeffect1.png"));
        setAttackEffect2(getImage("MonsterBoss/bossattackeffect2.png"));
    }
}
