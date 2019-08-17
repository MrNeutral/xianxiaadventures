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

/**
 *
 * @author Mr.Neutral
 */
public class ResetCommand extends Command {

    public ResetCommand() {
        super.name = "reset";
        super.arguments = "[@target]";
        super.ownerCommand = true;
        super.hidden = true;
    }

    @Override
    protected void execute(CommandEvent e) {
        Player player;
        if (e.getMessage().getMentionedMembers().size() == 1) {
            player = GameSystem.getPlayer(e.getMessage().getMentionedMembers().get(0).getId());
        } else {
            player = GameSystem.getPlayer(e.getMember().getId());
        }
        
        GameSystem.resetPlayer(player);
        e.reply(player.getName() + " was struck with Heavenly Tribulation and became a Mortal again.");
    }

}
