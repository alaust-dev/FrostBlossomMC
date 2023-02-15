package de.alaust.frostblossom.item

import org.bukkit.inventory.ItemStack

abstract class CustomItem(
    val itemStack: ItemStack
) {

    fun isSimilarTo(similarStack: ItemStack): Boolean {
        val similarItemMeta = similarStack.itemMeta
        val itemMeta = itemStack.itemMeta



        return itemStack.type == similarStack.type
                && ((itemMeta == null && similarItemMeta == null)
                || itemMeta.customModelData == similarItemMeta.customModelData)
    }
}