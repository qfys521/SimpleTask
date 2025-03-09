package cn.qfys521.simpleTask.core

import org.bukkit.entity.Player

/**
 * 表示一个任务接口，包含任务的名称、UUID、描述、文本以及任务的开始、完成和失败方法。
 */
interface Task {
    /**
     * 获取任务的名称。
     *
     * @return 任务的名称
     */
    fun taskName(): String

    /**
     * 获取任务的UUID。
     *
     * @return 任务的UUID
     */
    fun taskUuid(): String

    /**
     * 获取任务的描述。
     *
     * @return 任务的描述
     */
    fun taskDescription(): String

    /**
     * 获取任务的文本。
     *
     * @return 任务的文本
     */
    fun taskText(): List<String>

    /**
     * 任务开始时调用的方法。
     *
     * @param player 执行任务的玩家
     */
    fun onStart(player: Player)

    /**
     * 任务完成时调用的方法。
     *
     * @param player 执行任务的玩家
     */
    fun onComplete(player: Player)

    /**
     * 任务失败时调用的方法。
     *
     * @param player 执行任务的玩家
     */
    fun onFail(player: Player)
}

/**
 * 表示一个任务列表接口，包含获取当前任务、进入下一个任务和检查是否有下一个任务的方法。
 */
interface TaskList {
    /**
     * 获取当前的任务。
     *
     * @return 当前的任务，如果没有则返回null
     */
    fun getCurrentTask(): Task?

    /**
     * 进入下一个任务。
     *
     * @return 下一个任务，如果没有则返回null
     */
    fun nextTask(): Task?

    /**
     * 检查是否有下一个任务。
     *
     * @return 如果有下一个任务则返回true，否则返回false
     */
    fun hasNextTask(): Boolean
}