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
package com.neutral.xianxia.logic.battle;

import com.neutral.xianxia.logic.Cultivator;
import com.neutral.xianxia.logic.levels.BodyLevel;
import com.neutral.xianxia.logic.levels.CultivationLevel;
import com.neutral.xianxia.logic.levels.QiLevel;

/**
 *
 * @author Mr.Neutral
 */
public class Enemy extends Cultivator{

    public Enemy() {
        super();
    }

    public Enemy(int health, int maxHealth, int spirit, int maxSpirit, int exp, int powerLevel, BodyLevel bodyLevel, QiLevel qiLevel, CultivationLevel cultivationRealm) {
        super(health, maxHealth, spirit, maxSpirit, exp, powerLevel, bodyLevel, qiLevel, cultivationRealm);
    }
    
    public void resetValues(){
        setMaxHealth(10);
        setMaxSpirit(0);
        setHealth(10);
        setSpirit(0);
        setBodyLevel(BodyLevel.MORTAL_BODY);
        setQiLevel(QiLevel.MORTAL_SPIRIT);
        setCultivationRealm(CultivationLevel.MORTAL_REALM);
        checkRealm();
    }

}
