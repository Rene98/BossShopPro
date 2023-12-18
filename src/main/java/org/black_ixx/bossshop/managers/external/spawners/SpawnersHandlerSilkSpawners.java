package org.black_ixx.bossshop.managers.external.spawners;

import de.dustplanet.util.SilkUtil;
import org.black_ixx.bossshop.managers.misc.InputReader;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;


public class SpawnersHandlerSilkSpawners implements ISpawnerHandler, ISpawnEggHandler {

    private final SilkUtil util;


    public SpawnersHandlerSilkSpawners() {
        util = SilkUtil.hookIntoSilkSpanwers();
    }

    public ItemStack transformSpawner(ItemStack i, String entityName) {
        EntityType type = InputReader.readEntityType(entityName);
        if (type == null) {
            return null;
        }
        return util.newSpawnerItem(String.valueOf(type.getTypeId()), null, i.getAmount(), false);
    }

    public ItemStack transformEgg(ItemStack i, String entityName) {
        EntityType type = InputReader.readEntityType(entityName);
        if (type == null) {
            return null;
        }
        return util.newEggItem(String.valueOf(type.getTypeId()), i.getAmount(),entityName);
    }


    public String readSpawner(ItemStack i) {
        short EntityId = Short.parseShort(Objects.requireNonNull(util.getStoredSpawnerItemEntityID(i)));
        return util.getCreatureName(String.valueOf(EntityId));
    }

    public String readEgg(ItemStack i) {
        short EntityId = Short.parseShort(Objects.requireNonNull(util.getStoredEggEntityID(i)));
        return util.getCreatureName(String.valueOf(EntityId));
    }
}
