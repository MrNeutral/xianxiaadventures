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
public abstract class Cultivator {

    private int health;
    private int spirit;
    private int exp;
    private int powerLevel;
    private int bodyLevel;
    private int qiLevel;

    public Cultivator() {
        this.health = 10;
        this.spirit = 0;
        this.exp = 0;
        this.powerLevel = 1;
        this.bodyLevel = 1;
        this.qiLevel = 0;
    }

    public Cultivator(int health, int spirit, int exp, int powerLevel, int bodyLevel, int qiLevel) {
        this.health = health;
        this.spirit = spirit;
        this.exp = exp;
        this.powerLevel = powerLevel;
        this.bodyLevel = bodyLevel;
        this.qiLevel = qiLevel;
    }

    public double getAttack() {
        return getBodyLevel() * 1.5 + getQiLevel() * 2;
    }

    public void levelBody() {
        bodyLevel++;
    }

    public void levelQi() {
        qiLevel++;
    }

    public void attack(Cultivator cultivator) {
        cultivator.defend(this);
    }
    
    public void defend(Cultivator cultivator) {
        double potentialDamage = bodyLevel - cultivator.getAttack();
        health -= (potentialDamage > 0) ? Math.round(potentialDamage) : 0;
    }

    public void updatePowerLevel() {
        this.powerLevel = bodyLevel + qiLevel;
    }

    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @return the spirit
     */
    public int getSpirit() {
        return spirit;
    }

    /**
     * @return the exp
     */
    public int getExp() {
        return exp;
    }

    /**
     * @return the powerLevel
     */
    public int getPowerLevel() {
        return powerLevel;
    }

    /**
     * @return the bodyLevel
     */
    public int getBodyLevel() {
        return bodyLevel;
    }

    /**
     * @return the qiLevel
     */
    public int getQiLevel() {
        return qiLevel;
    }

}
