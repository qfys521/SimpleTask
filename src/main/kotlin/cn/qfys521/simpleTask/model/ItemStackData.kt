package cn.qfys521.simpleTask.model

import java.io.Serializable
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/**
 * ItemStackData 类用于表示物品堆的数据。
 *
 * @property type 物品的类型
 * @property amount 物品的数量
 * @property displayName 物品的显示名称
 */
data class ItemStackData(
    val type: String,
    val amount: Int,
    val displayName: String? = null
) : Serializable {
    companion object {
        /**
         * 从 ItemStack 创建 ItemStackData 对象。
         *
         * @param item ItemStack 对象
         * @return ItemStackData 对象
         */
        fun fromItemStack(item: ItemStack): ItemStackData {
            return ItemStackData(
                type = item.type.name,
                amount = item.amount,
                displayName = item.itemMeta?.displayName
            )
        }
    }

    /**
     * 将 ItemStackData 转换为 ItemStack。
     *
     * @return ItemStack 对象
     */
    fun toItemStack(): ItemStack {
        val item = ItemStack(Material.valueOf(type), amount)
        displayName?.let {
            val meta: ItemMeta = item.itemMeta!!
            meta.setDisplayName(it)
            item.itemMeta = meta
        }
        return item
    }
}