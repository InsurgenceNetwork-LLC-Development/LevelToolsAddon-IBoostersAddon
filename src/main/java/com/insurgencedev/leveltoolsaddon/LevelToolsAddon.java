package com.insurgencedev.leveltoolsaddon;

import com.insurgencedev.leveltoolsaddon.listeners.LevelToolsEventListener;
import org.insurgencedev.insurgenceboosters.api.addon.IBoostersAddon;
import org.insurgencedev.insurgenceboosters.api.addon.InsurgenceBoostersAddon;
import org.insurgencedev.insurgenceboosters.libs.fo.Common;

@IBoostersAddon(name = "LevelToolsAddon", version = "1.0.2", author = "InsurgenceDev", description = "LevelTools Support")
public class LevelToolsAddon extends InsurgenceBoostersAddon {

    @Override
    public void onAddonReloadAblesStart() {
        if (Common.doesPluginExist("LevelTools")) {
            registerEvent(new LevelToolsEventListener());
        }
    }
}
