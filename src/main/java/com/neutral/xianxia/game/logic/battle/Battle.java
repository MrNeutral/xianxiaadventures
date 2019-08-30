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
package com.neutral.xianxia.game.logic.battle;

import com.neutral.xianxia.game.logic.Cultivator;
import com.neutral.xianxia.game.logic.Player;
import java.util.Random;

/**
 *
 * @author Mr.Neutral
 */
public class Battle {

    private final Random random = new Random();
    private int handicap = 0;
    private boolean battleFinished = false;
    private boolean playerWon = false;
    private boolean playerBattle = false;
    private final Cultivator enemy;
    private final Player player;
    private String battleHistory = "";

    public Battle(Player player) {
        this.player = player;
        this.enemy = new Enemy();
    }

    public Battle(Player player, Player enemyPlayer) {
        this.player = player;
        this.enemy = enemyPlayer;
        playerBattle = true;
    }

    public boolean isBattleFinished() {
        return battleFinished;
    }

    public void setHandicap(int handicap) {
        this.handicap = handicap;
    }

    public int getHandicap() {
        return handicap;
    }

    public String getBattleHistory() {
        return battleHistory;
    }

    public boolean isPlayerWon() {
        return playerWon;
    }

    public void setBattleFinished(boolean battleFinished) {
        this.battleFinished = battleFinished;
    }

    public void setPlayerWon(boolean playerWon) {
        this.playerWon = playerWon;
    }

    public Player getPlayer() {
        return player;
    }

    public Cultivator getEnemy() {
        return enemy;
    }

    public void nextTurn(Player player) {
        enemy.setDefendingWithBody(false);
        enemy.setDefendingWithQi(false);
        if (random.nextInt(11) > 7) {
            if (random.nextBoolean() && enemy.getQiLevel().getRank() != 0 && enemy.getSpirit() > 0) {
                enemy.setDefendingWithQi(true);
                battleHistory += ((playerBattle) ? ((Player) enemy).getName() : "The enemy") + " prepares to defend against a spiritual attack.\n";
            } else {
                enemy.setDefendingWithBody(true);
                battleHistory += ((playerBattle) ? ((Player) enemy).getName() : "The enemy") + " prepares to defend against a physical attack.\n";
            }
        } else {
            if (random.nextBoolean() && enemy.getQiLevel().getRank() != 0 && enemy.getSpirit() > 0) {
                enemySpiritAttacks(player);
            } else {
                enemyPhysAttacks(player);
            }
        }
        checkBattleStatus(player);
        if (!battleFinished) {
            nextPlayerTurn(player);
        }
    }

    public void nextPlayerTurn(Player player) {
        player.setDefendingWithBody(false);
        player.setDefendingWithQi(false);
        if (random.nextInt(11) > 7) {
            if (random.nextBoolean() && player.getQiLevel().getRank() != 0 && player.getSpirit() > 0) {
                player.setDefendingWithQi(true);
                battleHistory += "You prepare to defend against a spiritual attack.\n";
            } else {
                player.setDefendingWithBody(true);
                battleHistory += "You prepare to defend against a physical attack.\n";
            }
        } else {
            if (random.nextBoolean() && player.getQiLevel().getRank() != 0 && player.getSpirit() > 0) {
                spiritAttackEnemy(player);
            } else {
                physicalAttackEnemy(player);
            }
        }
        checkBattleStatus(player);
        if (!battleFinished) {
            nextTurn(player);
        }
    }

    public void physicalAttackEnemy(Player player) {
        enemy.defendPhysical(player, this);
    }

    public void enemyPhysAttacks(Player player) {
        enemy.attackPhysical(player, this);
    }

    public void spiritAttackEnemy(Player player) {
        enemy.defendSpirit(player, this);
    }

    public void enemySpiritAttacks(Player player) {
        enemy.attackSpirit(player, this);
    }

    public void addToBattleHistory(String text) {
        battleHistory += (text + "\n");
    }

    public void checkBattleStatus(Player player) {
        if (player.getHealth() == 0) {
            setPlayerWon(false);
            setBattleFinished(true);
        } else if (enemy.getHealth() == 0) {
            setPlayerWon(true);
            setBattleFinished(true);
        } else {
            setPlayerWon(false);
            setBattleFinished(false);
        }
    }

}
