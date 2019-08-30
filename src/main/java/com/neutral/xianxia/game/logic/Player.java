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

import com.neutral.xianxia.game.logic.events.EventFlag;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mr.Neutral
 */
public final class Player extends Cultivator {

    private double expMultiplier = 1.0;
    private boolean tribulationDue = false;
    private final String ID;
    private String name;
    private final Map<EventFlag, Boolean> flags = new HashMap<>();

    public Player(String id, String name) {
        super();
        flags.put(EventFlag.JOINED_SECT, Boolean.FALSE);
        flags.put(EventFlag.CRIPPLED, Boolean.FALSE);
        flags.put(EventFlag.HAS_PET, Boolean.FALSE);
        flags.put(EventFlag.FOUND_INHERITANCE, Boolean.FALSE);
        flags.put(EventFlag.PASSED_TRIBULATION, Boolean.FALSE);
        ID = id;
        this.name = name;
    }

    public Player(String ID, String name, int health, int spirit, int qi, int body, int realm, int powerlevel, int exp, double expMult, boolean hasPet, boolean joinedSect, boolean crippled) {
        super(health, spirit, qi, body, realm, powerlevel, exp, expMult);
        this.ID = ID;
        this.name = name;
        flags.put(EventFlag.JOINED_SECT, joinedSect);
        flags.put(EventFlag.CRIPPLED, crippled);
        flags.put(EventFlag.HAS_PET, hasPet);
        flags.put(EventFlag.FOUND_INHERITANCE, Boolean.FALSE);
        flags.put(EventFlag.PASSED_TRIBULATION, Boolean.FALSE);
    }

    public int cultivate() {
        int exp = (int) (((Math.random() + 0.1) * 10) * expMultiplier
                * ((getCultivationRealm().getRank() > 0) ? getCultivationRealm().getRank() : 1));
        System.out.println(name + " gained XP: " + exp);
        grantExp(exp);
        return exp;
    }

    public boolean isTribulationDue() {
        return tribulationDue;
    }

    public void setTribulationDue(boolean tribulationDue) {
        this.tribulationDue = tribulationDue;
    }

    public Map<EventFlag, Boolean> getFlags() {
        return flags;
    }

    public boolean getFlag(EventFlag flag) {
        return flags.get(flag);
    }

    public boolean setFlag(EventFlag flag, boolean status) {
        return flags.replace(flag, status);
    }

    public double getExpMultiplier() {
        return this.expMultiplier;
    }

    public void changeExpMultiplier(double amount) {
        this.expMultiplier += amount;
    }

    public void setExpMultiplier(double expMultiplier) {
        if (expMultiplier > 0) {
            this.expMultiplier = Double.valueOf(String.format("%.1f", expMultiplier));
        } else {
            this.expMultiplier = 0.1;
        }
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
