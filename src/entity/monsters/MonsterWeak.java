package entity.monsters;

import javafx.scene.shape.Rectangle;
import panes.GamePanel;

import static utils.GetData.getImage;

public class MonsterWeak extends MonsterBase {
    // weakest monster (appears on all levels)
    public MonsterWeak() {
        super(100,100,3,50,3, 1.5 * GamePanel.tileSize);
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) getX(), (int) getY(), 1.5*GamePanel.tileSize, 1.5*GamePanel.tileSize);
    }

    @Override
    public void setMovementImages() {
        setUp1(getImage("MonsterWeak/weakup1.png"));
        setUp2(getImage("MonsterWeak/weakup2.png"));
        setDown1(getImage("MonsterWeak/weakdown_left1.png"));
        setDown2(getImage("MonsterWeak/weakdown_left2.png"));
        setLeft1(getImage("MonsterWeak/weakdown_left1.png"));
        setLeft2(getImage("MonsterWeak/weakdown_left2.png"));
        setRight1(getImage("MonsterWeak/weakright1.png"));
        setRight2(getImage("MonsterWeak/weakright2.png"));
    }

    @Override
    public void setAttackImages() {
        setAttackUp1(getImage("MonsterWeak/weakattackup1.png"));
        setAttackUp2(getImage("MonsterWeak/weakattackup2.png"));
        setAttackDown1(getImage("MonsterWeak/weakattackdown_left1.png"));
        setAttackDown2(getImage("MonsterWeak/weakattackdown_left2.png"));
        setAttackLeft1(getImage("MonsterWeak/weakattackdown_left1.png"));
        setAttackLeft2(getImage("MonsterWeak/weakattackdown_left2.png"));
        setAttackRight1(getImage("MonsterWeak/weakattackright1.png"));
        setAttackRight2(getImage("MonsterWeak/weakattackright2.png"));
        setAttackEffect1(getImage("MonsterWeak/weakattackeffect1.png"));
        setAttackEffect2(getImage("MonsterWeak/weakattackeffect2.png"));
    }
}


