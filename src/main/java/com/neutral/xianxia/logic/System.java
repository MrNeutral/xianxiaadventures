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

import com.neutral.xianxia.logic.events.Event;
import com.neutral.xianxia.logic.events.EventManager;
import com.neutral.xianxia.logic.levels.BodyLevel;
import com.neutral.xianxia.logic.levels.CultivationLevel;
import com.neutral.xianxia.logic.levels.QiLevel;
import java.util.Timer;

/**
 *
 * @author Mr.Neutral
 */
public final class System {

    private final EventManager eventManager = new EventManager(this);
    private final Player player = new Player();
    private boolean tribulationDue = false;
    private boolean toggleQiUpgrade = false;
    private boolean toggleBodyUpgrade = false;
    private Timer timer = null;

    public final void cultivate() {
        player.cultivate();
    }

    public final Event getEvent() {
        return eventManager.getRandomEvent();
    }

    public final static QiLevel[] getQiLevels() {
        return QiLevel.values();
    }

    public final static BodyLevel[] getBodyLevels() {
        return BodyLevel.values();
    }

    public final static BodyLevel getNextLevel(BodyLevel currLevel) {
        BodyLevel[] levels = BodyLevel.values();
        for (int i = 0; i < levels.length; ++i) {
            if (currLevel == levels[i] && i + 1 < levels.length) {
                return levels[i + 1];
            }
        }
        return null;
    }

    public final static QiLevel getNextLevel(QiLevel currLevel) {
        QiLevel[] levels = QiLevel.values();
        for (int i = 0; i < levels.length; ++i) {
            if (currLevel == levels[i] && i + 1 < levels.length) {
                return levels[i + 1];
            }
        }
        return null;
    }

    public final static CultivationLevel getNextLevel(CultivationLevel currLevel) {
        CultivationLevel[] levels = CultivationLevel.values();
        for (int i = 0; i < levels.length; ++i) {
            if (currLevel == levels[i] && i + 1 < levels.length) {
                return levels[i + 1];
            }
        }
        return null;
    }

    public final void attemptLevelUp(String target) {

        int cost;
        if (getUpgradeCost(target) == null) {
            return;
        }
        cost = getUpgradeCost(target);
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

    public final void checkPlayerRealm() {
        player.checkRealm();
    }

    public final Integer getUpgradeCost(String target) {
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

    public final int getPlayerExp() {
        return player.getExp();
    }

    public final int getPlayerHealth() {
        return player.getHealth();
    }

    public final int getPlayerSpirit() {
        return player.getSpirit();
    }

    public final int getPlayerMaxHealth() {
        return player.getMaxHealth();
    }

    public final int getPlayerMaxSpirit() {
        return player.getMaxSpirit();
    }

    public final double getPlayerAttack() {
        return player.getAttack();
    }

    public final double getPlayerDefence() {
        return player.getDefence();
    }

    public final double getPlayerExpMultiplier() {
        return player.getExpMultiplier();
    }

    public final void setPlayerHealth(int health) {
        player.setHealth(health);
    }

    public final void setPlayerSpirit(int spirit) {
        player.setSpirit(spirit);
    }

    public final void setPlayerMaxHealth(int health) {
        player.setMaxHealth(health);
    }

    public final void setPlayerMaxSpirit(int spirit) {
        player.setMaxSpirit(spirit);
    }

    public final void setPlayerExpMultiplier(double multiplier) {
        player.setExpMultiplier(multiplier);
    }

    public final void setPlayerBody(int level) {
        player.setBodyLevel(BodyLevel.getRealm(level));
    }

    public final void upgradePlayerBody(int level) {
        while (level > player.getBodyLevel().getRank()) {
            player.levelBody();
            if (getNextLevel(player.getBodyLevel()) == null) {
                break;
            }
        }
    }

    public final void setPlayerQi(int level) {
        player.setQiLevel(QiLevel.getRealm(level));
    }

    public final void upgradePlayerQi(int level) {
        while (level > player.getQiLevel().getRank()) {
            player.levelQi();
            if (getNextLevel(player.getQiLevel()) == null) {
                break;
            }
        }
    }

    public final CultivationLevel getPlayerRealm() {
        return player.getCultivationRealm();
    }

    public final BodyLevel getPlayerBody() {
        return player.getBodyLevel();
    }

    public final QiLevel getPlayerQi() {
        return player.getQiLevel();
    }

    public final void grantExp(int exp) {
        player.grantExp(exp);
    }

    public final boolean canTribulationUpgrade(String target) {
        if (!tribulationDue && getUpgradeCost(target) != null) {
            switch (target) {
                case "Body":
                    if (player.getCultivationRealm() == CultivationLevel.CORE_FORMATION_REALM && player.getBodyLevel() == BodyLevel.CORE_FORMATION_BODY_STAGE_9) {
                        return false;
                    } else if (player.getCultivationRealm() == CultivationLevel.SAGE_REALM && player.getBodyLevel() == BodyLevel.SAGE_BODY_STAGE_9) {
                        return false;
                    }
                    return true;
                case "Qi":
                    if (player.getCultivationRealm() == CultivationLevel.CORE_FORMATION_REALM && player.getQiLevel() == QiLevel.CORE_FORMATION_SPIRIT_STAGE_9) {
                        return false;
                    } else if (player.getCultivationRealm() == CultivationLevel.SAGE_REALM && player.getQiLevel() == QiLevel.SAGE_SPIRIT_STAGE_9) {
                        return false;
                    }
                    return true;
                default:
                    return true;
            }
        }
        return true;
    }

    public final boolean canLevel() {
        if (getUpgradeCost("Body") != null || getUpgradeCost("Qi") != null) {
            return (canTribulationUpgrade("Body")) ? true : canTribulationUpgrade("Qi");
        }
        return false;
    }

    public final boolean canLevel(String target) {
        if (getUpgradeCost(target) != null) {
            return (canTribulationUpgrade(target)) ? getUpgradeCost(target) <= player.getExp() : false;
        }
        return false;
    }

    public final boolean checkTribulation() {
        CultivationLevel realm = player.getCultivationRealm();
        if (realm == CultivationLevel.CORE_FORMATION_REALM) {
            if (player.getBodyLevel() == BodyLevel.CORE_FORMATION_BODY_STAGE_9 && player.getQiLevel() == QiLevel.CORE_FORMATION_SPIRIT_STAGE_9) {
                tribulationDue = true;
                return true;
            }
        } else if (realm == CultivationLevel.SAGE_REALM) {
            if (player.getBodyLevel() == BodyLevel.SAGE_BODY_STAGE_9 && player.getQiLevel() == QiLevel.SAGE_SPIRIT_STAGE_9) {
                tribulationDue = true;
                return true;
            }
        }
        return false;
    }

    public final String triggerTribulation() {
        return eventManager.triggerTribulation();
    }

    public final boolean isTribulationDue() {
        return tribulationDue;
    }

    /**
     * @param tribulationDue the tribulationDue to set
     */
    public void setTribulationDue(boolean tribulationDue) {
        this.tribulationDue = tribulationDue;
    }

    /**
     * @return the toggleQiUpgrade
     */
    public boolean isToggleQiUpgrade() {
        return toggleQiUpgrade;
    }

    /**
     * @param toggleQiUpgrade the toggleQiUpgrade to set
     */
    public void setToggleQiUpgrade(boolean toggleQiUpgrade) {
        this.toggleQiUpgrade = toggleQiUpgrade;
    }

    /**
     * @return the toggleBodyUpgrade
     */
    public boolean isToggleBodyUpgrade() {
        return toggleBodyUpgrade;
    }

    /**
     * @param toggleBodyUpgrade the toggleBodyUpgrade to set
     */
    public void setToggleBodyUpgrade(boolean toggleBodyUpgrade) {
        this.toggleBodyUpgrade = toggleBodyUpgrade;
    }

    /**
     * @return the timer
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * @param timer the timer to set
     */
    public void setTimer(Timer timer) {
        this.timer = timer;
    }

}
