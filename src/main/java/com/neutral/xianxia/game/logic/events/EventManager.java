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
package com.neutral.xianxia.game.logic.events;

import com.neutral.xianxia.game.logic.GameSystem;
import com.neutral.xianxia.game.logic.Player;
import static com.neutral.xianxia.game.logic.events.GameEvent.*;
import static com.neutral.xianxia.game.logic.levels.CultivationLevel.CORE_FORMATION_REALM;
import static com.neutral.xianxia.game.logic.levels.CultivationLevel.SAGE_REALM;
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

    public void checkAllowedEventTypes(List<EventType> eventTypes, Player player) {
        if (!player.getFlags().get(EventFlag.JOINED_SECT)) {
            eventTypes.remove(EventType.SECT);
            eventTypes.remove(EventType.DISCIPLE);
        }

        if (!player.getFlags().get(EventFlag.HAS_PET)) {
            eventTypes.remove(EventType.MYSTICAL_BEAST);
        }
    }

    public void checkAllowedEvents(List<GameEvent> events, Player player) {
        if (GameSystem.getPlayerRealm(player).getRank() < 19 || player.getFlags().get(EventFlag.CRIPPLED)) {
            events.remove(SYSTEM_DESTROYED);
        }

        if (player.getFlags().get(EventFlag.JOINED_SECT)) {
            events.remove(JOIN_SECT);
        }
    }

    public void triggerEventFlags(GameEvent event, Player player) {
        if (event == JOIN_SECT) {
            player.getFlags().replace(EventFlag.JOINED_SECT, true);
            GameSystem.setPlayerExpMultiplier(GameSystem.getPlayerExpMultiplier(player) + 0.5, player);
        }

        if (event == GOT_PET) {
            player.getFlags().replace(EventFlag.HAS_PET, true);
            GameSystem.setPlayerExpMultiplier(GameSystem.getPlayerExpMultiplier(player) + 0.2, player);
        }

        if (event == ENLIGHTENMENT) {
            GameSystem.setPlayerExpMultiplier(GameSystem.getPlayerExpMultiplier(player) + 1, player);
        }

        if (event == SYSTEM_DESTROYED) {
            player.getFlags().replace(EventFlag.CRIPPLED, true);
            GameSystem.setPlayerExpMultiplier(GameSystem.getPlayerExpMultiplier(player) - 1, player);
        }

        if (event == PET_DIED || event == PET_POISONED || event == PET_FOUND_MATE || event == PET_KILLED || event == PET_STOLEN || event == PET_TAMED_BY_OTHER) {
            player.getFlags().replace(EventFlag.HAS_PET, false);
            GameSystem.setPlayerExpMultiplier(GameSystem.getPlayerExpMultiplier(player) - 0.2, player);
        }
    }

    public GameEvent getRandomEvent(Player player) {
        List<EventType> allowedEventTypes = new ArrayList<>();
        allowedEventTypes.addAll(Arrays.asList(EventType.values()));
        checkAllowedEventTypes(allowedEventTypes, player);

        List<GameEvent> allowedEvents = new ArrayList<>();
        allowedEventTypes.forEach(eventType -> allowedEvents.addAll(eventType.getEvents()));
        checkAllowedEvents(allowedEvents, player);

        double chance = Double.valueOf(String.format("%.1f", RANDOM.nextDouble()));

        EventType eventType;
        GameEvent event;

        do {
            eventType = EventType.values()[RANDOM.nextInt(EventType.values().length - 1)];
            event = eventType.getRandomEvent();

            chance = (Double.valueOf(String.format("%.1f", chance - 0.1)) > 0) ? Double.valueOf(String.format("%.1f", chance - 0.1)) : 0;

        } while (event.getRarity() <= Double.valueOf(String.format("%.1f", chance + 0.1)));

        triggerEventFlags(event, player);

        return event;

    }

    public String triggerTribulation(Player player) {
        Random random = new Random();
        int tribulationStrength;
        switch (GameSystem.getPlayerRealm(player)) {
            case CORE_FORMATION_REALM:
                tribulationStrength = random.nextInt(TRIBULATION.MINOR_3_TRIBULATIONS.getDifficulty() + 1);
                if (GameSystem.getPlayerHealth(player) - tribulationStrength * 300 > 0) {
                    GameSystem.setPlayerHealth(GameSystem.getPlayerHealth(player) - tribulationStrength * 300, player);
                    GameSystem.upgradePlayerBody(GameSystem.getPlayerBody(player).getRank() + 1, player);
                    GameSystem.upgradePlayerQi(GameSystem.getPlayerQi(player).getRank() + 1, player);
                    GameSystem.setTribulationDue(false, player);
                    GameSystem.changeFlag(player, EventFlag.CRIPPLED, false);
                    GameSystem.setPlayerExpMultiplier(player.getExpMultiplier() * 2, player);
                    return "The tribulation deals " + tribulationStrength * 1200 + " damage. You manage to survive the 3 Minor Tribulations. You are no longer crippled, if you were previously.";
                } else {
                    player.getFlags().replace(EventFlag.CRIPPLED, true);
                    GameSystem.setPlayerExpMultiplier(player.getExpMultiplier() / 2, player);
                    GameSystem.setPlayerHealth(1, player);
                    GameSystem.setPlayerSpirit(0, player);
                    GameSystem.setPlayerBody(0, player);
                    GameSystem.setPlayerQi(0, player);
                    GameSystem.setPlayerMaxHealth(10, player);
                    GameSystem.setPlayerMaxSpirit(0, player);
                    GameSystem.setTribulationDue(false, player);
                    return "The tribulation deals " + tribulationStrength * 1200 + " damage. You failed to surpass the 3 Minor Tribulations. You are now crippled.";
                }
            case SAGE_REALM:
                tribulationStrength = random.nextInt(TRIBULATION.LESSER_6_TRIBULATIONS.getDifficulty() + 1);
                if (GameSystem.getPlayerHealth(player) - tribulationStrength * 2470 > 0) {
                    GameSystem.setPlayerHealth(GameSystem.getPlayerHealth(player) - tribulationStrength * 600, player);
                    GameSystem.upgradePlayerBody(GameSystem.getPlayerBody(player).getRank() + 1, player);
                    GameSystem.upgradePlayerQi(GameSystem.getPlayerQi(player).getRank() + 1, player);
                    GameSystem.setTribulationDue(false, player);
                    GameSystem.changeFlag(player, EventFlag.CRIPPLED, false);
                    GameSystem.setPlayerExpMultiplier(player.getExpMultiplier() * 2, player);
                    return "The tribulation deals " + tribulationStrength * 710 + " damage. You managed to survive the 6 Lesser Tribulations. You are no longer crippled, if you were previously.";
                } else {
                    player.getFlags().replace(EventFlag.CRIPPLED, true);
                    GameSystem.setPlayerExpMultiplier(player.getExpMultiplier() / 2, player);
                    GameSystem.setPlayerHealth(1, player);
                    GameSystem.setPlayerSpirit(0, player);
                    GameSystem.setPlayerBody(28, player);
                    GameSystem.setPlayerQi(28, player);
                    GameSystem.setPlayerMaxHealth(1000, player);
                    GameSystem.setPlayerMaxSpirit(1000, player);
                    GameSystem.setTribulationDue(false, player);
                    return "The tribulation deals " + tribulationStrength * 2470 + " damage. You failed to surpass the 6 Lesser Tribulations";
                }
            default:
                GameSystem.setTribulationDue(false, player);
                return "You fail to surpass the tribulation and turn to dust";
        }
    }

}
