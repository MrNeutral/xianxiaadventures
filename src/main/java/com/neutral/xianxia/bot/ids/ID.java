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
package com.neutral.xianxia.bot.ids;

/**
 *
 * @author Mr.Neutral
 */
public enum ID {
    ID_NEUTRAL("272493736187920384"),
    ID_GENERAL_CBT("597532360522596368"),
    ID_CULTIVATOR("609732842192109589");
    private final String ID;

    private ID(String id) {
        ID = id;
    }

    public String getID() {
        return ID;
    }
    
    
    
    
}
