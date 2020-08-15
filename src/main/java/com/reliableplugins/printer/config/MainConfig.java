/*
 * Project: Printer
 * Copyright (C) 2020 Bilal Salha <bsalha1@gmail.com>
 * GNU GPLv3 <https://www.gnu.org/licenses/gpl-3.0.en.html>
 */

package com.reliableplugins.printer.config;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class MainConfig extends Config
{
    private boolean scoreboard;
    private int costNotificationTime;
    private List<Material> unbreakables;
    private List<Material> unplaceables;
    private List<String> allowedCommands;
    private boolean onlyBreakPlaced;
    private boolean requireEmptyInventory;

    private boolean allowInWilderness;
    private boolean useFactions;

    private boolean useShopGuiPlus;

    private boolean useSuperiorSkyBlock;
    private boolean allowInNonIsland;
    private boolean allowNearNonIslandMembers;

    private int scoreboardMargin;
    private String scoreboardTitle;
    private String costScoreTitle;
    private String blocksScoreTitle;
    private String balanceScoreTitle;
    private String costFormat;
    private String blocksFormat;
    private String balanceFormat;

    public MainConfig()
    {
        super("config.yml");
    }

    @Override
    public void load()
    {
        scoreboard = getBoolean("scoreboard-enabled", true);
        costNotificationTime = getInt("cost-notification-time", 5);
        onlyBreakPlaced = getBoolean("only-break-placed", true);
        requireEmptyInventory = getBoolean("require-empty-inventory", false);
        unbreakables = getMaterialList("unbreakable-blocks", Arrays.asList(Material.BEDROCK, Material.BARRIER, Material.ENDER_PORTAL_FRAME, Material.DRAGON_EGG));
        unplaceables = getMaterialList("unplaceable-blocks", Arrays.asList(Material.POTION, Material.MONSTER_EGG));
        allowedCommands = getStringList("allowed-commands", Arrays.asList("f*", "printer*", "bal*", "tp*", "etp*", "msg % %"));

        useFactions = getBoolean("factions.support", true);
        allowInWilderness = getBoolean("factions.allow-in-wilderness", false);

        useShopGuiPlus = getBoolean("shopguiplus.support", true);

        useSuperiorSkyBlock = getBoolean("superior-skyblock.support", true);
        allowInNonIsland = getBoolean("superior-skyblock.allow-in-non-island", false);
        allowNearNonIslandMembers = getBoolean("superior-skyblock.allow-near-non-island-members", false);

        scoreboardMargin = getInt("scoreboard.margin", 32);
        scoreboardTitle = getColoredString("scoreboard.title", "&d&lPrinter");
        costScoreTitle = getColoredString("scoreboard.cost-score-title", "&7Cost&f:");
        costFormat = getColoredString("scoreboard.cost-format", "&c${NUM}");
        blocksScoreTitle = getColoredString("scoreboard.blocks-score-title", "&7Blocks&f:");
        blocksFormat = getColoredString("scoreboard.blocks-format", "&a{NUM}");
        balanceScoreTitle = getColoredString("scoreboard.balance-score-title", "&7Balance&f:");
        balanceFormat = getColoredString("scoreboard.balance-format", "&a${NUM}");

        save();
    }

    public int getCostNotificationTime()
    {
        return costNotificationTime;
    }

    public boolean isScoreboardEnabled()
    {
        return scoreboard;
    }

    public boolean isUnbreakable(Material material)
    {
        return unbreakables.contains(material);
    }

    public boolean isUnplaceable(Material material)
    {
        return unplaceables.contains(material);
    }

    public boolean isAllowedCommand(String command)
    {
        command = command.substring(1); // Take off '/'
        String[] commandArguments = command.split(" ");
        for(String allowedCommand : allowedCommands)
        {
            if(allowedCommand.contains("*") && command.startsWith(allowedCommand.replaceAll("\\*", "")))
            {
                return true;
            }
            else if(allowedCommand.contains("%"))
            {
                String[] allowedCommandArguments = allowedCommand.split(" ");
                if(commandArguments.length != allowedCommandArguments.length)
                {
                    continue;
                }

                int numMatches;
                for(numMatches = 0; numMatches < commandArguments.length; numMatches++)
                {
                    if(!allowedCommandArguments[numMatches].equalsIgnoreCase(commandArguments[numMatches])
                        && !allowedCommandArguments[numMatches].equals("%"))
                    {
                        break;
                    }
                }

                if(numMatches == commandArguments.length)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean allowInWilderness()
    {
        return allowInWilderness;
    }

    public boolean allowInNonIsland()
    {
        return allowInNonIsland;
    }

    public boolean allowNearNonIslandMembers()
    {
        return allowNearNonIslandMembers;
    }

    public boolean useShopGuiPlus()
    {
        return useShopGuiPlus;
    }

    public boolean useFactions()
    {
        return useFactions;
    }

    public boolean useSuperiorSkyBlock()
    {
        return useSuperiorSkyBlock;
    }

    public String getScoreboardTitle()
    {
        return scoreboardTitle;
    }

    public String getBlocksScoreTitle()
    {
        return blocksScoreTitle;
    }

    public String getCostScoreTitle()
    {
        return costScoreTitle;
    }

    public String getBalanceScoreTitle()
    {
        return balanceScoreTitle;
    }

    public int getScoreboardMargin()
    {
        return scoreboardMargin;
    }

    public String getBalanceFormat()
    {
        return balanceFormat;
    }

    public String getBlocksFormat()
    {
        return blocksFormat;
    }

    public String getCostFormat()
    {
        return costFormat;
    }

    public boolean onlyBreakPlaced()
    {
        return onlyBreakPlaced;
    }

    public boolean requireEmptyInventory()
    {
        return requireEmptyInventory;
    }
}
