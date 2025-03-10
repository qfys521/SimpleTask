package cn.qfys521.simpleTask.plugin

import cn.qfys521.simpleTask.configure.NCPManager
import cn.qfys521.simpleTask.configure.database.NPCManagerDatabaseImpl
import cn.qfys521.simpleTask.listener.NPCListener
import org.bukkit.plugin.java.JavaPlugin

class SimpleTaskPlugin : JavaPlugin() {
    val npcManager: NCPManager = NPCManagerDatabaseImpl()

    override fun onEnable() {
        // 注册事件监听器
        server.pluginManager.registerEvents(NPCListener(npcManager), this)
        logger.info("§aSimpleTask 插件已启用！")
    }

    override fun onDisable() {
        logger.info("§cSimpleTask 插件已禁用。")
    }
}