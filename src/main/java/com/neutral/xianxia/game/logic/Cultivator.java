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
package com.neutral.xianxia.game.logic;

import com.neutral.xianxia.game.logic.battle.Battle;
import com.neutral.xianxia.game.logic.battle.Enemy;
import com.neutral.xianxia.game.logic.battle.Fightable;
import com.neutral.xianxia.game.logic.levels.BodyLevel;
import com.neutral.xianxia.game.logic.levels.CultivationLevel;
import static com.neutral.xianxia.game.logic.levels.CultivationLevel.getRealm;
import com.neutral.xianxia.game.logic.levels.QiLevel;

/**
 *
 * @author Mr.Neutral
 */
public abstract class Cultivator implements Fightable {

    private int health;
    private int maxHealth;
    private int spirit;
    private int maxSpirit;
    private int exp;
    private int powerLevel;
    private BodyLevel bodyLevel;
    private QiLevel qiLevel;
    private CultivationLevel cultivationRealm;
    private boolean defendingWithBody = false;
    private boolean defendingWithQi = false;

    public Cultivator() {
        this.health = 10;
        this.maxHealth = 10;
        this.maxSpirit = 0;
        this.spirit = 0;
        this.exp = 0;
        this.powerLevel = 0;
        this.bodyLevel = BodyLevel.MORTAL_BODY;
        this.qiLevel = QiLevel.MORTAL_SPIRIT;
        this.cultivationRealm = CultivationLevel.MORTAL_REALM;
    }

    public Cultivator(int health, int maxHealth, int spirit, int maxSpirit, int exp, int powerLevel, BodyLevel bodyLevel, QiLevel qiLevel, CultivationLevel cultivationRealm) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.maxSpirit = maxSpirit;
        this.spirit = spirit;
        this.exp = exp;
        this.powerLevel = powerLevel;
        this.bodyLevel = bodyLevel;
        this.qiLevel = qiLevel;
        this.cultivationRealm = cultivationRealm;
    }

    public Cultivator(int health, int spirit, int qiLevel, int bodyLevel, int cultivationRealm, int powerLevel, int exp, double expMult) {
        this.health = health;
        this.maxHealth = 10;
        this.maxSpirit = 0;
        this.spirit = spirit;
        this.exp = exp;
        this.powerLevel = powerLevel;
        this.bodyLevel = BodyLevel.getRealm(bodyLevel);
        this.qiLevel = QiLevel.getRealm(qiLevel);
        this.cultivationRealm = CultivationLevel.getRealm(cultivationRealm);
    }

    public void checkRealm() {
        setCultivationRealm((getBodyLevel().getRank() > getQiLevel().getRank()) ? getRealm(getBodyLevel().getRank()) : getRealm(getQiLevel().getRank()));
    }

    public CultivationLevel getCultivationRealm() {
        return cultivationRealm;
    }

    @Override
    public double getPhysicalAttack() {
        return (getBodyLevel().getRank() * 1.5) * ((getCultivationRealm().getRank() == 0) ? 1 : getCultivationRealm().getRank()) + 1;
        // 1 * 1.5 * 1 + 1 = 2.5 
    }

    @Override
    public double getSpiritAttack() {
        return (getQiLevel().getRank() * 2.5) * ((getCultivationRealm().getRank() == 0) ? 1 : getCultivationRealm().getRank());
        // 1 * 3.5 + 1 = 4.5
    }

    @Override
    public double getPhysicalDefence() {
        return (getBodyLevel().getRank() * 2 + getQiLevel().getRank() * 0.25)
                * ((getCultivationRealm().getRank() == 0) ? 1 : (getCultivationRealm().getRank() * 0.75));
        // 2 * 2 + 2 * 0.25 + 1 = 4.5
    }

    @Override
    public double getSpiritDefense() {
        return (getBodyLevel().getRank() * 0.25 + getQiLevel().getRank() * 2.25)
                * ((getCultivationRealm().getRank() == 0) ? 1 : (getCultivationRealm().getRank() * 0.75));
        // 2 * 0.25 + 2 * 2 + 1 = 4.5
    }

    public void levelBody() {
        if (GameSystem.getNextLevel(bodyLevel) != null) {
            setMaxHealth(maxHealth + GameSystem.getNextLevel(bodyLevel).getRank() * 10);
            setHealth(maxHealth);
            setBodyLevel(GameSystem.getNextLevel(bodyLevel));
        }
    }

    public void setCultivationRealm(CultivationLevel cultivationRealm) {
        this.cultivationRealm = cultivationRealm;
    }

    public void levelQi() {
        if (GameSystem.getNextLevel(qiLevel) != null) {
            setMaxSpirit(maxSpirit + GameSystem.getNextLevel(qiLevel).getRank() * 5);
            setSpirit(maxSpirit);
            setQiLevel(GameSystem.getNextLevel(qiLevel));
        }
    }

    public void grantExp(int amount) {
        if (amount < 0 && (exp + amount) < 0) {
            this.exp = 0;
        } else {
            this.exp += amount;
        }
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

    public void changeHealth(int health) {
        if (this.health + health <= 0) {
            this.health = 0;
        } else {
            this.health += health;
        }
    }

    public void setHealth(int health) {
        if (health <= 0) {
            this.health = 0;
        } else if (health > maxHealth) {
            this.health = maxHealth;
        } else {
            this.health = health;
        }
    }

    public void changeSpirit(int spirit) {
        if (this.spirit + spirit <= 0) {
            this.spirit = 0;
        } else {
            this.spirit += spirit;
        }
    }

    public void setSpirit(int spirit) {
        if (spirit <= 0) {
            this.spirit = 0;
        } else if (spirit > maxSpirit) {
            this.spirit = maxSpirit;
        } else {
            this.spirit = spirit;
        }
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

    @Override
    public void defendSimpleFromSpirit(Fightable cultivator, Battle battle) {
        double potentialDamage = cultivator.getSpiritAttack();
        if (potentialDamage <= 0) {
            potentialDamage = 0;
            battle.addToBattleHistory(((cultivator instanceof Enemy) ? "The enemy uses his Qi to attack you but " : "You use your Qi to attack him but ") + "it does no damage. " + ((cultivator instanceof Enemy) ? "You are too strong." : "The enemy is too strong."));
        } else {
            battle.addToBattleHistory(((cultivator instanceof Enemy) ? "The enemy uses his Qi to attack you for **" : "You use your Qi to attack him for **") + Math.round(potentialDamage) + "** point" + ((Math.round(potentialDamage) > 1) ? "s" : "") + " of damage.");
        }
        ((Cultivator) cultivator).changeSpirit((int) -Math.round(cultivator.getSpiritAttack() / 4));
        changeHealth((int) ((health - potentialDamage > 0) ? -Math.round(potentialDamage) : -health));
    }

    @Override
    public void defendSimpleFromPhysical(Fightable cultivator, Battle battle) {
        double potentialDamage = cultivator.getPhysicalAttack();
        if (potentialDamage <= 0) {
            potentialDamage = 0;
            battle.addToBattleHistory(((cultivator instanceof Enemy) ? "The enemy uses his Body to attack you but " : "You use your Body to attack him but ") + "it does no damage. " + ((cultivator instanceof Enemy) ? "You are too strong." : "The enemy is too strong."));
        } else {
            battle.addToBattleHistory(((cultivator instanceof Enemy) ? "The enemy uses his Body to attack you for **" : "You use your Body to attack him for **") + Math.round(potentialDamage) + "** point" + ((Math.round(potentialDamage) > 1) ? "s" : "") + " of damage.");
        }
        changeHealth((int) ((health - potentialDamage > 0) ? -Math.round(potentialDamage) : -health));
    }

    @Override
    public void spiritDefendFromSpirit(Fightable cultivator, Battle battle) {
        double potentialDamage = cultivator.getSpiritAttack() - getSpiritDefense() * 1.25;
        if (potentialDamage <= 0) {
            potentialDamage = 0;
            battle.addToBattleHistory(((cultivator instanceof Enemy) ? "The enemy uses his Qi to attack you but " : "You use your Qi to attack him but ") + "it does no damage. " + ((cultivator instanceof Enemy) ? "You used your own Qi to fully block it." : "The enemy used his Qi to fully block it."));
        } else {
            battle.addToBattleHistory(((cultivator instanceof Enemy) ? "The enemy uses his Qi to attack you for **" : "You use your Qi to attack him for **") + Math.round(potentialDamage) + "** point" + ((Math.round(potentialDamage) > 1) ? "s" : "") + " of damage. " + ((cultivator instanceof Enemy) ? "You use your own Qi to defend." : "The enemy uses his Qi to defend."));
        }
        ((Cultivator) cultivator).changeSpirit((int) -Math.round(cultivator.getSpiritAttack() / 4));
        changeSpirit((int) Math.round(-potentialDamage / 2));
        ((Cultivator) cultivator).setDefendingWithQi(false);
        changeHealth((int) ((health - potentialDamage > 0) ? -Math.round(potentialDamage) : -health));
    }

    @Override
    public void spiritDefendFromPhysical(Fightable cultivator, Battle battle) {
        double potentialDamage = cultivator.getPhysicalAttack() - getSpiritDefense() * 0.75;
        if (potentialDamage <= 0) {
            potentialDamage = 0;
            battle.addToBattleHistory(((cultivator instanceof Enemy) ? "The enemy uses his Body to attack you but " : "You use your Body to attack him but ") + "it does no damage. " + ((cultivator instanceof Enemy) ? "You used your own Qi to fully block it." : "The enemy used his Qi to fully block it."));
        } else {
            battle.addToBattleHistory(((cultivator instanceof Enemy) ? "The enemy uses his Body to attack you for **" : "You use your Body to attack him for **") + Math.round(potentialDamage) + "** point" + ((Math.round(potentialDamage) > 1) ? "s" : "") + " of damage. " + ((cultivator instanceof Enemy) ? "You use your Qi to defend." : "The enemy uses his Qi to defend."));
        }
        ((Cultivator) cultivator).setDefendingWithQi(false);
        changeSpirit((int) Math.round(-potentialDamage / 2));
        changeHealth((int) ((health - potentialDamage > 0) ? -Math.round(potentialDamage) : -health));
    }

    @Override
    public void physicalDefendFromSpirit(Fightable cultivator, Battle battle) {
        double potentialDamage = cultivator.getSpiritAttack() - getPhysicalDefence() * 0.75;
        if (potentialDamage <= 0) {
            potentialDamage = 0;
            battle.addToBattleHistory(((cultivator instanceof Enemy) ? "The enemy uses his Qi to attack you but " : "You use your Qi to attack him but ") + "it does no damage. " + ((cultivator instanceof Enemy) ? "You used your own Body to fully block it." : "The enemy used his Body to fully block it."));
        } else {
            battle.addToBattleHistory(((cultivator instanceof Enemy) ? "The enemy uses his Qi to attack you for **" : "You use your Qi to attack him for **") + Math.round(potentialDamage) + "** point" + ((Math.round(potentialDamage) > 1) ? "s" : "") + " of damage. " + ((cultivator instanceof Enemy) ? "You use your Body to defend." : "The enemy uses his Body to defend."));
        }
        ((Cultivator) cultivator).changeSpirit((int) -Math.round(cultivator.getSpiritAttack() / 4));
        ((Cultivator) cultivator).setDefendingWithBody(false);
        changeHealth((int) ((health - potentialDamage > 0) ? -Math.round(potentialDamage) : -health));
    }

    @Override
    public void physicalDefendFromPhysical(Fightable cultivator, Battle battle) {
        double potentialDamage = cultivator.getPhysicalAttack() - getPhysicalDefence() * 0.75;
        if (potentialDamage <= 0) {
            potentialDamage = 0;
            battle.addToBattleHistory(((cultivator instanceof Enemy) ? "The enemy uses his Body to attack you but " : "You use your Body to attack him but ") + "it does no damage. " + ((cultivator instanceof Enemy) ? "You used your own Body to fully block it." : "The enemy used his Body to fully block it."));
        } else {
            battle.addToBattleHistory(((cultivator instanceof Enemy) ? "The enemy uses his Body to attack you for **" : "You use your Body to attack him for **") + Math.round(potentialDamage) + "** point" + ((Math.round(potentialDamage) > 1) ? "s" : "") + " of damage. " + ((cultivator instanceof Enemy) ? "You use your Body to defend." : "The enemy uses his Body to defend."));
        }
        ((Cultivator) cultivator).setDefendingWithBody(false);
        changeHealth((int) ((health - potentialDamage > 0) ? -Math.round(potentialDamage) : -health));

    }

    /**
     * @return the defendingWithBody
     */
    public boolean isDefendingWithBody() {
        return defendingWithBody;
    }

    /**
     * @param defendingWithBody the defendingWithBody to set
     */
    public void setDefendingWithBody(boolean defendingWithBody) {
        this.defendingWithBody = defendingWithBody;
    }

    /**
     * @return the defendingWithQi
     */
    public boolean isDefendingWithQi() {
        return defendingWithQi;
    }

    /**
     * @param defendingWithQi the defendingWithQi to set
     */
    public void setDefendingWithQi(boolean defendingWithQi) {
        this.defendingWithQi = defendingWithQi;
    }

    @Override
    public void attackPhysical(Fightable cultivator, Battle battle) {
        cultivator.defendPhysical(this, battle);
    }

    @Override
    public void attackSpirit(Fightable cultivator, Battle battle) {
        cultivator.defendSpirit(this, battle);
    }

    @Override
    public void defendSpirit(Fightable cultivator, Battle battle) {
        if (isDefendingWithBody()) {
            physicalDefendFromSpirit(cultivator, battle);
        } else if (isDefendingWithQi()) {
            spiritDefendFromSpirit(cultivator, battle);
        } else {
            defendSimpleFromSpirit(cultivator, battle);
        }
    }

    @Override
    public void defendPhysical(Fightable cultivator, Battle battle) {
        if (isDefendingWithBody()) {
            physicalDefendFromPhysical(cultivator, battle);
        } else if (isDefendingWithQi()) {
            spiritDefendFromPhysical(cultivator, battle);
        } else {
            defendSimpleFromPhysical(cultivator, battle);
        }
    }

}
