package de.alaust.frostblossom

import de.alaust.frostblossom.test.item.TestItem
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemFactory
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin
import org.junit.jupiter.api.BeforeAll
import org.mockito.Mockito
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class FrostBlossomMCTest {

    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            val mockJavaPlugin = Mockito.mock(JavaPlugin::class.java)
            val mockedBukkit = Mockito.mockStatic(Bukkit::class.java)
            val mockedPluginManager = Mockito.mock(PluginManager::class.java)
            val mockedItemFactory = Mockito.mock(ItemFactory::class.java)

            mockedBukkit.`when`<PluginManager> { apply { Bukkit.getPluginManager() } }.thenReturn(mockedPluginManager)
            mockedBukkit.`when`<ItemFactory> { apply { Bukkit.getItemFactory() } }.thenReturn(mockedItemFactory)
            FrostBlossomMC.initialise(mockJavaPlugin, "de.alaust.frostblossom.item")
        }
    }

    @Test
    fun testItemIsInitialised() {
        val testItemInstance = FrostBlossomMC.instanceOf(TestItem::class.java)
        assertNotNull(testItemInstance)
    }

    @Test
    fun testCustomItemItemStackIsLilyPad() {
        val testItemInstance = FrostBlossomMC.instanceOf(TestItem::class.java)!!
        assertEquals(Material.LILY_PAD, testItemInstance.itemStack.type)
    }
}