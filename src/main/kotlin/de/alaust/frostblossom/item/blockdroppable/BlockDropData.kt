package de.alaust.frostblossom.item.blockdroppable

import de.alaust.frostblossom.item.DropAmountRange
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class BlockDropData private constructor(
    val sourceMaterial: Material,
    val dropPercentChance: Float,
    val dropItemStack: ItemStack,
    val dropAmountRange: DropAmountRange
) {
    data class Builder(
        var sourceMaterial: Material = Material.AIR,
        var dropPercentChance: Float = 1f,
        var dropItemStack: ItemStack = ItemStack(Material.AIR),
        var dropAmountRange: DropAmountRange = DropAmountRange(1)
    ) {
        fun sourceMaterial(sourceMaterial: Material) = apply { this.sourceMaterial = sourceMaterial }

        fun dropPercentChance(dropPercentChance: Float) = apply { this.sourceMaterial }

        fun dropItemStack(dropItemStack: ItemStack) = apply { this.dropItemStack = dropItemStack }

        fun dropItemStackAmount(dropAmountRange: DropAmountRange) =
            apply { this.dropAmountRange = dropAmountRange }

        fun build(): BlockDropData =
            BlockDropData(sourceMaterial, dropPercentChance, dropItemStack, dropAmountRange)
    }
}