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
import static com.neutral.xianxia.bot.ids.ID.ID_NEUTRAL;
import com.neutral.xianxia.game.logic.GameSystem;
import com.neutral.xianxia.game.logic.Player;

/**
 *
 * @author Mr.Neutral
 */
public class HealCommand extends Command {

    public HealCommand() {
        super.name = "heal";
        super.arguments = "[@target]";
        super.help = "Use Spirit to heal yourself. 20% of Spirit for 10% of health.";
        super.requiredRole = "Cultivator";
        super.cooldown = 5;
    }

    @Override
    protected void execute(CommandEvent e) {
        Player player;
        if (e.getMessage().getMentionedMembers().size() == 1) {
            player = GameSystem.getPlayer(e.getMessage().getMentionedMembers().get(0).getId());
        } else {
            player = GameSystem.getPlayer(e.getMember().getId());
        }

        int healCost = player.getMaxHealth() - player.getHealth() * 2;
        if (e.getMessage().getMember().getId().equals(ID_NEUTRAL.getID())) {
            healCost = 0;
        }
        if (player.getHealth() == player.getMaxHealth()) {
            if (!player.getName().equals(GameSystem.getPlayer(e.getMember().getId()))) {
                e.reply("You have no injuries.");
            } else {
                e.reply(player.getName() + "has no injuries.");
            }
        } else if (player.getSpirit() - healCost >= 0) {
            if (!player.getName().equals(GameSystem.getPlayer(e.getMember().getId()).getName())) {
                e.reply("It costs you " + healCost + " Spirit to fully heal " + player.getName() + ".");
            } else {
                e.reply("It costs you " + healCost + " Spirit to fully heal yourself.");
            }
            player.changeHealth(Math.round(healCost / 2));
            player.changeSpirit(-healCost);
        } else {
            int healAmount = Math.round((healCost + (player.getSpirit() - healCost)) / 2);
            if (!player.getName().equals(GameSystem.getPlayer(e.getMember().getId()).getName())) {
                e.reply("You use all of your Spirit to heal " + player.getName() + " " + healAmount + " points.");
            } else {
                e.reply("You use all of your Spirit to heal yourself " + healAmount + " points.");
            }
            player.changeSpirit(-healCost);
            player.changeHealth(healAmount);
        }
    }

}
