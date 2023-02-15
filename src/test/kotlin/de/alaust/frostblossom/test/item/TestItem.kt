package de.alaust.frostblossom.test.item

import de.alaust.frostblossom.common.annotations.FrostBlossom
import de.alaust.frostblossom.item.CustomItem
import de.alaust.frostblossom.item.blockdroppable.BlockDropData
import de.alaust.frostblossom.item.blockdroppable.BlockDroppable
import org.bukkit.inventory.ItemStack

@FrostBlossom
class TestItem(itemStack: ItemStack,
               private val blockDropData: BlockDropData) : CustomItem(itemStack), BlockDroppable {
    override fun getBlockDropData(): BlockDropData {
        return blockDropData
    }
}