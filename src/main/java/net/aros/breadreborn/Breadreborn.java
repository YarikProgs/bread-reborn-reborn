package net.aros.breadreborn;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Breadreborn implements ModInitializer {
    public static final String MOD_ID = "breadreborn";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Hello bread reborn!");
    }
}
