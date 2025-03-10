import cn.qfys521.simpleTask.configure.database.NPCManagerDatabaseImpl
import cn.qfys521.simpleTask.impl.SimpleNPC
import cn.qfys521.simpleTask.impl.SimpleTask
import cn.qfys521.simpleTask.model.DialogueData
import cn.qfys521.simpleTask.model.ItemStackData
import cn.qfys521.simpleTask.model.NPCData
import java.util.UUID
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class TestSimpleNPCTask {
    val npcManager = NPCManagerDatabaseImpl()

    val task = SimpleTask(
        taskName = "收集木材",
        taskUuid = UUID.randomUUID().toString(),
        taskDescription = "收集10个橡木",
        taskText = listOf("帮助村民收集木材。")
    )

    val dialogue = DialogueData(
        npcMessage = "你好，你能给我5个橡木吗？",
        playerOptions = listOf("好的", "拒绝"),
        requiredItem = ItemStackData.fromItemStack(ItemStack(Material.OAK_LOG, 5)),
        rewardItem = ItemStackData.fromItemStack(ItemStack(Material.GOLD_NUGGET, 3))
    )

    val npcData = NPCData(
        id = "villager_1",
        name = "村民",
        profession = "Farmer",
        location = Triple(100, 64, 200),
        taskList = TODO("listOf(task)"),
        dialogueSystem = listOf(dialogue)
    )

    val npc = SimpleNPC(
        npcData.id,
        npcData.name,
        npcData.profession,
        npcData.location,
        taskList = TODO("npcData.taskList.map { it.toTask() }"),
        dialogueSystem = TODO("npcData.dialogueSystem.map { it.toDialogueSystem() }"),
    )

    // 注册 NPC
    fun test() {
        npcManager.registerNPC(npc.bukkitEntity!!, npc)
    }
}