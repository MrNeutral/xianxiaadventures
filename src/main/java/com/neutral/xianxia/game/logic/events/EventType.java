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

import static com.neutral.xianxia.game.logic.events.GameEvent.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Mr.Neutral
 */
public enum EventType {
    //<editor-fold defaultstate="collapsed" desc="GameEvent types">
    SECT(
            SECT_MEMBER_DIES,
            SENIOR_ADVISES,
            ADMIRER_GIFTS,
            SECT_DESTOYED
    ),
    MYSTICAL_BEAST(
            BITTEN_BY_PET,
            PET_DIED,
            PET_KILLED,
            PET_POISONED,
            PET_FOUND_MATE,
            PET_STOLEN,
            PET_ATE_TREASURE,
            PET_FINDS_TREASURE,
            FAILED_TO_TAME_PET,
            PET_TAMED_BY_OTHER
    ),
    PILL_CONSUMPTION(
            PILL_COUGH_BLOOD,
            PILL_IMPURE_CURE,
            PILL_PURE,
            PILL_CULTIVATION,
            PILL_GODLY,
            PILL_FAINT,
            PILL_SHRINK,
            PILL_GROW,
            PILL_BUNNY,
            PILL_DREAM
    ),
    GAMBLING(
            GAMBLING_FAIL,
            GAMBLING_SUCCESS,
            CASINO_CHEAT_FOUND
    ),
    AMBUSH(
            ASSASSIN_SNEAK_ATTACK,
            CUSTOMER_MOB
    ),
    SLAVES(
            SLAVES_STEAL_MONEY
    ),
    SHOP(
            SHOP_ROBBED,
            SHOP_PROFITS,
            SHOPKEEPER_STEALS_EVERYTHING
    ),
    CHOSEN_ONE(
            CHOSEN_KILLS_GRANDSON,
            CHOSEN_DESTROYS_PAVILION
    ),
    RANDOM(
            PROMISSORY_NOTE,
            SLIP_BANANA,
            HOT_HOTPOT,
            CRASH_BOAT,
            JOIN_SECT
    ),
    CULTIVATION_CAVE(
            DRAGON_IN_CAVE,
            CAVE_DESTROYED,
            CAVE_ROBBED,
            CAVE_STOLEN
    ),
    INHERITANCE(
            INHERITANCE_ALREADY_CLAIMED,
            INHERITANCE_FOUND
    ),
    DISCIPLE(
            DISCIPLE_BETRAYS,
            LOYAL_DISCIPLE
    ),
    CULTIVATION(
            SYSTEM_DESTROYED,
            ENLIGHTENMENT
    ),
    ALCHEMY(
            CAULDRON_EXPLODES,
            PILL_SUCCESS,
            PILL_RUINED
    ),
    CULTIVATORS(
            NEIGHBOR_BBQ
    ),
    AUCTION(
            AUCTION_BEAT_UP,
            AUCTION_GET_TREASURE
    ),
    USERS(
            PRAY_EXP_SUCCESS,
            PRAY_EXP_FAILURE
    ),
    BATTLE(
            EASY_BATTLE,
            AVERAGE_BATTLE,
            HARD_BATTLE
    );
    //</editor-fold>

    List<GameEvent> events = new ArrayList<>();

    private EventType(GameEvent... event) {
        this.events.addAll(Arrays.asList(event));
    }

    GameEvent getRandomEvent() {
        return events.get(EventManager.RANDOM.nextInt((events.size() - 1 > 0) ? events.size() - 1 : 1));
    }

    List<GameEvent> getEvents() {
        return this.events;
    }

}
