package net.roaringmind.gentest;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import static net.minecraft.server.command.CommandManager.literal;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenTestMod implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "gentest";
    public static final String MOD_NAME = "GenTest Mod";

    public static final Block SAJT = new Block(FabricBlockSettings.of(Material.ORGANIC_PRODUCT).hardness(0.1F).breakByTool(FabricToolTags.AXES, 0));

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");

        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(literal("foo").executes(context -> {
                System.out.println("foo");
                return 1;
            }));
        });
        Registry.register(Registry.BLOCK, new Identifier("gentest", "sajt"), SAJT);
        Registry.register(Registry.ITEM, new Identifier("gentest", "sajt"), new BlockItem(SAJT, new Item.Settings().group(ItemGroup.MISC)));
    }

    public static void log(Level level, String message) {
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }

}
