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

/**
 *
 * @author Mr.Neutral
 */

public final class Player extends Cultivator {

    private double expMultiplier = 1.0;

    public Player() {
        super();
    }

    public void cultivate() {
        grantExp((int) (((Math.random() + 0.1) * 10) * expMultiplier));
    }

    public double getExpMultiplier() {
        return this.expMultiplier;
    }

    public void changeExpMultiplier(double amount) {
        this.expMultiplier += amount;
    }

    public void setExpMultiplier(double expMultiplier) {
        this.expMultiplier = expMultiplier;
    }

}
