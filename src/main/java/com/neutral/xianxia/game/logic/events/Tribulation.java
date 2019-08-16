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
package com.neutral.xianxia.game.logic.events;

/**
 *
 * @author Mr.Neutral
 */
enum TRIBULATION {
    MINOR_3_TRIBULATIONS(3, "Minor 3 Tribulations"),
    LESSER_6_TRIBULATIONS(6, "Lesser 6 Tribulations"),
    GREATER_9_TRIBULATIONS(9, "Greater 9 Tribulations"),
    HEAVENLY_TRIBULATION(15, "Heavenly Tribulation"),
    VOID_TRIBULATION(22, "Void Tribulation");

    private final int difficulty;
    private final String name;

    private TRIBULATION(int difficulty, String name) {
        this.difficulty = difficulty;
        this.name = name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getName() {
        return name;
    }

}
