package cn.qfys521.simpleTask.model

import java.io.Serializable

/**
 * NPCData 类用于表示 NPC 的数据。
 *
 * @property id NPC 的唯一标识符
 * @property name NPC 的名称
 * @property profession NPC 的职业
 * @property location NPC 的位置 (x, y, z)
 * @property taskList NPC 的任务列表
 * @property dialogueSystem NPC 的对话系统
 */
data class NPCData(
    val id: String,
    val name: String,
    val profession: String,
    val location: Triple<Int, Int, Int>,
    val taskList: List<TaskData>,
    val dialogueSystem: List<DialogueData>
) : Serializable

/**
 * TaskData 类用于表示任务的数据。
 *
 * @property taskName 任务的名称
 * @property taskUuid 任务的 UUID
 * @property taskDescription 任务的描述
 * @property taskText 任务的文本
 * @property isCompleted 任务是否完成
 */
data class TaskData(
    val taskName: String,
    val taskUuid: String,
    val taskDescription: String,
    val taskText: List<String>,
    val isCompleted: Boolean = false
) : Serializable

/**
 * DialogueData 类用于表示对话的数据。
 *
 * @property npcMessage NPC 的对话消息
 * @property playerOptions 玩家选项列表
 * @property requiredItem 完成对话所需的物品
 * @property rewardItem 完成对话的奖励物品
 * @property nextDialogueId 下一轮对话的 ID
 */
data class DialogueData(
    val npcMessage: String,
    val playerOptions: List<String>,
    val requiredItem: ItemStackData? = null,
    val rewardItem: ItemStackData? = null,
    val nextDialogueId: String? = null
) : Serializable