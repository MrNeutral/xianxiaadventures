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
import static com.neutral.xianxia.bot.commands.BattleCommand.generateEmbed;
import com.neutral.xianxia.bot.sql.Storage;
import com.neutral.xianxia.game.logic.GameSystem;
import com.neutral.xianxia.game.logic.Player;
import com.neutral.xianxia.game.logic.events.GameEvent;
import java.util.Random;
import net.dv8tion.jda.api.EmbedBuilder;

/**
 *
 * @author Mr.Neutral
 */
public class CultivateCommand extends Command {

    public CultivateCommand() {
        super.name = "cultivate";
        super.help = "Roll for XP";
        super.requiredRole = "Cultivator";
        super.cooldown = 10;
    }

    @Override
    protected void execute(CommandEvent e) {

        if (!Storage.isCultivator(e.getMember().getId())) {
            return;
        }

        Player player = GameSystem.getPlayer(e.getMember().getId());
        
        if(player.getHealth() == 0){
            e.reply("Your Health is too low to cultivate. Heal or wait it out.");
            return;
        }
        
        int exp = player.cultivate();

        e.reply("*You gained **" + String.valueOf(exp) + "** exp.*");

        if (new Random().nextInt(101) <= 20) {

            GameEvent ge = GameSystem.getEvent(player);
            String title = ge.getEventName();
            String text = ge.getEventText();

            if (title.contains("Battle")) {

                GameSystem.fight(GameSystem.getPlayer(e.getMember().getId()));
                EmbedBuilder em = new EmbedBuilder();
                e.reply(generateEmbed(em, e, player).build());

            } else {
                exp = (int) Math.round((GameSystem.getPlayerExp(player) + 10) * ge.getExpEffect());

                String message = "**" + title + "**" + "\n" + text + "\n" + "*You " + ((ge.getExpEffect() >= 0) ? "gained " : "lost ") + String.valueOf(exp) + " exp.*";

                GameSystem.grantExp(exp, player);
                e.reply(message);
            }
        }
    }
}
