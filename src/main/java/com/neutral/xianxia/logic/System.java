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

/**
 *
 * @author Mr.Neutral
 */
public final class System {

    private final EventManager eventManager = new EventManager(this);
    private final Player player = new Player();

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

    public final void setPlayerQi(int level) {
        player.setQiLevel(QiLevel.getRealm(level));
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

    public final boolean canLevel() {
        if (getUpgradeCost("Body") != null) {
            if (player.getBodyLevel().getRank() > player.getQiLevel().getRank()) {
                return getUpgradeCost("Qi") <= player.getExp();
            } else {
                return getUpgradeCost("Body") <= player.getExp();
            }
        } else if (getUpgradeCost("Qi") != null) {
            if (player.getBodyLevel().getRank() < player.getQiLevel().getRank()) {
                return getUpgradeCost("Body") <= player.getExp();
            } else {
                return getUpgradeCost("Qi") <= player.getExp();
            }
        }
        return false;
    }

    public final boolean canLevel(String target) {
        if (getUpgradeCost(target) != null) {
            return getUpgradeCost(target) <= player.getExp();
        }
        return false;
    }
    
}
