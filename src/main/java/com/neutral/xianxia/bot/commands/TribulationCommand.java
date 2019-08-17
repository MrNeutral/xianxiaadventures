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
package com.neutral.xianxia.bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.neutral.xianxia.game.logic.GameSystem;
import com.neutral.xianxia.game.logic.Player;
import com.neutral.xianxia.game.logic.levels.BodyLevel;
import com.neutral.xianxia.game.logic.levels.QiLevel;

/**
 *
 * @author Mr.Neutral
 */
public class TribulationCommand extends Command {

    public TribulationCommand() {
        super.name = "tribulation";
        super.help = "Face tribulation to ascend";
        super.requiredRole = "Cultivator";
        super.cooldown = 10;
    }

    @Override
    protected void execute(CommandEvent e) {

        Player player = GameSystem.getPlayer(e.getMember().getId());
        if (!GameSystem.isTribulationDue(player)) {
            e.reply("You don't need to face tribulation.");
            return;
        }
        int tribulationCost
                = GameSystem.getNextLevel(BodyLevel.getRealm(player.getBodyLevel().getRank())).getCost()
                + GameSystem.getNextLevel(QiLevel.getRealm(player.getQiLevel().getRank())).getCost();

        if (player.getExp() - tribulationCost >= 0) {
            e.reply(GameSystem.triggerTribulation(player));
        } else {
            e.reply("You don't have enough exp to face tribulation. You need " + tribulationCost + ".");
        }
    }

}
