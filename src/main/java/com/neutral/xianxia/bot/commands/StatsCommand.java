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
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

/**
 *
 * @author Mr.Neutral
 */
public class StatsCommand extends Command {

    public StatsCommand() {
        super.name = "stats";
        super.help = "Show stats";
        super.arguments = "[@target]";
        super.requiredRole = "Cultivator";
    }

    @Override
    protected void execute(CommandEvent e) {
        String[] args = e.getArgs().split(" ");

        EmbedBuilder em = new EmbedBuilder();
        if (args.length == 1 && args[0].equals("")) {
            Player player = GameSystem.getPlayer(e.getMember().getId());
            User user = e.getAuthor();
            em = generateEmbed(em, user, player, e);
            e.reply(em.build());

        } else if (e.getMessage().getMentionedMembers().size() == 1) {
            Player player = GameSystem.getPlayer(e.getMessage().getMentionedMembers().get(0).getId());
            User user = e.getMessage().getMentionedMembers().get(0).getUser();
            em = generateEmbed(em, user, player, e);
            e.reply(em.build());
        } else {
            e.reply("Invalid arguments.");
        }
    }

    private final EmbedBuilder generateEmbed(EmbedBuilder em, User user, Player player, CommandEvent e) {
        em.setAuthor(e.getMember().getEffectiveName());
        em.setThumbnail((user.getAvatarUrl() == null) ? user.getDefaultAvatarUrl() : user.getAvatarUrl());
        em.setTitle(player.getCultivationRealm().getName());
        em.setDescription("**Player Stats**\n"
                + "Health: " + player.getHealth() + "/" + player.getMaxHealth() + "\n"
                + "Spirit: " + player.getSpirit() + "/" + player.getMaxSpirit() + "\n"
                + "Body: " + player.getBodyLevel().getName() + "\n"
                + "Qi: " + player.getQiLevel().getName() + "\n"
                + "Exp: " + player.getExp() + "\n"
                + "Exp Multiplier: " + player.getExpMultiplier());
        return em;
    }

}
