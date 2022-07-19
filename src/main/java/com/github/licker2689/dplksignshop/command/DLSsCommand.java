package com.github.licker2689.dplksignshop.command;

import com.darksoldier1404.dppc.lang.DLang;
import com.darksoldier1404.dss.functions.DSSFunction;
import com.github.licker2689.dplksignshop.DPLKSignShop;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
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
                    if (!(p.getEyeLocation().getBlock().getType() == Material.SIGN_POST || p.getEyeLocation().getBlock().getType() == Material.WALL_SIGN)) {
                        sender.sendMessage(prefix + lang.get("shop_cmd_not_sign"));
                        return false;
                    }
                    DSSFunction.createShop(sender, args[1], args[2]);
                    plugin.name = args[1];
                    if (plugin.signs.containsKey(plugin.name)) {
                        p.sendMessage(plugin.prefix + "이미 존재하는 상점입니다.");
                        return false;
                    }
                    plugin.signs.put(new Location(p.getWorld(), p.getEyeLocation().getBlockX(), p.getEyeLocation().getBlockY(), p.getEyeLocation().getBlockZ()), plugin.name);
                    p.sendMessage(plugin.prefix + "상점을 생성했습니다.");
                    plugin.config.set("Settings.warps." + plugin.name + ".world", p.getWorld().getName());
                    plugin.config.set("Settings.warps." + plugin.name + ".x", p.getEyeLocation().getBlockX());
                    plugin.config.set("Settings.warps." + plugin.name + ".y", p.getEyeLocation().getBlockY());
                    plugin.config.set("Settings.warps." + plugin.name + ".z", p.getEyeLocation().getBlockZ());

                    return false;
                }
            }

        return false;
    }
}
