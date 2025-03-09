package cn.qfys521.simpleTask.core

import org.bukkit.entity.Player

/**
 * 表示一个NPC接口，包含NPC的ID、名称、职业、位置和与玩家互动的方法。
 */
interface NPC {
    /**
     * 获取NPC的ID。
     *
     * @return NPC的ID
     */
    fun id(): String

    /**
     * 获取NPC的名称。
     *
     * @return NPC的名称
     */
    fun name(): String

    /**
     * 获取NPC的职业。
     *
     * @return NPC的职业
     */
    fun profession(): String

    /**
     * 获取NPC的位置。
     *
     * @return NPC的位置，使用三元组表示（x, y, z）
     */
    fun location(): Triple<Int, Int, Int>

    /**
     * 与玩家互动的方法。
     *
     * @param player 与之互动的玩家
     */
    fun interactWithPlayer(player: Player)

    /**
     * 生成村民并设置属性。
     */
    fun spawnVillager()
}