package cn.qfys521.simpleTask.plugin

import cn.qfys521.simpleTask.database.NPCManager
import cn.qfys521.simpleTask.listener.NPCListener
import org.bukkit.plugin.java.JavaPlugin

class SimpleTaskPlugin : JavaPlugin() {
    private val npcManager = NPCManager()

    override fun onEnable() {
        // 注册事件监听器
        server.pluginManager.registerEvents(NPCListener(npcManager), this)
        logger.info("§aSimpleTask 插件已启用！")
    }

    override fun onDisable() {
        logger.info("§cSimpleTask 插件已禁用。")
    }
}