package ru.MatrixWorld.zazacaca32.scam.Case;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class GiveTimer extends BukkitRunnable {

    private Player p;
    private ItemStack item;

    public GiveTimer(Player player, ItemStack item) {
        this.p = player;
        this.item = item;
    }

    @Override
    public void run() {
        Location loc = p.getTargetBlock(null, 5).getLocation();
        loc.setY(loc.getBlockY()+2);
        p.getWorld().dropItem(loc, item);
        cancel();
    }
}