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
        super.arguments = "[@target] [free]";
        super.help = "Use Spirit to heal yourself. 20% of Spirit for 10% of health. If target is included they are healed in your place.";
        super.requiredRole = "Cultivator";
        super.cooldown = 5;
    }

    @Override
    protected void execute(CommandEvent e) {
        Player target;
        String[] args = e.getArgs().split("");
        boolean cheating = false;
        for (String string : args) {
            if (string.equals("true")) {
                cheating = true;
            }
        }

        if (e.getMessage().getMentionedMembers().size() == 1) {
            target = GameSystem.getPlayer(e.getMessage().getMentionedMembers().get(0).getId());
        } else {
            target = GameSystem.getPlayer(e.getMember().getId());
        }

        if (GameSystem.getPlayer(e.getMember().getId()).getMaxSpirit() == 0) {
            e.reply("You have no Spirit to heal yourself. You have to wait for your wounds to heal on their own.");
        }

        int healCost = (int) Math.round(target.getMaxHealth() - target.getHealth() * 1.25);
        if (healCost < 0) {
            healCost = 0;
        }
        if (cheating && e.getMember().getId().equals(ID_NEUTRAL.getID())) {
            healCost = 0;
        }
        if (target.getHealth() == target.getMaxHealth()) {
            if (!target.getName().equals(GameSystem.getPlayer(e.getMember().getId()).getName())) {
                e.reply(target.getName() + " has no injuries.");
            } else {
                e.reply("You have no injuries.");
            }
        } else if (target.getSpirit() - healCost >= 0) {
            if (!target.getName().equals(GameSystem.getPlayer(e.getMember().getId()).getName())) {
                e.reply("It costs you " + healCost + " Spirit to fully heal " + target.getName() + ".");
            } else {
                e.reply("It costs you " + healCost + " Spirit to fully heal yourself.");
            }
            if (cheating && e.getMember().getId().equals(ID_NEUTRAL.getID())) {
                target.changeHealth(target.getMaxHealth());
            } else {
                target.changeHealth(Math.round(healCost / 2));
            }
            target.changeSpirit(-healCost);
        } else {
            int healAmount = 0;
            if (cheating) {
                healAmount = target.getMaxHealth();
            }
            if (!cheating || !e.getMessage().getMember().getId().equals(ID_NEUTRAL.getID())) {
                healAmount = Math.round((healCost + (target.getSpirit() - healCost)) / 2);
            }
            if (!target.getName().equals(GameSystem.getPlayer(e.getMember().getId()).getName())) {
                e.reply("You use all of your Spirit to heal " + target.getName() + " " + healAmount + " points.");
            } else {
                e.reply("You use all of your Spirit to heal yourself " + healAmount + " points.");
            }
            target.changeSpirit(-healCost);
            target.changeHealth(healAmount);
        }
    }

}
