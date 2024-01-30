package com.insurgencedev.leveltoolsaddon.listeners;

import me.byteful.plugin.leveltools.api.event.LevelToolsXPIncreaseEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.insurgencedev.insurgenceboosters.api.IBoosterAPI;
import org.insurgencedev.insurgenceboosters.data.BoosterFindResult;

public final class LevelToolsEventListener implements Listener {

    @EventHandler
    public void onReceive(LevelToolsXPIncreaseEvent event) {
        final String TYPE = "Tool";
        final String NAMESPACE = "LEVEL_TOOLS";
        final double[] totalMulti = {0};

        BoosterFindResult pResult = IBoosterAPI.INSTANCE.getCache(event.getPlayer()).getBoosterDataManager().findActiveBooster(TYPE, NAMESPACE);
        if (pResult instanceof BoosterFindResult.Success boosterResult) {
            totalMulti[0] += boosterResult.getBoosterData().getMultiplier();
        }

        IBoosterAPI.INSTANCE.getGlobalBoosterManager().findGlobalBooster(TYPE, NAMESPACE, globalBooster -> {
            totalMulti[0] += globalBooster.getMultiplier();
            return null;
        }, () -> null);

        if (totalMulti[0] > 0) {
            event.setNewXp(calculateAmount(event.getNewXp(), totalMulti[0]));
        }
    }

    private double calculateAmount(double amount, double multi) {
        return amount * (multi < 1 ? 1 + multi : multi);
    }
}
