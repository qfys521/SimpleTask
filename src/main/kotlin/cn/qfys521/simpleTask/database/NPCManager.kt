@file:Suppress("SqlResolve")

package cn.qfys521.simpleTask.database

import cn.qfys521.simpleTask.core.NPC
import cn.qfys521.simpleTask.model.NPCData
import com.google.gson.Gson
import org.bukkit.entity.Entity

/**
 * NPC管理类，用于管理NPC的注册、保存和加载。
 */
class NPCManager {
    private val gson = Gson()
    private val entityToNPC = mutableMapOf<Entity, NPC>()

    /**
     * 根据实体获取对应的NPC。
     *
     * @param entity 实体
     * @return 对应的NPC，如果没有则返回null
     */
    fun getNPCByEntity(entity: Entity): NPC? {
        return entityToNPC[entity]
    }

    /**
     * 注册一个NPC。
     *
     * @param entity 实体
     * @param npc NPC
     */
    fun registerNPC(entity: Entity, npc: NPC) {
        entityToNPC[entity] = npc
    }

    /**
     * 保存NPC数据到数据库。
     *
     * @param data NPC数据
     */
    fun saveNPC(data: NPCData) {
        DatabaseUtil.getConnection().prepareStatement("INSERT OR REPLACE INTO npcs (id, data) VALUES (?, ?)")
            .apply {
                setString(1, data.id)
                setString(2, gson.toJson(data))
                executeUpdate()
            }
    }

    /**
     * 从数据库加载NPC数据。
     *
     * @param id NPC的ID
     * @return NPC数据，如果没有则返回null
     */
    fun loadNPC(id: String): NPCData? {
        return DatabaseUtil.getConnection().prepareStatement("SELECT data FROM npcs WHERE id = ?").run {
            setString(1, id)
            val rs = executeQuery()
            if (rs.next()) gson.fromJson(rs.getString("data"), NPCData::class.java) else null
        }
    }
}