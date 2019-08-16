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
import com.neutral.xianxia.bot.sql.Storage;
import com.neutral.xianxia.game.logic.Player;
import java.util.List;

/**
 *
 * @author Mr.Neutral
 */
public class GetPlayersCommand extends Command {
    
    public GetPlayersCommand() {
        super.name = "getPlayers";
        super.help = "Print list of players and some data from DB";
        super.requiredRole = "Cultivator";
    }
    
    @Override
    protected void execute(CommandEvent e) {
        List<Player> players = Storage.getPlayers();
        StringBuilder message = new StringBuilder();
        for (Player player : players) {
            message.append(player.getName())
                    .append("\t")
                    .append(player.getBodyLevel().getName())
                    .append("\t")
                    .append(player.getQiLevel().getName())
                    .append("\t")
                    .append(player.getExp());
        }
        e.reply(message.toString());
    }
    
}
