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
package com.neutral.xianxia.game.logic.battle;

/**
 *
 * @author Mr.Neutral
 */
public interface Fightable {

    public double getPhysicalAttack();

    public double getSpiritAttack();

    public double getPhysicalDefence();

    public double getSpiritDefense();

    public void attackSpirit(Fightable cultivator, Battle battle);

    public void defendSpirit(Fightable cultivator, Battle battle);

    public void defendSimpleFromSpirit(Fightable cultivator, Battle battle);

    public void attackPhysical(Fightable cultivator, Battle battle);

    public void defendPhysical(Fightable cultivator, Battle battle);

    public void defendSimpleFromPhysical(Fightable cultivator, Battle battle);

    public void spiritDefendFromSpirit(Fightable cultivator, Battle battle);

    public void spiritDefendFromPhysical(Fightable cultivator, Battle battle);

    public void physicalDefendFromSpirit(Fightable cultivator, Battle battle);

    public void physicalDefendFromPhysical(Fightable cultivator, Battle battle);
}
