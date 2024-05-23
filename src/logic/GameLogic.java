package logic;

import entity.Player;
import entity.monsters.MonsterBase;
import entity.monsters.MonsterField;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.Iterator;

public class GameLogic {
    private MonsterField monsterField;
    private Player player;
    public final int[] currentMonsters = {0}; //
    private int currentWave; //

    private boolean gameOver; //
    private boolean winMessage; //

    // constructor
    public GameLogic(Player player) {
        this.player = player;
        setCurrentWave(1);
    }

    // methods

    public void setUpGame() {
        initializeMonster(currentWave); //
        winMessage = false; //
        gameOver = false; //
    }
    public void initializeMonster(int wave) {
        monsterField = new MonsterField(player); // change para to player, gamelogic
        final int spawnInterval = 3000; // 3 seconds

        PauseTransition pause = new PauseTransition(Duration.millis(spawnInterval));
        pause.setOnFinished(event -> {
            if (currentMonsters[0] < getNumMonster(wave) && !gameOver && !winMessage) {
                if(wave == 1) {
                    monsterField.addMonsterWeak();
                }
                else if(wave == 2) {
                    if (currentMonsters[0]<15) {
                        monsterField.addMonsterStrong();
                    } else if (currentMonsters[0]<17){
                        monsterField.addMonsterWeak();
                    } else if (currentMonsters[0]<20){
                        monsterField.addMonsterStrong();
                    } else if(currentMonsters[0]<23){
                        monsterField.addMonsterWeak();
                    } else if(currentMonsters[0]<26){
                        monsterField.addMonsterStrong();
                    }
                }
                else if(wave == 3){
                    monsterField.addMonsterBoss();
                }
                MonsterBase monster = monsterField.getMonsters().get(monsterField.getMonsters().size() - 1);
                Thread thread = new Thread(() -> {
                    while (monster.isAlive()) {
                        monsterField.updatePosition(monster);
                        monsterField.updateSolidArea(monster);
                        monster.checkAttack(player);
                        player.attack(monster);
                        try {
                            Thread.sleep(30); // Add a small delay to control the update speed
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
                currentMonsters[0]++;
                if (currentMonsters[0] < getNumMonster(wave)) {
                    pause.playFromStart();
                }
            }
        });
        pause.playFromStart();
    }

    public void update() {
        player.updatePosition();
        player.updateAttackArea();
        checkWin();
    }

    public int getNumMonster(int wave) {
        int num = 0;
        if (wave == 1) {
            num = 10;
        } else if (wave == 2) {
            num = 25;
        } else if (wave == 3) {
            num = 27;
        }
        return num;
    }

    public void checkWin() {
        Iterator<MonsterBase> iterator = monsterField.getMonsters().iterator();
        while (iterator.hasNext()) {
            MonsterBase monster = iterator.next();
            if (!monster.isAlive()) {
                iterator.remove();
                monsterField.setMonsterCount(monsterField.getMonsterCount() - 1);
            }
        }
        if (monsterField.getMonsters().isEmpty() & currentMonsters[0] == getNumMonster(currentWave)) {
            if (currentWave > 3) {
                //
            } else if (currentWave < 3) {
                currentWave++;
                System.out.println("new wave");
                System.out.println(currentWave);
                initializeMonster(currentWave);
            } else if (currentWave == 3) {
                winMessage = true;
            }
        }
    }

    public void setCurrentWave(int currentWave) {
        this.currentWave = currentWave;
    }

    public int getCurrentWave() {
        return currentWave;
    }

    public MonsterField getMonsterField() {
        return monsterField;
    }

    public void setMonsterField(MonsterField monsterField) {
        this.monsterField = monsterField;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isWinMessage() {
        return winMessage;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setWinMessage(boolean winMessage) {
        this.winMessage = winMessage;
    }

}
