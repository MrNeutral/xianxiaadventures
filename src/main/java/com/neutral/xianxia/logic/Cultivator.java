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

import com.neutral.xianxia.logic.levels.BodyLevel;
import com.neutral.xianxia.logic.levels.CultivationLevel;
import com.neutral.xianxia.logic.levels.QiLevel;

/**
 *
 * @author Mr.Neutral
 */
public abstract class Cultivator {

    private int health;
    private int maxHealth;
    private int spirit;
    private int maxSpirit;
    private int exp;
    private int powerLevel;
    private BodyLevel bodyLevel;
    private QiLevel qiLevel;
    private CultivationLevel cultivationRealm;

    public Cultivator() {
        this.health = 10;
        this.maxHealth = 10;
        this.maxSpirit = 0;
        this.spirit = 0;
        this.exp = 0;
        this.powerLevel = 1;
        this.bodyLevel = BodyLevel.MORTAL_BODY;
        this.qiLevel = QiLevel.MORTAL_SPIRIT;
        this.cultivationRealm = CultivationLevel.MORTAL_REALM;
    }

    public Cultivator(int health, int maxHealth, int spirit, int maxSpirit, int exp, int powerLevel, BodyLevel bodyLevel, QiLevel qiLevel) {
        this.health = health;
        this.maxHealth = health;
        this.maxSpirit = maxSpirit;
        this.spirit = spirit;
        this.exp = exp;
        this.powerLevel = powerLevel;
        this.bodyLevel = bodyLevel;
        this.qiLevel = qiLevel;
        this.cultivationRealm = CultivationLevel.MORTAL_REALM;
    }

    public CultivationLevel getCultivationRealm() {
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
        if (System.getNextLevel(bodyLevel) != null) {
            setMaxHealth(maxHealth + System.getNextLevel(bodyLevel).getRank() * 10);
            setHealth(maxHealth);
            setBodyLevel(System.getNextLevel(bodyLevel));
        }
    }

    public void setCultivationRealm(CultivationLevel cultivationRealm) {
        this.cultivationRealm = cultivationRealm;
    }

    public void levelQi() {
        if (System.getNextLevel(qiLevel) != null) {
            setMaxSpirit(maxSpirit + System.getNextLevel(qiLevel).getRank() * 5);
            setSpirit(maxSpirit);
            setQiLevel(System.getNextLevel(qiLevel));
        }
    }

    public void grantExp(int amount) {
        if (amount < 0 && (exp + amount) < 0) {
            this.exp = 0;
        } else {
            this.exp += amount;
        }
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

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * @return the spirit
     */
    public int getSpirit() {
        return spirit;
    }

    public int getMaxSpirit() {
        return maxSpirit;
    }

    public void setMaxSpirit(int maxSpirit) {
        this.maxSpirit = maxSpirit;
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
    public BodyLevel getBodyLevel() {
        return bodyLevel;
    }

    /**
     * @return the qiLevel
     */
    public QiLevel getQiLevel() {
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
    public void setBodyLevel(BodyLevel bodyLevel) {
        this.bodyLevel = bodyLevel;
    }

    /**
     * @param qiLevel the qiLevel to set
     */
    public void setQiLevel(QiLevel qiLevel) {
        this.qiLevel = qiLevel;
    }

}
