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

import com.neutral.xianxia.game.logic.Level;

/**
 *
 * @author Mr.Neutral
 */
public enum BodyLevel implements Level {

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
    CORE_FORMATION_BODY_STAGE_9(27, 2700, "Core Formation Body 9th Stage"), // 3790 HP if only body
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
    SAGE_BODY_STAGE_9(54, 5400, "Sage Body 9th Stage"),
    LESSER_ANCESTOR_BODY_STAGE_1(55, 5500, "Lesser Ancestor Body 1st Stage");

    private final int rank;
    private final int cost;
    private final String name;

    private BodyLevel(int rank, int cost, String name) {
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

    public final static BodyLevel getFinalRealm() {
        return BodyLevel.values()[BodyLevel.values().length - 1];
    }

    public final static BodyLevel getRealm(int rank) {
        for (BodyLevel level : BodyLevel.values()) {
            if (level.getRank() == rank) {
                return level;
            }
        }
        return getFinalRealm();
    }

}
