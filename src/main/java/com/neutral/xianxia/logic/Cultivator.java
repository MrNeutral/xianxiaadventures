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

import com.neutral.xianxia.logic.System.BODY_LEVEL;
import com.neutral.xianxia.logic.System.CULTIVATION_LEVEL;
import com.neutral.xianxia.logic.System.QI_LEVEL;

/**
 *
 * @author Mr.Neutral
 */
public abstract class Cultivator {

    private int health;
    private int spirit;
    private int exp;
    private int powerLevel;
    private BODY_LEVEL bodyLevel;
    private QI_LEVEL qiLevel;
    private CULTIVATION_LEVEL cultivationRealm;

    public Cultivator() {
        this.health = 10;
        this.spirit = 0;
        this.exp = 0;
        this.powerLevel = 1;
        this.bodyLevel = BODY_LEVEL.MORTAL_BODY;
        this.qiLevel = QI_LEVEL.MORTAL_SPIRIT;
        this.cultivationRealm = CULTIVATION_LEVEL.MORTAL_REALM;
    }

    public Cultivator(int health, int spirit, int exp, int powerLevel, BODY_LEVEL bodyLevel, QI_LEVEL qiLevel) {
        this.health = health;
        this.spirit = spirit;
        this.exp = exp;
        this.powerLevel = powerLevel;
        this.bodyLevel = bodyLevel;
        this.qiLevel = qiLevel;
        this.cultivationRealm = CULTIVATION_LEVEL.MORTAL_REALM;
    }

    public CULTIVATION_LEVEL getCultivationRealm() {
        return cultivationRealm;
    }

    public double getAttack() {
        return getBodyLevel().getRank() * 1.5 + getQiLevel().getRank() * 2;
        //Base defence at 1/1 is 3.5
    }

    public double getDefence() {
        return getBodyLevel().getRank() * 2 + getQiLevel().getRank() * 1.25;
        //Base attack at 1/1 is 3.25
    }

    public void levelBody() {
        setHealth(health + System.getNextLevel(bodyLevel).getRank() * 10);
        setBodyLevel(System.getNextLevel(bodyLevel));
    }

    public void setCultivationRealm(CULTIVATION_LEVEL cultivationRealm) {
        this.cultivationRealm = cultivationRealm;
    }

    public void levelQi() {
        setSpirit(spirit + System.getNextLevel(qiLevel).getRank() * 5);
        setQiLevel(System.getNextLevel(qiLevel));
    }

    public void grantExp(int amount) {
        this.exp += amount;
    }

    public void attack(Cultivator cultivator) {
        cultivator.defend(this);
    }

    public void defend(Cultivator cultivator) {
        double potentialDamage = getDefence() - cultivator.getAttack();
        setHealth((int) ((health - potentialDamage > 0) ? Math.round(potentialDamage) : 0));
    }

    public void updatePowerLevel() {
        this.powerLevel = bodyLevel.getRank() + qiLevel.getRank();
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
    public BODY_LEVEL getBodyLevel() {
        return bodyLevel;
    }

    /**
     * @return the qiLevel
     */
    public QI_LEVEL getQiLevel() {
        return qiLevel;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @param spirit the spirit to set
     */
    public void setSpirit(int spirit) {
        this.spirit = spirit;
    }

    /**
     * @param bodyLevel the bodyLevel to set
     */
    public void setBodyLevel(BODY_LEVEL bodyLevel) {
        this.bodyLevel = bodyLevel;
    }

    /**
     * @param qiLevel the qiLevel to set
     */
    public void setQiLevel(QI_LEVEL qiLevel) {
        this.qiLevel = qiLevel;
    }

}
