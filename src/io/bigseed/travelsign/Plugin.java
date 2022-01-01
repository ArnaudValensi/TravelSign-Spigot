package io.bigseed.travelsign;

import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new TravelSignClickedListener(this), this);
    }
}
