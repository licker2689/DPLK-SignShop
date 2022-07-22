package com.github.licker2689.dplksignshop.command;

import com.darksoldier1404.dss.functions.DSSFunction;
import com.github.licker2689.dplksignshop.DPLKSignShop;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;


@SuppressWarnings("all")
public class DLSsCommand implements CommandExecutor {

    private final DPLKSignShop plugin = DPLKSignShop.getInstance();

    private final YamlConfiguration config = plugin.data.getConfig();



    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player) sender;

        if (args.length == 0) {
            sender.sendMessage(plugin.data.getPrefix() + ("/dlss create <상점 이름>"));
            sender.sendMessage(plugin.data.getPrefix() + ("/dlss edit <상점 이름>"));
            return false;
        }

        if (args.length == 1) {
            if (args[0].equals("생성") || args[0].equalsIgnoreCase("create")) {
                if (!sender.hasPermission("dss.admin")) {
                    sender.sendMessage(plugin.data.getPrefix() + "상점 관리 권한 이 필요합니다");
                    return false;
                }
                if (args.length == 1) {
                    sender.sendMessage(plugin.data.getPrefix() + "상점 이름이 필요합니다");
                    return false;
                }
                if (args.length == 2) {
                    sender.sendMessage(plugin.data.getPrefix() + "shop_cmd_require_line");
                    return false;
                }
                if (args.length == 3) {
                    if (!(p.getEyeLocation().getBlock().getType() == Material.LEGACY_WALL_SIGN || p.getEyeLocation().getBlock().getType() == Material.LEGACY_SIGN_POST)) {
                        sender.sendMessage(plugin.data.getPrefix() + "shop_cmd_not_sign");
                        return false;
                    }
                    DSSFunction.createShop(sender, args[1], args[2]);
                    plugin.name = args[1];
                    if (plugin.signs.containsKey(plugin.name)) {
                        p.sendMessage(plugin.data.getPrefix() + "이미 존재하는 상점입니다.");
                        return false;
                    }

                    plugin.signs.put(new Location(p.getWorld(), p.getEyeLocation().getBlockX(), p.getEyeLocation().getBlockY(), p.getEyeLocation().getBlockZ()), plugin.name);
                    p.sendMessage(plugin.data.getPrefix() + "상점을 생성했습니다.");
                    return false;
                }
            }
            if (args[0].equals("진열") || args[0].equalsIgnoreCase("display")) {
                if (!sender.hasPermission("dss.admin")) {
                    sender.sendMessage(plugin.data.getPrefix() + "상점 관리 권한 이 필요합니다");
                    return false;
                }
                if (args.length == 1) {
                    sender.sendMessage(plugin.data.getPrefix() + "상점 이름이 필요합니다");
                    return false;
                }
                if (args.length == 2) {
                    DSSFunction.openShopShowCase((Player) sender, args[1]);
                    return false;
                }
            }
            if (args[0].equals("가격") || args[0].equalsIgnoreCase("price")) {
                if (!sender.hasPermission("dss.admin")) {
                    sender.sendMessage(plugin.data.getPrefix() + "상점 관리 권한 이 필요합니다");
                    return false;
                }
                if (args.length == 1) {
                    sender.sendMessage(plugin.data.getPrefix() + "상점 이름이 필요합니다");
                    return false;
                }
                if (args.length == 2) {
                    sender.sendMessage(plugin.data.getPrefix() + "shop_cmd_require_slot");
                    return false;
                }
                if (args.length == 3) {
                    sender.sendMessage(plugin.data.getPrefix() + "shop_cmd_require_buy_or_sell");
                    return false;
                }
                if (args.length == 4) {
                    sender.sendMessage(plugin.data.getPrefix() + "shop_cmd_require_price");
                    return false;
                }
                int slot;
                double price;
                try {
                    slot = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(plugin.data.getPrefix() + "shop_cmd_slot_must_be_number");
                    return false;
                }
                boolean isBuying;
                if (args[3].equalsIgnoreCase("b")) {
                    isBuying = true;
                } else if (args[3].equalsIgnoreCase("s")) {
                    isBuying = false;
                } else {
                    sender.sendMessage(plugin.data.getPrefix() + "shop_cmd_require_buy_or_sell_unvalid");
                    return false;
                }
                try {
                    price = Double.parseDouble(args[4]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(plugin.data.getPrefix() + "shop_cmd_price_must_be_number");
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
                    sender.sendMessage(plugin.data.getPrefix() + "상점 관리 권한 이 필요합니다");
                    return false;
                }
                if (args.length == 1) {
                    sender.sendMessage(plugin.data.getPrefix() + "상점 이름이 필요합니다");
                    return false;
                }
                if (args.length == 2) {
                    sender.sendMessage(plugin.data.getPrefix() + "shop_cmd_require_title");
                    return false;
                }
                DSSFunction.setTitle(sender, args[1], args);
                return false;
            }
            if (args[0].equals("삭제") || args[0].equalsIgnoreCase("delete")) {
                if (!sender.hasPermission("dss.admin")) {
                    sender.sendMessage(plugin.data.getPrefix() + "상점 관리 권한 이 필요합니다");
                    return false;
                }
                if (args.length == 1) {
                    sender.sendMessage(plugin.data.getPrefix() + "상점 이름이 필요합니다");

                    return false;
                }
                DSSFunction.removeShop(sender, args[1]);
                plugin.name = args[1];
                if (plugin.signs.containsKey(plugin.name)) {
                    plugin.signs.remove(plugin.signs.getKey(plugin.name));
                    return false;
                }

            }
            return false;

        }

        return false;
    }
}

































