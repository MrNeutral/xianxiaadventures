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

import com.neutral.xianxia.logic.EventManager.EVENT;
import com.neutral.xianxia.logic.System.QI_LEVEL;

/**
 *
 * @author Mr.Neutral
 */
public final class System {

    public static enum QI_LEVEL implements Level {

        MORTAL_SPIRIT(0, 0, "Mortal Spirit"),
        QI_CONDENSATION_SPIRIT_STAGE_1(1, 100, "Qi Condensation Spirit 1st Stage"),
        QI_CONDENSATION_SPIRIT_STAGE_2(2, 200, "Qi Condensation Spirit 2nd Stage"),
        QI_CONDENSATION_SPIRIT_STAGE_3(3, 300, "Qi Condensation Spirit 3rd Stage"),
        QI_CONDENSATION_SPIRIT_STAGE_4(4, 400, "Qi Condensation Spirit 4th Stage"),
        QI_CONDENSATION_SPIRIT_STAGE_5(5, 500, "Qi Condensation Spirit 5th Stage"),
        QI_CONDENSATION_SPIRIT_STAGE_6(6, 600, "Qi Condensation Spirit 6th Stage"),
        QI_CONDENSATION_SPIRIT_STAGE_7(7, 700, "Qi Condensation Spirit 7th Stage"),
        QI_CONDENSATION_SPIRIT_STAGE_8(8, 800, "Qi Condensation Spirit 8th Stage"),
        QI_CONDENSATION_SPIRIT_STAGE_9(9, 900, "Qi Condensation Spirit 9th Stage"),
        FOUNDATION_ESTABLISHMENT_SPIRIT_STAGE_1(10, 1000, "Foundation Establishment Spirit 1st Stage"),
        FOUNDATION_ESTABLISHMENT_SPIRIT_STAGE_2(11, 1100, "Foundation Establishment Spirit 2nd Stage"),
        FOUNDATION_ESTABLISHMENT_SPIRIT_STAGE_3(12, 1200, "Foundation Establishment Spirit 3rd Stage"),
        FOUNDATION_ESTABLISHMENT_SPIRIT_STAGE_4(13, 1300, "Foundation Establishment Spirit 4th Stage"),
        FOUNDATION_ESTABLISHMENT_SPIRIT_STAGE_5(14, 1400, "Foundation Establishment Spirit 5th Stage"),
        FOUNDATION_ESTABLISHMENT_SPIRIT_STAGE_6(15, 1500, "Foundation Establishment Spirit 6th Stage"),
        FOUNDATION_ESTABLISHMENT_SPIRIT_STAGE_7(16, 1600, "Foundation Establishment Spirit 7th Stage"),
        FOUNDATION_ESTABLISHMENT_SPIRIT_STAGE_8(17, 1700, "Foundation Establishment Spirit 8th Stage"),
        FOUNDATION_ESTABLISHMENT_SPIRIT_STAGE_9(18, 1800, "Foundation Establishment Spirit 9th Stage"),
        CORE_FORMATION_SPIRIT_STAGE_1(19, 1900, "Core Formation Spirit 1st Stage"),
        CORE_FORMATION_SPIRIT_STAGE_2(20, 2000, "Core Formation Spirit 2nd Stage"),
        CORE_FORMATION_SPIRIT_STAGE_3(21, 2100, "Core Formation Spirit 3rd Stage"),
        CORE_FORMATION_SPIRIT_STAGE_4(22, 2200, "Core Formation Spirit 4th Stage"),
        CORE_FORMATION_SPIRIT_STAGE_5(23, 2300, "Core Formation Spirit 5th Stage"),
        CORE_FORMATION_SPIRIT_STAGE_6(24, 2400, "Core Formation Spirit 6th Stage"),
        CORE_FORMATION_SPIRIT_STAGE_7(25, 2500, "Core Formation Spirit 7th Stage"),
        CORE_FORMATION_SPIRIT_STAGE_8(26, 2600, "Core Formation Spirit 8th Stage"),
        CORE_FORMATION_SPIRIT_STAGE_9(27, 2700, "Core Formation Spirit 9th Stage"),
        NASCENT_SOUL_SPIRIT_STAGE_1(28, 2800, "Nascent Soul Spirit 1st Stage"),
        NASCENT_SOUL_SPIRIT_STAGE_2(29, 2900, "Nascent Soul Spirit 2nd Stage"),
        NASCENT_SOUL_SPIRIT_STAGE_3(30, 3000, "Nascent Soul Spirit 3rd Stage"),
        NASCENT_SOUL_SPIRIT_STAGE_4(31, 3100, "Nascent Soul Spirit 4th Stage"),
        NASCENT_SOUL_SPIRIT_STAGE_5(32, 3200, "Nascent Soul Spirit 5th Stage"),
        NASCENT_SOUL_SPIRIT_STAGE_6(33, 3300, "Nascent Soul Spirit 6th Stage"),
        NASCENT_SOUL_SPIRIT_STAGE_7(34, 3400, "Nascent Soul Spirit 7th Stage"),
        NASCENT_SOUL_SPIRIT_STAGE_8(35, 3500, "Nascent Soul Spirit 8th Stage"),
        NASCENT_SOUL_SPIRIT_STAGE_9(36, 3600, "Nascent Soul Spirit 9th Stage"),
        SPIRIT_SEVERING_SPIRIT_STAGE_1(37, 3700, "Spirit Severing Spirit 1st Stage"),
        SPIRIT_SEVERING_SPIRIT_STAGE_2(38, 3800, "Spirit Severing Spirit 2nd Stage"),
        SPIRIT_SEVERING_SPIRIT_STAGE_3(39, 3900, "Spirit Severing Spirit 3rd Stage"),
        SPIRIT_SEVERING_SPIRIT_STAGE_4(40, 4000, "Spirit Severing Spirit 4th Stage"),
        SPIRIT_SEVERING_SPIRIT_STAGE_5(41, 4100, "Spirit Severing Spirit 5th Stage"),
        SPIRIT_SEVERING_SPIRIT_STAGE_6(42, 4200, "Spirit Severing Spirit 6th Stage"),
        SPIRIT_SEVERING_SPIRIT_STAGE_7(43, 4300, "Spirit Severing Spirit 7th Stage"),
        SPIRIT_SEVERING_SPIRIT_STAGE_8(44, 4400, "Spirit Severing Spirit 8th Stage"),
        SPIRIT_SEVERING_SPIRIT_STAGE_9(45, 4500, "Spirit Severing Spirit 9th Stage"),
        SAGE_SPIRIT_STAGE_1(46, 4600, "Sage Spirit 1st Stage"),
        SAGE_SPIRIT_STAGE_2(47, 4700, "Sage Spirit 2nd Stage"),
        SAGE_SPIRIT_STAGE_3(48, 4800, "Sage Spirit 3rd Stage"),
        SAGE_SPIRIT_STAGE_4(49, 4900, "Sage Spirit 4th Stage"),
        SAGE_SPIRIT_STAGE_5(50, 5000, "Sage Spirit 5th Stage"),
        SAGE_SPIRIT_STAGE_6(51, 5100, "Sage Spirit 6th Stage"),
        SAGE_SPIRIT_STAGE_7(52, 5200, "Sage Spirit 7th Stage"),
        SAGE_SPIRIT_STAGE_8(53, 5300, "Sage Spirit 8th Stage"),
        SAGE_SPIRIT_STAGE_9(54, 5400, "Sage Spirit 9th Stage");

        private final int rank;

        private final int cost;
        private final String name;

        private QI_LEVEL(int rank, int cost, String name) {
            this.rank = rank;
            this.cost = cost;
            this.name = name;
        }

        @Override
        public final int getRank() {
            return this.rank;
        }

        @Override
        public final int getCost() {
            return this.cost;
        }

        @Override
        public final String getName() {
            return this.name;
        }

        public final static QI_LEVEL getFinalRealm() {
            return QI_LEVEL.values()[QI_LEVEL.values().length - 1];
        }

        public final static QI_LEVEL getRealm(int rank) {
            for (QI_LEVEL level : QI_LEVEL.values()) {
                if (level.getRank() == rank) {
                    return level;
                }
            }
            return getFinalRealm();
        }

    }

    public static enum BODY_LEVEL implements Level {

        MORTAL_BODY(0, 0, "Mortal Body"),
        QI_CONDENSATION_BODY_STAGE_1(1, 100, "Qi Condensation Body 1st Stage"),
        QI_CONDENSATION_BODY_STAGE_2(2, 200, "Qi Condensation Body 2nd Stage"),
        QI_CONDENSATION_BODY_STAGE_3(3, 300, "Qi Condensation Body 3rd Stage"),
        QI_CONDENSATION_BODY_STAGE_4(4, 400, "Qi Condensation Body 4th Stage"),
        QI_CONDENSATION_BODY_STAGE_5(5, 500, "Qi Condensation Body 5th Stage"),
        QI_CONDENSATION_BODY_STAGE_6(6, 600, "Qi Condensation Body 6th Stage"),
        QI_CONDENSATION_BODY_STAGE_7(7, 700, "Qi Condensation Body 7th Stage"),
        QI_CONDENSATION_BODY_STAGE_8(8, 800, "Qi Condensation Body 8th Stage"),
        QI_CONDENSATION_BODY_STAGE_9(9, 900, "Qi Condensation Body 9th Stage"),
        FOUNDATION_ESTABLISHMENT_BODY_STAGE_1(10, 1000, "Foundation Establishment Body 1st Stage"),
        FOUNDATION_ESTABLISHMENT_BODY_STAGE_2(11, 1100, "Foundation Establishment Body 2nd Stage"),
        FOUNDATION_ESTABLISHMENT_BODY_STAGE_3(12, 1200, "Foundation Establishment Body 3rd Stage"),
        FOUNDATION_ESTABLISHMENT_BODY_STAGE_4(13, 1300, "Foundation Establishment Body 4th Stage"),
        FOUNDATION_ESTABLISHMENT_BODY_STAGE_5(14, 1400, "Foundation Establishment Body 5th Stage"),
        FOUNDATION_ESTABLISHMENT_BODY_STAGE_6(15, 1500, "Foundation Establishment Body 6th Stage"),
        FOUNDATION_ESTABLISHMENT_BODY_STAGE_7(16, 1600, "Foundation Establishment Body 7th Stage"),
        FOUNDATION_ESTABLISHMENT_BODY_STAGE_8(17, 1700, "Foundation Establishment Body 8th Stage"),
        FOUNDATION_ESTABLISHMENT_BODY_STAGE_9(18, 1800, "Foundation Establishment Body 9th Stage"),
        CORE_FORMATION_BODY_STAGE_1(19, 1900, "Core Formation Body 1st Stage"),
        CORE_FORMATION_BODY_STAGE_2(20, 2000, "Core Formation Body 2nd Stage"),
        CORE_FORMATION_BODY_STAGE_3(21, 2100, "Core Formation Body 3rd Stage"),
        CORE_FORMATION_BODY_STAGE_4(22, 2200, "Core Formation Body 4th Stage"),
        CORE_FORMATION_BODY_STAGE_5(23, 2300, "Core Formation Body 5th Stage"),
        CORE_FORMATION_BODY_STAGE_6(24, 2400, "Core Formation Body 6th Stage"),
        CORE_FORMATION_BODY_STAGE_7(25, 2500, "Core Formation Body 7th Stage"),
        CORE_FORMATION_BODY_STAGE_8(26, 2600, "Core Formation Body 8th Stage"),
        CORE_FORMATION_BODY_STAGE_9(27, 2700, "Core Formation Body 9th Stage"),
        NASCENT_SOUL_BODY_STAGE_1(28, 2800, "Nascent Soul Body 1st Stage"),
        NASCENT_SOUL_BODY_STAGE_2(29, 2900, "Nascent Soul Body 2nd Stage"),
        NASCENT_SOUL_BODY_STAGE_3(30, 3000, "Nascent Soul Body 3rd Stage"),
        NASCENT_SOUL_BODY_STAGE_4(31, 3100, "Nascent Soul Body 4th Stage"),
        NASCENT_SOUL_BODY_STAGE_5(32, 3200, "Nascent Soul Body 5th Stage"),
        NASCENT_SOUL_BODY_STAGE_6(33, 3300, "Nascent Soul Body 6th Stage"),
        NASCENT_SOUL_BODY_STAGE_7(34, 3400, "Nascent Soul Body 7th Stage"),
        NASCENT_SOUL_BODY_STAGE_8(35, 3500, "Nascent Soul Body 8th Stage"),
        NASCENT_SOUL_BODY_STAGE_9(36, 3600, "Nascent Soul Body 9th Stage"),
        SPIRIT_SEVERING_BODY_STAGE_1(37, 3700, "Spirit Severing Body 1st Stage"),
        SPIRIT_SEVERING_BODY_STAGE_2(38, 3800, "Spirit Severing Body 2nd Stage"),
        SPIRIT_SEVERING_BODY_STAGE_3(39, 3900, "Spirit Severing Body 3rd Stage"),
        SPIRIT_SEVERING_BODY_STAGE_4(40, 4000, "Spirit Severing Body 4th Stage"),
        SPIRIT_SEVERING_BODY_STAGE_5(41, 4100, "Spirit Severing Body 5th Stage"),
        SPIRIT_SEVERING_BODY_STAGE_6(42, 4200, "Spirit Severing Body 6th Stage"),
        SPIRIT_SEVERING_BODY_STAGE_7(43, 4300, "Spirit Severing Body 7th Stage"),
        SPIRIT_SEVERING_BODY_STAGE_8(44, 4400, "Spirit Severing Body 8th Stage"),
        SPIRIT_SEVERING_BODY_STAGE_9(45, 4500, "Spirit Severing Body 9th Stage"),
        SAGE_BODY_STAGE_1(46, 4600, "Sage Body 1st Stage"),
        SAGE_BODY_STAGE_2(47, 4700, "Sage Body 2nd Stage"),
        SAGE_BODY_STAGE_3(48, 4800, "Sage Body 3rd Stage"),
        SAGE_BODY_STAGE_4(49, 4900, "Sage Body 4th Stage"),
        SAGE_BODY_STAGE_5(50, 5000, "Sage Body 5th Stage"),
        SAGE_BODY_STAGE_6(51, 5100, "Sage Body 6th Stage"),
        SAGE_BODY_STAGE_7(52, 5200, "Sage Body 7th Stage"),
        SAGE_BODY_STAGE_8(53, 5300, "Sage Body 8th Stage"),
        SAGE_BODY_STAGE_9(54, 5400, "Sage Body 9th Stage");

        private final int rank;
        private final int cost;
        private final String name;

        private BODY_LEVEL(int rank, int cost, String name) {
            this.rank = rank;
            this.cost = cost;
            this.name = name;
        }

        @Override
        public final int getRank() {
            return this.rank;
        }

        @Override
        public final int getCost() {
            return this.cost;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public final static BODY_LEVEL getFinalRealm() {
            return BODY_LEVEL.values()[BODY_LEVEL.values().length - 1];
        }

        public final static BODY_LEVEL getRealm(int rank) {
            for (BODY_LEVEL level : BODY_LEVEL.values()) {
                if (level.getRank() == rank) {
                    return level;
                }
            }
            return getFinalRealm();
        }

    }

    public static enum CULTIVATION_LEVEL {
        MORTAL_REALM("Mortal", 0),
        QI_CONDENSATION_REALM("Qi Condensation", 1, 2, 3, 4, 5, 6, 7, 8, 9),
        FOUNDATION_ESTABLISHMENT_REALM("Foundation Establishment", 10, 11, 12, 13, 14, 15, 16, 17, 18),
        CORE_FORMATION_REALM("Core Formation", 19, 20, 21, 22, 23, 24, 25, 26, 27),
        NASCENT_SOUL_REALM("Nascent Soul", 28, 29, 30, 31, 32, 33, 34, 35, 36),
        BODY_SEVERING_REALM("Spirit Severing", 37, 38, 39, 40, 41, 42, 43, 44, 45),
        SAGE_REALM("Sage", 46, 47, 48, 49, 50, 51, 52, 53, 54);

        private final int[] ranks;
        private final String name;

        private CULTIVATION_LEVEL(String name, int... ranks) {
            this.ranks = ranks;
            this.name = name;
        }

        public final int getRank() {
            return this.ranks[0];
        }

        public final int[] getRanks() {
            return this.ranks;
        }

        public final String getName() {
            return this.name;
        }

        public final static CULTIVATION_LEVEL getFinalRealm() {
            return CULTIVATION_LEVEL.values()[CULTIVATION_LEVEL.values().length - 1];
        }

        public final static CULTIVATION_LEVEL getRealm(int rank) {
            for (CULTIVATION_LEVEL level : CULTIVATION_LEVEL.values()) {
                for (int levelRank : level.getRanks()) {
                    if (rank == levelRank) {
                        return level;
                    }
                }
            }
            return getFinalRealm();
        }

    }

    private final EventManager eventManager = new EventManager(this);
    private final Player player = new Player();

    public final void cultivate() {
        player.cultivate();
    }

    public final EVENT getEvent() {
        return eventManager.getRandomEvent();
    }

    public final static QI_LEVEL[] getQiLevels() {
        return QI_LEVEL.values();
    }

    public final static BODY_LEVEL[] getBodyLevels() {
        return BODY_LEVEL.values();
    }

    final static BODY_LEVEL getNextLevel(BODY_LEVEL currLevel) {
        BODY_LEVEL[] levels = BODY_LEVEL.values();
        for (int i = 0; i < levels.length; ++i) {
            if (currLevel == levels[i] && i + 1 < levels.length) {
                return levels[i + 1];
            }
        }
        return null;
    }

    final static QI_LEVEL getNextLevel(QI_LEVEL currLevel) {
        QI_LEVEL[] levels = QI_LEVEL.values();
        for (int i = 0; i < levels.length; ++i) {
            if (currLevel == levels[i] && i + 1 < levels.length) {
                return levels[i + 1];
            }
        }
        return null;
    }

    final static CULTIVATION_LEVEL getNextLevel(CULTIVATION_LEVEL currLevel) {
        CULTIVATION_LEVEL[] levels = CULTIVATION_LEVEL.values();
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

    public void checkPlayerRealm() {
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

    public final void setPlayerExpMultiplier(double multiplier) {
        player.setExpMultiplier(multiplier);
    }

    public final void setPlayerBody(int level) {
        player.setBodyLevel(BODY_LEVEL.getRealm(level));
    }

    public final void setPlayerQi(int level) {
        player.setQiLevel(QI_LEVEL.getRealm(level));
    }

    public final CULTIVATION_LEVEL getPlayerRealm() {
        return player.getCultivationRealm();
    }

    public final BODY_LEVEL getPlayerBody() {
        return player.getBodyLevel();
    }

    public final QI_LEVEL getPlayerQi() {
        return player.getQiLevel();
    }

    public void grantExp(int exp) {
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
