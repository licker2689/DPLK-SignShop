package com.github.licker2689.dplksignshop;

import com.darksoldier1404.dppc.lang.DLang;
import com.darksoldier1404.dppc.utils.ConfigUtils;
import com.github.licker2689.dplksignshop.command.DLSsCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public final class DPLKSignShop extends JavaPlugin {
    private static DPLKSignShop plugin;
    public static YamlConfiguration config;

    public static final String prefix = "§f[ §aDLSs §f] ";

    public static DLang lang;

    public static Map<String, Location> signs = new HashMap<>();

    public static DPLKSignShop getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        config = ConfigUtils.loadDefaultPluginConfig(plugin);
        lang = new DLang("Korean", plugin);
        // plugin.getServer().getPluginManager().registerEvents(new DLSsEvent(), plugin);
        getCommand("dlss").setExecutor(new DLSsCommand());

    }



}
