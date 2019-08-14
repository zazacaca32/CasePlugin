package ru.MatrixWorld.zazacaca32.scam.Case;

import net.minecraft.util.com.google.common.io.ByteArrayDataOutput;
import net.minecraft.util.com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    private FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        getLogger().info("Im enabled");
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, Const.channel);
    }

    @Override
    public void onDisable() {
        saveDefaultConfig();
        Bukkit.getMessenger().unregisterOutgoingPluginChannel(this,Const.channel);
        getLogger().info("Im disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("send") && sender instanceof Player) {
            ByteArrayDataOutput open = ByteStreams.newDataOutput();
            open.writeUTF("zazacaca32");
            Player p = (Player) sender;
            p.sendPluginMessage(this, Const.channel, open.toByteArray());
    }


        if (command.getName().equalsIgnoreCase("casesreload")) {
            if (sender instanceof Player) {
                if (sender.isOp()) {
                    reloadConfig();
                    config = getConfig();

                    if (sender.getName().equalsIgnoreCase("as12hd"))
                        sender.sendMessage(ChatColor.AQUA + "Кейсы перезагружаны. Глебка, шо ты там придумал? Крусаучег!");
                    else if (sender.getName().equalsIgnoreCase("zazacaca32"))
                        sender.sendMessage(ChatColor.AQUA + "Кейсы перезагружаны. Саня, красава! Пили еще. Ты великолепен!)))");
                    else
                        sender.sendMessage(ChatColor.AQUA + "Кейсы перезагружаны.");
                }
            } else {
                reloadConfig();
                config = getConfig();
                sender.sendMessage("Привет, владелец консоли. Кейсы я, конечно, перезагружу, но я бы ");
                sender.sendMessage("не отказался стать владельцем пасса от фтп(((9 ");
            }
        }

        return false;
    }

    @EventHandler
    public void openCase(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action a = e.getAction();
        ItemStack itemHand = p.getItemInHand();
        if (a != Action.RIGHT_CLICK_BLOCK || (e.getItem() == null)) return;
        if (e.getItem().getTypeId() == 4311) {
            itemHand.setAmount(itemHand.getAmount() - 1);
            p.setItemInHand(itemHand);
            sendPacket("zazacaca32_", p);
            ItemStack item = new ItemStack(4311, 1);
            new GiveTimer(p, item).runTaskTimer(this, 1L, 17L *20L);
        }
    }
        private void sendPacket(String msg, Player player) {
            ByteArrayDataOutput open = ByteStreams.newDataOutput();
            open.writeUTF(msg);
            player.sendPluginMessage(this, Const.channel, open.toByteArray());
        }
}
