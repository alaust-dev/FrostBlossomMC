package de.alaust.frostblossom.item.blockdroppable

import de.alaust.frostblossom.item.DropAmountRange
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.junit.jupiter.api.Test

class BlockDropDataTest {

    @Test
    fun blockDropDataIsBuildCorrectly() {
        val builtBlockDropData = BlockDropData.Builder()
            .dropItemStack(ItemStack(Material.LILY_PAD))
            .dropPercentChance(.5f)
            .dropItemStackAmount(DropAmountRange(2))
    }
}