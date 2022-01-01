package io.bigseed.travelsign;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TravelSignClickedListener implements Listener {
    private final Plugin plugin;

    public TravelSignClickedListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerClickSign(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();

        if (block == null) {
            return;
        }

        if (!(block.getState() instanceof Sign sign)) {
            return;
        }

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Player player = event.getPlayer();
        String playerName = player.getName();
        String firstLine = sign.getLine(0);

        if (firstLine.trim().equals("[Travel]")) {
            String secondLine = sign.getLine(1);

            final String regex = "(-?\\d+),(-?\\d+),(-?\\d+)";
            final Pattern pattern = Pattern.compile(regex);
            final Matcher matcher = pattern.matcher(secondLine.trim());

            if (matcher.find()) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                int z = Integer.parseInt(matcher.group(3));

                plugin.getLogger().info("Teleporting " + playerName + " to (" + x + ", " + y + ", " + z + ")");

                Location newLocation = player.getLocation().clone();
                newLocation.setX(x);
                newLocation.setY(y);
                newLocation.setZ(z);

                player.teleport(newLocation);
            }
        }
    }
}
