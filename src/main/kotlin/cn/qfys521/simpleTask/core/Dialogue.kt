package cn.qfys521.simpleTask.core

import cn.qfys521.simpleTask.model.ItemStackData

/**
 * 表示一个对话接口，包含NPC消息、玩家选项、所需物品、奖励物品和下一个对话ID。
 */
interface Dialogue {
    /**
     * 获取NPC的消息。
     *
     * @return NPC的消息
     */
    val npcMessage: String

    /**
     * 获取玩家的选项列表。
     *
     * @return 玩家选项列表
     */
    val playerOptions: List<String>

    /**
     * 获取所需物品。
     *
     * @return 所需物品，如果没有则返回null
     */
    val requiredItem: ItemStackData?

    /**
     * 获取奖励物品。
     *
     * @return 奖励物品，如果没有则返回null
     */
    val rewardItem: ItemStackData?

    /**
     * 获取下一个对话的ID。
     *
     * @return 下一个对话的ID，如果没有则返回null
     */
    val nextDialogueId: String?
}

/**
 * 表示一个对话系统接口，包含获取当前对话、进入下一个对话和重置对话的方法。
 */
interface DialogueSystem {
    /**
     * 获取当前的对话。
     *
     * @return 当前的对话，如果没有则返回null
     */
    fun getCurrentDialogue(): Dialogue?

    /**
     * 进入下一个对话。
     *
     * @return 下一个对话，如果没有则返回null
     */
    fun nextDialogue(): Dialogue?

    /**
     * 重置对话系统。
     */
    fun reset()
}