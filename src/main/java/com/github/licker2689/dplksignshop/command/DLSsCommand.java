package com.github.licker2689.dplksignshop.command;

import com.darksoldier1404.dppc.lang.DLang;
import com.darksoldier1404.dss.functions.DSSFunction;
import com.github.licker2689.dplksignshop.DPLKSignShop;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.github.licker2689.dplksignshop.DPLKSignShop.prefix;

@SuppressWarnings("all")
public class DLSsCommand implements CommandExecutor {

    private final DPLKSignShop plugin = DPLKSignShop.getInstance();

    private final DLang lang = plugin.lang;

    public String name;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        Player p = (Player) sender;

        if (args.length == 0) {
            if (sender.hasPermission("dss.admin")) {
                sender.sendMessage(prefix + lang.get("shop_cmd_create"));
                sender.sendMessage(prefix + lang.get("shop_cmd_edit"));
            }
        }
        if (args.length == 1)
            if (args[0].equals("생성") || args[0].equalsIgnoreCase("create")) {
                if (!sender.hasPermission("dss.admin")) {
                    sender.sendMessage(prefix + lang.get("shop_cmd_permission_required"));
                    return false;
                }
                if (args.length == 1) {
                    sender.sendMessage(prefix + lang.get("shop_cmd_require_name"));
                    return false;
                }

                if (args.length == 2) {
                    DSSFunction.createShop(sender, args[1], args[2]);
                    name = args[1];
                    if (plugin.signs.containsKey(name)) {
                        p.sendMessage(plugin.prefix + "이미 존재하는 상점입니다.");
                        return false;
                    }
                    plugin.signs.put(name, new Location(p.getWorld(), p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ()));
                    p.sendMessage(plugin.prefix + "상점을 생성했습니다.");
                    plugin.config.set("Settings.warps." + name + ".world", p.getWorld().getName());
                    plugin.config.set("Settings.warps." + name + ".x", p.getLocation().getBlockX());
                    plugin.config.set("Settings.warps." + name + ".y", p.getLocation().getBlockY());
                    plugin.config.set("Settings.warps." + name + ".z", p.getLocation().getBlockZ());

                    return false;
                }
            }

        return false;
    }
}
