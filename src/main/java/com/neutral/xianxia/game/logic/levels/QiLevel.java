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
public enum QiLevel implements Level {

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
    NASCENT_SOUL_SPIRIT_STAGE_1(28, 28000, "Nascent Soul Spirit 1st Stage"),
    NASCENT_SOUL_SPIRIT_STAGE_2(29, 29000, "Nascent Soul Spirit 2nd Stage"),
    NASCENT_SOUL_SPIRIT_STAGE_3(30, 30000, "Nascent Soul Spirit 3rd Stage"),
    NASCENT_SOUL_SPIRIT_STAGE_4(31, 31000, "Nascent Soul Spirit 4th Stage"),
    NASCENT_SOUL_SPIRIT_STAGE_5(32, 32000, "Nascent Soul Spirit 5th Stage"),
    NASCENT_SOUL_SPIRIT_STAGE_6(33, 33000, "Nascent Soul Spirit 6th Stage"),
    NASCENT_SOUL_SPIRIT_STAGE_7(34, 34000, "Nascent Soul Spirit 7th Stage"),
    NASCENT_SOUL_SPIRIT_STAGE_8(35, 35000, "Nascent Soul Spirit 8th Stage"),
    NASCENT_SOUL_SPIRIT_STAGE_9(36, 36000, "Nascent Soul Spirit 9th Stage"),
    SPIRIT_SEVERING_SPIRIT_STAGE_1(37, 37000, "Spirit Severing Spirit 1st Stage"),
    SPIRIT_SEVERING_SPIRIT_STAGE_2(38, 38000, "Spirit Severing Spirit 2nd Stage"),
    SPIRIT_SEVERING_SPIRIT_STAGE_3(39, 39000, "Spirit Severing Spirit 3rd Stage"),
    SPIRIT_SEVERING_SPIRIT_STAGE_4(40, 40000, "Spirit Severing Spirit 4th Stage"),
    SPIRIT_SEVERING_SPIRIT_STAGE_5(41, 41000, "Spirit Severing Spirit 5th Stage"),
    SPIRIT_SEVERING_SPIRIT_STAGE_6(42, 42000, "Spirit Severing Spirit 6th Stage"),
    SPIRIT_SEVERING_SPIRIT_STAGE_7(43, 43000, "Spirit Severing Spirit 7th Stage"),
    SPIRIT_SEVERING_SPIRIT_STAGE_8(44, 44000, "Spirit Severing Spirit 8th Stage"),
    SPIRIT_SEVERING_SPIRIT_STAGE_9(45, 45000, "Spirit Severing Spirit 9th Stage"),
    SAGE_SPIRIT_STAGE_1(46, 46000, "Sage Spirit 1st Stage"),
    SAGE_SPIRIT_STAGE_2(47, 47000, "Sage Spirit 2nd Stage"),
    SAGE_SPIRIT_STAGE_3(48, 48000, "Sage Spirit 3rd Stage"),
    SAGE_SPIRIT_STAGE_4(49, 49000, "Sage Spirit 4th Stage"),
    SAGE_SPIRIT_STAGE_5(50, 50000, "Sage Spirit 5th Stage"),
    SAGE_SPIRIT_STAGE_6(51, 51000, "Sage Spirit 6th Stage"),
    SAGE_SPIRIT_STAGE_7(52, 52000, "Sage Spirit 7th Stage"),
    SAGE_SPIRIT_STAGE_8(53, 53000, "Sage Spirit 8th Stage"),
    SAGE_SPIRIT_STAGE_9(54, 54000, "Sage Spirit 9th Stage"),
    LESSER_ANCESTOR_SPIRIT_STAGE_1(55, 550000, "Lesser Ancestor Spirit 1st Stage"),
    LESSER_ANCESTOR_SPIRIT_STAGE_2(56, 560000, "Lesser Ancestor Spirit 2st Stage"),
    LESSER_ANCESTOR_SPIRIT_STAGE_3(57, 570000, "Lesser Ancestor Spirit 3st Stage"),
    LESSER_ANCESTOR_SPIRIT_STAGE_4(58, 580000, "Lesser Ancestor Spirit 4st Stage"),
    LESSER_ANCESTOR_SPIRIT_STAGE_5(59, 590000, "Lesser Ancestor Spirit 5st Stage"),
    LESSER_ANCESTOR_SPIRIT_STAGE_6(60, 600000, "Lesser Ancestor Spirit 6st Stage"),
    LESSER_ANCESTOR_SPIRIT_STAGE_7(61, 610000, "Lesser Ancestor Spirit 7st Stage"),
    LESSER_ANCESTOR_SPIRIT_STAGE_8(62, 620000, "Lesser Ancestor Spirit 8st Stage"),
    LESSER_ANCESTOR_SPIRIT_STAGE_9(63, 630000, "Lesser Ancestor Spirit 9st Stage"),
    GREATER_ANCESTOR_SPIRIT_STAGE_1(64, 640000, "Greater Ancestor Spirit 1st Stage"),
    GREATER_ANCESTOR_SPIRIT_STAGE_2(65, 650000, "Greater Ancestor Spirit 2st Stage"),
    GREATER_ANCESTOR_SPIRIT_STAGE_3(66, 660000, "Greater Ancestor Spirit 3st Stage"),
    GREATER_ANCESTOR_SPIRIT_STAGE_4(67, 670000, "Greater Ancestor Spirit 4st Stage"),
    GREATER_ANCESTOR_SPIRIT_STAGE_5(68, 680000, "Greater Ancestor Spirit 5st Stage"),
    GREATER_ANCESTOR_SPIRIT_STAGE_6(69, 690000, "Greater Ancestor Spirit 6st Stage"),
    GREATER_ANCESTOR_SPIRIT_STAGE_7(70, 700000, "Greater Ancestor Spirit 6st Stage"),
    GREATER_ANCESTOR_SPIRIT_STAGE_8(71, 710000, "Greater Ancestor Spirit 7st Stage"),
    GREATER_ANCESTOR_SPIRIT_STAGE_9(72, 720000, "Greater Ancestor Spirit 8st Stage"),
    ETERNAL_ANCESTOR_SPIRIT_STAGE_1(73, 730000, "Eternal Ancestor Spirit 1st Stage"),
    ETERNAL_ANCESTOR_SPIRIT_STAGE_2(74, 740000, "Eternal Ancestor Spirit 2st Stage"),
    ETERNAL_ANCESTOR_SPIRIT_STAGE_3(75, 750000, "Eternal Ancestor Spirit 3st Stage"),
    ETERNAL_ANCESTOR_SPIRIT_STAGE_4(76, 760000, "Eternal Ancestor Spirit 4st Stage"),
    ETERNAL_ANCESTOR_SPIRIT_STAGE_5(77, 770000, "Eternal Ancestor Spirit 5st Stage"),
    ETERNAL_ANCESTOR_SPIRIT_STAGE_6(78, 780000, "Eternal Ancestor Spirit 6st Stage"),
    ETERNAL_ANCESTOR_SPIRIT_STAGE_7(79, 790000, "Eternal Ancestor Spirit 7st Stage"),
    ETERNAL_ANCESTOR_SPIRIT_STAGE_8(80, 800000, "Eternal Ancestor Spirit 8st Stage"),
    ETERNAL_ANCESTOR_SPIRIT_STAGE_9(81, 810000, "Eternal Ancestor Spirit 9st Stage");

    private final int rank;

    private final int cost;
    private final String name;

    private QiLevel(int rank, int cost, String name) {
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

    public final static QiLevel getFinalRealm() {
        return QiLevel.values()[QiLevel.values().length - 1];
    }

    public final static QiLevel getRealm(int rank) {
        for (QiLevel level : QiLevel.values()) {
            if (level.getRank() == rank) {
                return level;
            }
        }
        return getFinalRealm();
    }

}
