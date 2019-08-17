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
package com.neutral.xianxia.game.logic.levels;

/**
 *
 * @author Mr.Neutral
 */
public enum CultivationLevel {
    MORTAL_REALM("Mortal", 0),
    QI_CONDENSATION_REALM("Qi Condensation", 1, 2, 3, 4, 5, 6, 7, 8, 9),
    FOUNDATION_ESTABLISHMENT_REALM("Foundation Establishment", 10, 11, 12, 13, 14, 15, 16, 17, 18),
    CORE_FORMATION_REALM("Core Formation", 19, 20, 21, 22, 23, 24, 25, 26, 27),
    NASCENT_SOUL_REALM("Nascent Soul", 28, 29, 30, 31, 32, 33, 34, 35, 36),
    SPIRIT_SEVERING_REALM("Spirit Severing", 37, 38, 39, 40, 41, 42, 43, 44, 45),
    SAGE_REALM("Sage", 46, 47, 48, 49, 50, 51, 52, 53, 54),
    LESSER_ANCESTOR_REALM("Lesser Ancestor", 55);

    private final int[] ranks;
    private final String name;

    private CultivationLevel(String name, int... ranks) {
        this.ranks = ranks;
        this.name = name;
    }

    public final int getRank() {
        return this.ranks[0];
    }

    public final int getFinalRank() {
        return this.ranks[ranks.length - 1];
    }

    public final int[] getRanks() {
        return this.ranks;
    }

    public final String getName() {
        return this.name;
    }

    public final static CultivationLevel getFinalRealm() {
        return CultivationLevel.values()[CultivationLevel.values().length - 1];
    }

    public final static CultivationLevel getRealm(int rank) {
        for (CultivationLevel level : CultivationLevel.values()) {
            for (int levelRank : level.getRanks()) {
                if (rank == levelRank) {
                    return level;
                }
            }
        }
        return getFinalRealm();
    }

}
