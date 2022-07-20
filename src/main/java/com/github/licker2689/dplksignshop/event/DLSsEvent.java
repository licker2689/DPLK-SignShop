package com.github.licker2689.dplksignshop.event;

import com.github.licker2689.dplksignshop.DPLKSignShop;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.darksoldier1404.dss.functions.DSSFunction.openShop;


public class DLSsEvent implements Listener {

    DPLKSignShop plugin = DPLKSignShop.getInstance();


    @EventHandler
    public void onClickSign(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        String ShopName;

        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            if (e.getClickedBlock().getType() == Material.LEGACY_SIGN_POST || e.getClickedBlock().getType() == Material.LEGACY_WALL_SIGN) {

                Location location = e.getClickedBlock().getLocation();
                ShopName = plugin.signs.get(location);
                if (ShopName.equals(null)) {
                    p.sendMessage(plugin.prefix + "§a" + ShopName + "§f이 없습니다.");
                } else {
                    openShop(p, ShopName);
                }
            }
        }
    }


}
