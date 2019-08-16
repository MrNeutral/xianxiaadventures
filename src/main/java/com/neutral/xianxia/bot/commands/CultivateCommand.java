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
import com.neutral.xianxia.bot.sql.Storage;
import com.neutral.xianxia.game.logic.GameSystem;
import com.neutral.xianxia.game.logic.Player;
import com.neutral.xianxia.game.logic.battle.Battle;
import com.neutral.xianxia.game.logic.events.GameEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Random;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

/**
 *
 * @author Mr.Neutral
 */
public class CultivateCommand extends Command {

    public CultivateCommand() {
        super.name = "cultivate";
        super.help = "Roll for XP";
        super.requiredRole = "Cultivator";
    }

    @Override
    protected void execute(CommandEvent e) {

        if (!Storage.isCultivator(e.getMember().getId())) {
            return;
        }

        Player player = GameSystem.getPlayer(e.getMember().getId());
        player.cultivate();

        if (new Random().nextInt(101) <= 20) {
            
            GameEvent ge = GameSystem.getEvent(player);
            String title = ge.getEventName();
            String text = ge.getEventText();
            
            if (title.contains("Battle")) {
                
                GameSystem.fight(GameSystem.getPlayer(e.getMember().getId()));
                EmbedBuilder em = new EmbedBuilder();
                e.reply(generateEmbed(em, e, player).build());
                
            } else {
                int exp = (int) Math.round((GameSystem.getPlayerExp(player) + 10) * ge.getExpEffect());
                
                String message = "**" + title + "**" + "\n" + text + "\n" + "*You " + ((ge.getExpEffect() >= 0) ? "gained " : "lost ") + String.valueOf(exp) + " exp.*";
                
                GameSystem.grantExp(exp, player);
                e.reply(message);
            }
        }
    }

    private final EmbedBuilder generateEmbed(EmbedBuilder em, CommandEvent e, Player player) {
        
        User user = e.getAuthor();
        Battle battle = GameSystem.getBattle(player.getID());
        
        em.setAuthor(e.getMember().getEffectiveName());
        em.setThumbnail((user.getAvatarUrl() == null) ? user.getDefaultAvatarUrl() : user.getAvatarUrl());
        
        String emTitle = "Battle against ";

        int luck = battle.getHandicap();
        if (luck >= 50) {
            emTitle += "a weaker cultivator";
        } else if (luck >= 25) {
            emTitle += "a fellow cultivator";
            // handicap range: -1, 0, 1
        } else {
            emTitle += "a stronger cultivator";
        }

        em.setTitle(emTitle);

        em.setDescription("**Enemy Stats**\n"
                + "Health: " + battle.getEnemy().getMaxHealth() + "\n"
                + "Spirit: " + battle.getEnemy().getMaxSpirit() + "\n"
                + "Body: " + battle.getEnemy().getBodyLevel().getName() + "\n"
                + "Qi: " + battle.getEnemy().getQiLevel().getName());

        try (BufferedReader reader = new BufferedReader(new StringReader(battle.getBattleHistory()))) {
            String line;
            int turn = 1;
            while ((line = reader.readLine()) != null) {
                if (line.contains("You lost") || line.contains("You managed")) {
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
            em.addField("You lost:", String.valueOf(GameSystem.calculateBattleExp(player)) + " exp", false);
        }

        em.setFooter("BattleHistory");
        
        return em;
    }

}
