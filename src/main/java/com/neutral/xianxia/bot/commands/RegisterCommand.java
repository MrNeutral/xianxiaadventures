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
import static com.neutral.xianxia.bot.ids.ID.ID_CULTIVATOR;
import com.neutral.xianxia.bot.sql.Storage;
import com.neutral.xianxia.game.logic.GameSystem;
import com.neutral.xianxia.game.logic.Player;
import net.dv8tion.jda.api.entities.Guild;

/**
 *
 * @author Mr.Neutral
 */
public class RegisterCommand extends Command {
    
    public RegisterCommand() {
        super.name = "register";
        super.help = "Grants Cultivator role and registers you to the bot";
        super.ownerCommand = true;
    }
    
    @Override
    protected void execute(CommandEvent e) {
        try {
            Guild guild = e.getGuild();
            if (!Storage.isCultivator(e.getMember().getId())) {
                try {
                    Player player = new Player(e.getMember().getId(), e.getMember().getEffectiveName());
                    guild.addRoleToMember(e.getMember(), guild.getRoleById(ID_CULTIVATOR.getID())).queue();
                    GameSystem.addPlayer(player);
                    Storage.registerPlayer(player);
                    e.reply("Granted Cultivator role");
                } catch (Exception ex) {
                    e.reply("Something went wrong.");
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            } else {
                e.reply("You already are a Cultivator");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
}
