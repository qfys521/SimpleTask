package cn.qfys521.simpleTask.impl

import cn.qfys521.simpleTask.core.Dialogue
import cn.qfys521.simpleTask.core.DialogueSystem
import cn.qfys521.simpleTask.core.NPC
import cn.qfys521.simpleTask.core.TaskList
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.Villager
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

/**
 * SimpleNPC 是 NPC 接口的一个简单实现。
 *
 * @property id NPC 的唯一标识符
 * @property name NPC 的名称
 * @property profession NPC 的职业
 * @property location NPC 的位置 (x, y, z)
 * @property taskList NPC 的任务列表
 * @property dialogueSystem NPC 的对话系统
 * @property bukkitEntity 关联的 Bukkit 实体
 */
class SimpleNPC(
    override val id: String,
    override val name: String,
    override val profession: String,
    override val location: Triple<Int, Int, Int>,
    private val taskList: TaskList,
    private val dialogueSystem: DialogueSystem,
    var bukkitEntity: Villager? = null // 改为可空类型，允许为空
) : NPC {

    private val plugin: JavaPlugin = JavaPlugin.getProvidingPlugin(SimpleNPC::class.java)

    init {
        // 如果 bukkitEntity 不存在，则生成村民
        if (bukkitEntity == null || bukkitEntity!!.isDead) {
            spawnVillager()
        }
    }

    /**
     * 生成村民并设置属性。
     */
    fun spawnVillager() {
        val world = plugin.server.worlds[0] // 获取主世界
        val loc = Location(world, location.first.toDouble(), location.second.toDouble(), location.third.toDouble())

        // 生成村民
        bukkitEntity = world.spawnEntity(loc, EntityType.VILLAGER) as Villager

        // 设置村民属性
        bukkitEntity?.let { villager ->
            villager.customName = name
            villager.isCustomNameVisible = true
            villager.profession = Villager.Profession.valueOf(profession.uppercase())
            villager.isInvulnerable = true // 无敌
            villager.addPotionEffect(PotionEffect(PotionEffectType.SLOW, Int.MAX_VALUE, 255, false, false)) // 不可移动
            villager.setAI(false) // 禁用 AI
        }
    }

    /**
     * 与玩家交互。
     *
     * @param player 与 NPC 交互的玩家
     */
    override fun interactWithPlayer(player: Player) {
        val currentDialogue = dialogueSystem.getCurrentDialogue()
        if (currentDialogue != null) {
            handleDialogue(player, currentDialogue)
        } else {
            handleTask(player)
        }
    }

    /**
     * 处理对话逻辑。
     *
     * @param player 与 NPC 交互的玩家
     * @param dialogue 当前的对话
     */
    private fun handleDialogue(player: Player, dialogue: Dialogue) {
        // 发送 NPC 的对话消息
        player.sendMessage("${ChatColor.GREEN}$name: ${dialogue.npcMessage}")

        // 检查是否需要提交物品
        val requiredItem = dialogue.requiredItem?.let { it.toItemStack() }
        if (requiredItem != null) {
            if (player.inventory.containsAtLeast(requiredItem, requiredItem.amount)) {
                // 移除玩家物品
                player.inventory.removeItem(requiredItem)

                // 给予奖励物品
                dialogue.rewardItem?.let {
                    val reward = it.toItemStack()
                    if (reward != null) {
                        player.inventory.addItem(reward)
                        player.sendMessage("${ChatColor.GOLD}你获得了 ${reward.amount}x ${reward.type.name}！")
                    }
                }

                player.sendMessage("${ChatColor.GREEN}$name: 谢谢你提交了物品！")
            } else {
                player.sendMessage("${ChatColor.RED}$name: 你需要 ${requiredItem.amount}x ${requiredItem.type.name} 来完成这个对话。")
                return
            }
        }

        // 显示玩家选项
        dialogue.playerOptions.forEachIndexed { index, option ->
            player.sendMessage("${ChatColor.YELLOW}[$index] $option")
        }

        // 如果有下一轮对话，则更新对话系统
        dialogue.nextDialogueId?.let {
            dialogueSystem.nextDialogue()
        }
    }

    /**
     * 处理任务逻辑。
     *
     * @param player 与 NPC 交互的玩家
     */
    private fun handleTask(player: Player) {
        val task = taskList.getCurrentTask()
        if (task == null) {
            player.sendMessage("${ChatColor.YELLOW}$name: 我现在没有任务给你。")
            return
        }

        // 处理任务逻辑
        task.onStart(player)
    }
}