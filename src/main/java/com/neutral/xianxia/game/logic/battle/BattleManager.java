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

import com.neutral.xianxia.game.logic.GameSystem;
import static com.neutral.xianxia.game.logic.GameSystem.getNextLevel;
import com.neutral.xianxia.game.logic.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Mr.Neutral
 */
public class BattleManager {

    private final Random random = new Random();
    private final Map<String, Battle> battles = new HashMap<>();

    public boolean initBattle(Player player, Player enemyPlayer) {
        if (battles.size() > 10) {
            battles.clear();
        }
        
        Battle battle = new Battle(player, enemyPlayer);
        battle.setHandicap(100);
        battles.remove(player.getID());
        battles.put(player.getID(), battle);
        return random.nextBoolean();
    }

    public boolean initBattle(Player player) {
        if (battles.size() > 10) {
            battles.clear();
        }

        int handicap;
        int luck = random.nextInt(101);

        if (luck >= 50) {
            handicap = random.nextInt(3) - 3;
            // handicap range: -3, -2, -1
        } else if (luck >= 25) {
            handicap = random.nextInt(3) - 1;
            // handicap range: -1, 0, 1
        } else {
            handicap = random.nextInt(3) + 1;
            // handicap range: 1, 2, 3
        }

        Battle battle = new Battle(player);

        battle.setHandicap(handicap);

        battles.remove(player.getID());
        battles.put(player.getID(), battle);

        while (battle.getEnemy()
                .getQiLevel().getRank() < (GameSystem.getPlayerQi(player).getRank() - handicap)) {
            if (getNextLevel(battle.getEnemy().getQiLevel()) == null) {
                break;
            }
            battle.getEnemy().levelQi();
        }

        while (battle.getEnemy()
                .getBodyLevel().getRank() < (GameSystem.getPlayerBody(player).getRank() - handicap)) {
            if (getNextLevel(battle.getEnemy().getBodyLevel()) == null) {
                break;
            }
            battle.getEnemy().levelBody();
        }

        battle.getEnemy()
                .checkRealm();

        return random.nextBoolean();
    }

    public Map<String, Battle> getBattles() {
        return battles;
    }

    public Battle getBattle(String ID) {
        return battles.get(ID);
    }
}
