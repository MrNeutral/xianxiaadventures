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

import com.neutral.xianxia.logic.System.QI_LEVEL;

/**
 *
 * @author Mr.Neutral
 */
public final class System {

    public static enum QI_LEVEL implements Level {

        MORTAL_SPIRIT(0, 0, "Mortal Spirit"), QI_CONDENSATION_SPIRIT(1, 100, "Qi Condensation Spirit"), FOUNDATION_ESTABLISHMENT_SPIRIT(2, 200, "Foundation Establishment Spirit"), CORE_FORMATION_SPIRIT(3, 300, "Core Formation Spirit"), NASCENT_SOUL_SPIRIT(4, 400, "Nascent Soul Spirit"), SPIRIT_SEVERING_SPIRIT(5, 500, "Spirit Severing Spirit"), SAGE_SPIRIT(6, 600, "Sage Spirit");

        private final int rank;
        private final int cost;
        private final String name;

        private QI_LEVEL(int rank, int cost, String name) {
            this.rank = rank;
            this.cost = cost;
            this.name = name;
        }

        @Override
        public int getRank() {
            return this.rank;
        }

        @Override
        public int getCost() {
            return this.cost;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public final static QI_LEVEL getRealm(int rank) {
            for (QI_LEVEL level : QI_LEVEL.values()) {
                if (level.getRank() == rank) {
                    return level;
                }
            }
            return null;
        }

    }

    public static enum BODY_LEVEL implements Level {

        MORTAL_BODY(0, 0, "Mortal Body"), QI_CONDENSATION_BODY(1, 100, "Qi Condensation Body"), FOUNDATION_ESTABLISHMENT_BODY(2, 200, "Foundation Establishment Body"), CORE_FORMATION_BODY(3, 300, "Core Formation Body"), NASCENT_SOUL_BODY(4, 400, "Nascent Soul Body"), SPIRIT_SEVERING_BODY(5, 500, "Spirit Severing Body"), SAGE_BODY(6, 600, "Sage Body");

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

        public final static BODY_LEVEL getRealm(int rank) {
            for (BODY_LEVEL level : BODY_LEVEL.values()) {
                if (level.getRank() == rank) {
                    return level;
                }
            }
            return null;
        }

    }

    public static enum CULTIVATION_LEVEL implements Level {
        MORTAL_REALM(0, "Mortal"), QI_CONDENSATION_REALM(1, "Qi Condensation"), FOUNDATION_ESTABLISHMENT_REALM(2, "Foundation Establishment"), CORE_FORMATION_REALM(3, "Core Formation"), NASCENT_SOUL_REALM(4, "Nascent Soul"), BODY_SEVERING_REALM(5, "Spirit Severing"), SAGE_REALM(6, "Sage");

        private final int rank;
        private final String name;

        private CULTIVATION_LEVEL(int rank, String name) {
            this.rank = rank;
            this.name = name;
        }

        @Override
        public final int getRank() {
            return this.rank;
        }

        @Override
        public final String getName() {
            return this.name;
        }

        @Override
        @Deprecated
        public int getCost() {
            return 1;
        }

        public final static CULTIVATION_LEVEL getRealm(int rank) {
            for (CULTIVATION_LEVEL level : CULTIVATION_LEVEL.values()) {
                if (level.getRank() == rank) {
                    return level;
                }
            }
            return null;
        }

    }

    private final Player player;

    public System(Player player) {
        this.player = player;
    }

    public void cultivate() {
        player.cultivate();
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
            if (currLevel == levels[i] && levels[i + 1] != null) {
                return levels[i + 1];
            }
        }
        return null;
    }

    final static CULTIVATION_LEVEL getNextLevel(CULTIVATION_LEVEL currLevel) {
        CULTIVATION_LEVEL[] levels = CULTIVATION_LEVEL.values();
        for (int i = 0; i < levels.length; ++i) {
            if (currLevel == levels[i] && levels[i + 1] != null) {
                return levels[i + 1];
            }
        }
        return null;
    }

    public void attemptLevelUp(String target) {

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

    public Integer getUpgradeCost(String target) {
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

    public int getPlayerExp() {
        return player.getExp();
    }

    public CULTIVATION_LEVEL getPlayerRealm() {
        return player.getCultivationRealm();
    }

    public BODY_LEVEL getPlayerBody() {
        return player.getBodyLevel();
    }

    public QI_LEVEL getPlayerQi() {
        return player.getQiLevel();
    }

    public boolean canLevel() {
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

    public boolean canLevel(String target) {
        if (getUpgradeCost(target) != null) {
            return getUpgradeCost(target) <= player.getExp();
        }
        return false;
    }

}
