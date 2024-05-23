package entity.monsters;

import entity.Player;
import javafx.scene.shape.Rectangle;
import logic.GameLogic;
import panes.GamePanel;

import java.util.ArrayList;
import java.util.Iterator;

public class MonsterField {
    // contains collection of monsters and methods to make monsters move
    // fields
    private ArrayList<MonsterBase> monsters;
    private Player player;
    private int monsterCount;

    // constructor
    public MonsterField(Player player) {
        monsters = new ArrayList<MonsterBase>();
        setPlayer(player);
        monsterCount = 0;
    }

    // methods; add monster
    public void addMonsterWeak() {
        monsters.add(new MonsterWeak());
        monsterCount++;
    }

    public void addMonsterStrong() {
        monsters.add(new MonsterStrong());
        monsterCount++;
    }

    public void addMonsterBoss() {
        monsters.add(new MonsterBoss());
        monsterCount++;
    }

    // methods; update each monster's position

    public void updatePosition(MonsterBase monster) {
        double dx = player.getX() - monster.getX();
        double dy = player.getY() - monster.getY();
        if (monster.getAttacking()) {
            monster.attack(player);
        } else {
            if(Math.abs(dx) > Math.abs(dy)) {
                if(dx > 0) {
                    monster.setDirection("right");
                } else {
                    monster.setDirection("left");
                }
                setSprites(monster);
                monster.setX(monster.getX() + dx / Math.abs(dx) * monster.getSpeed());
            } else {
                if(dy > 0) {
                    monster.setDirection("down");
                } else {
                    monster.setDirection("up");
                }
                setSprites(monster);
                monster.setY(monster.getY() + dy / Math.abs(dy) * monster.getSpeed());
            }
        }
        // Check for collisions with other monsters
        for (MonsterBase otherMonster : monsters) {
            if (otherMonster != monster && monster.getBounds().getBoundsInLocal().intersects(otherMonster.getBounds().getBoundsInLocal())) {
                // Handle collision: move monster away from other monster
                moveMonsterAwayFromOtherMonster(monster, otherMonster);
            }
        }
    }

    public void updateSolidArea(MonsterBase monster) {
        monster.setSolidArea(new Rectangle(monster.getX(),monster.getY(), monster.getSize(), monster.getSize()));
    }

    private void moveMonsterAwayFromOtherMonster(MonsterBase monster, MonsterBase otherMonster) {
        // Calculate the direction to move away from the other monster
        double dx = monster.getX() - otherMonster.getX();
        double dy = monster.getY() - otherMonster.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Move the monster away from the other monster
        monster.setX(monster.getX() + dx / distance * monster.getSpeed());
        monster.setY(monster.getY() + dy / distance * monster.getSpeed());
    }

    public void setSprites(MonsterBase monster) {
        monster.setSpriteCounter(monster.getSpriteCounter() + 1);
        if(monster.getSpriteCounter() > 12) {
            if(monster.getSpriteNum() == 1) {
                monster.setSpriteNum(2);
            }
            else if(monster.getSpriteNum() == 2) {
                monster.setSpriteNum(1);
            }
            monster.setSpriteCounter(0);
        }
    }

    // getters setters
    public ArrayList<MonsterBase> getMonsters() {
        return monsters;
    }

    public void setMonsters(ArrayList<MonsterBase> monsters) {
        this.monsters = monsters;
    }

    public int getMonsterCount() {
        return monsterCount;
    }

    public void setMonsterCount(int monsterCount) {
        this.monsterCount = monsterCount;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
