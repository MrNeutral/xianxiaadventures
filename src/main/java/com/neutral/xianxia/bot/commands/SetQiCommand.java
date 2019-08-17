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
public class SetQiCommand extends Command {
    
    public SetQiCommand() {
        super.name = "setqi";
        super.help = "Upgrades Qi increasing Spirit in the process";
        super.arguments = "[@target] [amount] [bool:force]";
        super.hidden = true;
        super.requiredRole = "Cultivator";
        super.ownerCommand = true;
    }
    
    @Override
    protected void execute(CommandEvent e) {
        String[] args = e.getArgs().split(" ");
        if (args.length == 1 && args[0].equals("")) {
            e.reply("Please enter an amount and optionally a target");
            return;
        }
        if (!e.getMessage().getMentionedMembers().isEmpty()) {
            try {
                Player target = GameSystem.getPlayer(e.getMessage().getMentionedMembers().get(0).getId());
                if (args.length == 3 && args[2].equals("true")) {
                    GameSystem.setPlayerQi(Integer.valueOf(args[1]), target);
                    e.reply(target.getName() + "'s Qi set to " + target.getQiLevel().getName());
                } else {
                    GameSystem.upgradePlayerQi(Integer.valueOf(args[1]), target);
                    e.reply(target.getName() + "'s Qi leveled to " + target.getQiLevel().getName());
                }
            } catch (NumberFormatException ex) {
                e.reply("Invalid amount.");
            }
        } else {
            try {
                Player player = GameSystem.getPlayer(e.getMember().getId());
                if (args.length == 2 && args[1].equals("true")) {
                    GameSystem.setPlayerQi(Integer.valueOf(args[0]), player);
                    e.reply(player.getName() + "'s Qi set to " + player.getQiLevel().getName());
                } else {
                    GameSystem.upgradePlayerQi(Integer.valueOf(args[0]), player);
                    e.reply(player.getName() + "'s Qi leveled to " + player.getQiLevel().getName());
                }
            } catch (NumberFormatException ex) {
                e.reply("Invalid amount.");
            }
        }
    }
}
