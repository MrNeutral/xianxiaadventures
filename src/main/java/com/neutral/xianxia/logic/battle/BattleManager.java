/*
 * Copyright (C) 2019 Mr.Neutral
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.neutral.xianxia.logic.battle;

import com.neutral.xianxia.logic.Player;
import com.neutral.xianxia.logic.System;
import static com.neutral.xianxia.logic.System.getNextLevel;
import java.util.Random;

/**
 *
 * @author Mr.Neutral
 */
public class BattleManager {

    private final Player player = System.getPLAYER();
    private boolean battleFinished = false;
    private boolean playerWon = false;
    private final Enemy enemy = new Enemy();
    private final Random random = new Random();

    public void resetBattle() {
        enemy.resetValues();
        System.setBattleAction("");
        battleFinished = false;
        playerWon = false;
    }

    public boolean initBattle() {
        resetBattle();
        int handicap;
        switch (random.nextInt(3)) {
            case 0:
                handicap = random.nextInt(3) - 3;
                // handicap range: -3, -2, -1
                break;
            case 1:
                handicap = random.nextInt(3) - 1;
                break;
            case 2:
                handicap = random.nextInt(3) + 1;
                break;
            default:
                handicap = 0;
                break;
        }

        while (enemy.getQiLevel().getRank() < (System.getPlayerQi().getRank() - handicap)) {
            if (getNextLevel(enemy.getQiLevel()) == null) {
                break;
            }
            enemy.levelQi();
        }
        while (enemy.getBodyLevel().getRank() < (System.getPlayerBody().getRank() - handicap)) {
            if (getNextLevel(enemy.getBodyLevel()) == null) {
                break;
            }
            enemy.levelBody();
        }
        enemy.checkRealm();

        return random.nextBoolean();
    }

    public void nextTurn() {
        enemy.setDefendingWithBody(false);
        enemy.setDefendingWithQi(false);
        if (random.nextInt(11) > 7) {
            if (random.nextBoolean() && enemy.getQiLevel().getRank() != 0 && enemy.getSpirit() > 0) {
                enemy.setDefendingWithQi(true);
                System.setBattleAction("The enemy prepares to defend against a spiritual attack.");
            } else {
                enemy.setDefendingWithBody(true);
                System.setBattleAction("The enemy prepares to defend against a physical attack.");
            }
        } else {
            if (random.nextBoolean() && enemy.getQiLevel().getRank() != 0 && enemy.getSpirit() > 0) {
                enemySpiritAttacks();
            } else {
                enemyPhysAttacks();
            }
        }
        checkBattleStatus();

    }

    public void physicalAttackEnemy() {
        enemy.defendPhysical(player);
    }

    public void enemyPhysAttacks() {
        enemy.attackPhysical(player);
    }

    public void spiritAttackEnemy() {
        enemy.defendSpirit(player);
    }

    public void enemySpiritAttacks() {
        enemy.attackSpirit(player);
    }

    public void checkBattleStatus() {
        if (player.getHealth() == 0) {
            playerWon = false;
            battleFinished = true;
        } else if (enemy.getHealth() == 0) {
            playerWon = true;
            battleFinished = true;
        } else {
            playerWon = false;
            battleFinished = false;
        }
    }

    /**
     * @return the battleFinished
     */
    public boolean isBattleFinished() {
        return battleFinished;
    }

    /**
     * @return the playerWon
     */
    public boolean isPlayerWon() {
        return playerWon;
    }

    public Enemy getEnemy() {
        return enemy;
    }

}
