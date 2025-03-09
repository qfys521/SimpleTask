package cn.qfys521.simpleTask.impl

import cn.qfys521.simpleTask.core.Task
import org.bukkit.entity.Player

/**
 * SimpleTask 是 Task 接口的一个简单实现。
 *
 * @property taskName 任务的名称
 * @property taskUuid 任务的 UUID
 * @property taskDescription 任务的描述
 * @property taskText 任务的文本
 */
class SimpleTask(
    override val taskName: String,
    override val taskUuid: String,
    override val taskDescription: String,
    override val taskText: List<String>
) : Task {

    /**
     * 任务开始时调用的方法。
     *
     * @param player 执行任务的玩家
     */
    override fun onStart(player: Player) {
        player.sendMessage("§a任务开始：$taskName")
    }

    /**
     * 任务完成时调用的方法。
     *
     * @param player 执行任务的玩家
     */
    override fun onComplete(player: Player) {
        player.sendMessage("§6任务完成：$taskName")
    }

    /**
     * 任务失败时调用的方法。
     *
     * @param player 执行任务的玩家
     */
    override fun onFail(player: Player) {
        player.sendMessage("§c任务失败：$taskName")
    }
}