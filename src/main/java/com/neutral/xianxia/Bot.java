package com.neutral.xianxia;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.neutral.xianxia.bot.commands.CultivateCommand;
import com.neutral.xianxia.bot.commands.DeRegisterCommand;
import com.neutral.xianxia.bot.commands.GetPlayersCommand;
import com.neutral.xianxia.bot.commands.GrantExp;
import com.neutral.xianxia.bot.commands.LevelBody;
import com.neutral.xianxia.bot.commands.LevelQi;
import com.neutral.xianxia.bot.commands.RegisterCommand;
import com.neutral.xianxia.bot.commands.StatsCommand;
import static com.neutral.xianxia.bot.ids.ID.ID_NEUTRAL;
import com.neutral.xianxia.bot.sql.Storage;
import com.neutral.xianxia.game.logic.GameSystem;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


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
/**
 *
 * @author Mr.Neutral
 */
public class Bot extends ListenerAdapter {

    public static void main(String[] args)
            throws LoginException, InterruptedException {
        CommandClientBuilder client = new CommandClientBuilder();
        client.setOwnerId(ID_NEUTRAL.getID());
        client.setPrefix("$");
        client.addCommands(new RegisterCommand(), new DeRegisterCommand(), new CultivateCommand(), new GetPlayersCommand(), new GrantExp(), new LevelBody(), new LevelQi(), new StatsCommand());
        // Note: It is important to register your Bot before building
        JDA jda = new JDABuilder("NjA5NzIzOTY3MTQ5NzAzMTc4.XU645Q.fWDpsXAvOtMOdok9bd9g_H9wKRE")
                .addEventListeners(new Bot(), client.build())
                .build();

        // optionally block until JDA is ready
        jda.awaitReady();
    }

    @Override
    public void onReady(ReadyEvent event) {
        try {
            System.out.println("Bot loaded. Loading players...");
            GameSystem.addPlayers(Storage.getPlayers());
            System.out.println("Players loaded");
            event.getJDA().getGuilds().get(0).getDefaultChannel().sendMessage("Bot ready").queue();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
