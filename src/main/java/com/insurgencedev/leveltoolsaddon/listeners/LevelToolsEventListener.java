package com.insurgencedev.leveltoolsaddon.listeners;

import me.byteful.plugin.leveltools.api.event.LevelToolsXPIncreaseEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.insurgencedev.insurgenceboosters.api.IBoosterAPI;
import org.insurgencedev.insurgenceboosters.models.booster.GlobalBoosterManager;
import org.insurgencedev.insurgenceboosters.settings.IBoostersPlayerCache;

public final class LevelToolsEventListener implements Listener {

    @EventHandler
    public void onReceive(LevelToolsXPIncreaseEvent event) {
        String type = "Tool";
        final String NAMESPACE = "LEVEL_TOOLS";
        double totalMulti = 1;

        IBoostersPlayerCache.BoosterFindResult pResult = IBoosterAPI.getCache(event.getPlayer()).findActiveBooster(type, NAMESPACE);
        if (pResult instanceof IBoostersPlayerCache.BoosterFindResult.Success boosterResult) {
            totalMulti += getMulti(boosterResult.getBooster().getMultiplier());
        }

        GlobalBoosterManager.BoosterFindResult gResult = IBoosterAPI.getGlobalBoosterManager().findBooster(type, NAMESPACE);
        if (gResult instanceof GlobalBoosterManager.BoosterFindResult.Success boosterResult) {
            totalMulti += getMulti(boosterResult.getBooster().getMultiplier());
        }

        event.setNewXp(event.getNewXp() * totalMulti);
    }

    private double getMulti(double amount) {
        return (amount >= 1) ? amount - 1 : amount;
    }
}
