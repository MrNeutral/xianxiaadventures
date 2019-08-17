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
package com.neutral.xianxia.bot.sql;

import com.neutral.xianxia.game.logic.GameSystem;
import com.neutral.xianxia.game.logic.Player;
import com.neutral.xianxia.game.logic.events.EventFlag;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mr.Neutral
 */
public class Storage {

    private static Connection conn = null;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-217-206-65.eu-west-1.compute.amazonaws.com:5432/ddn3vv8iunrqeo?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", "vvdjqnfqfwdvil", "9aae73205cbb08392b59ba5797b35709b8307b16c1882fc47e764e07dadf78c0");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static final void updatePlayer(Player player) {
        updatePlayerName(player);
        updatePlayerLevel(player);
        updatePlayerStats(player);
        updatePlayerFlags(player);
    }

    private static final void updatePlayerName(Player player) {
        try (Statement statement = conn.createStatement()) {
            statement.execute("UPDATE cultivator_name SET name = '"
                    + player.getName()
                    + "' WHERE _id = '"
                    + player.getID() + "'");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static final void updatePlayerLevel(Player player) {
        try (Statement statement = conn.createStatement()) {
            statement.execute("UPDATE cultivator_level SET qi = '"
                    + player.getQiLevel().getRank()
                    + "', body = '"
                    + player.getBodyLevel().getRank()
                    + "', realm = '"
                    + player.getCultivationRealm().getRank()
                    + "', powerlevel = '"
                    + player.getPowerLevel()
                    + "' WHERE _id = '"
                    + player.getID()
                    + "'");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static final void updatePlayerStats(Player player) {
        try (Statement statement = conn.createStatement()) {
            statement.execute("UPDATE cultivator_stats SET health = '"
                    + player.getHealth()
                    + "', spirit = '"
                    + player.getSpirit()
                    + "', exp = '"
                    + player.getExp()
                    + "', exp_mult = '"
                    + player.getExpMultiplier()
                    + "' WHERE _id = '"
                    + player.getID()
                    + "'");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static final void updatePlayerFlags(Player player) {
        try (Statement statement = conn.createStatement()) {
            statement.execute("UPDATE cultivator_flags SET has_pet = '"
                    + player.getFlag(EventFlag.HAS_PET)
                    + "', joined_sect = '"
                    + player.getFlag(EventFlag.JOINED_SECT)
                    + "', crippled = '"
                    + player.getFlag(EventFlag.CRIPPLED)
                    + "', tribulation_due = '"
                    + player.isTribulationDue()
                    + "' WHERE _id = '"
                    + player.getID()
                    + "'");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static final List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();

        try (Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery(
                "SELECT * FROM alldata")) {

            while (results.next()) {
                Player player = new Player(String.valueOf(
                        results.getLong(1)), //id
                        results.getString(2), //name
                        results.getInt(3), //health
                        results.getInt(4), //spirit
                        0, //qi
                        0, //body
                        0, //realm
                        0, //powerlevel
                        results.getInt(9), //exp
                        results.getDouble(10), //exp_mult
                        results.getBoolean(11), //has_pet
                        results.getBoolean(12), //joined_sect
                        results.getBoolean(13) //crippled
                );
                GameSystem.upgradePlayerQi(results.getInt(5), player); //qi
                GameSystem.upgradePlayerBody(results.getInt(6), player); //body
                player.checkRealm();
                player.updatePowerLevel();
                player.setTribulationDue(results.getBoolean(14)); // tribulation due
                System.out.println("Loaded " + player.getName());
                players.add(player);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return players;
    }

    public static final void registerPlayer(Player player) {
        registerPlayerName(player);
        registerPlayerStats(player);
        registerPlayerLevel(player);
        registerPlayerFlags(player);
    }

    public static final boolean isCultivator(String id) {
        try (Statement statement = conn.createStatement(); ResultSet result = statement.executeQuery("SELECT name FROM cultivator_name WHERE _id = '" + id + "'")) {
            return result.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    private static final void registerPlayerName(Player player) {
        try (Statement statement = conn.createStatement()) {
            statement.execute("INSERT INTO cultivator_name VALUES('"
                    + player.getID() + "', '"
                    + player.getName() + "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static final void registerPlayerStats(Player player) {
        try (Statement statement = conn.createStatement()) {
            statement.execute("INSERT INTO cultivator_stats VALUES('"
                    + player.getID() + "', '"
                    + player.getHealth() + "', '"
                    + player.getSpirit() + "', '"
                    + player.getExp() + "', '"
                    + player.getExpMultiplier() + "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static final void registerPlayerLevel(Player player) {
        try (Statement statement = conn.createStatement()) {
            statement.execute("INSERT INTO cultivator_level VALUES('"
                    + player.getID() + "', '"
                    + player.getQiLevel().getRank() + "', '"
                    + player.getBodyLevel().getRank() + "', '"
                    + player.getCultivationRealm().getRank() + "', '"
                    + player.getPowerLevel() + "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static final void registerPlayerFlags(Player player) {
        try (Statement statement = conn.createStatement()) {
            statement.execute("INSERT INTO cultivator_flags VALUES('"
                    + player.getID() + "', '"
                    + player.getFlag(EventFlag.HAS_PET) + "', '"
                    + player.getFlag(EventFlag.JOINED_SECT) + "', '"
                    + player.getFlag(EventFlag.CRIPPLED) + "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static final void deletePlayer(String id) {
        try (Statement statement = conn.createStatement()) {
            statement.execute("DELETE FROM cultivator_name WHERE _id = '" + id + "'");
            statement.execute("DELETE FROM cultivator_flags WHERE _id = '" + id + "'");
            statement.execute("DELETE FROM cultivator_level WHERE _id = '" + id + "'");
            statement.execute("DELETE FROM cultivator_stats WHERE _id = '" + id + "'");
            GameSystem.deletePlayer(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static final void selectNames() {
        try (Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery("SELECT * FROM cultivator_name")) {
            System.out.println("printing names");
            while (results.next()) {
                System.out.println("id: " + results.getLong(1));
                System.out.println("name: " + results.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
