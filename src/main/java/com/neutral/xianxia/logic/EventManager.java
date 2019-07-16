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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author Mr.Neutral
 */
public final class EventManager {

    private final static Random RANDOM = new Random();

    public static enum EVENT {
        FIND_MANUAL("You find an ancient cultivation manual", "Finding a manual", 100, 0.6, false), FIND_INHERITANCE("You find an ancient inheritance", "Finding an inheritance", 300, 0.8, false), FIND_TREASURE("You find an ancient treasure", "Finding a treasure", 50, 0.6, false), BEAT_UP("You got beat up by a young master", "Getting beat up", -50, 0.3, false), ENLIGHTENED("You got enlightened to a dao", "Enlightenment", 500, 1.0, false), CRIPPLED("You got crippled", "Being crippled", -500, 0.0, false), COUGH_BLOOD("You cough blood", "Injury", -20, 0.2, false), WANDER_AIMLESSLY("You wander aimlessly", "Aimless", 0, 0.5, false);

        private final UUID eventId = UUID.randomUUID();
        private final String eventText;
        private final String eventTitle;
        private final int expEffect;
        private final double luck;
        private final boolean isEncounter;

        private EVENT(String eventText, String eventTitle, int expEffect, double luck, boolean isEncounter) {
            this.eventText = eventText;
            this.eventTitle = eventTitle;
            this.expEffect = expEffect;
            this.luck = luck;
            this.isEncounter = isEncounter;
        }

        public final double getLuck() {
            return luck;
        }

        public static EVENT getRandomEvent() {
            double chance = Double.valueOf(String.format("%.1f", RANDOM.nextDouble()));
            List<EVENT> events = Arrays.asList(EVENT.values());
            Collections.shuffle(events);

            for (double i = 0.0; i <= chance; i -= 0.1) {
                for (EVENT event : events) {
                    if (event.getLuck() == chance - i) {
                        return event;
                    }
                }
            }

            return WANDER_AIMLESSLY;
        }

        /**
         * @return the eventId
         */
        public final UUID getEventId() {
            return eventId;
        }

        /**
         * @return the eventText
         */
        public final String getEventText() {
            return eventText;
        }

        /**
         * @return the eventTitle
         */
        public final String getEventTitle() {
            return eventTitle;
        }

        /**
         * @return the expEffect
         */
        public final int getExpEffect() {
            return expEffect;
        }

        /**
         * @return the isEncounter
         */
        public final boolean isIsEncounter() {
            return isEncounter;
        }

    }

    public EVENT getEvent() {
        return EVENT.getRandomEvent();
    }

}
