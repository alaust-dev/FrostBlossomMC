package de.alaust.frostblossom.item.blockdroppable

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack

class BlockBreakListener : Listener {
    companion object {
        private val mappedTypes = HashMap<Material, ArrayList<BlockDropData>>()

        fun registerBlockDropItem(blockDroppable: BlockDroppable) {
            val blockDropDataArray = blockDroppable.getBlockDropData()
            for (blockDropData in blockDropDataArray) {
                if (!mappedTypes.containsKey(blockDropData.sourceMaterial)) {
                    mappedTypes[blockDropData.sourceMaterial] = ArrayList()
                }
                mappedTypes[blockDropData.sourceMaterial]!!.add(blockDropData)
            }
        }
    }

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val destroyedBlock = event.block
        val destroyedBlockType = destroyedBlock.type

        if (!mappedTypes.containsKey(destroyedBlockType)) {
            return
        }

        for (blockDrop in mappedTypes[destroyedBlockType]!!) {
            if (blockDrop.dropPercentChance > Math.random()) {
                val drop = ItemStack(blockDrop.dropItemStack)

                drop.amount = blockDrop.dropAmountRange.getRandom()
                destroyedBlock.world.dropItemNaturally(destroyedBlock.location, drop)
            }
        }
    }
}