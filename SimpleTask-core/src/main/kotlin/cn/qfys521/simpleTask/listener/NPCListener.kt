package cn.qfys521.simpleTask.listener

import cn.qfys521.simpleTask.configure.NCPManager
import cn.qfys521.simpleTask.core.NPC
import org.bukkit.entity.Villager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent

/**
 * NPC监听器类，用于处理与NPC相关的事件。
 *
 * @property npcManager NPC管理器
 */
class NPCListener(private val npcManager: NCPManager) : Listener {

    /**
     * 处理实体死亡事件。
     *
     * @param event 实体死亡事件
     */
    @EventHandler
    fun onEntityDeath(event: EntityDeathEvent) {
        val entity = event.entity
        if (entity is Villager) {
            // 检查是否为NPC的村民
            val npc = npcManager.getNPCByEntity(entity)
            if (npc is NPC) {
                // 重新生成村民
                npc.spawnVillager()
            }
        }
    }


}