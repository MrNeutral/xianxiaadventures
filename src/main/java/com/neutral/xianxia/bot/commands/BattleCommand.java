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
import com.neutral.xianxia.game.logic.battle.Battle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

/**
 *
 * @author Mr.Neutral
 */
public class BattleCommand extends Command {

    public BattleCommand() {
        super.name = "battle";
        super.arguments = "[@target]";
        super.help = "Battle against other cultivators for exp";
        super.requiredRole = "Cultivator";
        super.cooldown = 10;
    }

    @Override
    protected void execute(CommandEvent e) {
        if (e.getMessage().getMentionedMembers().size() == 1) {
            Player player = GameSystem.getPlayer(e.getMember().getId());
            if (player.getHealth() == 0) {
                e.reply("You are already near death. There would be no point to going out to fight.");
                return;
            }
            try {
                Player enemyPlayer = GameSystem.getPlayer(e.getMessage().getMentionedMembers().get(0).getId());
                if (enemyPlayer.getHealth() == 0) {
                    e.reply("He is already near death. There would be no point to fighting him.");
                    return;
                }
                if (enemyPlayer.getCultivationRealm().getRank() > player.getCultivationRealm().getRank()) {
                    e.reply("That would be suicide. Better not.");
                    return;
                } else if (enemyPlayer.getCultivationRealm().getRank() < player.getCultivationRealm().getRank()) {
                    e.reply("You would lose face if you attacked a weaker cultivator.");
                    return;
                }
                GameSystem.fight(player, enemyPlayer);
                EmbedBuilder em = new EmbedBuilder();
                e.reply(generateEmbed(em, e, player).build());
            } catch (NullPointerException ex) {
                e.reply("Invalid target");
                return;
            }
        } else {
            Player player = GameSystem.getPlayer(e.getMember().getId());
            GameSystem.fight(player);
            EmbedBuilder em = new EmbedBuilder();
            e.reply(generateEmbed(em, e, player).build());
        }
    }

    protected static final EmbedBuilder generateEmbed(EmbedBuilder em, CommandEvent e, Player player) {

        User user = e.getAuthor();
        Battle battle = GameSystem.getBattle(player.getID());

        em.setAuthor(e.getMember().getEffectiveName());
        em.setThumbnail((user.getAvatarUrl() == null) ? user.getDefaultAvatarUrl() : user.getAvatarUrl());

        String emTitle = "Battle against ";

        int handicap = battle.getHandicap();
        if (handicap < 0) {
            emTitle += "a weaker cultivator";
        } else if (handicap < 2) {
            emTitle += "a fellow cultivator";
        } else if (handicap < 100) {
            emTitle += "a stronger cultivator";
        } else {
            emTitle += ((Player) battle.getEnemy()).getName();
        }

        em.setTitle(emTitle);

        em.setDescription("**Enemy Stats After Battle**\n"
                + "Health: " + battle.getEnemy().getHealth() + "/" + battle.getEnemy().getMaxHealth() + "\n"
                + "Spirit: " + battle.getEnemy().getSpirit() + "/" + battle.getEnemy().getMaxSpirit() + "\n"
                + "Body: " + battle.getEnemy().getBodyLevel().getName() + "\n"
                + "Qi: " + battle.getEnemy().getQiLevel().getName());

        try (BufferedReader reader = new BufferedReader(new StringReader(battle.getBattleHistory()))) {
            String line;
            int turn = 1;
            while ((line = reader.readLine()) != null) {
                if (line.contains("You lost") || line.contains("You managed") || turn == 24) {
                    while (!(line.contains("You lost") || line.contains("You managed"))) {
                        line = reader.readLine();
                    }
                    em.addField("Result:", line, false);
                    break;
                } else {
                    em.addField("Turn " + turn + ":", line, false);
                }
                turn++;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        if (battle.isPlayerWon()) {
            em.addField("You gained:", String.valueOf(GameSystem.calculateBattleExp(player)) + " exp", false);
        } else {
            em.addField("You lost:", String.valueOf(Math.round(GameSystem.calculateBattleExp(player) / 2)) + " exp", false);
        }

        em.setFooter("BattleHistory");

        return em;
    }

}
