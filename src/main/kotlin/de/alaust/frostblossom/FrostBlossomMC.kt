package de.alaust.frostblossom

import de.alaust.frostblossom.common.annotations.FrostBlossom
import de.alaust.frostblossom.item.CustomItem
import de.alaust.frostblossom.item.blockdroppable.BlockBreakListener
import de.alaust.frostblossom.item.blockdroppable.BlockDroppable
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.reflections.Reflections

class FrostBlossomMC {
    companion object {
        private val classInstances = HashMap<Class<*>, Any?>()

        fun initialise(plugin: JavaPlugin, rootPath: String) {
            scanForFrostBlossomClasses(rootPath)

            val pluginManager = Bukkit.getPluginManager()
            pluginManager.registerEvents(BlockBreakListener(), plugin)
        }

        fun <T> instanceOf(clazz: Class<T>): T? {
            if (!classInstances.containsKey(clazz)) {
                return null
            }
            return classInstances[clazz] as T
        }

        private fun scanForFrostBlossomClasses(rootPath: String) {
            val reflectedPackage = Reflections(rootPath)
            val annotatedClasses = reflectedPackage.getTypesAnnotatedWith(FrostBlossom::class.java)

            for (clazz in annotatedClasses) {
                if(CustomItem::class.java.isAssignableFrom(clazz)) {
                    registerCustomItem(clazz as Class<CustomItem>)
                }
            }
        }

        private fun <T: CustomItem> registerCustomItem(itemClass: Class<T>) {
            val customItem = itemClass.getDeclaredConstructor().newInstance()
            classInstances[itemClass] = customItem

            if (customItem is BlockDroppable) {
                BlockBreakListener.registerBlockDropItem(customItem)
            }
        }
    }
}