package com.neutral.xianxia;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.examples.command.ShutdownCommand;
import com.neutral.xianxia.bot.commands.BattleCommand;
import com.neutral.xianxia.bot.commands.CheckUpgradeCommand;
import com.neutral.xianxia.bot.commands.CultivateCommand;
import com.neutral.xianxia.bot.commands.DeRegisterCommand;
import com.neutral.xianxia.bot.commands.GetPlayersCommand;
import com.neutral.xianxia.bot.commands.GrantExp;
import com.neutral.xianxia.bot.commands.HealCommand;
import com.neutral.xianxia.bot.commands.LevelBody;
import com.neutral.xianxia.bot.commands.LevelQi;
import com.neutral.xianxia.bot.commands.RegisterCommand;
import com.neutral.xianxia.bot.commands.ResetCommand;
import com.neutral.xianxia.bot.commands.SetBodyCommand;
import com.neutral.xianxia.bot.commands.SetExpMultCommand;
import com.neutral.xianxia.bot.commands.SetQiCommand;
import com.neutral.xianxia.bot.commands.StatsCommand;
import com.neutral.xianxia.bot.commands.TribulationCommand;
import static com.neutral.xianxia.bot.ids.ID.ID_NEUTRAL;
import com.neutral.xianxia.bot.sql.Storage;
import com.neutral.xianxia.game.logic.GameSystem;
import java.util.List;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
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
        client.setPrefix("?");
        client.addCommands(new RegisterCommand(), new DeRegisterCommand(), new CultivateCommand(), new GetPlayersCommand(), new GrantExp(), new LevelBody(), new LevelQi(), new StatsCommand(), new SetBodyCommand(), new SetQiCommand(), new BattleCommand(), new ShutdownCommand() {
            @Override
            protected void execute(CommandEvent e) {
                GameSystem.updatePlayers();
                e.reactWarning();
                e.getJDA().shutdown();
                System.out.println("Bot shut down");
                System.exit(0);
            }
        }, new SetExpMultCommand(), new TribulationCommand(), new ResetCommand(), new HealCommand(), new CheckUpgradeCommand());
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
            System.out.println("Players loaded.");
            System.out.println("Starting timers...");
            GameSystem.dayTimer();
            GameSystem.commitToStorage();
            System.out.println("Timers started");
            List<Guild> guilds = event.getJDA().getGuilds();
            for (Guild guild : guilds) {
                if (guild.getName().equals("Cool Based Tests")) {
                    guild.getDefaultChannel().sendMessage("Bot Ready").queue();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

}
