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

import com.neutral.xianxia.logic.System;
import static com.neutral.xianxia.logic.events.Event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Mr.Neutral
 */
public final class EventManager {

    final static Random RANDOM = new Random();

    public static boolean getFlag(EventFlag flag) {
        for (EventFlag eventFlag : EventFlag.values()) {
            if (flag == eventFlag) {
                return eventFlag.isTriggered();
            }
        }
        return false;
    }

    public void checkAllowedEventTypes(List<EventType> eventTypes) {
        if (!EventFlag.JOINED_SECT.isTriggered()) {
            eventTypes.remove(EventType.SECT);
            eventTypes.remove(EventType.DISCIPLE);
        }

        if (!EventFlag.HAS_PET.isTriggered()) {
            eventTypes.remove(EventType.MYSTICAL_BEAST);
        }
    }

    public void checkAllowedEvents(List<Event> events) {
        if (System.getPlayerRealm().getRank() < 19 || EventFlag.CRIPPLED.isTriggered()) {
            events.remove(SYSTEM_DESTROYED);
        }

        if (EventFlag.JOINED_SECT.isTriggered()) {
            events.remove(JOIN_SECT);
        }
    }

    public void triggerEventFlags(Event event) {
        if (event == JOIN_SECT) {
            EventFlag.JOINED_SECT.setTriggered(true);
            System.setPlayerExpMultiplier(System.getPlayerExpMultiplier() + 0.5);
        }

        if (event == GOT_PET) {
            EventFlag.HAS_PET.setTriggered(true);
            System.setPlayerExpMultiplier(System.getPlayerExpMultiplier() + 0.2);
        }

        if (event == ENLIGHTENMENT) {
            System.setPlayerExpMultiplier(System.getPlayerExpMultiplier() + 1);
        }

        if (event == SYSTEM_DESTROYED) {
            EventFlag.CRIPPLED.setTriggered(true);
            System.setPlayerExpMultiplier(System.getPlayerExpMultiplier() - 1);
        }

        if (event == PET_DIED || event == PET_POISONED || event == PET_FOUND_MATE || event == PET_KILLED || event == PET_STOLEN || event == PET_TAMED_BY_OTHER) {
            EventFlag.HAS_PET.setTriggered(false);
            System.setPlayerExpMultiplier(System.getPlayerExpMultiplier() - 0.2);
        }
    }

    public Event getRandomEvent() {
        List<EventType> allowedEventTypes = new ArrayList<>();
        allowedEventTypes.addAll(Arrays.asList(EventType.values()));
        checkAllowedEventTypes(allowedEventTypes);

        List<Event> allowedEvents = new ArrayList<>();
        allowedEventTypes.forEach(eventType -> allowedEvents.addAll(eventType.getEvents()));
        checkAllowedEvents(allowedEvents);

        double chance = Double.valueOf(String.format("%.1f", RANDOM.nextDouble()));

        EventType eventType;
        Event event;

        do {
            eventType = EventType.values()[RANDOM.nextInt(EventType.values().length - 1)];
            event = eventType.getRandomEvent();

            chance = (Double.valueOf(String.format("%.1f", chance - 0.1)) > 0) ? Double.valueOf(String.format("%.1f", chance - 0.1)) : 0;

        } while (event.getRarity() <= Double.valueOf(String.format("%.1f", chance + 0.1)));

        triggerEventFlags(event);

        return event;

    }

    public String triggerTribulation() {
        Random random = new Random();
        int tribulationStrength;
        switch (System.getPlayerRealm()) {
            case CORE_FORMATION_REALM:
                tribulationStrength = random.nextInt(TRIBULATION.MINOR_3_TRIBULATIONS.getDifficulty() + 1);
                if (System.getPlayerHealth() - tribulationStrength * 300 > 0) {
                    System.setPlayerHealth(System.getPlayerHealth() - tribulationStrength * 300);
                    System.upgradePlayerBody(System.getPlayerBody().getRank() + 1);
                    System.upgradePlayerQi(System.getPlayerQi().getRank() + 1);
                    System.setTribulationDue(false);
                    return "You managed to survive the 3 Minor Tribulations";
                } else {
                    EventFlag.CRIPPLED.setTriggered(true);
                    System.setPlayerHealth(1);
                    System.setPlayerSpirit(0);
                    System.setPlayerBody(0);
                    System.setPlayerQi(0);
                    System.setPlayerMaxHealth(10);
                    System.setPlayerMaxSpirit(0);
                    System.setTribulationDue(false);
                    return "You fail to surpass the 3 Minor Tribulations";
                }
            case SAGE_REALM:
                tribulationStrength = random.nextInt(TRIBULATION.LESSER_6_TRIBULATIONS.getDifficulty() + 1);
                if (System.getPlayerHealth() - tribulationStrength * 600 > 0) {
                    System.setPlayerHealth(System.getPlayerHealth() - tribulationStrength * 600);
                    System.upgradePlayerBody(System.getPlayerBody().getRank() + 1);
                    System.upgradePlayerQi(System.getPlayerQi().getRank() + 1);
                    System.setTribulationDue(false);
                    return "You managed to survive the 6 Lesser Tribulations";
                } else {
                    EventFlag.CRIPPLED.setTriggered(true);
                    System.setPlayerHealth(1);
                    System.setPlayerSpirit(0);
                    System.setPlayerBody(28);
                    System.setPlayerQi(28);
                    System.setPlayerMaxHealth(1000);
                    System.setPlayerMaxSpirit(1000);
                    System.setTribulationDue(false);
                    return "You fail to surpass the 6 Lesser Tribulations";
                }
            default:
                System.setTribulationDue(false);
                return "You fail to surpass the tribulation and turn to dust";
        }
    }

}
