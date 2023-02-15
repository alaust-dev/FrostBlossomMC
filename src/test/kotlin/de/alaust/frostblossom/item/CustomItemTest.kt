package de.alaust.frostblossom.item

import de.alaust.frostblossom.test.item.TestItem
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemFactory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.junit.jupiter.api.BeforeAll
import org.mockito.Mockito
import kotlin.test.Test
import kotlin.test.assertTrue

class CustomItemTest {

    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            val mockedBukkit = Mockito.mockStatic(Bukkit::class.java)
            val mockedItemFactory = Mockito.mock(ItemFactory::class.java)
            val mockedItemMeta = Mockito.mock(ItemMeta::class.java)

            mockedBukkit.`when`<ItemFactory> { apply { Bukkit.getItemFactory() } }.thenReturn(mockedItemFactory)
            mockedBukkit.`when`<ItemMeta> { apply { Bukkit.getItemFactory().getItemMeta(Material.LILY_PAD) } }
                .thenReturn(mockedItemMeta)
        }
    }

    @Test
    fun customItemSimilarTest() {
        val itemStack = ItemStack(Material.LILY_PAD)
        val itemMeta = itemStack.itemMeta
        itemMeta.setCustomModelData(10)
        itemStack.itemMeta = itemMeta

        val customItem = TestItem(itemStack)
        val similarItem = ItemStack(Material.LILY_PAD)
        similarItem.itemMeta = itemMeta

        assertTrue(customItem.isSimilarTo(similarItem))
    }

    @Test
    fun customItemSimilarWithItemMetaNullTest() {
        val itemStack = ItemStack(Material.LILY_PAD)
        val customItem = TestItem(itemStack)
        val similarItem = ItemStack(Material.LILY_PAD)

        assertTrue(customItem.isSimilarTo(similarItem))
    }
}