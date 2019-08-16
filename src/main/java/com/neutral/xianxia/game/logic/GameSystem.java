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
package com.neutral.xianxia.game.logic;

import com.neutral.xianxia.game.logic.battle.Battle;
import com.neutral.xianxia.game.logic.battle.BattleManager;
import com.neutral.xianxia.game.logic.battle.Enemy;
import com.neutral.xianxia.game.logic.events.EventManager;
import com.neutral.xianxia.game.logic.events.GameEvent;
import com.neutral.xianxia.game.logic.levels.BodyLevel;
import com.neutral.xianxia.game.logic.levels.CultivationLevel;
import com.neutral.xianxia.game.logic.levels.QiLevel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

/**
 *
 * @author Mr.Neutral
 */
public final class GameSystem {

    private static final EventManager EVENT_MANAGER = new EventManager();
    private static final BattleManager BATTLE_MANAGER = new BattleManager();
    private static List<Player> players = new ArrayList<>();
    private static boolean tribulationDue = false;
    private static boolean toggleQiUpgrade = false;
    private static boolean toggleBodyUpgrade = false;
    private static Timer timer = null;

    private GameSystem() {
    }

    public static final void addPlayer(Player player) {
        players.add(player);
    }

    public static final void deletePlayer(String id) {
        Player toRemove = null;
        for (Player player : players) {
            toRemove = (player.getID().equals(id)) ? player : toRemove;
            if (toRemove != null) {
                break;
            }
        }
    }

    public static final void addPlayers(List<Player> newPlayers) {
        players.addAll(newPlayers);
    }

    public static List<Player> getPlayers() {
        return players;
    }

    public static final void cultivate(Player player) {
        player.cultivate();
    }

    public static final GameEvent getEvent(Player player) {
        return EVENT_MANAGER.getRandomEvent(player);
    }

    public static final QiLevel[] getQiLevels() {
        return QiLevel.values();
    }

    public static final BodyLevel[] getBodyLevels() {
        return BodyLevel.values();
    }

    public static final BodyLevel getNextLevel(BodyLevel currLevel) {
        BodyLevel[] levels = BodyLevel.values();
        for (int i = 0; i < levels.length; ++i) {
            if (currLevel == levels[i] && i + 1 < levels.length) {
                return levels[i + 1];
            }
        }
        return null;
    }

    public static final QiLevel getNextLevel(QiLevel currLevel) {
        QiLevel[] levels = QiLevel.values();
        for (int i = 0; i < levels.length; ++i) {
            if (currLevel == levels[i] && i + 1 < levels.length) {
                return levels[i + 1];
            }
        }
        return null;
    }

    public static final CultivationLevel getNextLevel(CultivationLevel currLevel) {
        CultivationLevel[] levels = CultivationLevel.values();
        for (int i = 0; i < levels.length; ++i) {
            if (currLevel == levels[i] && i + 1 < levels.length) {
                return levels[i + 1];
            }
        }
        return null;
    }

    public static final void attemptLevelUp(String target, Player player) {
        int cost;

        if (getUpgradeCost(target, player) == null) {
            return;
        }

        cost = getUpgradeCost(target, player);
        if (cost <= player.getExp()) {
            player.grantExp(-cost);
            if (target.equals("Body")) {
                player.levelBody();
            } else {
                player.levelQi();
            }
            player.checkRealm();
        }
    }

    public static final void checkPlayerRealm(Player player) {
        player.checkRealm();
    }

    public static final Integer getUpgradeCost(String target, Player player) {
        switch (target) {
            case "Body":
                if (getNextLevel(player.getBodyLevel()) == null) {
                    return null;
                }
                return getNextLevel(player.getBodyLevel()).getCost();
            case "Qi":
                if (getNextLevel(player.getQiLevel()) == null) {
                    return null;
                }
                return getNextLevel(player.getQiLevel()).getCost();
        }
        return null;
    }

    public static final int getPlayerExp(Player player) {
        return player.getExp();
    }

    public static final int getPlayerHealth(Player player) {
        return player.getHealth();
    }

    public static final int getPlayerSpirit(Player player) {
        return player.getSpirit();
    }

    public static final int getPlayerMaxHealth(Player player) {
        return player.getMaxHealth();
    }

    public static final int getPlayerMaxSpirit(Player player) {
        return player.getMaxSpirit();
    }

    public static final double getPlayerAttack(Player player) {
        return player.getPhysicalAttack() + player.getSpiritAttack();
    }

    public static final double getPlayerDefence(Player player) {
        return player.getPhysicalDefence() + player.getSpiritDefense();
    }

    public static final double getPlayerExpMultiplier(Player player) {
        return player.getExpMultiplier();
    }

    public static final void setPlayerHealth(int health, Player player) {
        player.setHealth(health);
    }

    public static final void setPlayerSpirit(int spirit, Player player) {
        player.setSpirit(spirit);
    }

    public static final void setPlayerMaxHealth(int health, Player player) {
        player.setMaxHealth(health);
    }

    public static final void setPlayerMaxSpirit(int spirit, Player player) {
        player.setMaxSpirit(spirit);
    }

    public static final void setPlayerExpMultiplier(double multiplier, Player player) {
        player.setExpMultiplier(multiplier);
    }

    public static final void setPlayerBody(int level, Player player) {
        player.setBodyLevel(BodyLevel.getRealm(level));
    }

    public static final void upgradePlayerBody(int level, Player player) {
        while (level > player.getBodyLevel().getRank()) {
            player.levelBody();
            if (getNextLevel(player.getBodyLevel()) == null) {
                break;
            }
        }
    }

    public static final void setPlayerQi(int level, Player player) {
        player.setQiLevel(QiLevel.getRealm(level));
    }

    public static final void upgradePlayerQi(int level, Player player) {
        while (level > player.getQiLevel().getRank()) {
            player.levelQi();
            if (getNextLevel(player.getQiLevel()) == null) {
                break;
            }
        }
    }

    public static final CultivationLevel getPlayerRealm(Player player) {
        return player.getCultivationRealm();
    }

    public static final BodyLevel getPlayerBody(Player player) {
        return player.getBodyLevel();
    }

    public static final QiLevel getPlayerQi(Player player) {
        return player.getQiLevel();
    }

    public static final void grantExp(int exp, Player player) {
        player.grantExp(exp);
    }

    public static final boolean canTribulationUpgrade(String target, Player player) {
        if (!tribulationDue && getUpgradeCost(target, player) != null) {
            switch (target) {
                case "Body":
                    if (player.getCultivationRealm() == CultivationLevel.CORE_FORMATION_REALM && player.getBodyLevel() == BodyLevel.CORE_FORMATION_BODY_STAGE_9) {
                        return false;
                    } else if (player.getCultivationRealm() == CultivationLevel.SAGE_REALM && player.getBodyLevel() == BodyLevel.SAGE_BODY_STAGE_9) {
                        return false;
                    }
                    return getUpgradeCost(target, player) <= player.getExp();
                case "Qi":
                    if (player.getCultivationRealm() == CultivationLevel.CORE_FORMATION_REALM && player.getQiLevel() == QiLevel.CORE_FORMATION_SPIRIT_STAGE_9) {
                        return false;
                    } else if (player.getCultivationRealm() == CultivationLevel.SAGE_REALM && player.getQiLevel() == QiLevel.SAGE_SPIRIT_STAGE_9) {
                        return false;
                    }
                    return getUpgradeCost(target, player) <= player.getExp();
                default:
                    return getUpgradeCost(target, player) <= player.getExp();
            }
        }
        return false;
    }

    public static final boolean canLevel(Player player) {
        if (getUpgradeCost("Body", player) != null || getUpgradeCost("Qi", player) != null) {
            return (canTribulationUpgrade("Body", player)) ? true : canTribulationUpgrade("Qi", player);
        }
        return false;
    }

    public static final boolean canLevel(String target, Player player) {
        if (getUpgradeCost(target, player) != null) {
            return canTribulationUpgrade(target, player);
        }
        return false;
    }

    public static final boolean checkTribulation(Player player) {
        CultivationLevel realm = player.getCultivationRealm();
        if (realm == CultivationLevel.CORE_FORMATION_REALM) {
            if (player.getBodyLevel() == BodyLevel.CORE_FORMATION_BODY_STAGE_9 && player.getQiLevel() == QiLevel.CORE_FORMATION_SPIRIT_STAGE_9) {
                tribulationDue = true;
            }
        } else if (realm == CultivationLevel.SAGE_REALM) {
            if (player.getBodyLevel() == BodyLevel.SAGE_BODY_STAGE_9 && player.getQiLevel() == QiLevel.SAGE_SPIRIT_STAGE_9) {
                tribulationDue = true;
            }
        }
        return tribulationDue;
    }

    public static final String triggerTribulation(Player player) {
        return EVENT_MANAGER.triggerTribulation(player);
    }

    public static final boolean isTribulationDue() {
        return tribulationDue;
    }

    /**
     * @param tribulationDue the tribulationDue to set
     */
    public static final void setTribulationDue(boolean tribulationDue) {
        GameSystem.tribulationDue = tribulationDue;
    }

    /**
     * @return the toggleQiUpgrade
     */
    public static final boolean isToggleQiUpgrade() {
        return toggleQiUpgrade;
    }

    /**
     * @param toggleQiUpgrade the toggleQiUpgrade to set
     */
    public static final void setToggleQiUpgrade(boolean toggleQiUpgrade) {
        GameSystem.toggleQiUpgrade = toggleQiUpgrade;
    }

    /**
     * @return the toggleBodyUpgrade
     */
    public static final boolean isToggleBodyUpgrade() {
        return toggleBodyUpgrade;
    }

    /**
     * @param toggleBodyUpgrade the toggleBodyUpgrade to set
     */
    public static final void setToggleBodyUpgrade(boolean toggleBodyUpgrade) {
        GameSystem.toggleBodyUpgrade = toggleBodyUpgrade;
    }

    /**
     * @return the timer
     */
    public static final Timer getTimer() {
        return timer;
    }

    /**
     * @param timer the timer to set
     */
    public static final void setTimer(Timer timer) {
        GameSystem.timer = timer;
    }

    public static final boolean initBattle(Player player) {
        return BATTLE_MANAGER.initBattle(player);
    }

    public static final void physicalAttackEnemy(Player player) {
        BATTLE_MANAGER.getBattle(player.getID()).physicalAttackEnemy(player);
    }

    public static final Player getPlayer(String ID) {
        Player result = null;
        for (Player player : players) {
            result = (player.getID().equals(ID)) ? player : result;
        }
        return result;
    }

    public static final void spiritAttackEnemy(Player player) {
        BATTLE_MANAGER.getBattle(player.getID()).spiritAttackEnemy(player);
    }

    public static final void defendWithBody(Player player, Battle battle) {
        player.setDefendingWithBody(true);
        battle.addToBattleHistory("You prepare to defend against a physical attack.");
    }

    public static final void defendWithSpirit(Player player, Battle battle) {
        player.setDefendingWithQi(true);
        battle.addToBattleHistory("You prepare to defend against a spiritual attack.");
    }

    public static final void nextTurn(Player player) {
        BATTLE_MANAGER.getBattle(player.getID()).nextTurn(player);
    }

    public static final void nextPlayerTurn(Player player) {
        BATTLE_MANAGER.getBattle(player.getID()).nextPlayerTurn(player);
    }

    public static final Enemy getEnemy(Player player) {
        return BATTLE_MANAGER.getBattle(player.getID()).getEnemy();
    }

    public static final boolean isBattleFinished(Player player) {
        return BATTLE_MANAGER.getBattle(player.getID()).isBattleFinished();
    }

    public static final boolean isPlayerWon(Player player) {
        return BATTLE_MANAGER.getBattle(player.getID()).isPlayerWon();
    }

    public static final int calculateBattleExp(Player player) {
        return BATTLE_MANAGER.getBattle(player.getID()).getEnemy().getMaxHealth() * 2 + BATTLE_MANAGER.getBattle(player.getID()).getEnemy().getMaxSpirit() * 2 + new Random().nextInt(101);
    }

    public static final void fight(Player player) {
        Random random = new Random();
        initBattle(player);

        if (random.nextBoolean()) {
            nextTurn(player);
        } else {
            nextPlayerTurn(player);
        }

        if (isPlayerWon(player)) {
            int xp = calculateBattleExp(player);
            getBattle(player.getID()).addToBattleHistory("You managed to defeat the enemy.\nYou gain **" + xp + "** exp.");
        } else {
            int xp = calculateBattleExp(player);
            getBattle(player.getID()).addToBattleHistory("You lost but for some reason the enemy decides to spare your pathetic life.\nYou lose " + Math.round(xp * 0.5) + " exp.");
            grantExp((int) -Math.round(xp * 0.5), player);
        }
    }

    public static final Battle getBattle(String id) {
        return BATTLE_MANAGER.getBattle(id);
    }

    public static final String getBattleResults(Battle battle) {
        return battle.getBattleHistory();
    }
}
