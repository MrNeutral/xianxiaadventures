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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mr.Neutral
 */
public class ChallengersCommand extends Command {

    public ChallengersCommand() {
        super.name = "challengers";
        super.help = "Lists all cultivators that you can fight";
        super.requiredRole = "Cultivator";
        super.cooldown = 10;
    }

    @Override
    protected void execute(CommandEvent e) {
        List<Player> players = new ArrayList<>(GameSystem.getPlayers());
        players.remove(GameSystem.getPlayer(e.getMember().getId()));
        String challengers = "";
        for (Player player : players) {
            if (player.getCultivationRealm() == GameSystem.getPlayer(e.getMember().getId()).getCultivationRealm() && player.getHealth() > 0) {
                challengers += player.getName() + "\n";
            }
        }
        e.reply("You can fight against:\n" + challengers);
    }

}
