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
public class CheckUpgradeCommand extends Command {

    public CheckUpgradeCommand() {
        super.name = "check";
        super.help = "Check exp needed for leveling Qi and Body and tribulations";
        super.requiredRole = "Cultivator";
        super.cooldown = 5;
    }

    @Override
    protected void execute(CommandEvent e) {
        Player player = GameSystem.getPlayer(e.getMember().getId());
        int neededExpQi = 0;
        try {
            neededExpQi = GameSystem.getUpgradeCost("Qi", player) - player.getExp();
        } catch (NullPointerException ex) {
        }
        int neededExpBody = 0;
        try {
            neededExpBody = GameSystem.getUpgradeCost("Body", player) - player.getExp();
        } catch (NullPointerException ex) {
        }

        e.reply("You need " + ((neededExpQi > 0) ? neededExpQi + " more" : "no more") + " exp to upgrade Qi.");
        e.reply("You need " + ((neededExpBody > 0) ? neededExpBody + " more" : "no more") + " exp to upgrade Body.");
        if (GameSystem.getPlayer(e.getMember().getId()).isTribulationDue()) {
            e.reply("You need " + (GameSystem.getUpgradeCost("Body", player) + GameSystem.getUpgradeCost("Qi", player)) + " exp to face tribulation.");
        }
    }

}
