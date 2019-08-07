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
package com.neutral.xianxia.logic;

import com.neutral.xianxia.logic.battle.BattleManager;
import com.neutral.xianxia.logic.battle.Enemy;
import com.neutral.xianxia.logic.events.Event;
import com.neutral.xianxia.logic.events.EventManager;
import com.neutral.xianxia.logic.levels.BodyLevel;
import com.neutral.xianxia.logic.levels.CultivationLevel;
import com.neutral.xianxia.logic.levels.QiLevel;
import java.util.Random;
import java.util.Timer;

/**
 *
 * @author Mr.Neutral
 */
public final class System {

    private static final EventManager EVENT_MANAGER = new EventManager();
    private static final Player PLAYER = new Player();
    private static final BattleManager BATTLE_MANAGER = new BattleManager();
    private static boolean tribulationDue = false;
    private static String battleAction = "";
    private static boolean toggleQiUpgrade = false;
    private static boolean toggleBodyUpgrade = false;
    private static Timer timer = null;

    private System() {
    }

    public static final void cultivate() {
        PLAYER.cultivate();
    }

    public static final Event getEvent() {
        return EVENT_MANAGER.getRandomEvent();
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

    public static final void attemptLevelUp(String target) {

        int cost;
        if (getUpgradeCost(target) == null) {
            return;
        }
        cost = getUpgradeCost(target);
        if (cost <= PLAYER.getExp()) {
            PLAYER.grantExp(-cost);
            if (target.equals("Body")) {
                PLAYER.levelBody();
            } else {
                PLAYER.levelQi();
            }
            PLAYER.checkRealm();
        }
    }

    public static final void checkPlayerRealm() {
        PLAYER.checkRealm();
    }

    public static final Integer getUpgradeCost(String target) {
        switch (target) {
            case "Body":
                if (getNextLevel(PLAYER.getBodyLevel()) == null) {
                    return null;
                }
                return getNextLevel(PLAYER.getBodyLevel()).getCost();
            case "Qi":
                if (getNextLevel(PLAYER.getQiLevel()) == null) {
                    return null;
                }
                return getNextLevel(PLAYER.getQiLevel()).getCost();
        }
        return null;
    }

    public static final int getPlayerExp() {
        return PLAYER.getExp();
    }

    public static final int getPlayerHealth() {
        return PLAYER.getHealth();
    }

    public static final int getPlayerSpirit() {
        return PLAYER.getSpirit();
    }

    public static final int getPlayerMaxHealth() {
        return PLAYER.getMaxHealth();
    }

    public static final int getPlayerMaxSpirit() {
        return PLAYER.getMaxSpirit();
    }

    public static final double getPlayerAttack() {
        return PLAYER.getPhysicalAttack();
    }

    public static final double getPlayerDefence() {
        return PLAYER.getPhysicalDefence();
    }

    public static final double getPlayerExpMultiplier() {
        return PLAYER.getExpMultiplier();
    }

    public static final void setPlayerHealth(int health) {
        PLAYER.setHealth(health);
    }

    public static final void setPlayerSpirit(int spirit) {
        PLAYER.setSpirit(spirit);
    }

    public static final void setPlayerMaxHealth(int health) {
        PLAYER.setMaxHealth(health);
    }

    public static final void setPlayerMaxSpirit(int spirit) {
        PLAYER.setMaxSpirit(spirit);
    }

    public static final void setPlayerExpMultiplier(double multiplier) {
        PLAYER.setExpMultiplier(multiplier);
    }

    public static final void setPlayerBody(int level) {
        PLAYER.setBodyLevel(BodyLevel.getRealm(level));
    }

    public static final void upgradePlayerBody(int level) {
        while (level > PLAYER.getBodyLevel().getRank()) {
            PLAYER.levelBody();
            if (getNextLevel(PLAYER.getBodyLevel()) == null) {
                break;
            }
        }
    }

    public static final void setPlayerQi(int level) {
        PLAYER.setQiLevel(QiLevel.getRealm(level));
    }

    public static final void upgradePlayerQi(int level) {
        while (level > PLAYER.getQiLevel().getRank()) {
            PLAYER.levelQi();
            if (getNextLevel(PLAYER.getQiLevel()) == null) {
                break;
            }
        }
    }

    public static final CultivationLevel getPlayerRealm() {
        return PLAYER.getCultivationRealm();
    }

    public static final BodyLevel getPlayerBody() {
        return PLAYER.getBodyLevel();
    }

    public static final QiLevel getPlayerQi() {
        return PLAYER.getQiLevel();
    }

    public static final void grantExp(int exp) {
        PLAYER.grantExp(exp);
    }

    public static final boolean canTribulationUpgrade(String target) {
        if (!tribulationDue && getUpgradeCost(target) != null) {
            switch (target) {
                case "Body":
                    if (PLAYER.getCultivationRealm() == CultivationLevel.CORE_FORMATION_REALM && PLAYER.getBodyLevel() == BodyLevel.CORE_FORMATION_BODY_STAGE_9) {
                        return false;
                    } else if (PLAYER.getCultivationRealm() == CultivationLevel.SAGE_REALM && PLAYER.getBodyLevel() == BodyLevel.SAGE_BODY_STAGE_9) {
                        return false;
                    }
                    return getUpgradeCost(target) <= PLAYER.getExp();
                case "Qi":
                    if (PLAYER.getCultivationRealm() == CultivationLevel.CORE_FORMATION_REALM && PLAYER.getQiLevel() == QiLevel.CORE_FORMATION_SPIRIT_STAGE_9) {
                        return false;
                    } else if (PLAYER.getCultivationRealm() == CultivationLevel.SAGE_REALM && PLAYER.getQiLevel() == QiLevel.SAGE_SPIRIT_STAGE_9) {
                        return false;
                    }
                    return getUpgradeCost(target) <= PLAYER.getExp();
                default:
                    return getUpgradeCost(target) <= PLAYER.getExp();
            }
        }
        return getUpgradeCost(target) <= PLAYER.getExp();
    }

    public static final boolean canLevel() {
        if (getUpgradeCost("Body") != null || getUpgradeCost("Qi") != null) {
            return (canTribulationUpgrade("Body")) ? true : canTribulationUpgrade("Qi");
        }
        return false;
    }

    public static final boolean canLevel(String target) {
        if (getUpgradeCost(target) != null) {
            return canTribulationUpgrade(target);
        }
        return false;
    }

    public static final boolean checkTribulation() {
        CultivationLevel realm = PLAYER.getCultivationRealm();
        if (realm == CultivationLevel.CORE_FORMATION_REALM) {
            if (PLAYER.getBodyLevel() == BodyLevel.CORE_FORMATION_BODY_STAGE_9 && PLAYER.getQiLevel() == QiLevel.CORE_FORMATION_SPIRIT_STAGE_9) {
                tribulationDue = true;
                return true;
            }
        } else if (realm == CultivationLevel.SAGE_REALM) {
            if (PLAYER.getBodyLevel() == BodyLevel.SAGE_BODY_STAGE_9 && PLAYER.getQiLevel() == QiLevel.SAGE_SPIRIT_STAGE_9) {
                tribulationDue = true;
                return true;
            }
        }
        return false;
    }

    public static final String triggerTribulation() {
        return EVENT_MANAGER.triggerTribulation();
    }

    public static final boolean isTribulationDue() {
        return tribulationDue;
    }

    /**
     * @param tribulationDue the tribulationDue to set
     */
    public static final void setTribulationDue(boolean tribulationDue) {
        System.tribulationDue = tribulationDue;
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
        System.toggleQiUpgrade = toggleQiUpgrade;
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
        System.toggleBodyUpgrade = toggleBodyUpgrade;
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
        System.timer = timer;
    }

    public static final boolean initBattle() {
        return BATTLE_MANAGER.initBattle();
    }

    public static final void physicalAttackEnemy() {
        BATTLE_MANAGER.physicalAttackEnemy();
    }

    public static final Player getPLAYER() {
        return PLAYER;
    }

    public static final void spiritAttackEnemy() {
        BATTLE_MANAGER.spiritAttackEnemy();
    }

    public static final void defendWithBody() {
        PLAYER.setDefendingWithBody(true);
        System.setBattleAction("You prepare to defend against a physical attack.");
    }

    public static final void defendWithSpirit() {
        PLAYER.setDefendingWithQi(true);
        System.setBattleAction("You prepare to defend against a spiritual attack.");
    }

    public static final void nextTurn() {
        BATTLE_MANAGER.nextTurn();
    }

    public static final Enemy getEnemy() {
        return BATTLE_MANAGER.getEnemy();
    }

    public static final void setBattleAction(String string) {
        System.battleAction = string;
    }

    public static final String getBattleAction() {
        return battleAction;
    }

    public static final boolean isBattleFinished() {
        return BATTLE_MANAGER.isBattleFinished();
    }

    public static final void resetBattle() {
        BATTLE_MANAGER.resetBattle();
    }

    public static final boolean isPlayerWon() {
        return BATTLE_MANAGER.isPlayerWon();
    }

    public static final int calculateBattleExp() {
        return BATTLE_MANAGER.getEnemy().getMaxHealth() * 2 + BATTLE_MANAGER.getEnemy().getMaxSpirit() * 2 + new Random().nextInt(101);
    }
}
