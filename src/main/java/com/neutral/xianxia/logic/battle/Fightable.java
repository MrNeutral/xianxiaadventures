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

/**
 *
 * @author Mr.Neutral
 */
public interface Fightable {

    public double getPhysicalAttack();

    public double getSpiritAttack();

    public double getPhysicalDefence();

    public double getSpiritDefense();

    public void attackSpirit(Fightable cultivator);

    public void defendSpirit(Fightable cultivator);

    public void defendSimpleFromSpirit(Fightable cultivator);

    public void attackPhysical(Fightable cultivator);

    public void defendPhysical(Fightable cultivator);

    public void defendSimpleFromPhysical(Fightable cultivator);

    public void spiritDefendFromSpirit(Fightable cultivator);

    public void spiritDefendFromPhysical(Fightable cultivator);

    public void physicalDefendFromSpirit(Fightable cultivator);

    public void physicalDefendFromPhysical(Fightable cultivator);
}
