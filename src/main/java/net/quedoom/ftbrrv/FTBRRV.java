package net.quedoom.ftbrrv;

import dev.ftb.mods.ftbquests.FTBQuests;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(FTBRRV.MODID)
public class FTBRRV {
    public static final String MODID = "ftbrrv";
    public static final Logger LOGGER = LogUtils.getLogger();

    public FTBRRV() {
        if (ModList.get().isLoaded("rrv"))
            FTBQuests.setRecipeModHelper(new RRVRecipeHelper());
    }
}
