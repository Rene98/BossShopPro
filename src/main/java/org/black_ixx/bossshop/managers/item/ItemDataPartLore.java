package org.black_ixx.bossshop.managers.item;

import org.black_ixx.bossshop.core.BSBuy;
import org.black_ixx.bossshop.managers.ClassManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class ItemDataPartLore extends ItemDataPart {

    @Override
    public ItemStack transform(ItemStack item, String used_name, String argument) {
        ItemMeta meta = item.getItemMeta();
        // although not the most beautiful solution, what it does is the following:
        // it causes all hex color codes (started by hashtag) to be transformed already.
        // Therefore, all remaining hashtags are safe to interpret as new line (traditional BossShop line separator).
        String argumentTransformed = ClassManager.manager.getStringManager().transform(argument);
        String[] parts = argumentTransformed.split("[#\\n]");
        assert meta != null;
        List<String> lore = meta.getLore();
        if (lore == null) {
            lore = new ArrayList<>();
        }
        lore.addAll(Arrays.asList(parts));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public int getPriority() {
        return PRIORITY_NORMAL;
    }

    @Override
    public boolean removeSpaces() {
        return false;
    }

    @Override
    public String[] createNames() {
        return new String[]{"lore", "description"};
    }


    @Override
    public List<String> read(ItemStack i, List<String> output) {
        if (Objects.requireNonNull(i.getItemMeta()).hasLore()) {
            int a = 1;
            for (String line : Objects.requireNonNull(i.getItemMeta().getLore())) {
                output.add("lore" + a + ":" + line.replaceAll(String.valueOf(ChatColor.COLOR_CHAR), "&"));
                a++;
            }
        }
        return output;
    }


    @Override
    public boolean isSimilar(ItemStack shop_item, ItemStack player_item, BSBuy buy, Player p) {
        ItemMeta ms = shop_item.getItemMeta();
        ItemMeta mp = player_item.getItemMeta();
        assert ms != null;
        if (ms.hasLore()) {
            assert mp != null;
            if (!mp.hasLore()) {
                return false;
            }

            if (Objects.requireNonNull(ms.getLore()).size() > Objects.requireNonNull(mp.getLore()).size()) {
                return false;
            }
            for (int i = 0; i < ms.getLore().size(); i++) {
                String shop_item_lore_line = ms.getLore().get(i);
                if (ClassManager.manager.getStringManager().checkStringForFeatures(buy == null ? null : buy.getShop(), buy, buy == null ? null : buy.getItem(), shop_item_lore_line)) {
                    shop_item_lore_line = ClassManager.manager.getStringManager().transform(shop_item_lore_line, buy, buy == null ? null : buy.getShop(), null, p);
                }
                if (!mp.getLore().get(i).equals(shop_item_lore_line)) {
                    return false;
                }
            }

        }
        return true;
    }

}
