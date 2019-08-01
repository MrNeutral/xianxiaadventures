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
package com.neutral.xianxia.logic.events;

/**
 *
 * @author Mr.Neutral
 */
public enum EventFlag {
    JOINED_SECT(false),
    CRIPPLED(false),
    HAS_PET(false),
    FOUND_INHERITANCE(false),
    PASSED_TRIBULATION(false),
    FAILED_TRIBULATION(false);

    private boolean triggered;

    private EventFlag(boolean triggered) {
        this.triggered = triggered;
    }

    boolean isTriggered() {
        return triggered;
    }

    void setTriggered(boolean triggered) {
        this.triggered = triggered;
    }

}
