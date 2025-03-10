package cn.qfys521.simpleTask.configure

import cn.qfys521.simpleTask.core.NPC
import cn.qfys521.simpleTask.model.NPCData
import org.bukkit.entity.Entity

interface NCPManager {
    fun getNPCByEntity(entity: Entity): NPC?
    fun registerNPC(entity: Entity, npc: NPC)
    fun saveNPC(data: NPCData)
    fun loadNPC(id: String): NPCData?
}