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
public class LevelBody extends Command {

    public LevelBody() {
        super.name = "levelbody";
        super.help = "Attempt to level up Body.";
        super.requiredRole = "Cultivator";
        super.cooldown = 5;
    }

    @Override
    protected void execute(CommandEvent e) {
        Player player = GameSystem.getPlayer(e.getMember().getId());

        if (GameSystem.canTribulationUpgrade("Body", player)) {
            if (GameSystem.getNextLevel(player.getBodyLevel()) != null) {
                e.reply("You used " + GameSystem.getNextLevel(player.getBodyLevel()).getCost() + " exp to break through. Health and Spirit are set to max.");
                GameSystem.attemptLevelUp("Body", player);
                if (GameSystem.checkTribulation(player)) {
                    e.reply("You cannot increase your Body further unless you face tribulation");
                }
            } else {
                e.reply("You have reached the peak and can go no further.");
            }
        } else if (GameSystem.isTribulationDue(player)) {
            e.reply("You cannot increase your Body any further unless you face tribulation.");
        } else {
            int damage = (int) -Math.round(player.getMaxHealth() * 0.2);
            e.reply("You do not have enough exp to break through and trying to do so damages you. You lose " + damage + " health.");
            player.changeHealth(damage);
        }
    }

}
