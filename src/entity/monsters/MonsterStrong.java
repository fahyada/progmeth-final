package entity.monsters;

import javafx.scene.shape.Rectangle;
import panes.GamePanel;

import static utils.GetData.getImage;

public class MonsterStrong extends MonsterBase {
    // 2nd strongest monster (only appears on level 2,3)
    public MonsterStrong() {
        super(100,100,8,80,3,1.5 * GamePanel.tileSize);
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) getX(), (int) getY(), 2* GamePanel.tileSize, 2*GamePanel.tileSize);
    }

    @Override
    public void setMovementImages() {
        setUp1(getImage("MonsterStrong/strongup1.png"));
        setUp2(getImage("MonsterStrong/strongup2.png"));
        setDown1(getImage("MonsterStrong/strongdown_left1.png"));
        setDown2(getImage("MonsterStrong/strongdown_left2.png"));
        setLeft1(getImage("MonsterStrong/strongdown_left1.png"));
        setLeft2(getImage("MonsterStrong/strongdown_left2.png"));
        setRight1(getImage("MonsterStrong/strongright1.png"));
        setRight2(getImage("MonsterStrong/strongright2.png"));
    }

    @Override
    public void setAttackImages() {
        setAttackUp1(getImage("MonsterStrong/strongattackup1.png"));
        setAttackUp2(getImage("MonsterStrong/strongattackup2.png"));
        setAttackDown1(getImage("MonsterStrong/strongattackdown_left1.png"));
        setAttackDown2(getImage("MonsterStrong/strongattackdown_left2.png"));
        setAttackLeft1(getImage("MonsterStrong/strongattackdown_left1.png"));
        setAttackLeft2(getImage("MonsterStrong/strongattackdown_left2.png"));
        setAttackRight1(getImage("MonsterStrong/strongattackright1.png"));
        setAttackRight2(getImage("MonsterStrong/strongattackright2.png"));
        setAttackEffect1(getImage("MonsterStrong/strongattackeffect1.png"));
        setAttackEffect2(getImage("MonsterStrong/strongattackeffect2.png"));
    }
}
