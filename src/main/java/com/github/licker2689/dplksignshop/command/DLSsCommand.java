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
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;



@SuppressWarnings("all")
public class DLSsCommand implements CommandExecutor {

    private final DPLKSignShop plugin = DPLKSignShop.getInstance();

    private final DLang lang = plugin.lang;

    private final String prefix = plugin.prefix;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player) sender;

        if (args.length == 0) {
            sender.sendMessage(prefix + lang.get("shop_cmd_create"));
            sender.sendMessage(prefix + lang.get("shop_cmd_edit"));
        }

        if (args.length == 1) {
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
                    sender.sendMessage(prefix + lang.get("shop_cmd_require_line"));
                    return false;
                }
                if (args.length == 3) {
                    if (!(p.getEyeLocation().getBlock().getType() == Material.LEGACY_WALL_SIGN || p.getEyeLocation().getBlock().getType() == Material.LEGACY_SIGN_POST)) {
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
            if (args[0].equals("진열") || args[0].equalsIgnoreCase("display")) {
                if (!sender.hasPermission("dss.admin")) {
                    sender.sendMessage(prefix + lang.get("shop_cmd_permission_required"));
                    return false;
                }
                if (args.length == 1) {
                    sender.sendMessage(prefix + lang.get("shop_cmd_require_name"));
                    return false;
                }
                if (args.length == 2) {
                    DSSFunction.openShopShowCase((Player) sender, args[1]);
                    return false;
                }
            }
            if (args[0].equals("가격") || args[0].equalsIgnoreCase("price")) {
                if (!sender.hasPermission("dss.admin")) {
                    sender.sendMessage(prefix + lang.get("shop_cmd_permission_required"));
                    return false;
                }
                if (args.length == 1) {
                    sender.sendMessage(prefix + lang.get("shop_cmd_require_name"));
                    return false;
                }
                if (args.length == 2) {
                    sender.sendMessage(prefix + lang.get("shop_cmd_require_slot"));
                    return false;
                }
                if (args.length == 3) {
                    sender.sendMessage(prefix + lang.get("shop_cmd_require_buy_or_sell"));
                    return false;
                }
                if (args.length == 4) {
                    sender.sendMessage(prefix + lang.get("shop_cmd_require_price"));
                    return false;
                }
                int slot;
                double price;
                try {
                    slot = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(prefix + lang.get("shop_cmd_slot_must_be_number"));
                    return false;
                }
                boolean isBuying;
                if (args[3].equalsIgnoreCase("b")) {
                    isBuying = true;
                } else if (args[3].equalsIgnoreCase("s")) {
                    isBuying = false;
                } else {
                    sender.sendMessage(prefix + lang.get("shop_cmd_require_buy_or_sell_unvalid"));
                    return false;
                }
                try {
                    price = Double.parseDouble(args[4]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(prefix + lang.get("shop_cmd_price_must_be_number"));
                    return false;
                }
                if (isBuying) {
                    DSSFunction.setShopPrice(sender, slot, price, args[1]);
                } else {
                    DSSFunction.setShopSellPrice(sender, slot, price, args[1]);
                }
                return false;
            }
            if (args[0].equals("타이틀") || args[0].equalsIgnoreCase("title")) {
                if (!sender.hasPermission("dss.admin")) {
                    sender.sendMessage(prefix + lang.get("shop_cmd_permission_required"));
                    return false;
                }
                if (args.length == 1) {
                    sender.sendMessage(prefix + lang.get("shop_cmd_require_name"));
                    return false;
                }
                if (args.length == 2) {
                    sender.sendMessage(prefix + lang.get("shop_cmd_require_title"));
                    return false;
                }
                DSSFunction.setTitle(sender, args[1], args);
                return false;
            }
            if (args[0].equals("삭제") || args[0].equalsIgnoreCase("delete")) {
                if (!sender.hasPermission("dss.admin")) {
                    sender.sendMessage(prefix + lang.get("shop_cmd_permission_required"));
                    return false;
                }
                if (args.length == 1) {
                    sender.sendMessage(prefix + lang.get("shop_cmd_require_name"));

                    return false;
                }
                DSSFunction.removeShop(sender, args[1]);
                plugin.name = args[1];
                if (plugin.signs.containsKey(plugin.name)) {
                    plugin.signs.remove(plugin.signs.getKey(plugin.name));
                }

            }
            return false;

        }

        return false;
    }
}

































