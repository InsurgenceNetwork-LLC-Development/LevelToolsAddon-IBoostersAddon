package com.insurgencedev.leveltoolsaddon;

import com.insurgencedev.leveltoolsaddon.listeners.LevelToolsEventListener;
import org.bukkit.Bukkit;
import org.insurgencedev.insurgenceboosters.api.addon.IBoostersAddon;
import org.insurgencedev.insurgenceboosters.api.addon.InsurgenceBoostersAddon;

@IBoostersAddon(name = "LevelToolsAddon", version = "1.0.1", author = "InsurgenceDev", description = "LevelTools Support")
public class LevelToolsAddon extends InsurgenceBoostersAddon {

    @Override
    public void onAddonReloadablesStart() {
        if (Bukkit.getPluginManager().isPluginEnabled("LevelTools")) {
            registerEvent(new LevelToolsEventListener());
        }
    }
}
