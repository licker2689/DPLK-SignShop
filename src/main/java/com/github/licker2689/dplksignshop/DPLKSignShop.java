package com.github.licker2689.dplksignshop;

import com.darksoldier1404.dppc.lang.DLang;
import com.darksoldier1404.dppc.utils.ConfigUtils;
import com.github.licker2689.dplksignshop.command.DLSsCommand;
import com.github.licker2689.dplksignshop.event.DLSsEvent;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Location;

import java.util.*;

import com.github.licker2689.dplksignshop.event.DLSsEvent;
import org.jetbrains.annotations.NotNull;

public final class DPLKSignShop extends JavaPlugin implements Listener {
    private static DPLKSignShop plugin;
    public static YamlConfiguration config;

    public static final String prefix = "§f[ §aDLSs §f] ";

    public static DLang lang;

    public static BidiMap<Location, String> signs = new BidiMap<Location, String>() {
        @Override
        public String put(Location location, String s) {
            return null;
        }

        @Override
        public Location getKey(Object o) {
            return null;
        }

        @Override
        public Location removeValue(Object o) {
            return null;
        }

        @Override
        public BidiMap<String, Location> inverseBidiMap() {
            return null;
        }

        @Override
        public Set<String> values() {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public String get(Object key) {
            return null;
        }

        @Override
        public String remove(Object key) {
            return null;
        }

        @Override
        public void putAll(@NotNull Map<? extends Location, ? extends String> m) {

        }

        @Override
        public void clear() {

        }

        @NotNull
        @Override
        public Set<Location> keySet() {
            return null;
        }

        @NotNull
        @Override
        public Set<Entry<Location, String>> entrySet() {
            return null;
        }

        @Override
        public MapIterator<Location, String> mapIterator() {
            return null;
        }
    };

    public static DPLKSignShop getInstance() {
        return plugin;
    }

    public String name;

    @Override
    public void onEnable() {
        plugin = this;
        config = ConfigUtils.loadDefaultPluginConfig(plugin);
        lang = new DLang(config.getString("Settings.Lang") == null ? "Korean" : config.getString("Settings.Lang"), plugin);
        plugin.getServer().getPluginManager().registerEvents(new DLSsEvent(), plugin);
        Objects.requireNonNull(getCommand("dlss")).setExecutor(new DLSsCommand());

    }


    @Override
    public void onDisable() {
        ConfigUtils.savePluginConfig(plugin, config);
    }
}
